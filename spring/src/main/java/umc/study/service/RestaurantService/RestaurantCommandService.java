package umc.study.service.RestaurantService;

import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

public interface RestaurantCommandService {
    // 9주차 미션 : 특정 가게 추가
    static Restaurant joinRestaurant(RestaurantRequestDTO.JoinDTO request, Region region) {
        return Restaurant;
    }
    Review createReview(Long memberId, Long restaurantId, RestaurantRequestDTO.ReviewDTO request);
    RestaurantResponseDTO.ReviewPreViewListDTO getReviewList(Long restaurantId, int zeroBasedPage);
}
