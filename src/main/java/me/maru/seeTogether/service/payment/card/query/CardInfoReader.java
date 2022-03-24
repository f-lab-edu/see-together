package me.maru.seeTogether.service.payment.card.query;

import me.maru.seeTogether.api.v1.payment.query.CardInfoQueryResponse;

public interface CardInfoReader {
    CardInfoQueryResponse getCardInfo();
}
