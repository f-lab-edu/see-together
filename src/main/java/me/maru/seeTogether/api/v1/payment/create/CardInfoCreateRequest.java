package me.maru.seeTogether.api.v1.payment.create;

import lombok.Builder;
import lombok.Getter;
import me.maru.seeTogether.domain.payment.card.CardCode;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
public class CardInfoCreateRequest {
    @NotNull
    private final Long userId;
    @NotNull
    private final String userName;
    @NotNull
    private final CardCode cardCode;
    @NotNull
    private final String userCardNumber;

    @Builder
    public CardInfoCreateRequest(Long userId, String userName, CardCode cardCode, String userCardNumber) {
        this.userId = userId;
        this.userName = userName;
        this.cardCode = cardCode;
        this.userCardNumber = userCardNumber;
    }

    @Override
    public String toString() {
        return "CardInfoCreateRequest{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", cardCode=" + cardCode +
                ", userCardNumber='" + userCardNumber + '\'' +
                '}';
    }

    public boolean compareToUser(String name, Long userId) {
        return (this.userName.equals(name) && Objects.equals(this.userId, userId));
    }
}
