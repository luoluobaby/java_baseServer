package com.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * websocket发送数据格式
 * @author Young
 *
 */
public class SocketSendTextFormat<T> {
	
	private int type;
	
	private T content;
	
	public SocketSendTextFormat( int type, T content) {
		this.type = type;
		this.content = content;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();
		return gson.toJson(this);
	}
	
	
}
