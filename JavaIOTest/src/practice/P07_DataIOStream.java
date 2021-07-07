package practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class P07_DataIOStream {

	public static void main(String[] args) throws IOException {
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/test2.dat");
		
		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.writeUTF("홍길동");
		dos.writeInt(12313);
		dos.writeFloat(3.14f);
		dos.writeDouble(3.14);
		dos.writeBoolean(true);
		System.out.println("출력함");
		dos.close();
		
		FileInputStream fis = new FileInputStream("d:/D_Other/test2.dat");
		
		DataInputStream dis = new DataInputStream(fis);
		
//		System.out.println(dis.readUTF());
		System.out.println(dis.readInt());
		System.out.println(dis.readFloat());
//		System.out.println(dis.readDouble());
//		System.out.println(dis.readBoolean());
		dis.close();
		
	}
	
}
