package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.dto.project.CreateProjectDto;
import com.nhnacademy.gateway.dto.project.ProjectDto;
import com.nhnacademy.gateway.dto.project.ProjectWithMemberDto;
import com.nhnacademy.gateway.exception.CreateFailureException;
import com.nhnacademy.gateway.exception.ProjectNotFoundException;
import com.nhnacademy.gateway.service.ProjectService;
import com.nhnacademy.gateway.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProejctController {

    private final ProjectService projectService;

    private final TaskService taskService;

    // 프로젝트 리스트
    @GetMapping("/project")
    public String projectList(Model model) {
        ResponseEntity<List<ProjectDto>> projectsEntity = projectService.getAllProjects();
        if(!projectsEntity.getStatusCode().is2xxSuccessful()) {
            throw new ProjectNotFoundException("프로젝트 전체 조회 실패");
        }
        model.addAttribute("projects", projectsEntity.getBody());
        return "/project/project-list";
    }

    // 업무 리스트(프로젝트 상세)
    @GetMapping("/project/{id}")
    public String projectDetails(@PathVariable("id") Long id,
                                 Model model) {
        ProjectDto project =  projectService.getProjectById(id);
        if(Objects.isNull(project)) {
            return "redirect:/project";
//            throw new ProjectNotFoundException("프로젝트 상세 조회 실패");
        }
        model.addAttribute("project", project);
        return "/project/project-detail";
    }

    // 프로젝트 생성 - GET
    @GetMapping("/project/register")
    public String registerProjectForm() {
        return "/project/project-register";
    }

    // 프로젝트 생성 - POST
    @PostMapping("/project/register")
    public String registerProject(Model model,
                                  CreateProjectDto createProjectDto) {
        ResponseEntity<ProjectWithMemberDto> projectEntity = projectService.createProject(createProjectDto);
        if(!projectEntity.getStatusCode().is2xxSuccessful()) {
            return "redirect:/project/project-register";
//            throw new CreateFailureException("프로젝트 생성 실패");
        }
        ProjectWithMemberDto projectWithMemberDto = projectEntity.getBody();
        Long projectId = projectWithMemberDto.getProject().getId();
        return "redirect:/project/" + projectId;
    }





}
