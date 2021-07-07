package kr.or.ddit.basic;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener, ServletContextAttributeListener {

	public MyServletContextListener() {
		System.out.println("[MyServletContextListener]"
				+ "생성자 메서드 호출됨.");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// 지워질 때 뭘 할까
		System.out.println("[MyServletContextListener]"
				+ "contextDestroyed() 메서드 호출됨.");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 생길 때 뭘 할까
		System.out.println("[MyServletContextListener]"
				+ "contextInitialized() 메서드 호출됨.");
		
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		// 속성이 추가될때 뭘할까
		System.out.println("[MyServletContextListener]"
				+ "attributeAdded() 메서드 호출됨 : "
				+ scae.getName());
		
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		// 속성이 지워질 때 뭘할까
		System.out.println("[MyServletContextListener]"
				+ "attributeRemoved() 메서드 호출됨 : "
				+ scae.getName());
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		// 속성이 교체될 때 뭘할까
		System.out.println("[MyServletContextListener]"
				+ "attributeReplaced() 메서드 호출됨 : "
				+ scae.getName());
		
	}

}
