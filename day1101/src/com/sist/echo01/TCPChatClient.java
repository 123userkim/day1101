//코드를 복사하여 
//UDP방식으로 수정 해 봅니다.


package com.sist.echo01;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TCPChatClient extends JFrame implements ActionListener,Runnable {
	
	//대화내용을 출력할 텍스트에리어를 맴버변수로 만들어요
	JTextArea jta;
	
	//내가 대화내용을 입력할 텍스트필드를 맴버변수로 만들어요
	JTextField jtf;
	
	//입출력스트림을 맴버변수로 만들어요
	InputStream is;
	OutputStream os;
	
	public TCPChatClient() {
		
		
		//맴버변수 텍스트에리어와 텍스트필드를 생성합니다.
		jta = new JTextArea();
		jtf = new JTextField(50);
		
		
		//"전송"을 위한 버튼을 생성합니다.
		JButton btn = new JButton("전송");
		
		//버튼에 이벤트를 등록합니다.
		btn.addActionListener(this);
		
		//텍스트필드와 버튼을 담기위한 패널을 생성합니다.
		JPanel p = new JPanel();
		p.add(jtf);
		p.add(btn);
		
		//텍스트에리어를 스크롤팬으로 감싸요
		JScrollPane jsp = new JScrollPane(jta);
		
		//프레임의 가운데에 텍스트에리어를 감싸고 있는 스크롤팬을 담아요
		add(jsp,BorderLayout.CENTER);
		
		//텍스트필드와 버튼을 담고 있는 패널을 프레임의 아래에 담아요
		add(p,BorderLayout.SOUTH);
		
		//프레임의 크기를 설정합니다.
		setSize(800,600);
		
		//프레임이 화면에 보이도록 설정합니다.
		setVisible(true);
		
		try {
			
			//통신을 위하여 서버에 접속을 요청한다.
			Socket socket = new Socket("192.168.0.42", 9003);
			
			//입출력을 위한 스트림을 생성합니다.
			is = socket.getInputStream();
			os = socket.getOutputStream();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		Thread t = new Thread(this);
		t.start();
		
	}//end  생성자
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TCPChatClient();
		//main start
		//생성자 start
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		try {
			//사용자가 입력한 텍스트필드의 내용을 서버로 보내도록 합니다.
			//OutputStream void	write(byte[] b) 메소드를 이용합니다.
			byte []data = jtf.getText().getBytes();
			os.write(data);
			
			//메세지를 전송하고 다음 메세지 입력을 위하여 텍스트필드를 깨끗이 지워요
			jtf.setText("");
			
		}catch (Exception ex) {
			System.out.println("예외발생:"+ex.getMessage());
		}
		
	}


	//서버로 부터 수신된 데이터를 계속 받도록 합니다.
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		byte []data = new byte[100];
		while(true) {
			try {
				is.read(data);
				String msg = new String(data);
				msg = msg.trim();
				jta.append(msg+"\n");
				
			}catch (Exception e) {
				// TODO: handle exception
			}//end catch
			
		}//end while
	}//end run
	
	
}










