package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class T13_DataIOStreamTest {
	
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("d:/D_Other/test.dat");
		
		//DataOutStream은 출력용 데이터를 자료형에 맞게 출력한다.
		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.writeUTF("홍길동");
		dos.writeInt(17);
		dos.writeFloat(3.14f);
		dos.writeDouble(3.14);
		dos.writeBoolean(true);
		System.out.println("출력완료!");
		dos.close();
		
		// 출력 자료 읽어오기
		FileInputStream fis = new FileInputStream("d:/D_Other/test.dat");
		DataInputStream dis = new DataInputStream(fis);
		
		System.out.println("문자열 자료 : " + dis.readUTF());
		System.out.println("정수형 자료 : " + dis.readInt());
		System.out.println("실수형(Float) 자료 : " + dis.readFloat());
		System.out.println("실수형(Double) 자료 : " + dis.readDouble());
		System.out.println("논리형 자료 : " + dis.readBoolean());
		dis.close();
	}
	
}
