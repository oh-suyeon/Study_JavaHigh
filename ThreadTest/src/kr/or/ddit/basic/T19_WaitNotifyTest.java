package kr.or.ddit.basic;

public class T19_WaitNotifyTest {
	public static void main(String[] args) {
		
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
		
	}
}

//공통 사용 객체
class WorkObject {
	
	public synchronized void methodA() {
		System.out.println("methodA() 메서드 작업 중...");
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void methodB() {
		System.out.println("methodB() 메서드 작업 중...");
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

//methodA() 만 호출하는 스레드
class ThreadA extends Thread {
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료." );
	}
}

//methodB() 만 호출하는 스레드
class ThreadB extends Thread {
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료." );
	}
}


