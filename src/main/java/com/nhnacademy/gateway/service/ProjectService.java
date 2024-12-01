package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.dto.project.CreateProjectDto;
import com.nhnacademy.gateway.dto.project.ProjectDto;
import com.nhnacademy.gateway.dto.project.ProjectWithMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final RestTemplate restTemplate;

    private String PROJECT_URL = "http://localhost:8082/api/project";

    // 프로젝트 전체 조회
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        ParameterizedTypeReference<List<ProjectDto>> responseType =
                new ParameterizedTypeReference<List<ProjectDto>>() {};

        return restTemplate.exchange(
                PROJECT_URL,
                HttpMethod.GET,
                null,
                responseType
        );
    }

    // id로 프로젝트 조회
    public ProjectDto getProjectById(Long id) {
        return restTemplate.getForObject(PROJECT_URL + "/" + id, ProjectDto.class);
    }

    // 프로젝트 생성
    public ResponseEntity<ProjectWithMemberDto> createProject(CreateProjectDto createProjectDto) {
        return restTemplate.postForEntity(PROJECT_URL, createProjectDto, ProjectWithMemberDto.class);
    }

}
