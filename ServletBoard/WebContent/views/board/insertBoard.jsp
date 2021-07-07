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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>

<%
	String boardListURL = request.getContextPath() + "/board/getBoardList.do";
%>

</head>
<body>
	<div class="container">
		
		<h3 class="marginTop marginBottom cursorPoint" onclick="location.href='<%=boardListURL%>'">게시판📢</h3>
		
		<div>
			<form method="post" action="<%=request.getContextPath() %>/board/insertBoard.do" class="form-horizontal">
			
				<div class="form-group">
					<label for="boardWriter" class="col-sm-2 control-label">작성자</label>
					<div class="col-sm-4 ">
						<input type="text" name="boardWriter" id="boardWriter" class="form-control form-inline">
					</div>
				</div>
				<div class="form-group">
					<label for="boardTitle" class="col-sm-2 control-label">제목</label>
					<div class="col-sm-8 ">
						<input type="text" name="boardTitle" id="boardTitle" class="form-control form-inline">
					</div>
				</div>
				<div class="form-group">
					<label for="boardContent" class="col-sm-2 control-label">내용</label>
					<div class="col-sm-8 ">
						<textarea name="boardContent" id="boardContent" class="form-control form-inline" style="height: 200px;"></textarea>
					</div>
				</div>
				
				<div class="text-right col-sm-10" style="padding:0px;">
					<button type="button" onclick="location.href='<%=boardListURL%>'" class="btn btn-default">취소</button>
					<button type="submit" class="btn btn-info">등록</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>