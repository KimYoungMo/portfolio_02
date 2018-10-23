<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- tag library 선언 : c tag --%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="kr">
<head>
<meta charset="UTF-8">
<title>로그인 & 회원가입</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login.css" />" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var panelOne = $('.form-panel.two').height(), 
			panelTwo = $('.form-panel.two')[0].scrollHeight;
	
		$('.form-panel.two').not('.form-panel.two.active').on('click', function(e) {
			e.preventDefault();
	
		    $('.form-toggle').addClass('visible');
		    $('.form-panel.one').addClass('hidden');
		    $('.form-panel.two').addClass('active');  
		    $('.form').animate({
		      'height': panelTwo
		    }, 200);
		});
	
		$('.form-toggle').on('click', function(e) {
		    e.preventDefault();
		    $(this).removeClass('visible');
		    $('.form-panel.one').removeClass('hidden');
		    $('.form-panel.two').removeClass('active');
		    $('.form').animate({
		      'height': panelOne
		    }, 200);
		});
		
		$('#btnReg').on('click', function(){
		var joinForm = document.joinForm;
		var userId = joinForm.userId.value;
		var userPw = joinForm.userPw.value;
		var userPw2 = document.getElementById("userPw2").value;
		var userName = joinForm.userName.value;
		var userNick = joinForm.userNick.value;
		var email = joinForm.email.value;
		var birthDate = joinForm.birthDate.value;
		
		if(userId == null || userId == ""){
			alert("아이디를 입력하세요.");
			return joinForm.userId.focus();
		}
		if(userId.length < 7  || userId.length > 12){
			alert("아이디는 7자 ~ 12자 사이만 가능합니다.");
			return joinForm.userId.focus();
		}
		if(userPw == null || userPw == ""){
			alert("비밀번호를 입력하세요.");
			return joinForm.userPw.focus();
		}
		if(userPw != userPw2){
			alert("비밀번호가 일치하지 않습니다.");
			return document.getElementById("userPw2").focus();
		}
		if(userName == null || userName == ""){
			alert("이름을 입력하세요.");
			return joinForm.userName.focus();
		}
		if(userNick == null || userNick == ""){
			alert("닉네임을 입력하세요.");
			return joinForm.userNick.focus();
		}
		if(birthDate == null || birthDate == ""){
			alert("생일을 입력하세요.");
			return joinForm.birthDate.focus();
		}
		
		// 회원가입 요청 전 비동기 통신으로 ID 중복검사
		$.ajax({
					url : '<c:url value="/member/checkId.do"/>',
					// url에 보낼 데이터 = 파라미터
					data : { memberId : $('#userId').val() },
					success : function(result, textStatus, XMLHttpRequest){ 
						alert("값이 도착했스무니다.");
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
	<!-- 디자인 출처 : http://www.blueb.co.kr/?c=2/34&where=subject%7Ctag&keyword=%EB%A1%9C%EA%B7%B8%EC%9D%B8&uid=4050 -->
	<!-- Form-->
	<div class="form">
		<div class="form-toggle"></div>
		<div class="form-panel one">
			<div class="form-header">
				<h1>Account Login</h1>
				<c:if test="${msg != null || msg != ''}">
					<b><font color="red">${msg}</font></b>
				</c:if>
			</div>
			<!-- 로그인 -->
			<div class="form-content">
				<form action="<c:url value='/member/login.do'/>" method="post">
					<input type="hidden" name="cmd" value="login" />
					<div class="form-group">
						<label for="username">User Id</label> 
						<input type="text" id="" name="userId" required="required" />
					</div>
					<div class="form-group">
						<label for="password">Password</label> 
						<input type="password" id="" name="userPw" required="required" />
					</div>
					<div class="form-group">
						<label class="form-remember"> 
							<input type="checkbox" />Remember Me
						</label>
						<a href="#" class="form-recovery">Forgot Password?</a>
					</div>
					<div class="form-group">
						<button type="submit">Log In</button>
					</div>
				</form>
			</div>
		</div>
		
		<!-- 회원가입 -->
		<div class="form-panel two">
			<div class="form-header">
				<h1>Register Account</h1>
			</div>
			<div class="form-content">
				<form name="joinForm">
					<input type="hidden" name="cmd" value="join" />
					<div class="form-group">
						<label for="username">User Id</label> 
						<input type="text" id="userId" name="userId" required="required" />
					</div>
					<div class="form-group">
						<label for="password">Password</label> 
						<input type="password" id="" name="userPw" required="required" />
					</div>
					<div class="form-group">
						<label for="cpassword">Confirm Password</label> 
						<input type="password" id="userPw2" required="required" />
					</div>
					<div class="form-group">
						<label for="name">Name</label> 
						<input type="text" id="" name="userName" required="required" />
					</div>
					<div class="form-group">
						<label for="nickname">Nickname</label> 
						<input type="nick" id="" name="userNick" required="required" />
					</div>
					<div class="form-group">
						<label for="email">e-mail</label> 
						<input type="email" id="" name="email" required="required" />
					</div>
					<div class="form-group">
						<label for="birthDate">Birth Date(yyyy-mm-dd)</label> 
						<input type="birthDate" id="" name="birthDate" required="required" />
					</div>
					<div class="form-group">
						<button type="button" id="btnReg" >Register</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>