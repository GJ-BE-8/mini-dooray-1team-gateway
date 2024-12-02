package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.dto.task.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class TaskService {

    private final RestTemplate restTemplate;

    private String TESK_URL = "http://localhost:8082/tasks";

   // 프로젝트의 Task 전체 가져오기

}
