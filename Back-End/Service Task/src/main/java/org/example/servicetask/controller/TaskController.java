package org.example.servicetask.controller;


import com.sun.tools.jconsole.JConsoleContext;
import org.example.servicetask.domain.dto.TaskDto;
import org.example.servicetask.domain.entities.TaskEntity;
import org.example.servicetask.mapper.Mapper;
import org.example.servicetask.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    public TaskController(TaskService taskService, Mapper<TaskEntity, TaskDto> taskDtoMapper) {
        this.taskService = taskService;
        this.taskDtoMapper = taskDtoMapper;
    }
    private final TaskService taskService;

    private final Mapper<TaskEntity, TaskDto> taskDtoMapper;


    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        try{

            List<TaskEntity> taskEntities = taskService.getAllTasks();
            List<TaskDto> taskDtos = taskEntities.stream().map(taskDtoMapper::mapTo).toList();

            return new ResponseEntity<>(taskDtos,HttpStatus.OK);

        }catch (Exception e){
            System.out.println("Erreur Niveau Serveur " + e);
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("id") Integer id){
        try{
            TaskEntity taskEntity = taskService.findByid(id).orElse(null);

            if (taskEntity == null) return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(taskDtoMapper.mapTo(taskEntity),HttpStatus.OK);

        }catch (Exception e){
            System.out.println("Server Error" + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto){
        try{
            TaskEntity taskEntity = taskService.save(taskDtoMapper.mapFrom(taskDto));

            return new ResponseEntity<>(taskDtoMapper.mapTo(taskEntity),HttpStatus.OK);

        }catch (Exception e){
            System.out.println("Server Error"+e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public  ResponseEntity<TaskDto> updateTask(
            @PathVariable("id") Integer id,
            @RequestBody TaskDto taskDto){
        try {

            if(!taskService.isExisting(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            TaskEntity taskEntity = taskService.save(taskDtoMapper.mapFrom(taskDto));

            return new ResponseEntity<>(taskDtoMapper.mapTo(taskEntity),HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }








}
