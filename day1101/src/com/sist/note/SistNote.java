package com.sist.note;

//"새파일"과 "열기"에 대한 기능을 구현 해 봅니다.
//완성하면 "1"

import javax.swing.JFileChooser;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.MenuKeyListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


//"부메뉴" "새파일", "열기","저장"을 눌렀을때 이벤트처리를 위하여 ActionListener인터페이스를 구현하도록 합니다.
public class SistNote extends JFrame implements ActionListener, KeyListener{
	
	String fileName = "제목 없음";
	
	//이미 파일명이 있을 경우 물어보지 않고 이 파일에 저장하도록 하기 위하여 File객체를 맴버변수로 만들어요.
	File file=null;
		
	//메모장의 입력 및 출력을 위하여 "여러줄"의 문장을 입력할수 있는 JTextArea를 맴버변수로 선언해요.
	JTextArea jta;
	
	//저장할 파일이름과 열어올 파일이름을 선택하기 위한 JFileChooser를 맴버변수로 선언해요.
	JFileChooser jfc;
	
	//메모장의 내용이 변경된 내용이 있는지 상태를 저장하기 위한 변수를 선언해요
	boolean isNew;
	
	//생성자에서 JTextArea객체를 생성하여 프레임에 담아요
	public SistNote() {
		jta = new JTextArea();
		
		//isNew에 기본값을 false를 설정합니다.
		isNew = false;
		
		//JFileChooser 객체를 생성합니다.
		jfc = new JFileChooser("c:/myData");
		
		//add(jta);
		//텍스트에리어를 바로 프레임에 담으면 
		//화면을 벗어난 글자들이 안보여요!
		//그래서 텍스트에리어는 바로 프레임에 담지 않고 
		//스크롤을 자동으로 만들어 주는 JScrollPane으로 감싸서 프레임에 담아야 해요.		
		JScrollPane jsp = new JScrollPane(jta);
		add(jsp);
		
		//"메뉴바"를 위한 객체를 생성합니다.
		JMenuBar jmb = new JMenuBar();
		
		//"주메뉴" "파일"을 생성합니다.
		JMenu mn_file = new JMenu("파일");
		
		//"부메뉴", "새파일", "열기","저장"을 만들어요
		JMenuItem file_new = new JMenuItem("새파일");
		JMenuItem file_open = new JMenuItem("열기");
		JMenuItem file_save = new JMenuItem("저장");
		
		//"부메뉴", "새파일", "열기", "저장"을 "주메뉴" "파일"에 담아요
		mn_file.add(file_new);
		mn_file.add(file_open);
		mn_file.add(file_save);
		
		//"주메뉴" "파일"을 "메뉴바"에 담아요
		jmb.add(mn_file);
		
		//"프레임"에 "메뉴바"를 설정해요
		setJMenuBar(jmb);
		
		//"부메뉴" "새파일","열기","저장"에 대하여 메뉴를 눌렀을때에 어떤 일을 하도록 이벤트를 등록합니다.
		//매개변수 "이벤트처리담당객체"를 전달해야 하는데, 이 클래스(SistNote) 자신이 처리하도록 하기 위하여 
		//this를 전달합니다.
		file_new.addActionListener( this );
		file_open.addActionListener( this );
		file_save.addActionListener( this );
		
		//텍스트에리어에 키이벤트를 등록합니다.
		jta.addKeyListener(this);
		
		//프레임의 크기를 설정하고, 프레임을 보여주도록 설정합니다
		setSize(800,600);
		setVisible(true);
		setTitle(fileName);
		/*
		jta.setText("안녕하세요");
		try {
			Thread.sleep(3000);
		}catch (Exception e) {
			// TODO: handle exception
		}
		jta.setText("");
		*/
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//메뉴중에 누가 눌러졌는지 파악하기 위하여 
		//ActionEvent의 getActionCommand 메소드를 이용하여 
		//눌러진 메뉴의 "글자"를 가져옵니다.
		String cmd = e.getActionCommand();
		
		//눌러진 메뉴에 따라 각각의 일처리를 하도록 합니다.
		if(cmd.equals("새파일")) {
			
			if(isNew == true) {
				int re = JOptionPane.showConfirmDialog(this, "저장하시겠습니까?");
				System.out.println("re:"+re);
//				re:0	:저장을 하고 새파일 처리
//				re:1	:저장은 안하고 새파일 처리
//				re:2	:새파일을 취소
				switch(re) {
					case 0:saveFile();
					case 1:
						jta.setText("");
						fileName="제목 없음";
						setTitle(fileName);
						isNew = false;
						file = null;
						break;
					case 2:return;
				}
			}else {
				jta.setText("");
				fileName="제목 없음";
				setTitle(fileName);
				isNew = false;
				file = null;
			}
			
		}
		else if(cmd.equals("열기")) {
			try {
				//"열기"메뉴를 누르면 어디에 있는 어떤파일을 열어올지 선택하기 위한 다이얼로그를 띄워요
				int re = jfc.showOpenDialog(this);
				
				//다이얼로그에서 "취소"를 누르지 않고 "열기"를 눌렀을때에 파일의 내용을 읽어오는 처리를 하도록 합니다.
				if(re == 0) {
					//다이얼로그에서 선택한 파일정보를 가져옵니다.
					//열어온 파일을 맴버변수 file에 저장해요!
					file = jfc.getSelectedFile();
					
					//그 파일의 내용을 컴퓨터메모리로 읽어들이위한 스트림을 생성합니다.
					FileReader fr = new FileReader(file);
					
					//파일의 모든 내용을 담기 위한 문자열 변수를 만들어요
					//파일로 부터 한글자씩 읽와서 누적하기 위하여 ""으로 초기화 해 줍니다.
					String data="";
					
					//파일로 부터 한글자씩 읽기 위한 변수를 만들어요
					int ch;
					
					//반복문을 이용하여 파일의 끝이 아닐동안 한글자씩 읽어 들여요
					while(true) {
						
						//파일로 부터 한글자씩 읽어들입니다.
						//문자에 해당하는 정수값을 반환합니다.
						//만약 더이상 읽어들일 내용이 없고 파일의 끝에 도달하면 -1을 반환합니다.
						ch = fr.read();
						
						//파일로 부터 더이상 읽어들일 내용이 없으면 반복문을 탈출합니다.
						if(ch == -1) {
							break;
						}
						
						//파일의 끝이 아니라면 읽어들인 문자의 숫자를 다시 문자로 변환하여 
						//문자열 변수 data에 누적합니다.
						data = data + (char)ch;
					}
					//파일의 내용을 모두 읽어들인 다음에는 while을 탈출합니다.
					//while을 탈출했을때에 파일의 모든 내용은 String 변수 data에 담겨져 있어요.
					//data의 내용을 텍스트에리어에 설정합니다.
					jta.setText(data);
					
					//파일을 읽어왔다고 메세지를 출력합시다.
					JOptionPane.showMessageDialog(this, "파일을 읽어왔습니다.");
					
					//읽어온 파일의 정보를 담고 있는 File객체로 부터 파일명을 뽑아서 맴벼변수 fileName에 담아요
					fileName = file.getName();
					//String []arr = fileName.split(".");
					//fileName = arr[0];
					//split메소드는 .으로 분리할 수 없어요!
					
					fileName = fileName.substring(0, fileName.indexOf("."));
					
					//파일명을 프레임의 제목표시줄로 설정합니다.
					setTitle(fileName);
				}
				
				isNew = false;
				
			}catch (Exception ex) {
				System.out.println("예외발생:"+ex.getMessage());
			}
		}
		else if(cmd.equals("저장")) {
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
		//"저장" 메뉴를 누르면 어디에 어떤파일이름을 저장할 것인지 선택하도록 다이얼로그를 띄워요
		//매개변수 this는 이 다이얼로그를 어떤 프레임위에서 띄울것인지를 전달합니다.
		//현재 이프레임 SistNote에 띄우라는 의미로 객체자신을 의미하는 this를 전달합니다.
		//showSaveDialog에서 사용자는 "저장"을 누를수도 있고 "취소"를 누를수도 있어요
		//"취소"를 눌렀는데 파일저장하는 명령어를 처리하면 곤란해요.
		//그래서 이메소드는 "저장"을 누르면 0을 반환하고 "취소"를 누르면 1을 반환합니다.
		//그 반환하는 값은 변수에 담아 그 값에 따라 그다음 이처리를 다르게 할 수 있어요.
		
		//이미 파일이 없다면 파일을 선택하기 위한 다이얼로그를 띄우고 
		//이미 파일이 있다면 그이름으로 저장하도록 합니다.
		
		int re = 0;
		// 이미파일이름이 있는상태에서 File이 null이 아닌상태서 
		// 저장을 누르면 무조건 저장해야 합니다.
		// 그런데 이제 막 처음 파일을 저장하면 File이 null상태에서 "저장"을 누르면
		// 파일을 선택하는 다이얼로그에서 "취소"를 누르지않고 "저장"을 눌렀을때만 저장하도록 처리해야 합니다.
		// 이것을 동시에 처리하기 위하여 re에 기본값을 0을 설정해요.
		// 그래서 이미 파일이 있으면 다음의 if를 만나지 않아서 re는 그대로 0을 유지하고 있어요.
		// 만야 파일이 없는 상태면 다음의 if를 만나서
		// 띄워진 다이얼로그에서 "취소"를 누르면 re=1이 됩어요.
		// 그러면 뒤에 저장처리를 안합니다.
		
		if(file == null) {
			re =jfc.showSaveDialog(this);
			if(re == 0) {
				file = jfc.getSelectedFile();
			}
		}
		
		
		//"저장하기"다이얼로그에 "저장"을 눌렀을때 파일저장을 위한 명령어들이 동작하도록 합니다.
		if(re == 0) {
			//다이얼로그에서 선택한 파일의 정보를 읽어 옵니다.
			File file = jfc.getSelectedFile();
			
			//그 선택한 파일에 텍스트에리어 쓰여진 내용을 출력하기 위한 스트림을 생성합니다.
			//문자단위의 출력위한 Writer의 후손인 FileWriter를 이용합니ㅏㄷ.
			//입출력과 관련된 모든 생성자와 메소드들은 예외를 포함하고 있어요.
			//그 예외들은 RuntimeException의 후손들이 아니기 때문에 
			//사용자가 반드시 예외처리를 해야합니다.
			try {
				FileWriter fw = new FileWriter(file);
				
				//텍스트에리어에 쓰여진 내용을 읽어와서 파일로 출력합니다.
				fw.write( jta.getText()   );
				
				//파일의 사용이 끝났으므로 파일을 닫아줍니다.
				fw.close();
				
				//사용자한테 알려줄 내용을 콘솔에 출력하지 말고 다이얼로그를 띄워요
				JOptionPane.showMessageDialog(this, "파일로 저장하였습니다.");
				
				//저장한 파일의 정보를 갖고 있는 객체 File file로 부터 파일이름을 뽑아와서 
				//맴버변수 fileName에 담아요.
				//fileName = file.getName();   
				//메모.txt
				//.으로 분리하여 파일명만 뽑아와요
				fileName = file.getName();
//				System.out.println("파일명:"+fileName);
//				String []arr = fileName.split(".");
//				fileName = arr[0];
				
				fileName= fileName.substring(0, fileName.indexOf("."));
				
				//파일명을 제목표시줄에 설정합니다.
				setTitle(fileName);
				
			}catch (Exception ex) {//예외변수이름과 이벤트변수이름을 다르게 변경해 줍니다.
				System.out.println("예외발생:"+ex.getMessage()); 
				//예외가 발생하면 예외메세지를 출력하여 
				//메세지를 파악하여 문제를 해결하도록 합니다.
			}
			
			isNew = false;
			
		}
	}
}








