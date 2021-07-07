package kr.or.ddit.basic;

public class T10_ThreadStateTest {
	public static void main(String[] args) {
		
		Thread th = new StatePrintThread(new TargetThread());
		
		th.start();		
	}
}

// 타겟 스레드 상태 출력할 클래스 
class StatePrintThread extends Thread {
	private Thread targetThread;
	
	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		while(true) {
			//스레드상태 구하기 getState()
			Thread.State state = targetThread.getState(); // Tread내부의 enum 객체 State
			System.out.println("타겟 스레드의 상태값 : " + state);
			
			//new 상태인지 검사
			if(state == Thread.State.NEW) {
				targetThread.start();	// 메인에서 start()한게 아니라 다른 스레드에서 실행.
			}
			
			//종료 상태인지 검사
			if(state == Thread.State.TERMINATED) {
				break;
			}
			
			try {
				Thread.sleep(100);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
}

class TargetThread extends Thread {
	@Override
	public void run() {
		for(long i = 1; i <= 1000000000L; i++) {} // 시간 지연용
		try {
			Thread.sleep(1500);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(long i = 1; i <= 1000000000L; i++) {}
	}
}

