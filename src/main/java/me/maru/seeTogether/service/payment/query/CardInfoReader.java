package me.maru.seeTogether.service.payment.query;

import me.maru.seeTogether.api.v1.payment.query.CardInfoQueryResponse;

public interface CardInfoReader {
    CardInfoQueryResponse getCardInfo();
}
