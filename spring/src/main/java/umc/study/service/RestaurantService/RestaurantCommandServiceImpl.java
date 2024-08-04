package umc.study.service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.converter.RestaurantConverter;
import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.RegionRepository;
import umc.study.repository.RestaurantRepository;
import umc.study.repository.ReviewRepository;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true)
public class RestaurantCommandServiceImpl implements RestaurantCommandService {

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberRepository;
    private final RegionRepository regionRepository;

//    @Override
//    @Transactional
//    public Restaurant joinRestaurant(RestaurantRequestDTO.JoinDTO request) {
//        Restaurant newRestaurant = RestaurantConverter.toRestaurant(request);
//        return restaurantRepository.save(newRestaurant);
//    }

    @Override
    @Transactional
    public Restaurant joinRestaurant(RestaurantRequestDTO.JoinDTO request, Region region) {

        Restaurant restaurant = new Restaurant();
        Restaurant newRestaurant = RestaurantConverter.toJoinRestaurantResultDTO(request, region);

        return restaurantRepository.save(newRestaurant);
    }

    @Override
    public Review createReview(Long memberId, Long restaurantId, RestaurantRequestDTO.ReviewDTO request) {
        Review review = RestaurantConverter.toReview(request);
        review.setMember(memberRepository.findById(memberId).get());
        review.setRestaurant(restaurantRepository.findById(restaurantId).get());
        return reviewRepository.save(review);
    }
}
