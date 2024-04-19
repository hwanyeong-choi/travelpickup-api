package com.travelpickup.member.enums;

import lombok.Getter;

@Getter
public enum MemberResponseMessage {

    CREATE("로그인 또는 회원가입에 성공하였습니다."),
    RE_TRY_LOGIN("로그인 또는 회원가입에 실패하였습니다. 다시 시도해 주세요.");

    private String message;

    MemberResponseMessage(String message) {
        this.message = message;
    }

}
