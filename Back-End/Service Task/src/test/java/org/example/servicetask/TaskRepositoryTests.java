package org.example.servicetask;


import org.assertj.core.api.Assertions;
import org.example.servicetask.domain.entities.TaskEntity;
import org.example.servicetask.domain.enums.TaskStatus;
import org.example.servicetask.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaskRepositoryTests {

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testSaveTask(){

        TaskEntity task = new TaskEntity(null, "Test Task","Testing", TaskStatus.IN_PROGRESS,1);

        TaskEntity taskSaved = taskRepository.save(task);

        Assertions.assertThat(taskSaved).isNotNull();
        Assertions.assertThat(taskSaved.getId()).isGreaterThan(0);

    }

    @Test
    public void testFindAll(){

        TaskEntity task = new TaskEntity(null, "Test1","Testing", TaskStatus.IN_PROGRESS,1);
        TaskEntity task2 = new TaskEntity(null, "Test2","Testing", TaskStatus.COMPLETED,2);

        taskRepository.save(task);
        taskRepository.save(task2);


        List<TaskEntity> taskEntities = taskRepository.findAll();

        Assertions.assertThat(taskEntities).isNotNull();
        Assertions.assertThat(taskEntities.size()).isGreaterThan(0);
        Assertions.assertThat(taskEntities.getFirst().getTitle()).isEqualTo(task.getTitle());

    }


}
