package me.maru.seeTogether.domain.group.account;

import lombok.Builder;
import me.maru.seeTogether.domain.group.ProductGroup;
import me.maru.seeTogether.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "group_follower")
public class GroupFollower {
    @Id
    @Column(name = "group_follower_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupFollowerId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne(targetEntity = ProductGroup.class)
    @JoinColumn(name = "product_group_id", referencedColumnName = "product_group_id")
    private ProductGroup productGroup;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "delete_yn")
    private Boolean deleteYn;

    public GroupFollower() {}

    @Builder
    public GroupFollower(Long groupFollowerId, User user, ProductGroup productGroup, LocalDateTime createdAt, LocalDateTime deletedAt, Boolean deleteYn) {
        this.groupFollowerId = groupFollowerId;
        this.user = user;
        this.productGroup = productGroup;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.deleteYn = deleteYn;
    }
}
