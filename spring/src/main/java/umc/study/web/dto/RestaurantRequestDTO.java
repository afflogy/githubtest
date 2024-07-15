package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    // 9주차 : 특정 가게 추가하기
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AddRestaurantDTO {
        @NotNull(message = "지역 ID는 필수입니다.")
        private Long regionId;

        @NotNull(message = "가게 이름은 필수입니다.")
        private String name;
        @Size(min=5, max=12)
        private String address;
        private String phoneNumber;
    }

}

