package org.lastbamboo.common.offer.answer;

import org.littleshoot.mina.common.ByteBuffer;

public interface OfferAnswerMessage 
    {

    /**
     * The key to use for transactions.
     * @return
     */
    String getTransactionKey();

    ByteBuffer getBody();
    
    }
