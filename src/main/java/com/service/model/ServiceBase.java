package com.service.model;

import javax.annotation.Resource;

import com.dao.BaseDaoI;
/**
 * 数据库操作服务基类
 * @author 阳
 *
 * @param <TP>
 */
public abstract class ServiceBase<TP> {
	@Resource
	protected BaseDaoI<TP> dao;

	public BaseDaoI<TP> getDao() {
		return dao;
	}

	public void setDao(BaseDaoI<TP> dao) {
		this.dao = dao;
	}
}
