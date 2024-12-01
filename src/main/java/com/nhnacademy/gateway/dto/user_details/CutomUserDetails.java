package com.nhnacademy.gateway.dto.user_details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class CutomUserDetails implements UserDetails {

    private String ids;
    private String password;
    private String role;

    public CutomUserDetails(String ids, String password) {
        this.ids = ids;
        this.password = password;
        this.role = "USER";
    }

    public CutomUserDetails(String ids, String password, PasswordEncoder passwordEncoder) {
        this.ids = ids;
        this.password = passwordEncoder.encode(password);
        this.role = "USER";
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
