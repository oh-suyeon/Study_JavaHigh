package kr.or.ddit.basic;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XPathParsing {
	
	public void parse() throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
		File file = new File(getClass().getResource("../../../../new_book.xml").getPath());
							// 클래스 객체를 갖고 온다. 나의 현재 클래스를 기준으로 찾는다. 그냥 절대 경로, 상대 경로를 넣어도 된다.
		FileReader fr = new FileReader(file); // xml 파일은 문자열이니까 reader
		
		//XML Document 객체 생성
		InputSource is = new InputSource(fr);
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		
		//Xpath객체 생성
		XPath xPath = XPathFactory.newInstance().newXPath();
		
		//NodeList 가져오기 : booklist 아래 모든 book 노드 선택
		NodeList bookList = (NodeList) xPath.evaluate("//booklist/book", document, XPathConstants.NODESET);
		
		System.out.println("//booklist/book 검색 결과...");
		System.out.println("---------------------------------------------------");
		
		for(int i = 0; i<bookList.getLength(); i++) {
			System.out.println(bookList.item(i).getTextContent());
		}
		System.out.println("---------------------------------------------------");
		
		// kind 속성이 JAVA인 모든 Node의 isbn attribute 값 가져오기
		Node node = (Node) xPath.evaluate("//*[@kind='JAVA']", document, XPathConstants.NODE);
		System.out.println("//*[@kind='JAVA'] => " + node.getAttributes().getNamedItem("isbn").getTextContent());
		
		// isbn이 B001인 Node의 textContent값 가져오기
		System.out.println("//*[@isbn='B001'] => " + xPath.evaluate("//*[@isbn='B001']", document, XPathConstants.STRING));
		
	}
	
	public static void main(String[] args) throws XPathExpressionException, IOException, SAXException, ParserConfigurationException {
		new XPathParsing().parse();
	}
	
}
