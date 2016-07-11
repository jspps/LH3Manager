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
<title>尚学在线后台管理系统</title>
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
<body class="body_bg">
    <!--内容-->
    <div class="w_content kclb" style="min-width: 800px;">
        <div class="div_table f_st">
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
                        <th width="145">选择</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageEnt.listPages}" var = "ent" varStatus="">
	                    <tr>
	                        <td id="val_uu1_${ent.id}">${ent.lhub.name}</td>
	                        <td id="val_uu2_${ent.id}">${ent.coures.adqdepartmentFkDepartid.name} </td>
	                    	<td id="val_uu3_${ent.id}">${ent.coures.nmMajor}</td>
	                    	<td id="val_uu4_${ent.id}">${ent.coures.nmLevel}</td>
	                    	<td id="val_uu5_${ent.id}">${ent.coures.nmSub}</td>
	                    	<td id="val_uu6_${ent.id}">${ent.coures.nmArea}</td>
	                    	<td>
	                    	<select id="val_uu7_${ent.id}">
	                    	  <c:forEach items="${ent.kinds}" var = "kind" varStatus="">
	                    		<option value="${kind.id}">${kind.nmKClass}</option>
	                    	</c:forEach>
	                    	</select>
	                    	</td>
	                        <td>
	                        <a href="javascript:void(0);" onclick="thisCloseWindow('${ent.id}');" class="update">确定</a>
	                        </td>
	                   </tr>
					</c:forEach>
                </tbody>
        	</table>
    	</div>
    </div>
    
    <p:pageTag name="pageEnt" action="center/productList"/>
    
	<script type="text/javascript">
	//套餐id  学习中心	课程类别	专业名称	层次	科目	考试范围	套餐
	function thisCloseWindow(val_uuid){
		var val1 = $("#val_uu1_"+val_uuid).text();
		var val2 = $("#val_uu2_"+val_uuid).text();
		var val3 = $("#val_uu3_"+val_uuid).text();
		var val4 = $("#val_uu4_"+val_uuid).text();
		var val5 = $("#val_uu5_"+val_uuid).text();
		var val6 = $("#val_uu6_"+val_uuid).text();
		var val7 = $("#val_uu7_"+val_uuid).find("option:selected").text();
		var kindid =  $("#val_uu7_"+val_uuid).val();
 		parent.closeWindow(kindid,val1,val2,val3,val4,val5,val6,val7,'${uuid}');
	}
	</script>
</body>
</html>