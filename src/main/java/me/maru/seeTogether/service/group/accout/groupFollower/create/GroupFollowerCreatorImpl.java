package me.maru.seeTogether.service.group.accout.groupFollower.create;

import me.maru.seeTogether.api.v1.group.accout.groupFollower.GroupFollowerCreateRequest;
import me.maru.seeTogether.api.v1.group.accout.groupFollower.GroupFollowerCreateResponse;
import me.maru.seeTogether.domain.group.account.GroupAccount;
import me.maru.seeTogether.repository.group.GroupAccountRepository;
import me.maru.seeTogether.repository.group.ProductGroupRepository;
import me.maru.seeTogether.repository.payment.CardInfoRepository;
import me.maru.seeTogether.repository.product.OttProductRepository;
import me.maru.seeTogether.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class GroupFollowerCreatorImpl implements GroupFollowerCreator{

    private final CardInfoRepository cardInfoRepository;
    private final UserRepository userRepository;
    private final ProductGroupRepository productGroupRepository;
    private final OttProductRepository ottProductRepository;
    private final GroupAccountRepository groupAccountRepository;

    @Autowired
    public GroupFollowerCreatorImpl(CardInfoRepository cardInfoRepository, UserRepository userRepository, ProductGroupRepository productGroupRepository, OttProductRepository ottProductRepository, GroupAccountRepository groupAccountRepository) {
        this.cardInfoRepository = cardInfoRepository;
        this.userRepository = userRepository;
        this.productGroupRepository = productGroupRepository;
        this.ottProductRepository = ottProductRepository;
        this.groupAccountRepository = groupAccountRepository;
    }

    @Override
    public GroupFollowerCreateResponse create(GroupFollowerCreateRequest groupFollowerCreateRequest) {
        final var user = userRepository.findById(groupFollowerCreateRequest.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("서버에 등록된 유저가 아닙니다."));

        final var cardInfo = cardInfoRepository.findCardInfoByUserAndDeleteYn(user, false).orElseThrow(
                () -> new NoSuchElementException("카드가 등록되어있지 않는 유저입니다."));

        final var ottProduct = ottProductRepository.findOttProductByOttProductIdAndDelete(groupFollowerCreateRequest.getOttProductId(), false)
                .orElseThrow(() -> new IllegalArgumentException("해당 OTT 아이디는 등록되어있지 않습니다."));
        final var productGroup = productGroupRepository.findTopByOttProductAndCurrentParticipantsSizeBeforeAndDelete(ottProduct, ottProduct.getTotalParticipantsSize(), false)
                .orElseThrow(() -> new NoSuchElementException("제공되는 그룹이 존재하지 않습니다."));

        // TODO:결제진행 은 PG사를 mocking 하고싶다.

        productGroup.setCurrentParticipantsSize(productGroup.getCurrentParticipantsSize() + 1);
        productGroupRepository.save(productGroup);

        final var groupAccount= groupAccountRepository.findGroupAccountByProductGroupAndDelete(productGroup, false)
                .orElseThrow(() -> new NoSuchElementException("해당 아이디가 존재하지 않습니다."));

        // TODO : id 와 password 를 getter 를 이용한 방식은 고쳐야함.
        return GroupFollowerCreateResponse.builder()
                .productName(ottProduct.getProductName())
                .ottId(groupAccount.getOttId())
                .ottPassword(groupAccount.getOttPassword())
                .build();
    }
}
