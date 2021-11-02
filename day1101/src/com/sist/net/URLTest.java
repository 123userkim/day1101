package com.sist.net;

import java.io.InputStream;
import java.net.URL;

public class URLTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			//인터넷상의 문서객체를 생성합니다.
			URL url = new URL("https://www.hanbit.co.kr/store/books/new_book_list.html");
			
			//그 문서의 내용을 읽어들이기 위하여 스트림을 생성합니다.
			InputStream is = url.openStream();
			
			//한번에 읽어들일 byte형의 배열을 만들어요
			byte []data = new byte[100];
			
			//스트림을 통해 읽어 문자열을 다 모아놓을 변수를 만들어요
			String str = "";
			
			//스트림의 끝이 아닐때까지 100바이트씩 읽어 들여요
			while(  is.read(data) != -1 ) {
				
				//읽어들인 byte배열의 데이터를 문자열로 만들어 누적해요.
				str += new String(data, "utf-8");
			}
			
			//전체누적된 문자열 변수를 출력해요.
			System.out.println(str);
			
			//스트림을 닫아줍니다.
			is.close();
		
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}

}
