package me.maru.seeTogether.jwt;

import lombok.Getter;

@Getter
public class JwtToken {
    private final String accessToken;

    public JwtToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
