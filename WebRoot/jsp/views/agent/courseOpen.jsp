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
<title>课程开通</title>
<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>



</head>
<body class="body_bg1" menuName="课程开通">
	<!--头部-->
	<jsp:include page="../../common/agent/header.jsp"></jsp:include>
	<!--内容-->

	<div class="j_content">
		<!--菜单-->
		<jsp:include page="../../common/agent/meun.jsp"></jsp:include>
		<div class="user_content">
			<div class="uc_content" style="overflow:visible;">
				<div class="kcszbtn">
					<div class="fl reseach">
						<input type="text" class="r_txt f_mor"><input value=""
							class="r_btn" type="submit">
					</div>
					<input class="btn btn_2" type="button" value="导出">
				</div>
				<div class="div_table f_st div_table_kc">
					<table class="table" border="0" cellpadding="0" cellspacing="0">
						<thead class="thead">

							<tr>
								<th>学习中心</th>
								<th>课程类别</th>
								<th>专业名称</th>
								<th>层次</th>
								<th>科目</th>
								<th>考试范围</th>
								<th>套餐</th>
								<th>学生姓名</th>
								<th>学生手机号</th>
								<th>登录密码</th>
								<th>账户状态</th>
								<th width="100">操作</th>
							</tr>

						</thead>
						<tbody>
							<form action="agent/modifyOpenKindCustByAgent" id="form_add">
								<input id="kindid_add" name="kindid" nullmsg="请选择套餐"
									type="hidden" />
								<tr id="tr_from_add">
									<td><a href="javascript:void(0);"
										onclick="openWindow('add');" id="uu_val1_add">请选择</a></td>
									<td><a href="javascript:void(0);"
										onclick="openWindow('add');" id="uu_val2_add">请选择</a></td>
									<td><a href="javascript:void(0);"
										onclick="openWindow('add');" id="uu_val3_add">请选择</a></td>
									<td><a href="javascript:void(0);"
										onclick="openWindow('add');" id="uu_val4_add">请选择</a></td>
									<td><a href="javascript:void(0);"
										onclick="openWindow('add');" id="uu_val5_add">请选择</a></td>
									<td><a href="javascript:void(0);"
										onclick="openWindow('add');" id="uu_val6_add">请选择</a></td>
									<td><a href="javascript:void(0);"
										onclick="openWindow('add');" id="uu_val7_add">请选择</a></td>
									<td><input class="kc_text_2" type="text" name="uname"
										nullmsg="学生姓名不能为空" />
									</td>
									<td><input class="kc_text_2" type="text" name="uphone"
										zz="电话" nullmsg="学生手机号不能为空" /></td>
									<td><input class="kc_text_2" type="text" name="upass"
										nullmsg="登录密码不能为空" /></td>
									<td>未使用</td>
									<td widtd="100"><a href="javascript:void(0);"
										onclick="operating('add');" class="check">添加</a>
									</td>
								</tr>
							</form>

							<c:forEach items="${pageEnt.listPages}" var="ent" varStatus="">
								<tr id="tr_date_${ent.id}">
									<td>${ent.kindEn.nmLhub}</td>
			                    	<td>${ent.nmDepart}</td>
			                    	<td>${ent.course.nmMajor}</td>
				                   	<td>${ent.course.nmLevel}</td>
				                   	<td>${ent.course.nmSub}</td>
				                   	<td>${ent.course.nmArea}</td>
				                   	<td>${ent.kindEn.nmKClass}</td>
			                    	<td>${ent.custname}</td>
			                    	<td>${ent.accEn.phone}</td>
									<td pass="${ent.accEn.lgpwd}">********</td>
									<td> 
				                    	<c:choose>
				                    	<c:when test="${ent.status==2}">已使用</c:when>
				                    	<c:otherwise>未使用</c:otherwise>
				                    	</c:choose>
			                    	</td>
									<td><a href="javascript:void(0);"
										onclick="delfromexamt('${ent.id}');" class="delete">删除</a> <a
										href="javascript:void(0);" onclick="showFrom('${ent.id}')"
										class="check">修改</a>
									</td>
								</tr>

								<tr id="tr_from_${ent.id}" style="display: none">
									<form action="agent/modifyOpenKindCustByAgent" id="form_${ent.id}">
									<input name="okcustid"  value="${ent.id}" type="hidden"/>
			                   		<input id="kindid_${ent.id}" value="${ent.kindid}"  name="kindid"  type="hidden"/>
					                    <td> <a  href="javascript:void(0);"  onclick="openWindow('${ent.id}');" id="uu_val1_${ent.id}">${ent.kindEn.nmLhub}</a></td>
				                        <td> <a  href="javascript:void(0);" onclick="openWindow('${ent.id}');" id="uu_val2_${ent.id}">${ent.nmDepart}</a></td>
				                        <td> <a href="javascript:void(0);" onclick="openWindow('${ent.id}');" id="uu_val3_${ent.id}">${ent.course.nmMajor}</a></td>
				                        <td> <a href="javascript:void(0);" onclick="openWindow('${ent.id}');" id="uu_val4_${ent.id}">${ent.course.nmLevel}</a></td>
				                        <td> <a href="javascript:void(0);" onclick="openWindow('${ent.id}');" id="uu_val5_${ent.id}">${ent.course.nmSub}</a></td>
				                        <td> <a href="javascript:void(0);" onclick="openWindow('${ent.id}');" id="uu_val6_${ent.id}">${ent.course.nmArea}</a></td>
				                        <td> <a href="javascript:void(0);" onclick="openWindow('${ent.id}');" id="uu_val7_${ent.id}">${ent.kindEn.nmKClass}</a></td>
				                        <td><input class="kc_text_2" type="text" name="uname" value="${ent.custname}" nullmsg="学生姓名不能为空"/></td>
				                        <td><input class="kc_text_2" type="text" name="uphone" value="${ent.accEn.phone}"  nullmsg="学生手机号不能为空" /> </td>
				                        <td><input class="kc_text_2" type="text" value="${ent.accEn.lgpwd}"  name="upass"   nullmsg="登录密码不能为空" /></td>
				                        <td>
				                        	<c:choose>
					                    	<c:when test="${ent.status==2}">已使用</c:when>
					                    	<c:otherwise>未使用</c:otherwise>
					                    	</c:choose>
				                        </td>
										<td widtd="100">
											<a href="javascript:void(0);" onclick="hideFrom('${ent.id}')" class="delete">取消</a> 
											<a href="javascript:void(0);" onclick="operating('${ent.id}')" class="check">确定</a>
										</td>
									</form>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
				<p:pageTag name="pageEnt" action="agent/courseOpen"/>
			</div>
		</div>
		<i class="clear"></i>
	</div>

	<div id="window"
		style="padding:5px;background: #fafafa ; display: none;">
		<iframe id="operate" width="100%" scrolling="auto" height="100%"
			frameborder="0" src=""></iframe>
	</div>


	<script type="text/javascript">
		//parent.closeWindow(uuid,kindid){
		//学习中心	课程类别	专业名称	层次	科目	考试范围	套餐
		function showFrom(id) {
			$("#tr_date_" + id).hide();
			$("#tr_from_" + id).show();
		}
		function hideFrom(id) {
			$("#tr_date_" + id).show();
			$("#tr_from_" + id).hide();
		}
		function delfromexamt(id) {
			jQuery.messager.progress({
				title : '请等待',
				text : '提交数据中...',
				interval : 700
			});
			jQuery.post("agent/delCourseopened", {
				"okcustid" : id
			}, function(data) {
				jQuery.messager.progress('close');
				alert(data.msg);
				if (data.status == 1) {
					window.location.reload();
				}
			}, "json");
		}
		function closeWindow(kindid, val1, val2, val3, val4, val5, val6, val7,
				uuid, agentitemid) {
			$("#uu_val1_" + uuid).text(val1);
			$("#uu_val2_" + uuid).text(val2);
			$("#uu_val3_" + uuid).text(val3);
			$("#uu_val4_" + uuid).text(val4);
			$("#uu_val5_" + uuid).text(val5);
			$("#uu_val6_" + uuid).text(val6);
			$("#uu_val7_" + uuid).text(val7);
			$("#kindid_" + uuid).val(kindid);
			jQuery('#window').window('close');
		}
		
		function openWindow(uuid) {
			jQuery("#operate").attr("src",
					"agent/actingProject?courseOpen=1&uuid=" + uuid);
			jQuery('#window').show();
			jQuery('#window').window({
				"title" : "选择套餐",
				width : jQuery("body").width() / 1.5,
				height : jQuery("body").height(),
				modal : true,
				closable : true,
				minimizable : false,
				maximizable : false,
				shadow : false,
				resizable : false,
				draggable : false,
				collapsible : false
			});
		};

		function operating(id) {
			var kindid = $("#kindid_" + id).val();
			if (kindid == null || kindid == "") {
				alert("请选择套餐");
			} else {
				if (jiaoyan('#tr_from_' + id + ' :input')) {
					jQuery.messager.progress({
						title : '请等待',
						text : '提交数据中...',
						interval : 700
					});

					jQuery.post($("#form_" + id).attr('action'), $(
							"#form_" + id).serialize(), function(data) {
						jQuery.messager.progress('close');
						alert(data.msg);
						if (data.status == 1) {
							window.location.reload();
						}
					}, "json");
				}
			}

		};
	</script>
</body>
</html>