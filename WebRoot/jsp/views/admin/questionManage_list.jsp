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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<base href="<%=basePath%>">
<title>尚学在线后台管理系统-题库管理-课程列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="尚学后台管理">
<meta http-equiv="description" content="题库管理--课程列表">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg" menuName="题库管理">
	<!--头部-->
	<div class="header">
		<jsp:include page="../../common/admin/header.jsp"></jsp:include>
		<!-- 菜单 -->
		<jsp:include page="../../common/admin/meun.jsp"></jsp:include>


		<div class="header_child">
			<div class="h_content">
				<div class="list">
					<a href="admin/questionManage_set">课程设置</a> <a
						href="admin/questionManage_comment">课程点评</a> <a
						href="admin/questionManage_list" class="current">题库列表</a>
				</div>
			</div>
		</div>
	</div>

	<!--内容-->
	<div class="w_content kclb">
		<div class="div_table f_st">
			<table class="table" border="0" cellpadding="0" cellspacing="0">
				<thead class="thead">
					<tr>
					<form action="admin/questionManage_list" method="post">
						<jsp:include page="thCourses.jsp" />
						<th>章节练习</th>
						<th>历年真题</th>
						<th>全真模拟</th>
						<th>绝胜押题</th>
						<th>学习中心</th>
						<th>联系人</th>
						<th>创建时间</th>
						<th width="145">
							<input type="submit" value="进行查询" />
						</th>
					</form>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pageEnt.listPages}" var="ent" varStatus="">
						<tr>
							<td>${ent.coures.adqdepartmentFkDepartid.name} </td>
	                    	<td>${ent.coures.nmMajor}</td>
	                    	<td>${ent.coures.nmLevel}</td>
	                    	<td>${ent.coures.nmSub}</td>
	                    	<td>${ent.coures.nmArea}</td>
							<td>${ent.exercisesnum}</td>
							<td>${ent.ahentinum}</td>
							<td>${ent.simulationnum}</td>
							<td>${ent.vastnum}</td>
							<td>${ent.lhub.name}</td>
							<td>${ent.lhub.uname}</td>
							<!-- <td><a href="javascript:void(0)">12</a></td> -->
							<th><p:fmtDate parttern="yyyy-MM-dd" value="${ent.createtime}" /></th>
							<td><a href="javascript:void(0);"
								onclick="deletepid(${ent.id});" class="delete">删除</a> <c:choose>
									<c:when test="${ent.examineStatus == 0 }">
										<span class="check">题库添加中</span>
									</c:when>
									<c:when test="${ent.examineStatus == 1 }">
										<a href="javascript:void(0);" class="check"
											onclick="checkpid(${ent.id});">审核</a>
									</c:when>
									<c:when test="${ent.examineStatus == 2 }">
										<a href="javascript:void(0);" class="check"
											title="${ent.examineDes}">审核不通过</a>
									</c:when>
									<c:when test="${ent.examineStatus == 3 }">
										<a href="javascript:void(0);" class="check"
											onclick="updatepid(${ent.id});">推荐</a>
									</c:when>
								</c:choose></td>
						</tr>

						<tr id="tr_from_${ent.id}" style="display: none">
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>

		<!--弹出层-->
		<div id="tab_zz_check" class="tab_zz hide"></div>
		<div id="tab_check" class="tab hide" id="hfyj">
			<div class="tab_info">
				<div class="hfyj">
					<h2>
						<a href="javascript:void(0)" onclick="closecheck();"
							class="t_close"></a>
					</h2>
					<p>回复意见：</p>
					<div class="text_div">
						<input id="checkpid" type="hidden" />
						<textarea id="neirong"></textarea>
					</div>
					<div class="btn_div">
						<input type="button" onclick="examineStatus(2);" class="tab_btn1" />
						<input type="button" onclick="examineStatus(3);" class="tab_btn2" />
					</div>
				</div>
			</div>
		</div>
		<div style="padding-top: 10px;">
			<p:pageTag name="pageEnt" action="admin/questionManage_list"/>
		</div>
	</div>
	<!--e内容-->

	<script type="text/javascript">
	function closecheck(){
		$("#tab_zz_check").hide();
		$("#tab_check").hide();
	}
	function checkpid(pid){
		$("#tab_zz_check").show();
		$("#tab_check").show();
		$("#checkpid").val(pid);
		
	}
	// 0不通过 1 通过
	function examineStatus(es){
		var examineDes  = $("#neirong").val();
		var pid = $("#checkpid").val();
		if(neirong==null || neirong==""){
			alert("回复意见不能为空！");
		}else{
			jQuery.messager.progress({
				title:'请等待',
				text:'提交数据中...',
				interval:700
			});
			
			var url = '<%=basePath%>' + "admin/statusProduct";
			
			jQuery.post(url, {examineDes:examineDes,pid:pid,examineStatus:es}, function(data) {
				jQuery.messager.progress('close');
				alert(data.msg);
				if(data.status==1){
					  location.replace(location);
				}
		    }, "json");
		}
	}
 
	function deletepid(pid){
		if(confirm("删除是不可恢复的，你确认要删除吗？")){
			jQuery.messager.progress({
				title:'请等待',
				text:'提交数据中...',
				interval:700
			});
			var url = '<%=basePath%>' + "admin/deleteProduct";
			jQuery.post(url, {pid:pid}, function(data) {
				jQuery.messager.progress('close');
				alert(data.msg);
				if(data.status==1){
					  location.replace(location);
				}
		    }, "json");
		}
	}
	
	function updatepid(productid){
		jQuery.messager.progress({
			title:'请等待',
			text:'提交数据中...',
			interval:700
		});
		var url = '<%=basePath%>' + "admin/recommendProduct";
		var data = {"productid":productid};
		var infun4CallBack = function(back){
			jQuery.messager.progress('close');
			alert(back.msg);
		};
		jQuery.post(url,data,infun4CallBack,"json");
	}
</script>
</body>
</html>