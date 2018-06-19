package com.websocket;

import java.io.IOException;

import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;

@ServerEndpoint("/helloworld")
public class Helloworld {
	
	@OnOpen
	public void OnOpen(@PathParam("machine") String machine,Session session) throws IOException
	{
		session.getBasicRemote().sendText("hello world");
	}
	@OnError
	public void OnError(Session session,Throwable error) {
		System.out.println("duankai,and Error="+error.getMessage());
	}
	
	@OnClose
	public void OnClose(Session session) {
		System.out.println("close");
	}
}
