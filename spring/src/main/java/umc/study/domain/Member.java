package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.MemberStatus;
import umc.spring.domain.enums.SocialType;
import umc.spring.domain.enums.mapping.MemberAgree;
import umc.spring.domain.enums.mapping.MemberPoint;
import umc.spring.domain.enums.mapping.MemberPrefer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false, length = 20)
   private String name;

   @Column(nullable = false, length = 20)
   private String phone_num;

   @Column(nullable = false, length = 40)
   private String address;

   @Enumerated(EnumType.STRING)
   @Column(columnDefinition = "VARCHAR(10)")
   private Gender gender;

   @Enumerated(EnumType.STRING)
   private SocialType socialType;

   @Enumerated(EnumType.STRING)
   @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
   private MemberStatus status;

   private LocalDate inactiveDate;

   @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
   private List<MemberAgree> memberAgreeList = new ArrayList<>();

   @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
   private List<MemberPrefer> memberPreferList = new ArrayList<>();

   @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
   private List<Review> reviewList = new ArrayList<>();

   @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
   private List<MemberPoint> memberPointList = new ArrayList<>();

}
