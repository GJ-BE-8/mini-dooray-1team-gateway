package com.nhnacademy.gateway.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProjectMemberDto
 {
     Long id;
     String name;
     String email;
     String role;
     ProjectDto project;
 }
