<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
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
<script type="text/javascript" src="jsp/js/dateEx.js"></script>
</head>

<body class="body_login">
	<div class="logon">
		<div class="login_info">
			<div class="login1">
				<h2>尚学在线后台管理系统</h2>
				<form action="admin/doLogin" method="post">
				<div class="login_div">
					<span class="fl">用户名：</span> 
					<input type="text" class="l_txt" name="lgid" id="lgid" />
				</div>
				<div class="login_div">
					<span class="fl">密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
					<input type="password" class="l_txt" name="lgpwd" id="lgpwd" />
				</div>
				<div class="login_div">
					<span class="fl">验证码：</span>
					<input type="text" class="l_yz fl" name="lgcode" id="lgcode" />
					<a href="javascript:void(0);" class="yzm_a fl">
						<img src="checkCode?jtime=<%=Calendar.getInstance().getTimeInMillis()%>" width="90" height="33" onclick="getNewCode(this);" />
					</a>
				</div>
				<div class="login_div">
					<span class="fl">&nbsp;</span> 
					<input type="submit" class="l_btn" value="登 陆" />
				</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function getNewCode(that){
			var url = "checkCode?jtime=" + getTimeMSecond();
			that.src = url;
		}
	</script>
</body>
</html>
