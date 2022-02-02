package me.maru.seeTogether.api.v1.auth;

import me.maru.seeTogether.jwt.JwtToken;
import me.maru.seeTogether.service.user.UserAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 로그인 API를 담당하는 클래스
 *
 * @author 이석민
 */
@RestController
@RequestMapping("/api/v1")
public class LoginController {

    private final UserAuthenticator userAuthenticator;

    @Autowired
    public LoginController(final UserAuthenticator userAuthenticator) {
        this.userAuthenticator = userAuthenticator;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody final LoginRequest loginRequest) {
        return ResponseEntity.ok(userAuthenticator.login(loginRequest));
    }
}
