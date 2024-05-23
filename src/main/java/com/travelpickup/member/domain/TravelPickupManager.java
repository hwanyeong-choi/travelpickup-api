package com.travelpickup.member.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travelpickup.member.dto.KakaoUserMeResponseDto;
import com.travelpickup.member.enums.LoginProvider;
import com.travelpickup.member.enums.TravelPickupManagerRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "travelpickup_manager")
public class TravelPickupManager {

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

	@Column(name = "center_id", nullable = true)
	private Long centerId;

	@Column(name = "role", columnDefinition = "VARCHAR(100)", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private TravelPickupManagerRole travelPickupManagerRole;

	@CreationTimestamp
	@Column(name = "create_at", nullable = false)
	private LocalDateTime createAt;

	@UpdateTimestamp
	@Column(name = "modify_at", nullable = false)
	private LocalDateTime modifyAt;

	@Builder
	public TravelPickupManager(String nickName, LoginProvider provider, Long providerId, Long centerId,
		TravelPickupManagerRole travelPickupManagerRole, LocalDateTime createAt, LocalDateTime modifyAt) {
		this.nickName = nickName;
		this.provider = provider;
		this.providerId = providerId;
		this.centerId = centerId;
		this.travelPickupManagerRole = travelPickupManagerRole;
		this.createAt = createAt;
		this.modifyAt = modifyAt;
	}

	public static TravelPickupManager createAdminKakaoUser(KakaoUserMeResponseDto kakaoUserMeResponseDto) {
		return TravelPickupManager.builder()
			.provider(LoginProvider.kakao)
			.providerId(kakaoUserMeResponseDto.getId())
			.nickName(kakaoUserMeResponseDto.getProperties().getNickname())
			.travelPickupManagerRole(TravelPickupManagerRole.PENDING_APPROVAL)
			.build();
	}

	public void assignPickupCenter(Long centerId) {
		this.centerId = centerId;
	}

}
