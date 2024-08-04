package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantConverter {

    // 1. 가게 추가
    public static RestaurantResponseDTO.JoinRestaurantResultDTO toJoinRestaurantResultDTO(Restaurant restaurant, Region region) {
        return RestaurantResponseDTO.JoinRestaurantResultDTO.builder()
                .restaurantId(restaurant.getId())
                .regionName(region.getName())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 1에서 추가한 가게를 RequestDTO에 보냄
    public static Restaurant toJoinRestaurant(RestaurantRequestDTO.JoinDTO request) {
        return Restaurant.builder()
                .name(request.getName())
                .address(request.getAddress())
                .reviewList(new ArrayList<>()) // **리스트는 <>()로 초기화해야함
                .build();
    }

    // 2. 리뷰 생성
    public static RestaurantResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review){
        return RestaurantResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 2에서 추가한 리뷰를 ReviewDTO에 보냄
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
    public static RestaurantResponseDTO.ReviewPreViewDTO toReviewPreViewDTO(Review review) {
        return RestaurantResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static RestaurantResponseDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> reviewList) {

        List<RestaurantResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(RestaurantConverter::toReviewPreViewDTO).collect(Collectors.toList());

        return RestaurantResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static RestaurantResponseDTO.MissionPreViewDTO toMissionPreViewDTO(Mission mission) {
        return RestaurantResponseDTO.MissionPreViewDTO.builder()
                .ownerRestaurant(mission.getRestaurant().getName())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static RestaurantResponseDTO.MissionPreViewListDTO toMissionPreViewListDTO(Page<Mission> missionList) {

        List<RestaurantResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(RestaurantResponseDTO::toMissionPreViewDTO).collect(Collectors.toList());

        return RestaurantResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }

}
