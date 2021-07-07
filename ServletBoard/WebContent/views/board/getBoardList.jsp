<%@page import="board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link href="<%=request.getContextPath() %>/css/common/common.css" type="text/css" rel="stylesheet">

<%
	List<BoardVO> boardList = (List<BoardVO>)request.getAttribute("boardList");

	String htmlStr = "";
	
	for(int i=0; i<boardList.size(); i++){
		
		String boardNo = boardList.get(i).getBoardNo();
		
		htmlStr += "<tr onclick='getBoard(" + boardNo + ");' class='cursorPoint'>"
				+ "<td>" + boardNo + "</td>"
				+ "<td>" + boardList.get(i).getBoardTitle() + "</td>"
				+ "<td>" + boardList.get(i).getBoardWriter() + "</td>"
				+ "<td>" + boardList.get(i).getBoardDate() + "</td>"
				+ "</tr>";
	}
	
	if(boardList.size()==0){
		htmlStr += "<tr>"
				+ "<td colspan='4'>" + "게시글이 존재하지 않습니다." + "</td>"
				+ "</tr>";
	}
%>
<%
	BoardVO boardVO = (BoardVO)request.getAttribute("boardVO");
	
	int firstPageNo = boardVO.getFirstPageNo();
	int countPerPage = boardVO.getCountPerPage();
	int lastPageNo = boardVO.getLastPageNo();
	int currentPageNo = boardVO.getCurrentPageNo();
	int totalPageCount = boardVO.getTotalPageCount();
	
	String searchURL = "searchOpt=" + request.getAttribute("searchOpt") + "&searchValue=" + request.getAttribute("searchValue") + "&flag=search";

	String prePageURL = request.getContextPath() + "/board/getBoardList.do?pageNo=" + (firstPageNo - countPerPage) + "&" + searchURL;
	String nextPageURL = request.getContextPath() + "/board/getBoardList.do?pageNo=" + (firstPageNo + countPerPage) + "&" + searchURL;
	String numPageURL = request.getContextPath() + "/board/getBoardList.do?"+ searchURL +"&pageNo=";
			
	String insertURL = request.getContextPath() + "/board/insertBoard.do";
%>
<script type="text/javascript">
	// 게시글 클릭하면 이동
	function getBoard(boardNo) {
		location.href = "<%=request.getContextPath()%>/board/getBoard.do?boardNo=" + boardNo;
	}
	
<%
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	if(msg.equals("success")){
%>
		alert("요청을 반영했습니다.");
<%		
	}else if(msg.equals("fail")){
%>
		alert("요청을 반영하지 못했습니다.");
<%		
	}
%>
	
</script>

</head>
<body>

	<div class="container">
	
		<h3 class="marginTop marginBottom cursorPoint" onclick="location.href='<%=request.getContextPath()%>/board/getBoardList.do'">게시판📢</h3>
		
		<div class="container row marginBottom">
			<form action="<%=request.getContextPath()%>/board/getBoardList.do">
			
				<div class="col-xs-8" style="padding:0px; text-align: right;">
					<div class="col-xs-10"></div>
					<div class="col-xs-2" style="padding:0px">
						<select class="form-control" name="searchOpt">
							<option value="">== 선택 ==</option>
							<option value="boardWriter">작성자</option>
							<option value="boardTitle">제목</option>
							<option value="boardContent">내용</option>
						</select>
					</div>
				</div>
				
				<div class="col-xs-4" style="padding:0px">
					<div class="col-xs-10" style="padding:0px">
						<div class="text-right">
							<input type="text" class="form-control form-inline" name="searchValue">
						</div>
					</div>
					
					<div class="col-xs-2 text-right" style="padding:0px">
						<button type="submit" class="btn btn-default">검색</button>
					</div>
					
					<input type="hidden" name="flag" value="search">
				</div>
				
			</form>
		</div>
		
		<div>
			<table id="tb" class="table table-hover text-center">
				<colgroup>
					<col style="width: 80px;">
					<col style="width: auto;">
					<col style="width: 200px;">
					<col style="width: 150px;">
				</colgroup>
				<thead>
					<tr>
						<th class="text-center">글번호</th>
						<th class="text-center">제목</th>
						<th class="text-center">작성자</th>
						<th class="text-center">작성일</th>
					</tr>
				</thead>
				<tbody>
					<%=htmlStr %>
				</tbody>
			</table>
		</div>
		
		<div class="text-right">
			<button type="button" class="btn btn-info" onclick="location.href='<%=insertURL%>'">등록</button>
		</div>
	</div>
	
	<div class="text-center">
		<ul class="pagination pagination-sm">
				<%if(firstPageNo > countPerPage){ %>
					<li class="page-item">
						<a href="<%=prePageURL%>">이전</a>
					</li>
				<%} %>
				<%for(int pNo = firstPageNo; pNo <= lastPageNo; pNo++){ %>
					<li>
						<a href="<%=numPageURL %><%=pNo %>"><%=pNo %></a>
					</li>
				<%} %>
				<%if(lastPageNo < totalPageCount){ %>
					<li>
						<a href="<%=nextPageURL %>">다음</a>
					</li>
				<%} %>
		</ul>
	</div>
	
	
</body>
</html>





























