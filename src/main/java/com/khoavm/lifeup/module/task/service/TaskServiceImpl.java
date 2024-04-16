package com.khoavm.lifeup.module.task.service;


import com.khoavm.lifeup.config.security.Context;
import com.khoavm.lifeup.config.security.ContextImpl;
import com.khoavm.lifeup.exception.NotFoundException;
import com.khoavm.lifeup.module.common.dto.Page;
import com.khoavm.lifeup.module.task.constant.TaskStatus;
import com.khoavm.lifeup.module.task.dto.CreateTaskDto;
import com.khoavm.lifeup.module.task.dto.TaskDto;
import com.khoavm.lifeup.module.task.entity.Task;
import com.khoavm.lifeup.module.task.entity.Task_;
import com.khoavm.lifeup.module.task.mapper.TaskMapper;
import com.khoavm.lifeup.module.task.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{


    private final TaskRepository taskRepository;
    private Context context;
    @Override
    public TaskDto createTask(CreateTaskDto createTaskDto) {
        var newTask = TaskMapper.TaskFromCreateDto(createTaskDto);
        newTask.setStatus(TaskStatus.CREATED);
        newTask.setUserId(context.getUserId());
        newTask = taskRepository.save(newTask);
        return TaskMapper.TaskToDto(newTask);
    }

    @Override
    public TaskDto getTaskDetail(String id) {
        var task = taskRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Task not found"));
        return TaskMapper.TaskToDto(task);
    }

    @Override
    public Page<TaskDto> searchListTask() {
        return null;
    }


    private Specification<Task> byName(String name) {

        return (root, query, builder) ->
                builder.like( builder.lower(root.get(Task_.name)), "%" + name.toLowerCase() + "%");
    }

    private Specification<Task> byDescription(String description) {
        return (root, query, builder) ->
                builder.like( builder.lower(root.get(Task_.description)), "%" + description.toLowerCase() + "%");
    }
}
