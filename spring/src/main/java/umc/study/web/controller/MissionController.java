package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.validation.annotation.ExistRestaurant;
import umc.study.validation.annotation.ValidPage;
import umc.study.web.dto.MissionResponseDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/mission")
public class MissionController {

    //week 9 스터디 노트 : 미션 목록 조회, 간단한 회원가입
    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.JoinResultDTO> join(@RequestBody @Valid MissionRequestDTO.AddDTO request){
        Mission mission = MissionCommandService.AddMission(request);
        return ApiResponse.onSuccess(MissionConverter.toAddResultDTO(mission));
    }

    // week 10 스터디노트 : 특정 가게 미션 목록
    @GetMapping("/{restaurantId}")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션들의 목록을 조회하는 API + 페이징을 포함. query String으로 page번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE4001", description = "유효한 가게가 없습니다.")
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디 path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<List<MissionResponseDTO.MissionListResultDTO>> getMissionList(@ExistRestaurant @PathVariable(name = "restaurantId") Long restaurantId, @ValidPage @RequestParam(name = "page") Integer page) {
        Page<Mission> mission = missionQueryService.getMissionList(restaurantId, page);
        return ApiResponse.onSuccess(MissionConverter.getMissionResultListDTO(mission));
    }

}
