package kr.or.ddit.basic;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * XML DOM 을 이용한 XML 문서 파싱 예제(레시피 정보 조회 API)
 * @author macween
 *
 */
public class DOMParsingExam {

	public void parse(){
		try
        {
             //DOM Document 객체 생성하기 위한 메서드
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

             //DOM 파서로부터 입력받은 파일을 파싱하도록 요청
             DocumentBuilder builder = dbf.newDocumentBuilder();

            String svcKey = "Grid_20150827000000000227_1";  // 레시피 재료 정보 조회 서비스
     		String apiKey = "1df7e8571e8df3f8cbc9b87691ca7d3e4d04f03c593d477e52bf67b03f0b6e1c"; // 개인별 발급.
     		String startIdx = "1";  	// 레시피 재료 시작 순번
     		String endIdx = "5";		// 레시피 재료 종료 순번
     		String recipeId = "195428";	// 래시피가 궁금한 음식 ID

     		URL url = new URL("http://211.237.50.150:7080/openapi/"+ apiKey
     				+ "/xml/"+ svcKey + "/"+startIdx +"/" + endIdx
     				+"?RECIPE_ID=" +  recipeId);
     		
     		System.out.println(url);
     		
             //DOM 파서로부터 입력받은 파일을 파싱하도록 요청 (DOM Document object를 리턴함.)
             Document xmlDoc = builder.parse(url.toString()); // http...를 집어 넣어서 document 객체를 만들어오는 것.

             // DOM Document 객체로부터 루트 엘리먼트 및 자식 객체 가져오기
             Element root = xmlDoc.getDocumentElement();
             System.out.println("루트 엘리먼트 태그명 : " + root.getTagName());    //Grid_20150827000000000227_1

             // 하위 엘리먼트 접근방법1 : getElementsByTagName() 메서드를 이용
             NodeList rowNodeList = root.getElementsByTagName("row");

            String code = root.getElementsByTagName("code").item(0).getTextContent();
            String totalCnt = root.getElementsByTagName("totalCnt").item(0).getTextContent();
            endIdx = totalCnt;

            url = new URL("http://211.237.50.150:7080/openapi/"+ apiKey
     				+ "/xml/"+ svcKey + "/"+startIdx +"/" + endIdx
     				+"?RECIPE_ID=" +  recipeId);

             xmlDoc = builder.parse(url.toString());

             root = xmlDoc.getDocumentElement();

             rowNodeList = root.getElementsByTagName("row");

            if(code.equals("INFO-000")) {

            	for(int i = 0; i < rowNodeList.getLength(); i++) {
            		Element element = (Element) rowNodeList.item(i);
            		String rowNum = element.getElementsByTagName("ROW_NUM").item(0).getTextContent();
            		String irdntNm = element.getElementsByTagName("IRDNT_NM").item(0).getTextContent();
            		String irdntCpcty = element.getElementsByTagName("IRDNT_CPCTY").item(0).getTextContent();
            		String irdntTypNm = element.getElementsByTagName("IRDNT_TY_NM").item(0).getTextContent();
            		String str = String.format("%3s %8s %10s %10s %8s", rowNum, recipeId, irdntTypNm, irdntNm, irdntCpcty);
            		System.out.println(str);
            		System.out.println("------------------------------------------------------------------");
            	}
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		DOMParsingExam parser = new DOMParsingExam();
		parser.parse();
	}
}
