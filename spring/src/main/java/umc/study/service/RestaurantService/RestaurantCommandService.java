package umc.study.service.RestaurantService;

import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.web.dto.RestaurantRequestDTO;

public interface RestaurantCommandService {
    static Restaurant joinRestaurant(RestaurantRequestDTO.JoinDTO request) {
        return null;
    }

    Review createReview(Long memberId, Long restaurantId, RestaurantRequestDTO.ReviewDTO request);

    void getReviewList(Long restaurantId, Integer page);
}
