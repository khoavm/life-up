package com.khoavm.lifeup.module.task.controller;


import com.khoavm.lifeup.module.common.dto.ResponseDto;
import com.khoavm.lifeup.module.task.dto.CreateTaskDto;
import com.khoavm.lifeup.module.task.dto.TaskDto;
import com.khoavm.lifeup.module.task.service.TaskService;
import com.khoavm.lifeup.module.user.dto.UserDto;
import com.khoavm.lifeup.module.user.service.UserService;
import com.khoavm.lifeup.util.ResponseUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {

    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;


    @PostMapping
    public ResponseEntity<ResponseDto> createTask(@RequestBody CreateTaskDto createTaskDto){
        var task = taskService.createTask(createTaskDto);
        return ResponseUtil.DefaultCreateSuccessResponse(task);
    }

}
