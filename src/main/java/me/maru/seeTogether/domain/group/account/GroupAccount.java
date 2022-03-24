package me.maru.seeTogether.domain.group.account;

import lombok.Builder;
import lombok.Getter;
import me.maru.seeTogether.domain.group.ProductGroup;

import javax.persistence.*;

@Entity
@Table(name = "group_account")
public class GroupAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupAccountId;

    @OneToOne(targetEntity = ProductGroup.class)
    @JoinColumn(name = "product_group_id", referencedColumnName = "product_group_id")
    private ProductGroup productGroup;

    @Getter
    @Column(name = "ott_id")
    private String ottId;

    @Getter
    @Column(name = "ott_password")
    private String ottPassword;

    @Column(name = "delete_yn")
    private Boolean delete;

    public GroupAccount() {}

    @Builder
    public GroupAccount(Long groupAccountId, ProductGroup productGroup, String ottId, String ottPassword, Boolean delete) {
        this.groupAccountId = groupAccountId;
        this.productGroup = productGroup;
        this.ottId = ottId;
        this.ottPassword = ottPassword;
        this.delete = delete;
    }
}