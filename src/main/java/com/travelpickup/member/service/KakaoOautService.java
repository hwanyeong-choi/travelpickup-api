package com.travelpickup.member.service;

import com.travelpickup.member.adaptor.KakaoOauthWebClient;
import com.travelpickup.member.domain.TravelPickupUser;
import com.travelpickup.member.dto.KakaoTokenResponseDto;
import com.travelpickup.member.dto.KakaoUserMeResponseDto;
import com.travelpickup.member.dto.LoginResponseDto;
import com.travelpickup.member.enums.LoginProvider;
import com.travelpickup.member.repository.TravelPickupUserRepository;
import com.travelpickup.util.JWTUtil;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class KakaoOautService {

    private final KakaoOauthWebClient kakaoOauthWebClient;
    private final TravelPickupUserRepository travelPickupUserRepository;

    private final JWTUtil jwtUtil;

    public KakaoOautService(KakaoOauthWebClient kakaoOauthWebClient,
                            TravelPickupUserRepository travelPickupUserRepository,
                            JWTUtil jwtUtil) {
        this.kakaoOauthWebClient = kakaoOauthWebClient;
        this.travelPickupUserRepository = travelPickupUserRepository;
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
                            saveTravelPickupUser.getUserRole().name(),
                            60*60*60*3600L
                    ));
        }

        TravelPickupUser travelPickupUser = TravelPickupUser.createKakaoUser(kakaoUserMeResponseDto);
        TravelPickupUser saveTravelPickupUser = travelPickupUserRepository.save(travelPickupUser);

        return  LoginResponseDto.of(
                jwtUtil.createJwt(
                        saveTravelPickupUser.getId(),
                        saveTravelPickupUser.getUserRole().name(),
                        60*60*60*3600L
                ));

    }

}
