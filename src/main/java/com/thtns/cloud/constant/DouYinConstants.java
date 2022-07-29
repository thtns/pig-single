package com.thtns.cloud.constant;

public interface DouYinConstants {


    String base_url = "https://open.douyin.com/";

    /**
     * 根据code 获取access_token
     */
    String access_token = base_url + "oauth/access_token";

    /**
     * 获取access_token 获取用户公开信息
     */
    String user_info = base_url + "oauth/userinfo";


}
