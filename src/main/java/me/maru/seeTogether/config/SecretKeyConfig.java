package me.maru.seeTogether.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

/**
 * Base64로 암호화된 java.security.Key 빈등록 <p>
 * 1. Key 를 이용하여 TokenProvider 를 통해 토큰 제공, 토큰 인증을 통한 Authentication 을 모두 사용하는게 아닌
 * TokenProvider 와 AuthProvider 를 통해 2가지로 나누는 역할에서 key를 빈으로 등록하여 Autowired 를 진행 하게 도와줍니다.
 *
 * @author 이석민
 */
@Configuration
public class SecretKeyConfig {
    @Value("${jwt.secret_key}")
    private String secretKey;

    @Bean
    public Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
