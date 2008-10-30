package org.lastbamboo.common.offer.answer;


/**
 * Offer/answer exchange that generates a media session.
 */
public interface MediaOfferAnswer extends OfferAnswer
    {

    /**
     * Starts exchanging media.
     * 
     * @param mediaListener The listener to notify of media events.
     */
    void startMedia(OfferAnswerMediaListener mediaListener);

    /**
     * Perform any necessary close operations for the media.
     */
    void close();


    }
