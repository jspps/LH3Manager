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
<title>学习中心管理后台_错误反馈</title>
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
    <div class="user_list">
    	<div class="kcszbtn">
       		<input class="btn btn_2" type="button" value="导出数据"/>
        </div>
    	<div class="div_table f_st">
        	<table class="table" border="0" cellpadding="0" cellspacing="0">
        		<thead>
                <tr>
        			<th width="85">时间</th>
                    <th width="85">提交人</th>
                    <th width="25%">课程名称</th>
                   <!--  <th width="85">题目序号</th> -->
                    <th>主要问题</th>
                    <th width="55">操作</th>
                </tr>
        		</thead>
                <tbody>
                	 <c:forEach items="${pageEnt.listPages}" var = "ent" varStatus="">
		                	<tr>
		                    	<td><p:fmtDate parttern="yyyy-MM-dd" value="${ent.createtime }"/> </td>
		                    	<td>${ent.nmCust}</td>
		                    	<td>${ent.nmExam}</td>
		                    	<!-- <td>232题</td> -->
		                    	<td>${ent.description}</td>
		                    	<td>
		                    		<a href="center/errback4Redirect?optid=${ent.optid}" class="a color1">修改</a>
		                    	</td>
		                    </tr>
                      </c:forEach>

                    
                  </tbody>
                  <tfoot>
                    <tr>
                    	<td colspan="7" class="al">
                        	<span class="cufk_span">合计错误题目数：${countError}个</span>
                            <span class="cufk_span">总题目数：${count}个</span>
                        	<span class="cufk_span">错误率：${rate}%</span>
                        </td>
                    </tr>
                   </tfoot>
        	</table>
    	</div>
    	<p:pageTag name="pageEnt" action="center/errorBack"/>
    </div>
    <i class="clear"></i>
    </div>
 </div>
</body>
</html>