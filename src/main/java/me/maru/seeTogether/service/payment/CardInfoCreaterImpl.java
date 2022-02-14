package me.maru.seeTogether.service.payment;

import me.maru.seeTogether.api.v1.payment.CardInfoCreateRequest;
import me.maru.seeTogether.api.v1.payment.CardInfoCreateResponse;
import me.maru.seeTogether.domain.payment.card.CardInfo;
import me.maru.seeTogether.domain.user.User;
import me.maru.seeTogether.repository.payment.CardInfoRepository;
import me.maru.seeTogether.repository.user.UserRepository;
import me.maru.seeTogether.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class CardInfoCreaterImpl implements CardInfoCreater {

    private final CardInfoRepository cardInfoRepository;
    private final UserRepository userRepository;

    @Autowired
    public CardInfoCreaterImpl(final CardInfoRepository cardInfoRepository,final UserRepository userRepository) {
        this.cardInfoRepository = cardInfoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CardInfoCreateResponse create(final CardInfoCreateRequest cardInfoCreateRequest) {
        final var userId = SecurityUtil.getUserId();
        final var user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("등록된 유저가 아닙니다."));

        if(!checkSameUser(user, cardInfoCreateRequest))
            throw new IllegalArgumentException("회원 정보와 카드 정보가 일치하지 않습니다.");

        final var cardInfo = CardInfo.builder()
                .user(user)
                .cardCode(cardInfoCreateRequest.getCardCode())
                .userCardNumber(cardInfoCreateRequest.getUserCardNumber())
                .createdAt(LocalDateTime.now())
                .deleteYn(false)
                .build();

        final var savedCardInfo = cardInfoRepository.save(cardInfo);

        return CardInfoCreateResponse.builder()
                .userId(userId)
                .cardCode(savedCardInfo.getCardCode())
                .createdAt(savedCardInfo.getCreatedAt())
                .build();
    }

    private Boolean checkSameUser(final User user, final CardInfoCreateRequest cardInfoCreateRequest){
        return Objects.equals(user.getUserId(), cardInfoCreateRequest.getUserId()) &&
                !user.getName().equals(cardInfoCreateRequest.getUserName());
    }
}
