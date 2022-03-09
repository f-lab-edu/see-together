package me.maru.seeTogether.domain.payment.card;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.maru.seeTogether.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "card_info")
public class CardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardInfoId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "user_card_number")
    private String userCardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_card_code")
    private CardCode cardCode;

    @Setter
    @Column(name = "delete_yn")
    private Boolean deleteYn;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public CardInfo(){}

    @Builder
    public CardInfo(Long cardInfoId, User user, String userCardNumber, CardCode cardCode, Boolean deleteYn, LocalDateTime createdAt) {
        this.cardInfoId = cardInfoId;
        this.user = user;
        this.userCardNumber = userCardNumber;
        this.cardCode = cardCode;
        this.deleteYn = deleteYn;
        this.createdAt = createdAt;
    }
}