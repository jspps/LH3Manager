<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/tags" prefix="p"%>
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
<title>学习中心管理后台_设置试题类别</title>
<jsp:include page="../../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg1">
	<!--头部-->
	<jsp:include page="../../../common/center/header.jsp" />
	
	<!--内容-->
	<div class="j_content">
	    <!--菜单-->
		<jsp:include page="../../../common/center/meun.jsp" />
		<div class="user_content">
			<div class="user_list">
				<jsp:include page="cursor.jsp" />
				<div style="text-align: center;font-size: 25px;line-height: 50px;font-weight: bold;">${product.name}</div>
				<div class="kcszbtn u_tkgl">
					<div class="u_tkgl_content">
			     	 	<span>试题类别：</span>
			      	 	<span style="margin-right:30px;"><input name="catalog" type="radio" value="5" checked="checked">知识要点</span>
						<span style="margin-right:30px;"><input name="catalog" type="radio" value="1">章节练习</span>
						<span style="margin-right:30px;"><input name="catalog" type="radio" value="2">历年真题</span>
						<span style="margin-right:30px;"><input name="catalog" type="radio" value="3">全真模拟</span>
						<span style="margin-right:30px;"><input name="catalog" type="radio" value="4">绝胜押题</span>
					</div>
				 	
					<!--中间内容-->
					<div class="kcszbtn_cont" id="div_cruces">
					  	
					</div>
					<i class="clear"></i>
			        <div class="lrst_btn">
			    		<input type="button" value="设置试题销售套餐" class="lrbtn_2" onclick="click2SetKind();" />&nbsp;&nbsp;&nbsp;&nbsp;试题添加完毕后方可设置试题销售套餐！
			        </div>
			        <i class="clear"></i>
				</div>
			</div>
		</div>
	</div>
	<form action="center/makeKind" method="post" id="fm_go2_makekind">
	   	<input type="hidden" value="${product.id}" name="productid" />
	   	<input type="hidden" value="1" name="kindclassId" />
   	</form>
	<script type="text/javascript">
		function getHtml4Catalog(ctype){
			var url = "center/catalog4Product";
			var data={};
			data.productid = "${product.id}";
			data.ctype = ctype;
			var callFun = function(backData){
				$("#div_cruces").html(backData);
			};
			$.post(url,data,callFun);
		};
		
		getHtml4Catalog(5);
		
		$(document).ready(function(){
			$("div.u_tkgl_content span input:radio").change(function(){
				getHtml4Catalog($(this).val());
			});
			// var curVal = $("div.u_tkgl_content span input:radio:checked").val();
			// getHtml4Catalog(curVal);
		});
		
		function click2SetKind(){
			$("#fm_go2_makekind").submit();
		};
	</script>
</body>
</html>