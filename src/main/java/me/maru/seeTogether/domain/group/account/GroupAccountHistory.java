package me.maru.seeTogether.domain.group.account;

import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "group_account_history")
public class GroupAccountHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupAccountHistoryId;

    @Column(name = "group_account_id")
    private String groupAccountId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "delete_description")
    private String deleteDescription;

    public GroupAccountHistory() {}

    @Builder
    public GroupAccountHistory(Long groupAccountHistoryId, String groupAccountId, LocalDateTime createdAt, LocalDateTime deletedAt, String deleteDescription) {
        this.groupAccountHistoryId = groupAccountHistoryId;
        this.groupAccountId = groupAccountId;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.deleteDescription = deleteDescription;
    }
}
