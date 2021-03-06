package com.whl.tomcat.client.server.demo1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.bjsxt.util.CloseUtil;

/**
 * ������������������
 * 
 * 1������
 * 2����Ӧ
 * @author Administrator
 *
 */
public class Server7 {
	private ServerSocket server;
	public static final String CRLF="\r\n";
	public static final String BLANK=" ";
	
	private boolean isShutDown= false;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		Server7 server = new Server7();
		server.start();
		
		
	}
	/**
	 * ��������
	 */
	public void start(){		
		start(8888);
	
	}
	/**
	 * ָ���˿ڵ���������
	 */
	public void start(int port){		
		try {
			server = new ServerSocket(port);
			this.receive();
		} catch (IOException e) {
			//e.printStackTrace();
			stop();
		}
	
	}
	/**
	 * ���տͻ���
	 */
	private void receive(){
		try {
			while(!isShutDown){
				new Thread(new Dispatcher(server.accept())).start();
			}
		} catch (IOException e) {
			//e.printStackTrace();
			stop();
		}
		
	}
	
	/**
	 * ֹͣ������
	 */
	public void stop(){
		isShutDown=true;
		CloseUtil.closeSocket(server);
	}
	
	
}
