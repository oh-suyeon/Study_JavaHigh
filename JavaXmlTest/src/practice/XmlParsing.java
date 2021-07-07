package practice;

import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlParsing {
	
	public void parse() {
		
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			
			String apiKey = "a80c3fa1001975121d8dddf35b4eafe66af1a697a26229cea01bd3ef30d9ba09";
			Integer pageNum = 1;
			Integer paseSize = 10;
			String category = URLEncoder.encode("도서", "UTF-8");
			String kwd = URLEncoder.encode("클린 코드", "UTF-8");
			
			URL url = new URL("https://www.nl.go.kr/NL/search/openApi/search.do?"
							+ "key=" + apiKey
							+ "&pageNum=" + pageNum
							+ "&pageSize" + paseSize
							+ "&apiType=xml"
							+ "&srchTarget=total"
							+ "&kwd=" + kwd
							+ "&category=" + category
							+ "&sort=ipub_year"
							+ "&order=desc"
							);
			
			Document xmlDoc = builder.parse(url.toString());
			
			Element root = xmlDoc.getDocumentElement();
			
			NodeList nodeList = root.getElementsByTagName("item");
			
			
			for(int i=0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				String title = element.getElementsByTagName("title_info").item(0).getTextContent();
				String author = element.getElementsByTagName("author_info").item(0).getTextContent();
				String pubyearinfo = element.getElementsByTagName("pub_year_info").item(0).getTextContent();
				String licyn = element.getElementsByTagName("lic_yn").item(0).getTextContent();
				String isbn = element.getElementsByTagName("isbn").item(0).getTextContent();
				System.out.println("제목 : " + title);
				System.out.println("작가 : " + author);
				System.out.println("발행년도 : " + pubyearinfo);
				System.out.println("저작권 유무 : " + licyn);
				System.out.println("isbn : " + isbn);
				System.out.println("========================================");
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new XmlParsing().parse();
	}
	
}
