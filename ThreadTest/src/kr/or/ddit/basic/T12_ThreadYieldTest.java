package kr.or.ddit.basic;

public class T12_ThreadYieldTest {
	
	public static void main(String[] args) {
		YieldThreadEx1 t1 = new YieldThreadEx1();
		YieldThreadEx2 t2 = new YieldThreadEx2();
		
		t1.start();
		t2.start();
	}
	
}

class YieldThreadEx1 extends Thread {
	
	@Override
	public void run() {
		for(int i = 0; i < 5; i++) {
			System.out.println("YieldThreadEx1 : " + i);
			yield(); // 양보하기
		}
	}
}

class YieldThreadEx2 extends Thread {
	
	@Override
	public void run() {
		for(int i = 0; i < 5; i++) {
			System.out.println("YieldThreadEx2 : " + i);
		}
	}
}