package com.service.model;

import java.io.Serializable;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import com.dao.BaseDaoI;

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
		Class <TP> entityClass = (Class <TP>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];   
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
		Class <TP> entityClass = (Class <TP>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
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
	
}
