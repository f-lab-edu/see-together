package me.maru.seeTogether.domain.group;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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

    @Getter
    @Setter
    @Column(name = "current_participants_size")
    private Integer currentParticipantsSize;

    @Column(name = "delete_yn")
    private Boolean delete;

    public ProductGroup() {}

    @Builder
    public ProductGroup(Long productGroupId, OttProduct ottProduct, Integer currentParticipantsSize, Boolean delete) {
        this.productGroupId = productGroupId;
        this.ottProduct = ottProduct;
        this.currentParticipantsSize = currentParticipantsSize;
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "ProductGroup{" +
                "productGroupId=" + productGroupId +
                ", ottProduct=" + ottProduct +
                ", currentParticipantsSize=" + currentParticipantsSize +
                ", delete=" + delete +
                '}';
    }
}
