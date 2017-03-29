<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>打印界面</title>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	
	<style type="text/css" media=print>
		.noprint{display : none }
	</style>
	
	<link rel="stylesheet" type="text/css" href="jsp/css/base_print.css" />

  </head>
  
  <body>
    <div id="noprint_menu" class="noprint">
    	<object classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" width="0" id="WebBrowser"></object>
    	<div>  
			<input type="button" value="预览" onclick="pagePreview()"/>  
			<input type="button" value="打印" onclick="pagePrint()"/>  
			<input type="button" value="页面设置" onclick="pageSetup()"/>  
		</div> 
		
		<!-- 打印预览 -->
		<div class="print-box">
			<span class="print-title">
				打印预览
			</span>
			<span class="select-list">
				<span class="mechanism">
					<input type="checkbox" id="mechanism-logo" class="regular-checkbox" name="">
					<label for="mechanism-logo">
						<i>机构LOGO</i>
					</label>
				
				</span>
	
				<span>
					<input type="checkbox" id="contain-answer" class="regular-checkbox" name="">
					<label for="contain-answer">
						<i>包含答案</i>
					</label>
				</span>
				
				
			</span>
	
			<span class="page-info t-center">共4页</span>
	
			<span class="print-button-box t-center">
				<input type="button" class="print-button" value="打印" name="">
			</span>
		</div>
    </div>
  </body>
</html>
