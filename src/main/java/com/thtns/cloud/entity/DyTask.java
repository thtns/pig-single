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
package com.thtns.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.thtns.cloud.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 抖音_任务
 *
 * @author 刘玉军
 * @date 2022-07-30 00:40:08
 */
@Data
@TableName("dy_task")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "抖音_任务")
public class DyTask extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    /**
     * 任务名称
     */
    @Schema(description = "任务名称")
    private String name;

    /**
     * 任务类型，10=立即发送，20=定时发送，30=周期发送
     */
    @Schema(description = "任务类型，10=立即发送，20=定时发送，30=周期发送")
    private Integer type;

    /**
     * 周期发送cron表达式
     */
    @Schema(description = "周期发送cron表达式")
    private String cron;

    /**
     * 定时发送时间
     */
    @Schema(description = "定时发送时间")
    private LocalDateTime fixedTime;

    /**
     * 视频封面地址
     */
    @Schema(description = "视频封面地址")
    private String coverImg;


}
