package me.maru.seeTogether.repository.product;

import me.maru.seeTogether.domain.product.OttProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OttProductRepository extends JpaRepository<OttProduct, Long> {
    Optional<OttProduct> findOttProductByOttProductIdAndDelete(Long ottProductId, Boolean delete);
}
