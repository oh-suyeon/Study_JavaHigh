package kr.or.ddit.basic;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class T03_ResourceBundleTest {
	
	public static void main(String[] args) {
		// ResourceBundle 객체생성 (파일 지정시 '파일명'만 지정하고 확장자는 지정하지않는다. 확장자는 항상 properties이기 때문)
		
		System.out.println(Locale.getDefault());
		ResourceBundle bundle = ResourceBundle.getBundle("db");
//		ResourceBundle bundle = ResourceBundle.getBundle("db", Locale.JAPANESE);
		
		// key값들만 읽어오기
		Enumeration<String> keys = bundle.getKeys();
		
		// key값 개수만큼 반복해서 value값 가져오기
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			
			// key 값을 이용해 value값을 읽어오는 방법
			// ==> bundle 객체 변수. getString(key값);
			String value = bundle.getString(key);
			
			System.out.println(key + " = >" + value);
		}
		System.out.println("출력 끝...");
	}
	
}
