package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class T02_FileTest {
	
	public static void main(String[] args) {
		File f1 = new File("d:/D_Other/sample.txt");
		File f2 = new File("d:/D_Other/test.txt");
		
		if(f1.exists()) {
			System.out.println(f1.getAbsolutePath() + "은 존재한다.");				
		} else {
			System.out.println(f1.getAbsolutePath() + "은 존재하지 않는다.");
			
			try {
				if(f1.createNewFile()) {
					System.out.println(f1.getAbsolutePath() + "파일을 새로 만들었다.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(f2.exists()) {
			System.out.println(f2.getAbsolutePath() + "은 존재한다.");
		} else {
			System.out.println(f2.getAbsolutePath() + "은 존재하지 않는다.");
		}
		System.out.println("----------------------------");
		
		File f3 = new File("d:/D_Other");
		File[] files = f3.listFiles();
		for(int i = 0; i < files.length; i++) {
			System.out.print(files[i].getName() + "=>");
			if(files[i].isFile()) {
				System.out.println("파일");
			} else if(files[i].isDirectory()) {
				System.out.println("디렉토리");
			}
		}
		System.out.println("----------------------------");
		String[] strFiles = f3.list();
		for(int i = 0; i < strFiles.length; i++) {
			System.out.println(strFiles[i]);
		}
		System.out.println("----------------------------");
		System.out.println();
		
		// 출력할 디렉토리 정보를 갖는 file 객체 생성
		File f4 = new File("C:\\Users\\PC-08\\Documents"); 
		
		displayFileList(f4);
		
	}
	
	private static void displayFileList(File dir) {
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리내용");
		
		// 디렉토리 안 모든 파일 목록 가져오기
		File[] files = dir.listFiles();
		
		// 하위 디렉토리 정보 저장할 List 객체 생성 (File 배열의 인덱스 저장)
		List<Integer> subDirList = new ArrayList<Integer>();
		
		// 날짜를 출력하기 위한 형식 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		
		for(int i = 0; i < files.length; i++) {
			String attr = ""; // 파일 속성 (읽기, 쓰기, 히든, 디렉토리 구분)
			String size = ""; // 파일 용량
			if(files[i].isDirectory()) {
				attr = "<DIR>";
				subDirList.add(i); //인덱스 정보 List에 추가
			} else {
				size = files[i].length() + " ";
				attr += files[i].canRead() ? "R" : " ";
				attr += files[i].canWrite() ? "W" : " ";
				attr += files[i].isHidden() ? "H" : " ";
			} 
			System.out.printf("%s %-5s %12s %s\n", 
				sdf.format(new Date(files[i].lastModified())),
				attr, size, files[i].getName());
		}
		int dirCount = subDirList.size();
		int fileCount = files.length - dirCount;
		
		System.out.println(fileCount + "개의 파일, " + dirCount + "개의 디렉토리");
		
		System.out.println();
		
		for(int i = 0; i < subDirList.size(); i++) {
			// 하위 폴더의 내용들도 출력하기 위해 현재의 메서드를 재귀호출하여 처리.
			displayFileList(files[subDirList.get(i)]);
			
		}
	}
	
	
}
