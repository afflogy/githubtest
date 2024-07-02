package umc.study.service.RestaurantService;

import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.web.dto.RestaurantRequestDTO;

public interface RestaurantCommandService {
    Restaurant joinRestaurant(RestaurantRequestDTO.JoinDTO request);

    Review createReview(Long memberId, Long restaurantId, RestaurantRequestDTO.ReviewDTO request);
}
