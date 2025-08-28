package com.spring.service.impl;

import com.base.ServiceBase;
import com.spring.dao.LuyouMapper;
import com.spring.entity.Luyou;
import com.spring.service.LuyouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("luyouService")
public class LuyouServiceImpl extends ServiceBase<Luyou> implements LuyouService {

    @Autowired
    private LuyouMapper luyouMapper;

    @Override
    protected Mapper<Luyou> getDao() {
        return luyouMapper;
    }

    @Override
    public Map<String, Object> pageQuery(int pageNum, int pageSize, String search, String difficulty) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", search);
        params.put("difficulty", difficulty);

        int pageStart = (pageNum - 1) * pageSize;
        params.put("pageStart", pageStart);
        params.put("pageSize", pageSize);

        List<Luyou> list = luyouMapper.queryAllByLimit(params);
        long total = luyouMapper.count(params);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        result.put("pageNum", pageNum);
        result.put("pageSize", pageSize);

        return result;
    }
}