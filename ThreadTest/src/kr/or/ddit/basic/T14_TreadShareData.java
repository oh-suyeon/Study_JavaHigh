package kr.or.ddit.basic;

public class T14_TreadShareData {
	
	// ex. 원주율 계산 스레드, 계산 결과 출력 스레드
	// 원주율을 계산한 후 값을 출력하는 프로그램 만들기
	// 이때 원주율을 저장할 객체가 필요.
	
	public static void main(String[] args) {
		
		//공동 사용 객체의 인스턴스
		ShareData sd = new ShareData();
		
		//처리 스레드 객체
		CalcPIThread cpt = new CalcPIThread(sd);
		PrintPIThread ppt = new PrintPIThread(sd);
		
		cpt.start();
		ppt.start();
	}
		
}

// 원주율 관리 클래스 (공용)
class ShareData {
	
	public double result;
	
	//volatile ==> 선언된 변수를 컴파일러의 최적화 대상에서 제외시킨다. 
	// 값이 변경되는 즉시 변수에 적용
	// 다중 스레드에서 하나의변수가 완벽하게 한번에 작동하도록 보장하는 키워드
	// (일종의동기화)
	// Main Memory에 read & write를 보장하는 키워드.
	//하나의 Thread가 write하고 나머지 Thread가 읽는 상황인 경우.
	//변수의 값이 최신의 값으로 읽어와야 하는 경우.
	
	//원주율 계산 완료됐는지 나타내는 변수
	volatile public boolean isOk = false;
}

// 원주율 계산하는 스레드
class CalcPIThread extends Thread {
	private ShareData sd;
	
	public CalcPIThread (ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		// 원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 ... ) * 4;
		//			1  -  3  +  5  -  7  +  9 => 분모
		//			0     1     2     3     4 => 2로 나눈 몫
		
		double sum = 0.0;
		for(int i = 1; i <= 150000000; i += 2) {
			if((i/2) % 2== 0) {	// 2로 나눈 몫이 짝수이면 + 
				sum += (1.0/i);
			}else {
				sum -= (1.0/i);
			}
		}
		sd.result = sum * 4; // 계산된 원주율 공통객체 멤버변수에저장
		sd.isOk = true; // 계산 완료 표시
	}
}

// 계산된 원주율 출력하기
class PrintPIThread extends Thread {
	private ShareData sd;
	
	public PrintPIThread (ShareData sd) {
		this.sd = sd;
	}
	@Override
	public void run() {
		while(true) {
			if(sd.isOk) {
				break;
			}
		}
		System.out.println();
		System.out.println("계산된 원주율 : " + sd.result);
		System.out.println("       PI : " + Math.PI);
	}
	
}