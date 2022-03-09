package me.maru.seeTogether.api.v1.payment.create;

import lombok.Builder;
import lombok.Getter;
import me.maru.seeTogether.domain.payment.card.CardCode;

import java.time.LocalDateTime;

@Getter
public class CardInfoCreateResponse {
    private final Long userId;
    private final CardCode cardCode;
    private final LocalDateTime createdAt;

    @Builder
    public CardInfoCreateResponse(Long userId, CardCode cardCode, LocalDateTime createdAt) {
        this.userId = userId;
        this.cardCode = cardCode;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CardInfoCreateResponse{" +
                "userId=" + userId +
                ", cardCode=" + cardCode +
                ", createdAt=" + createdAt +
                '}';
    }
}
