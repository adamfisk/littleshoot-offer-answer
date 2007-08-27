package org.lastbamboo.common.offer.answer;

import java.io.IOException;
import java.net.Socket;

import org.lastbamboo.common.util.SocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that listens for creation of RUDP sockets on the server side.
 */
public class OfferAnswerListenerImpl implements OfferAnswerListener
    {

    private final Logger m_log = LoggerFactory.getLogger(getClass());
    private final SocketHandler m_socketHandler;

    /**
     * Creates a new listener for RUDP server sockets.  These get past along
     * to a socket processing class.
     * 
     * @param socketHandler The class that handles incoming sockets.
     */
    public OfferAnswerListenerImpl(final SocketHandler socketHandler)
        {
        m_socketHandler = socketHandler;
        }
    
    public void onOfferAnswerComplete(final MediaOfferAnswer offerAnswer)
        {
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
                        try
                            {
                            m_socketHandler.handleSocket(sock);
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

    }
