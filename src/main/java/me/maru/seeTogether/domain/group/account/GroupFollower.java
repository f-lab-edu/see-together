package me.maru.seeTogether.domain.group.account;

import lombok.Builder;
import me.maru.seeTogether.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "group_follower")
public class GroupFollower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupFollowerId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "follower_user_id", referencedColumnName = "user_id")
    private User user;

    @JoinColumn(name = "ott_product_group_id")
    private Object ottProductGroup;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "delete_yn")
    private Boolean deleteYn;

    public GroupFollower() {}

    @Builder
    public GroupFollower(Long groupFollowerId, User user, Object ottProductGroup, LocalDateTime createdAt, LocalDateTime deletedAt, Boolean deleteYn) {
        this.groupFollowerId = groupFollowerId;
        this.user = user;
        this.ottProductGroup = ottProductGroup;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.deleteYn = deleteYn;
    }
}
