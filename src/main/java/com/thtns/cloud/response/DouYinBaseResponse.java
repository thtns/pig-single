package com.thtns.cloud.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author liuyj
 * node
 */
@RequiredArgsConstructor
@Data
public class DouYinBaseResponse<T> {

    private T data;

    private String message;

    private Extra extra;

    @Data
    public static class Extra {
        /**
         * 标识请求的唯一id
         */
        private String logid;
        /**
         * 毫秒级时间戳
         */
        private String now;
        /**
         * 错误码
         */
        private String error_code;
        /**
         * 错误码描述
         */
        private String description;
        /**
         * 子错误码
         */
        private String sub_error_code;
        /**
         * 子错误码描述
         */
        private String sub_description;


    }

}
