package com.nhnacademy.gateway.dto.task;

import com.nhnacademy.gateway.dto.project.Project;
import com.nhnacademy.gateway.dto.project.ProjectDto;
import com.nhnacademy.gateway.dto.project.ProjectMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Task {
    Long id;
    String title;
    String content;
    Project project;
    ProjectMember projectMember;

}
