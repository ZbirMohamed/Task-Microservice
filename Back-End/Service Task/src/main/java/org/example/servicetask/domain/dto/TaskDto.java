package org.example.servicetask.domain.dto;

import lombok.*;
import org.example.servicetask.domain.enums.TaskStatus;

@AllArgsConstructor @NoArgsConstructor
@Builder @Getter @Setter @ToString
public class TaskDto {
    private Integer id;

    private String title;
    // steps to take
    private String Description;

    private TaskStatus status;

    private Integer userId;
}
