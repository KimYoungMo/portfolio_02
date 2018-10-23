<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- tag library 선언 : c tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>게시물 상세페이지</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){ // onload="init();"를 대신함
		var msg = '${ msg }' ;
		if(msg != ''){
			alert(msg);
		}
		$('#bntMod').on('click', function(){
			var frm = document.readForm;
			
			if(confirm("게시글을 수정하시겠습니까?")){
			frm.action = "<c:url value='/board/modify.do'/>";
			frm.submit();
			}
		});	
	});
	
	$(document).ready(function(){
		$('#bntDel').on('click', function(){
			var frm = document.readForm;
			
			if(confirm("게시글을 삭제하시겠습니까?")){
				frm.action = "<c:url value='/board/delete.do'/>";
				frm.submit();
			}
		});	
	});
	
	
</script>

</head>
<body>

	<!-- wrap -->
	<div id="wrap">

		<!-- container -->
		<div id="container">

			<!-- content -->
			<div id="content">
				<!-- title board detail -->
				<div class="title_board_detail">게시물 보기</div>
				<!-- //title board detail -->

				<!-- board_area -->
				<div class="board_area">
					<form name="readForm" method="post" >
						<input type="hidden" name="boardSeq" value="${ getBoard.get('board_seq') }" />
						<input type="hidden" name="typeSeq" value="${ getBoard.get('type_seq') }" />
					
						<fieldset>
							<legend>Ses & Food 게시물 상세 내용</legend>

							<!-- board detail table -->
							<table summary="표 내용은 Ses & Food 게시물의 상세 내용입니다." class="board_detail_table">
								<caption>Ses & Food 게시물 상세 내용</caption>
								<colgroup>
									<col width="%" />
									<col width="%" />
									<col width="%" />
									<col width="%" />
									<col width="%" />
									<col width="%" />
								</colgroup>
								<tbody>
									<tr>
										<th class="tcenter">제목</th>
										<td class="tcenter">${ getBoard.get("title") }</td>
										<th class="tcenter">조회수</th>
										<td class="tcenter">${ getBoard.get("hits") }</td>										
									</tr>
									<tr>
										<th class="tcenter">작성자</th>
										<td class="tcenter">${ getBoard.get("member_nick") }</td>
										<th class="tcenter">추천</th>
										<td class="tcenter">100</td>
									</tr>
									<tr>
										<th class="tcenter">작성일</th>
										<td>${ getBoard.get("create_Date") }</td>
										<th class="tcenter">수정일</th>
										<td>${ getBoard.get("update_Date") }</td>
									</tr>
									<tr>
										<td colspan="6" class="tleft">
											<div class="body">
												${ getBoard.get("content") }
											</div>
										</td>
									</tr>
								</tbody>
							</table>
							<!-- //board detail table -->

							<!-- bottom button -->
							<div class="btn_bottom">
								<div class="btn_bottom_left">
									<input type="button" value="추천하기" title="추천하기" />
								</div>
								<div class="btn_bottom_right">
									<c:if test="${ sessionScope.memberId != null }">
										<input type="button" id="bntMod" value="수정" title="수정" /> 
										<input type="button" id="bntDel" value="삭제" title="삭제" /> 
									</c:if>
									<input type="button" value="목록" title="목록" onclick="history.back();" />
								</div>
							</div>
							<!-- //bottom button -->

						</fieldset>
					</form>
				</div>
				<!-- //board_area -->

			</div>
			<!-- //content -->

		</div>
		<!-- //container -->

	</div>
	<!-- //wrap -->

</body>
</html>