package com.khoavm.lifeup.module.task.mapper;

import com.khoavm.lifeup.module.task.dto.CreateTaskDto;
import com.khoavm.lifeup.module.task.dto.TaskDto;
import com.khoavm.lifeup.module.task.entity.Task;

public class TaskMapper {
    public static TaskDto TaskToDto(Task task){
        var taskDto = new TaskDto();
        taskDto.id(task.getId());
        taskDto.name(task.getName());
        taskDto.description(task.getDescription());
        taskDto.createdAt(task.getCreatedAt());
        taskDto.updatedAt(task.getUpdatedAt());
        taskDto.coinReward(task.getCoinReward());
        taskDto.deadline(task.getDeadline());
        taskDto.difficulty(task.getDifficulty());
        taskDto.userId(task.getUserId());
        taskDto.importance(task.getImportance());
        taskDto.isRepeat(task.getIsRepeat());
        taskDto.repeatTimes(task.getRepeatTimes());
        taskDto.status(task.getStatus());
        taskDto.startTime(task.getStartTime());
        return taskDto;
    }

    public static Task TaskFromDto(TaskDto taskDto){
        var task = new Task();
        task.setId(taskDto.id());
        task.setName(taskDto.name());
        task.setDescription(taskDto.description());
        task.setCreatedAt(taskDto.createdAt());
        task.setUpdatedAt(taskDto.updatedAt());
        task.setCoinReward(taskDto.coinReward());
        task.setDeadline(taskDto.deadline());
        task.setDifficulty(taskDto.difficulty());
        task.setImportance(taskDto.importance());
        task.setIsRepeat(taskDto.isRepeat());
        task.setRepeatTimes(taskDto.repeatTimes());
        task.setStatus(taskDto.status());
        task.setStartTime(taskDto.startTime());
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
