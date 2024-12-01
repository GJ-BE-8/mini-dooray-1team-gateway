package com.nhnacademy.gateway.dto.user_details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class CutomUserDetails implements UserDetails {

    private Long id;
    private String ids;
    private String password;
    private String name;
    private String email;
    private String role;

    public CutomUserDetails(String ids, String password, String name, String email) {
        this.ids = ids;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = "USER";
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() { // 로그인 id 리턴
        return this.ids;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "ROLE" + this.role;
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }
}
