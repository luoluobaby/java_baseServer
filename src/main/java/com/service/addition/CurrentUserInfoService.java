package com.service.addition;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.model.po.CurrentUserInfo;
import com.model.vo.CurrentUserInfoV;
import com.service.model.impl.CurrentUserInfoServiceImpl;

@Service("currentUserInfoService")
public class CurrentUserInfoService {
	@Resource
	private CurrentUserInfoServiceImpl currentUserInfoAdditionServiceImpl;
	
	/**
	 * 根据主键查询指定行数据
	 * @param id
	 * @return
	 */
	public CurrentUserInfo queryByKey(String id) {
		return currentUserInfoAdditionServiceImpl.queryByKey(id);
	}
	/**
	 * 根据设备号id查询指定行数据
	 * @param equipmentId
	 * @return
	 */
	public CurrentUserInfo queryByEquipmentId(String equipmentId) {
		return currentUserInfoAdditionServiceImpl.queryByEquipmentId(equipmentId);
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(String id) {
		currentUserInfoAdditionServiceImpl.delete(id);
	}
	/**
	 * 更新
	 * @param additionV
	 */
	public void update(CurrentUserInfo additionV) {
		currentUserInfoAdditionServiceImpl.update(additionV);
	}
	/**
	 * 插入
	 * @param additionV
	 */
	public void insert(CurrentUserInfo additionV) {
		currentUserInfoAdditionServiceImpl.insert(additionV);
	}
	
}
