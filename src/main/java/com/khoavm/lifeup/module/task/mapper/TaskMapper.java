package com.khoavm.lifeup.module.task.mapper;

import com.khoavm.lifeup.module.task.dto.CreateTaskDto;
import com.khoavm.lifeup.module.task.dto.TaskDto;
import com.khoavm.lifeup.module.task.entity.Task;

public class TaskMapper {
    public static TaskDto TaskToDto(Task task){
        var taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setCreatedAt(task.getCreatedAt());
        taskDto.setUpdatedAt(task.getUpdatedAt());
        taskDto.setCoinReward(task.getCoinReward());
        taskDto.setDeadline(task.getDeadline());
        taskDto.setDifficulty(task.getDifficulty());
        taskDto.setUserId(task.getUserId());
        taskDto.setImportance(task.getImportance());
        taskDto.setIsRepeat(task.getIsRepeat());
        taskDto.setRepeatTimes(task.getRepeatTimes());
        taskDto.setStatus(task.getStatus());
        taskDto.setStartTime(task.getStartTime());
        return taskDto;
    }

    public static Task TaskFromDto(TaskDto taskDto){
        var task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setCreatedAt(taskDto.getCreatedAt());
        task.setUpdatedAt(taskDto.getUpdatedAt());
        task.setCoinReward(taskDto.getCoinReward());
        task.setDeadline(taskDto.getDeadline());
        task.setDifficulty(taskDto.getDifficulty());
        task.setImportance(taskDto.getImportance());
        task.setIsRepeat(taskDto.getIsRepeat());
        task.setRepeatTimes(taskDto.getRepeatTimes());
        task.setStatus(taskDto.getStatus());
        task.setStartTime(taskDto.getStartTime());
        return task;
    }

    public static Task TaskFromCreateDto(CreateTaskDto taskDto){
        var task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setCoinReward(taskDto.getCoinReward());
        task.setDeadline(taskDto.getDeadline());
        task.setDifficulty(taskDto.getDifficulty());
        task.setImportance(taskDto.getImportance());
        task.setIsRepeat(taskDto.getIsRepeat());
        task.setRepeatTimes(taskDto.getRepeatTimes());
        task.setStartTime(taskDto.getStartTime());
        return task;
    }
}
