package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.dto.account.AccountDto;
import com.nhnacademy.gateway.dto.account.AuthenticationDto;
import com.nhnacademy.gateway.dto.account.LoginDto;
import com.nhnacademy.gateway.dto.account.RegisterDto;
import com.nhnacademy.gateway.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AccountController {

    private final AccountService accountService;

    private RedisTemplate<String, Object> redisTemplate;

    private String SESSION_HASH_NAME = "Session:";

    // 로그인 GET
    @GetMapping("/login")
    public String getLogin() {
        return "account/login";
    }

    // 로그인 POST
    @PostMapping("/login")
    public String postLogin(@ModelAttribute LoginDto loginDto,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        ResponseEntity<?> responseEntity = accountService.loginAccount(loginDto);

        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            return "redirect:/";
        }
        return "account/login";
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

    // 회원 가입 - GET
    @GetMapping("/account/register")
    public String getRegister() {
        return "account/register";
    }

    // 회원 가입 - POST
    @PostMapping("/account/register")
    public String postRegister(@ModelAttribute RegisterDto registerDto) {
        ResponseEntity<AccountDto> responseEntity = accountService.registerAccount(registerDto);
        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            return "redirect:/login";
        }
        return "account/register";
    }

    // 마이 페이지 - 나의 계정 정보
    @GetMapping("/account/{ids}")
    public String getAccount(@PathVariable String ids, Model model) {
        ResponseEntity<AuthenticationDto> responseEntity = accountService.getAuthenticationByIds(ids);

        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("account", responseEntity.getBody());
            return "/account/myPage";
        }
        return "redirect:/login";
    }

    // 회원 정보 수정
    @PutMapping("/account/{id}")
    public String putAccount(@PathVariable Long id, @RequestBody RegisterDto registerDto) {
        accountService.updateAccount(id, registerDto);

        return "redirect:/account/" + id;
    }

    // 회원 삭제
    @DeleteMapping("/account/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "redirect:/login";
    }


}
