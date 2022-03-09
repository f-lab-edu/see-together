package me.maru.seeTogether.service.payment.update;

import me.maru.seeTogether.api.v1.payment.create.CardInfoCreateRequest;
import me.maru.seeTogether.api.v1.payment.create.CardInfoCreateResponse;
import me.maru.seeTogether.repository.payment.CardInfoRepository;
import me.maru.seeTogether.repository.user.UserRepository;
import me.maru.seeTogether.service.payment.create.CardInfoCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CardInfoUpdaterImpl implements CardInfoUpdater {

    private final CardInfoCreator cardInfoCreator;
    private final CardInfoRepository cardInfoRepository;
    private final UserRepository userRepository;

    @Autowired
    public CardInfoUpdaterImpl(final CardInfoCreator cardInfoCreator, final CardInfoRepository cardInfoRepository, final UserRepository userRepository) {
        this.cardInfoCreator = cardInfoCreator;
        this.cardInfoRepository = cardInfoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CardInfoCreateResponse update(final CardInfoCreateRequest cardInfoCreateRequest) {

        final var userId = cardInfoCreateRequest.getUserId();
        final var user = userRepository.getById(userId);

        var cardInfo = cardInfoRepository.findCardInfoByUserAndDeleteYn(user, false).orElseThrow(
                () -> new NoSuchElementException(String.format("해당 ID로 \"%d\" 등록된 카드정보를 찾을 수 업습니다.", userId))
        );

        cardInfo.setDeleteYn(true);
        cardInfoRepository.save(cardInfo);

        return cardInfoCreator.create(user, cardInfoCreateRequest);
    }
}
