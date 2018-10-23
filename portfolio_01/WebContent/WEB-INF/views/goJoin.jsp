<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>탈모네 회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	
	$('#btnReg').on('click', function(){
		var joinForm = document.joinForm;
		var memberId = joinForm.memberId.value;
		var memberPw = joinForm.memberPw.value;
		var memberPw2 = document.getElementById("memberPw2").value;
		var memberName = joinForm.memberName.value;
		var memberNick = joinForm.memberNick.value;
		var email = joinForm.email.value;
		var birth = joinForm.birth.value;
		
		var regExp = /^([_]?[a-zA-z0-9]){6,12}$/;
		
		if(memberId == null || memberId == ""){
			alert("아이디를 입력해주세요.");
			return joinForm.memberId.focus();
		}
		if(memberId.match(regExp) == null){
			alert("아이디는 영문, 숫자 조합으로만 만들 수 있습니다.");
			return joinForm.memberId.focus();
		}
		if(memberId.length < 6 || memberId.length > 12){
			alert("아이디는 6자 ~ 12자까지만 가능합니다.");
			return joinForm.memberId.focus();
		}
		if(memberPw == null || memberPw == ""){
			alert("비밀번호를 입력하세요.");
			return joinForm.memberPw.focus();
		}
		if(memberPw != memberPw2){
			alert("비밀번호가 일치하지 않습니다.");
			return document.getElementById("memberPw2").focus();
		}
		if(memberName == null || memberName == ""){
			alert("이름을 입력하세요.");
			return joinForm.memberName.focus();
		}
		if(memberNick == null || memberNick == ""){
			alert("닉네임을 입력하세요.");
			return joinForm.memberNick.focus();
		}
		if(email == null || email == ""){
			alert("이메일을 입력하세요.");
			return joinForm.email.focus();
		}
		var regExp =
			/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{3,4}$/i;
			
		if(email.match(regExp) == null){
			alert("입력하신 이메일 형식이 올바르지 않습니다. \n다시 입력해주세요. \nex) sample@sample.xxx");
			$('#email').focus();
			return;
		}
		if(birth == null || birth == ""){
			alert("생일을 입력하세요.");
			return joinForm.birth.focus();
		}

		// 회원가입 요청 전 비동기 통신으로 ID 중복검사
		$.ajax({
			url : '<c:url value="/member/checkId.do"/>',
			// url에 보낼 데이터 = 파라미터
			data : { memberId : $('#memberId').val() },
			success : function(result, textStatus, XMLHttpRequest){ 
				if(result == 0){
					// 회원가입 요청
					var frm = document.joinForm;
					frm.action = "<c:url value='/member/join.do'/>";
					frm.method = "post";
					frm.submit();

				} else {
					alert("중복된 ID입니다. 다른 ID로 생성해주세요.");
				}
				
				console.log(result);
				console.log(textStatus);
				console.log(XMLHttpRequest);
			},
			// success 끝
			error : function() {
				
				
			}
			// error 끝
		});
		
	})
});
	
</script>
</head>
<body>
	</br>
	<table border="1" align="center" width="400" height="40">
	<form name="joinForm">
		<tr>
			<td colspan="2" align="center" height="40"><b>탈모네 회원가입</b></td>
		</tr>
		<tr>
			<td align="center" width="150" height="40">아이디</td>
			<td align="center" width="250"><input type="text" id="memberId" name="memberId"></td>
		</tr>
		<tr>
			<td align="center" width="150" height="40">비밀번호</td>
			<td align="center" width="250"><input type="password" name="memberPw"></td>
		</tr>
		<tr>
			<td align="center" width="150" height="40">비밀번호 확인</td>
			<td align="center" width="250"><input type="password" id="memberPw2" name="memberPw2"></td>
		</tr>
		<tr>
			<td align="center" width="150" height="40">이 름</td>
			<td align="center" width="250"><input type="text" name="memberName"></td>
		</tr>
		<tr>
			<td align="center" width="150" height="40">닉네임</td>
			<td align="center" width="250"><input type="text" name="memberNick"></td>
		</tr>
		<tr>
			<td align="center" width="150" height="40">이메일</td>
			<td align="center" width="250"><input type="text" name="email"></td>
		</tr>
		<tr>
			<td align="center" width="150" height="40">생년월일</br>(0000-00-00)</td>
			<td align="center" width="250"><input type="text" name="birth"></td>
		</tr>
	</form>
	</table>
		<p align="center">
		<input type="button" id="btnReg" value="가입하기" align="center"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="<c:url value='/index.do' />">
		<input type="button" value="취소" align="center"/>
		</a>
		</p>
</body>
</html>