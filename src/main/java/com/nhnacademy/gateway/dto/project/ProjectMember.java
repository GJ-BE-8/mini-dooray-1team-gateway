package com.nhnacademy.gateway.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProjectMember
 {
     Long id;
     String name;
     String email;
     String role;
     Project project;
 }
