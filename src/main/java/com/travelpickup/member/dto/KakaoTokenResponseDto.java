package com.travelpickup.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoTokenResponseDto {

	private String access_token;

	private String token_type;

	private String refresh_token;

	private String expires_in;

	private String scope;

	private String refresh_token_expires_in;

}
