package org.example.servicetask.service.impl;


import lombok.AllArgsConstructor;
import org.example.servicetask.domain.entities.TaskEntity;
import org.example.servicetask.repository.TaskRepository;
import org.example.servicetask.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    // injection par constructor
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskEntity save(TaskEntity task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<TaskEntity> findByid(Integer id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    @Override
    public boolean isExisting(Integer id) {
        return taskRepository.existsById(id);
    }
}
