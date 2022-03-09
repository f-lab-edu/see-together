package me.maru.seeTogether.service.payment.query;

import me.maru.seeTogether.api.v1.payment.query.CardInfoQueryResponse;
import me.maru.seeTogether.domain.payment.card.CardInfo;
import me.maru.seeTogether.domain.user.User;
import me.maru.seeTogether.repository.payment.CardInfoRepository;
import me.maru.seeTogether.repository.user.UserRepository;
import me.maru.seeTogether.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CardInfoReaderImpl implements CardInfoReader {

    private final UserRepository userRepository;
    private final CardInfoRepository cardInfoRepository;
    @Autowired
    public CardInfoReaderImpl(UserRepository userRepository, CardInfoRepository cardInfoRepository) {
        this.userRepository = userRepository;
        this.cardInfoRepository = cardInfoRepository;
    }

    @Override
    public CardInfoQueryResponse getCardInfo() {
        final var user = userRepository.findById(SecurityUtil.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("서버에 등록된 유저가 아닙니다."));

        final var cardInfo = cardInfoRepository.findCardInfoByUserAndDeleteYn(user, false)
                .orElseThrow(() -> new NoSuchElementException("해당 유저는 카드등록이 되어있지 않습니다."));

        return CardInfoQueryResponse.builder()
                .cardCode(cardInfo.getCardCode())
                .createdAt(cardInfo.getCreatedAt())
                .userCardNumber(cardInfo.getUserCardNumber())
                .build();
    }
}
