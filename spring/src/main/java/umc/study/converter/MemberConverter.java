package umc.study.converter;

import umc.study.domain.Member;
import umc.study.domain.enums.Gender;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
        // dto에 있는 파일의 속성을 가져옴
    }

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
}

// 향상된 switch문
//Gender gender = switch (request.getGender()) {
//    case 1 -> Gender.MALE;
//    case 2 -> Gender.FEMALE;
//    case 3 -> Gender.NONE;
//    default -> null;
//};
