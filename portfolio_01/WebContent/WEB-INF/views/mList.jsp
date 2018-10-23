<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery-ui/css/jquery-ui.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jqgrid/css/ui.jqgrid.css" />" />
<style type="text/css">
.board_search {
	margin-bottom: 5px;
	text-align: right;
	margin-right: -7px;
}
.board_search .btn_search, #btnDelete {
	height: 20px;
	line-height: 20px;
	padding: 0 10px;
	vertical-align: middle;
	border: 1px solid #e9e9e9;
	background-color: #f7f7f7;
	font-size: 12px;
	font-family: Dotum, "돋움";
	font-weight: bold;
	text-align: center;
	cursor: pointer;
}
select {
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	margin-left : 3px;
	padding: 0;
	font-size: 12px;
	height: 20px;
	line-height: 20px;
	border: 1px solid #d7d7d7;
	color: #7f7f7f;
	/* padding: 0 5px; */
	vertical-align: middle;
}
</style>
<script type="text/javascript" src="<c:url value="/resources/jquery/js/jquery-3.2.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqgrid/js/jquery.jqGrid.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqgrid/js/i18n/grid.locale-kr.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery-ui/js/jquery-ui.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery/js/jquery-migrate-1.4.1.js" />"></script>

<script type="text/javascript">

$(document).ready(function(){
	// Tab
	$( "#tabs" ).tabs();
	
	$('#btnSearch').click(function(){
		// 아이디/성명
		var searchType = $('#searchType option:selected').val();
		// 검색어
		var searchText = $('#searchText').val();
		$('#jqGrid').jqGrid('setGridParam',
				{
					postData : {
						searchType : searchType,
						searchText : searchText
					},
					page : 1
				}
			).trigger('reloadGrid');
		console.log(searchType);
		console.log(searchText);
		
	});

	$("#jqGrid").jqGrid({
		//datatype : "local",
		//data : mList,
		url: '<c:url value="/member/mList.do" />',
		datatype: "json",
		jsonReader : {id:"memberIdx"}, 
		prmNames : {id:'memberIdx'}, //pk 컬럼 지정
		colModel: [
			{ label: '순번', name: 'memberIdx', width: 75, align:'center' },
			{ label: 'ID', name: 'memberId', width: 90, align:'center' },
			{ label: '성명', name: 'memberName', width: 100, align:'center' },
			{ label: '별명', name: 'memberNick', width: 80, align:'center' },
			{ label: '가입일', name: 'createDate', width: 80, align:'center' }                   
		],
		viewrecords: true, // show the current page, data rang and total records on the toolbar
		width: 740,
		height: 200,
		rowNum: 5, // 가져올 게시글 수 (기본)
		rowList: [5, 10, 15], // 가져올 게시글 수 (선택)
		caption: '회원목록', // 그리드 위에 제목달기
		rownumbers: true, // 그리드 목록에 대한 순번
		sortname: "memberIdx", // 기본 정렬 컬럼
		sortorder: "desc", // 기본 정렬
		scrollrows: true, // 스크롤 유무
		viewrecords: true, // row 보기
		pager: "#jqGridPager"
	});
	
	//navButtons
	$('#jqGrid').jqGrid('navGrid', "#jqGridPager",
		{ // navbar options
			edit: false,
			add: false,
			del: true,
			search: false,
			refresh: true,
			view: true
		},
		{}, // edit 
		{}, // add
		{	// del
			caption: '회원정보 삭제',
			msg: "선택한 회원을 삭제하시겠습니까?",
			width:300,
			recreateForm: true,
			url : '<c:url value="/member/delMember.do" />',
			beforeShowForm : function(e) {
				console.log(e);
				var form = $(e[0]);
				console.log(form);
				return;
			},
			// 보내기 전
			beforeSubmit : function(post){
				// post : 지울 key 값
				console.log('beforeSubmit : '+post);
				// post로 row 전체 값 가져오기, return type = Object
				var rowData = $("#jqGrid").jqGrid('getRowData', post);
				var loginId = '${sessionScope.memberId}';
				if(loginId == rowData.memberId){
					return [false, "자기 자신을 삭제할 수 없습니다."];
				} else {
					return [true, "", ""];
				}
			},
			// 완료된 후
			afterComplete : function(result, postdata){
				console.log('delOption - afterComplete');
				console.log(result);
				console.log(result.responseJSON);
				alert(result.responseJSON.msg);
			}
		}
	);
	
	$('#btnDelete').on('click', function(){
		// selrow : 선택한 row, getGridParam : 그리드 파라미터 가져오기
		var rowId = $('#jqGrid').jqGrid('getGridParam', 'selrow');
		// 선택한 행(ROW)가 있다면, rowId가 무조건 존재함.
		console.log("rowId : "+rowId);
		if(rowId == null){
			alert("삭제할 회원을 선택해주세요.");
			return;
		} else {
			// 선택한 행(ROW)을 가져오려면 key가 필요함.
			var rowData = $('#jqGrid').jqGrid('getRowData', rowId);
			console.log(rowData);
			
			$.ajax({
				url: '<c:url value="/member/delMember.do" />',
				// url에 보낼 데이터 = 파라미터
				data: { memberIdx : rowData.memberIdx },
				success : function(result, textStatus, XMLHttpRequest){
					console.log(result);
					alert("삭제되었습니다.");
					// 그리드 데이터 새로고침
					$("#jqGrid").jqGrid('setGridParam', {page : 1}).trigger("reloadGrid");
				},				
				// success 끝
				error : function(){
				// error 끝
				}
			})
		}
	});
});
</script>
<title></title>
</head>
<body>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1">회원 목록</a></li>
	</ul>
	<div id="tabs-1">
		<!-- content -->
		<div id="content">

			<!-- board_search -->
			<div class="board_search">
				<form name="searchForm" method="get">
					<select id="searchType" name="searchType" title="선택메뉴">
						<option value="1" selected="selected">전체</option>
						<option value="2">ID</option>
						<option value="3">별명</option>
					</select> 
					<input type="text" id="searchText" name="searchText" title="검색어 입력박스" class="input_100" /> 
					<input type="button" id="btnSearch" value="검색" title="검색버튼" class="btn_search" />
				</form>
			</div>
			
			<!-- //board_search -->

			<!-- board_area -->
			<div class="board_area board_search">
				<button id="btnDelete" style="margin-bottom:3px;">삭제</button>
				<table id="jqGrid"></table>
  					<div id="jqGridPager"></div>
			</div>
			<!-- //board_area -->

		</div>
		<!-- //content -->
	</div>
</div>
</body>
</html>