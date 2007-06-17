package org.lastbamboo.common.answer;

import org.apache.mina.common.ByteBuffer;

/**
 * Interface for classes that wish to process "answers" in an offer/answer
 * protocol.
 */
public interface AnswerProcessor
    {

    /**
     * Process the specified answer bytes.
     * 
     * @param answer The answer.
     */
    void processAnswer(final ByteBuffer answer);
    }
