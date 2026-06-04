package com.toy.task.dto;

import com.toy.task.domain.Task;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskResponse {

    private Long id;
    private String title;
    private String memo;
    private String scheduledDate;
    private String priority;
    private String priorityLabel;
    private int xp;
    private boolean completed;
    private String createdAt;
    private String completedAt;

    public static TaskResponse from(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .memo(task.getMemo())
                .scheduledDate(task.getScheduledDate())
                .priority(task.getPriority())
                .priorityLabel(toPriorityLabel(task.getPriority()))
                .xp(task.getXp())
                .completed(task.isCompleted())
                .createdAt(task.getCreatedAt())
                .completedAt(task.getCompletedAt())
                .build();
    }

    private static String toPriorityLabel(String priority) {
        return switch (priority) {
            case "LOW" -> "Low";
            case "NORMAL" -> "Normal";
            case "HIGH" -> "High";
            default -> priority;
        };
    }
}
