package com.sist.net;

import java.net.Inet4Address;
import java.net.InetAddress;

public class InsertAddressTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try { 
		InetAddress []list =InetAddress.getAllByName("www.naver.com");
		 for(InetAddress naver:list) {
			 System.out.println(naver);
		 }
		}catch(Exception e) {
			System.out.println("예외발생 : "+e.getMessage());
		}
	}

}
