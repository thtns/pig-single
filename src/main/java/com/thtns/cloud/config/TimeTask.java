package com.thtns.cloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author liuyj
 * node 指定时间的定时任务
 */
@Component
@Slf4j
public class TimeTask extends TimerTask {

    public Long id;

    @Override
    public void run() {
        log.info("指定任务开始执行了，任务ID：{}", id);
    }


    public void timingTask(TimerTask task, Date date, Long id) {
        Timer timer = new Timer();
        timer.schedule(task, date);
        this.id = id;
    }


}
