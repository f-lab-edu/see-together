package me.maru.seeTogether.repository.user;

import me.maru.seeTogether.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /* 이메일을 통해서 유저 객체 반환*/
    Optional<User> findUserByEmail(String email);

}
