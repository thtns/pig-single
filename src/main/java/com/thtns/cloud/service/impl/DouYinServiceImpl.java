package com.thtns.cloud.service.impl;

import cn.hutool.core.util.StrUtil;
import com.thtns.cloud.constant.DouYinConstants;
import com.thtns.cloud.entity.DyCustom;
import com.thtns.cloud.response.DYGetAccessTokenResponse;
import com.thtns.cloud.response.DYGetUserInfoResponse;
import com.thtns.cloud.response.DouYinBaseResponse;
import com.thtns.cloud.service.DouYinService;
import com.thtns.cloud.service.DyCustomService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
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

    private final DyCustomService dyCustomService;


    @Override
    @SneakyThrows
    public void getAccessToken(String code) {

        String format = StrUtil.format("{}?client_key={}&client_secret={}&code={}&grant_type=authorization_code", DouYinConstants.access_token, "awild8le9qfu6vai", "d53efc0d56d08ec83a561fa2ca483345", code);

        ParameterizedTypeReference<DouYinBaseResponse<DYGetAccessTokenResponse>> typeReference = new ParameterizedTypeReference<DouYinBaseResponse<DYGetAccessTokenResponse>>() {
        };


        ResponseEntity<DouYinBaseResponse<DYGetAccessTokenResponse>> response = restTemplate.exchange(format, HttpMethod.POST, null, typeReference);


        DYGetAccessTokenResponse data = response.getBody().getData();


        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("access_token", data.getAccess_token());
        hashMap.put("open_id", data.getOpen_id());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> httpEntity = new HttpEntity<>(hashMap, httpHeaders);


        ParameterizedTypeReference<DouYinBaseResponse<DYGetUserInfoResponse>> userInfoTypeReference = new ParameterizedTypeReference<DouYinBaseResponse<DYGetUserInfoResponse>>() {
        };


        ResponseEntity<DouYinBaseResponse<DYGetUserInfoResponse>> userInfoResponse = restTemplate.exchange(DouYinConstants.user_info, HttpMethod.POST, httpEntity, userInfoTypeReference);


        DYGetUserInfoResponse infoResponse = userInfoResponse.getBody().getData();


        DyCustom build = DyCustom.builder()
                .accessToken(data.getAccess_token())
                .openId(data.getOpen_id())
                .refreshToken(data.getRefresh_token())
                .checkUptime(LocalDateTime.now().plusSeconds(data.getExpires_in()))
                .refreshOutTime(LocalDateTime.now().plusSeconds(data.getRefresh_expires_in()))
                .avatar(infoResponse.getAvatar())
                .city(infoResponse.getCity())
                .country(infoResponse.getCountry())
                .gender(infoResponse.getGender())
                .nickname(infoResponse.getNickname())
                .province(infoResponse.getProvince())
                .unionId(infoResponse.getUnion_id())
                .status(1)
                .tokenOutTime(LocalDateTime.now().plusSeconds(data.getExpires_in()))
                .build();

        dyCustomService.save(build);


    }

}
