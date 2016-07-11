<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://page.bowlong.com/jsp/tags" prefix="p"%>
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
<base href="<%=basePath%>"/>
<title>学习中心管理后台_成交记录</title>
<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg1">
	<!--头部-->
	<jsp:include page="../../common/center/header.jsp"></jsp:include>
    
    <!--内容-->
    
    <div class="j_content">
    
     <!--菜单-->
    <jsp:include page="../../common/center/meun.jsp"></jsp:include>
	<div class="user_content">
	    <div class="user_list">
	    	<div class="kcszbtn">
	        	<input type="button" value="导出数据" class="btn" />
	        </div>
	        <div class="div_table f_st">
	        	<table class="table" border="0" cellpadding="0" cellspacing="0">
	        		<thead>
		                <tr>
		        			<th width="15%">时间</th>
		                    <th width="15%">购买者</th>
		                    <th width="30%">课程</th>
		                    <th width="10%">原价(元)</th>
		                    <th width="10%">成交价(元)</th>
		                    <th width="10%">题库提成(元)</th>
		                    <th width="10%">质量押金(元)</th>                    
		                </tr>
	        		</thead>
	                <tbody>
	                 <c:forEach items="${pageEnt.listPages}" var = "ent" varStatus="">
	                	<tr>
	                    	<td>${ent.createtime}</td>
	                    	<td>${ent.cust.name}</td>
	                    	<td>${ent.kind.nmProduct}</td>
	                    	<td>${ent.price}</td>
	                    	<td>${ent.realprice}</td>
	                        <td>${ent.lhubRoyalty}</td>
	                        <td>${ent.lhubDeposit}</td>
	                    </tr>
	                 </c:forEach>   
	                 </tbody>
	        	</table>
	    	</div>
	        <p:pageTag name="pageEnt" action="center/tradeStatistics" />
	    </div>
    </div>
 </div>
</body>
</html>