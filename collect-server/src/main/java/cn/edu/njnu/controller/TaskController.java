package cn.edu.njnu.controller;

import cn.edu.njnu.pojo.Result;
import cn.edu.njnu.pojo.ResultFactory;
import cn.edu.njnu.pojo.Task;
import cn.edu.njnu.service.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/addTask")
    public Result addTask(@RequestBody Task task) {
        System.out.println(task);
        // return ResultFactory.buildSuccessResult("创建成功", null);
        task = taskService.addTask(task);
        if (task.getCode().equals("")) {
            return ResultFactory.buildFailResult("创建失败");
        }
        return ResultFactory.buildSuccessResult("创建成功", task);
    }
}
