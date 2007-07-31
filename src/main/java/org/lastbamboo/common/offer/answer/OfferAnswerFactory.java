package org.lastbamboo.common.offer.answer;

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
     * @return A new class for processing offers and creating answers.
     */
    OfferAnswer createAnswerer();
    }
