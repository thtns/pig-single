/*
 * Copyright (c) 2020 comic4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thtns.cloud.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.thtns.cloud.constant.CacheConstants;
import com.thtns.cloud.constant.SecurityConstants;
import com.thtns.cloud.entity.SysUser;
import com.thtns.cloud.exception.ErrorCodes;
import com.thtns.cloud.exception.UnauthorizedException;
import com.thtns.cloud.request.UserInfo;
import com.thtns.cloud.service.SysUserService;
import com.thtns.cloud.utils.MsgUtils;
import com.thtns.cloud.utils.R;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户详细信息
 *
 * @author lengleng hccake
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class ComicAppUserDetailsServiceImpl implements ComicUserDetailsService {

    private final SysUserService userService;

    private final CacheManager cacheManager;

    /**
     * 手机号登录
     *
     * @param phone 手机号
     * @return
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String phone) {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(phone) != null) {
            return (ComicUser) cache.get(phone).get();
        }


        SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getPhone, phone));
        if (user == null) {
            throw new UnauthorizedException(MsgUtils.getMessage(ErrorCodes.SYS_USER_USERINFO_EMPTY, phone));
        }

        R<UserInfo> result = R.ok(userService.getUserInfo(user));


        UserDetails userDetails = getUserDetails(result);
        if (cache != null) {
            cache.put(phone, userDetails);
        }
        return userDetails;
    }

    /**
     * check-token 使用
     *
     * @param comicUser user
     * @return
     */
    @Override
    public UserDetails loadUserByUser(ComicUser comicUser) {
        return this.loadUserByUsername(comicUser.getPhone());
    }

    /**
     * 是否支持此客户端校验
     *
     * @param clientId 目标客户端
     * @return true/false
     */
    @Override
    public boolean support(String clientId, String grantType) {
        return SecurityConstants.APP.equals(grantType);
    }

}
