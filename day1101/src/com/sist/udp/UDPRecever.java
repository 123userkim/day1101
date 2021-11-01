package com.sist.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class UDPRecever {
	public static void main(String[] args) {
		//데이터를 주고받기 위해서 DatagramSocket객체를 생성함
		
		
		try {
			DatagramSocket socket = new DatagramSocket(9002);
			
			//보내오는 데이터를 담기위한 byte형의 배열을 만듦
			byte []data = new byte[100]; //한번에 100개씩 받기
			
			//sender가 보내오는 데이터를 받기 위하여 datagramPacket객체를 생성함
			DatagramPacket packet = new DatagramPacket(data,data.length);
			
			//sender가 보내오는 데이터를 계속 받기 위해서 무한반복
			while(true) {
					//datagramSocket의 receive메소드를 통해 받기
				socket.receive(packet);
				
				// 진짜로 받아진 데이터는 byte형의 배열인 (패킷을 만들 때 준 배열)data에 담겨져 있응ㅁ
				//이것을 String생성자를 이용해 문자열로 만들기
				String msg = new String(data);
				System.out.println("수신된 데이터: "+msg);
				
				//다음에 수신되는 데이터를 위해서 byte의 배열 data를 비워야함
				Arrays.fill(data, (byte)0);
			}
			
		}catch(Exception e) {
			System.out.println("예외발생 : "+e.getMessage());
		}
	}
}
