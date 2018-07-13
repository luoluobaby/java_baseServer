package com.webservice.impl;


import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.model.vo.CurrentUserInfoV;
import com.service.addition.AdditionService;
import com.service.addition.TrainingRecordService;
import com.webservice.DriveSimulatorController;
import com.service.util.ApplicationContextUtil;

@WebService(endpointInterface="com.webservice.DriveSimulatorController",serviceName="simulatorcontroller")
public class DriveSimulatorControllerImpl implements DriveSimulatorController{
	
	/**
	 * 
	 */
	private AdditionService additionService =null;
	
	private AdditionService getAddtionService(){
		if (null == additionService) {
			additionService =(AdditionService) ApplicationContextUtil.static_content.getBean(AdditionService.class);
		}
		return additionService;
	}
	
	
	/**
	 * 训练内容
	 */
//	private TrainingRecordService trainRecording=(new ClassPathXmlApplicationContext("springmvc.xml")).getBean(TrainingRecordService.class);
	private TrainingRecordService trainRecording=null;
	
	private TrainingRecordService getTrainingRecordService()
	{
		if (null == trainRecording) {
			trainRecording=(TrainingRecordService)ApplicationContextUtil.static_content.getBean(TrainingRecordService.class);
		}
		return trainRecording;
	}
	
	/*日志*/
	protected Logger logger=LoggerFactory.getLogger(DriveSimulatorControllerImpl.class);
	
	/**
	 *  将传入的数据放入对象中，然后登陆。
	 */
	@Override
	public  @WebResult(targetNamespace="http://impl.webservice.com/") String CheckInBy(
			@WebParam(name="userCode",targetNamespace="http://impl.webservice.com/")String userCode,
			@WebParam(name="password",targetNamespace="http://impl.webservice.com/")String password,
			@WebParam(name="TRAIN_UNIT_CODE",targetNamespace="http://impl.webservice.com/")String train_unit_code,
			@WebParam(name="pupilNo",targetNamespace="http://impl.webservice.com/")String pupino,
			@WebParam(name="pupilName",targetNamespace="http://impl.webservice.com/")String pupilName,
			@WebParam(name="cardNo",targetNamespace="http://impl.webservice.com/")String cardNo, 
			@WebParam(name="simulatorId",targetNamespace="http://impl.webservice.com/")String simulatorId, 
			@WebParam(name="EQUIPMENT_TYPE",targetNamespace="http://impl.webservice.com/")String equipment_type, 
			@WebParam(name="time",targetNamespace="http://impl.webservice.com/")String time) {
		if (null != logger) {
			logger.info("userCode="+userCode+",password="+password+",TRAIN_UNIT_CODE="+train_unit_code+",pupilNo="+pupino
					+",pupilName="+pupilName+",cardNo="+cardNo+",simulatorId="+simulatorId+",EQUIPMENT_TYPE="+equipment_type+",time="+time);
		}
		// 创建一个用户数据的对象
		CurrentUserInfoV inputAdditionV= new CurrentUserInfoV(simulatorId, train_unit_code, pupino, pupilName, cardNo, equipment_type,time);
		return getAddtionService().login(inputAdditionV , userCode,password);
	}
	/**
	 * 
	 */
	@Override
	public @WebResult(targetNamespace="http://impl.webservice.com/") String CheckOutBy(
			@WebParam(name="userCode",targetNamespace="http://impl.webservice.com/")String userCode,
			@WebParam(name="password",targetNamespace="http://impl.webservice.com/")String password, 
			@WebParam(name="TRAIN_UNIT_CODE",targetNamespace="http://impl.webservice.com/")String Train_Unit_Code, 
			@WebParam(name="pupilNo",targetNamespace="http://impl.webservice.com/")String pupino, 
			@WebParam(name="pupilName",targetNamespace="http://impl.webservice.com/")String pupilName,
			@WebParam(name="cardNo",targetNamespace="http://impl.webservice.com/")String cardNo,
			@WebParam(name="simulatorId",targetNamespace="http://impl.webservice.com/")String simulatorId){
		// TODO Auto-generated method stub
		//学员签退
		if (null != logger) {
			logger.info("userCode="+userCode+",password="+password+",TRAIN_UNIT_CODE="+Train_Unit_Code+",pupilNo="+pupino
					+",pupilName="+pupilName+",cardNo="+cardNo+",simulatorId="+simulatorId);
		}
		//流水号等于驾校编号+训练流水号 
		simulatorId = Train_Unit_Code+"_"+simulatorId;
		return getAddtionService().LogOut(simulatorId, userCode, password);
	}

	@Override
	public @WebResult(targetNamespace="http://impl.webservice.com/") String GetTrainRecord(
			@WebParam(name="userCode",targetNamespace="http://impl.webservice.com/")String userCode,
			@WebParam(name="password",targetNamespace="http://impl.webservice.com/")String password,
			@WebParam(name="simulatorId",targetNamespace="http://impl.webservice.com/")String simulatorId) {
		// TODO Auto-generated method stub
		if (null != logger) {
			logger.info("userCode="+userCode+",password="+password+",simulatorId="+simulatorId);
		}
		String str ="";
		if (false == getAddtionService().CheckPasswd(userCode, password)){
			//验证失败
			str="0,99,通讯凭证错误";
		}
		else
		{
			str = getTrainingRecordService().GetTrainRecord(simulatorId);
		}
		return str;
	}
	
	
}
