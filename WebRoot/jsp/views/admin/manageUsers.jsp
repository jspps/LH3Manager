<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<base href="<%=basePath%>" />
<title>尚学在线后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="尚学后台管理" />
<meta http-equiv="description" content="管理用户" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

<link rel="stylesheet" type="text/css" href="jsp/css/style.css" />
<script type="text/javascript" src="jsp/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="jsp/js/manageUsers.js"></script>

<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>

</head>
<body class="body_bg" menuName="管理用户">
	<!--头部-->
    <div class="header">
	   <jsp:include page="../../common/admin/header.jsp" />
	   <!-- 菜单 -->
	   <jsp:include page="../../common/admin/meun.jsp" />
    </div>
    
    <!--内容-->
    <div class="w_content kcsz ">
    	<form action="admin/submitNewUser" method="post" onsubmit="return onsubmitNewUser(this);">
    	<div class="glyh f_st">
        	<span>登录帐号：</span><input type="text" value="" class="text_yh" id="lgid" name="lgid" />
        	<span>登录密码：</span><input type="text" value="" class="text_yh" id="lgpwd" name="lgpwd" />
        	<span>用户名：</span><input type="text" value="" class="text_yh" id="uname" name="uname" />
        	<span>电话：</span><input type="text" value="" class="text_yh" id="phone" name="phone" />
        </div>
        <div class="glyh_list">
        	<h2>权限模块：</h2>
        	<ul>
        		<c:forEach items="${powerParents}" var="item" varStatus="itemStatus">
        			<c:set var="curType" value="${itemStatus.index+1}"></c:set>
        			<c:set var="subKey" value="id_${item.prid}"></c:set>
        			<li class="f_st" type="${curType}">
	                	<span class="span"><input type="checkbox" value="${item.prid}"  onclick="clickCBox(this,${curType});" />  ${item.name}</span>
	                	<c:forEach items="${subMap}" var="isubMap">
	                		<c:if test="${isubMap.key == subKey}">
	                			<c:forEach items="${isubMap.value}" var="sub" varStatus="subStatus">
	                				<span><input type="checkbox" value="${sub.prid}" onclick="clickCBox(this,${curType});" />${sub.name}</span>
	                			</c:forEach>
	                		</c:if>
	                	</c:forEach>
	                </li>
        		</c:forEach>
            </ul>
            <div class="phgz phgz_1 phgz_2">
	        	 <div class="kcszbtn">
	            	<input class="btn" type="submit" value="确定" />
	            </div>
	        </div>
        </div>
        </form>
        
        <form action="admin/queryUserBy" method="post" onsubmit="return onsubmitQueryUser();" id="fm_query_use">
        <div class="kcszbtn">
        	<div class="fl reseach">
        		<input type="text" class="r_txt f_mor" id="queryName" name="queryName" /><input type="submit" class="r_btn" value="" />
        	</div>
        </div>
        </form>
        
        <div class="div_table div_table_1 f_st">
        	<table cellspacing="0" cellpadding="0" border="0" class="table">
        		<thead>
                <tr>
        			<th width="140"></th>
                    <th width="120">权限模板</th>
                    <th width="25%">权限</th>
                    <th width="120">操作</th>
                </tr>
        		</thead>
                <tbody id="query_cont">
                </tbody>
        	</table>
    	</div>
    </div>
</body>
</html>