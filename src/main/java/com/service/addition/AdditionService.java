package com.service.addition;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.model.po.CurrentUserInfo;
import com.model.po.SimulatorInfo;
import com.model.vo.CurrentUserInfoV;
import com.service.model.impl.CurrentUserInfoServiceImpl;

@Service("additionService")
public class AdditionService {
	
	private final String userCode ="moni";
	
	private final String password="123";
	
	@Resource
	private CurrentUserInfoServiceImpl currentUserInfoAdditionServiceImpl;
	@Resource
	private SimulatorInfoService simulatorInfoService;
	/**
	 * 登陆
	 * @param trainUnitCode 驾校编号
	 * @param equipmentType 训练类型(手动/自动等)
	 * @param inputAdditionV 输入的CurrentUserInfo表相关信息
	 * @return
	 */
	
	public String login(CurrentUserInfoV inputAdditionV , String userCode , String password) {
		
		String strback = "";
		
		if (false == CheckPasswd(userCode, password)) {
			strback = BackString(false,99,"通讯凭证错误");//登录用户名和密码错误
		}
		else
		{
			CurrentUserInfo outAddition=currentUserInfoAdditionServiceImpl.queryByKey(inputAdditionV.getSimulatorId());
			if(null==outAddition)
			{				
				//系统分配机器
				SimulatorInfo	simulatorInfoAddition=simulatorInfoService.getByGroup(
							inputAdditionV.getTrainUnitCode(), inputAdditionV.getEquipmentType());
				if(null==simulatorInfoAddition)
					strback = BackString(false,1,"查询无此模拟器");
				if (simulatorInfoAddition.getStat()==1) 
					strback = BackString(false,2,"模拟器状态为非空闲");
				else if( simulatorInfoAddition.getStat()==2 )
					strback = BackString(false,3,"模拟器故障");
				else {
					inputAdditionV.setEquipmentId(simulatorInfoAddition.getEquipmentId());
					inputAdditionV.setStat((byte)0);
					String key= (String) currentUserInfoAdditionServiceImpl.insert(inputAdditionV);
					if(null==key||"".equals(key))
						strback = BackString(false,99,"通讯凭证错误");
					else
					{
						simulatorInfoAddition.setStat((short)1);
						simulatorInfoService.update(simulatorInfoAddition);
						strback = BackString(true,0,"模拟器登入成功!编号："+simulatorInfoAddition.getEquipmentCode());
					}					
				}				
			}			
		}
		
		return strback;
	}
	/**
	 * 正常退出，不删除，只标记
	 * @param simulatorId 训练记录流水id
	 */
	public String logoutNormalNoDelete(String simulatorId,String userCode, String password) {
		
		String strback = "";
		if (false == CheckPasswd(userCode, password)) {
			strback = BackString(false,99,"通讯凭证错误");//登录用户名和密码错误
		}
		else
		{
			CurrentUserInfo currentUserInfoAdditionV=currentUserInfoAdditionServiceImpl.
					queryByKey(simulatorId);
			if(null==currentUserInfoAdditionV)
				strback = BackString(false,1,"模拟器编号或流水号出错");
			
			if (null==currentUserInfoAdditionV.getSimulatorInfo() || (short)0==currentUserInfoAdditionV.getSimulatorInfo().getStat()) {
				strback = BackString(false,2,"模拟器并未处于使用状态");
			}
			else
			{
				currentUserInfoAdditionV.getSimulatorInfo().setStat((short)0);
				simulatorInfoService.update(currentUserInfoAdditionV.getSimulatorInfo());
			}
			currentUserInfoAdditionV.setStat((byte)1);
			currentUserInfoAdditionServiceImpl.update(currentUserInfoAdditionV);
			strback = BackString(true,0,"模拟器登退成功");
		}		
		return strback;
	}
	/**
	 * 正常退出，并删除
	 * @param simulatorId
	 */
	public void logoutNormalDelete(String simulatorId) {
		CurrentUserInfo currentUserInfoAdditionV=currentUserInfoAdditionServiceImpl.
				queryByKey(simulatorId);
		if(null==currentUserInfoAdditionV||
			(byte)1!=currentUserInfoAdditionV.getStat())
			return;
		
		currentUserInfoAdditionServiceImpl.delete(currentUserInfoAdditionV.getSimulatorId());
	}
	
	/**
	 * 验证调用函数的用户名和密码 ， 由我们给对方
	 * @param userCode
	 * @param password
	 * @return
	 */
	public boolean CheckPasswd(String userCode , String password)
	{
		if ( this.userCode.equals(userCode) && this.password.equals(password) ) 
			return true;
		else
			return false;
	}
	
	/**
	 * 设置回复消息
	 * @param 执行操作成功还是失败
	 * @param 错误Id
	 * @param 错误描述
	 * @return
	 */
	private String BackString(boolean ifSucc,int backId,String str) {
		String back ="";
		back+=(ifSucc==true?"1":"0");
		back+=(","+backId);
		back+=(","+str);
		return back;
	}
}
