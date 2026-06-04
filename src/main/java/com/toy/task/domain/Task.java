package com.toy.task.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    private long id;
    private long userId;
    private String title;
    private String memo;
    private String scheduledDate;
    private String priority;
    private int xp;
    private boolean completed;
    private String createdAt;
    private String completedAt;

}
