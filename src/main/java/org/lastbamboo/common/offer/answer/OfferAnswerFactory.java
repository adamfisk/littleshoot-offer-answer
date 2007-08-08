package org.lastbamboo.common.offer.answer;

import java.io.IOException;

import org.apache.mina.common.ByteBuffer;

/**
 * Interface for factories that create classes the create offers and process
 * answers. 
 */
public interface OfferAnswerFactory
    {

    /**
     * Creates a new class for creating offers and processing answers.
     * 
     * @return A new class for creating offers and processing answers.
     */
    OfferAnswer createOfferer();
    
    /**
     * Creates a new class for processing offers and creating answers.
     * 
     * @param offer The offer.
     * @return A new class for processing offers and creating answers.
     * @throws IOException If we could not understand the offer from the 
     * remote host.
     */
    OfferAnswer createAnswerer(ByteBuffer offer) throws IOException;
    }
