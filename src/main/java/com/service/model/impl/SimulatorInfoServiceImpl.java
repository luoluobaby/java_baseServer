package com.service.model.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.model.po.SimulatorInfo;
import com.service.model.BaseServiceImpl;
import com.util.StringUtils;

@Service("simulatorInfoAdditionServiceImpl")
public class SimulatorInfoServiceImpl extends BaseServiceImpl<SimulatorInfo> {
	
	
	public List<SimulatorInfo> queryByGroup(String trainUnitCode,String equipmentType,short stat) {
		
		List<SimulatorInfo> additions=dao.find(
				"from SimulatorInfoAddition t where t.DriveTrainCompanyInfo.trainUnitCode = ? "
				+ "and t.equipmentType = ? and "
				+ "t.stat = ? ",new Object[]{trainUnitCode,equipmentType,stat});
		
		return additions;
	}
	public List<SimulatorInfo> queryByGroup(short stat) {
		
		List<SimulatorInfo> additions=dao.find(
				"from SimulatorInfoAddition t where t.stat = ? ",new Object[]{stat});
		if(null==additions||0>=additions.size())
			return null;		
		return additions;
	}
	public SimulatorInfo getByGroup(String trainUnitCode,String equipmentType,String equipentCode,short stat) {
		equipentCode=StringUtils.Pad(equipentCode, StringUtils.PADDING_LEGTH_EQUIPMENT_CODE, true);
		SimulatorInfo addition= dao.get(
				"select t from SimulatorInfoAddition t,MachineInfoProfession m where t.DriveTrainCompanyInfo.trainUnitCode = ? "
				+ "and t.equipmentType = ? and"
				+ " t.stat = ? and t.equipmentCode= ? and t.equipmentId=m.chipId and m.expireTime>= now()",new Object[]{trainUnitCode,equipmentType,stat,equipentCode});
		return addition;
	}
	
	public SimulatorInfo queryByGroup(String trainUnitCode,String equipmentType) {
		
		SimulatorInfo addition=dao.get(
				"from SimulatorInfoAddition t where t.DriveTrainCompanyInfo.trainUnitCode = ? "
				+ "and t.equipmentType = ? ",new Object[]{trainUnitCode,equipmentType});
		if(null==addition)
			return null;
		return addition;
	}
	
	public SimulatorInfo getByGroup(String trainUnitCode,String equipmentType,short stat) {
		
		SimulatorInfo addition= dao.get(
				"select t from SimulatorInfoAddition t,MachineInfoProfession m where t.DriveTrainCompanyInfo.trainUnitCode = ? "
				+ "and t.equipmentType = ? and "
				+ "t.stat = ? and t.equipmentId=m.chipId and m.expireTime>= now() order by t.equipmentCode asc",new Object[]{trainUnitCode,equipmentType,stat});
		if(null==addition)
			return null;
		return addition;
	}
	public SimulatorInfo getByGroup(String trainUnitCode,String equipmentType) {
		
		SimulatorInfo addition= dao.get(
				"from SimulatorInfo t where t.driveTrainCompanyInfo.trainUnitCode = ? "
				+ "and t.equipmentType = ? "
				+ "order by t.equipmentCode asc",new Object[]{trainUnitCode,equipmentType});
		return addition;
	}
}
