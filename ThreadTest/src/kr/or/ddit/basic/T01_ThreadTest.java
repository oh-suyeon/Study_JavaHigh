package kr.or.ddit.basic;

public class T01_ThreadTest {
	 
	// 순차적으로 진행 single thread
	public static void main(String[] args) {
		for(int i = 0; i <=200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		
		for(int i = 0; i <=200; i++) {
			System.out.print("$");
		}
	}
	
}
