package com.sist.note;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import java.awt.event.KeyListener;



//�ָ޴� "����"
//�θ޴� "������" "����" "����"�޴��� �����

public class SistNote extends JFrame  implements ActionListener,KeyListener{
	JTextArea jta;   //�޸����� ������� ���ؼ� �������� ������ �Է��� �� �ִ� JTextArea�� ��������� ����
	
	//������ �����̸��� ����� �����̸��� �����ϱ� 0���� JFileChooser�� ��������� ����
	JFileChooser jfc;
 
	String fileName = "�������";
	File file; //�̹� ���ϸ��� ���� ��� �� ���Ͽ� �����ϵ��Τ���
	
	//�޸����� ������ ����� ������ �ִ��� ���¸� �����ϱ� ���� ���� ����
	boolean isNew;
	
	//�����ڿ��� �����ؼ� �����ӿ� �����
	public SistNote() {
		jta = new JTextArea();
		
		//isNew�� �⺻ �� False��  ����
		isNew= false;
		
		//JFileChooser ��ü�� ������
		jfc = new JFileChooser("c:/Mydata");
		
		
		//add(jta);
		//JTextArea�� �ٷ� �����ӿ� ������ ȭ���� ��� ���ڰ� �Ⱥ���
		//JTextArea�� �ٷ� ���� �ʰ�, ��ũ���� ������ִ� JScorllPane���� ���μ� �����ӿ� ���
		
		//ȭ���� ��� ��Ҵ� companent�� �ļ�
		JScrollPane jsp= new JScrollPane(jta);
		add(jsp);
		
		//�������� ũ�⸦ �����ϰ�, �����ֱ�
		setSize(800,600);
		setVisible(true);
		setTitle(fileName);
		
		
		JMenuBar jmb = new JMenuBar();
		
		JMenu mn_file= new JMenu("����");
		
		JMenuItem file_new= new JMenuItem("������");
		JMenuItem file_open = new JMenuItem("����");
		JMenuItem file_save = new JMenuItem("����");
		 
		
		mn_file.add(file_new);
		mn_file.add(file_open);
		mn_file.add(file_save);
		
		jmb.add(mn_file);
		
		setJMenuBar(jmb);
		//�θ޴��� ���ؼ� �޴��� ������ �� � ���� �ϵ��� �̺�Ʈ�� �����
		  
		//�Ű������� �̺�Ʈó�� �����(��ü)����, 
		//�� Ŭ���� SistNote�ڽ��� ó���ϱ� ���ؼ� this����
		
		file_new.addActionListener( this );
		file_open.addActionListener(this);
		file_save.addActionListener(this);
		
		//txtArea�� keyEvent�� �����
		jta.addKeyListener(this);
		
		/*
		jta.setText("�ȳ��ϼ���");
		try {
			Thread.sleep(3000);
		}catch(Exception e) {
			
		}
		jta.setText(""); //��� �����
		*/
	
	}

	
	//�θ޴��� ������ �̰� ���۵� -> �׷��� ���ؼ� �̺�Ʈ�� ���
	//� �� ���������� ���� �ؾ��ϴ� ���� �ٸ���, �װ� ���⼭ �ľ��ϱ�
	//ActionEvent�� getActionCommand �޼ҵ带 �̿��ؼ� ������ �޴���
	//���ڸ� ������  
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		
		
		//������ �޴��� ���� ���� ��ó��
		if(cmd.equals("������")) {  //��ĭ������ �Ȱ��� ���������
			
			if(isNew ==true) {
				int re = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?");
				System.out.println("re:"+re);
				
				//re:0 : ������ �ϰ�, ������ó��
				//re:1: ������ ���ϰ� ������ ó��
				//re:2: ������ ���
				
				switch(re) {
				case 0: saveFile();
				case 1: 
					jta.setText("");
					fileName = "�������";
					setTitle(fileName);
					isNew = false;
					
					break;
				case 2: return; //�� �͵� ����
				}
			}
		
			
		}else if(cmd.equals("����")) {
		//ip�ּҤѸ� ���� ��Ѵ� �����ϵ� 
			
			// ��Ī�Ǵ� ���ڷ� �������µ� ������ �ø�
			
			try {
				//���̾�α׸� ���
				int re = jfc.showOpenDialog(this);
				
				//��Ҹ� ������ �ʰ� ���⸦ ������ ���� ������ ������ ó��
				if(re ==0 ) {
					
					//���̾�α׿��� ������ ���������� ������
					file= jfc.getSelectedFile();
					 
					//���� �޸𸮷� �о���̱����� ��Ʈ�� ����
					FileReader fileReader = new FileReader(file);
					
					//������ ��� ������ ������� ���ڿ� ���� �����
					String data=""; //�� ���ھ� �о�� ������ ���� "�ʱ�ȭ"
					int ch;
					while(true) {
						ch = fileReader.read();
						
						if(ch == -1) {
							break;
						}
						data= data+(char)ch;
					}
					//������ ������ ��� �о���� �� �ݺ��� Ż����
					//������ ��� ������ String���� data�� �����
					//data�� ������ txtArea�� ����
					jta.setText(data);
					
					JOptionPane.showMessageDialog(this, "������ �о���� ����!");
					
					
					//�о�� ������ ������ ����ִ� file��ü�κ��� ���ϸ��� �̾Ƽ� ������� filename�� ���
					fileName = file.getName();
					//String []arr= fileName.split(".");
					//fileName = arr[0];
					
					fileName= fileName.substring(0, fileName.indexOf("."));
					
					setTitle(fileName);
				
				}
				
				isNew = false;
			}catch (Exception ex) {
				System.out.println("���ܹ߻�:"+ex.getMessage());
			}
			
		}else if(cmd.equals("����")) {
			saveFile();
		
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		isNew = true;
	}
	
	
	public void saveFile() {
		//����޴��� ������ �� ��� � �����̸��� ������ ������
		//���̾�α� ����
		int re = 0;
		//�̹� ������
		if(file ==null) {
		re=jfc.showSaveDialog(this);  //this: �� ���̾�α״� � ������������ ��� ������ ����
		if(re==0) {
			 file= jfc.getSelectedFile();
		}
		}
		if(re==0) {
			//�����ϱ⸦ ������ �� ��ɾ���� �����ϵ�����
			//������ ������ ������ �о��
			File file= jfc.getSelectedFile();
			
			//�� ������ ���Ͽ� �ؽ�Ʈ������ ������ ������ ����ϱ� ���� ��Ʈ���� ����
			//���ڴ����� ����� ���� writer���ļ��� fileWriter�� �̿���
			//����°��õ� ��� �����ڿ� �޼ҵ�� ���ܸ� ������
			//�� ���ܵ��� RunTimeException�� �ļ��� �ƴ�-> ����ڰ� ó��
		
		try {
			FileWriter fw = new FileWriter(file);
			
			//TextArea�� ������ ������ �о�ͼ� ���Ϸ� ���
			fw.write(jta.getText());
			fw.close();
			
			//System.out.println("���Ϸ� �����߽��ϴ�");
			
			JOptionPane.showMessageDialog(this, "���� ����!");
			
			//������ �����̸����� ����ǥ������ ������
			//������ ������ �����ִ� ��ü File file�κ��� �����̸��� �̾ƿ�
			//fileName = file.getName(); ->�޸�.txt���� �� ��
			//.�� �������� ���ϸ� �̾ƿ�
		
			//�о�� ������ ������ ����ִ� file��ü�κ��� ���ϸ��� �̾Ƽ� ������� filename�� ���
			fileName = file.getName();
			//System.out.println("���ϸ�:"+fileName);
			//String []arr= fileName.split(".");
			//fileName = arr[0];
			fileName= fileName.substring(0, fileName.indexOf("."));
			
			setTitle(fileName);
			
		}catch(Exception ex) { 
			System.out.println("���ܹ߻� : "+ex.getMessage());
		}
		isNew = false;
		
		}
	}
	
	
	
}
