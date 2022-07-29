package com.thtns.cloud.response;

import lombok.Data;

/**
 * @author liuyj
 * node
 */
@Data
public class DYGetUserInfoResponse {

    /**
     *
     */
    private String avatar;
    /**
     *
     */
    private String city;
    /**
     *
     */
    private String country;
    /**
     * 错误码描述
     */
    private Integer description;
    /**
     * 类型: * `EAccountM` - 普通企业号 * `EAccountS` - 认证企业号 * `EAccountK` - 品牌企业号
     */
    private String e_account_role;
    /**
     * 性别: * `0` - 未知 * `1` - 男性 * `2` - 女性
     */
    private Integer gender;
    /**
     * 错误码
     */
    private String error_code;
    /**
     *
     */
    private String nickname;

    /**
     * 用户在当前应用的唯一标识
     */
    private String open_id;
    /**
     *
     */
    private String province;
    /**
     * 用户在当前开发者账号下的唯一标识（未绑定开发者账号没有该字段）
     */
    private String union_id;


}
