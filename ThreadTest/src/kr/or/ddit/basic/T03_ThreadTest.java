package kr.or.ddit.basic;

public class T03_ThreadTest {
	
	public static void main(String[] args) {
		// 스레드의 수행 시간 체크해보기
		Thread th = new Thread(new MyRunner());
		
		// 유닉스 타임스탬프
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		try {
			th.join();	// 현재 실행중인 스레드에서 작업중인 스레드 (지금은 th스레드가) 종료될 때까지 기다린다. 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과 시간 : " + (endTime - startTime));
		
	}
	
}

class MyRunner implements Runnable {
	
	// 1~1000000000까지의 합계 구하기
	@Override
	public void run() {
		long sum = 0;
		for(long i = 1L; i <= 1000000000; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
}
