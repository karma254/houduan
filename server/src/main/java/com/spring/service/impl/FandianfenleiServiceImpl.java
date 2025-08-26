package com.spring.service.impl;

import com.base.ServiceBase;
import com.spring.dao.FandianfenleiMapper;
import com.spring.entity.Fandianfenlei;
import com.spring.service.FandianfenleiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("FandianfenleiService")
public class FandianfenleiServiceImpl extends ServiceBase<Fandianfenlei> implements FandianfenleiService {
    @Resource
    private FandianfenleiMapper dao;

    @Override
    protected FandianfenleiMapper getDao() {
        return dao;
    }
}
