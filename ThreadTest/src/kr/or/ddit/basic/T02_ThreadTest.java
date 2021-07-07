package kr.or.ddit.basic;

public class T02_ThreadTest {
	//멀티 스레드 프로그램 방식
	
	public static void main(String[] args) {
		
		MyThread1 th1 = new MyThread1();
		th1.start();
		
		MyThread2 r1 = new MyThread2();
		Thread th2 = new Thread(r1);
		th2.start();
		
		Thread th3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i <= 200; i++) {
					System.out.print("@");
					try {
						//Thread.sleep(시간) => 주어진 시간동안작업을 잠시 멈춘다.
						//시간은 밀리세컨드 단위를 사용 (1000 = 1초)
						Thread.sleep(100);
						
					} catch(InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		
		th3.start();
		
	}
	
}


class MyThread1 extends Thread {
	
	@Override
	public void run() {
		for(int i = 0; i <= 200; i++) {
			System.out.print("*");
			try {
				//Thread.sleep(시간) => 주어진 시간동안작업을 잠시 멈춘다.
				//시간은 밀리세컨드 단위를 사용 (1000 = 1초)
				Thread.sleep(100);
				
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
}

class MyThread2 implements Runnable {

	@Override
	public void run() {
		for(int i = 0; i <= 200; i++) {
			System.out.print("$");
			try {
				//Thread.sleep(시간) => 주어진 시간동안작업을 잠시 멈춘다.
				//시간은 밀리세컨드 단위를 사용 (1000 = 1초)
				Thread.sleep(100);
				
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
}