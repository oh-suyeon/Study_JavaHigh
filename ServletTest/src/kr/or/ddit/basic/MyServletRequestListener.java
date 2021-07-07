package kr.or.ddit.basic;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener, ServletRequestAttributeListener {

	public MyServletRequestListener() {
		System.out.println("MyServletRequestListener 생성됨.");
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("[MyServletRequestListener] requestDestroyed() 호출됨.");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("[MyServletRequestListener] requestInitialized() 호출됨.");
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("[ServletRequestAttributeEvent] attributeAdded() 호출됨. => "
							+ srae.getName() + " : " + srae.getValue());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("[ServletRequestAttributeEvent] attributeRemoved() 호출됨. => "
							+ srae.getName() + " : " + srae.getValue());
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("[ServletRequestAttributeEvent] attributeReplaced() 호출됨. => "
							+ srae.getName() + " : " + srae.getValue());
	}
	
	
	
}
