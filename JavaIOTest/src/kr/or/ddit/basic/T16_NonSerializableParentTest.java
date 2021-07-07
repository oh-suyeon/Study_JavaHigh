package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T16_NonSerializableParentTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/nonSerializableTest.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		oos.writeObject(child);
		oos.close();
		
		FileInputStream fis = new FileInputStream("d:/D_Other/nonSerializableTest.bin");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Child child2 = (Child) ois.readObject();//역직렬화
		
		System.out.println("parentName : " + child2.getParentName());
		System.out.println("childName : " + child2.getChildName());
		
		ois.close();
	}
}
//Serializable 구현하지 않은 부모클래스
class Parent {
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}

//Serializable 구현한 자식 클래스
class Child extends Parent implements Serializable {
	
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	
	// 직렬화 될 때 자동으로 호출되는 메서드. 접근 제한자가 private 이 아니면 자동 호출되지 않음.
	private void writeObject(ObjectOutputStream out) throws IOException {
		//자동으로 호출되는 이 메서드에 수동으로 IO작업을 해준다.
		out.writeUTF(getParentName()); // 우리가 추가한 부분. 부모도 직렬화된 것처럼 저장하려고. 
		out.defaultWriteObject(); // 자동호출되는 기본적인, 원래의 기능
	}
	
	// 역직렬화 될 때 자동으로 호출되는 메서드. 접근 제한자가 private 이 아니면 자동 호출되지 않음.
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		setParentName(in.readUTF());
		in.defaultReadObject();
	}
}