package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RestaurantResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{
        private Long restaurantId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewResultDTO {
        private Long reviewId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewListDTO{
        private List<ReviewPreViewDTO> reviewList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDTO{
        private String ownerNickname;
        private Float score;
        private String body;
        private LocalDate createdAt;
    }

//    @Getter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Builder
//    public static class RestaurantResponseDTO {
//        private Long id;
//        private String name;
//        private String address;
//        private String phoneNumber;
//        private List<ReviewPreViewDTO> reviews;
//    }
}
