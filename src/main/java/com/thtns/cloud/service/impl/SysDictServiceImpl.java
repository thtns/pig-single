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
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thtns.cloud.constant.CacheConstants;
import com.thtns.cloud.constant.enums.DictTypeEnum;
import com.thtns.cloud.entity.SysDict;
import com.thtns.cloud.entity.SysDictItem;
import com.thtns.cloud.exception.ErrorCodes;
import com.thtns.cloud.mapper.SysDictItemMapper;
import com.thtns.cloud.mapper.SysDictMapper;
import com.thtns.cloud.service.SysDictService;
import com.thtns.cloud.utils.MsgUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 字典表
 *
 * @author lengleng
 * @date 2019/03/19
 */
@Service
@RequiredArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

	private final SysDictItemMapper dictItemMapper;

	/**
	 * 根据ID 删除字典
	 *
	 * @param id 字典ID
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public void removeDict(Long id) {
		SysDict dict = this.getById(id);
		// 系统内置
		Assert.state(!DictTypeEnum.SYSTEM.getType().equals(dict.getSystemFlag()),
				MsgUtils.getMessage(ErrorCodes.SYS_DICT_DELETE_SYSTEM));
		baseMapper.deleteById(id);
		dictItemMapper.delete(Wrappers.<SysDictItem>lambdaQuery().eq(SysDictItem::getDictId, id));
	}

	/**
	 * 更新字典
	 *
	 * @param dict 字典
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.DICT_DETAILS, key = "#dict.type")
	public void updateDict(SysDict dict) {
		SysDict sysDict = this.getById(dict.getId());
		// 系统内置
		Assert.state(!DictTypeEnum.SYSTEM.getType().equals(sysDict.getSystemFlag()),
				MsgUtils.getMessage(ErrorCodes.SYS_DICT_UPDATE_SYSTEM));
		this.updateById(dict);
	}

	@Override
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public void clearDictCache() {

	}

}
