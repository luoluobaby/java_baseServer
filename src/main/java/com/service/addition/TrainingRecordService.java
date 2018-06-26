package com.service.addition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.model.po.TrainingRecords;
import com.service.model.impl.TrainingRecordsServiceImpl;
import com.util.StringUtils;

@Service("trainingRecordService")
public class TrainingRecordService {
	
	@Resource
	private TrainingRecordsServiceImpl trainingRecordsServiceImpl ;
	
	@Resource
	private AdditionService additionService ;
	
	/**
	 * 查找当前流水号，并且保存训练图片
	 * @param simulatorId训练流水号
	 * @param imageData 保存的图片
	 * @return
	 */
	public boolean SaveImage(String userCode ,String password, String simulatorId , byte[] imageData)
	{
		boolean b = additionService.CheckPasswd(userCode, password);
		if (true == b) {
			String path  = CaculatePath(simulatorId);
			String filaname =""+System.currentTimeMillis()+".png";
			SaveOneImage(path,filaname,imageData);
		}		
		return b;
	}
	
    /**
     * 保存训练记录
     * @param simulatorId 训练流水号
     * @param content保存的训练内容
     * @return
     */
	public boolean SaveTrainingData(String userCode ,String password,String simulatorId , String content)
	{
		boolean b = additionService.CheckPasswd(userCode, password);
		if (true == b) {
			TrainingRecords recordv = new TrainingRecords(simulatorId , content, CaculatePath(simulatorId));
			String temp = (String)trainingRecordsServiceImpl.insert(recordv);
		}
		return b;
	}
	
	/**
	 * 获取训练内容
	 * @param simulatorId需要获取内容的训练流水号
	 * @return把训练内容转换成的字符串
	 */
	public String GetTrainRecord( String simulatorId )
	{
		 String str= "";
		 TrainingRecords recordsV = trainingRecordsServiceImpl.queryByKey(simulatorId);
		 if (null == recordsV) { //记录没有上传
			 str = "0,2,训练内容未上传";
		 }
		 else
		 {
			 str="1,D,"+recordsV.getContent();
		 }
		
		return null ;
	}
	
	/**
	 * 保存记录文件
	 */
	private void  SaveOneImage( String pathstr , String filename , byte[] imageData ) {
		
		if (StringUtils.IsNullOrEmpty(pathstr) || StringUtils.IsNullOrEmpty(filename) || null == imageData) 
		return ;
			//判断文件夹是否存在
		File path = new File(pathstr);
		if (!path.exists()) {
			path.mkdirs();
		}		
		File file = new File(path+filename);		
		try {
			if(!file.exists())
				file.createNewFile();
			OutputStream writer = new FileOutputStream(file);
			writer.write(imageData);
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
	/**
	 * 计算用户保存记录文件的路径
	 * @param simulateId
	 * @return
	 */
	private String CaculatePath(String simulateId)
	{
		//%08s 表示最小为8位，
		String strUserId = String.format("%08s", simulateId) ;
		String path = "F:/SaveRecords";			
		int len = strUserId.length();
		path+="/"+strUserId.substring(len-2,len-1)+"/"+strUserId.substring(len-3,len-2)+"/"+strUserId.substring(len-4,len-3)+"/"+strUserId.substring(len-5,len-4)+"/";
		return path ;
	}
}
