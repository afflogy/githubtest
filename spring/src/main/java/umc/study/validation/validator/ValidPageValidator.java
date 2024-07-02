//어노테이션으로 검증 - 2) ValidPageValidator validator 생성
package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.study.validation.annotation.ValidPage;

public class ValidPageValidator implements ConstraintValidator<ValidPage, Integer> {
    @Override
    public void initialize(ValidPage constraintAnnotation) {
        // 초기화 로직이 필요하다면 여기서 작성
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && value >= 0;
    }
}
