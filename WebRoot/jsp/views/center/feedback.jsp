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
<title>模考分析_问题反馈</title>
<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg1">
	<!--头部-->
		<jsp:include page="../../common/center/header.jsp"></jsp:include>
    
    <!--内容-->
    
    <div class="j_content">
    
     <!--菜单-->
    <jsp:include page="../../common/center/meun.jsp"></jsp:include>
    
    <div class="user_content">
    	<div class="uc_content ">
   		<div class="mkwt"><a href="javascript:void(0)" class="current">学员名单</a></div>
        <div class="mkwt_sel">
        	<select><option>选择分类</option></select>
        	<select><option>选择专业</option></select>
        	<select><option>选择层次</option></select>
        	<select><option>选择课程</option></select>
        	<select><option>选择期数</option></select>
            <input type="button" value="确定" class="mkwt_sel_btn">
        </div>
        <div class="mkwt">
        	<a href="javascript:void(0)" class="">章节练习</a><a href="javascript:void(0)">历年真题</a>
        	<a href="javascript:void(0)" class="">全真模拟</a><a href="javascript:void(0)">绝胜押题</a>
        	<a href="javascript:void(0)" class="current">反馈问题</a>
        </div>
        <div class="reseach"><input type="text" class="r_txt f_mor"><input type="submit" class="r_btn" value=""></div>
        <div class="mkwt_list">
         <ul>
        	<li>
            	<p><a href="javascript:void(0)">李大钊</a>2023-23-23 12:02</p>
                <div class="mkwt_div">反馈问题反馈问题反反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题</div>
            </li> 
            	<li>
            	<p><a href="javascript:void(0)">李大钊</a>2023-23-23 12:02</p>
                <div class="mkwt_div">反馈问题反馈问题反反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题</div>
            </li> 
            	<li>
            	<p><a href="javascript:void(0)">李大钊</a>2023-23-23 12:02</p>
                <div class="mkwt_div">反馈问题反馈问题反反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题</div>
            </li> 
            	<li>
            	<p><a href="javascript:void(0)">李大钊</a>2023-23-23 12:02</p>
                <div class="mkwt_div">反馈问题反馈问题反反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题</div>
            </li> 
            	<li>
            	<p><a href="javascript:void(0)">李大钊</a>2023-23-23 12:02</p>
                <div class="mkwt_div">反馈问题反馈问题反反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题</div>
            </li> 
            	<li>
            	<p><a href="javascript:void(0)">李大钊</a>2023-23-23 12:02</p>
                <div class="mkwt_div">反馈问题反馈问题反反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题</div>
            </li> 
            	<li>
            	<p><a href="javascript:void(0)">李大钊</a>2023-23-23 12:02</p>
                <div class="mkwt_div">反馈问题反馈问题反反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题</div>
            </li> 
            	<li>
            	<p><a href="javascript:void(0)">李大钊</a>2023-23-23 12:02</p>
                <div class="mkwt_div">反馈问题反馈问题反反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题</div>
            </li> 	<li>
            	<p><a href="javascript:void(0)">李大钊</a>2023-23-23 12:02</p>
                <div class="mkwt_div">反馈问题反馈问题反反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题</div>
            </li> 
            	<li>
            	<p><a href="javascript:void(0)">李大钊</a>2023-23-23 12:02</p>
                <div class="mkwt_div">反馈问题反馈问题反反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题反馈问题</div>
            </li> 
         
         
         
        </ul>
    	</div>
        
        <div class="kcszbtn">
        	 <div class="page">
        
                <a class="fy" href="">&lt;</a>
                <a href="javascript:void(0)">1</a>
                <a href="javascript:void(0)">2</a>
                <a href="javascript:void(0)">3</a>
                <a href="javascript:void(0)">4</a>
                <a href="javascript:void(0)">5</a>
                <span>6</span>
                <a href="javascript:void(0)">8</a>
                <a href="javascript:void(0)">9</a>
                <a href="javascript:void(0)">...</a>
                <a class="fy" href="">&gt;</a>
            
            	<input class="btn fr" type="button" value="导出数据">
            </div>
        
        	
        </div>
        
        
    	
    	</div>
    </div>
 </div>
    
 
 
</body>
</html>