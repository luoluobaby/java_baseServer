package com.webservice.impl;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.model.vo.CurrentUserInfoV;
import com.service.addition.AdditionService;
import com.service.addition.TrainingRecordService;
import com.webservice.DriveSimulatorController;

@WebService(endpointInterface="com.webservice.DriveSimulatorController",serviceName="simulatorcontroller")
public class DriveSimulatorControllerImpl implements DriveSimulatorController{

	/**
	 * 
	 */
	private AdditionService additionService =(new ClassPathXmlApplicationContext("springmvc.xml")).getBean(AdditionService.class);

	/**
	 * 训练内容
	 */
	private TrainingRecordService trainRecording=(new ClassPathXmlApplicationContext("springmvc.xml")).getBean(TrainingRecordService.class);
	/**
	 *  将传入的数据放入对象中，然后登陆。
	 */
	@Override
	public String CheckInBy(
			@WebParam(name="userCode")String userCode, @WebParam(name="password")String password,
			@WebParam(name="train_unit_code")String train_unit_code, @WebParam(name="pupino")String pupino,
			@WebParam(name="pupilName")String pupilName,@WebParam(name="cardNo")String cardNo, 
			@WebParam(name="simulatorId")String simulatorId, @WebParam(name="equipment_type")String equipment_type, 
			@WebParam(name="time")String time) {
		// 创建一个用户数据的对象	
		CurrentUserInfoV inputAdditionV= new CurrentUserInfoV(simulatorId, train_unit_code, pupino, pupilName, cardNo, equipment_type,time);
		return additionService.login(inputAdditionV , userCode,password);
	}
	/**
	 * 
	 */
	@Override
	public String CheckOutBy(@WebParam(name="userCode")String userCode, @WebParam(name="password")String password, 
			@WebParam(name="Train_Unit_Code")String Train_Unit_Code, @WebParam(name="pupino")String pupino, 
			@WebParam(name="pupilName")String pupilName,@WebParam(name="cardNo")String cardNo,
			@WebParam(name="simulatorId")String simulatorId){
		// TODO Auto-generated method stub
		//学员签退
		//流水号等于驾校编号+训练流水号 
		simulatorId = Train_Unit_Code+"_"+simulatorId;
		return additionService.LogOut(simulatorId, userCode, password);
	}

	@Override
	public String GetTrainRecord(@WebParam(name="userCode")String userCode, @WebParam(name="password")String password,
			@WebParam(name="simulatorId")String simulatorId) {
		// TODO Auto-generated method stub
		String str ="";
		if (false == additionService.CheckPasswd(userCode, password)){
			//验证失败
			str="0,99,通讯凭证错误";
		}
		else
		{
			str = trainRecording.GetTrainRecord(simulatorId);
		}
		return str;
	}
	
	
}
