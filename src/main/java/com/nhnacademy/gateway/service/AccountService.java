package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.dto.account.AccountDto;
import com.nhnacademy.gateway.dto.account.AuthenticationDto;
import com.nhnacademy.gateway.dto.account.LoginDto;
import com.nhnacademy.gateway.dto.account.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// restTemplate.getForObject(String url, responseType(응답 받을 객체의 타입 지정), uriVariable(url에 변수 포함));

@RequiredArgsConstructor
@Service
public class AccountService {

    private final RestTemplate restTemplate;

    private String ACCOUNT_URL = "http://localhost:8081/accounts";

    // 회원 가입
    public ResponseEntity<AccountDto> registerAccount(RegisterDto registerDto) {
        return restTemplate.postForEntity(ACCOUNT_URL + "/register", registerDto, AccountDto.class);
    }

    // 로그인
    public ResponseEntity<AccountDto> loginAccount(LoginDto loginDto) {
        return restTemplate.postForEntity(ACCOUNT_URL + "/login", loginDto, AccountDto.class);
    }

//    // 모든 계정 조회
//    public ResponseEntity<List<AccountDto>> getAllAccount() {
//        return restTemplate.getForEntity(ACCOUNT_URL+"/all", List<AccountDto>.class);
//    }

    // 계정 1개 조회
    public ResponseEntity<AccountDto> getAccountById(Long id) {
        return restTemplate.getForEntity(ACCOUNT_URL + "/" + id, AccountDto.class);
    }

//    // ids로 계정 조회
//    public ResponseEntity<AccountDto> getAccountByIds(String ids) {
//        ResponseEntity<AccountDto> forEntity = restTemplate.getForEntity(ACCOUNT_URL + "/" + ids, AccountDto.class);
//        System.out.println(forEntity.getBody());
//        return forEntity;
//    }

    // ids로 인증 id, pw, name, email 조회
    public ResponseEntity<AuthenticationDto> getAuthenticationByIds (String ids) {
        ResponseEntity<AuthenticationDto> forEntity = restTemplate.getForEntity(ACCOUNT_URL + "/" + ids, AuthenticationDto.class);
        System.out.println(forEntity.getBody());
        return forEntity;
    }

    // 회원 정보 업데이트
    public void updateAccount(Long id, RegisterDto registerDto) {
         restTemplate.put(ACCOUNT_URL+"/" + id, registerDto, AccountDto.class);
    }

    // 회원 삭제
    public void deleteAccount(Long id) {
        restTemplate.delete(ACCOUNT_URL+"/" + id);
    }



}
