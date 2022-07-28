package com.thtns.cloud.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.io.IoUtil;
import com.thtns.cloud.constant.CacheConstants;
import com.thtns.cloud.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author liuyj
 * node 验证码
 */
@RestController
@RequestMapping("code")
@RequiredArgsConstructor
@Slf4j
public class CodeController {

    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping
    @SneakyThrows
    public void captcha(HttpServletResponse response, String randomStr) {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100, 4, 150);
        lineCaptcha.write(response.getOutputStream());
        IoUtil.close(response.getOutputStream());


        redisTemplate.opsForValue().set(CacheConstants.DEFAULT_CODE_KEY + randomStr, lineCaptcha.getCode(),
                SecurityConstants.CODE_TIME, TimeUnit.SECONDS);
    }


}
