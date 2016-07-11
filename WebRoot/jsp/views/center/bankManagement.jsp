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
<title>学习中心管理后台_题库管理_新增题库</title>
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
   
    	<div class="uc_content ">
         <h2 class="h2 h2_2"><span>行政管理学试题设置</span></h2>
             <div class="txsz_info f_st txsz_bg_2">
                <div class="txsz_bg txsz_bg_1">
                	<span class="span1">试题类别：</span>
                    <span class="span2"><input type="radio"> 知识要点</span>
                    <span class="span2"><input type="radio"> 章节练习</span>
                    <span class="span2"><input type="radio"> 历年真题</span>
                    <span class="span2"><input type="radio"> 全真模拟</span>
                    <span class="span2"><input type="radio"> 决胜押题</span>
                    
                    <div class="upload" style="margin-top:30px; margin-bottom:20px;">
					<span class="span1">上传知识要点（推荐word文档）：</span>
                    <input type="file"/><input value="上传" type="button" class="btn1"/>
                	</div>
                    
                    <div class="upload" style="border-top:1px solid #CCC; height:auto;padding:10px 0px 10px 0px;">
						<span class="span1" style="float:left;">上传知识要点上传知识要点上传知识要点.doc</span>
                        <span style="float:right;"><a href="javascript:void(0)">删除</a><a href="javascript:void(0)">修改</a></span>
                	</div>
                    <div class="upload" style="border-top:1px solid #CCC; height:auto;padding:10px 0px 10px 0px;">
						<span class="span1" style="float:left;">上传知识要点上传知识要点上传知识要点.doc</span>
                        <span style="float:right;"><a href="javascript:void(0)">删除</a><a href="javascript:void(0)">修改</a></span>
                	</div>
                    <div class="upload" style="border-top:1px solid #CCC; height:auto;padding:10px 0px 10px 0px;">
						<span class="span1" style="float:left;">上传知识要点上传知识要点上传知识要点.doc</span>
                        <span style="float:right;"><a href="javascript:void(0)">删除</a><a href="javascript:void(0)">修改</a></span>
                	</div>
                    <div class="upload" style="border-top:1px solid #CCC; height:auto;padding:10px 0px 10px 0px;">
						<span class="span1" style="float:left;">上传知识要点上传知识要点上传知识要点.doc</span>
                        <span style="float:right;"><a href="javascript:void(0)">删除</a><a href="javascript:void(0)">修改</a></span>
                	</div>
                    <div class="upload" style="border-top:1px solid #CCC; height:auto;padding:10px 0px 10px 0px;">
						<span class="span1" style="float:left;">上传知识要点上传知识要点上传知识要点.doc</span>
                        <span style="float:right;"><a href="javascript:void(0)">删除</a><a href="javascript:void(0)">修改</a></span>
                	</div>
                    <div class="upload" style="border-top:1px solid #CCC; height:auto;padding:10px 0px 10px 0px;">
						<span class="span1" style="float:left;">上传知识要点上传知识要点上传知识要点.doc</span>
                        <span style="float:right;"><a href="javascript:void(0)">删除</a><a href="javascript:void(0)">修改</a></span>
                	</div>
                    <div class="upload" style="border-top:1px solid #CCC; height:auto;padding:10px 0px 10px 0px;">
						<span class="span1" style="float:left;">上传知识要点上传知识要点上传知识要点.doc</span>
                        <span style="float:right;"><a href="javascript:void(0)">删除</a><a href="javascript:void(0)">修改</a></span>
                	</div>
                    <div class="upload" style="border-top:1px solid #CCC; height:auto;padding:10px 0px 10px 0px;">
						<span class="span1" style="float:left;">上传知识要点上传知识要点上传知识要点.doc</span>
                        <span style="float:right;"><a href="javascript:void(0)">删除</a><a href="javascript:void(0)">修改</a></span>
                	</div>
                    
                    
                      
                    
                </div>
                
                
            	
            
            
            </div>

            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
         
         
         
         </div>
    </div>
    <i class="clear"></i>
    </div>
    
 
 
</body>
</html>