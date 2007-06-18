package org.lastbamboo.common.offer;

import java.io.IOException;

/**
 * Interface for classes that create offers for offer/answer protocols such as
 * SIP.
 */
public interface OfferGenerator
    {

    /**
     * Generates an offer.
     * 
     * @return The offer bytes.
     * @throws IOException If there's an IO error generating the offer.
     */
    byte[] generateOffer() throws IOException;

    }
