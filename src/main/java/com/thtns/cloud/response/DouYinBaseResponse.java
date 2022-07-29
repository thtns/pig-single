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

}
