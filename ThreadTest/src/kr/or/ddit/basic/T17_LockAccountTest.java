package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//은행 입출금을 스레드로 처리하기
//Lock을 이용 동기화 처리
public class T17_LockAccountTest {

	public static void main(String[] args) {
		// 락 객체 생성
		ReentrantLock lock = new ReentrantLock(true);
		
		// 공유객체생성
		LockAccount lAcc = new LockAccount(lock);
		lAcc.deposit(10000);
		
		BankThread2 bth1 = new BankThread2(lAcc);
		BankThread2 bth2 = new BankThread2(lAcc);
		
		bth1.start();
		bth2.start();
		
	}
	
}

class LockAccount {
	
	private int balance;
	
	// Lock 객체 생성 => 되도록이면 private final로 만들기
	private final Lock lock;
	
	public LockAccount(Lock lock) {
		this.lock = lock;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void deposit(int money) {
		//Lock 객체의 lock()메서드가 동기화 시작이고 
		//unlock() 메서드가 동기화이 끝.
		// lock() 메서드로 동기화를 설정한 곳에서는 반드시unlock() 메서드로 해제해주어야한다. 
		lock.lock(); // 시작 (락을 획득하기 전까지 BLOCKED)
		balance += money; // 동기화 처리 부분
		lock.unlock();
	}
	
	public boolean withdraw(int money) {
		
		lock.lock();
		boolean chk = false;
		
		// try catch 블럭에서는 unlock() finally블럭에서 하기
		
		try {
			if(balance >= money) {
				for(int i = 1; i <= 1000000000; i++) {}
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance());
			}
		}catch (Exception e) {
			chk = false;
		}finally {
			lock.unlock();
		}
		return chk;
	}
}

// 업무 처리
class BankThread2 extends Thread {
	
	private LockAccount lAcc;
	
	public BankThread2(LockAccount lAcc) {
		this.lAcc = lAcc;
	}
	
	@Override
	public void run() {
		boolean result = lAcc.withdraw(6000);
		System.out.println("스레드 안에서 result = " + result + ", balance = " + lAcc.getBalance());
		
	}
	
}










