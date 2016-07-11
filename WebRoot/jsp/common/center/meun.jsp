<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("appPath", request.getContextPath());
%>
<div class="user_meun">
    	<h2 style="background:url(jsp/imgs/leftuser2.png) no-repeat;" class="h2">学习中心管理后台</h2>
    	<div class="u_menu jtxx">
        	<h2>机构信息</h2>
        	<!-- class="current" -->
            <a href="center/basicInformation">基本信息</a>
            <a  href="center/passwordSettings">密码设置</a>
            <a href="center/latestNews">最新消息</a>
        </div>
        
        <div class="u_menu tkgl">
        	<h2>题库管理</h2>
            <a href="center/go2ManagerProducts">题库管理</a>
            <a href="center/latestReviews">最新点评</a>
            <a href="center/errorBack">错误反馈</a>
            <!-- 
            <a href="center/feedback">同学有问</a>
            -->
        </div>
        
        <div class="u_menu mkgl">
        	<h2>模考管理</h2>
            <a href="center/courseOpen">课程开通</a>
            <a href="center/tradeStatistics">成交情况</a>
            <a href="center/examAnalysis">模考分析</a>
        </div>
        <!-- 
        <div class="u_menu zjzh">
        	<h2>资金账户</h2>
            <a href="center/mypay">支付宝账户</a>
        </div>
         -->
    </div>