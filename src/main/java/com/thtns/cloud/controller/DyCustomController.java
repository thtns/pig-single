/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.thtns.cloud.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thtns.cloud.entity.DyCustom;
import com.thtns.cloud.service.DyCustomService;
import com.thtns.cloud.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 抖音用户表
 *
 * @author 刘玉军
 * @date 2022-07-30 00:40:09
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dycustom")
@Tag(name = "抖音用户表管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class DyCustomController {

    private final DyCustomService dyCustomService;

    /**
     * 分页查询
     *
     * @param page     分页对象
     * @param dyCustom 抖音用户表
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('demo_dycustom_get')")
    public R getDyCustomPage(Page page, DyCustom dyCustom) {
        return R.ok(dyCustomService.page(page, Wrappers.query(dyCustom)));
    }


    /**
     * 通过id查询抖音用户表
     *
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('demo_dycustom_get')")
    public R getById(@PathVariable("id") Long id) {
        return R.ok(dyCustomService.getById(id));
    }

    /**
     * 新增抖音用户表
     *
     * @param dyCustom 抖音用户表
     * @return R
     */
    @Operation(summary = "新增抖音用户表", description = "新增抖音用户表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('demo_dycustom_add')")
    public R save(@RequestBody DyCustom dyCustom) {
        return R.ok(dyCustomService.save(dyCustom));
    }

    /**
     * 修改抖音用户表
     *
     * @param dyCustom 抖音用户表
     * @return R
     */
    @Operation(summary = "修改抖音用户表", description = "修改抖音用户表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('demo_dycustom_edit')")
    public R updateById(@RequestBody DyCustom dyCustom) {
        return R.ok(dyCustomService.updateById(dyCustom));
    }

    /**
     * 通过id删除抖音用户表
     *
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除抖音用户表", description = "通过id删除抖音用户表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('demo_dycustom_del')")
    public R removeById(@PathVariable Long id) {
        return R.ok(dyCustomService.removeById(id));
    }

}
