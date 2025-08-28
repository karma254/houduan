package com.spring.dao;

import com.spring.entity.Luyou;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


@Repository
public interface LuyouMapper extends Mapper<Luyou> {
    Luyou queryById(Integer id);
    List<Luyou> queryAllByLimit(Map<String, Object> params);
    long count(Map<String, Object> params);
}