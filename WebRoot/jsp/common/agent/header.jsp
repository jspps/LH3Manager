<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.admin.db.bean.Agent" %>
<%
pageContext.setAttribute("appPath", request.getContextPath());
Object objAgent = session.getAttribute("enAgent");
String userLogin = "";
String userName = "";
if(objAgent!=null){
	userLogin ="agent";
	Agent _agent1 = (Agent)objAgent;
	userName = _agent1.getUname();
}
%>
<script type="text/javascript" >
	var userLogin = "<%=userLogin%>";
	//代理商中心
	if(userLogin!="agent"){
		window.location.href = "${appPath}";
	};
</script>
    <div class="header">
            <div class="header_jb">
            <div class="f_st">
            	<span class="fl mr">尚学在线欢迎您！</span>
               
                <a class="fr" href="${appPath}/loginAgent">退出登录</a> 
                <span class="fr span">|</span>
                <a href="${appPath}/agent/agentInfo" class="fr user_name"><%=userName%></a>
                <span class="header_center">尚学在线管理后台</span>
            
           	</div>
            </div>
    </div>
