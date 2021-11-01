package com.sist.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender {
	public static void main(String[] args) {
		
		String ip= args[0];
		String msg = args[1];
		
		
		
		//�ִ� ���̵� �޴� ���̵� DatagramSocket�� �ʿ���
		//DatagramSocket(int port): ��ӵ� ��Ʈ ��ȣ
		
		try {
			
			DatagramSocket socket = new DatagramSocket(9002); //�������� ���� ������ �ְ�ޱ� ����
			
		

			//�������� �ּ� ip�� ���� InetAddress��ü ����
			InetAddress addr= InetAddress.getByName(ip);
			
			//�����ϰ����ϴ� ���ڿ� msg�� byte�ǹ迭�� �����
			byte []data = msg.getBytes();
			
			//udp����� ���������۴��� ��Ŷ�� ������
			DatagramPacket packet = new DatagramPacket(data, data.length,addr,9002);
		
			
			//datagramSocket�� send�޼ҵ带 ���� �����͸� ������
			socket.send(packet);
			
			//������ �ݾ���
			socket.close();
			
		}catch(Exception e) {
			System.out.println("���ܹ߻� : "+e.getMessage());
		}
		
	}
}
