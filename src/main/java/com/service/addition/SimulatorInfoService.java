package com.service.addition;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.BaseDaoI;
import com.model.po.SimulatorInfo;
import com.model.vo.CurrentUserInfoV;
import com.service.model.impl.DriveTrainCompanyInfoServiceImpl;
import com.service.model.impl.SimulatorInfoServiceImpl;
import com.util.StringUtils;

@Service("simulatorInfoService")
public class SimulatorInfoService {
	private final static String USER="MoNi";
	private final static String PASSWORD="8g4oid624";
	@Resource
	private SimulatorInfoServiceImpl simulatorInfoAdditionServiceImpl;
	public SimulatorInfoServiceImpl getSimulatorInfoAdditionServiceImpl() {
		return simulatorInfoAdditionServiceImpl;
	}
	@Resource
	private CurrentUserInfoService currentUserInfoService;
	
	@Resource
	private DriveTrainCompanyInfoServiceImpl trainCompanyInfoAdditionServiceImpl;
	@Resource
	private BaseDaoI<SimulatorInfo> baseDaoI;
	
	public SimulatorInfo queryByKey(String id) {
		return simulatorInfoAdditionServiceImpl.queryByKey(id);
	}
	public void delete(String id) {
		simulatorInfoAdditionServiceImpl.delete(id);
	}
	public void update(SimulatorInfo additionV) {
		simulatorInfoAdditionServiceImpl.update(additionV);
	}
	public void insert(SimulatorInfo additionV) {
		simulatorInfoAdditionServiceImpl.insert(additionV);
	}

	
	public List<SimulatorInfo> queryByGroup(String trainUnitCode,String equipmentType,short stat)
	{
		return simulatorInfoAdditionServiceImpl.queryByGroup(trainUnitCode, equipmentType, stat);
	}
	public List<SimulatorInfo> queryByGroup(short stat)
	{
		return simulatorInfoAdditionServiceImpl.queryByGroup( stat);
	}
	
	public SimulatorInfo queryByGroup(String trainUnitCode,String equipmentType)
	{
		return simulatorInfoAdditionServiceImpl.queryByGroup(trainUnitCode, equipmentType);
	}
	
	public SimulatorInfo getByGroup(String trainUnitCode,String equipmentType) {
		return simulatorInfoAdditionServiceImpl.getByGroup(trainUnitCode, equipmentType);
	}
}
