package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.dto.account.AccountDto;
import com.nhnacademy.gateway.dto.account.LoginDto;
import com.nhnacademy.gateway.dto.account.RegisterDto;
import com.nhnacademy.gateway.feign.AccountApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AccountController {

    private final AccountApiClient accountApiClient;

    // 로그인 GET
    @GetMapping("/login")
    public String getLogin() {
        return "account/login";
    }

    // 로그인 POST
    @PostMapping("/login")
    public String postLogin(@ModelAttribute LoginDto loginDto) {
        ResponseEntity<?> responseEntity = accountApiClient.postLogin(loginDto);
        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            return "home/index";
        }
        return "redirect:/login";
    }

    // 계정 전체 조회
//    @GetMapping("/accounts")
//    public String getAllAccounts(Model model) {
//        ResponseEntity<List<AccountDto>> responseEntity = accountApiClient.getAllAccount();
//        if(responseEntity.getStatusCode().is2xxSuccessful()) {
//            model.addAttribute("accounts", responseEntity.getBody());
//            return "account/accounts";
//        }
//    }

    // 회원 가입
    @PostMapping("/account")
    public String registerUser(@ModelAttribute RegisterDto registerDto) {
        ResponseEntity<AccountDto> responseEntity = accountApiClient.registerAccount(registerDto);
        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            return "account/login";
        }
        return "redirect:/register";
    }

    // 마이 페이지 - 나의 계정 정보
    @GetMapping("/account/{id}")
    public String getAccount(@PathVariable Long id, Model model) {
        ResponseEntity<AccountDto> responseEntity = accountApiClient.getAccountById(id);
        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("account", responseEntity.getBody());
            return "account/myPage";
        }
        return "redirect:/login";
    }

    // 회원 정보 수정
    @PutMapping("/account/{id}")
    public String putAccount(@PathVariable Long id, @RequestBody RegisterDto registerDto) {
        ResponseEntity<AccountDto> responseEntity = accountApiClient.updateAccount(id, registerDto);
        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            return "account/myPage";
        }
        return "redirect:/login";
    }

    // 회원 삭제
    @DeleteMapping("/account/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountApiClient.deleteAccount(id);
        return "redirect:/login";
    }


}
