//어노테이션으로 검증 - 1) validpage 어노테이션 생성
package umc.study.validation.annotation;

import jakarta.validation.Payload;

public @interface ValidPage {
    String message() default "유효하지 않은 페이지 번호. 페이지 번호는 0이거나 그 이상이어야 한다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}