package org.lastbamboo.common.offer.answer;

import java.net.Socket;

/**
 * Offer/answer media session that runs over a socket.
 */
public class OfferAnswerSocketMedia implements OfferAnswerMedia
    {

    private final Socket m_socket;

    /**
     * Creates a new socket session.
     * 
     * @param socket The socket the media session runs over.
     */
    public OfferAnswerSocketMedia(Socket socket)
        {
        m_socket = socket;
        }

    public <T> T accept(OfferAnswerMediaVisitor<T> mediaVisitor)
        {
        return mediaVisitor.visitSocketMedia(this);
        }

    /**
     * Accessor for the socket.
     * 
     * @return The socket.
     */
    public Socket getSocket()
        {
        return m_socket;
        }

    }
