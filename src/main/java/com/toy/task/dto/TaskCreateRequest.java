package com.toy.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskCreateRequest {

    @NotBlank
    @Size(max = 100)
    private String title;

    @Size(max = 255)
    private String memo;

    @NotBlank
    private String scheduledDate;

    @NotNull
    private String priority;

}
