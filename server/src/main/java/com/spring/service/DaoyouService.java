package com.spring.service;

import com.base.IServiceBase;
import com.spring.entity.Daoyou;

import java.util.Map;

public interface DaoyouService extends IServiceBase<Daoyou> {
    Map<String, Object> pageQuery(int pageNum, int pageSize, String search, String status);
}