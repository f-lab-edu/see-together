package me.maru.seeTogether.api.v1.payment;

import lombok.Getter;
import me.maru.seeTogether.domain.payment.card.CardCode;

import javax.validation.constraints.NotNull;

@Getter
public class CardInfoCreateRequest {
    @NotNull
    private Long userId;
    @NotNull
    private String userName;
    @NotNull
    private CardCode cardCode;
    @NotNull
    private String userCardNumber;

    @Override
    public String toString() {
        return "CardInfoCreateRequest{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", cardCode=" + cardCode +
                ", userCardNumber='" + userCardNumber + '\'' +
                '}';
    }
}
