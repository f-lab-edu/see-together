package me.maru.seeTogether.domain.group.account;

import lombok.Builder;
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

    @Column(name = "ott_id")
    private String ottId;

    @Column(name = "ott_password")
    private String ottPassword;

    public GroupAccount() {}

    @Builder
    public GroupAccount(Long groupAccountId, ProductGroup productGroup, String ottId, String ottPassword) {
        this.groupAccountId = groupAccountId;
        this.productGroup = productGroup;
        this.ottId = ottId;
        this.ottPassword = ottPassword;
    }
}