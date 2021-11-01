package com.sist.echo;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class TCPChatServer {
	
	public static void main(String[] args) {
		
		//Ŭ���̾�Ʈ�κ��� ���ŵ� �����͸� ��� ���� byte�� �迭�� ����
		byte []data =new byte[100];
		
		try {
			
			//severSocket�� ���� ������ ������
			ServerSocket sever = new ServerSocket(9003);
			
			while(true) { //���� ��� ���·� Ŭ���̾�Ʈ�� ������ ��ٸ�
				//Ŭ���̾�Ʈ�� ��û�� �����Ѵ�
				Socket socket = sever.accept();
				
				//socket�� ���ؼ� �����͸� �ְ���� ��Ʈ���� ����
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				
				//������ �� Ŭ���̾�Ʈ�� ����Ͽ� �����͸� �ְ� �ޱ� ���ؼ� �ݺ����� �̿���
				while(true) {
					//Ŭ���̾�Ʈ�κ��� �����͸� ������
					is.read(data);
					String msg = new String(data);
					System.out.println("���ŵ� ������: " + msg.trim());
					
					//Ŭ���̾�Ʈ�κ��� ���ŵ� �����͸� �״�� �޾Ƹ�
					os.write(data);
					
					//���� �����͸� ���ؼ� �迭�� ������ �����
					Arrays.fill(data, (byte)0);
				}
			}
			
		}catch(Exception e) {
			
		}
	}
}
