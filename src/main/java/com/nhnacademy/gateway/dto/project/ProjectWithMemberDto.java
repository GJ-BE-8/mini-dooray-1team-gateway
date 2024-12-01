package com.nhnacademy.gateway.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ProjectWithMemberDto {
    private ProjectDto project;
    private ProjectMemberDto projectMember;
}
