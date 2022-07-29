/*
 * Copyright (c) 2020 comic4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thtns.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.thtns.cloud.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class DyCustom extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description = "主键id")
    private Long id;

    /**
     * access_token
     */
    @Schema(title = "access_token")
    private String accessToken;

    /**
     * 检查更新时间
     */
    @Schema(description = "检查更新时间")
    private LocalDateTime checkUptime;

    /**
     * 头像图片
     */
    @Schema(description = "头像图片")
    private String avatar;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * open_id
     */
    @Schema(description = "open_id")
    private String openId;

    /**
     * 自动续期结束时间
     */
    @Schema(description = "自动续期结束时间")
    private LocalDateTime refreshOutTime;

    /**
     * 部门ID
     */
    @Schema(description = "refresh_token")
    private String refreshToken;

    @Schema(description = "状态")
    private Integer status;
    @Schema(description = "授权到期时间")
    private LocalDateTime tokenOutTime;
    @Schema(description = "锁定标记")
    private String city;
    private String country;
    private String province;
    @Schema(description = "性别: * `0` - 未知 * `1` - 男性 * `2` - 女性")
    private Integer gender;
    @Schema(description = "用户在当前开发者账号下的唯一标识（未绑定开发者账号没有该字段）")
    private String unionId;


}
