package com.nhnacademy.gateway.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private String SESSION_HASH_NAME = "Session:";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("로그인 성공 핸들러");
        /* 로그인하면 SESSION 이란 이름의 쿠키를 생성하고, 레디스에 세션 생성함. */

        HttpSession session = request.getSession(false);
        User user = (User) authentication.getPrincipal();

        Cookie cookie = new Cookie("SESSION", user.getUsername()); // user 의 id를 SESSION 쿠키에 value 로 넣음.
        cookie.setHttpOnly(true); // 보안 설정
        cookie.setMaxAge(60*60); // 쿠키 유효 시간(1시간)
        cookie.setPath("/"); // 모든 경로에서 쿠키 접근 가능
        response.addCookie(cookie);


        redisTemplate.opsForHash().put(SESSION_HASH_NAME, session.getId(), user.getUsername()); // Redis에 Session:{sessionID, User ID} 저장.
        redisTemplate.expire(SESSION_HASH_NAME, 60, TimeUnit.MINUTES); // Redis에 세션 만료 시간(1시간) 설정.

        response.sendRedirect("/");
    }

}
