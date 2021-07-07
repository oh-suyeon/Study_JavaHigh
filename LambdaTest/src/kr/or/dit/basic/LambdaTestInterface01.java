package kr.or.dit.basic;

public interface LambdaTestInterface01 {
	// 반환값이 없고, 매개변수도 없는 추상메서드 선언
	public void test();
}

interface LambdaTestInterface02{
	// 반환값 없고 매개변수가 있는 추상메서드
	public void test(int a);
}

interface LambdaTestInterface03{
	// 반환값이 있고, 매개변수도 있는 추상메서드
	public int test(int a, int b);
}