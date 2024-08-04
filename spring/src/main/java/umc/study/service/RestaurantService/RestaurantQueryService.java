package umc.study.service.RestaurantService;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;

import java.util.Optional;

public interface RestaurantQueryService {
    Optional<Restaurant> findStore(Long id);
    Page<Review> getReviewList(Long StoreId, Integer page);
    Page<Mission> getMissionList(Long StoreId, Integer page);
}
