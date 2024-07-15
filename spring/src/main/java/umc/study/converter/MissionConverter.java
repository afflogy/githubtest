package umc.study.converter;

import umc.study.domain.Mission;
import umc.study.domain.mapping.MemberMission;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static List<MemberMission> toMemberMissionList(List<Mission> memberMissionList){

        return memberMissionList.stream()
                .map(mission ->
                        MemberMission.builder()
                                .mission(mission)
                                .build()
                ).collect(Collectors.toList()).reversed();
    }
}
