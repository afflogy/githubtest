package umc.study.converter;

import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

import java.time.LocalDateTime;

public class RestaurantConverter {

    public static RestaurantResponseDTO.JoinResultDTO toJoinResultDTO(Restaurant restaurant){
        return RestaurantResponseDTO.JoinResultDTO.builder()
                .restaurantId(restaurant.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // CreateReviewResultDTO 혹은 toReview 중에서 하나로 만들기
    public static RestaurantResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review){
        return RestaurantResponseDTO.CreateReviewResultDTO.buider()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(RestaurantRequestDTO.ReviewDTO request){
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .build();
    }

}
