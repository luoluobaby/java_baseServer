package com.service.addition;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.BaseDaoI;
import com.exception.constraint.ValueIllegalException;
import com.model.po.DriveTrainCompanyInfo;
import com.model.po.SimulatorInfo;
import com.model.vo.CurrentUserInfoV;
import com.service.model.impl.DriveTrainCompanyInfoServiceImpl;
import com.service.model.impl.SimulatorInfoServiceImpl;
import com.sun.xml.bind.v2.runtime.Name;
import com.util.StringUtils;

@Service("simulatorInfoService")
public class SimulatorInfoService {
	
	@Resource
	private SimulatorInfoServiceImpl simulatorInfoAdditionServiceImpl;
	
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
	
	
	public SimulatorInfo queryByGroup(String trainUnitCode,String equipmentType ,short state)
	{
		return simulatorInfoAdditionServiceImpl.queryByGroup(trainUnitCode , state);
	}
	
	/**
	 * 注册一台机器
	 * @param chipId
	 * @param SimulatorNum
	 * @param Train_Unit_Code
	 * @param Equipment_Type
	 * @return
	 */
	public boolean RigisterMachine(String chipId ,String simulatorNum , String companyName , String equipment_Type )
	{
		SimulatorInfo simulatorInfo = simulatorInfoAdditionServiceImpl.queryByKey(chipId);
		//查找当前要注册的驾校编号
		DriveTrainCompanyInfo companyInfo = trainCompanyInfoAdditionServiceImpl.queryByCompanyName(companyName);
		if (null == companyInfo ||( null != simulatorInfo &&false == companyInfo.getTrainUnitCode().equals(simulatorInfo.getDriveTrainCompanyInfo().getTrainUnitCode())) ) {
			new ValueIllegalException("公司名称输入错误");
			return false;
		}
		if (simulatorInfo != null) {
			simulatorInfo.setEquipmentCode(simulatorNum);
			simulatorInfo.setEquipmentType(equipment_Type);
			simulatorInfoAdditionServiceImpl.update(simulatorInfo);
		}
		else
		{
			simulatorInfo = new SimulatorInfo();
			simulatorInfo.setEquipmentId(chipId);
			simulatorInfo.setEquipmentType(equipment_Type);
			simulatorInfo.setDriveTrainCompanyInfo(companyInfo);
			simulatorInfo.setStat((short)(0));
			simulatorInfo.setEquipmentCode(simulatorNum);
			simulatorInfoAdditionServiceImpl.insert(simulatorInfo);			
		}
		
		SimulatorInfo temp = simulatorInfoAdditionServiceImpl.queryByKey(chipId);
		return null!=temp&& temp.getEquipmentCode().equals(simulatorInfo.getEquipmentCode())
				&&temp.getEquipmentType().equals(simulatorInfo.getEquipmentType());
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
