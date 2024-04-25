package com.travelpickup.member.enums;

public enum TravelPickupUserRole {

    USER("일반유저"),
    MANAGER("센터 매니저"),
    ADMIN("관리자");

    private String roleName;

    TravelPickupUserRole(String roleName) {
        this.roleName = roleName;
    }

}
