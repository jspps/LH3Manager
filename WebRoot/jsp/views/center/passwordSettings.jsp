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
<title>学习中心管理后台_密码设置</title>
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
    	
        <table class="user_table" border="0" cellpadding="0" cellspacing="0">
        	<tr><th>原密码：</th><td><input type="password" id="ypsassword" class="input1 w_5"></td></tr>
        	<tr><th>设置新密码：</th><td><input type="password"  id="password" class="input1 w_5"></td></tr>
        	<tr><th>确认新密码：</th><td><input type="password" id="password2"  class="input1 w_5"></td></tr>
        	<tr><th></th><td style="text-align:center;">
        	<input type="button" onclick="modpassword();" class="btn_yes mt_10" value="确定"></td></tr>
        </table>
         
         
         
        
    
    	
    
    </div>
    <i class="clear"></i>
    </div>
    
 
 <script type="text/javascript">
	//审核
	function modpassword(){
		var ypsassword = jQuery("#ypsassword").val();
		var password = jQuery("#password").val();
		var password2 = jQuery("#password2").val();
		var bool = true;
		if(bool &&  ypsassword=="" ){
			alert("原密码不能为空");
			bool = false;
		}
		if(bool &&  password==""){
			alert("密码不能为空");
			bool = false;
		}
		if(bool && password2==""){
			alert("密码不能为空");
			bool = false;
		}
		if(bool){
			if(password.length<6){
				alert("密码不能少于6位！");
				bool = false;
			}
		}
		if(bool){
			if((ypsassword.length>20 || password.length>20)){
				alert("密码不能大于20位！");
				bool = false;
			}
		}
		if( bool &&  password!=password2){
			alert("两次密码不一致");
			bool = false;
		}
		if(bool && password==ypsassword){
			alert("新密码不能为原密码不一致");
			bool = false;
		}
		if(bool){
			jQuery.messager.progress({
				title:'请等待',
				text:'提交数据中...',
				interval:700
		    });
			jQuery.post("center/modpassword",{"ypsassword":ypsassword,"password2":password2,"password":password}, function(data) {
				jQuery.messager.progress('close');
				alert(data.msg);
				if(data.status==1){
					  location.replace(location);
				}
			}, "json");
		}
		
			 
	}
 </script>
</body>
</html>