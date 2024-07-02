//어노테이션으로 검증 - 4) 에러발생 시 처리하는 핸들러 적용
package umc.study.apiPayload.exception.handler;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import umc.study.apiPayload.ApiResponse;

@RestControllerAdvice
public class ValidPageHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ApiResponse.onFailure("Invalid request", );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ApiResponse.onFailure(ex.getMessage(), );
    }
}
