package me.maru.seeTogether.domain.group;

import lombok.Builder;
import me.maru.seeTogether.domain.product.OttProduct;

import javax.persistence.*;

@Entity
@Table(name = "product_group")
public class ProductGroup {
    @Id
    @Column(name = "product_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productGroupId;

    @ManyToOne(targetEntity = OttProduct.class)
    @JoinColumn(name = "ott_product_id", referencedColumnName = "ott_product_id")
    private OttProduct ottProduct;

    @Column(name = "current_ participants_size")
    private Integer currentParticipantsSize;

    public ProductGroup() {}

    @Builder
    public ProductGroup(Long productGroupId, OttProduct ottProduct, Integer currentParticipantsSize) {
        this.productGroupId = productGroupId;
        this.ottProduct = ottProduct;
        this.currentParticipantsSize = currentParticipantsSize;
    }
}
