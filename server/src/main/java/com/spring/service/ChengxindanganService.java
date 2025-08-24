package com.spring.service;

import com.base.IServiceBase;
import com.spring.entity.Chengxindangan;

public interface ChengxindanganService extends IServiceBase<Chengxindangan> {
    Chengxindangan findByTow(String leibie, String relativeId);
}
