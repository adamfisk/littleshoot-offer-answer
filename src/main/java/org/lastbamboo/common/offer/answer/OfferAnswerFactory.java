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
     * @param listener Listener for events during the offer/answer exchange. 
     * @return A new class for creating offers and processing answers.
     */
    OfferAnswer createOfferer();
    
    /**
     * Creates a new class for processing offers and creating answers.
     * 
     * @param offer The offer.
     * @param listener Listener for events during the offer/answer exchange. 
     * @return A new class for processing offers and creating answers.
     * @throws IOException If we could not understand the offer from the 
     * remote host.
     */
    MediaOfferAnswer createAnswerer(ByteBuffer offer) throws IOException;

    /**
     * Creates an offerer that establishes a media session.
     * 
     * @return The offerer.
     */
    MediaOfferAnswer createMediaOfferer();

    }
