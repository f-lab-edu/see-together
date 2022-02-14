package me.maru.seeTogether.service.payment.create;

import me.maru.seeTogether.api.v1.payment.create.CardInfoCreateRequest;
import me.maru.seeTogether.api.v1.payment.create.CardInfoCreateResponse;
import me.maru.seeTogether.domain.user.User;

public interface CardInfoCreator {
    CardInfoCreateResponse create(final CardInfoCreateRequest cardInfoCreateRequest);

    CardInfoCreateResponse create(final User user, final CardInfoCreateRequest cardInfoCreateRequest);
}
