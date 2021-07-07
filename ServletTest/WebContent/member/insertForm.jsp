<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>신규 회원 등록</title>
</head>
<body>
	<form action="<%= request.getContextPath()%>/member/insert" method="post">
		<table>
			<tr>
				<td>I D: </td>
				<td><input type="text" name="memId"></td>
			</tr>
			<tr>
				<td>이름: </td>
				<td><input type="text" name="memName"></td>
			</tr>
			<tr>
				<td>전화번호: </td>
				<td><input type="text" name="memTel"></td>
			</tr>
			<tr>
				<td>주소: </td>
				<td><textarea name="memAddr"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="회원 등록">
	</form>
</body>
</html>