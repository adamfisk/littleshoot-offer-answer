package org.lastbamboo.common.offer.answer;

import java.io.IOException;

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
    
    /**
     * Tells an offerer to process its answer.
     * 
     * @param answer The answer.
     * @param offerAnswerListener Listener for offer/answer events.
     * @throws IOException If there's an error decoding the answer.
     */
    void processAnswer(ByteBuffer answer, 
        OfferAnswerListener offerAnswerListener) throws IOException;

    /**
     * Tells an answerer to process its offer.
     * 
     * @param offer The offer.
     * @param offerAnswerListener Listener for offer/answer events.
     * @throws IOException If there's an error decoding the offer.
     */
    void processOffer(ByteBuffer offer,
        OfferAnswerListener offerAnswerListener) throws IOException;

    }
