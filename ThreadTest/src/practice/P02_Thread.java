package practice;

public class P02_Thread {
	
	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		
		SumThread s = new SumThread(1, 500000000L);
		
		s.start();
		
		try {
			s.join();
		} catch (Exception e) {
			e.printStackTrace();
		}		

		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
	}
	
}
class SumThread extends Thread {
	
	private long max, min;
	
	public SumThread(long min, long max) {
		this.max = max;
		this.min = min;
	}
	
	@Override
	public void run() {
		long sum = 0L;
		for(long i = min; i <= max; i++) {
			sum += i;
		}
		System.out.println(min + "~" + max +"까지의 합 : " + sum );
	}
	
}