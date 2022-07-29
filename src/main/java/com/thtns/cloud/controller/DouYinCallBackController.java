package com.thtns.cloud.controller;

import com.thtns.cloud.service.DouYinService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuyj
 * node 抖音回调
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("douYin")
public class DouYinCallBackController {

    private final DouYinService douYinService;

    private final ThreadPoolTaskExecutor executor;


    @GetMapping("callback")
    public void callback(String code) {


        executor.execute(() -> douYinService.getAccessToken(code));


    }


}
