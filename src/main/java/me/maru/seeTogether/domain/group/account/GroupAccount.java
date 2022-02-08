package me.maru.seeTogether.domain.group.account;

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
}