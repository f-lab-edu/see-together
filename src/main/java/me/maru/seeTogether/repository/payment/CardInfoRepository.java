package me.maru.seeTogether.repository.payment;

import me.maru.seeTogether.domain.payment.card.CardInfo;
import me.maru.seeTogether.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardInfoRepository extends JpaRepository<CardInfo, Long> {
    Optional<CardInfo> findCardInfoByUserAndDeleteYn(User user, boolean deleteYn);
}
