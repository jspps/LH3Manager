<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<meta http-equiv="description" content="代理排行榜">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="" menuName="代理商管理">
	<!--头部-->
	<div class="header">
			   <jsp:include page="../../common/admin/header.jsp"></jsp:include>
	   <!-- 菜单 -->
	   <jsp:include page="../../common/admin/meun.jsp"></jsp:include>


		<div class="header_child">
			<div class="h_content">
				<div class="list">
					<a href="admin/adminAgentManage">代理管理</a> <a href="admin/agentTops"
						class="current">代理排行榜</a> <a href="admin/setBonus">奖金设置</a>

				</div>
			</div>
		</div>
	</div>

	<!--内容-->
	<div class="w_content kcsz">
		<!-- <div class="zx_phb">
			<span class="fl">
            	<a href="javascript:void(0)">2012</a>
            	<a href="javascript:void(0)">2013</a>
            	<a href="javascript:void(0)" class="current">2014</a>
            	<a href="javascript:void(0)">2015</a>
            	<a href="javascript:void(0)">2016</a>
            	<a href="javascript:void(0)">2017</a>
            	<a href="javascript:void(0)">2018</a>
            </span>
            <span class="zx_Phb_txt fr">
            	最终榜单以2014年12月31日23时59分59秒截止数据为准。
            </span>
            
             <i class="clear"></i>
        </div>  -->
		<div class="div_table f_st">
			<table class="table" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th width="100">名次</th>
						<th width="120">姓名</th>
						<th width="25%">省区</th>
						<th width="120">城市</th>
						<th>成交量</th>
						<th>销售金额</th>
						<th>预计奖金</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pageEnt.listPages }" var="agtops" varStatus="status">
						<tr>
							<td>${status.index + 1 }</td>
							<td>${agtops.uname }</td>
							<td>四川省</td>
							<td>成都市</td>
							<td>${agtops.volume }</td>
							<td>20000.00</td>
							<td><span class="a color1">20000.00</span>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="page">

			<a href="" class="fy">&lt;</a> <a href="javascript:void(0)">1</a> <a href="javascript:void(0)">2</a> <a
				href="javascript:void(0)">3</a> <a href="javascript:void(0)">4</a> <a href="javascript:void(0)">5</a> <span>6</span> <a
				href="javascript:void(0)">8</a> <a href="javascript:void(0)">9</a> <a href="javascript:void(0)">...</a> <a href=""
				class="fy">&gt;</a>
		</div>
		<!-- <div class="phgz">
        	<h2>排行规则</h2>
        	<div class="phgz_txt">
            	<textarea></textarea>
            </div>
            <div class="kcszbtn"><input class="btn" type="button" value="确定修改"></div>
        
        </div>
         -->



	</div>
	<!--e内容-->











</body>
</html>
