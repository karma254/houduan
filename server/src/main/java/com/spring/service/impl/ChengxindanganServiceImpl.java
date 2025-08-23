package com.spring.service.impl;

import com.base.ServiceBase;
import com.spring.dao.ChengxindanganMapper;
import com.spring.entity.Chengxindangan;
import com.spring.service.ChengxindanganService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("ChengxindanganService")
public class ChengxindanganServiceImpl extends ServiceBase<Chengxindangan> implements ChengxindanganService {
    @Resource
    private ChengxindanganMapper dao;

    @Override
    protected ChengxindanganMapper getDao() {
        return dao;
    }
}
