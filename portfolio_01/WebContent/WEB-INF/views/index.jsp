<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>탈모네 포트폴리오_01</title>
</head>
<body>
	<table border="2" width="1200" height="40" align="center">
		<tr>
			<td width="600" align="center">
				<a href="<c:url value='/index.do' />">H O M E</a>
			</td>
			<td width="600" align="center">
				<a href="<c:url value='/devNotes.do' />">개발노트</a>
			</td>
		</tr>
	</table>
	</br>
	<table border="1" width="1200" align="center">
		<tr>
			<!-- 메뉴항목이 보여지는 공간 -->
			<td width="300" height="100" align="center">
				<input type="button" value="준비중입니다." style="width:280;height:30;">
			</td>
			<!-- 게시물들이 보여지는 공간 -->
			<form>
				<td width="900" rowspan="5" align="center">
					<h1> Intro </h1><br/>
					이 페이지는 아래 항목을 이용하여 구현하였습니다. <br/>
					본 포트폴리오는 계속해서 수정/보완 개발 중에 있습니다. <br/>
					<br/>
					<li>Spring Framework 4.3.14.RELEASE</li> <br/>
					<li>myBatis 3.4.1</li> <br/>
					<li>MySql 8.0.11 </li> <br/>
					<li>jUnit 4.12</li> <br/>
					<li>AWS EC2 Instance </li> <br/>
					<h1>포트폴리오 URL 이전 안내 : </br>http://bit.ly/pf_youngmo</h1>
				</td>
			
			</form>
		</tr>
		<tr>
			<td width="300" height="100" align="center">
				<a href="<c:url value='/board/list.do' />">
				<input type="button" value="자유게시판" style="width:280;height:30;"></a>
				<br/><br/>
				<a href="<c:url value='/board/list2.do' />">
				<input type="button" value="자유게시판(Grid)" style="width:280;height:30;"></a>
			</td>
		</tr>
		<tr>
			<td width="300" height="100" align="center">
				<c:choose>
				<c:when test="${ sessionScope.memberId != null}">
					${sessionScope.memberId} 님 반갑습니다. <br/><br/>
					<a href="<c:url value='/member/logout.do' />">
						<input type="button" value="로그아웃" title="로그아웃" /></a>
				</c:when>
				<c:otherwise>
					<a href="<c:url value='login.do' />">
						<input type="button" value="로그인" title="로그인" /></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="<c:url value='/goJoin.do' />">
						<input type="button" value="회원가입" style="width:280;height:30;"></a>
				</c:otherwise> 
			</c:choose>
			</td>
		</tr>
		<tr>
			<td width="300" height="100" align="center">
				<c:if test="${ sessionScope.typeSeq == '9'}">
					[관리자전용 메뉴입니다] <br/><br/>
					<a href="<c:url value='/member/mListPage.do' />">
						<input type="button" value="회원관리" title="회원관리" /></a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td width="300" height="100" align="center">
					<input type="button" value="준비중입니다." style="width:280;height:30;">
			</td>
		</tr>
	</table>
	
</body>
</html>