<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- tag library 선언 : c tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>게시글 수정하기</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />" />

<script src="https://cdn.ckeditor.com/ckeditor5/11.0.1/classic/ckeditor.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#btnUpdate').on('click', function(){
			var frm = document.updateForm;
			var title = $('#title').val();
			if(title.length == 0){
				alert("제목을 적어주세요.");	
				return;
			}		
			
			frm.content,value = ckEditor.getData();
			var content = frm.content.value;
			if(content.length == ''){
				alert("내용을 적어주세요.");
				return
			}
			
			frm.action = "<c:url value='/board/update.do'/>";
			frm.submit();			
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

				<!-- title board write -->
				<div class="title_board_write">게시글 수정</div>
				<!-- //title board write -->

				<!-- board_area -->
				<div class="board_area">
					<form name="updateForm" method="post">
						<fieldset>
							<legend>게시글 수정</legend>

							<!-- board write table -->
							<table summary="표 내용은 게시글 수정 박스입니다."
								class="board_write_table">
								<caption>게시글 수정 박스</caption>
								<colgroup>
									<col width="20%" />
									<col width="80%" />
								</colgroup>
								<tbody>
									<tr>
										<th class="tcenter"><label for="board_write_name">글번호</label></th>
										<td class="tleft">
											<input type="text" id="boardSeq" name="boardSeq" title="글번호" class="input_100" readonly="readonly" value="${ getBoard.get('board_seq') }" /></td>
											<input type="hidden" id="typeSeq" name="typeSeq" title="게시판번호" readonly="readonly" value="${ getBoard.get('type_seq') }" /></td>
											
									</tr>
									<tr>
										<th class="tcenter"><label for="board_write_name">작성자</label></th>
										<td class="tleft">
											<input type="text" id="memberNick" name="memberNick" title="memberNick" class="input_100" readonly="readonly" value="${ getBoard.get('member_nick') }"/></td>
									</tr>
									<tr>
										<th class="tcenter"><label for="board_write_title">제목</label></th>
										<td class="tleft">
											<input type="text" id="title" name="title" title="제목 입력박스" class="input_380" value="${ getBoard.get('title') }"/>
										</td>
									</tr>
									<tr>
										<th class="tcenter"><label for="board_write_title">내용</label></th>
										<td class="tleft">
											<div class="editer">
												<p>
													<textarea rows="30" cols="100" name="content" id="editor" >${ getBoard.get('content') }</textarea>
													<script>
    													ClassicEditor
      													  .create( document.querySelector( '#editor' ) )
      													  .then( editor => {
      														  ckEditor = editor;
      													  } )
      													  .catch( error => {
        													    console.error( error );
      														  } );
													</script>
												</p>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
							<!-- //board write table -->

							<!-- bottom button -->
							<div class="btn_bottom">
								<div class="btn_bottom_right">
									<input type="button" value="뒤로" title="뒤로" onclick="history.back();" /> 
									<input type="button" id="btnUpdate" value="완료" title="완료" />
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