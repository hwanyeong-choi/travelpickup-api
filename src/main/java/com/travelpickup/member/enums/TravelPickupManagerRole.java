package com.travelpickup.member.enums;

public enum TravelPickupManagerRole {

    PENDING_APPROVAL("승인대기"),
    MANAGER("센터 매니저"),
    ADMIN("관리자");

    private String roleName;

    TravelPickupManagerRole(String roleName) {
        this.roleName = roleName;
    }

}
