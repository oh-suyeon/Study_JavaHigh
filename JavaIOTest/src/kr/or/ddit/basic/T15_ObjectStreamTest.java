package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T15_ObjectStreamTest {
	public static void main(String[] args) {
		
		//Member 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일재매", 30, "대구");
		Member mem3 = new Member("이몽룡", 40, "광주");
		Member mem4 = new Member("성춘향", 50, "부산");
		
		try {
			// 객체를 파일에 저장하기
			// 출력용 스트림 객체 생성 (파라미터는 출력용 스트림)
			
			ObjectOutputStream oos = new ObjectOutputStream(
											new BufferedOutputStream(
													new FileOutputStream("d:/D_Other/memObj.bin"), 5));
		
			// 쓰기 작업
			oos.writeObject(mem1); // 내부적으로 직렬화가 일어난다. 
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
		
			System.out.println("쓰기 작업 완료");
			oos.close();
		
			// ======================
			// 저장한 객체 읽어와 출력하기
			// 입력용 스트림 객체생성
			
			ObjectInputStream ois = new ObjectInputStream(
											new BufferedInputStream(
													new FileInputStream("d:/D_Other/memObj.bin"), 5));
			
			Object obj = null;
		
			while ((obj = ois.readObject()) != null) {
				// 읽어온 데이터를 원래의 객체형으로 변환
				Member mem = (Member) obj; // ClassNotFoundException가 일어날 수있다.
				System.out.println("이름 : " + mem.getName());
				System.out.println("나이 : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				System.out.println("------------------------------");
			}
			ois.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (IOException e) {
			// e.printStackTrace(); 실제 앱에서는 이 부분을 보여줄 필요없음.
			// 더이상 읽어올 객체가 없으면 예외 발생함.
			System.out.println("출력작업 끝...");
		}
	}
}

class Member implements Serializable {
	private transient String name;
	private transient int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
}