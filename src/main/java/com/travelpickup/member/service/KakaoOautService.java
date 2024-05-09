package com.travelpickup.member.service;

import com.travelpickup.member.adaptor.KakaoOauthWebClient;
import com.travelpickup.member.domain.TravelPickupManager;
import com.travelpickup.member.domain.TravelPickupUser;
import com.travelpickup.member.dto.KakaoTokenResponseDto;
import com.travelpickup.member.dto.KakaoUserMeResponseDto;
import com.travelpickup.member.dto.LoginResponseDto;
import com.travelpickup.member.repository.TravelPickupManagerRepository;
import com.travelpickup.member.repository.TravelPickupUserRepository;
import com.travelpickup.secutiry.util.JWTUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class KakaoOautService {

    private final KakaoOauthWebClient kakaoOauthWebClient;
    private final TravelPickupUserRepository travelPickupUserRepository;
    private final TravelPickupManagerRepository travelPickupManagerRepository;

    private final JWTUtil jwtUtil;

    public KakaoOautService(KakaoOauthWebClient kakaoOauthWebClient,
                            TravelPickupUserRepository travelPickupUserRepository,
                            TravelPickupManagerRepository travelPickupManagerRepository,
                            JWTUtil jwtUtil) {
        this.kakaoOauthWebClient = kakaoOauthWebClient;
        this.travelPickupUserRepository = travelPickupUserRepository;
        this.travelPickupManagerRepository = travelPickupManagerRepository;
        this.jwtUtil = jwtUtil;
    }

    @Transactional(readOnly = false)
    public LoginResponseDto login(String codeValue) {
        KakaoTokenResponseDto kakaoTokenResponseDto = kakaoOauthWebClient.getKakaoAccessToken(codeValue);
        KakaoUserMeResponseDto kakaoUserMeResponseDto = kakaoOauthWebClient.getKakaoUserMe(kakaoTokenResponseDto.getAccess_token());

        Optional<TravelPickupUser> optionalTravelPickupUser = travelPickupUserRepository.findByProviderId(kakaoUserMeResponseDto.getId());

        if (optionalTravelPickupUser.isPresent()) {
            TravelPickupUser saveTravelPickupUser = optionalTravelPickupUser.get();
            return LoginResponseDto.of(
                    jwtUtil.createJwt(
                            saveTravelPickupUser.getId(),
                            saveTravelPickupUser.getTravelPickupUserRole().name(),
                            60*60*60*3600L
                    ));
        }

        TravelPickupUser travelPickupUser = TravelPickupUser.createKakaoUser(kakaoUserMeResponseDto);
        TravelPickupUser saveTravelPickupUser = travelPickupUserRepository.save(travelPickupUser);

        return  LoginResponseDto.of(
                jwtUtil.createJwt(
                        saveTravelPickupUser.getId(),
                        saveTravelPickupUser.getTravelPickupUserRole().name(),
                        60*60*60*3600L
                ));

    }

    @Transactional(readOnly = false)
    public LoginResponseDto adminLogin(String codeValue) {
        KakaoTokenResponseDto kakaoTokenResponseDto = kakaoOauthWebClient.getKakaoAccessToken(codeValue);
        KakaoUserMeResponseDto kakaoUserMeResponseDto = kakaoOauthWebClient.getKakaoUserMe(kakaoTokenResponseDto.getAccess_token());

        Optional<TravelPickupManager> optionalTravelPickupManager = travelPickupManagerRepository.findByProviderId(kakaoUserMeResponseDto.getId());

        if (optionalTravelPickupManager.isPresent()) {
            TravelPickupManager saveTravelPickupManager = optionalTravelPickupManager.get();
            return LoginResponseDto.of(
                    jwtUtil.createJwt(
                            saveTravelPickupManager.getId(),
                            saveTravelPickupManager.getCenterId(),
                            saveTravelPickupManager.getTravelPickupManagerRole().name(),
                            60*60*60*3600L
                    ));
        }

        TravelPickupManager travelPickupManager = TravelPickupManager.createAdminKakaoUser(kakaoUserMeResponseDto);
        TravelPickupManager saveTravelPickupManager = travelPickupManagerRepository.save(travelPickupManager);

        return  LoginResponseDto.of(
                jwtUtil.createJwt(
                        saveTravelPickupManager.getId(),
                        saveTravelPickupManager.getCenterId(),
                        saveTravelPickupManager.getTravelPickupManagerRole().name(),
                        60*60*60*3600L
                ));

    }

}
