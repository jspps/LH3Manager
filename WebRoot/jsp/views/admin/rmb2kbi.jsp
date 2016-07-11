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
	<div class="header">
		<!--头部-->
		<jsp:include page="../../common/admin/header.jsp"></jsp:include>
		<!-- 菜单 -->
		<jsp:include page="../../common/admin/meun.jsp"></jsp:include>
		
		<div class="header_child">
           	<div class="h_content">
             	<div class="list">
					<span style="width: 100px;display: inline-block;">
						<a href="javascript:void(0);" style="color:#00a8f6;">考币币值设置</a>
					</span>
					<span style="width: 100px;display: inline-block;">
						<a href="admin/exchangeRmb">申请提现管理</a>
					</span>
					<!-- 
					<span style="width: 100px;display: inline-block;">
						<a href="admin/cancelExchangeRmb">取消申请管理</a>
					</span>
					 -->
				</div>
			</div>
		</div>
	</div>
	<!--内容-->
	<div class="w_content kcsz">
		<div class="phgz phgz_1 f_st">
			<div style="margin: 0 auto; width: 600px;text-align: center;margin-top: 60px;">
				<div style="font-weight: bold;font-size: 16px;">
					人民币兑换考币
				</div>
				<div style="margin-top: 50px;">
					<span style="width: 80px;display: inline-block;border: 1px solid;color: red;">1元</span>
					<span style="width: 80px;display: inline-block;">兑换</span>
				    <input type="text" id="rmb_2_kbi" class="input1" name="rmb_2_kbi" value="${rateKbi}" />
				    <span style="color: blue;">考币</span>
			    </div>
				<div class="kcszbtn" style="text-align:center;">
					<input class="btn_1" type="button" value="修改" onclick="submitRmb2Kbi();"/> 
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function submitRmb2Kbi() {
			var val = $("#rmb_2_kbi").val();
			val = parseInt(val,10);
			if(isNaN(val)){
				alert("请输入要兑换的值!");
				return;
			}
			var url = '<%=basePath%>' + "admin/submitRmb2Kbi";
			var data ={"kbi":val};
			var inCallFun = function(back){
				alert(back.msg);
			};
			$.post(url,data,inCallFun,"json");
		}
	</script>
</body>
</html>