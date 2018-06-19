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
}
