package com.sist.net;

import java.io.InputStream;
import java.net.URL;

public class URLTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//���ͳݻ��� ������ü�� ������
			URL url= new URL("https://www.hanbit.co.kr");
			
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
			}
			System.out.println(str);
			is.close();
			
			//��Ʈ���� �ݾ������
		}catch(Exception e){
			System.out.println("���ܹ߻� : "+e.getMessage());
		}
	}

}
