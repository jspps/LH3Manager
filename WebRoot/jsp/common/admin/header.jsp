<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
pageContext.setAttribute("appPath", request.getContextPath());
Object objAgent = session.getAttribute("enAdmin");
String userLogin = "";
if(objAgent!=null){
	userLogin ="agent";
}
%>
<script type="text/javascript" >
	var userLogin = "<%=userLogin%>";
	//代理商中心
	if(userLogin!="agent"){
		window.location.href = "${appPath}";
	};
	function OutXls(url){
		window.location.href = "${appPath}"+"/admin/"+url;
	}
</script>
		<div class="head_uinfo">
			<span class="fl">当前用户：【<a href="javascript:void(0);">admin</a>】 </span> <a
				href="admin/login" class="fr">退出登录</a> <i class="clear"></i>
		</div>
