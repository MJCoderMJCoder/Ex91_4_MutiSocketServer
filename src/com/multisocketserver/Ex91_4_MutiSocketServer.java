package com.multisocketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
//服务器端程序
public class Ex91_4_MutiSocketServer {
	static int num = 1; //客户端计数
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket client = null;
		while (true) {
			try {
				serverSocket = new ServerSocket(4444); //绑定端口4444监听客户请求
			} catch (Exception e) {
				System.out.println("Error:" + e); //屏幕打印出错信息
				System.exit(-1);
			} try {
				//使用accept()阻塞等待客户请求，请求到来时产生一个socket对象
				client = serverSocket.accept();
			} catch (Exception e) {
				System.out.println("接受请求失败");
				System.exit(-1);
			}
			System.out.println("Client[" + Ex91_4_MutiSocketServer.num + "]登陆······");
			ServerThread st = new ServerThread(client); //必须实例化（线程类）
			Thread t = new Thread(st);
			t.start(); //用于启动线程
			//监听到客户请求，据客户计数创建服务线程并启动
			try {
				serverSocket.close(); //关闭ServerSocket
			} catch(IOException e) {
				System.out.println("关闭失败");
			}
			num++; //增加客户计数
		}
	}

}
