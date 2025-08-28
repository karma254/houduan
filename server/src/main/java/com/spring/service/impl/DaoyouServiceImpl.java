package com.spring.service.impl;

import com.base.ServiceBase;
import com.spring.dao.DaoyouMapper;
import com.spring.entity.Daoyou;
import com.spring.service.DaoyouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("daoyouService")
public class DaoyouServiceImpl extends ServiceBase<Daoyou> implements DaoyouService {

    @Autowired
    private DaoyouMapper daoyouMapper;

    @Override
    protected Mapper<Daoyou> getDao() {
        return daoyouMapper;
    }

    @Override
    public Map<String, Object> pageQuery(int pageNum, int pageSize, String search, String status) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", search);
        params.put("status", status);

        int pageStart = (pageNum - 1) * pageSize;
        params.put("pageStart", pageStart);
        params.put("pageSize", pageSize);

        List<Daoyou> list = daoyouMapper.queryAllByLimit(params);
        long total = daoyouMapper.count(params);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        result.put("pageNum", pageNum);
        result.put("pageSize", pageSize);

        return result;
    }
}