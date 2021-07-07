package kr.or.dit.basic;

public class LambdaTest03 {
	
		static int stVar = 9;
		private String name = "aaa";
		
		
		public void testMethod(final int temp) {
			final int localVar = 50;
			int kor = 100;
			
			//람다식 내부에서 사용하는 지역변수는 모두 final이어야 한다.
			//보통 final을 붙이지 않으면 컴파일러가 자동으로 붙여준다.
			//단 지역변수의 값을 변경하는 식이 있을 경우 자동으로 붙여주지 않는다.
			
//			temp = 500; 지역변수는 final로 이미 값이 있어서 오류가 난다.
//			localVar = 2000;
//			kor = 400;
			
			// 람다식에서 지역변수 사용하기
			
			LambdaTestInterface01 lt = 
					()->{
						System.out.println("temp = "+ temp);
						System.out.println("localVar = " + localVar);
						System.out.println("kor = " + kor);
						System.out.println("stVar = " + stVar);
						System.out.println("name = " + this.name); // 필드 값. 멤버 변수. 
					};
				lt.test();
		}
		
		public static void main(String[] args) {
			new LambdaTest03().testMethod(200);
		}
		
	
}
