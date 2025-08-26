package com.spring.service.impl;

import com.base.ServiceBase;
import com.spring.dao.DifangfandianMapper;
import com.spring.entity.Difangfandian;
import com.spring.service.DifangfandianService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("DifangfandianService")
public class DifangfandianServiceImpl extends ServiceBase<Difangfandian> implements DifangfandianService {
    @Resource
    private DifangfandianMapper dao;

    @Override
    protected DifangfandianMapper getDao() {
        return dao;
    }
}
