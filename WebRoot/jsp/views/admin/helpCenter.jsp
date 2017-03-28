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
<base href="<%=basePath%>" />
<title>尚学在线后台管理系统</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学后台管理" />
<meta http-equiv="description" content="帮助中心" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg">
	<!--头部-->
	<div class="header">
		<jsp:include page="../../common/admin/header.jsp"></jsp:include>
		<!-- 菜单 -->
		<jsp:include page="../../common/admin/meun.jsp"></jsp:include>
		
		<div class="header_child">
           	<div class="h_content">
             		<div class="list">
					<span style="width: 100px;display: inline-block;">
						<c:choose>
						<c:when test="${type==1}"><a href="admin/helpCenter?type=1" style="color:#00a8f6;">新手指引</a></c:when>
						<c:otherwise><a href="admin/helpCenter?type=1">新手指引</a></c:otherwise>
						</c:choose>
					</span>
					<span style="width: 100px;display: inline-block;">
						<c:choose>
						<c:when test="${type==2}"><a href="admin/helpCenter?type=2" style="color:#00a8f6;">学习保障</a></c:when>
						<c:otherwise><a href="admin/helpCenter?type=2">学习保障</a></c:otherwise>
						</c:choose>
					</span>
					<span style="width: 100px;display: inline-block;">
						<c:choose>
						<c:when test="${type==3}"><a href="admin/helpCenter?type=3" style="color:#00a8f6;">支付方式</a></c:when>
						<c:otherwise><a href="admin/helpCenter?type=3">支付方式</a></c:otherwise>
						</c:choose>
					</span>
					<span style="width: 100px;display: inline-block;">
						<c:choose>
						<c:when test="${type==4}"><a href="admin/helpCenter?type=4" style="color:#00a8f6;">关于我们</a></c:when>
						<c:otherwise><a href="admin/helpCenter?type=4">关于我们</a></c:otherwise>
						</c:choose>
					</span>
				</div>
			</div>
		</div>
	</div>
	
	<!--内容-->
	<div class="w_content kcsz">
		<form action="admin/doHelp" method="post" onsubmit="return submitChanged(this);">
		<div class="phgz phgz_1 f_st">
			<h2>${guid.name}</h2>
			<div class="phgz_txt phgz_txt_1">
				<textarea style="resize:none; white-space:normal;width: 99%;" name="guid_cont" id="guid_cont">
					${guid.valStr}
				</textarea>
			</div>
			<input type="hidden" value="${guid.cfgid}" name="cfg_guid_id" id="cfg_guid_id" />
			<div class="kcszbtn">
				<input class="btn_1" type="submit" value="修改" /> 
			</div>
		</div>
		</form>
	</div>
	<script type="text/javascript">
		function submitChanged(that) {
			//关闭编辑器
			syncKEOne(0);
			
			var jqForm = $(that);
			var id = $("#cfg_guid_id").val();
			var cont = $("#guid_cont").val();
			var url = '<%=basePath%>' + "admin/doHelp";
			var data = {"cfgid":id,"strval":cont};
			var callBack = function(result) {
				if (result.status) {
					alert("修改成功");
				} else {
					alert("修改失败");
				}
			};
			$.post(url,data,callBack, "json");
			return false;
		}
	
		function changeEdit(id) {
			document.getElementById(id).disabled = false;
		}
		
		// ========= 设置界面编辑器 begin ==========
		function isCreateKE(){
			return true;
		}
		
		function createKEFunc(K,options){
			return createKEOne(K,options,"guid_cont");
		}
		// ========= 设置界面编辑器 end ==========
	</script>
</body>
</html>