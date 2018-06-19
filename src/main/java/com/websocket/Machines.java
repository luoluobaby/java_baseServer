package com.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.addition.SimulatorInfoService;
import com.util.StringUtils;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
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
	 */
	private Session session ;
	
	/**
	 * 查询机器是否存在的类
	 */
	private static SimulatorInfoService simulatorInfoService=(new ClassPathXmlApplicationContext("springmvc.xml")).getBean(SimulatorInfoService.class);
	
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
		if (true == StringUtils.IsNullOrEmpty(machineChip) || false == simulatorInfoService.CheckIfMachineExist(machine) ) {
			
			return ;
		}
		IsExistAndRemoveCurr(this);
		//说明当前机器已经上线，等待用户打卡签到
		Machines.getHeartSet().add(this);
		simulatorInfoService.SetMachineOnLine(this.machineChip);
	}
	@OnError
	public void OnError(Session session,Throwable error) {
		System.out.println(error.getMessage());
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
		//客户端发送过来的消息
		//退出当前用户
		if ("loginout".equals(message)) {
			simulatorInfoService.SetMachineOnLine(this.machineChip);			
		}
	}
	
	@OnClose
	public void OnClose(Session session) {
		simulatorInfoService.SetMachineOffLine(this.machineChip);
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
				if (machines.machineChip.equals(curr.machineChip)) {
					Machines.getHeartSet().remove(machines);
					return;
				}
			}
		}
	}
}
