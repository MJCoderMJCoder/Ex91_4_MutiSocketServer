package com.multisocketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ServerThread implements Runnable {
	private Socket client; //��������
	public ServerThread(Socket client) {
		this.client = client; //��ʼ��client����
	}
	public void run() { //�߳�����
		try { //ʵ�����ݴ���
			//��socket����õ�����������������Ӧ��BufferedReader����
			BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			//��socket����õ��������������PrintWriter����
			PrintWriter os = new PrintWriter(client.getOutputStream());
			//��ϵͳ��׼�����豸����BufferdReader����
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Client��" + is.readLine()); //�ӱ�׼����ϴ�ӡ�ӿͻ��˶�����ַ���
			String inputString = sin.readLine(); //�ӱ�׼�������һ�ַ���
			
			//while:���������ַ���Ϊquit�����˳�ѭ��
			while (inputString != null && !inputString.trim().equals("quit")) {
				os.println(inputString); //��ͻ���������ַ���
				os.flush(); //ˢ���������ʹ��client�����յ����ַ���
				System.out.println("Server���͵���ϢΪ��" + inputString);
				//�ӱ�׼����ϴ�ӡ�ӿͻ��˶�����ַ���
				System.out.println("Client���͵���ϢΪ��" + is.readLine());
				inputString = sin.readLine(); //��ϵͳ��׼�������һ�ַ���
			} //����ѭ��
			os.close(); //�ر�socket�����
			is.close(); //�ر�socket������
			client.close(); //�ر�socket
			System.out.println("�������");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
