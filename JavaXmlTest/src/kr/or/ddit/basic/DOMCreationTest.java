package kr.or.ddit.basic;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * XML DOM을 이용한 문서 생성 예제
 * @author PC-08
 *
 */
public class DOMCreationTest {
	
	//W3C(World Wide Web Consortium) DOM(Document Object Model)
	
	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		//XML 문서를생성하기 위한 DocumentBuilder 객체 생성
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		
		//Document(xml 문서) 객체 생성  == 이걸 만들고 싶어서 builder, builderfactory를 만든 것. 
		Document document = documentBuilder.newDocument();
		
		//root 엘리먼트 생성
		Element root = document.createElement("data");
		
		//booklist 엘리먼트 생성
		Element booklist = document.createElement("booklist");
		
		//book 엘리먼트 생성 및 속성 값 설
		Element book = document.createElement("book");
		book.setAttribute("isbn", "B001");
		book.setAttribute("kind", "JAVA");
		
		//자식 엘리먼트 생성 및 설정
		Element title = document.createElement("title");
		title.setTextContent("자바초급");
		Element author = document.createElement("author");
		author.setTextContent("이순신");
		Element price = document.createElement("price");
		price.setTextContent("25000");
		
		//book 엘리먼트에 자식 엘리먼트 추가
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		//booklist에 book 추가
		booklist.appendChild(book);
		
		//----------------------------------------------------------------
		// book 엘리먼트 생성 및 속성 값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B002");
		book.setAttribute("kind", "JAVA");
		
		//자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("자바고급");
		author = document.createElement("author");
		author.setTextContent("이몽룡");
		price = document.createElement("price");
		price.setTextContent("30000");
		
		//book 엘리먼트에 자식 엘리먼트 추가
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		//booklist에 book 추가		
		booklist.appendChild(book);
		
		//----------------------------------------------------------------
		// book 엘리먼트 생성 및 속성 값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B003");
		book.setAttribute("kind", "DB");
		
		//자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("디비설계");
		author = document.createElement("author");
		author.setTextContent("성춘향");
		price = document.createElement("price");
		price.setTextContent("40000");
		
		//book 엘리먼트에 자식 엘리먼트 추가
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		//booklist에 book 추가		
		booklist.appendChild(book);
		
		//----------------------------------------------------------------
		// book 엘리먼트 생성 및 속성 값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B004");
		book.setAttribute("kind", "DB");
		
		//자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("디비구현");
		author = document.createElement("author");
		author.setTextContent("강감찬");
		price = document.createElement("price");
		price.setTextContent("35000");
		
		//book 엘리먼트에 자식 엘리먼트 추가
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		//booklist에 book 추가		
		booklist.appendChild(book);
		
		//----------------------------------------------------------------
		
		// 루트 엘리먼트에 booklist 추가하기
		root.appendChild(booklist);
		
		// 도큐먼트에 root 엘리먼트 추가하기
		document.appendChild(root);
		
		// XML 문자열로 변환하기
		ByteArrayOutputStream boas = new ByteArrayOutputStream();
		
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(boas);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		
		//출력 인코딩설정
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
		//들여쓰기 설정(공백 크기: 2)
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		
		transformer.transform(source, result);
		
		try(FileOutputStream fos = new FileOutputStream("./src/new_book.xml")){
			
			fos.write(boas.toByteArray());
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
