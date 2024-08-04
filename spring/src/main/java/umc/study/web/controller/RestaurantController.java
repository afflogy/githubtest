//어노테이션으로 검증 - 3) 컨트롤러 메소드에 어노테이션 적용
package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.RestaurantConverter;
import umc.study.domain.Mission;
import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.service.RegionService.RegionCommandService;
import umc.study.service.RestaurantService.RestaurantCommandService;
import umc.study.service.RestaurantService.RestaurantQueryService;
import umc.study.validation.annotation.ExistMember;
import umc.study.validation.annotation.ExistRestaurant;
import umc.study.validation.annotation.ValidPage;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantCommandService restaurantCommandService;
    private final RestaurantQueryService restaurantQueryService;
    private final RegionCommandService regionCommandService;

    //week 9 스터디 노트 : 가게 리스트, 간단한 회원가입
    @PostMapping("/")
    public ApiResponse<RestaurantResponseDTO.JoinRestaurantResultDTO> join(
            @RequestBody @Valid RestaurantRequestDTO.JoinDTO request){
        Region region = regionCommandService.findById(request.getRegion());
        Restaurant restaurant = RestaurantCommandService.joinRestaurant(request, region); //joinRestaurant RestaurantCommandService에 생성함
        return ApiResponse.onSuccess(RestaurantConverter.toJoinRestaurantResultDTO(restaurant, region));
    }

    // week 10 스터디노트 : 가게 리뷰 Controller
    @GetMapping("/{restaurantId}/reviews")
    public ApiResponse<RestaurantResponseDTO.CreateReviewResultDTO> createReview(
            @RequestBody @Valid RestaurantRequestDTO.ReviewDTO request,
            @ExistRestaurant @PathVariable(name = "restaurantId") Long restaurantId,
            @ExistMember @RequestParam(name = "memberId") Long memberId) {

        Review review = restaurantCommandService.createReview(memberId, restaurantId, request);
        return ApiResponse.onSuccess(RestaurantConverter.toCreateReviewResultDTO(review));
    }

    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1페이지 입니다.")
    })
    public ApiResponse getReviewList(@ExistRestaurant @PathVariable(name = "restaurantId") Long restaurantId, @ValidPage @RequestParam(name = "page") Integer page){
        int zeroBasedPage = page - 1;
        RestaurantResponseDTO.ReviewPreViewListDTO reviews = restaurantCommandService.getReviewList(restaurantId, zeroBasedPage);
        return ApiResponse.onSuccess(reviews);
    }

    @Operation(summary = "특정 가게의 미션 목록 조회 API",description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("/{restaurantId}/missions")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1페이지 입니다."),
    })
    public ApiResponse<RestaurantResponseDTO.MissionPreViewListDTO> getMissionList(
            @ExistRestaurant @PathVariable(name = "restaurantId") Long restaurantId,
            @ValidPage @RequestParam(name = "page") Integer page){
        Page<Mission> missions = restaurantQueryService.getMissionList(restaurantId, page - 1);
        return ApiResponse.onSuccess(RestaurantConverter.toMissionPreViewListDTO(missions));
    }

}
