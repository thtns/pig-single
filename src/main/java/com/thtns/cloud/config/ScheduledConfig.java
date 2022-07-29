package com.thtns.cloud.config;

import com.thtns.cloud.entity.DyTask;
import com.thtns.cloud.service.DyTaskService;
import com.thtns.cloud.utils.ScheduledUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Configuration
@Slf4j
public class ScheduledConfig implements SchedulingConfigurer {
    private final Map<String, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<>();
    private DyTaskService dyTaskService;
    private ScheduledTaskRegistrar taskRegistrar;
    private Set<ScheduledFuture<?>> scheduledFutures = null;

    /**
     * 这个方法在Spring初始化的时候会帮我们执行，这里也会拉取数据库内需要执行的任务，进行添加到定时器里。
     *
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        List<TriggerTask> list = new ArrayList<>();
        //查询出来当前数据库中存储的所有有效的任务
        List<DyTask> dyTasks = dyTaskService.listAllByType(30);
        //循环添加任务
        dyTasks.forEach(t -> {
            TriggerTask triggerTask = new TriggerTask(() -> log.info("执行定时任务:{}", LocalDateTime.now()), triggerContext -> {
                log.info("执行Cron:{},任务ID={}", t.getCron(), t.getId());
                //如果需要动态的指定当前定时任务的执行corn。这里可以增加一步，查询数据库操作。如果任务corn不需要精确修改，corn可进行缓存。到期在去查询数据库。这里根据读者的需求自行取舍。
                return new CronTrigger(t.getCron()).nextExecutionTime(triggerContext);
            });
            list.add(triggerTask);
        });
        //将任务列表注册到定时器
        scheduledTaskRegistrar.setTriggerTasksList(list);
        this.taskRegistrar = scheduledTaskRegistrar;

    }


    /**
     * 添加任务
     *
     * @param taskId
     * @param triggerTask
     */
    public void addTask(String taskId, TriggerTask triggerTask) {
        //如果定时任务id已存在，则取消原定时器，从新创建新定时器，这里也是个更新定时任务的过程。
        if (taskFutures.containsKey(taskId)) {
            System.out.println("the taskId[" + taskId + "]  取消，重新添加");
            cancelTriggerTask(taskId);
        }
        TaskScheduler scheduler = taskRegistrar.getScheduler();
        ScheduledFuture<?> future = scheduler.schedule(triggerTask.getRunnable(), triggerTask.getTrigger());
        getScheduledFutures().add(future);
        taskFutures.put(taskId, future);
    }

    /**
     * 获取任务列表
     */
    private Set<ScheduledFuture<?>> getScheduledFutures() {
        if (scheduledFutures == null) {
            try {
                scheduledFutures = (Set<ScheduledFuture<?>>) ScheduledUtils.getProperty(taskRegistrar, "scheduledTasks");
            } catch (NoSuchFieldException e) {
                throw new SchedulingException("not found scheduledFutures field.");
            }
        }
        return scheduledFutures;
    }

    /**
     * 取消任务
     */
    public void cancelTriggerTask(String taskId) {
        ScheduledFuture<?> future = taskFutures.get(taskId);
        if (future != null) {
            future.cancel(true);
        }
        taskFutures.remove(taskId);
        getScheduledFutures().remove(future);
    }

    @Autowired
    public void setDyTaskService(DyTaskService dyTaskService) {
        this.dyTaskService = dyTaskService;
    }
}
