/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.thtns.cloud.init;


import com.thtns.cloud.config.TimeTask;
import com.thtns.cloud.entity.DyTask;
import com.thtns.cloud.service.DyTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * 初始化指定时间定时任务数据
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component
@Slf4j
public class JobCommandLineRunner implements CommandLineRunner {

    private DyTaskService dyTaskService;

    private TimeTask task;


    @Override
    public void run(String... args) {


        List<DyTask> dyTasks = dyTaskService.listAllByType(20);

        dyTasks.forEach(dyTask -> {
            //系统日期之后才执行
            if (dyTask.getFixedTime().isAfter(LocalDateTime.now())) {

                task.timingTask(task, Date.from(dyTask.getFixedTime().atZone(ZoneId.systemDefault()).toInstant()), dyTask.getId());
            }


        });


    }

    @Autowired
    public void setDyTaskService(DyTaskService dyTaskService) {
        this.dyTaskService = dyTaskService;
    }

    @Autowired
    public void setTask(TimeTask task) {
        this.task = task;
    }
}
