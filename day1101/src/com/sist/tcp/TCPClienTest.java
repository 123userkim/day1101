package com.sist.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClienTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			//1.Ŭ���̾�Ʈ ������ ������ ����� ��û�ؾ��� 
			Socket socket = new Socket("127.0.0.1",9001);
			
			//2. ������ ���� �����͸� �ְ� ���� ��Ʈ���� ����
			InputStream is = socket.getInputStream();
			OutputStream os =socket.getOutputStream();
			
			//3.��Ʈ���� ���ؼ� �ְ�ޱ�
			//������ �������� 10���� ������ �о�鿩 ȭ�鿡 ���
			
			for(int i=1; i<=10;i++) {
				int n = is.read();
				System.out.println("�����κ��� ���ŵ� ������ : "+n);
				Thread.sleep(200);
			}
			
			//4.����ߴ� �ڿ��� �ݾ���
			is.close();
			os.close();
			socket.close();
			
		}catch(Exception e) {
			System.out.println("���ܹ߻� : "+e.getMessage());
		}
	}

}
