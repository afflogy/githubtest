package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.repository.MemberMissionRepository;
import umc.study.validation.annotation.UniqueMemberMission;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UniqueMemberMissionValidator implements ConstraintValidator<UniqueMemberMission, List<Long>> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(UniqueMemberMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isChallenging =  values.stream()
                .allMatch(value -> memberMissionRepository.existsByMissionId(value));

        if (isChallenging) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_MISSION_ALREADY_EXISTS.toString()).addConstraintViolation();
            return false;
        }

        return !isChallenging;
    }
}