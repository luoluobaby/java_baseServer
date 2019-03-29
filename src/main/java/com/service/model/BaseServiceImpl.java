package com.service.model;

import com.dao.BaseDao;
import com.dao.impl.BaseDaoImpl;
import com.service.model.i.ServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;

/**
 * 数据库操作的封装基类
 *
 * @param <TP>
 * @author Administrator
 */
public abstract class BaseServiceImpl<TP, PK extends Serializable> implements ServiceI<TP, PK> {


    protected BaseDaoImpl<TP, PK> dao;

    private  Class<TP> c =null;

    @Resource
    public void setBaseDao(BaseDaoImpl<TP, PK> dao){
        this.dao = dao;
        this.c = (Class<TP>) getSuperClassGenricType(getClass(),0);
    }

    protected Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Override
    public void update(TP t) {
        // TODO Auto-generated method stub
        if (null != t) {
            dao.update(t);
        } else {
            logger.error("更新类为空!");
        }
    }

    @Override
    public boolean delete(PK id) {
        // TODO Auto-generated method stub
        if (null == id || "".equals(id)) {
            logger.error("id为空!");
            return true;
        }
        //基于‘，’将字符串分隔为多个字符串
        TP info = dao.get(id,c);
        if (null != info) {
            dao.delete(info);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public TP queryByKey(PK id) {
        // TODO Auto-generated method stub
        if (null == id || "".equals(id))
            return null;
        TP t = dao.get(id,c);
        return t;
    }

    @Override
    public PK insert(TP t) {
        // TODO Auto-generated method stub
        if (null != t) {
            return dao.save(t);
        } else {
            logger.error("inset class is null !");
            return null;
        }
    }

    @Override
    public void Validate(TP t) {
        // TODO Auto-generated method stub
    }

    /**
     * 返回 传入的类型的超类的参数的类型数组的index下标的类型对象
     * @param clazz
     * @param index
     * @return
     */
    //抑制警告的产生
    @SuppressWarnings("unchecked")
    public static Class<Object> getSuperClassGenricType(final Class clazz, final int index) {

        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();
        //instanceof指出对象是否是特定类的一个实例
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。  即为参数类中中的参数的类型
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }
}
