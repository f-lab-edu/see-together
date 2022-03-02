package me.maru.seeTogether.domain.payment.withdrawal;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "auto_withdrawal_history")
public class AutoWithdrawalHistory {

    @Id
    @Column(name = "auto_withdrawal_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autoWithdrawalHistoryId;

    @OneToOne(targetEntity = AutoWithdrawal.class)
    @JoinColumn(name = "auto_withdrawal_id", referencedColumnName = "auto_withdrawal_id")
    private AutoWithdrawal autoWithdrawal;

    @Column(name = "success_yn")
    private Boolean success;

    @Column(name = "fail_description")
    private String failDescription;

    public AutoWithdrawalHistory() {}

    @Builder
    public AutoWithdrawalHistory(Long autoWithdrawalHistoryId, AutoWithdrawal autoWithdrawal, Boolean success, String failDescription) {
        this.autoWithdrawalHistoryId = autoWithdrawalHistoryId;
        this.autoWithdrawal = autoWithdrawal;
        this.success = success;
        this.failDescription = failDescription;
    }
}