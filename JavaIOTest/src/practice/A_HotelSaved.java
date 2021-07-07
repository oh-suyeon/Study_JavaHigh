package practice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class A_HotelSaved {
	
	public static void main(String[] args) {
		
		Hotel h = new Hotel();
		h.open();
		
		while(true) {
			switch(h.work()) {
				case 1 : h.checkIn();
						 break;
				case 2 : h.checkOut();
						 break;
				case 3 : h.manageRoom();
						 break;
				case 4 : h.close();
				
				default : System.out.println("잘못된 입력입니다.");
						  break;
			}
		}
	}
}

class Hotel {
	
	private Map<Integer, hGuest> hGuests = new HashMap<>();
	
	public void open() {
		System.out.println("*********************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("*********************************");
		
		// 파일을 읽어와 hGuests 객체로 만들기 (입력)
		File file = new File("d:/D_Other/hGuests.bin");
		if(file.exists()) {
			try {
				FileInputStream fis = new FileInputStream("d:/D_Other/hGuests.bin");
				BufferedInputStream bis = new BufferedInputStream(fis);
				ObjectInputStream ois = new ObjectInputStream(bis);
				
				Object obj = null;
				
				while((obj = ois.readObject()) != null) {
					hGuest g = (hGuest)obj;
					hGuests.put(g.getRoomKey(), g);
				}
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("투숙객 목록을 불러왔습니다.");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public int work() {
		System.out.println();
		System.out.println("*********************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃  3.객실상태  4.업무종료");
		System.out.println("*********************************");
		System.out.println("메뉴선택 => ");
		int input = ScanUtil.nextInt();
		return input;
	}
	
	public void checkIn() {
		System.out.println();
		System.out.println("*********************************");
		System.out.println("어느 방에 체크인 하시겠습니까?");
		System.out.println("*********************************");
		int roomK = 0;
		while(true) {
			System.out.println("방 번호 입력 =>");
			roomK = ScanUtil.nextInt();
			if(hGuests.get(roomK) != null) {
				System.out.println(roomK + "실은 체크인된 객실입니다.");
			} else {
				break;
			}
		}
		System.out.println();
		System.out.println("*********************************");
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.println("*********************************");
		System.out.println("이름 입력 => ");
		String name = ScanUtil.nextLine();
				
		hGuests.put(roomK, new hGuest(roomK, name));	
		System.out.println("체크인 되었습니다.");
	}
	
	public void checkOut() {
		System.out.println();
		System.out.println("*********************************");
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.println("*********************************");
		int roomN = 0;
		while(true) {
			System.out.println("방 번호 입력 =>");
			roomN = ScanUtil.nextInt();
			if(hGuests.get(roomN) == null) {
				System.out.println(roomN + "실은 비어있는 객실입니다.");
			} else {
				break;
			}
		}
		hGuests.remove(roomN);
		System.out.println("체크아웃 되었습니다.");
	}
	
	public void manageRoom() {
		System.out.println();
		System.out.println("*********************************");
		if(hGuests.size() > 0) {
			for(int roomK : hGuests.keySet()) {
				System.out.println("방번호 : " + hGuests.get(roomK).getRoomKey());
				System.out.println("투숙객 : " + hGuests.get(roomK).getName());
				System.out.println();
			}
		} else {
			System.out.println("투숙객이 없습니다.");
		}
		System.out.println("*********************************");
	}
	
	public void close() {
		System.out.println("*********************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("*********************************");
		
		// hGuests 객체를 파일로 만들기 (출력)
		try {
			FileOutputStream fos = new FileOutputStream("d:/D_Other/hGuests.bin");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			
			for(int roomK : hGuests.keySet()) {
				oos.writeObject(hGuests.get(roomK));
			}
			bos.flush();
			System.out.println("투숙객 목록을 파일로 저장하였습니다.");
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.exit(0);
	}
}


class hGuest implements Serializable {
	private int roomKey;
	private String name;
	
	public hGuest(int roomKey, String name) {
		super();
		this.roomKey = roomKey;
		this.name = name;
	}

	public int getRoomKey() {
		return roomKey;
	}

	public void setRoomKey(int roomKey) {
		this.roomKey = roomKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}