package com.spring.service;

import com.base.IServiceBase;
import com.spring.entity.Yonghu;

public interface YonghuService extends IServiceBase<Yonghu> {
    Yonghu login(String username, String password);
    boolean updatePassword(int id, String newPassword);
}
