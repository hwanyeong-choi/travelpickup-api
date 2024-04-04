package com.travelpickup.member.enums;

public enum UserRole {

    USER("일반유저"), ADMIN("관리자");

    private String roleName;


    UserRole(String roleName) {
        this.roleName = roleName;
    }

}
