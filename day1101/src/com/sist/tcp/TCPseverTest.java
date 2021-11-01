package com.sist.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


public class TCPseverTest {

	public static void main(String[] args) {
		
		Random r = new Random();
		// TODO Auto-generated method stub
		try {
			
			//1.������Ĺ�� ������
			ServerSocket server = new ServerSocket(9001);
			
			//2.Ŭ���̾�Ʈ�������� ���Ѵ��
			while(true) {
				//3.Ŭ���̾��� ������ ������
				//�̸޼ҵ�� ������ ��û�� Ŭ���̾�Ʈ�� ����� �ϱ� ���� ���ϰ�ü�� ��ȯ��
				//�̷� ������ �����ͼ����̶�� ��
			Socket socket=server.accept(); 
			System.out.println("Ŭ���̾�Ʈ�� �����Ͽ����ϴ�.");
			
			//4.�����͸� �ְ� ���� ��Ʈ���� ������
			InputStream is =socket.getInputStream();
			OutputStream os= socket.getOutputStream();
			
			//5.��Ʈ���� ���� �����͸� �ְ� �ޱ�
			//��û�� Ŭ���̾�Ʈ���� 10���� ������ ������ �����
				for(int i=1;i<=10;i++) {
					//0���� 100������ ������ �����
					int n =r.nextInt(100);						
					System.out.println("������ ���� �ϳ��� ��������ϴ�."+n);
					
					//���ð� 0.1��
					Thread.sleep(100);
										
					//Ŭ���̾�Ʈ���� ����ϱ�
					os.write(n);
					
				}
				
				System.out.println("�����͸� �� �����½��ϴ�.");
				
				//6. ����ߴ� �ڿ��� �ݾ���
				is.close();
				os.close();
				socket.close();
			}
			
		}catch(Exception e) {
			System.out.println("���ܹ߻� : " +e.getMessage());
		}
	}

}
