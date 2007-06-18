package org.lastbamboo.common.answer;

import java.io.IOException;

/**
 * Interface for classes that create offers for offer/answer protocols such as
 * SIP.
 */
public interface AnswerGenerator
    {

    /**
     * Generates an offer.
     * 
     * @return The offer bytes.
     * @throws IOException 
     * @throws IOException If there's an IO error generating the offer.
     */
    //byte[] generateOffer() throws IOException;

    byte[] generateAnswer() throws IOException;

    }
