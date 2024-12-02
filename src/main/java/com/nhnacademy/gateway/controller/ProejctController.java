package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.dto.account.AuthenticationDto;
import com.nhnacademy.gateway.dto.project.*;
import com.nhnacademy.gateway.exception.ProjectNotFoundException;
import com.nhnacademy.gateway.service.AccountService;
import com.nhnacademy.gateway.service.ProjectService;
import com.nhnacademy.gateway.service.TaskService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProejctController {

    private final RedisTemplate<String, Object> redisTemplate;
    private String SESSION_HASH_NAME = "Session:";

    private final ProjectService projectService;
    private final AccountService accountService;

    // 프로젝트 리스트
    @GetMapping("/project")
    public String projectList(Model model) {
        ResponseEntity<List<Project>> projectsEntity = projectService.getAllProjects();
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
        Project project =  projectService.getProjectById(id);
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
    public String registerProject(@ModelAttribute ProjectDto projectDto,
                                  HttpServletRequest request) {
        log.info("프로젝트 생성 post in");

        // 누가 이렇게 구상함? 개빡치네
        /* SESSION Cookie에서 sessionId 가져와서 redis 에서 userId 가져와서 accountapi에게 요청 보내서 user정보 가져옴 */
        Cookie[] cookies = request.getCookies();
        String accountId = null;
        for(Cookie c : cookies) {
            if(c.getName().equals("SESSION")) {
                accountId=c.getValue();
            }
        }
        if(Objects.isNull(accountId)) {
            return "redirect:/login";
        }
        AuthenticationDto authenticationDto = accountService.getAuthenticationByIds(accountId).getBody();
        ProjectMemberDto projectMemberDto = new ProjectMemberDto(
                authenticationDto.name(),
                authenticationDto.email(),
                "ADMIN"
        );
        CreateProject createProject = new CreateProject(
                projectDto,
                projectMemberDto
        );
        //

        log.info("project정보{}, 멤버정보:{}", projectDto.toString(), projectMemberDto.toString());

        ResponseEntity<ProjectWithMember> projectEntity = projectService.createProject(createProject);
        if(!projectEntity.getStatusCode().is2xxSuccessful()) {
            return "redirect:/project/project-register";
//            throw new CreateFailureException("프로젝트 생성 실패");
        }
        ProjectWithMember projectWithMember = projectEntity.getBody();
        Long projectId = projectWithMember.getProject().getId();
        return "redirect:/project/" + projectId;
    }





}
