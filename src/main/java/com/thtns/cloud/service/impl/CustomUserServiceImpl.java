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

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thtns.cloud.constant.CacheConstants;
import com.thtns.cloud.entity.Custom;
import com.thtns.cloud.entity.SysRole;
import com.thtns.cloud.entity.SysUserRole;
import com.thtns.cloud.exception.ErrorCodes;
import com.thtns.cloud.mapper.CustomMapper;
import com.thtns.cloud.mapper.SysRoleMapper;
import com.thtns.cloud.request.CustomUserRequest;
import com.thtns.cloud.request.UserRequest;
import com.thtns.cloud.response.UserResponse;
import com.thtns.cloud.service.CustomUserService;
import com.thtns.cloud.utils.MsgUtils;
import com.thtns.cloud.utils.ParamResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl extends ServiceImpl<CustomMapper, Custom> implements CustomUserService {

	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

	private final SysRoleMapper sysRoleMapper;

	/**
	 * 保存用户信息
	 *
	 * @param userRequest DTO 对象
	 * @return success/fail
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveUser(CustomUserRequest userRequest) {
		Custom sysUser = new Custom();
		BeanUtils.copyProperties(userRequest, sysUser);
		//设置默认密码
		sysUser.setPassword(ENCODER.encode("123456"));
		sysUser.setNickname(userRequest.getUsername());

		// 获取默认角色编码
		String defaultRole = ParamResolver.getStr("CUSTOM_DEFAULT_ROLE");
		// 默认角色
		SysRole sysRole = sysRoleMapper
				.selectOne(Wrappers.<SysRole>lambdaQuery().eq(SysRole::getRoleCode, defaultRole));

		if (sysRole == null) {
			throw new RuntimeException(MsgUtils.getMessage(ErrorCodes.SYS_PARAM_CONFIG_ERROR, "CUSTOM_DEFAULT_ROLE"));
		}

		baseMapper.insert(sysUser);
		SysUserRole userRole = new SysUserRole();
		userRole.setUserId(sysUser.getId());
		userRole.setRoleId(sysRole.getRoleId());

		userRole.insert();

		return Boolean.TRUE;
	}


	/**
	 * 分页查询用户信息（含有角色信息）
	 *
	 * @param page        分页对象
	 * @param userRequest 参数列表
	 * @return
	 */
	@Override
	public IPage<List<UserResponse>> getUserWithRolePage(Page page, UserRequest userRequest) {
//		return baseMapper.getUserVosPage(page, userRequest);
		return null;
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id 用户ID
	 * @return 用户信息
	 */
	@Override
	public UserResponse getUserVoById(Long id) {
//		return baseMapper.selectById(id);
		return null;
	}


	@Override
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "#userRequest.username")
	public Boolean updateUserInfo(UserRequest userRequest) {

		LambdaQueryWrapper<Custom> wrapper = Wrappers.<Custom>lambdaQuery().eq(Custom::getUsername, userRequest.getUsername());
		Custom custom = getOne(wrapper);

//		UserResponse userResponse = baseMapper.getUserVoByUsername(userRequest.getUsername());

		Assert.isTrue(ENCODER.matches(userRequest.getPassword(), custom.getPassword()),
				MsgUtils.getMessage(ErrorCodes.SYS_USER_UPDATE_PASSWORDERROR));

		Custom sysUser = new Custom();
		if (StrUtil.isNotBlank(userRequest.getNewpassword1())) {
			sysUser.setPassword(ENCODER.encode(userRequest.getNewpassword1()));
		}
		sysUser.setPhone(userRequest.getPhone());
//		sysUser.setUserId(userVO.getUserId());
//		sysUser.setAvatar(userDto.getAvatar());
		return this.updateById(sysUser);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "#userRequest.username")
	public Boolean updateUser(UserRequest userRequest) {
		Custom sysUser = new Custom();
		BeanUtils.copyProperties(userRequest, sysUser);
		sysUser.setUpdateTime(LocalDateTime.now());

		if (StrUtil.isNotBlank(userRequest.getPassword())) {
			sysUser.setPassword(ENCODER.encode(userRequest.getPassword()));
		}
		this.updateById(sysUser);

		return Boolean.TRUE;
	}


	@Override
	public Custom getUserInfo(String sysUser) {

		LambdaQueryWrapper<Custom> wrapper = Wrappers.<Custom>lambdaQuery().eq(Custom::getUsername, sysUser).or().eq(Custom::getPhone, sysUser);

		return this.getOne(wrapper);


	}

	@Override
	public Boolean removeUserById(Custom sysUser) {
		return null;
	}
}
