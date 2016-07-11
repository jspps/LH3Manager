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
<title>学习中心管理后台_试题预览</title>
<jsp:include page="../../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg">
	<!--头部-->
		<jsp:include page="../../../common/center/header.jsp"></jsp:include>

    <div class="uc_content txt_cen">
    	<h2 class="h2 h2_1"><span class="span">${exam.name}</span></h2>
    </div>
    
    
    <!--内容-->
   <div class="w_content" style="min-width: 1260px;"> 
    <jsp:include page="includeEntry.jsp" />
    
    <div class="user_content lr_content ">
    
    	<jsp:include page="../product/cursor.jsp" />
        
    	<div class="sjsm">
    		<div>
				试卷说明：${exam.descstr}
		    </div>
		    <br>
		    <br>
			<div>
			   <span style="float:left;margin-top: -30px;margin-left: 27px;"> 
			   		 考试时间：${exam.examtime}分钟
			   </span>
			   <span style="float:right;margin-top: -30px;margin-right: 327px;"> 
			  		总分：${exam.score}分
			   </span>
			   <i class="clear"></i>
		    </div>
        </div>
     	
     	<c:set var="num" value="0"></c:set>
		<c:forEach items="${examcatalogs}" var="ent">
			<div class="big_answer f_st">
			<c:choose>
       		<c:when test="${ent.catalogType == 7 && ent.gid > 0}">
       		<div>
       			${ent.title }
       		</div>
       		</c:when>
       		<c:otherwise>
       		<div>${ent.serial}、${ent.title } </div>
       		</c:otherwise>
       		</c:choose>
       		
       		<i class="clear"></i>
       		<div>
	       	<c:forEach items="${ent.listChild}" var = "child">
	            <jsp:include page="preview.jsp">
	            	<jsp:param value="${num}" name="num"/>
	            	<jsp:param value="${child.type}" name="type"/>
	            	<jsp:param value="${child.gid}" name="gid"/>
	            	<jsp:param value="${child.imgPic}" name="imgPic"/>
	            	<jsp:param value="${child.voiceurl}" name="voiceurl"/>
	            	<jsp:param value="${child.videourl}" name="videourl"/>
	            	<jsp:param value="${child.content}" name="content"/>
	            	<jsp:param value="${child.right_2}" name="right_2"/>
	            	<jsp:param value="${child.position}" name="position"/>
	            	<jsp:param value="${child.analyse}" name="analyse"/>
	            </jsp:include>
	            <c:set var="num" value="${num + 1}"></c:set>
	       	</c:forEach>
	       	</div>
	       	</div>
		</c:forEach>
        <div class="lrst_btn">
        	<form action="center/makeKind" method="post">
        	<input type="hidden" value="${exam.productid}" name="productid" />
        	<input type="hidden" value="1" name="kindclassId" />
        	<input type="submit" class="lrbtn_2"  value="确认无误设置套餐" />
        	</form>
        </div>
    	<i class="clear"></i>
    </div>
  </div>
</body>
</html>