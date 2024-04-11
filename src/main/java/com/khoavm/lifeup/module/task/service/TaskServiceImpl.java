package com.khoavm.lifeup.module.task.service;


import com.khoavm.lifeup.config.security.Context;
import com.khoavm.lifeup.module.task.constant.TaskStatus;
import com.khoavm.lifeup.module.task.dto.CreateTaskDto;
import com.khoavm.lifeup.module.task.dto.TaskDto;
import com.khoavm.lifeup.module.task.mapper.TaskMapper;
import com.khoavm.lifeup.module.task.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{


    private final TaskRepository taskRepository;
    @Override
    public TaskDto createTask(CreateTaskDto createTaskDto) {
        var newTask = TaskMapper.TaskFromCreateDto(createTaskDto);
        newTask.setStatus(TaskStatus.CREATED);
        newTask.setUserId(Context.getUserId());
        newTask = taskRepository.save(newTask);
        return TaskMapper.TaskToDto(newTask);
    }
}
