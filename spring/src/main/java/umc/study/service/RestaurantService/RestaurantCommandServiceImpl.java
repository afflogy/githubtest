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
import umc.study.repository.MemberRepository;
import umc.study.repository.RegionRepository;
import umc.study.repository.RestaurantRepository;
import umc.study.repository.ReviewRepository;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantCommandServiceImpl implements RestaurantCommandService {

    private final RestaurantRepository restaurantRepository;

    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Restaurant joinRestaurant(RestaurantRequestDTO.JoinDTO request) {
        Restaurant newRestaurant = RestaurantConverter.toRestaurant(request);
        return restaurantRepository.save(newRestaurant);
    }

    @Override
    @Transactional
    public Review createReview(Long memberId, Long restaurantId, RestaurantRequestDTO.ReviewDTO request) {
        Review review = RestaurantConverter.toReview(request);
        review.setMember(memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 MemberID")));
        review.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 RestaurantID")));
        return reviewRepository.save(review);
    }

    @Override
    public RestaurantResponseDTO.ReviewPreViewListDTO getReviewList(Long restaurantId, int page) {
        // restaurantId로 Restaurant 객체 조회
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid restaurantId: " + restaurantId));

        // PageRequest 객체 생성
        PageRequest pageRequest = PageRequest.of(page, 10);

        // 조회된 Restaurant 객체로 리뷰 목록 조회
        Page<Review> reviews = reviewRepository.findAllByRestaurant(restaurant, pageRequest);

        // 리뷰 목록을 DTO로 변환 toreviewPreViewListDTO(reviews)에서 reviews를 toreviewPreViewListDTO는 원래 List로 받는데
        // 아래 코드에서는 reviews를 Page로 받아야하는 조건이 있어서 Converter에서 toreviewPreViewListDTO파라미터를 Page로 바꿈
        return RestaurantConverter.toreviewPreViewListDTO(reviews);
    }

    @Override
    public Restaurant addRestaurantToArea(RestaurantRequestDTO.AddRestaurantDTO request) {
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid areaId: " + request.getRegionId()));

        Restaurant restaurant = RestaurantConverter.toRestaurant(request);
        restaurant.setRegion(region);

        return restaurantRepository.save(restaurant);
    }

}
