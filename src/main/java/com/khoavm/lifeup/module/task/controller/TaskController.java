package com.khoavm.lifeup.module.task.controller;


import com.khoavm.lifeup.module.common.dto.PageDto;
import com.khoavm.lifeup.module.common.dto.ResponseDto;
import com.khoavm.lifeup.module.task.dto.CreateTaskDto;
import com.khoavm.lifeup.module.task.dto.TaskDto;
import com.khoavm.lifeup.module.task.service.TaskService;
import com.khoavm.lifeup.util.ResponseUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {

    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;


    @PostMapping
    public ResponseEntity<ResponseDto<TaskDto>> createTask(@RequestBody CreateTaskDto createTaskDto){
        var task = taskService.createTask(createTaskDto);
        return ResponseUtil.Created(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<TaskDto>> getTaskDetail(@PathVariable String id){
        var task = taskService.getTaskDetail(id);
        return ResponseUtil.Ok(task);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<PageDto<TaskDto>>> getTaskList(){
        var taskList = taskService.searchListTask();
        return ResponseUtil.Ok(taskList);
    }

}
