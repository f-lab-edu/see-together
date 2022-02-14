package me.maru.seeTogether.service.payment;

import me.maru.seeTogether.api.v1.payment.CardInfoCreateRequest;
import me.maru.seeTogether.api.v1.payment.CardInfoCreateResponse;

public interface CardInfoCreater {
    CardInfoCreateResponse create(final CardInfoCreateRequest cardInfoCreateRequest);
}
