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
	public void update(SimulatorInfo addition) {
		simulatorInfoAdditionServiceImpl.update(addition);
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
	
	/***********************************下面是websocket调用的函数***********************************************/
	/**
	 * 判断当前机器序列号是否有效
	 * @param machineChip
	 * @return
	 */
	public boolean CheckIfMachineExist(String machineChip)
	{
		return null != queryByKey(machineChip);
	}
	
	/**
	 * 设置当前机器上线，设置其stat
	 * @param machineChip
	 */
	public void SetMachineOnLine( String machineChip )
	{
		SetMachineState(machineChip,(short)0);
	}
	
	/**
	 * 设置当前机器下线
	 * @param machineChip
	 */
	public void SetMachineOffLine( String machineChip )
	{
		SetMachineState(machineChip,(short)2);
	}
	
	/**
	 * 设置当前机器忙碌
	 * @param machineChip
	 */
	public void SetMachineBusy( String machineChip )
	{
		SetMachineState(machineChip,(short)1);
	}
	
	private void SetMachineState( String machineChip ,short stat)
	{
		SimulatorInfo info = queryByKey(machineChip);
		if (null != info) {
			info.setStat(stat);
			update(info);
		}
	}
}
