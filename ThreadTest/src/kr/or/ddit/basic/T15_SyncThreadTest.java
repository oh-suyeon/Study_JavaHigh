package kr.or.ddit.basic;

public class T15_SyncThreadTest {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		WorkerThread th1 = new WorkerThread("1번 스레드", sObj);
		WorkerThread th2 = new WorkerThread("2번 스레드", sObj);
		
		th1.start();
		th2.start();
	}
}

// 공동 객체
class ShareObject {
	private int sum = 0; 
	
//	synchronized public void add() {
	public void add() {
		
		synchronized (this) {
			for(int i = 0; i < 1000000000; i++) {}	// 동기화처리전까지 시간벌기
			
			int n = sum;
			n += 10;
			sum = n;

			System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
		}
	}
	
	public int getSum() {
		return sum;
	}
}

// 작업 수행 
class WorkerThread extends Thread {
	
	ShareObject sObj;
	public WorkerThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			sObj.add();
		}
	}
	
}