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

    /**
     * 视频上传
     */
    String video_upload = base_url + "video/upload";
    /**
     * 视频创建
     */
    String video_create = base_url + "video/create";
    /**
     * 视频初始化
     */
    String video_part_init = base_url + "video/part/init";
    /**
     * 视频上传
     */
    String video_part_upload = base_url + "video/part/upload";
    /**
     * 视频上传完成
     */
    String video_part_complete = base_url + "video/part/complete";


    /**
     * 视频列表
     */
    String video_list = base_url + "video/list";
    /**
     * 上传图片
     */
    String image_upload = base_url + "image/upload";

    /**
     * 发布图片
     */
//    String image_text_create = base_url + "image_text/create";


}
