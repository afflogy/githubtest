package umc.study.converter;

import umc.study.domain.Review;
import umc.study.web.dto.RestaurantRequestDTO;

import java.time.LocalDateTime;

public class RestaurantConverter {

    public static Review toReview(RestaurantRequestDTO.ReviewDTO request){
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .build();
    }

    public static RestaurantReponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review){
        return RestaurantResponseDTO.CreateReviewResultDTO.buider()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
