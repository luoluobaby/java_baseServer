package com.service.model.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.model.po.CurrentUserInfo;
import com.model.po.SimulatorInfo;
import com.model.vo.CurrentUserInfoV;
import com.service.model.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
//相当于当前类中的TP指的都是CurrentUserInfo表
@Service("currentUserInfoServiceImpl")
public class CurrentUserInfoServiceImpl extends BaseServiceImpl<CurrentUserInfo> {
	
	@Resource
	private SimulatorInfoServiceImpl simulatorInfoServiceImpl;
	
	public CurrentUserInfo queryByEquipmentId(String equipmentId) {
		CurrentUserInfo addition=dao.get("from CurrentUserInfo t where t.SimulatorInfo.equipmentId = ?", new String[]{equipmentId});
		return addition;
	}
	
	public Serializable insert(CurrentUserInfoV c) {
		
		SimulatorInfo simulatorInfo = simulatorInfoServiceImpl.queryByGroup(c.getTrainUnitCode(), c.getEquipmentType());
		CurrentUserInfo curr = new CurrentUserInfo();
		BeanUtils.copyProperties(c, curr);
		curr.setSimulatorInfo(simulatorInfo);
		return insert(curr);
	}
}
