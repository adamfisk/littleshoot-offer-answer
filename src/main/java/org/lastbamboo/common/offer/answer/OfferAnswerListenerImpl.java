package org.lastbamboo.common.offer.answer;

import java.io.IOException;
import java.net.Socket;

import org.lastbamboo.common.util.SocketListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that listens for creation of RUDP sockets on the server side.
 */
public class OfferAnswerListenerImpl implements OfferAnswerListener
    {

    private final Logger m_log = LoggerFactory.getLogger(getClass());
    private final SocketListener m_socketListener;

    /**
     * Creates a new listener for RUDP server sockets.  These get past along
     * to a socket processing class.
     */
    public OfferAnswerListenerImpl(final SocketListener socketListener)
        {
        this.m_socketListener = socketListener;
        }
    
    public void onOfferAnswerComplete(final MediaOfferAnswer offerAnswer)
        {
        m_log.debug("Got offer/answer complete!!");
        final OfferAnswerMediaListener mediaListener =
            new OfferAnswerMediaListener()
            {
            public void onMedia(final OfferAnswerMedia media)
                {
                final OfferAnswerMediaVisitor<Void> mediaVisitor =
                    new OfferAnswerMediaVisitor<Void>()
                    {
    
                    public Void visitSocketMedia(
                        final OfferAnswerSocketMedia socketMedia)
                        {
                        final Socket sock = socketMedia.getSocket();
                        if (sock == null)
                            {
                            m_log.warn("Null socket!!!");
                            throw new NullPointerException("null socket!!");
                            }
                        try
                            {
                            m_log.debug("Sending server side socket to " +
                                "handler");
                            
                            // Set a timeout cap.  We set this really high
                            // because clients can request large content 
                            // lengths.  In that case, we won't read anything
                            // from them for a long time since they're doing
                            // all the reading.  Jetty uses 200 seconds by
                            // default.
                            sock.setSoTimeout(200 * 1000);
                            //final SocketListener sl = 
                            //    new RelayingSocketHandler();
                            
                            m_socketListener.onSocket(sock);
                            //sa.handleSocket();
                            }
                        catch (final IOException e)
                            {
                            m_log.debug("Exception on socket", e);
                            try
                                {
                                sock.close();
                                }
                            catch (final IOException e1)
                                {
                                m_log.warn("Could not close socket", e1);
                                }
                            }
                        return null;
                        }
                    
                    };
                media.accept(mediaVisitor);
                }
            };
        offerAnswer.startMedia(mediaListener);
        }

    public void onOfferAnswerFailed(final MediaOfferAnswer mediaOfferAnswer)
        {
        m_log.warn("Offer/Answer failed.  Not starting media for: {}",
            mediaOfferAnswer);
        }

    }
