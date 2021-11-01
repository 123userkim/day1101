package com.sist.net;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.awt.BorderLayout;


public class URLTestFrame extends JFrame implements ActionListener{
	
	//url을 입력받기위한 txtFiled를 멤버변수로 만들기
	JTextField jtf;
	
	//url의 문서 내용을 읽어와서 출력하기 위한 txtArea를 멤버변수로 만들기
	JTextArea jta;
	
	public URLTestFrame() {
		//멤버변수 jtf, jta의 객체를 생성함
		jtf = new JTextField(50); //50자만큼 쓸 수 있도록
		jta = new JTextArea();
		
		JButton bnt = new JButton("읽어오기");
		
		//텍스트필드와 버튼을 담기 위한 패널을 생성
		JPanel p = new JPanel();
		
		//패널에 jtf bnt를 담기
		p.add(jtf);
		p.add(bnt);
		
		//Jscrollpane으로 감쌈
		JScrollPane jsp= new JScrollPane(jta);
		
		//텍스트필드와 버튼을 담고있는 패널을 프레임 위쪽
		add(p,BorderLayout.NORTH);
		
		//텍스트에리어를 감싸고 있는 스크롤펜을 프레임의 가운데
		add(jsp,BorderLayout.CENTER);
		
		//프레임 크기 설정
		setSize(700,600);
		setVisible(true);
		
		//버튼의 이벤트를 등록하기
		bnt.addActionListener(this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new URLTestFrame(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			
			//사용자가 입력한 인터넷 주소를 읽어와서 
			//인터넷상의 문서객체를 생성함
			URL url= new URL(jtf.getText());
			
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
				
				//배열을 다시 일어들이기 전에 깨끗이 비워주기
				//그렇지 않으면 맨 끝에 이상한 쓰레기가 출력될 수 있음
				//byte형의 배열 data를 모두 0으로 초기화하기
				//숫자 0이 오면 자바는 int로 취급하기 ->byte로 형변환
				Arrays.fill(data, (byte)0);
			}
			//변수를 txtarea에 출력
			jta.setText(str);
			is.close();
			
			//스트림을 닫아줘야함
		}catch(Exception ex){
			System.out.println("예외발생 : "+ex.getMessage());
		}
	}

}
