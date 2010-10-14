package org.lastbamboo.common.offer.answer;

import java.net.URI;

public interface Offerer 
    {

    void offer(URI sipUri, byte[] offer, 
        OfferAnswerTransactionListener transactionListener);

    }
