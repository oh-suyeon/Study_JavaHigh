package kr.or.dit.basic;

public class LambdaTest01 {
	
	public static void main(String[] args) {
		// 람다식을 사용하지 않는 경우
		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					System.out.println(i);
				}
			}
		});
		th1.start();
		
		// 람다식 사용하는 경우
		Thread th2 = new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				System.out.println(i);
			}
		});
		th2.start();
	}
}


