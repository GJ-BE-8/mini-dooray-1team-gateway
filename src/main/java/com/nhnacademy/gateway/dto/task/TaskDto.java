package com.nhnacademy.gateway.dto.task;

import com.nhnacademy.gateway.dto.project.ProjectDto;
import com.nhnacademy.gateway.dto.project.ProjectMemberDto;

public record TaskDto(
        Long id,
        String title,
        String content,
        ProjectDto project,
        ProjectMemberDto projectMember,
        MilestoneDto mileStone,
        TagDto tag
) { }
