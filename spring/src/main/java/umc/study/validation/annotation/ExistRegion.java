package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.RegionExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RegionExistValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistRegion {
    String message() default "유효하지 않은 지역 ID입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
