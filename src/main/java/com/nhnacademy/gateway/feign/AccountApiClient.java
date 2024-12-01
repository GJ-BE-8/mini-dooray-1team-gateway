package com.nhnacademy.gateway.feign;

import com.nhnacademy.gateway.dto.account.AccountDto;
import com.nhnacademy.gateway.dto.account.LoginDto;
import com.nhnacademy.gateway.dto.account.RegisterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="account-api", url="http://localhost:8081/accounts")
public interface AccountApiClient {

    // 회원 가입
    @PostMapping("/register")
    ResponseEntity<AccountDto> registerAccount(@RequestBody RegisterDto registerDto);

    // 로그인
    @PostMapping("/login")
    ResponseEntity<?> postLogin(@RequestBody LoginDto loginDto);

    // 모든 계정 조회
    @GetMapping("/all")
    ResponseEntity<List<AccountDto>> getAllAccount();

    // 계정 하나 조회
    @GetMapping("/{id}")
    ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id);

    // 회원 정보 수정
    @PutMapping("/{id}")
    ResponseEntity<AccountDto> updateAccount(@PathVariable("id") Long id, @RequestBody RegisterDto registerDto);

    // 회원 상태 변경
    @PutMapping("/{id}/status")
    ResponseEntity<AccountDto> updateAccountStatus(@PathVariable("id") Long id, @RequestParam String status);

    // 회원 삭제
    @DeleteMapping("/{id}")
    void deleteAccount(@PathVariable("id") Long id);

}
