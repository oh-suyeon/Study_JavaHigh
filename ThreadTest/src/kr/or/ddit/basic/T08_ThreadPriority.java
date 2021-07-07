package kr.or.ddit.basic;

import javax.naming.InterruptedNamingException;

public class T08_ThreadPriority {
	
	public static void main(String[] args) {
		
		ThreadTest1 t1 = new ThreadTest1();
		ThreadTest1 t2 = new ThreadTest1();
		ThreadTest1 t3 = new ThreadTest1();
		ThreadTest1 t4 = new ThreadTest1();
		ThreadTest1 t5 = new ThreadTest1();
		ThreadTest2 t6 = new ThreadTest2();

		// 우선순위는 start() 메서드 호출 전에 설정
		
//		t1.setPriority(Thread.MIN_PRIORITY);
		t1.setPriority(1);
		t2.setPriority(1);
		t3.setPriority(1);
		t4.setPriority(1);
		t5.setPriority(1);
		t6.setPriority(10);
//		t6.setPriority(Thread.MAX_PRIORITY);
		
		System.out.println("제t1의 우선순위 : " + t1.getPriority());
		System.out.println("제t2의 우선순위 : " + t2.getPriority());
		System.out.println("제t3의 우선순위 : " + t3.getPriority());
		System.out.println("제t4의 우선순위 : " + t4.getPriority());
		System.out.println("제t5의 우선순위 : " + t5.getPriority());
		System.out.println("제t6의 우선순위 : " + t6.getPriority());
		        
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("최대 우선순위 : " + Thread.MAX_PRIORITY);
		System.out.println("최소 우선순위 : " + Thread.MIN_PRIORITY);
		System.out.println("보통 우선순위 : " + Thread.NORM_PRIORITY);
		
	}
	
}

// 대문자를 출력하는 메서드
class ThreadTest1 extends Thread {
	
	@Override
	public void run() {
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(ch);
		}
		
		// 아무것도 하지 않는 반복문 (CPU를 쓰면서 작업을 시키면서 시간 때우기 용/sleep은 작업을 멈춘 것)
		for(long i = 1; i <=1000000000L; i++) {
			
		}
	}
}
	
// 소문자를 출력하는 메서드
class ThreadTest2 extends Thread {
		
	@Override
	public void run() {
		for(char ch = 'a'; ch <= 'z'; ch++) {
			System.out.println(ch);
		}
		
		// 아무것도 하지 않는 반복문 (시간 때우기 용)
		for(long i = 1; i <=1000000000L; i++) {
			
		}
	}
}		

