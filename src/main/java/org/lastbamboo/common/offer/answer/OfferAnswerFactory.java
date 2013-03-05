package org.lastbamboo.common.offer.answer;


/**
 * Interface for factories that create classes the create offers and process
 * answers. 
 */
public interface OfferAnswerFactory<T> {

    /**
     * Creates a new class for creating offers and processing answers.
     * 
     * @param listener Listener for events during the offer/answer exchange. 
     * @param desc The description of the offer
     * @return A new class for creating offers and processing answers.
     * @throws OfferAnswerConnectException If there's an error connecting the
     * offerer.
     */
    OfferAnswer createOfferer(OfferAnswerListener<T> listener, 
        IceMediaStreamDesc desc) throws OfferAnswerConnectException;
    
    /**
     * Creates a new class for processing offers and creating answers.
     * 
     * @param offer The offer.
     * @param listener Listener for events during the offer/answer exchange. 
     * @param useRelay Whether or not to use a relay.
     * @return A new class for processing offers and creating answers.
     * @throws OfferAnswerConnectException If there's an error connecting the
     * answerer.
     */
    OfferAnswer createAnswerer(OfferAnswerListener<T> listener, boolean useRelay) 
        throws OfferAnswerConnectException;

    boolean isAnswererPortMapped();

    int getMappedPort();

}
