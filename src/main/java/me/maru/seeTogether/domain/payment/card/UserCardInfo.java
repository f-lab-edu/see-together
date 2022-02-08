package me.maru.seeTogether.domain.payment.card;

import me.maru.seeTogether.domain.user.User;

import javax.persistence.*;

@Entity
@Table
public class UserCardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCardInfoId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "user_card_number")
    private String userCardNumber;

    @Column(name = "user_card_code")
    private CardCode cardCode;
}