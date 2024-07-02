package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.study.validation.annotation.ExistReview;

import java.util.List;

public class RestaurantRequestDTO {

    @Getter
    public static class JoinDTO{
        @NotBlank
        String name;
        @NotBlank
        String phon_number;
        @Size(min=5, max=12)
        String address;
        @ExistReview
        List<Long> review;
    }

    @Getter
    public static class ReviewDTO{
        @NotBlank
        String title;
        @NotNull
        Float score;
        @NotBlank
        String body;
    }
}

