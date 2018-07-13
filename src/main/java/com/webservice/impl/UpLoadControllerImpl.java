package com.webservice.impl;

import javax.jws.WebService;

import org.apache.commons.lang.ObjectUtils.Null;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.addition.SimulatorInfoService;
import com.service.addition.TrainingRecordService;
import com.service.util.ApplicationContextUtil;
import com.webservice.UpLoadController;

@WebService(endpointInterface="com.webservice.UpLoadController",serviceName="uploadcontroller")
public class UpLoadControllerImpl implements UpLoadController{

	/**
	 * 训练记录管理
	 */
	private TrainingRecordService trainingRecordService =null;
	
	private TrainingRecordService getTrainingRecordService()
	{
		if (null == trainingRecordService) {
			trainingRecordService=(TrainingRecordService)ApplicationContextUtil.static_content.getBean(TrainingRecordService.class);
		}
		return trainingRecordService;
	}
	
	private SimulatorInfoService simulatorInfoService = null ;
	
	private SimulatorInfoService getSimulatorInfoService()
	{
		if (null == simulatorInfoService) {
			simulatorInfoService = (SimulatorInfoService)ApplicationContextUtil.static_content.getBean(SimulatorInfoService.class);
		}
		return simulatorInfoService;
	}
	
	@Override
	public boolean UpLoadTrainContent(String username, String password, String simulatorId, String trainContent){
		// TODO Auto-generated method stub		
		return getTrainingRecordService().SaveTrainingData(username, password, simulatorId, trainContent);
	}

	@Override
	public boolean UpLoadImage(String username, String password, String simulatorId, byte[] imageContent, String name){
		// TODO Auto-generated method stub
		return getTrainingRecordService().SaveImage(username, password, simulatorId, imageContent , name);
	}

	@Override
	public boolean RigisterSimulator(String chipId, String SimulatorNum, String companyName,
			String Equipment_Type) {
		return getSimulatorInfoService().RigisterMachine(chipId, SimulatorNum, companyName, Equipment_Type);
	}

	@Override
	public boolean UpLoadTwoImage(String username, String password, String simulatorId, byte[] imageContent,
			String name, byte[] imageContent1, String name1) {
		boolean b = getTrainingRecordService().SaveImage(username, password, simulatorId, imageContent, name);
		b=getTrainingRecordService().SaveImage(username, password, simulatorId, imageContent1, name1);
		// TODO 自动生成的方法存根
		return b;
	}

}
