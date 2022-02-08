package me.maru.seeTogether.domain.group;

import lombok.Builder;
import me.maru.seeTogether.domain.product.OttProduct;

import javax.persistence.*;

@Entity
@Table(name = "product_group")
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productGroupId;

    @ManyToOne(targetEntity = OttProduct.class)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private OttProduct ottProduct;

    @Column(name = "current_ participants_size")
    private Integer currentParticipantsSize;

    @Column(name = "delete_yn")
    private Boolean deleteYn;

    public ProductGroup() {}

    @Builder
    public ProductGroup(Long productGroupId, OttProduct ottProduct, Integer currentParticipantsSize, Boolean deleteYn) {
        this.productGroupId = productGroupId;
        this.ottProduct = ottProduct;
        this.currentParticipantsSize = currentParticipantsSize;
        this.deleteYn = deleteYn;
    }
}
