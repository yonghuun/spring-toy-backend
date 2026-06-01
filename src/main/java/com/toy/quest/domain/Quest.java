package com.toy.quest.domain;

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
public class Quest {

    private long id;
    private long userId;
    private String title;
    private String memo;
    private String difficulty;
    private int xp;
    private boolean completed;
    private String createdAt;
    private String completedAt;

}
