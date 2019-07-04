package com.multisocketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
//�������˳���
public class Ex91_4_MutiSocketServer {
	static int num = 1; //�ͻ��˼���
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket client = null;
		while (true) {
			try {
				serverSocket = new ServerSocket(4444); //�󶨶˿�4444�����ͻ�����
			} catch (Exception e) {
				System.out.println("Error:" + e); //��Ļ��ӡ������Ϣ
				System.exit(-1);
			} try {
				//ʹ��accept()�����ȴ��ͻ�����������ʱ����һ��socket����
				client = serverSocket.accept();
			} catch (Exception e) {
				System.out.println("��������ʧ��");
				System.exit(-1);
			}
			System.out.println("Client[" + Ex91_4_MutiSocketServer.num + "]��½������������");
			ServerThread st = new ServerThread(client); //����ʵ�������߳��ࣩ
			Thread t = new Thread(st);
			t.start(); //���������߳�
			//�������ͻ����󣬾ݿͻ��������������̲߳�����
			try {
				serverSocket.close(); //�ر�ServerSocket
			} catch(IOException e) {
				System.out.println("�ر�ʧ��");
			}
			num++; //���ӿͻ�����
		}
	}

}
