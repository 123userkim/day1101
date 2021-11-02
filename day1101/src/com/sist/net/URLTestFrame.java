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

public class URLTestFrame extends JFrame implements ActionListener {
	
	//url�� �Է¹ޱ� ���� �ؽ�Ʈ�ʵ带 �ɹ������� ������
	JTextField jtf;
	
	//url�� ������ ������ �ܾ�ͼ� ����ϱ� ���� �ؽ�Ʈ����� �ɹ������� ������
	JTextArea jta;
	
	public URLTestFrame() {
		//�ɹ����� �ؽ�Ʈ�ʵ�� �ؽ�Ʈ������ ��ü�� �����մϴ�.
		jtf = new JTextField(50);
		jta = new JTextArea();
		
		//�о���⸦ �����ų ��ư�� �����մϴ�.
		JButton btn = new JButton("�о����");
		
		//�ؽ�Ʈ�ʵ�� ��ư�� ��� ���� �г��� �����մϴ�.
		JPanel p = new JPanel();
		
		//�гο� �ؽ�Ʈ�ʵ�� ��ư�� ��ƿ�
		p.add(jtf);
		p.add(btn);
		
		//�ؽ�Ʈ����� ���α� ���� ��ũ������ �����մϴ�.
		JScrollPane jsp = new JScrollPane(jta);
		
		//�ؽ�Ʈ�ʵ�� ��ư�� ��� �ִ� �г��� �������� ���ʿ� ��ƿ�
		add(p, BorderLayout.NORTH);
		
		//�ؽ�Ʈ����� ���ΰ� �ִ� ��ũ������ �������� ����� ��ƿ�.
		add(jsp, BorderLayout.CENTER);
		
		//�������� ũ�⸦ �����մϴ�.
		setSize(800,600);
		
		//�������� ȭ�鿡 ���̵��� �����մϴ�.
		setVisible(true);
		
		//��ư�� �̺�Ʈ�� ����մϴ�.
		btn.addActionListener(this);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new URLTestFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			
			//����� �Է��� ���ͳ��ּҸ� �о�ͼ�
			//���ͳݻ��� ������ü�� �����մϴ�.
			URL url = new URL( jtf.getText()  );
			
			//�� ������ ������ �о���̱� ���Ͽ� ��Ʈ���� �����մϴ�.
			InputStream is = url.openStream();
			
			//�ѹ��� �о���� byte���� �迭�� ������
			byte []data = new byte[100];
			
			//��Ʈ���� ���� �о� ���ڿ��� �� ��Ƴ��� ������ ������
			String str = "";
			
			//��Ʈ���� ���� �ƴҶ����� 100����Ʈ�� �о� �鿩��
			while(  is.read(data) != -1 ) {
				
				//�о���� byte�迭�� �����͸� ���ڿ��� ����� �����ؿ�.
				str += new String(data, "utf-8");
				
				//�迭�� �ٽ� �о���̱� ���� ������ ������
				//�׷��� ������ �ǳ��� �̻��� �����Ⱑ ��µ� �� �־��!
				//byte���� �迭 data�� ��� 0���� �ʱ�ȭ�մϴ�.
				//���� 0�̿��� �ڹٴ� int�� ����ϱ� ������ byte���� ����ȯ�մϴ�.
				Arrays.fill(data,(byte)0);
			}
			
			//��ü������ ���ڿ� ������ �ؽ�Ʈ����� ����ؿ�.
			jta.setText(str);
			
			//��Ʈ���� �ݾ��ݴϴ�.
			is.close();
		
		}catch (Exception ex) {
			System.out.println("���ܹ߻�:"+ex.getMessage());
		}
	}

}
