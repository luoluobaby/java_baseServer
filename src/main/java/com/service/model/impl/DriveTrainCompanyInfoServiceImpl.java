package com.service.model.impl;

import org.springframework.stereotype.Service;
import com.model.po.DriveTrainCompanyInfo;
import com.service.model.BaseServiceImpl;

@Service("DriveTrainCompanyInfoServiceImpl")
public class DriveTrainCompanyInfoServiceImpl extends BaseServiceImpl<DriveTrainCompanyInfo> {
	
	/**
	 * 查找驾校的名字和信息
	 * @param numId
	 * @return
	 */
	public DriveTrainCompanyInfo queryByNumId(String numId) {
		if(null==numId||"".equals(numId))
			return null;
		DriveTrainCompanyInfo addition=dao.get("from DriveTrainCompanyInfo d where "
				+ "d.numId = ?", new Object[]{numId});		
		return addition;
	}
	
	/**
	 * 根据驾校的名称查找驾校
	 * @param name
	 * @return
	 */
	public DriveTrainCompanyInfo queryByCompanyName(String name)
	{
		if (null == name || "".equals(name))
			return null;
		DriveTrainCompanyInfo info = dao.get("from DriveTrainCompanyInfo d where "
				+ "d.name = ?", new Object[]{name});
		return info ;
	}
}
