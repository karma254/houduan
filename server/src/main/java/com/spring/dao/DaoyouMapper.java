package com.spring.dao;

import com.spring.entity.Daoyou;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface DaoyouMapper extends Mapper<Daoyou> {
    Daoyou queryById(Integer id);
    List<Daoyou> queryAllByLimit(Map<String, Object> params);
    long count(Map<String, Object> params);
}