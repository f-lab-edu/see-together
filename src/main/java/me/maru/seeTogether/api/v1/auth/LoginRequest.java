package me.maru.seeTogether.api.v1.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * api/login 리퀘스트 객체인 보내는 DTO 클래스 입니다. <p>
 * 클래스가 가지고있는 자신의 필드로 UsernamePasswordAuthenticationToken 를 반환합니다.
 *
 * @author 이석민
 */
public class LoginRequest {

    private final String email;
    private final String password;

    public LoginRequest(@JsonProperty("email") final String email, @JsonProperty("password") final String password) {
        this.email = email;
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(this.email, this.password);
    }
}
