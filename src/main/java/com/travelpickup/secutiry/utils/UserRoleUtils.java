package com.travelpickup.secutiry.utils;

import com.travelpickup.secutiry.dto.LoginManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("UserRoleUtils")
public class UserRoleUtils {


    public boolean isCheckManager(LoginManager loginManager) {
        return loginManager.isManager();
    }

    public boolean isCheckAdmin(LoginManager loginManager) {
        return loginManager.isAdmin();
    }

}
