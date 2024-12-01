package com.nhnacademy.gateway.dto;

public record MilestoneDto(
        Long id,
        String name,
        ProjectDto project
) {
}
