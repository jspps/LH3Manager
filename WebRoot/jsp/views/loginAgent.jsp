<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String host = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort() + "/";
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<base href="<%=basePath%>">
<title>尚学在线后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="尚学后台管理">
<meta http-equiv="description" content="登录">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<link rel="stylesheet" type="text/css" href="jsp/css/style.css" />
<script type="text/javascript" src="jsp/js/jquery-1.11.1.js"></script>
</head>

<body class="body_login">
	<script type="text/javascript">
		$(document).ready(function(){
			window.location.href = '<%=host%>'+"LH3/dls";
		});
	</script>
</body>
</html>
