package com.nhnacademy.gateway.dto.task;


import com.nhnacademy.gateway.dto.project.Project;

public record TagDto(
        Long id,
        String name,
        Project project
) {
}
