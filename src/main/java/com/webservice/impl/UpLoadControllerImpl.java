package com.webservice.impl;

import javax.jws.WebService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.addition.TrainingRecordService;
import com.webservice.UpLoadController;

@WebService(endpointInterface="com.webservice.UpLoadController",serviceName="uploadcontroller")
public class UpLoadControllerImpl implements UpLoadController{

	/**
	 * 训练记录管理
	 */
	private TrainingRecordService trainingRecordService =(new ClassPathXmlApplicationContext("springmvc.xml")).getBean(TrainingRecordService.class);
	
	@Override
	public boolean UpLoadTrainContent(String username, String password, String simulatorId, String trainContent){
		// TODO Auto-generated method stub		
		return trainingRecordService.SaveTrainingData(username, password, simulatorId, trainContent);
	}

	@Override
	public boolean UpLoadImage(String username, String password, String simulatorId, byte[] imageContent){
		// TODO Auto-generated method stub
		return trainingRecordService.SaveImage(username, password, simulatorId, imageContent);
	}

}
