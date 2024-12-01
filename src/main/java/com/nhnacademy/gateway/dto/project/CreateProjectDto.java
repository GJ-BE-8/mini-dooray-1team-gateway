package com.nhnacademy.gateway.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateProjectDto {
    private String name;
    private String status;
}
