package umc.study.service.RestaurantService;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.RestaurantHandler;
import umc.study.domain.Mission;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.repository.MissionRepository;
import umc.study.repository.RestaurantRepository;
import umc.study.repository.ReviewRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantQueryServiceImpl implements RestaurantQueryService{

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    @Override
    public Optional<Restaurant> findRestaurant(Long id) {

        return restaurantRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewList(Long RestaurantId, Integer page) {
        Restaurant restaurant = restaurantRepository.findById(RestaurantId).orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));

        Page<Review> RestaurantPage = reviewRepository.findAllByRestaurant(restaurant, PageRequest.of(page, 10));

        return RestaurantPage;
    }

    @Override
    public Page<Mission> getMissionList(Long StoreId, Integer page) {

        Restaurant restaurant = restaurantRepository.findById(StoreId).orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));

        Page<Mission> MissionPage = missionRepository.findAllByRestaurant(restaurant, PageRequest.of(page, 10));

        return MissionPage;
    }
}
