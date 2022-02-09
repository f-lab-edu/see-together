package me.maru.seeTogether.domain.payment.withdrawal;

import lombok.Builder;
import me.maru.seeTogether.domain.group.account.GroupFollower;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auto_withdrawal")
public class AutoWithdrawal {

    @Id
    @Column(name = "auto_withdrawal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autoWithdrawalId;

    @ManyToOne(targetEntity = GroupFollower.class)
    @JoinColumn(name = "group_follower_id", referencedColumnName = "group_follower_id")
    private GroupFollower groupFollower;

    @Column(name = "reserved_at")
    private LocalDateTime reservedAt;

    @Column(name = "withdrawal_price")
    private Integer withdrawalPrice;

    public AutoWithdrawal() {}

    @Builder
    public AutoWithdrawal(Long autoWithdrawalId, GroupFollower groupFollower, LocalDateTime reservedAt, Integer withdrawalPrice) {
        this.autoWithdrawalId = autoWithdrawalId;
        this.groupFollower = groupFollower;
        this.reservedAt = reservedAt;
        this.withdrawalPrice = withdrawalPrice;
    }
}
