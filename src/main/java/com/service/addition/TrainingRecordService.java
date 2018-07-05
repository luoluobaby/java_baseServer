package com.service.addition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.xml.resolver.helpers.Debug;
import org.springframework.stereotype.Service;

import com.exception.constraint.ValueIllegalException;
import com.model.po.TrainingRecords;
import com.service.model.impl.TrainingRecordsServiceImpl;
import com.util.MyDebug;
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
		boolean b = additionService.CheckPasswdByMySelf(userCode, password);
		if (true == b) {
			String path  = CaculatePath(simulatorId);
			//图片保存名字
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String dateString = formatter.format(currentTime);
			String filaname =dateString+".png";
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
		boolean b = additionService.CheckPasswdByMySelf(userCode, password);
		if (true == b) {
			/**
			 *检测一下是否已经上传过记录
			 */
			TrainingRecords record = trainingRecordsServiceImpl.queryByKey(simulatorId);
			if (null == record) {
				record = new TrainingRecords(simulatorId , content, CaculatePath(simulatorId));
			    trainingRecordsServiceImpl.insert(record);
			}
			else{
				record.setContent(content);
				trainingRecordsServiceImpl.update(record);
			}
			TrainingRecords tempRecord = trainingRecordsServiceImpl.queryByKey(simulatorId);
			b = (null!=tempRecord && tempRecord.getContent().equals(record.getContent()));
			if (true == b) {
				//删除当前在训练的记录
				additionService.deleteUserAndReleaseSimulator(simulatorId);
			}
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
			 String images = GetImages(simulatorId) ;
			 if (null==images) {
				 new ValueIllegalException("图片上传不成功");
				 str = "0,2,训练内容未上传";
			 }
			 else
				 str="1,D,"+recordsV.getContent()+","+images;			 
		 }		
		return null ;
	}
	
	private String GetImages( String simulatorId )
	{
		String back=null;
		String path = CaculatePath(simulatorId);
		if (null == path || "".equals(path)) {
			return back ;
		}
		File pathFile = new File(path);
		File[] fileList = pathFile.listFiles();
		if (null== fileList || 3>fileList.length) {
			return back ;
		}
		back=ImageUrl(simulatorId, fileList[0].getName())+",";
		back+=ImageUrl(simulatorId, fileList[fileList.length-1].getName())+",";
		List<String> tempList = new LinkedList<String>();
		for( int i=1 ;i<fileList.length-1;i++ )
		{
			tempList.add(ImageUrl(simulatorId , fileList[i].getName()));
		}
		return back+=tempList.toString();
	}
	
	/**
	 * 图片的url
	 * @param imageName
	 * @return
	 */
	private String ImageUrl(String simulatorId , String imageName )
	{
		return  simulatorId+"/"+imageName;
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
		File file = new File(pathstr+filename);		
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
		int index = simulateId.indexOf('_');
		if ( 1 >= index) {
			new ValueIllegalException("上传路径不合法");
			return "";
		}
		String train_Unit_code = simulateId.substring(0 , index-1);
		String realSimulateId  = simulateId.substring(index+1);
		int caculen = realSimulateId.length();
		String strUserId="";
		if (caculen < 5) {
			int templen = 5-caculen;
			for( int i=0;i<templen;i++ )
				strUserId+="0";
		}
		strUserId += realSimulateId ;
		String path = "/Dataroot/TrainRecords/";
		if (true == MyDebug.currIsDebug) {
			path = "F://SaveRecords/";
		}			
		path+=train_Unit_code;
		int len = strUserId.length();
		path+="/"+strUserId.substring(len-2,len-1)+"/"+strUserId.substring(len-3,len-2)+"/"+strUserId.substring(len-4,len-3)+"/"+strUserId.substring(len-5,len-4)+"/";
		path+=(realSimulateId+"/");
		return path ;
	}
}
