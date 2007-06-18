package org.lastbamboo.common.answer;

import java.io.IOException;
import java.net.Socket;

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
     * @return The generated {@link Socket} from the answer.
     * @throws IOException If there's an IO error processing the answer.
     */
    Socket processAnswer(ByteBuffer answer) throws IOException;
    }
