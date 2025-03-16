package org.example.servicetask;

/* ******************************
 Author: Zbir Mohamed Amine
 Purpose: Learning Unit and Integration Test and CI/CD pipelines
 Date: 2025-03-15
 App: Task Service ( MicroService: Full App )
 ********************************
 * */
import org.example.servicetask.domain.entities.TaskEntity;
import org.example.servicetask.domain.enums.TaskStatus;
import org.example.servicetask.repository.TaskRepository;
import org.example.servicetask.service.TaskService;
import org.example.servicetask.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


// This test doesn't involve persistence layer we mock the data layer using @Mock, and we inject the dependency using @InjectMock
// as seen below the taskServiceImpl needs a taskRepo instance, so we create a mock and then inject it using @InjectMock
@ExtendWith(MockitoExtension.class) // this annotation is used to tell Junit that we'll be using mockito for the initialization of mocks and other Mockito-related features.
public class TaskServiceTesting {

    @Mock // we create a mock instance of TaskRepository, so it can be used in the taskService
    private TaskRepository taskRepository;

    @InjectMocks // in here we inject the mock created (taskRepository)
    private TaskServiceImpl taskService;


    @Test
    public void testSaveTask() {

        TaskEntity task = new TaskEntity(1, "Test Task","Testing", TaskStatus.IN_PROGRESS,1);

        // Simule le comportement du mock (TaskRepository.save() renverra 'task')
        Mockito.when(taskRepository.save(task)).thenReturn(task);

        // Act : Appel de la méthode saveTask du service
        TaskEntity result = taskService.save(task);

        // Assert : Vérifier que le résultat est correct
        assertNotNull(result);
        assertEquals("Test Task", result.getTitle());
    }

    @Test
    public void testFindAll(){

        TaskEntity task1 = new TaskEntity(1, "Test1","Testing", TaskStatus.IN_PROGRESS,1);
        TaskEntity task2 = new TaskEntity(2, "Test2","Testing", TaskStatus.TODO,1);
        TaskEntity task3 = new TaskEntity(3, "Test3","Testing", TaskStatus.COMPLETED,1);

        List<TaskEntity> tasks = List.of(task1,task2,task3);

        Mockito.when(taskRepository.findAll()).thenReturn(tasks);

        List<TaskEntity> taskEntities = taskService.getAllTasks();

        // junit
        assertNotNull(taskEntities);
        assertEquals("Test1",taskEntities.getFirst().getTitle());
    }

    @Test
    public void testFindById(){
        TaskEntity task1 = new TaskEntity(1, "Test1","Testing", TaskStatus.IN_PROGRESS,1);
        TaskEntity task2 = new TaskEntity(2, "Test2","Testing", TaskStatus.TODO,1);
        TaskEntity task3 = new TaskEntity(3, "Test3","Testing", TaskStatus.COMPLETED,1);

        List<TaskEntity> tasks = List.of(task1,task2,task3);

        Integer id = 1;

        Mockito.when(taskRepository.findById(id)).thenReturn(tasks.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst());

        TaskEntity taskEntity = taskService.findByid(1).orElse(new TaskEntity());

        assertNotNull(taskEntity);
        assertEquals("Test1",taskEntity.getTitle());
    }
    // delete
    @Test
    public void testDeleteById(){

        Mockito.doNothing().when(taskRepository).deleteById(1);
        // Appeler la méthode de suppression
        taskService.deleteTask(1);
        // Vérifier que la méthode a été bien appelée
        Mockito.verify(taskRepository, Mockito.times(1)).deleteById(1);

    }

}
