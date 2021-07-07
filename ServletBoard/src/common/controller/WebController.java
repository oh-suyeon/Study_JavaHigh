package common.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import common.handler.CommandHandler;
import common.handler.NullHandler;

public class WebController  extends HttpServlet{

	private static Logger logger = Logger.getLogger(WebController.class);
	
	
	public void init(ServletConfig config) throws ServletException{
		String configFilePath = config.getInitParameter("handler-config");
		
		Properties prop = new Properties();
		FileReader fr;
		
		try {
			fr = new FileReader(config.getServletContext().getRealPath(configFilePath));
			prop.load(fr);
			fr.close();
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		for(Object key : prop.keySet()) {
			String reqURL = (String)key;
			
			try {
				Class<?> klass = Class.forName(prop.getProperty(reqURL));
				CommandHandler handler = (CommandHandler) klass.newInstance();
				cmmHandlerMap.put(reqURL, handler);
			}catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}
		}
		Set<Map.Entry<String, CommandHandler>> entrySet = cmmHandlerMap.entrySet();
		for(Map.Entry<String, CommandHandler> entry : entrySet) {
			logger.info(entry.getKey() + " : " + entry.getValue());
		}
	}
	
	
	public Map<String, CommandHandler> cmmHandlerMap = new HashMap<>();
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		process(req, resp);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		process(req, resp);
	}
	
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String reqURI = req.getRequestURI();
		
		if(reqURI.indexOf(req.getContextPath())==0) {
			reqURI = reqURI.substring(req.getContextPath().length());
		}
		
		CommandHandler handler = cmmHandlerMap.get(reqURI);
		
		if(handler == null) {
			handler = new NullHandler();
		}
		
		if(logger.isInfoEnabled()) {
			logger.info("요청URL : " + reqURI);
			logger.info("핸들러 객체 : " + handler);
		}
		
		String viewPage = "";
		
		try {
			viewPage = handler.process(req, resp);
		}catch (Throwable e) {
			e.printStackTrace();
			throw new ServletException();
		}
		
		logger.info("viewPage 정보 : " + viewPage);
		
		if(viewPage != null) {
			if(handler.isRedirect(req)) {
				resp.sendRedirect(viewPage);
			}else {
				req.getRequestDispatcher(viewPage).forward(req, resp);
			}
		}
	}
}
