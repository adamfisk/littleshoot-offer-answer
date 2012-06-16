package org.lastbamboo.common.offer.answer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.lastbamboo.common.sdp.api.MediaDescription;
import org.lastbamboo.common.sdp.api.SdpException;
import org.lastbamboo.common.sdp.api.SdpFactory;
import org.lastbamboo.common.sdp.api.SessionDescription;
import org.littleshoot.util.RelayingSocketHandler;
import org.littleshoot.util.SessionSocketListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that listens for creation of sockets on the server/answerer side.
 */
public class AnswererOfferAnswerListener implements OfferAnswerListener {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final String id;
    private final SessionSocketListener callSocketListener;
    private final boolean message;
    private final InetSocketAddress plainTextRelayAddress;
    //private final byte[] writeKey;
    //private final byte[] readKey;

    /**
     * Creates a new listener for RUDP server sockets. These get past along to a
     * socket processing class.
     * 
     * @param id The ID of the incoming "caller."
     * @param plainTextRelayAddress The address to relay data to.
     * @param callSocketListener The listener for incoming calls.
     * @param offer The raw offer. 
     * @throws OfferAnswerConnectException If there's an error parsing the 
     * SDP
     */
    public AnswererOfferAnswerListener(final String id, 
        final InetSocketAddress plainTextRelayAddress,
        final SessionSocketListener callSocketListener, 
        final String offer) 
        throws OfferAnswerConnectException {
        this.id = id;
        this.plainTextRelayAddress = plainTextRelayAddress;
        this.callSocketListener = callSocketListener;
        //this.writeKey = writeKey;
        //this.readKey = readKey;
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
                final RelayingSocketHandler rsh = 
                    new RelayingSocketHandler(this.plainTextRelayAddress);
                rsh.onSocket(this.id, sock);
                //socketListener.onSocket(this.id, sock);
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
