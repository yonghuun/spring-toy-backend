package com.toy.quest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestCreateRequest {

    @NotBlank
    @Size(max = 100)
    private String title;

    @Size(max = 255)
    private String memo;

    @NotNull
    private String difficulty;

}
