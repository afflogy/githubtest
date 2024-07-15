package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import umc.study.repository.RegionRepository;

@RequiredArgsConstructor
public class RegionExistValidator implements ConstraintValidator<ValidArea, Long> {

    private final RegionRepository regionRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && regionRepository.existsById(value);
    }
}
