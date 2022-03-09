package me.maru.seeTogether.domain.group;

import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_group_history")
public class ProductGroupHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productGroupHistoryId;

    @ManyToOne(targetEntity = ProductGroup.class)
    @JoinColumn(name = "product_group_id", referencedColumnName = "product_group_id")
    private ProductGroup productGroup;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    @Column(name = "delete_description")
    private String deleteDescription;

    public ProductGroupHistory(){}

    @Builder
    public ProductGroupHistory(Long productGroupHistoryId, ProductGroup productGroup, LocalDateTime createdAt, LocalDateTime deletedAt, String deleteDescription) {
        this.productGroupHistoryId = productGroupHistoryId;
        this.productGroup = productGroup;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.deleteDescription = deleteDescription;
    }
}
