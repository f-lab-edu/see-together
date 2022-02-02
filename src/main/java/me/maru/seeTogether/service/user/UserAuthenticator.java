package me.maru.seeTogether.service.user;

import me.maru.seeTogether.api.v1.auth.LoginRequest;
import me.maru.seeTogether.jwt.JwtToken;
import me.maru.seeTogether.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAuthenticator {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @Autowired
    public UserAuthenticator(AuthenticationManagerBuilder authenticationManagerBuilder, TokenProvider tokenProvider) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenProvider = tokenProvider;
    }

    @Transactional
    public JwtToken login(LoginRequest loginRequest) {
        final var authenticationToken = loginRequest.toAuthentication();
        final var authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return tokenProvider.provide(authentication);
    }
}
