
package com.thtns.cloud.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客户表
 *
 * @author thtns
 * @date 2022-05-18 23:47:52
 */
@Data
@TableName("custom")
@Schema(description = "客户表")
public class Custom {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String phone;

    /**
     * 币的数量
     */
    @Schema(description = "币的数量")
    private Long coin;

    /**
     * 是否是vip
     */
    @Schema(description = "是否是vip")
    private Boolean vip;

    /**
     * vip到期时间
     */
    @Schema(description = "vip到期时间")
    private LocalDateTime invalidTime;


    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
