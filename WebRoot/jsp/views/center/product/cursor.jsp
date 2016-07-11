<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!--游标-->
<div class="user_liuceng_div" style="min-width: 990px;">
	<div class="user_liuceng_list" onclick="click2Km()">
		<div class="user_liuceng_text_hover" type="1">选择科目</div>
		<div class="user_liuceng_ico_hover" num="1">1</div>
	</div>
	<div class="user_liuceng_list">
		<div class="user_liuceng_text" type="2">设置试题类别</div>
		<div class="user_liuceng_ico" num="2">2</div>
	</div>
	<div class="user_liuceng_list">
		<div class="user_liuceng_text" type="3">题型设置</div>
		<div class="user_liuceng_ico" num="3">3</div>
	</div>
	<div class="user_liuceng_list">
		<div class="user_liuceng_text" type="4">添加试题</div>
		<div class="user_liuceng_ico" num="4">4</div>
	</div>
	<div class="user_liuceng_list">
		<div class="user_liuceng_text" type="5">套餐设置</div>
		<div class="user_liuceng_ico" num="5">5</div>
	</div>
	<div class="user_liuceng_list">
		<div class="user_liuceng_text" type="6">提交审核</div>
		<div class="user_liuceng_ico" num="6">6</div>
	</div>
	<div class="user_liuceng_list">
		<div class="user_liuceng_text" type="7">销售</div>
		<div class="user_liuceng_ico" num="7">7</div>
	</div>
</div>
<script>
	function click2Km(){
		window.location.href = '<%=basePath%>'+"center/go2ManagerProducts";
	}
	
	$(function() {
		var curCursorType = "${curCursorType}";
		curCursorType = parseInt(curCursorType, 10);
		if (isNaN(curCursorType))
			curCursorType = 1;
		var jqTypes = $("div.user_liuceng_list div[type]");
		jqTypes.removeClass();
		jqTypes.each(function(){
			var val = $(this).attr("type");
			if(val == curCursorType){
				$(this).addClass("user_liuceng_text_hover");
			}else{
				$(this).addClass("user_liuceng_text");
			};
		});
		
		var jqNums = $("div.user_liuceng_list div[num]");
		jqNums.removeClass();
		jqNums.each(function(){
			var val = $(this).attr("num");
			if(val == curCursorType){
				$(this).addClass("user_liuceng_ico_hover");
			}else{
				$(this).addClass("user_liuceng_ico");
			};
		});
	});
</script>