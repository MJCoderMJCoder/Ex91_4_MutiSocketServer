package com.multisocketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ServerThread implements Runnable {
	private Socket client; //声明对象
	public ServerThread(Socket client) {
		this.client = client; //初始化client变量
	}
	public void run() { //线程主体
		try { //实现数据传输
			//由socket对象得到输入流，并构造相应的BufferedReader对象
			BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			//由socket对象得到输出流，并构造PrintWriter对象
			PrintWriter os = new PrintWriter(client.getOutputStream());
			//由系统标准输入设备构造BufferdReader对象
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Client：" + is.readLine()); //从标准输出上打印从客户端读入的字符串
			String inputString = sin.readLine(); //从标准输入读入一字符串
			
			//while:如果输入的字符串为quit，则退出循环
			while (inputString != null && !inputString.trim().equals("quit")) {
				os.println(inputString); //向客户端输出该字符串
				os.flush(); //刷新输出流，使得client马上收到该字符串
				System.out.println("Server发送的信息为：" + inputString);
				//从标准输出上打印从客户端读入的字符串
				System.out.println("Client发送的信息为：" + is.readLine());
				inputString = sin.readLine(); //从系统标准输入读入一字符串
			} //继续循环
			os.close(); //关闭socket输出流
			is.close(); //关闭socket输入流
			client.close(); //关闭socket
			System.out.println("聊天结束");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
