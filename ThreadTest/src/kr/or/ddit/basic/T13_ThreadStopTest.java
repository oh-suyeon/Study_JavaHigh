package kr.or.ddit.basic;

public class T13_ThreadStopTest {
	public static void main(String[] args) {
		/*
		ThreadStopEx1 th = new ThreadStopEx1();
		th.start();
		
		try {
			Thread.sleep(1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		th.stop();	// 자원 반납 없이 그냥 강제 종료 되어 버린다. 
		
		th.setStop(true);
		*/
		//interrupt() 방법1. interrupt() 메서드와 예외 처리로 스레드 멈추기
		ThreadStopEx2 th2 = new ThreadStopEx2();
		th2.start();
		
		try {
			Thread.sleep(3000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th2.interrupt(); // th2에 인터럽트 걸기
		
		
		
	}
}

class ThreadStopEx1 extends Thread {
	
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}

	@Override
	public void run() {
		while(!stop) {
			System.out.println("스레드 처리 중...");
		}
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료");
	}	
}


//interrupt() 메서드 이용
class ThreadStopEx2 extends Thread {
	
	@Override
	public void run() {
		/*
		// 방법1. sleep() or join() 등을 사용했을 때 
		// interrupt() 메서드 호출하면 InterruptedException이 발생
		try {
			while(true) {
				System.out.println("스레드 처리 중...");
				Thread.sleep(1); // 이걸 해야 InterruptedException가 발생할 가능성이 있다. 
								// sleep하는 동안에 누가 Interrupt를 걸면 깨어날 수 있다. --> 예외
			}
		}catch(InterruptedException e) {} 
		// InterruptedException이 발생하는 걸 잡겠다. 잡으면 그냥 빠져나오는 것.
		*/
		
		//interrupt() 방법2. interrupt 메서드가 호출되었는지 검사하기
		while(true) {
			/*
			System.out.println("스레드 처리 중...");
			if(this.isInterrupted()) { // interrupt()메서드 호출되면 true. 인스턴스 메서드
				System.out.println("인스턴스용 isInterrupted()");
				break;
			}
			*/
			// 검사 방법 2.
			if(Thread.interrupted()) {	// static 메서드라서, 한번만 체크할 수 있다. 두 번 연달아호출할 수 없음.
				System.out.println("정적 메서드 interrupted()");
				break;
			}
		}
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
	}
}

