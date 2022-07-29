package com.thtns.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thtns.cloud.entity.DyCustom;
import com.thtns.cloud.mapper.DyCustomMapper;
import com.thtns.cloud.response.DYGetAccessTokenResponse;
import com.thtns.cloud.response.DYGetUserInfoResponse;
import com.thtns.cloud.service.DouYinService;
import com.thtns.cloud.service.DyCustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author liuyj
 * node
 */
@Service
@RequiredArgsConstructor
public class DyCustomServiceImpl extends ServiceImpl<DyCustomMapper, DyCustom>
        implements DyCustomService {

    private final DouYinService douYinService;


    @Override
    public void add(String code) {

        DYGetAccessTokenResponse tokenResponse = douYinService.getAccessToken(code);
        DYGetUserInfoResponse userInfo = douYinService.getUserInfo(tokenResponse.getAccess_token(), tokenResponse.getOpen_id());

        DyCustom build = DyCustom.builder()
                .accessToken(tokenResponse.getAccess_token())
                .openId(tokenResponse.getOpen_id())
                .refreshToken(tokenResponse.getRefresh_token())
                .checkUptime(LocalDateTime.now().plusSeconds(tokenResponse.getExpires_in()))
                .refreshOutTime(LocalDateTime.now().plusSeconds(tokenResponse.getRefresh_expires_in()))
                .avatar(userInfo.getAvatar())
                .city(userInfo.getCity())
                .country(userInfo.getCountry())
                .gender(userInfo.getGender())
                .nickname(userInfo.getNickname())
                .province(userInfo.getProvince())
                .unionId(userInfo.getUnion_id())
                .status(1)
                .tokenOutTime(LocalDateTime.now().plusSeconds(tokenResponse.getExpires_in()))
                .build();

        save(build);


    }
}
