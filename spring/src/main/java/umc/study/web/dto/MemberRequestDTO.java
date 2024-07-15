package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.study.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {
    @Getter
    public static class JoinDTO{
        @NotBlank
        String name;
        @NotBlank
        String phon_number;
        @NotBlank
        Integer gender;
        @Size(min=5, max=12)
        String address;
        @ExistCategories
        List<Long> mission;
        @ExistCategories
        List<Long> preferCategory;
    }
}
