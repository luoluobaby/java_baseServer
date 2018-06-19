//package com.service.addition;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.model.po.SimulatorInfo;
//import com.model.vo.CurrentUserInfoV;
//
//@Service("updateSimulatorStatService")
//public class UpdateSimulatorStatService {
//	@Autowired
//	private SimulatorInfoService simulatorInfoService;
//	@Autowired
//	private CurrentUserInfoService currentUserInfoService;
//	@Autowired
//	private QueryRecordAdditionService queryRecordAdditionService;
//	
//	/**
//	 * 检测断开连接机器
//	 */
//	public void detectDisconnect()
//	{
//		List<SimulatorInfo> simulatorInfoAdditions=simulatorInfoService.
//				getSimulatorInfoAdditionServiceImpl().getDao().find
//				("select s from SimulatorInfoAddition as s,QueryRecordAddition as q where"
//			+ "  ((s.stat=? OR s.stat=?) and s.equipmentId=q.simulatorInfoAddition.equipmentId AND q.time<=?) or"
//			+ "(select count(*) as num from QueryRecordAddition as qq where qq.simulatorInfoAddition.equipmentId=s.equipmentId)=0",
//			new Object[]{(short)0,(short)1,new Date(new Date().getTime()-100000)});
//		
//		if(null==simulatorInfoAdditions||0>=simulatorInfoAdditions.size())
//			return;
//		for(int i=0;i<simulatorInfoAdditions.size();i++)
//		{
//			SimulatorInfo infoV=new SimulatorInfo();
//			BeanUtils.copyProperties(simulatorInfoAdditions.get(i), infoV);
//			infoV.setTrainUnitCode(simulatorInfoAdditions.get(i).getDriveTrainCompanyInfo().getTrainUnitCode());
//			
//			infoV.setStat((short)2);
//			simulatorInfoService.update(infoV);
//			
//			CurrentUserInfoV userInfoAdditionV=currentUserInfoService.queryByEquipmentId(infoV.getEquipmentId());
//			if(null!=userInfoAdditionV)//删除currentUserInfo中记录
//				currentUserInfoService.delete(userInfoAdditionV.getSimulatorId());
//		}
//		
//	}
//	
//	/**
//	 * 检测重连机器
//	 */
//	public void detectReconnect() {
//		String hql="select s from SimulatorInfoAddition s,QueryRecordAddition q where s.stat=2 and"
//				+ " q.simulatorInfoAddition.equipmentId=s.equipmentId and q.time>=?";
//		List<SimulatorInfo> additions=simulatorInfoService.
//				getSimulatorInfoAdditionServiceImpl().getDao().find(hql,new Object[]{new Date(new Date().getTime()-11000)});
//		
//		if(null==additions||0>=additions.size())
//			return;
//		for(int i=0;i<additions.size();i++)
//		{
//			SimulatorInfoV infoV=new SimulatorInfoV();
//			BeanUtils.copyProperties(additions.get(i), infoV);
//			infoV.setTrainUnitCode(additions.get(i).getDriveTrainCompanyInfo().getTrainUnitCode());
//			
//			infoV.setStat((short)0);
//			simulatorInfoService.update(infoV);
//		}
//	}
//}
