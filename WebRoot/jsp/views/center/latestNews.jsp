<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>学习中心管理后台_最新消息</title>
<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>
<script type="text/javascript" src="jsp/js/paging.js"></script>
</head>
<body class="body_bg1">
	<!--头部-->
		<jsp:include page="../../common/center/header.jsp"></jsp:include>
    
    <!--内容-->
    
    <div class="j_content">
    
     <!--菜单-->
    <jsp:include page="../../common/center/meun.jsp"></jsp:include>

    <div class="user_content">
    <div class="kcdp u_zxxx">
       <div class="kcdp_research">
        	<span class="kcdp_star"></span>
            <i class="clear"></i>
        </div>
        <c:forEach items="${pageEnt.listPages}" var = "ent" varStatus="">
        	<div class="kcdp_list">
	        	<div class="kcdp_right">
	            <div class="u_zx_time"><p:fmtDate parttern="yyyy-MM-dd" value="${ent.createtime }"/></div>
	        	<div class="kcdp_info">
	        		<span class="jj"></span>
	                <p>${ent.description}</p>
	        	</div>
	            <img src="jsp/imgs/kc_bor.png" width="100%" style="display:block;">
	        	</div>
	        </div>
        </c:forEach>
    </div>
    <p:pageTag name="pageEnt" action="center/latestNews"/>
    <i class="clear"></i>
    </div>
	</div>
</body>
</html>