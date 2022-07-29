package com.thtns.cloud.response;

import lombok.Data;

/**
 * @author liuyj
 * node
 */
@Data
public class DYImageUploadResponse {

    private Image image;

    @Data
    public static class Image {
        private String image_id;
        private String width;
        private String height;
    }
}
