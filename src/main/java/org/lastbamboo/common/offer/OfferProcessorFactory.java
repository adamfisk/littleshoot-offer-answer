package org.lastbamboo.common.offer;

import org.lastbamboo.common.answer.AnswerProcessor;

/**
 * Factory for creating classes for processing offer messages.
 */
public interface OfferProcessorFactory
    {

    /**
     * Creates a new class for processing an offer.  Many offer processors
     * will then prompt the {@link AnswerProcessor} to process the answer
     * they provide.
     * 
     * @param answerProcessor The class for processing any possible answer
     * the offer processor might make.  In SIP, for example, an offer 
     * processor might process and incoming SDP offer and prompt the answer
     * processor to handle the SDP response.
     * @return The {@link OfferProcessor} for handling any incoming offers.
     */
    OfferProcessor createOfferProcessor();

    }
