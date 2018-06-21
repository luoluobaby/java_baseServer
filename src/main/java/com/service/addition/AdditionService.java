package com.service.addition;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils.Null;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
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
			SimulatorInfo simulatorInfo = null;
			//查询是否已经在线
			if (null != outAddition) {
				simulatorInfo = outAddition.getSimulatorInfo();
			}
			else
			{
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
				{
					inputAdditionV.setEquipmentId(simulatorInfo.getEquipmentId());
					inputAdditionV.setStat((byte)0);
					String key= (String) currentUserInfoAdditionServiceImpl.insert(inputAdditionV);
					if(null==key||"".equals(key))
						strback = BackString(false,99,"通讯凭证错误");
					else
					{
						simulatorInfo.setStat((short)1);
						simulatorInfoService.update(simulatorInfo);
						strback = BackString(true,0,"模拟器登入成功!编号："+simulatorInfo.getEquipmentCode());
						//发送信息到该台模拟器。表明当前有用户登陆进来。
						Gson gson = new Gson();
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
