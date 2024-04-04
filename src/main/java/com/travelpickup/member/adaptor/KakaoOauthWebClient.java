package com.travelpickup.member.adaptor;

import com.travelpickup.member.dto.KakaoTokenResponseDto;
import com.travelpickup.member.dto.KakaoUserMeResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class KakaoOauthWebClient {

    private final String KAKAO_ACCESS_TOKEN_BASE_URI = "https://kauth.kakao.com";
    private final String KAKAO_USER_ME_BASE_URI = "https://kapi.kakao.com";

    private final String KAKAO_ME_PATH = "/v2/user/me";
    private final String KAKAO_OAUTH_TOKEN_PATH = "/oauth/token";

    private final String GRANT_TYPE_KEY = "grant_type";
    private final String GRANT_TYPE_VALUE =  "authorization_code";

    private final String REDIRECT_URI_KEY = "redirect_uri";
    private final String REDIRECT_URI_VALUE;

    private final String CLIENT_ID_KEY = "client_id";
    private final String CLIENT_ID_VALUE;

    private final String CODE_KEY = "code";

    private final String BEARER = "Bearer ";

    private WebClient kakaoWebTokenClient;
    private WebClient kakaoWebInfoClient;

    public KakaoOauthWebClient(@Value("${kakao.client-id}") String kakaoClientId,
                               @Value("${kakao.redirect-uri}") String kakaoRedirectUri) {
        this.CLIENT_ID_VALUE = kakaoClientId;
        this.REDIRECT_URI_VALUE = kakaoRedirectUri;
        this.kakaoWebTokenClient = WebClient.create(KAKAO_ACCESS_TOKEN_BASE_URI);
        this.kakaoWebInfoClient = WebClient.create(KAKAO_USER_ME_BASE_URI);
    }

    public KakaoTokenResponseDto getKakaoAccessToken(String codeValue) {

        MultiValueMap<String, String> kakaoAccessTokenQueryParam = getQueryParams(codeValue);

        return kakaoWebTokenClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(KAKAO_OAUTH_TOKEN_PATH)
                        .queryParams(kakaoAccessTokenQueryParam)
                        .build()
                )
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .retrieve()
                .bodyToMono(KakaoTokenResponseDto.class)
                .block();
    }


    public KakaoUserMeResponseDto getKakaoUserMe(String token) {
        return kakaoWebInfoClient
                .get()
                .uri(KAKAO_ME_PATH)
                .header(HttpHeaders.AUTHORIZATION,  BEARER + token)
                .retrieve()
                .bodyToMono(KakaoUserMeResponseDto.class)
                .block();
    }

    private MultiValueMap<String, String> getQueryParams(String codeValue) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add(GRANT_TYPE_KEY, GRANT_TYPE_VALUE);
        formData.add(REDIRECT_URI_KEY, REDIRECT_URI_VALUE);
        formData.add(CLIENT_ID_KEY, CLIENT_ID_VALUE);
        formData.add(CODE_KEY, codeValue);
        return formData;

    }

}
