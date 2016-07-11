<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://page.bowlong.com/jsp/tags" prefix="p"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"><head>
<base href="<%=basePath%>">
<title>尚学在线后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="尚学后台管理">
<meta http-equiv="description" content="订购管理">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg" menuName="订购管理">
		<!--头部-->
	<div class="header">
		<jsp:include page="../../common/admin/header.jsp"></jsp:include>
		<!-- 菜单 -->
		<jsp:include page="../../common/admin/meun.jsp"></jsp:include>
	</div>
    <!--内容-->
    <div class="w_content kclb">
    	<div class="kcszbtn"><input class="btn"  onclick="OutXls('orderManageOutXls');" type="button" value="导出数据"/></div>
        <div class="div_table f_st">
        	<table class="table" border="0" cellpadding="0" cellspacing="0">
        		<thead>
                 <tr>
                     <th width="20%">全部成交时间</th>
                     <th width="5%">购买者</th>
                     <th width="30%">订单内容</th>
                     <th width="15%">代理人员</th>
                     <th width="5%">原价</th>
                     <th width="5%">成交价</th>
                     <th width="10%">获得考币</th>
                     <th width="5%">交易状态</th>
                     <th width="5%">净收入</th>
                  </tr>
        		</thead>
                <tbody>
                     <c:forEach items="${pageEnt.listPages}" var = "ent" varStatus="">
                	 <tr>
                    	<td>${ent.createtime}</td>
                    	<td>${ent.nmMaker}</td>
                    	<td>${ent.name}</td>
                        <td>${ent.nmAgent}</td>
                    	<td>${ent.price}</td>
                    	<td>${ent.realprice}</td>
                    	<td>${ent.kbi}</td>
                        <td>${ent.statusProcess == 0 ? '未支付':'已支付'}</td>
                    	<td>
                    	<c:choose>
                    	<c:when test="${ent.statusProcess == 0 }">0</c:when>
                    	<c:otherwise>${ent.realprice - ent.mnAgent - ent.mnQBank - ent.mnDeposit - ent.bonusAgent - ent.mnDevelop - ent.mnArt }</c:otherwise>
                    	</c:choose>
                    	</td>
                    	<div></div>
                    </tr>
					 </c:forEach>
                </tbody>
                
                
                <tfoot>
                    <tr class="tc_td">
                    	<th style="text-align:left;padding-left:10px;" colspan="9">合计：${pageEnt.totalRecords} 条购买记录 </th>
                    </tr>
               </tfoot>
        	</table>
    	</div>
    	<div style="clear: both;height: 15px;"></div>
    	<p:pageTag name="pageEnt" action="admin/orderManage" />
    </div>
</body>
</html>