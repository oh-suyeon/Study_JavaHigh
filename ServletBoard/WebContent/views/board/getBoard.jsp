<%@page import="board.vo.BoardVO"%>
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
	BoardVO boardVO = (BoardVO)request.getAttribute("boardVO");
	
	String boardListURL = request.getContextPath() + "/board/getBoardList.do";
	String updateURL = request.getContextPath() + "/board/updateBoard.do?boardNo=" + boardVO.getBoardNo();
%>

<script type="text/javascript">

	function deleteBoard() {
		if(confirm("삭제하시겠습니까?")){
			location.href = "<%=request.getContextPath() %>/board/deleteBoard.do?boardNo=<%=boardVO.getBoardNo()%>";
		}
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
		
		<h3 class="marginTop marginBottom cursorPoint" onclick="location.href='<%=boardListURL%>'">게시판📢</h3>
		
		<div>
			<table id="tb" class="table">
				<colgroup>
					<col style="width: 80px;">
					<col style="width: 60%;">
					<col style="width: 80px;">
					<col style="width: 120px;">
				</colgroup>
				<tbody>
					<tr>
						<th class="text-center">제목</th>
						<td><%=boardVO.getBoardTitle() %></td>
						<th class="text-center">작성일</th>
						<td><%=boardVO.getBoardDate() %></td>
					</tr>
					<tr>
						<td colspan="4" style="padding: 20px 115px;"><%=boardVO.getBoardContent() %></td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="text-right">
			<button type="button" class="btn btn-default" onclick="location.href='<%=boardListURL%>'">목록</button>
			<button type="button" class="btn btn-info" onclick="location.href='<%=updateURL %>'">수정</button>
			<button type="button" class="btn btn-warning" onclick="deleteBoard()">삭제</button>
		</div>
	</div>
</body>
</html>