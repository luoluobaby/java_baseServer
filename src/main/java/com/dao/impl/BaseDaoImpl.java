package com.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;


/**
 * @author JimmyBlue
 * @date 2018/3/3 0003 9:55
 * 实现基于泛型的基本CRUD操作实现类
 *
 * @param <T>
 * @param <PK>
 */
@Repository
public class BaseDaoImpl<T, PK extends Serializable> implements com.dao.BaseDao<T, PK> {
    /**
     * 构造方法，依据实例类自己主动获取实体类类型
     */
    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
    }

    @Resource
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    /**
     * 依据主键获取实体。假设没有对应的实体，返回null
     *
     * @param id id
     * @return 返回
     */
    @SuppressWarnings("unchecked")
    @Override
    public T get(PK id , Class<T> c) {
        return (T) this.getSession().get(c, id);
    }


    // 依据主键获取实体。假设没有对应的实体，抛出异常。
    @Override
    @SuppressWarnings("unchecked")
    public T load(PK id, Class<T> c) {
        return (T) this.getSession().load(c, id);
    }

    /**
     * 更新实体
     * 使用merge替代update，避免出现 托管态update更新异常
     * @param entity 实体
     */
    @Override
    public  void update(T entity) {
        this.getSession().merge(entity);
    }


    /**
     * 创建实体，返回主键
     * @param entity 实体
     * @return 返回
     */
    @SuppressWarnings("unchecked")
    @Override
    public PK save(T entity) {
        Serializable result  =  this.getSession().save(entity);
        return (PK) result;
    }


    /**
     * 添加或更新实体
     *
     * @param entity 实体
     */
    @Override
    public void saveOrUpdate(T entity) {
        this.getSession().saveOrUpdate(entity);
}


    /**
     * 删除指定的实体
     *
     * @param entity 实体
     */
    @Override
    public void delete(T entity) {
        this.getSession().delete(entity);
    }


    /**
     * 依据主键删除指定实体
     *
     * @param id id
     */
    @Override
    public void delete(PK id , Class<T> c) {
        this.delete(this.load(id,c));
    }

    /**
     * 查询所有
     *
     * @return 返回
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(Class<T> c) {
        return this.getSession().createCriteria(c).list();
    }

    /**
     * 批量删除
     *
     * @param ids ids
     */
    @Override
    public void delete(PK[] ids , Class<T> c) {
        Session session = this.getSession();
        //数组中封装的是ID的集合;
        for(int i=0; i<ids.length; i++) {
            delete(ids[i] ,c);
        }
    }

    @Override
    public void deleteAll(String entityName) {
        String hql = "Delete FROM "+ entityName;
        this.getSession().createQuery(hql) ;
    }

}
