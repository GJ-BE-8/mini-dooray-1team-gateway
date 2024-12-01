package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.dto.project.CreateProject;
import com.nhnacademy.gateway.dto.project.Project;
import com.nhnacademy.gateway.dto.project.ProjectDto;
import com.nhnacademy.gateway.dto.project.ProjectWithMember;
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
    public ResponseEntity<List<Project>> getAllProjects() {
        ParameterizedTypeReference<List<Project>> responseType =
                new ParameterizedTypeReference<List<Project>>() {};

        return restTemplate.exchange(
                PROJECT_URL,
                HttpMethod.GET,
                null,
                responseType
        );
    }

    // id로 프로젝트 조회
    public Project getProjectById(Long id) {
        return restTemplate.getForObject(PROJECT_URL + "/" + id, Project.class);
    }

    // 프로젝트 생성
    public ResponseEntity<ProjectWithMember> createProject(CreateProject createProject) {
        return restTemplate.postForEntity(PROJECT_URL, createProject, ProjectWithMember.class);
    }

}
