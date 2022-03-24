package me.maru.seeTogether.service.payment.card.update;

import me.maru.seeTogether.api.v1.payment.create.CardInfoCreateRequest;
import me.maru.seeTogether.api.v1.payment.create.CardInfoCreateResponse;

public interface CardInfoUpdater {
    CardInfoCreateResponse update(final CardInfoCreateRequest cardInfoCreateRequest);
}
