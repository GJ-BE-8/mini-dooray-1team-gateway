package com.nhnacademy.gateway.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class UserAuthenticationFilter extends OncePerRequestFilter {

    private final RedisTemplate<String, Object> redisTemplate;
    private String SESSION_HASH_NAME = "Session:";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("사용자 인증 필터");

        String sessionId = request.getSession().getId();
        String accountId = null;



        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie c : cookies) {
                if(c.getName().equals("SESSION")) {
                    accountId = c.getValue();
                }
            }
        }

        log.info("session:{}, accountId:{}", sessionId, accountId);

        if(sessionId != null || accountId != null) {
            String redisAccountId = (String) redisTemplate.opsForHash().get(SESSION_HASH_NAME, sessionId);

            if(!( Objects.isNull(redisAccountId) || redisAccountId.isEmpty() || Objects.isNull(accountId) || accountId.isEmpty()) ) {

                if(redisAccountId.equals(accountId)) { // memberId가 redis에 존재하면.

                    Authentication authentication = new PreAuthenticatedAuthenticationToken(accountId, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }
            }
        }

        filterChain.doFilter(request,response);

    }
}
