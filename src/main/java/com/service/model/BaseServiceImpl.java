package com.service.model;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dao.BaseDaoI;
import com.exception.constraint.ValueNullException;
import com.service.addition.AdditionService;

import java.lang.reflect.Type;
/**
 * 数据库操作的封装基类
 * @author Administrator
 *
 * @param <TP>
 */
public abstract class BaseServiceImpl<TP> implements ServiceI<TP>{
	
	@Resource
	protected BaseDaoI<TP> dao;
	
	protected Logger logger=LoggerFactory.getLogger(BaseServiceImpl.class);
	
	@Override
	public void update(TP t) {
		// TODO Auto-generated method stub
		if (null!=t) {
			dao.update(t);
		}
		else {
			logger.error("更新类为空!");
		}
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		if (null==id||"".equals(id)) {
			logger.error("id为空!");
			return ;
		}
		Class <TP> entityClass = (Class<TP>)getSuperClassGenricType(getClass(), 0);
		//基于‘，’将字符串分隔为多个字符串
		TP info=dao.get(entityClass , id);
		if (null!=info) {
			dao.delete(info);
		}
		else {
			logger.error("delete class is not found !");
		}
	}

	@Override
	public TP queryByKey(String id) {
		// TODO Auto-generated method stub
		if(null==id||"".equals(id))
			return null;
		//获取Tp的类型
		Class <TP> entityClass = (Class<TP>)getSuperClassGenricType(getClass(), 0);  
		//根据TP的类型查找相应的表，然后根据id在表中查找对应的Tp对象，表为TP对象的集合 dao 封装了访问数据库的方法
		TP t=dao.get(entityClass, id);
		return t;
	}

	@Override
	public Serializable insert(TP t) {
		// TODO Auto-generated method stub
		if (null != t) {
			return dao.save(t);
		}
		else
		{
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
