package com.sist.echo01;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sist.echo.TCPChatClient;

import java.awt.BorderLayout;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UDPSender extends JFrame implements ActionListener{
	
		
		//��ȭ������ ����� �ؽ�Ʈ����� ��������� �����
		JTextArea jta;
		
		//���� �Է��� ��ȭ������ �Է��� �ؽ�Ʈ �ʵ带 ���������
		JTextField jtf;
		
		//����� ��Ʈ���� ��������� �����
		InputStream is;
		OutputStream os;
		
		public UDPSender() {
			
			
			
			
			jta = new JTextArea();
			jtf = new JTextField(50);
			
			//������ ���� ��ư�� ������
			JButton btn = new JButton("����");
			//��ư�� �̺�Ʈ�� �����
			btn.addActionListener(this);
			
			//�ؽ�Ʈ�ʵ�� ��ư�� ��� ���� �г��� ������
			JPanel p = new JPanel();
			p.add(jtf);
			p.add(btn);
			
			//��ũ�������� ���α�
			JScrollPane jsp = new JScrollPane(jta);
			
			//�������� ����� �ؽ�Ʈ����� ���δ� ��ũ������ ����
			add(jsp,BorderLayout.CENTER);
			
			//�ؽ�Ʈ �ʵ�� ��ư�� ����ִ� �г��� �Ʒ��ʿ�
			add(p,BorderLayout.SOUTH);
			
			//�������� ũ���� ����
			setSize(800,600);
			setVisible(true);
			
			try {
				
				
				
				DatagramSocket socket = new DatagramSocket();
				
				byte []data =new byte[100];
	 
				DatagramPacket packet = new DatagramPacket(data, data.length,9002);
							
				socket.send(packet);
							
				socket.close();
							
			}catch(Exception e) {
				
			}
			
			
			class ClinetThread extends Thread{
				byte []data =new byte[100];

				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (true) {
						try {
							//������ ������ �����͸� ������
						 is.read(data);
						 
						 //������ �����͸� ���ڿ��� ����
						 String msg = new String (data);
						 
						 //������ �����ؼ� ������ ���ڿ��� �ؽ�Ʈ����� �߰���
						 jta.append(msg.trim()+"\n");
						 
						}catch(Exception e) {
							System.out.println("���ܹ߻� : "+e.getMessage());
						}
					}//while
					
				}//run
				
			}//�̳� Ŭ����
			
			//�����κ��� ����Ͽ� ���ŵ� �޽����� �ޱ� ���� thread ��ü�� ������ ����
			ClinetThread ct= new ClinetThread();
			ct.start();
			
			
		}//������

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			new TCPChatClient ();

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			try {
				
				//����ڰ� �Է��� �ؽ�Ʈ�ʵ��� ������ byte���� �迭�� ����� ������ ������
				//outputStream void	write(byte[] b) �޼ҵ带 �̿���
				byte []data = jtf.getText().getBytes();		
				os.write(data);
				
				//�޽����� �����ϰ� �����޽����� ���� �ؽ�Ʈ �ʵ� ������
				jtf.setText("");
				
				
				
			}catch(Exception ex) {
				System.out.println("���ܹ߻� : "+ex.getMessage());
			}
		}

	}


