package me.maru.seeTogether.service.user;

import me.maru.seeTogether.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * UserDetailService 의 구현체 클래스 <p>
 * 1. loadUserByUsername 를 통해 UserDetails 객체 반환 합니다.<p>
 *
 * @author 이석민
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * loadUserByUsername 를 통해서 UserDetails 반환
     * @param email  User.class 객체에 name이 이미 있기 때문에 기본 username 이 아닌 eamil 을 사용
     * @throws UsernameNotFoundException 회원가입이 되어있지않은 유저
     */
    // TODO: username 대신 email 을 사용한 부분이 헷갈리는 변수명일 수도 있을 수 있음. 조금더 고민할 필요가 있음
    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email).map(user -> {
            return new User(
                    String.valueOf(user.getId()),
                    user.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(user.getAuthRole().toString()))
            );
        }).orElseThrow(() -> new UsernameNotFoundException(String.format("email \"%s\" does not exist", email)));
    }
}
