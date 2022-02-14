package me.maru.seeTogether.repository.payment;

import me.maru.seeTogether.domain.payment.card.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardInfoRepository extends JpaRepository<CardInfo, Long> {
}
