package org.lastbamboo.common.offer.answer;

import java.io.IOException;
import java.net.Socket;

import org.apache.mina.common.ByteBuffer;

/**
 * Interface for classes that generate offers and process answers for 
 * offer/answer protocols. 
 */
public interface OfferAnswer
    {

    /**
     * Generates an offer.
     * 
     * @return The offer.
     */
    byte[] generateOffer();

    /**
     * Generates an answer.
     * 
     * @return The answer.
     */
    byte[] generateAnswer();
    
    Socket createSocket(ByteBuffer answer) throws IOException;

    /**
     * Tells an answerer to process its offer.
     * 
     * @param offer The offer.
     * @throws IOException If there's an error decoding the offer.
     */
    void processOffer(ByteBuffer offer) throws IOException;

    }
