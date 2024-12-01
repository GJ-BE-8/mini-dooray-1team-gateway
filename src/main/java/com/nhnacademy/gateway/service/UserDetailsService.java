package com.nhnacademy.gateway.service;


import com.nhnacademy.gateway.dto.account.AccountDto;
import com.nhnacademy.gateway.dto.account.AuthenticationDto;
import com.nhnacademy.gateway.dto.user_details.CutomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthenticationDto authenticationDto = accountService.getAuthenticationByIds(username).getBody();

        String encodedPassword = passwordEncoder.encode(String.valueOf(authenticationDto.password()));

        return new CutomUserDetails(authenticationDto.ids(), authenticationDto.password());

    }
}
