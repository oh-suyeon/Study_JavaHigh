package kr.or.ddit.basic;

// 은행 입출금을 스레드로 처리하기
// synchronized을 이용 동기화 처리
public class T16_SyncAccountTest {
	public static void main(String[] args) {
		SynchAccount sAcc = new SynchAccount();
		sAcc.setBalance(10000);
		
		BankThread bth1 = new BankThread(sAcc);
		BankThread bth2 = new BankThread(sAcc);
		BankThread bth3 = new BankThread(sAcc);
		
		bth1.start();
		bth2.start();
		bth3.start();
	}
}

// 은행 입출금 관리하는 클래스
class SynchAccount{
	private int balance; // 잔액

	synchronized public int getBalance() { // 동기화 영역에서 호출하는 메서드도 동기화 처리 해줘야 함
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	// 입금 처리
	synchronized public void deposit(int money) {
		balance += money;
	}
	
	// 출금 처리 (성공:true, 실패:false)
	// 동기화되지 않았을 때는 첫번째 스레드가 '시간벌기'하고 있을때 두번째 스레드도 진입하고, 잔액이 줄어들지 않은 상태라 작업을 시작한다. 
	public boolean withdraw (int money) {
		synchronized (this) {
			if(balance >= money) {
				for(int i = 1; i <= 1000000000; i++) {} // 시간벌기
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance()); // 동기화 영역에서 호출하는 메서드도 동기화 처리 해줘야 함 (이 메서드가 있는 곳으로 이동하니까)
				return true;
			} else {
				return false;
			}
		}
	}
}

// 은행업무 처리하는 클래스
class BankThread extends Thread {
	private SynchAccount sAcc;
	
	public BankThread(SynchAccount sAcc) {
		this.sAcc = sAcc;
	}
	
	@Override
	public void run() {
		boolean result = sAcc.withdraw(6000);
		System.out.println("스레드 안에서 result = " + result + ", balance = " + sAcc.getBalance());
		System.out.println();
	}
}
