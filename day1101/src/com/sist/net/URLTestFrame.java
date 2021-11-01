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
	
	//url�� �Է¹ޱ����� txtFiled�� ��������� �����
	JTextField jtf;
	
	//url�� ���� ������ �о�ͼ� ����ϱ� ���� txtArea�� ��������� �����
	JTextArea jta;
	
	public URLTestFrame() {
		//������� jtf, jta�� ��ü�� ������
		jtf = new JTextField(50); //50�ڸ�ŭ �� �� �ֵ���
		jta = new JTextArea();
		
		JButton bnt = new JButton("�о����");
		
		//�ؽ�Ʈ�ʵ�� ��ư�� ��� ���� �г��� ����
		JPanel p = new JPanel();
		
		//�гο� jtf bnt�� ���
		p.add(jtf);
		p.add(bnt);
		
		//Jscrollpane���� ����
		JScrollPane jsp= new JScrollPane(jta);
		
		//�ؽ�Ʈ�ʵ�� ��ư�� ����ִ� �г��� ������ ����
		add(p,BorderLayout.NORTH);
		
		//�ؽ�Ʈ����� ���ΰ� �ִ� ��ũ������ �������� ���
		add(jsp,BorderLayout.CENTER);
		
		//������ ũ�� ����
		setSize(700,600);
		setVisible(true);
		
		//��ư�� �̺�Ʈ�� ����ϱ�
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
			
			//����ڰ� �Է��� ���ͳ� �ּҸ� �о�ͼ� 
			//���ͳݻ��� ������ü�� ������
			URL url= new URL(jtf.getText());
			
			//�� ������ ������ �о���̱� ���ؼ� ��Ʈ���� ������
			InputStream is =url.openStream();
			
			//�ѹ��� �о���� byte���� �迭�� ����
			byte []data = new byte[100];
			
			//��Ʈ���� ���� �о� ���ڿ��� �� ��Ƴ���, �ѹ��� ����ϱ� ���� ������ �����
			String str = "";
			
			//��Ʈ���� ���� �ƴҶ� ���� 100����Ʈ�� ����
			while(is.read(data) != -1 ) { //�о���ϰ� ��� ������ �ݺ��� ���ư�
				//�о���� byte�迭�� �����͸� ���ڿ��� ����� ����
				str+= new String(data,"utf-8");
				
				//�迭�� �ٽ� �Ͼ���̱� ���� ������ ����ֱ�
				//�׷��� ������ �� ���� �̻��� �����Ⱑ ��µ� �� ����
				//byte���� �迭 data�� ��� 0���� �ʱ�ȭ�ϱ�
				//���� 0�� ���� �ڹٴ� int�� ����ϱ� ->byte�� ����ȯ
				Arrays.fill(data, (byte)0);
			}
			//������ txtarea�� ���
			jta.setText(str);
			is.close();
			
			//��Ʈ���� �ݾ������
		}catch(Exception ex){
			System.out.println("���ܹ߻� : "+ex.getMessage());
		}
	}

}
