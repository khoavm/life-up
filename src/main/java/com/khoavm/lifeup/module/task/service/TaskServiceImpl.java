package com.khoavm.lifeup.module.task.service;


import com.khoavm.lifeup.config.context.Context;
import com.khoavm.lifeup.exception.InvalidArgumentException;
import com.khoavm.lifeup.exception.NotFoundException;
import com.khoavm.lifeup.exception.PreConditionFailedException;
import com.khoavm.lifeup.module.common.dto.PageDto;
import com.khoavm.lifeup.module.common.dto.Query;
import com.khoavm.lifeup.module.common.mapper.Mapper;
import com.khoavm.lifeup.module.task.constant.TaskStatus;
import com.khoavm.lifeup.module.task.dto.CreateTaskDto;
import com.khoavm.lifeup.module.task.dto.TaskDto;
import com.khoavm.lifeup.module.task.dto.UpdateTaskDto;
import com.khoavm.lifeup.module.task.entity.Task;
import com.khoavm.lifeup.module.task.entity.Task_;
import com.khoavm.lifeup.module.task.mapper.TaskMapper;
import com.khoavm.lifeup.module.task.repository.TaskRepository;
import com.khoavm.lifeup.util.CommonUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{


    private final TaskRepository taskRepository;
    private Context ctx;
    private Mapper commonMapper;
    private CommonUtil commonUtil;
    @Override
    public TaskDto createTask(CreateTaskDto createTaskDto) {
        var newTask = TaskMapper.TaskFromCreateDto(createTaskDto);
        newTask.setStatus(TaskStatus.CREATED);
        newTask.setUserId(ctx.getUserId());
        newTask = taskRepository.save(newTask);
        return TaskMapper.TaskToDto(newTask);
    }

    @Override
    public TaskDto getTaskDetail(UUID id) {
        return TaskMapper.TaskToDto(findTaskById(id));
    }

    @Override
    public PageDto<TaskDto> searchListTask() {
        var spec = buildTaskQuerySpecification(ctx.getQuery());
        var page = ctx.getPageable();
        var result = taskRepository.findAll(spec, page)
                .map(TaskMapper::TaskToDto);

        return commonMapper.pageToDto(result);
    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public TaskDto updateTask(UUID taskId, UpdateTaskDto updateTaskDto) {
        Task existTask = this.findTaskById(taskId);
        if (existTask.getStatus().equals(TaskStatus.COMPLETED)){
            throw new PreConditionFailedException("can't update completed task");
        }
        if (commonUtil.isPresent(updateTaskDto.getName())){
           existTask.setName(updateTaskDto.getName());
        }
        if (commonUtil.isPresent(updateTaskDto.getDescription())){
            existTask.setDescription(updateTaskDto.getDescription());
        }
        if (commonUtil.isPresent(updateTaskDto.getDifficulty())){
            existTask.setDifficulty(updateTaskDto.getDifficulty());
        }
        if (commonUtil.isPresent(updateTaskDto.getCoinReward())){
            existTask.setCoinReward(updateTaskDto.getCoinReward());
        }
        if (commonUtil.isPresent(updateTaskDto.getImportance())){
            existTask.setImportance(updateTaskDto.getImportance());
        }
        if (commonUtil.isPresent(updateTaskDto.getDeadline())){
            if (updateTaskDto.getDeadline().isBefore(OffsetDateTime.now())){
                throw new PreConditionFailedException("Deadline is not valid");
            }
            existTask.setDeadline(updateTaskDto.getDeadline());
        }


        taskRepository.save(existTask);
        return getTaskDetail(taskId);
    }

    private Task findTaskById(UUID taskId){
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task not found"));
    }

    private Specification<Task> buildTaskQuerySpecification(List<Query> queryList) {
        Specification<Task> spec = null;
        Specification<Task> tempSpec;
        for (Query query : queryList) {
            switch (query.field()) {
                case "name":
                    tempSpec = byName(query.value());
                    break;
                case "description":
                    tempSpec = byDescription(query.value());
                    break;
                default:
                    continue;
            }
            spec = (spec == null) ? Specification.where(tempSpec) : spec.and(tempSpec);
        }
        return spec;
    }

    private Specification<Task> byName(String name) {

        return (root, query, builder) ->
                builder.like( builder.lower(root.get(Task_.name)), "%"+ name.toLowerCase() + "%");
    }

    private Specification<Task> byDescription(String description) {
        return (root, query, builder) ->
                builder.like( builder.lower(root.get(Task_.description)), "%" + description.toLowerCase() + "%");
    }

    private Specification<Task> byUserId(UUID id){
        return (root, query, builder) ->
                builder.equal(root.get(Task_.id), id);
    }
}
