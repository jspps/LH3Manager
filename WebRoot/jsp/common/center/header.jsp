<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.admin.db.bean.Learnhub" %>
<%
pageContext.setAttribute("appPath", request.getContextPath());
Object objAgent = session.getAttribute("enCenter");
String userLogin = "";
Learnhub learnhub;
String userName = "";
if(objAgent!=null){ 
	userLogin ="center";
	learnhub = (Learnhub)objAgent;
	userName = learnhub.name;
}
%>
<script type="text/javascript" >
	var userLogin = "<%=userLogin%>";
	//代理商中心
	if(userLogin!="center"){
		window.location.href = "${appPath}";
	};
</script>
<div class="header header_1">
            <div class="header_jb">
            <div class="f_st">
            	<span class="fl mr">尚学在线欢迎您！</span>
                <a class="fl mr"  href="${appPath}/center/basicInformation">【返回首页】</a>
                <span class="fl mr h_mail"><a href="${appPath}/center/latestNews">您的消息</a></span>
            	 <!--<span class="fl ml">您目前的招生业绩：<strong>1203.00</strong>，在代理商中排在第<strong>123</strong>位！</span>-->
                  <a class="fr" href="${appPath}/loginLhub">退出登录</a> 
                <span class="fr span">|</span>
                <a class="fr user_name" href="${appPath}/center/basicInformation"><%=userName%></a>
           	</div>
            </div>
</div>
