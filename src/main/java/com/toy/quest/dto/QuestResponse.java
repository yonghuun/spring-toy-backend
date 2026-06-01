package com.toy.quest.dto;

import com.toy.quest.domain.Quest;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestResponse {

    private Long id;
    private String title;
    private String memo;
    private String difficulty;
    private String difficultyLabel;
    private int xp;
    private boolean completed;
    private String createdAt;
    private String completedAt;

    public static QuestResponse from(Quest quest) {
        return QuestResponse.builder()
                .id(quest.getId())
                .title(quest.getTitle())
                .memo(quest.getMemo())
                .difficulty(quest.getDifficulty())
                .difficultyLabel(toDifficultyLabel(quest.getDifficulty()))
                .xp(quest.getXp())
                .completed(quest.isCompleted())
                .createdAt(quest.getCreatedAt())
                .completedAt(quest.getCompletedAt())
                .build();
    }

    private static String toDifficultyLabel(String difficulty) {
        return switch (difficulty) {
            case "EASY" -> "Easy";
            case "NORMAL" -> "Normal";
            case "HARD" -> "Hard";
            default -> difficulty;
        };
    }
}