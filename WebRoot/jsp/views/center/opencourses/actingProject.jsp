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
<title>嘻嘻</title>
<jsp:include page="../../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../../common/common_easyui.jsp"></jsp:include>
</head>
<body menuName="代理项目">
	<div style="min-width: 800px;">
		<div class="user_content">
			<div class="uc_content" style="overflow:visible;">
				<div class="div_table div_table_2 f_st">
					<table cellspacing="0" cellpadding="0" border="0" class="table">
						<thead class="thead">
							<tr>
								<th>学习中心</th>
								<th>课程类别</th>
								<th>专业名称</th>
								<th>层次</th>
								<th>科目</th>
								<th>考试范围</th>
								<th>套餐</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageEnt.listPages}" var="ent" varStatus="">
								<tr>
									<input type="hidden" id="val_uu8_${ent.id}" value="${ent.kindid}" />
									<td id="val_uu1_${ent.id}">${ent.lhub.name}</td>
									<td id="val_uu2_${ent.id}">${ent.course.adqdepartmentFkDepartid.name}</td>
									<td id="val_uu3_${ent.id}">${ent.course.nmMajor}</td>
									<td id="val_uu4_${ent.id}">${ent.course.nmLevel}</td>
									<td id="val_uu5_${ent.id}">${ent.course.nmSub}</td>
									<td id="val_uu6_${ent.id}">${ent.course.nmArea}</td>
									<td id="val_uu7_${ent.id}">${ent.kindEn.nmKClass}<font color="red">(${ent.num})</font></td>
									<td>
										<a href="javascript:void(0);" onclick="thisCloseWindow(${ent.id});">确定</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<p:pageTag name="pageEnt" action="center/actingProject?courseOpen=${courseOpen}&uuid=${uuid}"/>
			</div>
		</div>
		<i class="clear"></i>
	</div>
	<script type="text/javascript">
	//套餐id  学习中心	课程类别	专业名称	层次	科目	考试范围	套餐
	function thisCloseWindow(val_uuid){
		var val1 = $("#val_uu1_"+val_uuid).text();
		var val2 = $("#val_uu2_"+val_uuid).text();
		var val3 = $("#val_uu3_"+val_uuid).text();
		var val4 = $("#val_uu4_"+val_uuid).text();
		var val5 = $("#val_uu5_"+val_uuid).text();
		var val6 = $("#val_uu6_"+val_uuid).text();
		var val7 = $("#val_uu7_"+val_uuid).text();
		var kindid =  $("#val_uu8_"+val_uuid).val();
 		parent.closeWindow(kindid,val1,val2,val3,val4,val5,val6,val7,'${uuid}',val_uuid);
	}
	</script>
</body>
</html>