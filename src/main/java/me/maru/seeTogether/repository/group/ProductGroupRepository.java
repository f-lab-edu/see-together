package me.maru.seeTogether.repository.group;

import me.maru.seeTogether.domain.group.ProductGroup;
import me.maru.seeTogether.domain.product.OttProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {
    Optional<ProductGroup> findTopByOttProductAndCurrentParticipantsSizeBeforeAndDelete(OttProduct ottProduct, Integer currentParticipantsSize, Boolean delete);
}
