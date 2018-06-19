package com.service.model.impl;

import org.springframework.stereotype.Service;
import com.model.po.DriveTrainCompanyInfo;
import com.service.model.BaseServiceImpl;

@Service("DriveTrainCompanyInfoServiceImpl")
public class DriveTrainCompanyInfoServiceImpl extends BaseServiceImpl<DriveTrainCompanyInfo> {
	
	public DriveTrainCompanyInfo queryByNumId(String numId) {
		if(null==numId||"".equals(numId))
			return null;
		DriveTrainCompanyInfo addition=dao.get("from DriveTrainCompanyInfo d where "
				+ "d.numId = ?", new Object[]{numId});		
		return addition;
	}
	
}