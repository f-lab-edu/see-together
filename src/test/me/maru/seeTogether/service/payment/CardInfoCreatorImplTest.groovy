package me.maru.seeTogether.service.payment

import me.maru.seeTogether.api.v1.payment.create.CardInfoCreateRequest
import me.maru.seeTogether.domain.payment.card.CardCode
import me.maru.seeTogether.service.payment.create.CardInfoCreator
import org.mockito.MockedStatic
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("test")
class CardInfoCreatorImplTest extends Specification {

    @Autowired
    CardInfoCreator cardInfoCreator

    def cardInfoCreateRequest

    def setup() {
        cardInfoCreateRequest = CardInfoCreateRequest.builder()
                .userId(1L)
                .cardCode(CardCode.HANA)
                .userCardNumber('110550123123')
                .build()
    }

    @Transactional
    @WithMockUser(username = "1", roles = "USER")
    def "CardInfo create 테스트"() {
        given:
        def now = LocalDateTime.of(2021, 11, 22, 9, 0, 0)
        def createResponse

        when:
        try (MockedStatic<LocalDateTime> topDateTimeUtilMock = Mockito.mockStatic(LocalDateTime.class)) {
            topDateTimeUtilMock.when(() -> LocalDateTime.now()).thenReturn(now)
            createResponse = cardInfoCreator.create(cardInfoCreateRequest)
        }

        then:
        createResponse.getUserId() == 1L
        createResponse.getCardCode() == CardCode.HANA
        createResponse.getCreatedAt() == now
    }

    @Transactional
    @WithMockUser(username = "2", roles = "USER")
    def "CardInfo 등록 실패 본인 X"() {
        when:
        cardInfoCreator.create(cardInfoCreateRequest)

        then:
        thrown(IllegalArgumentException.class) // 회원 정보와 카드 정보가 일치하지 않습니다.
    }

    @WithMockUser(username = "1111", roles = "USER")
    @Transactional
    def "유저 인증 실패 테스트"() {
        when:
        cardInfoCreator.create(cardInfoCreateRequest)

        then:
        thrown(UsernameNotFoundException.class)
    }
}
