package umc.study.converter;

import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RestaurantConverter {

    //DTO에 있던 정보 가져와서 바꿈
    public static RestaurantResponseDTO.JoinResultDTO toJoinResultDTO(Restaurant restaurant) {
        return RestaurantResponseDTO.JoinResultDTO.builder()
                .restaurantId(restaurant.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Restaurant toRestaurant(RestaurantRequestDTO.JoinDTO request) {
        return Restaurant.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phone_num(request.getPhon_number())
                .reviewList(new ArrayList<>()) // **리스트는 <>()로 초기화해야함
                .build();
    }

    public static RestaurantResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review){
        return RestaurantResponseDTO.CreateReviewResultDTO.builder()
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
