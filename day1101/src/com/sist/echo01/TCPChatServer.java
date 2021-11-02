package com.sist.echo01;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class TCPChatServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Ŭ���̾�Ʈ�� ���� ���ŵ� �����͸� ��� ���� byte�� �迭�� ������
		byte []data = new byte[100];
		
		try {
			//1. SeverSocket�� ���� ������ �����Ѵ�.
			ServerSocket server = new ServerSocket(9003);
			
			while(true) { //���Ѵ�� ���·� Ŭ���̾�Ʈ�� ������ ��ٸ���.
				
				//Ŭ���̾�Ʈ�� ��û�� �����Ѵ�.
				Socket socket = server.accept();
				
				//���ϰ�ü�� ���ؼ� �����͸� �ְ���� ��Ʈ���� �����մϴ�.
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				
				//������ �� Ŭ���̾�Ʈ�� ����Ͽ� �����͸� �ְ� �ޱ� ���Ͽ� �ݺ����� �̿��մϴ�.
				while(true) {
					//Ŭ���̾�Ʈ�� ���� �����͸� �����մϴ�.
					is.read(data);
					String msg = new String(data);
					System.out.println("���ŵ� ������:" + msg.trim());
					
					//Ŭ���̾�Ʈ�� ���� ���ŵ� �����͸� �״�� �޾Ƹ� �մϴ�.
					os.write(data);
					
					//������ ���ŵ� �����͸� ���Ͽ� �迭�� ������ ����ݴϴ�.
					Arrays.fill(data, (byte)0);
				}
				
			}
			
		}catch (Exception e) {
			
		}
	}
}
