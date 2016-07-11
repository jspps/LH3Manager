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
<title>尚学在线后台管理系统-题库管理-课程点评</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="尚学后台管理">
<meta http-equiv="description" content="题库管理--课程点评">
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
              <div class="list fr">
                	<a href="admin/questionManage_set">课程设置</a>
                	<a href="admin/questionManage_comment" class="current">课程点评</a>
                	<a href="admin/questionManage_list">题库列表</a>
               </div>
            </div>
            </div>
    </div>
    
    <!--内容-->
    <div class="kcdp_1">
    	<div class="kcdp_research">
        	<div class="fr reseach">
        	<input type="text" id="reseachName" value="${name}" class="r_txt" />
        	<input value="" class="r_btn" type="submit" onclick="selectquestionManage();"/></div>
        	<span class="kcdp_star"></span>
            <i class="clear"></i>
        </div>
        
         <c:forEach items="${pageEnt.listPages}" var = "ent" varStatus="">
				<div class="kcdp_list">
		        	<span class="fl kcdp_time f_st f_w">${ent.createtime}</span>
		        	<div class="kcdp_right">
		        	<div class="kcdp_info">
		        		<span class="jj"></span>
		        		<h2><a href="javascript:void(0);">企业人力资源管理师一级基础知识</a></h2>
		                <p>${ent.appraisetext}</p>
		        	</div>
		        	<!-- 
		            <div class="kcdp_hf">
		        		<span class="jj"></span>
		                <h2>回复：</h2>
		                <div class="hfinfo">中华人民共和国中华人民共和国中华人民共和国中华人民共和国中华人民共和国中华人民共和国中华人民共和国</div>
		            </div>
		             -->
		        	</div>
		        </div>
        </c:forEach>
    	<p:pageTag name="pageEnt" action="admin/questionManage_comment"/>
    </div>
    
  	<script type="text/javascript">
		function selectquestionManage(){
			$("#questionManage_name").val($("#reseachName").val());
			$("#learnCenterManage").submit();
		}
    </script>
</body>
</html>