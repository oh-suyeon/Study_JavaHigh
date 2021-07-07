package practice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Member;

public class P09_ObjectStream {
	
	public static void main(String[] args) {
		Guest g1 = new Guest(101, "김김김");
		Guest g2 = new Guest(102, "오오오");
		Guest g3 = new Guest(103, "사사사");
		
		try {
			FileOutputStream fos = new FileOutputStream("d:/D_Other/guest_test.bin");
			BufferedOutputStream bos = new BufferedOutputStream(fos, 5);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			
			oos.writeObject(g1);
			oos.writeObject(g2);
			oos.writeObject(g3);
			
			System.out.println("다썼어");
			oos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			FileInputStream fis = new FileInputStream("d:/D_Other/guest_test.bin");
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			Object obj = null;
			
			while ((obj = ois.readObject()) != null) {
				Guest g = (Guest) obj;
				System.out.println("방번호 : " + g.getRoomK());
				System.out.println("이름 : " + g.getName());
				System.out.println("----------------------");
			}
			ois.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("다했어");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

class Guest implements Serializable {
	private int roomK;
	private String name;
	
	public Guest(int roomK, String name) {
		super();
		this.roomK = roomK;
		this.name = name;
	}

	public int getRoomK() {
		return roomK;
	}
	public void setRoomK(int roomK) {
		this.roomK = roomK;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
