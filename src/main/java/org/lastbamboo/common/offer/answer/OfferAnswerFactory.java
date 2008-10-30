package org.lastbamboo.common.offer.answer;

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
     * @throws OfferAnswerConnectException If there's an error connecting the
     * offerer.
     */
    OfferAnswer createOfferer() throws OfferAnswerConnectException;
    
    /**
     * Creates a new class for processing offers and creating answers.
     * 
     * @param offer The offer.
     * @param listener Listener for events during the offer/answer exchange. 
     * @return A new class for processing offers and creating answers.
     * @throws OfferAnswerConnectException If there's an error connecting the
     * answerer.
     */
    MediaOfferAnswer createAnswerer(ByteBuffer offer)
        throws OfferAnswerConnectException;

    /**
     * Creates an offerer that establishes a media session.
     * 
     * @return The offerer.
     * @throws OfferAnswerConnectException If there's an error connecting the
     * offerer.
     */
    MediaOfferAnswer createMediaOfferer() throws OfferAnswerConnectException;

    }
