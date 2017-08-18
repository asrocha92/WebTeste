<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>CompraTeste</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./css/home/default.css" />

<link rel="stylesheet" type="text/css" href="./css/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="./css/bootstrap/css/bootstrap-theme.min.css" />

<script type="text/javascript" src="./js/libs/jquery-2.0.3.js"></script>
<script type="text/javascript" src="./js/libs/lodash-2.4.1.js"></script>

</head>
<body>
	<input id="idTrasacao" name="idTrasacao" value="0" style="display:none"/>
	<div id="header">
		<%@include file='/templates/home/header.html'%>
	</div>
	<div id="base">
		<%@include file='/templates/home/base.html'%>
	</div>
	<div id="footer" style="padding-bottom: 0;">
		<%@include file='/templates/home/footer.html'%>
	</div>
	
	<script type="text/javascript" src="./js/default.js"></script>
	<script src="./css/bootstrap/js/bootstrap.min.js"></script>
	<script src="./css/bootstrap/js/bootstrap.js"></script>
</body>

</html>
