<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("appPath", request.getContextPath());
%>
<script>
$(function() {
	var name = $("body").attr("menuName");
	if(name!=null){
		selectMenu(name);
	}
});
//选中导航菜单
function selectMenu(name){
	var menus = $(".header_menu a");
	   for (var i=0;i<menus.length;i++) {
		    var liName = $(".header_menu a").eq(i).text();
		    if(name==liName){
		    	$(".header_menu a").eq(i).addClass("current");
		    }
	   }
}
</script>

		<div class="header_bg">
			<div class="h_content">
				<span class="fl"><a href="javascript:void(0)"><img src="jsp/imgs/logo.png"> </a> </span>
				<div class="fl header_menu">
					<a href="admin/adminAgentManage" >代理商管理</a>
					 <a href="admin/learnCenterManage">学习中心管理</a> 
					 <a href="admin/questionManage_set">题库管理</a> 
					 <a href="admin/orderManage">订购管理</a> 
					 <a href="admin/companyUsers">集团帐户</a>
					<a href="admin/manageUsers">管理用户</a> 
					<a href="admin/helpCenter">帮助中心</a>
					<a href="admin/newsManage">消息管理</a>
					<a href="admin/rmb2Kbi">兑换管理</a>
				</div>
			</div>
		</div>
<!-- 
成交情况                   agent/turnover
成交统计                   agent/tradeStatistics
错误反馈                   agent/errorFeedback
代理商管理后台信息         agent/agentInfo
代理项目                   agent/actingProject
课程开通                   agent/courseOpen
密码设置                   agent/passwordSettings     
最新消息                   agent/latestNews
 -->