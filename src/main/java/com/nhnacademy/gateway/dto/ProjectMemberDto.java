package com.nhnacademy.gateway.dto;

public record ProjectMemberDto(
        Long id,
        String name,
        String email,
        String role,
        ProjectDto project
) { }
