<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/tags" prefix="date"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://page.bowlong.com/jsp/tags" prefix="p"%>

<%
    pageContext.setAttribute("appPath", request.getContextPath());
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
<meta http-equiv="description" content="代理商管理">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

<link rel="stylesheet" type="text/css" href="jsp/css/style.css" />
<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="" menuName="代理商管理">
	<!--头部-->
	<div class="header">
		
	   <jsp:include page="../../common/admin/header.jsp"></jsp:include>
	   <!-- 菜单 -->
	   <jsp:include page="../../common/admin/meun.jsp"></jsp:include>

		<!-- 
		<div class="header_child">
			<div class="h_content">
				<div class="list fr">
					<a href="admin/adminAgentManage" class="current">代理管理</a> <a
						href="admin/agentTops">代理排行榜</a> <a href="admin/setBonus">奖金设置</a>
				</div>
			</div>
		</div>
		 -->
	</div>
	<!--内容-->

	<div class="user_content">
		<div class="user_list">
			<div class="kcszbtn">
				<div class="fl reseach">
					<form action="admin/adminAgentManage" id="learnCenterManage" method="post">
					<input type="text" placeholder="请输入代理编号"   name="code" class="r_txt f_mor"  value="${code}"> 
					<input type="submit" class="r_btn" value="">
					</form>
				</div>
				<input type="button" onclick="OutXls('adminAgentManageOutXls');" value="导出数据" class="btn btn_2">
			</div>

			<div class="div_table f_st">
				<table class="table" border="0" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th>提交时间</th>
							<th>代理人员</th>
							<th>代理编号</th>
							<th>所在地</th>
							<th>登录用户名</th>
							<th>成交量</th>
							<th>实得佣金</th>
							<th>代理截止时间设置</th>
							<th>代理状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageEnt.listPages }" var="curAgent">
							<tr>
								<td><p:fmtDate parttern="yyyy-MM-dd HH:mm:ss" value="${curAgent.createtime}"/> </td>
								<td>${curAgent.uname}</td>
							    <td onclick="skip2Agent(${curAgent.accountid});">${curAgent.code}</td>  
								<td>${curAgent.seat }</td>
								 <td>${curAgent.accountFkAccountid.phone}</td>  
								<td>${curAgent.volume }</td>
								<td>${curAgent.curmoney }</td>
								<td><input style="width: 200px;" id = "setTime_${curAgent.agid}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" value="${curAgent.endtime}"></td>
								<td>
									<c:choose>
							 		<c:when test="${curAgent.examineStatus == 2}">不通过</c:when>
							 		<c:when test="${curAgent.examineStatus == 3}">已通过</c:when>
							 		<c:otherwise>审核中</c:otherwise>
							 		</c:choose>
								</td>
								<td>
									<input type="button" value="删除" class="delete" onclick="deleteCurAgent('${curAgent.agid}')" /> 
									<input type="button" value="审核" class="check" parVal="${curAgent.agid}" />
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
        <p:pageTag name="pageEnt" action="admin/adminAgentManage"/>
		</div>
	</div>
	<i class="clear"></i>
 
	<!--弹出层-->
	<div class="tab_zz hide" id="tab_zz111"></div>
	<div class="tab hide" id="hfyj">
		<div class="tab_info">
			<div class="hfyj">
				<h2>
					<a href="javascript:void(0);" onclick="t_close();" class="t_close"></a>
				</h2>
				<p>回复意见：</p>
				<div class="text_div">
					<textarea id="examineDes"></textarea>
				</div>
				<div class="btn_div">
					<input type="hidden" value="" id="parVal" name="parVal" />
					<input type="hidden" value="" id="timeVal" name="timeVal" />
					<input type="button" value="" class="tab_btn1" onclick="notPassAuditing(parVal.value)" /> 
					<input type="button" value="" class="tab_btn2" onclick="passAuditing(parVal.value)" />
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function t_close(){
		$(".tab_zz").addClass("hide");
		$("#hfyj").addClass("hide");
	};
		$(function() {
			$(".Wdate").each(function(){
				var date = $(this).val();
				if(date!=null && date!="" && date.length>10){
					$(this).val(date.substring(0,date.length-11));
				}
			  });
			$(".check").click(function() {
				var jqthis = $(this);
				$("#parVal").val(jqthis.attr("parVal"));
				var timerInput = jqthis.parent().siblings().children("input[type='text']");
				$("#timeVal").val(timerInput.val());
				//alert(timerInput.val());
				var h = 352;
				var top = $(window).scrollTop() + ($(window).height() - h) / 2;
				if (top <= 0) {
					top = 0;
				}
				$(".tab_zz").removeClass("hide");
				$("#hfyj").css("top", top);
				$("#hfyj").removeClass("hide");

			});

		});
		//删除代理商管理信息
		function deleteCurAgent(id) {
			if (confirm("删除是不可恢复的，你确认要删除吗？")) {
				jQuery.post("admin/deleteAgent", {
					agid : id
				}, function(data) {
					location.reload();
					alert(data.msg);
				}, "json");
			}
		}
		
		// 去代理商
		function skip2Agent(id){
			window.open('<%=basePath%>'+"admin/skip2Agent?id="+id);
		}

		//拒绝代理商通过审核
		function notPassAuditing(id) {
			var examineDes  = $("#examineDes").val();
			jQuery.messager.progress({
				title:'请等待',
				text:'提交数据中...',
				interval:700
		    });
			jQuery.post("admin/notPassAuditing", {
				agid : id,
				examineDes:examineDes
			}, function(data) {
				jQuery.messager.progress('close');
				alert(data.msg);
				if(data.status==1){
					  location.replace(location);
				}
			}, "json");
		}

		//代理商通过审核
		function passAuditing(id) {
			var timeStr = $("#setTime_"+id).val();
			var examineDes  = $("#examineDes").val();
			jQuery.messager.progress({
				title:'请等待',
				text:'提交数据中...',
				interval:700
		    });
			jQuery.post("admin/passAuditing", {
				agid : id,
				time:timeStr,
				examineDes:examineDes
			}, function(data) {
				jQuery.messager.progress('close');
				alert(data.msg);
				if(data.status==1){
					  location.replace(location);
				}
			}, "json");
		}
	</script>
</body>
</html>
