<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ERORR!!!!</title>
<script type="text/javascript">

	var msg = '${msg}';
	var nextLocation = '${nextLocation}';
function init(){
	var contextPath = '${pageContext.request.contextPath}';
	if(msg != ''){
		alert(msg);
		// 스크립트 코드를 이용한 화면이동
		window.top.location.href = contextPath + nextLocation;
	}
}

</script>

</head>
<body onload="init()">

</body>
</html>