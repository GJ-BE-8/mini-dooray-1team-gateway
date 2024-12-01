package com.nhnacademy.gateway.dto.account;

public record AuthenticationDto(
        String ids,
        String password,
        String name,
        String email) {
}
