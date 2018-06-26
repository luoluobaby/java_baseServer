package com.service.addition;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils.Null;
import org.springframework.stereotype.Service;

//import com.google.gson.Gson;
import com.model.po.CurrentUserInfo;
import com.model.po.SimulatorInfo;
import com.model.vo.CurrentUserInfoV;
import com.service.model.impl.CurrentUserInfoServiceImpl;
import com.websocket.Machines;
import com.websocket.SocketConstSendTextType;
import com.websocket.SocketSendTextFormat;

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
	 * @param inputAdditionV 输入的CurrentUserInfo表相关信息32.0.
	 * @return
	 */
	
	public String login(CurrentUserInfoV inputAdditionV , String userCode , String password) {
		
		String strback = "";
		//判断登陆的用户名和密码是否错误
		if (false == CheckPasswd(userCode, password)) {
			strback = BackString(false,99,"通讯凭证错误");
		}
		else
		{
			//判断当前训练的流水号是否已经登录  CurrentUserInfo表中存储的是已经登录的用户的信息
			// 根据训练流水号从CurrentUserInfo的表查找对应的用户信息      currentUserInfoAdditionServiceImpl 是表和表的操作的集合的对象
			CurrentUserInfo outAddition=currentUserInfoAdditionServiceImpl.queryByKey(inputAdditionV.getSimulatorId());
			SimulatorInfo simulatorInfo = null;
			String key=null;
			//当当前用户已经在线时， 查询当前用户的训练信息
			if (null != outAddition) {
				simulatorInfo = outAddition.getSimulatorInfo();
				key = simulatorInfo.getEquipmentId(); 
			}
			else
			{//当前用户还未在线时  从总的模拟器数据库中查找对应的模拟器信息
				
				simulatorInfo=simulatorInfoService.queryByGroup(
						inputAdditionV.getTrainUnitCode(), inputAdditionV.getEquipmentType() , (short)0);
				if(null==simulatorInfo)
				{
					strback = BackString(false,1,"查询无此模拟器");
				}
			}
			if( null != simulatorInfo )
			{
				Machines machines = Machines.GetMachine(simulatorInfo.getEquipmentId());
				if (null == machines) {
					//发生错误
					strback = BackString(false,1,"查询无此模拟器");
				}
				else
				{//分配的机器序号是在线和空闲的
					//给当前用户分配选择的机器序列号
					inputAdditionV.setEquipmentId(simulatorInfo.getEquipmentId());
					//改变当前用户的状态
					inputAdditionV.setStat((byte)0);
					//将当前用户信息插入到对应的表中
					if (null == key) {
						key= (String) currentUserInfoAdditionServiceImpl.insert(inputAdditionV);
					}
					if(null==key||"".equals(key))
						strback = BackString(false,99,"通讯凭证错误");
					else
					{
						//改变模拟器的状态
						simulatorInfo.setStat((short)1);
						//更新模拟器的信息
						simulatorInfoService.update(simulatorInfo);
						strback = BackString(true,0,"模拟器登入成功!编号："+simulatorInfo.getEquipmentCode());
						//发送信息到该台模拟器。表明当前有用户登陆进来。
						try {
							machines.sendMessage( new SocketSendTextFormat<CurrentUserInfoV>(SocketConstSendTextType.LoginIn ,inputAdditionV).toString() );
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
				//这是在一个列表中查找，而不是在表中查找
				//模拟器不退出，用户也不退出，通知机器上传训练记录，上传完成后当
				Machines machines = Machines.GetMachine(currentUserInfoAdditionV.getSimulatorInfo().getEquipmentId());
				if (null != machines) {
					try {
						machines.sendMessage(new SocketSendTextFormat<String>(SocketConstSendTextType.LoginOut,"Login out").toString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				strback = BackString(true,0,"模拟器登退成功");
			}
		}		
		return strback;
	}
	/**
	 * 正常退出，并删除
	 * @param simulatorId
	 */
	public void deleteUserAndReleaseSimulator(String simulatorId) {
		CurrentUserInfo info = currentUserInfoAdditionServiceImpl.queryByKey(simulatorId);
		if (null != info) {
			if (null != info.getSimulatorInfo()) {
				simulatorInfoService.SetMachineOnLine(info.getSimulatorInfo().getEquipmentId());
			}
			currentUserInfoAdditionServiceImpl.delete(simulatorId);
		}
		
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
