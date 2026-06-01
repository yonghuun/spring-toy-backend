package com.toy.quest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toy.auth.user.domain.User;
import com.toy.auth.user.mapper.UserMapper;
import com.toy.quest.domain.Quest;
import com.toy.quest.dto.QuestCreateRequest;
import com.toy.quest.dto.QuestResponse;
import com.toy.quest.mapper.QuestMapper;
import com.toy.quest.type.Difficulty;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestService {
	
	private final UserMapper userMapper;
    private final QuestMapper questMapper;


	public List<QuestResponse> findAllByUsername(String name) {
		User user = findUserByUsername(name);
		
		List<Quest> quests = questMapper.findAllByUserId(user.getId());
		
		return quests.stream()
				.map(QuestResponse::from)
				.toList();
	}


	public QuestResponse create(String name, QuestCreateRequest request) {
		User user = findUserByUsername(name);
		
		Difficulty difficulty = Difficulty.valueOf(request.getDifficulty());
		
		Quest quest = Quest.builder()
				.userId(user.getId())
				.memo(request.getMemo())
				.title(request.getTitle())
				.difficulty(difficulty.name())
				.xp(difficulty.getXp())
				.completed(false)
				.build();
		
		questMapper.insertQuest(quest);
		// 정확한 createdAt까지 주려면 insert 후 다시 조회해서 응답하는 게 좋다.
		Quest savedQuest = questMapper.findByIdAndUserId(quest.getId(), user.getId());
		return QuestResponse.from(savedQuest);
	}


	public QuestResponse toggleComplete(String name, long id) {
		User user = findUserByUsername(name);
		
		Quest quest = questMapper.findByIdAndUserId(id, user.getId());
		
		if(quest == null) {
			throw new RuntimeException("존재하지 않는 퀘스트");
		}
		
		boolean nextCompleted = !quest.isCompleted();
		
		quest.setCompleted(nextCompleted);
		
		questMapper.updateComplete(quest);
		
		Quest savedQuest = questMapper.findByIdAndUserId(id, user.getId());
		return QuestResponse.from(savedQuest);
	}


	public void delete(String name, long id) {
		User user = findUserByUsername(name);
		
		Quest quest = questMapper.findByIdAndUserId(id, user.getId());
		if(quest==null) {
			throw new RuntimeException("존재하지 않는 퀘스트");
		}
				
		questMapper.deleteByIdAndUserId(id, user.getId());
	}
	
	private User findUserByUsername(String name) {
		User user = userMapper.findByUsername(name);
		
		if(user == null) {
			throw new RuntimeException("존재하지 않는 사용자");
		}
		
		return user;
	}

}
