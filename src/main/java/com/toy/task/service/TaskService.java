package com.toy.task.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.toy.auth.user.domain.User;
import com.toy.auth.user.mapper.UserMapper;
import com.toy.common.exception.BusinessException;
import com.toy.task.domain.Task;
import com.toy.task.dto.TaskCreateRequest;
import com.toy.task.dto.TaskResponse;
import com.toy.task.mapper.TaskMapper;
import com.toy.task.type.Priority;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
	
	private final UserMapper userMapper;
    private final TaskMapper taskMapper;


	public List<TaskResponse> findAllByUsername(String name) {
		User user = findUserByUsername(name);
		
		List<Task> tasks = taskMapper.findAllByUserId(user.getId());
		
		return tasks.stream()
				.map(TaskResponse::from)
				.toList();
	}


	public TaskResponse create(String name, TaskCreateRequest request) {
		User user = findUserByUsername(name);
		
		Priority priority = parsePriority(request.getPriority());
		String scheduledDate = parseScheduledDate(request.getScheduledDate());
		
		Task task = Task.builder()
				.userId(user.getId())
				.memo(request.getMemo())
				.title(request.getTitle())
				.scheduledDate(scheduledDate)
				.priority(priority.name())
				.xp(priority.getXp())
				.completed(false)
				.build();
		
		taskMapper.insertTask(task);
		// 정확한 createdAt까지 주려면 insert 후 다시 조회해서 응답하는 게 좋다.
		Task savedTask = taskMapper.findByIdAndUserId(task.getId(), user.getId());
		return TaskResponse.from(savedTask);
	}


	public TaskResponse toggleComplete(String name, long id) {
		User user = findUserByUsername(name);
		
		Task task = taskMapper.findByIdAndUserId(id, user.getId());
		
		if(task == null) {
			throw new BusinessException("존재하지 않는 할 일입니다.", HttpStatus.NOT_FOUND);
		}
		
		boolean nextCompleted = !task.isCompleted();
		
		task.setCompleted(nextCompleted);
		
		taskMapper.updateComplete(task);
		
		Task savedTask = taskMapper.findByIdAndUserId(id, user.getId());
		return TaskResponse.from(savedTask);
	}


	public void delete(String name, long id) {
		User user = findUserByUsername(name);
		
		Task task = taskMapper.findByIdAndUserId(id, user.getId());
		if(task == null) {
			throw new BusinessException("존재하지 않는 할 일입니다.", HttpStatus.NOT_FOUND);
		}
				
		taskMapper.deleteByIdAndUserId(id, user.getId());
	}
	
	private User findUserByUsername(String name) {
		User user = userMapper.findByUsername(name);
		
		if(user == null) {
            throw new BusinessException("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND);
		}
		
		return user;
	}

	private Priority parsePriority(String priority) {
		try{
			return Priority.valueOf(priority);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("우선순위는 LOW, NORMAL, HIGH 중 하나여야 합니다.", HttpStatus.BAD_REQUEST);
		}

	}

	private String parseScheduledDate(String scheduledDate) {
		try {
			return LocalDate.parse(scheduledDate).toString();
		} catch (DateTimeParseException e) {
			throw new BusinessException("일정 날짜는 yyyy-MM-dd 형식이어야 합니다.", HttpStatus.BAD_REQUEST);
		}
	}

}
