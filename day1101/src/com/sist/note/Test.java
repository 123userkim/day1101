package com.sist.note;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String data = "메모.txt";
		//String []arr = data.split(".");
		//System.out.println(arr[0]);
		//System.out.println(arr[1]);
		
		// 마침표(.)으로는 분리할 수 없어요!
		
		//파일명의 마침표(.)이 있는 위치를 알아내고 
		//문자열의 처음부터 마침표(.)까지 추출해요
		String result = data.substring(0, data.indexOf("."));
		System.out.println(result);
	}

}
