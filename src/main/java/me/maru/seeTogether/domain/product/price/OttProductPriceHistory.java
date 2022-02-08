package me.maru.seeTogether.domain.product.price;

import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ott_product_price_history")
public class OttProductPriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ottProductPriceHistoryId;

    @Column(name = "ott_product_price_id")
    private Long ottProductPriceId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "delete_description")
    private String deleteDescription;

    public OttProductPriceHistory() {}

    @Builder
    public OttProductPriceHistory(Long ottProductPriceHistoryId, Long ottProductPriceId, LocalDateTime createdAt, LocalDateTime deletedAt, String deleteDescription) {
        this.ottProductPriceHistoryId = ottProductPriceHistoryId;
        this.ottProductPriceId = ottProductPriceId;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.deleteDescription = deleteDescription;
    }
}
