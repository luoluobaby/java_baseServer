package com.service.model.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.model.po.SimulatorInfo;
import com.service.model.BaseServiceImpl;
import com.util.StringUtils;

@Service("simulatorInfoAdditionServiceImpl")
public class SimulatorInfoServiceImpl extends BaseServiceImpl<SimulatorInfo> {
	
	/**
	 * 查找机器
	 * @param trainUnitCode
	 * @param equipmentType
	 * @return
	 */
	public SimulatorInfo queryByGroup(String trainUnitCode,String equipmentType) {		
		SimulatorInfo addition=dao.get(
				"from SimulatorInfo t where t.driveTrainCompanyInfo.trainUnitCode = ? "
				+ "and t.equipmentType = ? ",new Object[]{trainUnitCode,equipmentType});
		return addition;
	}
	
	/**
	 * 查找机器
	 * @param trainUnitCode
	 * @param equipmentType
	 * @param stat
	 * @return
	 */
	public SimulatorInfo queryByGroup(String trainUnitCode,short stat) {
		
		SimulatorInfo addition= dao.get(
				"from SimulatorInfo t where t.driveTrainCompanyInfo.trainUnitCode = ? "
				+ "and "
				+ "t.stat = ? order by t.equipmentCode asc",new Object[]{trainUnitCode,stat});
		return addition;
	}
}
