package com.thtns.cloud.service;

import com.thtns.cloud.response.*;

public interface DouYinService {


    DYGetAccessTokenResponse getAccessToken(String code);

    DYGetUserInfoResponse getUserInfo(String accessToken, String openId);


    DYVideoUploadResponse videoUpload();

    DYVideoCreateResponse videoCreate();

    DYVideoPartCompleteUploadResponse videoPartCompleteUpload();

    DYVideoPartInitUploadResponse videoPartInitUpload();

    DYImageUploadResponse imageUploadResponse();

}
