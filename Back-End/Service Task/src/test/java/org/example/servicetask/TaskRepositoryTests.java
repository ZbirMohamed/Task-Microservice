package org.example.servicetask;
/* ******************************
 Author: Zbir Mohamed Amine
 Purpose: Learning Unit and Integration Test and CI/CD pipelines
 Date: 2025-03-16
 App: Task Service ( MicroService: Full App )
 ********************************
 * */

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
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaskRepositoryTests {

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testRepository_Saves_Entity(){

        TaskEntity task = new TaskEntity(null, "Test Task","Testing", TaskStatus.IN_PROGRESS,1);

        TaskEntity taskSaved = taskRepository.save(task);

        Assertions.assertThat(taskSaved).isNotNull();
        Assertions.assertThat(taskSaved.getId()).isGreaterThan(0);

    }

    @Test
    public void testRepository_FindAllTasks_Return2Tasks(){

        TaskEntity task = new TaskEntity(null, "Test1","Testing", TaskStatus.IN_PROGRESS,1);
        TaskEntity task2 = new TaskEntity(null, "Test2","Testing", TaskStatus.COMPLETED,2);

        taskRepository.save(task);
        taskRepository.save(task2);


        List<TaskEntity> taskEntities = taskRepository.findAll();

        Assertions.assertThat(taskEntities).isNotNull();
        Assertions.assertThat(taskEntities.size()).isGreaterThan(0);
        Assertions.assertThat(taskEntities.getFirst().getTitle()).isEqualTo(task.getTitle());

    }

    @Test
    public void testRepository_FindOne_ReturnOneTask(){

        TaskEntity task = new TaskEntity(null, "Test1","Testing", TaskStatus.IN_PROGRESS,1);
        TaskEntity task2 = new TaskEntity(null, "Test2","Testing", TaskStatus.COMPLETED,2);
        TaskEntity task3 = new TaskEntity(null, "Test3","Testing", TaskStatus.BLOCKED,2);

        taskRepository.save(task);
        taskRepository.save(task2);
        taskRepository.save(task3);

        TaskEntity taskEntity = taskRepository.findById(2).get();

        Assertions.assertThat(taskEntity).isNotNull();
        Assertions.assertThat(taskEntity.getId()).isEqualTo(2);
        Assertions.assertThat(taskEntity.getTitle()).isEqualTo(task2.getTitle());

    }

    @Test
    public void taskTest_DeleteOne_ReturnEmpty(){
        TaskEntity task = new TaskEntity(null, "Test1","Testing", TaskStatus.IN_PROGRESS,1);
        TaskEntity task2 = new TaskEntity(null, "Test2","Testing", TaskStatus.COMPLETED,2);
        TaskEntity task3 = new TaskEntity(null, "Test3","Testing", TaskStatus.BLOCKED,2);

        taskRepository.save(task);
        taskRepository.save(task2);
        taskRepository.save(task3);

        taskRepository.deleteById(2);

        Optional<TaskEntity> taskEntity = taskRepository.findById(task2.getId());

        Assertions.assertThat(taskEntity).isEmpty();

    }

    @Test
    public void TaskRepository_UpdateTitle_ReturnTaskUpdate(){
        TaskEntity task = new TaskEntity(null, "Test1","Testing", TaskStatus.IN_PROGRESS,1);
        TaskEntity task2 = new TaskEntity(1, "Test2","Testing", TaskStatus.COMPLETED,1);

        // adding
        taskRepository.save(task);

        // updating
        taskRepository.save(task2);

        TaskEntity taskEntity = taskRepository.findById(1).get();
        List<TaskEntity> taskEntities = taskRepository.findAll();

        Assertions.assertThat(taskEntity.getTitle()).isNotNull();
        Assertions.assertThat(taskEntity.getDescription()).isNotNull();
        Assertions.assertThat(taskEntity.getTitle()).isEqualTo(task2.getTitle());
        Assertions.assertThat(taskEntity.getDescription()).isEqualTo(task2.getDescription());
        Assertions.assertThat(taskEntities.size()).isEqualTo(1);
    }



}
