package kr.or.ddit.basic;

public class T04_ThreadTest {
	
	// 1~20 합계 구하기에 걸린 시간 체크
	// 단독 쓰레드로 단독 작업했을 때 시간과
	// 멀티 쓰레드로 분할 작업했을 때 시간 비교
	
	public static void main(String[] args) {
		// 단독으로 처리할 때
		SumThread sm = new SumThread(1L, 20000000000L);
		
		long startTime = System.currentTimeMillis();
		sm.start();
		
		try {
			sm.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리할 때 처리시간 : " + (endTime - startTime));
		System.out.println("\n\n");
		
		// 여러 쓰레드가 협력할 때
		SumThread[] sumThs = new SumThread[] {
				new SumThread(1L, 5000000000L),
				new SumThread(500000001L, 10000000000L),
				new SumThread(10000000001L, 15000000000L),
				new SumThread(15000000001L, 20000000000L)
		};
		
		startTime = System.currentTimeMillis();
		
		for(int i = 0; i< sumThs.length; i++) {
			sumThs[i].start();
		}
		
		for(SumThread s : sumThs) {
			try {
				s.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리할 때 처리시간 : " + (endTime - startTime));
		
	}
}

class SumThread extends Thread {
	
	private long max, min;
	
	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public void run() {
		long sum = 0L;
		for(long i = min; i <= max; i++) {
			sum += 1;
		}
		System.out.println(min + "~" + max + "까지의 합 : " + sum);
	}
	
}
