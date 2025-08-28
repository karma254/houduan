package com.spring.service;

import com.base.IServiceBase;
import com.spring.entity.Luyou;

import java.util.Map;

public interface LuyouService extends IServiceBase<Luyou> {
    Map<String, Object> pageQuery(int pageNum, int pageSize, String search, String difficulty);
}