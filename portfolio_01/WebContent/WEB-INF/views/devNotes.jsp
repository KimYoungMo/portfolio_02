<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery-ui/css/jquery-ui.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/css/common.css" />" />
<script type="text/javascript" src="<c:url value="/resources/jquery/js/jquery-3.2.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery-ui/js/jquery-ui.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery/js/jquery-migrate-1.4.1.js" />"></script>
<script type="text/javascript">
var size = false;
$(document).ready(function(){
	$('#erdDialog').hide();
	
	//Tab
	$( "#tabs" ).tabs();
	
	$('#btnErd').on('click', function(){
		$('#erdDialog').dialog({
			position : {my:"center top", at:"top", of:"#tabs"},
			open : function(){
				$('#erdDialog').show();
				if(!size){
				$('#erdDialog').dialog('option', 'width', $('#erdImage').width()*0.7);
				$('#erdDialog').dialog('option', 'height', $('#erdImage').height()*0.8);
				$('erfImage').css({width:'100%'});
				size = true;
				}
			}
		});
	});
	
	
});
</script>
<title>탈모네 포트폴리오_01 - 개발노트</title>
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
					<div id="tabs">
						<ul>
							<a href="#tabs-1">Note</a></li>
						</ul>
						<div id="tabs-1">
							<h3>[ 탈모네 포트폴리오 개발노트 ]</h3>
					  		<ul class="page_desc">
							<li>사이트 DB 모델링(JPG)
								<input type="button" id="btnErd" value="보기" />
							</li><br/>
							<li>사이트 DB 모델링(MWB)
								<a href="<c:url value='/file/downloadErd.do' />">
								<input type="button" value="다운로드" />
								</a>
							</li>
						</div>
						<div id="erdDialog" title="모델링JPG">
							<img id="erdImage" src="<c:url value='/resources/erd.JPG' />" />
						</div>
					</div>
				</td>
			
			</form>
		</tr>
		<tr>
			<td width="300" height="100" align="center">
				<a href="<c:url value='/board/list.do' />">
				<input type="button" value="자유게시판" style="width:280;height:30;">
				</a>
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
					<a href="<c:url value='/member/mList.do' />">
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