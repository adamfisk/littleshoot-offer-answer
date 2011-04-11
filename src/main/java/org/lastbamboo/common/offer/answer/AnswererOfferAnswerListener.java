package org.lastbamboo.common.offer.answer;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.lastbamboo.common.sdp.api.MediaDescription;
import org.lastbamboo.common.sdp.api.SdpException;
import org.lastbamboo.common.sdp.api.SdpFactory;
import org.lastbamboo.common.sdp.api.SessionDescription;
import org.littleshoot.util.SessionSocketListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that listens for creation of sockets on the server/answerer side.
 */
public class AnswererOfferAnswerListener implements OfferAnswerListener {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final SessionSocketListener socketListener;
    private final String id;
    private final SessionSocketListener callSocketListener;
    private final boolean message;

    /**
     * Creates a new listener for RUDP server sockets. These get past along to a
     * socket processing class.
     * 
     * @param id The ID of the incoming "caller."
     * @param socketListener The listener for any sockets that are created.
     * @param callSocketListener The listener for incoming calls.
     * @param offer The raw offer. 
     * @throws OfferAnswerConnectException If there's an error parsing the 
     * SDP
     */
    public AnswererOfferAnswerListener(final String id, 
        final SessionSocketListener socketListener,
        final SessionSocketListener callSocketListener, 
        final String offer) throws OfferAnswerConnectException {
        this.id = id;
        this.socketListener = socketListener;
        this.callSocketListener = callSocketListener;
        this.message = isMessage(offer);
    }

    private boolean isMessage(final String offer) 
        throws OfferAnswerConnectException {
        final SdpFactory sdpFactory = new SdpFactory();
        try {
            final SessionDescription sdp = 
                sdpFactory.createSessionDescription(offer);
            final Collection<MediaDescription> mediaDescriptions = 
                sdp.getMediaDescriptions(true);
            log.debug("Creating candidates from media descs:\n"
                    + mediaDescriptions);
            
            for (final MediaDescription mediaDesc : mediaDescriptions) {
                final String mediaType = 
                    mediaDesc.getMedia().getMediaType();
                if (StringUtils.isNotBlank(mediaType)) {
                    if (mediaType.trim().equalsIgnoreCase("message")) {
                        return true;
                    }
                }
            }
            return false;
        } catch (final SdpException e) {
            log.warn("Could not parse SDP: " + offer);
            throw new OfferAnswerConnectException("Could not parse SDP", e);
        }
    }

    public void onOfferAnswerFailed(final OfferAnswer offerAnswer) {
        log.warn("Offer/Answer failed.  Not starting media for: {}",
                offerAnswer);
    }

    public void onTcpSocket(final Socket sock) {
        log.info("Received TCP socket");
        onSocket(sock);
    }

    public void onUdpSocket(final Socket sock) {
        log.info("Received UDP socket");
        onSocket(sock);
    }

    private void onSocket(final Socket sock) {
        log.info("Got socket on listener!!");
        // Set a timeout cap. We set this really high
        // because clients can request large content
        // lengths. In that case, we won't read anything
        // from them for a long time since they're doing
        // all the reading. Jetty uses 200 seconds by
        // default.
        try {
            sock.setSoTimeout(40 * 60 * 1000);
            
            // We use the "normal" socket listener for MIME message types
            // and otherwise the call listener.
            if (message) {
                socketListener.onSocket(this.id, sock);
            } else {
                callSocketListener.onSocket(this.id, sock);
            }
        } catch (final IOException e) {
            log.warn("Exception processing socket", e);
            try {
                sock.close();
            } catch (final IOException e1) {
                log.warn("Could not close socket", e1);
            }
        }
    }
}
