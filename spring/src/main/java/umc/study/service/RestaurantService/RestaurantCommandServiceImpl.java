package umc.study.service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.ReviewHandler;
import umc.study.converter.RestaurantConverter;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.repository.MemberRepository;
import umc.study.repository.RestaurantRepository;
import umc.study.repository.ReviewRepository;
import umc.study.web.dto.RestaurantRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantCommandServiceImpl implements RestaurantCommandService {

    private final RestaurantRepository restaurantRepository;

    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Restaurant joinRestaurant(RestaurantRequestDTO.JoinDTO request) {

        Restaurant newRestaurant = RestaurantConverter.toRestaurant(request);
        List<Review> reviewList = request.getReview().stream()
                .map(reviewId -> reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewHandler(ErrorStatus.REVIEW_NOT_FOUND))).toList();

        newRestaurant.setReviews(reviewList);

        return restaurantRepository.save(newRestaurant);
    }

    @Override
    @Transactional
    public Review createReview(Long memberId, Long restaurantId, RestaurantRequestDTO.ReviewDTO request) {

        Review review = RestaurantConverter.toReview(request);

        review.setMember(memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"))); // Custom exception recommended
        review.setRestaurant(restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"))); // Custom exception recommended

        return reviewRepository.save(review);
    }
}
