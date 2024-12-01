package com.nhnacademy.gateway.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProjectDto
{
    Long id;
    String name;
    String status;
}
