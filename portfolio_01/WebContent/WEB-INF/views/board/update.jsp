<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>탈모네 포트폴리오_01 - 자유게시판 - 글 읽기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		console.log('브라우저 디버깅 수동 로그');
		var msg = '${ msg }';
		if(msg != '') {
			alert(msg);
		}
		$('#btnUpdate').on('click', function(){
			var frm = document.writeForm;
			var title = $('#title').val();
			var content = $('#content').val();
			
			if(title.length == 0){
				alert("제목을 입력해주세요,");
				return;
			}
			
			if(content.length == ''){
				alert("내용을 입력해주세요.");
				return;
			}
			
			frm.action = "<c:url value='/board/update.do' />"
			frm.method = "post";
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
			<form name="writeForm">
				<table border="1" width="800" height="" align="center">
						<input type="hidden" name="boardSeq" value="${getBoard.get('board_seq')}" />
						<input type="hidden" name="typeSeq" value="${getBoard.get('type_seq')}" />
						<input type="hidden" name="hasFile" value="0" />
						<input type="hidden" name="memberId" value="${ sessionScope.memberId }" />
					<tr>
						<td align="center" width="150">작성자</td>
						<td colspan="4" align="left">${ memberNick }</td>
					</tr>
					<tr>
						<td align="center">제 목</td>
						<td colspan="4" align="left"><input type="text" id="title" name="title" value="${ getBoard.get('title') }"/></td>
					</tr>
					<tr>
						<td align="center">작성일자</td>
						<td colspan="2" align="center">${ getBoard.get('create_date') }</td>
						<td align="center">조회수</td>
						<td align="center">${ getBoard.get('hits') }</td>
					</tr>
					<tr>
						<td align="center">수정일자</td>
						<td colspan="2" align="center">${ getBoard.get('update_date') }</td>
						<td align="center">추천수</td>
						<td align="center">0</td>
					</tr>
					<tr>
						<td align="center" height="150">내 용</td>
						<td height="150" colspan="4" ><textarea id="content" name="content" rows="10" cols="100">${ getBoard.get('content') }</textarea></td>
					</tr>
					<c:if test="${empty file}">
					<tr>
						<td align="center">첨부파일</td>
						<td colspan="4"><input type="file" id="file" name="attFile" title="첨부파일" /></td>
					</tr>
					</c:if>
					<c:forEach items="${file}" var="file" varStatus="vs">
						<tr>
						<td align="center">첨부파일${vs.count}</td>
						<td colspan="4">
							<a href="<c:url value='/board/download.do?fileIdx=${file.file_idx}' />">
								${file.file_name} (${file.file_size} bytes)
							</a>
							<a href="<c:url value='/board/deleteAttach.do?fileIdx=${file.file_idx}&boardSeq=${getBoard.get("board_seq")}&typeSeq=${getBoard.get("type_seq")}' />">
							<input type="button" id="btnDelete" value="삭제" align="right"/></a>
						</td>
						</tr>
					</c:forEach>
				</table>
				<br/>
				<table>
					<input type="button" id="btnUpdate" name="btnUpdate" value="수정완료" />				
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="<c:url value='read.do' />">
					<input type="button" value="취소" /></a>
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
			<td width="300" height="100" align="center">
					<input type="button" value="준비중입니다." style="width:280;height:30;">
			</td>
		</tr>
	</table>
	
</body>
</html>