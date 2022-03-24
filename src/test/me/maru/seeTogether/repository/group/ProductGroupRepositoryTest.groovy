package me.maru.seeTogether.repository.group

import me.maru.seeTogether.repository.product.OttProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test")
class ProductGroupRepositoryTest extends Specification {

    @Autowired
    ProductGroupRepository productGroupRepository

    @Autowired
    OttProductRepository ottProductRepository

    @Transactional
    def "남은 인원 그룹 확인하기"(){
        given:
        def netflix = ottProductRepository.findOttProductByOttProductIdAndDelete(1L, false)
                .orElseThrow(() -> new NoSuchElementException())
        println 'totalSize' + netflix.getTotalParticipantsSize()

        when:
        def productGroup = productGroupRepository
                .findTopByOttProductAndCurrentParticipantsSizeBeforeAndDelete(netflix, netflix.getTotalParticipantsSize(), false)
                .orElseThrow(() -> new NoSuchElementException())

        then:
        println 'productGroup' + productGroup.toString()
    }
}
