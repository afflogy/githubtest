package umc.study.service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.converter.RestaurantConverter;
import umc.study.domain.Review;
import umc.study.repository.MemberRepository;
import umc.study.repository.RestaurantRepository;
import umc.study.repository.ReviewRepository;
import umc.study.web.dto.RestaurantRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantCommandServiceImpl implements RestaurantCommandService {
    private final RestaurantRepository restaurantRepository;

    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Review createReview(Long memberId, Long restaurantId, RestaurantRequestDTO.ReviewDTO request) {

        Review review = RestaurantConverter.toReview(request);

        review.setMember(memberRepository.findById(memberId).get());
        review.setRestaurant(restaurantRepository.findById(restaurantId).get());

        return reviewRepository.save(review);
    }
}
