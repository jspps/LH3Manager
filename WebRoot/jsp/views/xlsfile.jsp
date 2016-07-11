<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<base href="<%=basePath%>" />
<title>尚学在线后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学后台管理" />
<meta http-equiv="description" content="提示信息" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

<link rel="shortcut icon" href="jsp/imgs/ico.jpg" />
<style type="text/css">
<!--
#div_wrap{margin: 50px auto 0px auto;width: 600px;line-height: 100px;background:rgba(238,242,251,1); text-align: center;}
-->
</style>

</head>
<body style="background:#CCC;margin: 0 auto;">
	<div class="div_wrap" id="div_wrap">
		<div class="div_content" id="div_content">
			<div id="fileUp">		
				<div id="mn_mid">
					<form action="${action}" method="post" enctype="multipart/form-data">
						<input type="file" name="fileField" class="file" id="fileField" size="28" onchange="document.getElementById('textfield').value=this.value" style="display: none;"/>
						<input type='text' name='textfield' id='textfield' class='txt' />  
						<input type='button' class='btn' value='浏览...' onclick="clickSearch()"/>
						<input type="submit" name="submit" class="btn" value="上传" />
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function clickSearch (){
			document.getElementById("fileField").click();
		}
	</script>
</body>
</html>
