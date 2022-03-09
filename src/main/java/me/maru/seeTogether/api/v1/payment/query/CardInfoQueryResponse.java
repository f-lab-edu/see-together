package me.maru.seeTogether.api.v1.payment.query;

import lombok.Builder;
import lombok.Getter;
import me.maru.seeTogether.domain.payment.card.CardCode;

import java.time.LocalDateTime;

@Getter
public class CardInfoQueryResponse {
    private final CardCode cardCode;
    private final String userCardNumber;
    private final LocalDateTime createdAt;

    @Builder
    public CardInfoQueryResponse(CardCode cardCode, String userCardNumber, LocalDateTime createdAt) {
        this.cardCode = cardCode;
        this.userCardNumber = userCardNumber;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CardInfoQueryResponse{" +
                "cardCode=" + cardCode +
                ", userCardNumber='" + userCardNumber + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
