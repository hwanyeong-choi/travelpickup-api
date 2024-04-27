package com.travelpickup.member.enums;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public enum TravelPickupManagerRole {

    PENDING_APPROVAL("승인대기"),
    MANAGER("센터 매니저"),
    ADMIN("관리자");

    private String roleName;

    TravelPickupManagerRole(String roleName) {
        this.roleName = roleName;
    }

    public List<String> getManagerRoles() {
        return Arrays.asList(MANAGER.name(), ADMIN.name());
    }

}
