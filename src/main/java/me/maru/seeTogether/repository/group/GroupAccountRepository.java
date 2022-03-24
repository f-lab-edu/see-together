package me.maru.seeTogether.repository.group;

import me.maru.seeTogether.domain.group.ProductGroup;
import me.maru.seeTogether.domain.group.account.GroupAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupAccountRepository extends JpaRepository<GroupAccount, Long> {
    Optional<GroupAccount> findGroupAccountByProductGroupAndDelete(ProductGroup productGroup, Boolean delete);

}
