package org.example.servicetask.mapper.impl;

import org.example.servicetask.domain.dto.TaskDto;
import org.example.servicetask.domain.entities.TaskEntity;
import org.example.servicetask.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class TaskMapperImpl implements Mapper<TaskEntity, TaskDto> {

    public TaskMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private final ModelMapper modelMapper;

    @Override
    public TaskDto mapTo(TaskEntity taskEntity) {
        return modelMapper.map(taskEntity, TaskDto.class);
    }

    @Override
    public TaskEntity mapFrom(TaskDto taskDto) {
        return modelMapper.map(taskDto, TaskEntity.class);
    }

}
