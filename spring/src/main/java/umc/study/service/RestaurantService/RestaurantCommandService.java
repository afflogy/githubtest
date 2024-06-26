package umc.study.service.RestaurantService;

import umc.study.domain.Review;
import umc.study.web.dto.RestaurantRequestDTO;

public interface RestaurantCommandService {
    Review createReview(Long memberId, Long restaurantId, RestaurantRequestDTO.ReviewDTO request);
}
