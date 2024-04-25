package com.travelpickup.member.domain;

import com.travelpickup.member.dto.KakaoUserMeResponseDto;
import com.travelpickup.member.enums.LoginProvider;
import com.travelpickup.member.enums.TravelPickupUserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "travelpickup_user")
public class TravelPickupUser {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @Column(name = "provider", columnDefinition = "VARCHAR(100)", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LoginProvider provider;

    @Column(name = "provider_id", unique = true, nullable = false)
    private Long providerId;

    @Column(name = "role", columnDefinition = "VARCHAR(100)", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TravelPickupUserRole travelPickupUserRole;

    @CreationTimestamp
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "modify_at", nullable = false)
    private LocalDateTime modifyAt;

    public TravelPickupUser() {}

    @Builder
    private TravelPickupUser(String nickName,
                             LoginProvider provider,
                             Long providerId,
                             TravelPickupUserRole travelPickupUserRole) {
        this.nickName = nickName;
        this.provider = provider;
        this.providerId = providerId;
        this.travelPickupUserRole = travelPickupUserRole;
    }

    public static TravelPickupUser createKakaoUser(KakaoUserMeResponseDto kakaoUserMeResponseDto) {
        return TravelPickupUser
                .builder()
                .provider(LoginProvider.kakao)
                .providerId(kakaoUserMeResponseDto.getId())
                .nickName(kakaoUserMeResponseDto.getProperties().getNickname())
                .travelPickupUserRole(TravelPickupUserRole.USER)
                .build();
    }



}
