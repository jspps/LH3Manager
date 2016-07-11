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
        	<tr><th>我的支付宝：</th><td><input type="text" id="ypsassword" class="input1 w_5"></td></tr>
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
		if(ypsassword=="" && bool){
			alert("原密码不能为空");
			bool = false;
		}
		if(password==""&& bool){
			alert("密码不能为空");
			bool = false;
		}
		if(password2==""&& bool){
			alert("密码不能为空");
			bool = false;
		}
		if(ypsassword.length<6 || password.length<6){
			if(bool){
				alert("密码不能少于6位！");
			}
			
		}
		if(ypsassword.length>20 || password.length>20){
			if(bool){
				alert("密码不能少于6位！");
			}
		}
		if(password!=password2 && bool){
			alert("两次密码不一致");
			bool = false;
		}
		if(password==ypsassword){
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