package com.khoavm.lifeup.module.task.service;

import com.khoavm.lifeup.module.common.dto.PageDto;
import com.khoavm.lifeup.module.task.dto.CreateTaskDto;
import com.khoavm.lifeup.module.task.dto.TaskDto;


public interface TaskService{
    TaskDto createTask(CreateTaskDto createTaskDto);
    TaskDto getTaskDetail(String id);
    PageDto<TaskDto> searchListTask();
}
