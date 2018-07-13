package com.webservice;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace="http://impl.webservice.com/")
public interface  DriveSimulatorController {
	/**
	 * 学员签到函数
	 * @param userCode用户Code
	 * @param password 密码
	 * @param Train_Unit_Code驾校编号
	 * @param pupino学员身份证号
	 * @param pupilName学员姓名
	 * @param cardNo学员卡号
	 * @param simulatorId训练流水号
	 * @param Equipment_Type训练类型： 0手动挡；1自动挡
	 * @param time训练时长
	 * @return1 1,0,模拟器登入成功!编号：" + 模拟器号;
	 * 0,3,模拟器故障"; "0,2,模拟器状态为非空闲";"0,1,查询无此模拟器";"0,99,通讯凭证错误";
	 *
	 */
	public @WebResult(targetNamespace="http://impl.webservice.com/")  String CheckInBy(@WebParam(name="userCode",targetNamespace="http://impl.webservice.com/")String userCode,
			@WebParam(name="password",targetNamespace="http://impl.webservice.com/")String password,
			@WebParam(name="TRAIN_UNIT_CODE",targetNamespace="http://impl.webservice.com/")String train_unit_code,
			@WebParam(name="pupilNo",targetNamespace="http://impl.webservice.com/")String pupino,
			@WebParam(name="pupilName",targetNamespace="http://impl.webservice.com/")String pupilName,
			@WebParam(name="cardNo",targetNamespace="http://impl.webservice.com/")String cardNo, 
			@WebParam(name="simulatorId",targetNamespace="http://impl.webservice.com/")String simulatorId, 
			@WebParam(name="EQUIPMENT_TYPE",targetNamespace="http://impl.webservice.com/")String equipment_type, 
			@WebParam(name="time",targetNamespace="http://impl.webservice.com/")String time);
	
	/**
	 * 
	 * @param userCode
	 * @param password
	 * @param Train_Unit_Code
	 * @param pupino
	 * @param pupilName
	 * @param cardNo
	 * @param simulatorId
	 * @return 1,0,模拟器登退成功";0,3,模拟器故障";"0,2,模拟器并未处于使用状态";"0,1,模拟器编号或流水号出错";"0,99,通讯凭证错误";
	 */
	public @WebResult(targetNamespace="http://impl.webservice.com/")  String CheckOutBy(
			@WebParam(name="userCode",targetNamespace="http://impl.webservice.com/")String userCode,
			@WebParam(name="password",targetNamespace="http://impl.webservice.com/")String password, 
			@WebParam(name="TRAIN_UNIT_CODE",targetNamespace="http://impl.webservice.com/")String Train_Unit_Code, 
			@WebParam(name="pupilNo",targetNamespace="http://impl.webservice.com/")String pupino, 
			@WebParam(name="pupilName",targetNamespace="http://impl.webservice.com/")String pupilName,
			@WebParam(name="cardNo",targetNamespace="http://impl.webservice.com/")String cardNo,
			@WebParam(name="simulatorId",targetNamespace="http://impl.webservice.com/")String simulatorId);
	
	/**
	 * 获取培训内容
	 * @param userCode
	 * @param password
	 * @param simulatorId训练流水号
	 * @return“1,模拟器硬盘号,训练内容,签到照片URL，签退照片URL,过程图片URL的list”;"0,2,训练内容未上传";"0,1,流水号出错";"0,99,通讯凭证错误";
	 */
	public @WebResult(targetNamespace="http://impl.webservice.com/")  String GetTrainRecord(
			@WebParam(name="userCode",targetNamespace="http://impl.webservice.com/")String userCode,
			@WebParam(name="password",targetNamespace="http://impl.webservice.com/")String password,
			@WebParam(name="simulatorId",targetNamespace="http://impl.webservice.com/")String simulatorId);
}
