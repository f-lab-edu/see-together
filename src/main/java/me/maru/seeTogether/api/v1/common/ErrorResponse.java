package me.maru.seeTogether.api.v1.common;

import lombok.Builder;
import lombok.Getter;

/**
 * Error Rsponse 를 전달하는 클래스입니다.
 * http status 코드와 에러메시지를 담고 있습니다.
 */
@Getter
public class ErrorResponse {
    private final int statusCode;
    private final String message;

    @Builder
    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }
}
