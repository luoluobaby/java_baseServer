package com.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model.po.CurrentUserInfo;
import com.model.vo.CurrentUserInfoV;
import com.service.addition.AdditionService;
import com.service.addition.SimulatorInfoService;
import com.service.util.ApplicationContextUtil;
import com.util.StringUtils;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.sound.midi.MidiDevice.Info;
import javax.websocket.OnClose;

@ServerEndpoint("/machine/{machine}")
public class Machines {
	
	private static CopyOnWriteArraySet<Machines> user=new CopyOnWriteArraySet<Machines>();
	public static CopyOnWriteArraySet<Machines> getHeartSet() {
		return user;
	}
	/**
	 * 机器序列号
	 */
	private String machineChip ;
	
	/**
	 * 发送消息的session
	 * 
	 */
	private Session session ;
	
	/**
	 * 获取发送的session
	 * @return
	 */
	public Session getSession(){
		return this.session;
	}
	
	/**
	 * 查询机器是否存在的类
	 */
//	private static SimulatorInfoService simulatorInfoService=(new ClassPathXmlApplicationContext("springmvc.xml")).getBean(SimulatorInfoService.class);
	private static SimulatorInfoService _simulatorInfoService=null;
	private static SimulatorInfoService getSimulatorInfoService()
	{
		if (null == _simulatorInfoService) {
			_simulatorInfoService =(SimulatorInfoService) ApplicationContextUtil.static_content.getBean(SimulatorInfoService.class);
		}
		return _simulatorInfoService;
	}
	/**
	 * 在线用户查询类
	 */
//	private static AdditionService addtionService = (new ClassPathXmlApplicationContext("springmvc.xml")).getBean(AdditionService.class);;
	private static AdditionService _addtionService=null;
	private AdditionService getAddtionService(){
		if (null == _addtionService) {
			_addtionService =(AdditionService) ApplicationContextUtil.static_content.getBean(AdditionService.class);
		}
		return _addtionService;
	}
	/**
	 * 统一控制所有机器的信息管理,有哪个机器链接进来了！
	 * @param machine
	 * @param session
	 * @throws IOException
	 */
	@OnOpen
	public void OnOpen(@PathParam("machine") String machine,Session session) throws IOException
	{
		this.session = session; 
		this.machineChip = machine;
		
		//先判断当前是否是一个有效的机器序列号
		if (true == StringUtils.IsNullOrEmpty(machineChip) || false == getSimulatorInfoService().CheckIfMachineExist(machine) ) {
			return ;
		}
		System.out.println("open "+machine);
		IsExistAndRemoveCurr(this);
		//说明当前机器已经上线，等待用户打卡签到
		Machines.getHeartSet().add(this);
		//开机判断当前是否还有用户占用该机器，则应该让用户继续占用
		CurrentUserInfo info= null; 
		if (null != getAddtionService()) {
			info = getAddtionService().queryByEquipmentId(machineChip);
		}
		if (null != info) 
		{
			CurrentUserInfoV infoV = new CurrentUserInfoV(info);
			getAddtionService().SendLoginInMessage(infoV, this);
			getSimulatorInfoService().SetMachineBusy(this.machineChip);
		}
		else
			getSimulatorInfoService().SetMachineOnLine(this.machineChip);
	}
	@OnError
	public void OnError(Session session,Throwable error) {
		//断开
		System.out.println("错误断开");
	}
	
	 /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
	 * @throws IOException 
     */
	@OnMessage
	public void OnMessage(String message,Session session) throws IOException
	{
	}
	
	@OnClose
	public void OnClose(Session session) {
		//判断一下是否为异常退出
		
		//断开连接
		getSimulatorInfoService().SetMachineOffLine(this.machineChip);
		Machines.getHeartSet().remove(this);  //从set中删除
	}
	/**
	 * 判断当前是否存在，存在的话，则删除当前已经存在的值
	 * @param curr
	 * @return
	 */
	private static void IsExistAndRemoveCurr(Machines curr) {
		if (false ==Machines.getHeartSet().isEmpty()) {
			for (Machines machines : Machines.getHeartSet()) {
				if (machines.machineChip.equals(curr.machineChip)){
					Machines.getHeartSet().remove(machines);
					return;
				}
			}
		}
	}
	/**
	 * 根据机器序列号，查找机器列表
	 * @param machineChip
	 * @return
	 */
	public static Machines GetMachine( String machineChip )
	{
		Machines machines = null ;
		
		if ( false == Machines.getHeartSet().isEmpty()) {
			for (Machines m : Machines.getHeartSet()) {
				if (m.machineChip.equals(machineChip)) {
					machines = m;
					break;
				}
			}
		}
		return machines;
	}
	
	/**
	 * 跟session发送消息
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }
}
