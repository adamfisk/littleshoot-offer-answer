package org.lastbamboo.common.offer.answer;


/**
 * Listener for events from an offer/answer exchange. 
 */
public interface OfferAnswerListener
    {

    /**
     * Called when the offer/answer exchange has completed.  This includes
     * any processing associated with the exchange.  This is implementation
     * dependent, but with ICE, for example, the offer/answer might be 
     * considered completed when ICE has finished processing.
     * 
     * @param mediaOfferAnswer The {@link MediaOfferAnswer} that's complete. 
     */
    void onOfferAnswerComplete(MediaOfferAnswer mediaOfferAnswer);

    }
