package com.service.model;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.BaseDaoI;
import java.lang.reflect.Type;

public abstract class BaseServiceImpl<TP> implements ServiceI<TP>{
	
	@Resource
	protected BaseDaoI<TP> dao;
	
	@Override
	public void update(TP t) {
		// TODO Auto-generated method stub
		dao.update(t);
	}

	@Override
	public void delete(String ids) {
		// TODO Auto-generated method stub
		if(null==ids||"".equals(ids))
			return;
		Class <TP> entityClass = (Class<TP>)getSuperClassGenricType(getClass(), 0);   
		for(String id:ids.split(","))
		{
			
			TP info=dao.get(entityClass, id);
			dao.delete(info);
		}
	}

	@Override
	public TP queryByKey(String id) {
		// TODO Auto-generated method stub
		if(null==id||"".equals(id))
			return null;
		Class <TP> entityClass = (Class<TP>)getSuperClassGenricType(getClass(), 0);  
		TP t=dao.get(entityClass, id);
		return t;
	}

	@Override
	public Serializable insert(TP t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public void Validate(TP t) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("unchecked")  
    public static Class<Object> getSuperClassGenricType(final Class clazz, final int index) {  
          
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。  
        Type genType = clazz.getGenericSuperclass();  
  
        if (!(genType instanceof ParameterizedType)) {  
           return Object.class;  
        }  
        //返回表示此类型实际类型参数的 Type 对象的数组。  
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
