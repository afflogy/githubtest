package umc.study.web.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.RestaurantConverter;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.service.RestaurantService.RestaurantCommandService;
import umc.study.validation.annotation.ExistMember;
import umc.study.validation.annotation.ExistRestaurant;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantCommandService restaurantCommandService;

    @PostMapping("/")
    public ApiResponse<RestaurantResponseDTO.JoinResultDTO> join(@RequestBody @Valid RestaurantRequestDTO.JoinDTO request){
        Restaurant restaurant = RestaurantCommandService.joinRestaurant(request); //joinRestaurant RestaurantCommandService에 생성함
        return ApiResponse.onSuccess(RestaurantConverter.toJoinResultDTO(restaurant));
    }

    @PostMapping("/{restaurantId}/reviews")
    public ApiResponse<RestaurantResponseDTO.CreateReviewResultDTO> createReview(@RequestBody @Valid RestaurantRequestDTO.ReviewDTO request,
                                                                                 @ExistRestaurant @PathVariable(name = "restaurantId") Long restaurantId,
                                                                                 @ExistMember @RequestParam(name = "memberId") Long memberId) {
        Review review = restaurantCommandService.createReview(memberId, restaurantId, request);
        return ApiResponse.onSuccess(RestaurantConverter.toCreateReviewResultDTO(review));
    }
}
