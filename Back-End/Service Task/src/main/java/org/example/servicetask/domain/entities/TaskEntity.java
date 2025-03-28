package org.example.servicetask.domain.entities;


import jakarta.persistence.*;
import lombok.*;
import org.example.servicetask.domain.enums.TaskStatus;

@Entity(name = "tasks")
@Getter @Setter @AllArgsConstructor
@NoArgsConstructor @ToString @Builder
public class TaskEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    // steps to take
    private String Description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private Integer userId;

    public TaskEntity(Integer id, String title, String description, TaskStatus status, Integer userId) {
        this.id = id;
        this.title = title;
        Description = description;
        this.status = status;
        this.userId = userId;
    }

    public TaskEntity() {
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return Description;
    }
}
