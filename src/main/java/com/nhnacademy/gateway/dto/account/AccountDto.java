package com.nhnacademy.gateway.dto.account;

public record AccountDto(
        Long id,
        String ids,
        String name,
        String email,
        String status
) { }
