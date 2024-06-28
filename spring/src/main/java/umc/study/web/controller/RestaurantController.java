package umc.study.web.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.RestaurantService.RestaurantCommandService;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantCommandService restaurantCommandService;

    @PostMapping
    public ApiResponse<RestaurantResponseDTO.JoinResultDTO> join(@RequestBody @Valid RestaurantRequestDTO.JoinDTO request) {
        return null;
    }
}
