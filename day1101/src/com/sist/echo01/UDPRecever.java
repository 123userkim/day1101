package com.sist.echo01;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class UDPRecever {
	
	public static void main(String[] args) {
	
		InputStream is;
		OutputStream os;
		
	
		
		try {
			
			
			DatagramSocket socket = new DatagramSocket(9002);
			byte []data =new byte[100];
			
			DatagramPacket packet = new DatagramPacket(data,data.length);
			
			while(true) {
				
			socket.receive(packet);
			
			String msg = new String(data);
			System.out.println("수신된 데이터: "+msg.trim());
			
						 
			Arrays.fill(data, (byte)0);
			}
			
			
		}catch(Exception e) {
			
		}
	}
}
