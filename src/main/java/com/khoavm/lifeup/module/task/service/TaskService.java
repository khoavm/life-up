package com.khoavm.lifeup.module.task.service;

import com.khoavm.lifeup.module.task.dto.CreateTaskDto;
import com.khoavm.lifeup.module.task.dto.TaskDto;
import org.springframework.stereotype.Service;


public interface TaskService{
    TaskDto createTask(CreateTaskDto createTaskDto);
}
