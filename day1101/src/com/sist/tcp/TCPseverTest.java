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
			
			//1.서버소캣을 생성함
			ServerSocket server = new ServerSocket(9001);
			
			//2.클라이언트의접속을 무한대기
			while(true) {
				//3.클라이언스의 접속을 수락함
				//이메소드는 연결을 요청한 클라이언트와 통신을 하기 위한 소켓객체를 반환함
				//이런 소켓을 데이터소켓이라고 함
			Socket socket=server.accept(); 
			System.out.println("클라이언트가 접속하였습니다.");
			
			//4.데이터를 주고 받을 스트림을 생성함
			InputStream is =socket.getInputStream();
			OutputStream os= socket.getOutputStream();
			
			//5.스트림을 통해 데이터를 주고 받기
			//요청한 클라이언트한테 10개의 정수를 난수로 만들기
				for(int i=1;i<=10;i++) {
					//0에서 100사이의 난수를 만들기
					int n =r.nextInt(100);						
					System.out.println("서버가 난수 하나를 만들었습니다."+n);
					
					//대기시간 0.1초
					Thread.sleep(100);
										
					//클라이언트에게 출력하기
					os.write(n);
					
				}
				
				System.out.println("데이터를 다 내보냈습니다.");
				
				//6. 사용했던 자원을 닫아줌
				is.close();
				os.close();
				socket.close();
			}
			
		}catch(Exception e) {
			System.out.println("예외발생 : " +e.getMessage());
		}
	}

}
