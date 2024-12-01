package com.nhnacademy.gateway.dto.task;

import com.nhnacademy.gateway.dto.project.ProjectDto;

public record MilestoneDto(
        Long id,
        String name,
        ProjectDto project
) {
}
