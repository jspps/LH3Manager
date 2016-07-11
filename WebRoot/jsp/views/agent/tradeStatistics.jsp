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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<base href="<%=basePath%>"/>
<title>成交记录</title>
<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg1" menuName="成交统计">
 	<!--头部-->
	<jsp:include page="../../common/agent/header.jsp"></jsp:include>
    <!--内容-->
    
    <div class="j_content">
	    <!--菜单-->
	    <jsp:include page="../../common/agent/meun.jsp"></jsp:include>
	    <div class="user_content">
	        <div class="uc_content" style="overflow:visible;">
		        <div class="kcszbtn">
		        	<input type="button" value="导出数据" class="btn" />
		        </div>
		        
		    	<div class="div_table f_st">
		        
		        	<table cellspacing="0" cellpadding="0" border="0" class="table">
		        		<thead>
		                <tr>
		        			<th width="15%">时间</th>
		                    <th width="15%">购买者</th>
		                    <th width="30%">课程</th>
		                    <th width="10%">原价(元)</th>
		                    <th width="10%">成交价(元)</th>
		                    <th width="10%">佣金(元)</th>
		                    <th width="10%">奖金(元)</th>                    
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
		                        <td>${ent.agentRoyalty}</td>
		                        <td>${ent.agentBonus}</td>
		                    </tr>
		                 </c:forEach>   
		                  </tbody>
		        	</table>
		    	</div>
		        <p:pageTag name="pageEnt" action="agent/tradeStatistics" />
			</div> 
		</div>
	</div>
    <i class="clear"></i> 
</body>
</html>