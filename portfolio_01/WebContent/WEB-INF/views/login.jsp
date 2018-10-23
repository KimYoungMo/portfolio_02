<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>탈모네 로그인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	
		$('#btnLogin').on('click', function(){
			var loginForm = document.loginForm;
			var memberId = loginForm.memberId.value;
			var memberPw = loginForm.memberPw.value;
			
			if(memberId == null || memberId == ""){
				alert("ID를 입력해주세요.");
				return loginForm.memberId.focus();
			}
			if(memberPw == null || memberPw == ""){
				alert("비밀번호를 입력해주세요.");
				return loginForm.memberPw.focus();
			}

			loginForm.action = "<c:url value='/member/login.do'/>";
			loginForm.method = "post";
			loginForm.submit();
		});
		
		
	});


</script>
</head>
<body>
	<form name="loginForm">
	</br>
	<table border="1" align="center" width="600">
		<tr>
			<td colspan="2" align="center"><h3>탈모네 로그인</h3></td>
		</tr>
		<tr>
			<td colspan="2" align="center" height="30"><span style="color:red"><b>${msg}</b></td>
		</tr>
		<tr>
			<td align="center" height="50">아이디</td>
			<td align="center" height="50"><input type="text" name="memberId" /></td>
		</tr>
		<tr>
			<td align="center" height="50">비밀번호</td>
			<td align="center" height="50"><input type="password" name="memberPw" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center" height="40">
				<input type="button" id="btnLogin" value="로그인" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="비밀번호 찾기" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="<c:url value='/index.do' />"><input type="button" value="취소" /></a>
			</td>
		</tr>
	</form>
	</table>
</body>
</html>