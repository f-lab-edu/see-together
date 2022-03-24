package me.maru.seeTogether.domain.product;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ott_product")
public class OttProduct {
    @Id
    @Column(name = "ott_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ottProductId;

    @Getter
    @Column(name = "product_name")
    private String productName;

    @Getter
    @Column(name = "total_participants_size")
    private Integer totalParticipantsSize;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "delete_yn")
    private Boolean delete;

    @Builder
    public OttProduct(Long ottProductId, String productName, Integer totalParticipantsSize, LocalDateTime createdAt, LocalDateTime deletedAt, Boolean delete) {
        this.ottProductId = ottProductId;
        this.productName = productName;
        this.totalParticipantsSize = totalParticipantsSize;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.delete = delete;
    }

    public OttProduct() {
    }
}