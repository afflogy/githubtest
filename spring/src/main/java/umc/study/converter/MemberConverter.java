package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.enums.Gender;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {


    // 1. 사용자 가입
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
        // dto에 있는 파일의 속성을 가져옴
    }

    // 1에 대해서 MemberRequestDTO에 보냄
    public static Member toMember(MemberRequestDTO.JoinDTO request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }
        return Member.builder()
                .address(request.getAddress())
                .gender(gender)
                .name(request.getName())
                .phone_num(request.getPhon_number())
                .missionList(new ArrayList<>())
                .memberPreferList(new ArrayList<>()) // **리스트는 <>()로 초기화해야함
                .build();
    }

    // 2. 사용자의 리뷰 미리보기
    public static MemberResponseDTO.MyReviewPreViewDTO myReviewPreViewDTO(Review review) {
        return MemberResponseDTO.MyReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    // 2에 대한 미리보기 Res
    public static MemberResponseDTO.MyReviewPreViewListDTO myReviewPreViewListDTO(Page<Review> reviewList) {

        List<MemberResponseDTO.MyReviewPreViewDTO> myReviewPreViewDTOList = reviewList.stream()
                .map(MemberConverter::myReviewPreViewDTO).collect(Collectors.toList());

        return MemberResponseDTO.MyReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(myReviewPreViewDTOList.size())
                .reviewList(myReviewPreViewDTOList)
                .build();
    }
}