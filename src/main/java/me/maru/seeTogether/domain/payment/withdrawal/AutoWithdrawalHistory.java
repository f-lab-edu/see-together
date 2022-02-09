package me.maru.seeTogether.domain.payment.withdrawal;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "auto_withdrawal_history")
public class AutoWithdrawalHistory {

    @Id
    @Column(name = "auto_withdrawal_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AutoWithdrawalHistoryId;

    @OneToOne(targetEntity = AutoWithdrawal.class)
    @JoinColumn(name = "auto_withdrawal_id", referencedColumnName = "auto_withdrawal_id")
    private AutoWithdrawal autoWithdrawal;

    @Column(name = "success_yn")
    private Boolean successYn;

    @Column(name = "fail_description")
    private String failDescription;

    public AutoWithdrawalHistory() {}

    @Builder
    public AutoWithdrawalHistory(Long autoWithdrawalHistoryId, AutoWithdrawal autoWithdrawal, Boolean successYn, String failDescription) {
        AutoWithdrawalHistoryId = autoWithdrawalHistoryId;
        this.autoWithdrawal = autoWithdrawal;
        this.successYn = successYn;
        this.failDescription = failDescription;
    }
}
