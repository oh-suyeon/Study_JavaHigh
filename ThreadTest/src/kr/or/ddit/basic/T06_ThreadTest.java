package kr.or.ddit.basic;

import javax.swing.JOptionPane;

// 멀티 쓰레드 활용한 카운트다운 처리
public class T06_ThreadTest {
	
	// 입력 여부를 확인하기 위한 변수 선언
	// 모든 쓰레드에서 공통으로 사용하며 정보를 공유하는 변수
	// static이니까 언제든지 접근할 수 있다. 
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		
		DataInput di = new DataInput();
		di.start();
		
		CountDown cd = new CountDown();
		cd.start();
		
	}
}

// 데이터 입력받기
class DataInput extends Thread {
	
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		// 입력이 완료되면 inputCheck 변수를 true로 변경한다.
		T06_ThreadTest.inputCheck = true;
		
		System.out.println("입력한 값은 " + str + "입니다.");
	}
}

// 카운트다운을 처리하는 쓰레드
class CountDown extends Thread {
	
	@Override
	public void run() {
		for(int i = 10; i >= 1; i--) {
			// 입력이 완료되었는지 여부 검사하고 입력 완료되면 run() 종료한다.
			// 즉 현재 스레드를 종료한다. 
			if(T06_ThreadTest.inputCheck == true) {
				return ; // run() 종료되면 스레드도 끝난다.
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 10초가 경과되었는데 입력이 없으면 프로그램을 종료한다. 
		System.out.println("10초가 지났습니다.");
		System.exit(0);
	}
}