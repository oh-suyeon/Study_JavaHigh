package practice;

public class P01_Thread {

	public static void main(String[] args) {

		MyThread1 t1 = new MyThread1();
		t1.start();

		Thread t2 = new Thread(new MyThread2());
		t2.start();

		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.print("&");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t3.start();
	}
}

class MyThread1 extends Thread {
	
	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {
			System.out.print("*");
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable {

	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {
			System.out.print("@");
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}