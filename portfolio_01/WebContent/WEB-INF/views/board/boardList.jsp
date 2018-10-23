<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>탈모네 포트폴리오_01 - 자유게시판</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var msg = '${ msg }';
		if(msg != '') {
			alert(msg);
		}
		
		// 검색버튼 클릭
		$('#btnSearch').click(function(){
			var searchType = $('#searchType option:selected').val();
			var searchText = $('#searchText').val();
			
			if(searchText == ''){
				alert("검색어를 입력하세요.");
				return;
			}
			if(searchText.length < 2){
				alert("두 글자 이상 입력하세요.");
				return;
			}
			
			// submit 하기
			var frm = document.searchForm;
			frm.action = "<c:url value='/board/list.do' />";
			frm.submit();
		
		})
	
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
				
				<!-- board search -->
				<form name="searchForm" method="get" align="right">
					<select id="searchType" name="searchType" title="선택메뉴">
						<option value="1" selected="selected">전체</option>
						<option value="2">제목</option>
						<option value="3">내용</option>
					</select>
					<input type="text" id="searchText" name="searchText" title="검색어 입력박스" class="input_100" /> 
					<input type="button" id="btnSearch" value="검색" title="검색버튼" class="btn_search" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</form>
				<br/>
				<!-- board area -->
				<form name="board">
					<table border="1" width="800" height="" align="center">
						<thead>
							<tr>
								<td width="50" align="center">번호</td>
								<td width="300" align="center">제목</td>
								<td width="150" align="center">작성자</td>
								<td width="150" align="center">작성일</td>
								<td width="50" align="center">조회</td>
								<td width="50" align="center">추천</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ allList }" var="row">
							<tr>
								<td align="center"><font size=2 />${ row.board_seq }</td>
								</font>
								<td align="center"><font size=2 />
									<a href="<c:url value='/board/read.do?boardSeq=${ row.board_seq }&typeSeq=2' />">${ row.title }</a>
								</td>
								<td align="center"><font size=2 />${ row.member_nick }</td>
								<td align="center"><font size=2 />${ row.create_date }</td>
								<td align="center"><font size=2 />${ row.hits }</td>
								<td align="center"><font size=2 />0</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<br/>
				<!-- 페이징 처리 -->
				<c:choose>
					<c:when test="${ pageBlockStart > pageBlockSize }">
						<a href="<c:url value='/board/list.do?currentPage=${pageBlockStart-10}&searchType=${searchType}&searchText=${searchText}' />">
						<input type="button" name="pre" value="이전페이지" /></a>
					</c:when>
				</c:choose>
				<c:forEach begin="${ pageBlockStart }" end="${ pageBlockEnd }" step="1" var="pageNo">
					<c:choose>
						<c:when test="${ pageNo == currentPage }">
							<strong>${ currentPage }</strong>
						</c:when>
						<c:otherwise>
							<a href="<c:url value='/board/list.do?currentPage=${pageNo}&searchType=${searchType}&searchText=${searchText}' />">${pageNo}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${ pageBlockEnd != totalPageCnt }">
						<a href="<c:url value='/board/list.do?currentPage=${ pageBlockEnd + 1 }&searchType=${searchType}&searchText=${searchText}' />">
						<input type="button" name="next" value="다음페이지" /></a>
					</c:when>
				</c:choose>
				
				
				
				<!-- // 페이징 처리 -->	
					<br/>
				<div class="btn_bottom">
					<div class="btn_bottom_right">
						<p align="right" >
						<c:if test="${ sessionScope.memberId != null }">
						<a href="<c:url value='/board/goWrite.do' />"><input type="button" value="글쓰기" title="글쓰기" /></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						</c:if>
					</div>
				</div>
					
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