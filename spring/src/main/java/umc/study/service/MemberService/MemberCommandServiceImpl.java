package umc.study.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.FoodCategoryHandler;
import umc.study.apiPayload.exception.handler.MissionHandler;
import umc.study.converter.MemberConverter;
import umc.study.converter.MemberPreferConverter;
import umc.study.converter.MissionConverter;
import umc.study.domain.FoodCategory;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MemberMission;
import umc.study.domain.mapping.MemberPrefer;
import umc.study.repository.FoodCategoryRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.MissionRepository;
import umc.study.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository; //엔티티와 상호작용하는 레포지토리
    private final FoodCategoryRepository foodCategoryRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDTO request) {

        // 1. 요청 DTO를 Member 엔티티로 변환
        Member newMember = MemberConverter.toMember(request);

        // 2. 회원이 선호하는 음식 카테고리를 DB에서 조회
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        // 3. FoodCategory 엔티티 리스트를 MemberPrefer 리스트로 변환
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);
        //memberPreferList.forEach(memberPrefer -> memberPrefer.setMember(newMember));
        for (MemberPrefer memberPrefer : memberPreferList) {
            memberPrefer.setMember(newMember);
        }

        // 4. member가 선택한 미션을 DB에서 조회
        List<Mission> missionList = request.getMission().stream()
                .map(missionId -> missionRepository.findById(missionId)
                        .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND)))
                .collect(Collectors.toList());

        // 5. Mission 엔티티 리스트를 MemberMission 리스트로 변환
        List<MemberMission> memberMissionList = MissionConverter.toMemberMissionList(missionList);
        for (MemberMission memberMission : memberMissionList) {
            memberMission.setMember(newMember);
        }

        //
        return memberRepository.save(newMember);
    }
}