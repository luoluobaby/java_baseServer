package com.webservice;

import javax.jws.WebService;

@WebService
public interface UpLoadController {
	
	/**
	 * 
	 * @param 上传的训练内容
	 * @return
	 */
	public boolean UpLoadTrainContent(String username , String password, String simulatorId ,String trainContent);
	
	/**
	 * 上传训练图片
	 * @param imageContent
	 * @return
	 */
	public boolean UpLoadImage(String username , String password, String simulatorId ,byte[] imageContent);

	/**
	 * 注册一台机器
	 * @param chipId 机器的电路板号
	 * @param SimulatorNum 机器的编号
	 * @param CompanyName 机器的驾校名字
	 * @param Equipment_Type 机器的训练类型
	 * @return
	 */
	public boolean RigisterSimulator(String chipId ,String SimulatorNum , String CompanyName , String Equipment_Type );
}
