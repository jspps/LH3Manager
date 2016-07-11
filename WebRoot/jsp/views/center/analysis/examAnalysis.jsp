<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://page.bowlong.com/jsp/tags" prefix="p"%>
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
<title>模考分析</title>
<jsp:include page="../../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg1">
	<!--头部-->
	<jsp:include page="../../../common/center/header.jsp"></jsp:include>

	<!--内容-->
	<div class="j_content">
		<!--菜单-->
		<jsp:include page="../../../common/center/meun.jsp"></jsp:include>

		<div class="user_content">
			<div class="uc_content " style="overflow:visible">
				<div class="mkwt">
					<a href="center/examAnalysis" class="current">学员名单</a>
					<!-- 
					<a href="center/complexAnalysis">综合分析</a>
					 -->
				</div>

				<div class="kcszbtn u_tkgl">
					<div class="mkwt_sel mkwt_sel_1 fl">
						<form action="center/examAnalysis" method="post">
							<jsp:include page="../../admin/thCourses.jsp" />
							<input type="submit" value="确定" class="mkwt_sel_btn" />
						</form>
					</div>
					<!-- 
					<input type="button" value="提交需求" class="btn">
					 -->
					<div class="clear"></div>
				</div>

				<div class="div_table f_st">
					<table cellspacing="0" cellpadding="0" border="0" class="table">
						<thead>
							<tr>
								<th width="85">序号</th>
								<th width="150">姓名</th>
								<th width="150">联系电话</th>
								<th width="150">账号开通时间</th>
								<th width="150">模考次数</th>
								<th width="150">平均正确率</th>
								<th width="100">排名</th>
								<th width="">评语</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageEnt.listPages}" var="ent" varStatus="s">
								<tr id="tr_date_${ent.id}">
									<td>${s.count + (ent.page - 1) * ent.pageSize}</td>
									<td>${ent.custname}</td>
									<td>${ent.phone}</td>
									<td><p:fmtDate parttern="yyyy-MM-dd" value="${ent.cust_createtime}"/> </td>
									<td>${ent.num}次</td>
									<td>${ent.avecorrectrate}%</td>
									<td>${s.count + (ent.page - 1) * ent.pageSize}</td>
									<td></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<!--分页-->
				<p:pageTag name="pageEnt" action="center/examAnalysis"/>

			</div>
		</div>
		<i class="clear"></i>
	</div>


	<script>
		$(function() {

			$(".table_txt").each(function() {
				$(this).Ipt_defult_word({
					"word" : "请输入您对TA的评价"
				})

			})

		})
	</script>



</body>
</html>