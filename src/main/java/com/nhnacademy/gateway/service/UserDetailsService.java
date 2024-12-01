package com.nhnacademy.gateway.service;


import com.nhnacademy.gateway.dto.user_details.CutomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {



        return new CutomUserDetails();

    }
}
