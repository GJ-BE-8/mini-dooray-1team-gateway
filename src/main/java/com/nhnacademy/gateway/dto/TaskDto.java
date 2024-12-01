package com.nhnacademy.gateway.dto;

public record TaskDto(
        Long id,
        String title,
        String content,
        ProjectDto project,
        ProjectMemberDto projectMember,
        MilestoneDto mileStone,
        TagDto tag
) { }
