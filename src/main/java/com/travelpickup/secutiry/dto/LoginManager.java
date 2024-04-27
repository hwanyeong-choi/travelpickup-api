package com.travelpickup.secutiry.dto;

import com.travelpickup.member.domain.TravelPickupManager;
import com.travelpickup.member.enums.TravelPickupManagerRole;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class LoginManager implements UserDetails {

    private Long id;

    private String role;

    private Long centerId;

    public boolean isManager() {
        return this.role.equals(TravelPickupManagerRole.MANAGER.name());
    }

    public boolean isAdmin() {
        return this.role.equals(TravelPickupManagerRole.ADMIN.name());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Builder
    public LoginManager(Long id,
                        String role,
                        Long centerId) {
        this.id = id;
        this.role = role;
        this.centerId = centerId;
    }

    public static LoginManager of(Long id,
                                  String role,
                                  Long centerId) {
        return LoginManager
                .builder()
                .id(id)
                .role(role)
                .centerId(centerId)
                .build();
    }

}
