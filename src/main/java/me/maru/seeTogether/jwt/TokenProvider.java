package me.maru.seeTogether.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Token 생성자 클래스
 */
@Component
public class TokenProvider {

    @Value("${jwt.expiration_seconds_time_access_token}")
    private long ACCESS_TOKEN_EXPIRE_TIME;

    private final Key key;

    @Autowired
    public TokenProvider(Key key) {
        this.key = key;
    }

    /**
     * encrypt algorithm : SHA-512
     * access 토큰을 제공합니다. .
     * @param authentication
     * @return
     */
    public JwtToken provide(Authentication authentication) {

        final var authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        final var accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(getExpireTime(ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return new JwtToken(accessToken);
    }

    /**
     * token 의 시간 + 현재시간을 통해 만료 (real time)시간을 return 합니다.
     *
     * @param tokenExpireTime 토큰 만료 시간
     */
    private Date getExpireTime(long tokenExpireTime) {
        return new Date((new Date()).getTime() + tokenExpireTime);
    }
}
