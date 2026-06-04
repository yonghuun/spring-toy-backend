package com.toy.task.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.task.dto.TaskCreateRequest;
import com.toy.task.dto.TaskResponse;
import com.toy.task.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
	
    private final TaskService taskService;
	
    @GetMapping
    public List<TaskResponse> getTasks(Authentication auth) {
    	return taskService.findAllByUsername(auth.getName());
    }
    
    @PostMapping
    public TaskResponse create(Authentication auth, @Valid @RequestBody TaskCreateRequest request) {
    	return taskService.create(auth.getName(), request);
    }
    
    @PatchMapping("/{id}/complete")
    public TaskResponse toggleComplete(Authentication auth, @PathVariable long id) {
    	return taskService.toggleComplete(auth.getName(), id);
    }

    @DeleteMapping("/{id}")
    public void delete(Authentication auth, @PathVariable long id){
        taskService.delete(auth.getName(), id);
    }
	

}
