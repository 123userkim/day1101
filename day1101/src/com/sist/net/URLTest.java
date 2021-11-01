package com.sist.net;

import java.io.InputStream;
import java.net.URL;

public class URLTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//인터넷상의 문서객체를 생성함
			URL url= new URL("https://www.hanbit.co.kr");
			
			//그 문서의 내용을 읽어들이기 위해서 스트림을 생성함
			InputStream is =url.openStream();
			
			//한번에 읽어들일 byte형의 배열을 만듦
			byte []data = new byte[100];
			
			//스트림을 통해 읽어 문자열을 다 모아놓고, 한번에 출력하기 위한 변수를 만들기
			String str = "";
			
			//스트림이 끝이 아닐때 까지 100바이트씩 읽음
			while(is.read(data) != -1 ) { //읽어들일게 계속 있으면 반복문 돌아감
				//읽어들인 byte배열의 데이터를 문자열로 만들어 누적
				str+= new String(data,"utf-8");
			}
			System.out.println(str);
			is.close();
			
			//스트림을 닫아줘야함
		}catch(Exception e){
			System.out.println("예외발생 : "+e.getMessage());
		}
	}

}
