//�ڵ带 �����Ͽ� 
//UDP������� ���� �� ���ϴ�.


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
	
	//��ȭ������ ����� �ؽ�Ʈ����� �ɹ������� ������
	JTextArea jta;
	
	//���� ��ȭ������ �Է��� �ؽ�Ʈ�ʵ带 �ɹ������� ������
	JTextField jtf;
	
	//����½�Ʈ���� �ɹ������� ������
	InputStream is;
	OutputStream os;
	
	public TCPChatClient() {
		
		
		//�ɹ����� �ؽ�Ʈ������� �ؽ�Ʈ�ʵ带 �����մϴ�.
		jta = new JTextArea();
		jtf = new JTextField(50);
		
		
		//"����"�� ���� ��ư�� �����մϴ�.
		JButton btn = new JButton("����");
		
		//��ư�� �̺�Ʈ�� ����մϴ�.
		btn.addActionListener(this);
		
		//�ؽ�Ʈ�ʵ�� ��ư�� ������� �г��� �����մϴ�.
		JPanel p = new JPanel();
		p.add(jtf);
		p.add(btn);
		
		//�ؽ�Ʈ����� ��ũ�������� ���ο�
		JScrollPane jsp = new JScrollPane(jta);
		
		//�������� ����� �ؽ�Ʈ����� ���ΰ� �ִ� ��ũ������ ��ƿ�
		add(jsp,BorderLayout.CENTER);
		
		//�ؽ�Ʈ�ʵ�� ��ư�� ��� �ִ� �г��� �������� �Ʒ��� ��ƿ�
		add(p,BorderLayout.SOUTH);
		
		//�������� ũ�⸦ �����մϴ�.
		setSize(800,600);
		
		//�������� ȭ�鿡 ���̵��� �����մϴ�.
		setVisible(true);
		
		try {
			
			//����� ���Ͽ� ������ ������ ��û�Ѵ�.
			Socket socket = new Socket("192.168.0.42", 9003);
			
			//������� ���� ��Ʈ���� �����մϴ�.
			is = socket.getInputStream();
			os = socket.getOutputStream();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		Thread t = new Thread(this);
		t.start();
		
	}//end  ������
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TCPChatClient();
		//main start
		//������ start
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		try {
			//����ڰ� �Է��� �ؽ�Ʈ�ʵ��� ������ ������ �������� �մϴ�.
			//OutputStream void	write(byte[] b) �޼ҵ带 �̿��մϴ�.
			byte []data = jtf.getText().getBytes();
			os.write(data);
			
			//�޼����� �����ϰ� ���� �޼��� �Է��� ���Ͽ� �ؽ�Ʈ�ʵ带 ������ ������
			jtf.setText("");
			
		}catch (Exception ex) {
			System.out.println("���ܹ߻�:"+ex.getMessage());
		}
		
	}


	//������ ���� ���ŵ� �����͸� ��� �޵��� �մϴ�.
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










