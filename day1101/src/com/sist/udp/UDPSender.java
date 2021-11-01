package com.sist.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender {
	public static void main(String[] args) {
		
		String ip= args[0];
		String msg = args[1];
		
		
		
		//주는 쪽이든 받는 쪽이든 DatagramSocket이 필요함
		//DatagramSocket(int port): 약속된 포트 번호
		
		try {
			
			DatagramSocket socket = new DatagramSocket(9002); //아직까지 서로 데이터 주고받기 ㄴㄴ
			
		

			//목적지의 주소 ip를 갖고 InetAddress객체 생성
			InetAddress addr= InetAddress.getByName(ip);
			
			//전송하고자하는 문자열 msg를 byte의배열로 만들긔
			byte []data = msg.getBytes();
			
			//udp방삭의 데이터전송단위 패킷을 생성함
			DatagramPacket packet = new DatagramPacket(data, data.length,addr,9002);
		
			
			//datagramSocket의 send메소드를 통해 데이터를 전송함
			socket.send(packet);
			
			//소켓을 닫아줌
			socket.close();
			
		}catch(Exception e) {
			System.out.println("예외발생 : "+e.getMessage());
		}
		
	}
}
