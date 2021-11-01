package com.sist.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class UDPRecever {
	public static void main(String[] args) {
		//�����͸� �ְ�ޱ� ���ؼ� DatagramSocket��ü�� ������
		
		
		try {
			DatagramSocket socket = new DatagramSocket(9002);
			
			//�������� �����͸� ������� byte���� �迭�� ����
			byte []data = new byte[100]; //�ѹ��� 100���� �ޱ�
			
			//sender�� �������� �����͸� �ޱ� ���Ͽ� datagramPacket��ü�� ������
			DatagramPacket packet = new DatagramPacket(data,data.length);
			
			//sender�� �������� �����͸� ��� �ޱ� ���ؼ� ���ѹݺ�
			while(true) {
					//datagramSocket�� receive�޼ҵ带 ���� �ޱ�
				socket.receive(packet);
				
				// ��¥�� �޾��� �����ʹ� byte���� �迭�� (��Ŷ�� ���� �� �� �迭)data�� ����� ������
				//�̰��� String�����ڸ� �̿��� ���ڿ��� �����
				String msg = new String(data);
				System.out.println("���ŵ� ������: "+msg);
				
				//������ ���ŵǴ� �����͸� ���ؼ� byte�� �迭 data�� �������
				Arrays.fill(data, (byte)0);
			}
			
		}catch(Exception e) {
			System.out.println("���ܹ߻� : "+e.getMessage());
		}
	}
}
