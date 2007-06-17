package org.lastbamboo.common.offer;

import org.apache.mina.common.ByteBuffer;

/**
 * Interface for classes that wish to process "offers" in an offer/answer
 * protocol.
 */
public interface OfferProcessor
    {

    /**
     * Process the specified offer bytes.
     * 
     * @param offer The offer.
     * @return The answer to the offer.
     */
    ByteBuffer answer(final ByteBuffer offer);
    }
