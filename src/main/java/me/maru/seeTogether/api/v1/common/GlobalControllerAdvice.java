package me.maru.seeTogether.api.v1.common;


import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Exception 을 핸들링하는 로직을 담당하는 클래스 입니다.
 *
 * @author 이석민
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    // 비지니스 룰에 어긋나는 에러는 IllegalArgumentException.class 로 에러를 핸들링하고있습니다.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        final var response = ErrorResponse.builder()
                .statusCode(409)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(409).body(response);
    }

    // 사용자가 요청 또는 수정하려는 데이터가 없을 경우에 NoSuchElementException 으로 에럴 핸들링 하고있습니다.
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException e) {
        final var response = ErrorResponse.builder()
                .statusCode(404)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(404).body(response);
    }

    // @Valid Convert 에러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        // TODO : 변경하기
        /*
        final var errors1 = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toUnmodifiableMap());
        */

        return ResponseEntity.badRequest().body(errors);
    }
}
