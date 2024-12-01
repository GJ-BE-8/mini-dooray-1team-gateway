package com.nhnacademy.gateway.config;

import com.nhnacademy.gateway.filter.UserAuthenticationFilter;
import com.nhnacademy.gateway.handler.CustomAuthenticationFailureHandler;
import com.nhnacademy.gateway.handler.CustomAuthenticationSuccessHandler;
import com.nhnacademy.gateway.handler.CustomLogoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomLogoutHandler customLogoutHandler;
    private final RedisTemplate<String, Object> redisTemplate;

    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {

        // csrf disable
        http.csrf(AbstractHttpConfigurer::disable);

        // login authentication
        http.formLogin(formLogin -> formLogin
                .loginPage("/login")
                .usernameParameter("id")
                .passwordParameter("password")
                .loginProcessingUrl("/login/process") // 로그인 인증 요청을 처리하는 URL (POST 요청) -> UsernamePasswordAuthenticationFilter가 작동하여 로그인 인증 요청 처리함.
                .failureHandler(customAuthenticationFailureHandler)
                .successHandler(customAuthenticationSuccessHandler)
        );

        // User Authentication
        http.addFilterBefore(new UserAuthenticationFilter(redisTemplate), UsernamePasswordAuthenticationFilter.class);

        // Authorization
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers(HttpMethod.GET, "/login", "/account/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/login", "/account/register").permitAll()
                .anyRequest().authenticated()

        );

        // security logout
        http.logout(logout -> logout
                .logoutSuccessUrl("/loginForm")
                .addLogoutHandler(customLogoutHandler)
                .logoutSuccessHandler((request, response, authentication) ->
                        response.sendRedirect("/loginForm"))
                .deleteCookies("JSESSIONID", "SESSION")
        );

        return http.build();

    }



}
