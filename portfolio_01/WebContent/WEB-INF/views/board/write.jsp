<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>탈모네 포트폴리오_01 - 자유게시판 - 글쓰기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		console.log('브라우저 디버깅 수동 로그');
		var msg = '${ msg }';
		if(msg != '') {
			alert(msg);
		}
		
		$('#btnWrite').on('click', function(){
			var frm = document.writeForm;
			var title = $('#title').val();
			if(title.length == 0){
				alert("제목을 입력하세요.");
				$('#title').focus();
				return;
			}
			
			if(contents.length == 0){
				alert("내용을 입력하세요.");
				frm.contents.focus();
				return;
			}
			
			frm.action = "<c:url value='/board/write.do'/>";
			frm.submit();
			
		});
	
	});
	
</script>
</head>
<body>
	<table border="2" width="1200" height="40" align="center">
		<tr>
			<td width="600" align="center">
				<a href="<c:url value='/index.do' />">H O M E</a>
			</td>
			<td width="600" align="center">개발노트</td>
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
				<td width="900" rowspan="5" align="center">
			<!-- board area -->
			<form name="writeForm" method="post" enctype="multipart/form-data">
				<table border="1" width="800" height="" align="center">
						<input type="hidden" name="typeSeq" value="2" />
						<input type="hidden" name="hasFile" value="0" />
						<input type="hidden" name="memberIdx" value="${ sessionScope.memberIdx }" />						
						<input type="hidden" name="memberId" value="${ sessionScope.memberId }" />
					<tr>
						<td align="center" width="150">작성자</td>
						<td colspan="4" align="left"><input type="text" name="memberNick" readonly="readonly" value="${ memberNick }" /></td>
					</tr>
					<tr>
						<td align="center">제 목</td>
						<td colspan="4" align="left"><input type="text" id="title" name="title" /></td>
					</tr>
					<tr>
						<td align="center" height="150">내 용</td>
						<td height="150" colspan="4" >
							<textarea id="contents" name="contents" rows="15" cols="100"></textarea>
						</td>
					</tr>
					<tr>
						<td align="center">첨부파일</td>
						<td colspan="4"><input type="file" id="file" name="attFile" title="첨부파일" />
						${file.file_name} (${file.file_size} bytes)</td>
					</tr>
				</table>
				<br/>
				<table>
					<input type="button" id="btnWrite" name="btnWrite" value="작성완료" />				
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="<c:url value='list.do' />">
					<input type="button" value="작성취소" />
					</a>
				</table>
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
						<input type="button" value="로그아웃" title="로그아웃" />
					</a>
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
			<td width="300" height="100"></td>
		</tr>
	</table>
	
</body>
</html>