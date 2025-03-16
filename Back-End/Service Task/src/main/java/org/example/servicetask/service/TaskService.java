package org.example.servicetask.service;


import org.example.servicetask.domain.entities.TaskEntity;

import java.util.List;
import java.util.Optional;

public interface TaskService {


    // create and update
    TaskEntity save(TaskEntity task);

    Optional<TaskEntity> findByid(Integer id);

    List<TaskEntity> getAllTasks();

    void deleteTask(Integer id);

    boolean isExisting(Integer id);


}
