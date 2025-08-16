package com.base;

import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Service基本类接口
 * @param <E>
 */
public interface IServiceBase<E> {
    List<E> select();
    List<E> select(E y);
    E find(Object id);
    E findEntity(E id);
    List<E> selectPage(E obj, int page, int pageSize);
    List<E> selectPageExample(Example obj, int page, int pageSize);
    int delete(Object id);
    int insert(E y);
    int update(E y);
}
