package com.khoavm.lifeup.module.task.service;

import com.khoavm.lifeup.module.common.dto.PageDto;
import com.khoavm.lifeup.module.task.dto.CreateTaskDto;
import com.khoavm.lifeup.module.task.dto.TaskDto;
import com.khoavm.lifeup.module.task.dto.UpdateTaskDto;

import java.util.UUID;


public interface TaskService{
    TaskDto createTask(CreateTaskDto createTaskDto);
    TaskDto getTaskDetail(UUID id);
    PageDto<TaskDto> searchListTask();
    void deleteTask(UUID taskId);
    TaskDto updateTask (UUID taskId, UpdateTaskDto updateTaskDto);
}
