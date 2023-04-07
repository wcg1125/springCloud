package com.hln.znbf.task.controller;


import com.hln.znbf.task.service.TimedTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("znbf/task")
public class TimedTaskController {


    /**
     * 农业情况统计定时任务
     */

    @Autowired
    private TimedTaskService timedTaskService;

    @GetMapping("dsrnhTask")
    @Scheduled(cron = "0 0 1 * * ? ")
    public void dsrnhTask(){
        System.out.println("=============开始执行定时任务==========");
         timedTaskService.dsrnhTask();
        System.out.println("=============定时任务执行结束==========");
    }

}
