package me.maru.seeTogether.jwt;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * JwtFilter 클래스
 */
public class JwtFilter extends OncePerRequestFilter {

    private final AuthProvider authProvider;

    public JwtFilter(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws IOException, ServletException {

        findToken(request).ifPresent(
                jwt -> {
                    if (StringUtils.hasText(jwt) && authProvider.isTokenActivated(jwt)) {
                        final var authentication = authProvider.provide(jwt);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
        );
        filterChain.doFilter(request, response);
    }

    private Optional<String> findToken(final HttpServletRequest request) {
        final var jwt = request.getHeader("Authorization");
        if (StringUtils.hasText(jwt)) {
            return Optional.of(jwt);
        }
        return Optional.empty();
    }
}