package org.lastbamboo.common.answer;


/**
 * Interface for factories for classes that wish to process "answers" in an 
 * offer/answer protocol.
 */
public interface AnswerProcessorFactory
    {

    /**
     * Creates a new answer processor.
     * 
     * @return The answer processor.
     */
    AnswerProcessor createProcessor();

    }
