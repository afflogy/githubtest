package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.ReviewExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ReviewExistValidator.class) // 자바에서 제공하는 사용자가 validation을 커스텀 어노테이션을 통해 할 수 있도록 제공
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistReview {
    String message() default "해당하는 리뷰가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
