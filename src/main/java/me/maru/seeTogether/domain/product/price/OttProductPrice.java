package me.maru.seeTogether.domain.product.price;

import lombok.Builder;
import me.maru.seeTogether.domain.product.OttProduct;

import javax.persistence.*;

@Entity
@Table(name = "ott_product_price")
public class OttProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ottProductPriceId;

    @OneToOne(targetEntity = OttProduct.class)
    @JoinColumn(name = "ott_product_id", referencedColumnName = "ott_product_id")
    private OttProduct ottProduct;

    @Column(name = "product_total_price")
    private Integer productTotalPrice;

    @Column(name = "product_individual_price")
    private Integer productIndividualPrice;

    public OttProductPrice() {}

    @Builder
    public OttProductPrice(Long ottProductPriceId, OttProduct ottProduct, Integer productTotalPrice, Integer productIndividualPrice) {
        this.ottProductPriceId = ottProductPriceId;
        this.ottProduct = ottProduct;
        this.productTotalPrice = productTotalPrice;
        this.productIndividualPrice = productIndividualPrice;
    }
}
