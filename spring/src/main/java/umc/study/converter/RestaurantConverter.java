package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    // 10주차 실습 추가
    // 파라미터 List<Review> reviewList -> Page<Review> reviewPage로 변경
    // 그 이후에 반환값에서 .isLast(), .isFirst()의 오류가 사라짐
    public static RestaurantResponseDTO.ReviewPreViewListDTO toreviewPreViewListDTO(Page<Review> reviewPage){
        List<RestaurantResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewPage.stream()
                .map(RestaurantConverter::reviewPreViewDTO).collect(Collectors.toList());

        return RestaurantResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewPage.isLast())
                .isFirst(reviewPage.isFirst())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static RestaurantResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return RestaurantResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

}
