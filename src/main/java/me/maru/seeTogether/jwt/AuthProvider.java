package me.maru.seeTogether.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Authentication 를 제공해주는 클래스
 * 1. 토큰을 입력받고, 해당 토큰의 인증정보를 제공해준다.
 * 2. 해당 토큰의 생명주기가 옳바른지 확인해준다.
 */
@Component
public class AuthProvider {
    private final Logger logger = LoggerFactory.getLogger(AuthProvider.class);
    private final Key key;

    @Autowired
    public AuthProvider(Key key) {
        this.key = key;
    }

    /**
     * 토큰을 가지고, SimpleGrantedAuthority 을 통해 권한 객체 생성 후 Authentication 반환
     * @param accessToken 서블릿 필터의 request header 에서 추출한 accessToken
     */
    public Authentication provide(final String accessToken) {
        final var claims = parseClaimsJws(accessToken);

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    /**
     * @param accessToken 해당 토큰 입력
     * @return
     */
    public boolean isTokenActivated(final String accessToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken);
            return true;
        } catch (ExpiredJwtException e) {
            logger.warn("This token was expired");
        } catch (JwtException e){
            logger.warn("This token can't truest");
        }
        return false;
    }

    private Claims parseClaimsJws(final String accessToken) {
        try {
            // 만료되어있지 않은 accessToken 으로 반환
            final var claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
            logger.info("this token trusted: '{}'", claims.getSubject());
            return claims;
        } catch (ExpiredJwtException e) {
            // 만료된 accessToken payload 정보 가져옴
            final var expiredClaims = e.getClaims();
            logger.info("expired username: '{}' ", expiredClaims.getSubject());
            return expiredClaims;
        }
    }

    private Optional<String> findAuthByClaims(final Claims claims) {
        Object auth = claims.get("auth");
        if (auth == null) {
            return Optional.empty();
        } else {
            return Optional.of(auth.toString());
        }
    }
}
