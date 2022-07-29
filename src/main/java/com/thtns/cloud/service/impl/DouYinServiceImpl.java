package com.thtns.cloud.service.impl;

import cn.hutool.core.util.StrUtil;
import com.thtns.cloud.constant.DouYinConstants;
import com.thtns.cloud.response.*;
import com.thtns.cloud.service.DouYinService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @author liuyj
 * node
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DouYinServiceImpl implements DouYinService {


    private final RestTemplate restTemplate;


    @Override
    @SneakyThrows
    public DYGetAccessTokenResponse getAccessToken(String code) {

        String format = StrUtil.format("{}?client_key={}&client_secret={}&code={}&grant_type=authorization_code", DouYinConstants.access_token, "awild8le9qfu6vai", "d53efc0d56d08ec83a561fa2ca483345", code);

        ParameterizedTypeReference<DouYinBaseResponse<DYGetAccessTokenResponse>> typeReference = new ParameterizedTypeReference<DouYinBaseResponse<DYGetAccessTokenResponse>>() {
        };


        ResponseEntity<DouYinBaseResponse<DYGetAccessTokenResponse>> response = restTemplate.exchange(format, HttpMethod.POST, null, typeReference);


        return response.getBody().getData();


    }

    @Override
    public DYGetUserInfoResponse getUserInfo(String accessToken, String openId) {

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("access_token", accessToken);
        hashMap.put("open_id", openId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> httpEntity = new HttpEntity<>(hashMap, httpHeaders);


        ParameterizedTypeReference<DouYinBaseResponse<DYGetUserInfoResponse>> userInfoTypeReference = new ParameterizedTypeReference<DouYinBaseResponse<DYGetUserInfoResponse>>() {
        };


        ResponseEntity<DouYinBaseResponse<DYGetUserInfoResponse>> userInfoResponse = restTemplate.exchange(DouYinConstants.user_info, HttpMethod.POST, httpEntity, userInfoTypeReference);


        return userInfoResponse.getBody().getData();


    }

    @Override
    public DYVideoUploadResponse videoUpload() {
        return null;
    }

    @Override
    public DYVideoCreateResponse videoCreate() {
        return null;
    }

    @Override
    public DYVideoPartCompleteUploadResponse videoPartCompleteUpload() {
        return null;
    }

    @Override
    public DYVideoPartInitUploadResponse videoPartInitUpload() {
        return null;
    }

    @Override
    public DYImageUploadResponse imageUploadResponse() {
        return null;
    }
}
