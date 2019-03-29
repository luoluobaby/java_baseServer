package com.dao;


import java.io.Serializable;
import java.util.List;


/**
 * @author JimmyBlue
 * @date 2018/3/3 0003 9:55
 * 基本CRUD接口，用于实现泛型的基本数据库操作接口
 * @param <T>
 * @param <PK>
 */
public interface BaseDao<T, PK extends Serializable> {

    /**
     * 依据主键获取实体。假设没有对应的实体。返回 null。
      * @param id id
     * @return 主键
     */
    T get(PK id,Class<T> c);

    /**
     * 依据主键获取实体。假设没有对应的实体。抛出异常。
      * @param id id
     * @return 类型
     */
    T load(PK id, Class<T> c);



    /**
     * 更新实体
     * @param entity 实体
     */
    void update(T entity);

    /**
     * 存储实体到数据库
     * @param entity 实体
     */
    PK save(T entity);


    /**
     * 添加或更新实体
     * @param entity 实体
     */
    void saveOrUpdate(T entity);

    /**
     * 删除指定的实体
     * @param entity 实体
     */
    void delete(T entity);

    /**
     * 依据主键删除指定实体
      * @param id id
     */
    void delete(PK id, Class<T> c);

    /**
     * 依据主键删除指定实体
     * @return 返回
     */
    List<T> findAll(Class<T> c);

    /**
     * 批量删除
     * @param ids ids
     */
    void delete(PK[] ids, Class<T> c);

    /**
     * 清空表数据
     */
    void deleteAll(String tableName);
}
