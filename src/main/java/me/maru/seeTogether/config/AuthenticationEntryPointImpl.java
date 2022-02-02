package me.maru.seeTogether.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.maru.seeTogether.api.v1.common.ErrorResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthenticationEntryPointImpl 클래스
 * 시큐리티 인증이 안된 경우를 핸들링 입니다.
 *
 * @author 이석민
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException, ServletException {

        // Status Code 는 401 로 반환한다.
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=utf-8");

        final var mapper = new ObjectMapper();
        final var errorResponse = ErrorResponse.builder()
                .statusCode(401)
                .message("잘못된 인증 입니다.")
                .build();

        final var out = response.getWriter();
        out.print(mapper.writeValueAsString(errorResponse));
    }
}
