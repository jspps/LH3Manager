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
<title>尚学在线后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="尚学后台管理">
<meta http-equiv="description" content="错误反馈">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg" menuName="学习中心管理">
	<!--头部-->
    <div class="header">
	   <jsp:include page="../../common/admin/header.jsp"></jsp:include>
	   <!-- 菜单 -->
	   <jsp:include page="../../common/admin/meun.jsp"></jsp:include>

            
            <div class="header_child">
            <div class="w_content">
              <div class="list">
                	<a href="admin/learnCenterManage">学习中心管理</a>
                	<a href="admin/errorBack" class="current">错误反馈（${trs}）</a>
                           
               </div>
            </div>
            </div>
    </div>
    
    <!--内容-->
    <div class="w_content kcsz">
    	<div class="kcszbtn">
        	<div class="fl reseach"><input type="text" class="r_txt" /><input value="" class="r_btn" type="submit"/></div>
        	<input class="btn btn_2" onclick="OutXls('errorBackOutXls');" type="button" value="导出数据"/>
        </div>
        <div class="div_table f_st">
        	<table class="table" border="0" cellpadding="0" cellspacing="0">
        		<thead>
                <tr>
        			<th width="15%">时间</th>
                    <th width="10%">提交人</th>
                    <th width="25%">课程名称</th>
                    <th width="15%">所属信息中心</th>
                    <th>主要问题</th>
                </tr>
        		</thead>
                <tbody>
                	 <c:forEach items="${pageEnt.listPages}" var = "ent">
		                	<tr>
		                    	<td><p:fmtDate parttern="yyyy-MM-dd HH:mm:ss" value="${ent.createtime}"/> </td>
		                    	<td>${ent.nmCust}</td>
		                    	<td>${ent.nmExam}</td>
		                    	<td>${ent.nmLhub}</td>
		                    	<td>${ent.description}</td>
		                    </tr>
                      </c:forEach>
                  </tbody>
                    <tfoot>
                    <tr>
                    	<td colspan="7" class="al">
                        	<span class="cufk_span">合计错误题目数：${trs}个</span>
                            <span class="cufk_span">总题目数：${count}个</span>
                        	<span class="cufk_span">错误率：${trscount}%</span>
                        </td>
                    </tr>
                    
                    </tfoot>
        	</table>
    	</div>
        
   		 <p:pageTag name="pageEnt" action="admin/errorBack"/>
    </div>
	<script type="text/javascript"></script>
</body>
</html>