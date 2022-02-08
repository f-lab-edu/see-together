package me.maru.seeTogether.domain.product;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ott_product")
public class OttProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "total_participants_size")
    private Integer totalParticipantsSize;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "delete_yn")
    private Boolean deleteYn;
}