package umc.study.service.RestaurantService;

import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

public interface RestaurantCommandService {
    static Restaurant joinRestaurant(RestaurantRequestDTO.JoinDTO request) {
        return null;
    }

    // 9주차 미션 : 특정 가게 추가
    Restaurant addRestaurantToRegion(RestaurantRequestDTO.AddRestaurantDTO request);

    Review createReview(Long memberId, Long restaurantId, RestaurantRequestDTO.ReviewDTO request);

    RestaurantResponseDTO.ReviewPreViewListDTO getReviewList(Long restaurantId, int page);
}
