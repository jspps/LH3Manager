<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://page.bowlong.com/jsp/tags" prefix="p"%>
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
<title>学习中心管理后台_最新点评</title>
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
    <div class="kcdp_1">
    	<div class="kcdp_research">
        	<span class="kcdp_star"></span>
            <i class="clear"></i>
        </div>
        
        <c:forEach items="${pageEnt.listPages}" var = "ent" varStatus="">
				<div class="kcdp_list" style="width: 94%;">
		        	<span class="fl kcdp_time f_st f_w"><p:fmtDate parttern="yyyy-MM-dd" value="${ent.createtime }"/></span>
		        	<div class="kcdp_right">
		        	<div class="kcdp_info">
			        		<span class="jj"></span>
			        		<h2><a href="javascript:void(0);">${ent.kindname }</a></h2>
			                <p>${ent.appraisetext }</p>
			        	</div>
			            <div class="kcdp_hf" style="width: 96%;">
			        		<span class="jj"></span>
			                <h2>回复：</h2>
			                <div class="hfinfo" id="div_reback">
			                	<c:choose>
			                	<c:when test="${ent.reback == ''}">
			                		<div style="margin: 1px 0px 10px 0px"><textarea style="width: 100%;height: 50px;resize:none;" id="reback_4_appraise"></textarea></div>
                					<div class="kcdp_hf_btn"><input value="" class="btn" type="submit" onclick="click2reback(${ent.id});"/></div>
			                	</c:when>
			                	<c:otherwise>
			                		${ent.reback}
			                	</c:otherwise>
			                	</c:choose>
			                </div>
			            </div>
		        	</div>
		        </div>
        </c:forEach>
    </div>       
    
    </div>
    <i class="clear"></i>
    </div>
    <script type="text/javascript">
    	function click2reback(tmpid){
    		var reback = $("#reback_4_appraise").val();
    		var url = '<%=basePath%>'+"center/reback4Appraise";
    		var data = {"id":tmpid,"reback":reback};
    		var callBack = function(back){
    			alert(back.msg);
    			if(back.status == 1){
    				$("#div_reback").html(reback);
    			}
    		}
    		$.post(url,data,callBack,"json");
    	}
    </script>
</body>
</html>