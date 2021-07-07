package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T18_SyncCollectionTest {
	
	//동기화 처리 X
	private static List<Integer> list1 = new ArrayList<Integer>(); 
	// add 할때마다 size가 자동으로 늘어난다. 그런데 5개의 thread가 동시다발적으로 값을 넣으면 size가 늘어나기도 전에 값을 넣는 일이 벌어지고 에러가 난다.
	// 동기화 처리가 필요
	
	//동기화 처리 O
	//Collections 정적 메서드중 Synchronized로 시작하는 메서드 사용
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());
	
	public static void main(String[] args) {
		
		//익명 클래스로 스레드 구현
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i = 1; i <= 10000; i++) {
					list2.add(i); // 동기화 안 한 리스트
				}
			}
		};
		
		Thread[] ths = new Thread[] {
				new Thread(r), new Thread(r),
				new Thread(r), new Thread(r), new Thread(r)
		};
		
		for(Thread th : ths) {
			th.start();
		}
		
		for(Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		System.out.println("list1의 개수 : " + list1.size());
		System.out.println("list2의 개수 : " + list2.size());
		
	}
	
}

