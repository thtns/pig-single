package com.thtns.cloud.response;

import lombok.Data;

/**
 * @author liuyj
 * node
 */
@Data
public class DYVideoUploadResponse {

    private Video video;

    @Data
    public static class Video {
        private String video_id;
        private String width;
        private String height;
    }
}
