package com.thtns.cloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thtns.cloud.entity.DyCustom;

public interface DyCustomService extends IService<DyCustom> {

    void add(String code);


}
