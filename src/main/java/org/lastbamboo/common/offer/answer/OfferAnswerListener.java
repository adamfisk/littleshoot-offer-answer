package org.lastbamboo.common.offer.answer;


/**
 * Listener for events from an offer/answer exchange. 
 */
public interface OfferAnswerListener<T>
    {

    /**
     * Called when an offer/answer exchange failed, with definitions of 
     * failure depending on the specific type of offer/answer.
     * 
     * @param offerAnswer The {@link OfferAnswer} that failed.
     */
    void onOfferAnswerFailed(OfferAnswer offerAnswer);

    void onTcpSocket(T sock);
    void onUdpSocket(T sock);


    }
