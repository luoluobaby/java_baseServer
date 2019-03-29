package com.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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

	/*日志*/
	protected Logger logger=LoggerFactory.getLogger(Machines.class);

	/**
	 * 统一控制所有机器的信息管理,有哪个机器链接进来了！
	 * @param machine
	 * @param session
	 * @throws IOException
	 */
	@OnOpen
	public void OnOpen(@PathParam("machine") String machine,Session session) throws IOException
	{

	}
	@OnError
	public void OnError(Session session,Throwable error) {

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

	}

	/**
	 * 跟session发送消息
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException{
    }
}
