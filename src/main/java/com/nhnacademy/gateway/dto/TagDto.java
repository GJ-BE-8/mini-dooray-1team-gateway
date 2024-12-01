package com.nhnacademy.gateway.dto;

public record TagDto(
        Long id,
        String name,
        ProjectDto project
) {
}
