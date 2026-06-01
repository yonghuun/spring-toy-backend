package com.toy.quest.controller;

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

import com.toy.quest.dto.QuestCreateRequest;
import com.toy.quest.dto.QuestResponse;
import com.toy.quest.service.QuestService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/quests")
@RequiredArgsConstructor
public class QuestController {
	
    private final QuestService questService;
	
    @GetMapping
    public List<QuestResponse> getQuests(Authentication auth) {
    	return questService.findAllByUsername(auth.getName());
    }
    
    @PostMapping
    public QuestResponse create(Authentication auth, @Valid @RequestBody QuestCreateRequest request) {
    	return questService.create(auth.getName(), request);
    }
    
    @PatchMapping("/{id}/complete")
    public QuestResponse toggleComplete(Authentication auth, @PathVariable long id) {
    	return questService.toggleComplete(auth.getName(), id);
    }

    @DeleteMapping("/{id}")
    public void delete(Authentication auth, @PathVariable long id){
        questService.delete(auth.getName(), id);
    }
	

}
