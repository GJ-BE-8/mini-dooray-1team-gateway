//package com.nhnacademy.gateway.feign;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import com.nhnacademy.gateway.dto.TaskDto;
//
//@FeignClient(name="task-api", url="http://localhost:8082/tasks", configuration = FeignClient.class)
//public interface TaskApiClient {
//
//    @GetMapping("/{id}")
//    TaskDto getTask(@PathVariable("id") Long id);
//}
