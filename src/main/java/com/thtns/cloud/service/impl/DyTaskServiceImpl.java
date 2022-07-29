/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.thtns.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thtns.cloud.entity.DyTask;
import com.thtns.cloud.mapper.DyTaskMapper;
import com.thtns.cloud.service.DyTaskService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 抖音_任务
 *
 * @author 刘玉军
 * @date 2022-07-30 00:40:08
 */
@Service
public class DyTaskServiceImpl extends ServiceImpl<DyTaskMapper, DyTask> implements DyTaskService {

    @Override
    public List<DyTask> listAllByType(Integer type) {

        LambdaQueryWrapper<DyTask> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(DyTask::getType, type);
        return list(wrapper);
    }

}
