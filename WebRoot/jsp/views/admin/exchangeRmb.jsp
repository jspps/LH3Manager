<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://page.bowlong.com/jsp/tags" prefix="p"%>
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
						<a href="admin/rmb2Kbi">考币币值设置</a>
					</span>
					<span style="width: 100px;display: inline-block;">
						<a href="javascript:void(0);" style="color:#00a8f6;">申请提现管理</a>
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
	<div class="user_content">
	    <div class="user_list">
	    	<div class="kcszbtn">
	        	<input type="button" value="导出数据" class="btn btn_2" />
	        </div>
	        
	        <div class="div_table f_st">
	        	<table class="table" border="0" cellpadding="0" cellspacing="0">
	        		<thead>
	                <tr>
	        			<th width="10%">申请时间</th>
	                    <th width="10%">申请人</th>
	                    <th width="20%">支付宝账号</th>
	                    <th width="20%">申请事项</th>
	                    <th width="10%">可提现金额</th>
	                    <th width="10%">提现金额</th>
	                    <th width="10%">申请状态</th>
	                    <th width="10%">操作</th>
	                </tr>
	        		</thead>
	                <tbody>
	                	<c:forEach items="${pageEnt.listPages }" var="item">
	                	<tr>
	                    	<td><p:fmtDate parttern="" value="${item.createtime}" /></td>
	                    	<td>${item.alipayName }</td>
	                    	<td>${item.alipay }</td>
	                    	<td>${item.reason }</td>
	                    	<td>${item.moneyCur }</td>
	                    	<td>${item.monyeApply } </td>
	                        <td>
	                        	<c:choose>
	                            <c:when test="${item.statusOpt == 0 }">申请中</c:when>
	                            <c:when test="${item.statusOpt == 1 }">撤销中</c:when>
	                            <c:when test="${item.statusOpt == 2 }">已取消</c:when>
	                            <c:when test="${item.statusOpt == 3 }">被拒绝</c:when>
	                            <c:otherwise>已成功</c:otherwise>
	                            </c:choose>
	                        </td>
	                    	<td>
	                    		<c:if test="${item.statusOpt == 0 || item.statusOpt == 1}">
	                    			<a href="javascript:void(0);" class="delete" name="${item.statusOpt}" pars="${item.id}">同意</a>
	                    			<a href="javascript:void(0);" class="check"  name="${item.statusOpt}" pars="${item.id}">拒绝</a>
	                    		</c:if>
	                    	</td>
	                    </tr>
	                    </c:forEach>
	                </tbody>
	        	</table>
	    	</div>
	        <p:pageTag name="pageEnt" action="admin/exchangeRmb" />
    	</div>
    	<div id="appay_4_go_2_alipay"></div>       
    </div>
	<script type="text/javascript">
		$(document).ready(function(){
			$("div table a.delete").click(function(){
				// 同意
				var val = $(this).attr("pars");
				val = parseInt(val,10);
	     		if(isNaN(val)){
	     			alert("同意失败!");
	     			return;
	     		}
	     		var optStatus = $(this).attr("name");
	     		optStatus = parseInt(optStatus,10);
	     		
	     		var desc = "同意取消用户提取现金吗?";
	     		if(optStatus == 0){
	     			desc = "同意用户提取现金吗?";
	     		}
	     		if(confirm(desc)){
	     			var data = {};
	     			data.pars_id = val;
					var url = '<%=basePath%>'+"admin/sureExchangeRmb";
					var infunCallBack = function(back){
						alert(back.msg);
						if(back.status == 1){
							if(back.data){
								$("#appay_4_go_2_alipay").html(back.data);
							}else{
								window.location.reload();
							}
						}
					};
					$.post(url,data,infunCallBack,"json");
				};
			});
			
			$("div table a.check").click(function(){
				// 拒绝
				var val = $(this).attr("pars");
				val = parseInt(val,10);
	     		if(isNaN(val)){
	     			alert("拒绝失败!");
	     			return;
	     		}
	     		
	     		var optStatus = $(this).attr("name");
	     		optStatus = parseInt(optStatus,10);
	     		
	     		var desc = "拒绝取消用户提取现金吗?";
	     		if(optStatus == 0){
	     			desc = "拒绝用户提取现金吗?";
	     		}
	     		if(confirm(desc)){
	     			var data = {};
	     			data.pars_id = val;
					var url = '<%=basePath%>'+"admin/cancelExchangeRmb";
					var infunCallBack = function(back){
						alert(back.msg);
						if(back.status == 1){
							window.location.reload();
						}
					};
					$.post(url,data,infunCallBack,"json");
				};
			});
		});
	</script>
</body>
</html>