package com.khoavm.lifeup.module.task.service;


import com.khoavm.lifeup.config.security.Context;
import com.khoavm.lifeup.exception.NotFoundException;
import com.khoavm.lifeup.module.common.dto.PageDto;
import com.khoavm.lifeup.module.common.dto.Query;
import com.khoavm.lifeup.module.common.mapper.Mapper;
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

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{


    private final TaskRepository taskRepository;
    private Context ctx;
    private Mapper commonMapper;
    @Override
    public TaskDto createTask(CreateTaskDto createTaskDto) {
        var newTask = TaskMapper.TaskFromCreateDto(createTaskDto);
        newTask.setStatus(TaskStatus.CREATED);
        newTask.setUserId(ctx.getUserId());
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
    public PageDto<TaskDto> searchListTask() {
        var spec = buildTaskQuerySpecification(ctx.getQuery());
        var page = ctx.getPageable();
        var result = taskRepository.findAll(spec, page)
                .map(TaskMapper::TaskToDto);

        return commonMapper.pageToDto(result);
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
