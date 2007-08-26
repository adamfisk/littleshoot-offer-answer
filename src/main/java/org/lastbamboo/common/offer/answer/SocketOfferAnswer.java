package org.lastbamboo.common.offer.answer;

import java.net.Socket;

/**
 * Interface for {@link OfferAnswer} classes that are also capable of
 * creating {@link Socket}s from the offer/answer exchange.
 */
public interface SocketOfferAnswer extends OfferAnswer
    {

    /**
     * Creates the {@link Socket}.
     * 
     * @return A {@link Socket} created from the offer/answer exchange.
     */
    Socket createSocket();
    }
