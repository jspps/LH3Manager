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
	var menus = $(".u_menu a");
	   for (var i=0;i<menus.length;i++) {
		    var liName = $(".u_menu a").eq(i).text();
		    if(name==liName){
		    	$(".u_menu a").eq(i).addClass("current");
		    }
	   }
}
</script>

<div class="user_meun dl_meun">
    	<h2 class="h2" style="color:#3f8ec6;">代理商管理后台</h2>
    	<div class="u_menu">
        	<h2><a href="agent/agentInfo" class="a_1">基本信息</a></h2>
        </div>
        <div class="u_menu">
        	<h2><a href="agent/latestNews" class="a_2">最新信息</a></h2>
        </div>
        <!-- 
        <div class="u_menu">
        	<h2><a href="agent/actingProject" class="a_3">代理项目</a></h2>
        </div>
        -->
        <div class="u_menu">
        	<h2><a href="agent/courseOpen" class="a_4">课程开通</a></h2>
        </div>
        <div class="u_menu">
        	<h2><a href="agent/tradeStatistics" class="a_5">成交统计</a></h2>
        </div>
        <div class="u_menu">
        	<h2><a href="agent/passwordSettings" class="a_6">密码设置</a></h2>
        </div>
        <!-- 
        <div class="u_menu">
        	<h2><a href="agent/mypay" class="a_7">我的支付宝</a></h2>
        </div>
        -->
</div>