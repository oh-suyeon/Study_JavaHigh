package kr.or.ddit.basic;

// 3개(명)의 스레드가 각각 알파벳대문자를 출력하는데 
// 출력 끝난 순서대로 결과를나타내는프로그램을 작성	

public class T11_DisplayCharacterTest {
	
	static String strRank = "";
	
	public static void main(String[] args) {
		
		Thread[] disChars = new DisplayCharacter[] {
			new DisplayCharacter("홍길동"), 
			new DisplayCharacter("일지매"), 
			new DisplayCharacter("변학도") 
		};
		
		for(Thread th : disChars) {
			th.start();
		}
		for(Thread th : disChars) {
			try {
				th.join();	// 메인 메서드는 기다린다.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("경기 끝...");
		System.out.println("--------------------------");
		System.out.println();
		System.out.println("경기 결과");
		System.out.println("순위 : " + strRank);
	}
	
}

class DisplayCharacter extends Thread {
	
	private String name;
	
	public DisplayCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(name + "의 출력 문자 : " + ch);
			
			try {
				//sleep 메서드의 값을 200-500사이의 난수로
				Thread.sleep((int)(Math.random() * 301 + 200));
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}System.out.println(name + "출력 끝...");
		
		T11_DisplayCharacterTest.strRank += name + " ";
	}
	
}
