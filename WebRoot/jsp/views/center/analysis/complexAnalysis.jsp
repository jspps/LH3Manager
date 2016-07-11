<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>模考分析_综合分析</title>
<jsp:include page="../../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg1">
	<!--头部-->
		<jsp:include page="../../../common/center/header.jsp"></jsp:include>
    
    <!--内容-->
    
    <div class="j_content">
    
     <!--菜单-->
    <jsp:include page="../../../common/center/meun.jsp"></jsp:include>

    <div class="user_content">
    	<div class="uc_content ">
   		<div class="mkwt"><a href="javascript:void(0)" class="current">学员名单</a><a href="center/complexAnalysis">综合分析</a></div>
        <div class="mkwt_sel">
        	<select><option>选择分类</option></select>
        	<select><option>选择专业</option></select>
        	<select><option>选择层次</option></select>
        	<select><option>选择课程</option></select>
        	<select><option>选择期数</option></select>
            <input type="button" value="确定" class="mkwt_sel_btn"/>
        </div>
        <div class="mkwt">
        	<a href="javascript:void(0)" class="">章节练习</a><a href="javascript:void(0)">历年真题</a>
        	<a href="javascript:void(0)" class="">全真模拟</a><a href="javascript:void(0)">绝胜押题</a>
        	<a href="javascript:void(0)" class="current">反馈问题</a>
            <span class="mkwt_span"><a href="javascript:void(0)" class="current">按内容分析</a><a href="javascript:void(0)">按分数段分析</a></span>
        </div>
        <div class="mkwt_list">
        	<div class="mkwt_h2">本期总人数：55人</div>
        	<div class="chart"><img src="../images/pic.jpg"/></div>
        
        
        
    	</div>
        
   
        
        
       
    	
    	</div>
    </div>
    <i class="clear"></i>
    </div>
    
    
    
 
 
</body>
</html>