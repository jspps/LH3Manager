<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>尚学在线后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="尚学后台管理">
<meta http-equiv="description" content="消息管理">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg" menuName="消息管理">
	<!--头部-->
    <div class="header">
	   <jsp:include page="../../common/admin/header.jsp"></jsp:include>
	   <!-- 菜单 -->
	   <jsp:include page="../../common/admin/meun.jsp"></jsp:include>
    </div>
    
    <!--内容-->
    <div class="w_content kcsz">
    	  <div class="phgz phgz_1 f_st">
        	<h2>编辑最新消息</h2>
        	<div class="phgz_txt">
            	<textarea id="description"></textarea>
            </div>
            
            <div class="kcszbtn">
            	<div class="xsgl">选择发生对象：
            	<input type="checkbox"  value="1" id="checkbox_obj_1" /> 学生&nbsp;&nbsp;&nbsp;&nbsp;
            	<input type="checkbox"  value="2" id="checkbox_obj_2" /> 代理商&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="checkbox"  value="3" id="checkbox_obj_3"/> 学习中心</div>
            <input class="btn" type="button" onclick="addmodmsg('0');" value="发送"></div>
        
        </div>
        
    
    	<div class="xxjl_tit">已发送消息记录</div>
      
        <div class="div_table f_st">
        	<table class="table" border="0" cellpadding="0" cellspacing="0">
        		<thead>
                <tr>
        			<th width="200">发送时间</th>
        			<th width="300">接收对象</th>
                    <th width="">消息内容</th>
                    <th width="200">浏览量</th>
                    <th width="250">操作</th>
                </tr>
        		</thead>
                <tbody>
                	<c:forEach items="${pageEnt.listPages}" var = "ent" varStatus="">
	        	        <tr id="tr_date_${ent.id}">
	                    	<td>${ent.createtime}</td>
							<c:if test="${ent.target =='1'}">
								<td>学生</td>
							</c:if>
							<c:if test="${ent.target =='2'}">
								<td>代理商</td>
							</c:if>
							<c:if test="${ent.target =='3'}">
								<td>学习中心</td>
							</c:if>
							<c:if test="${ent.target =='12'}">
								<td>学生、代理商</td>
							</c:if>
							<c:if test="${ent.target =='13'}">
								<td>学生、学习中心</td>
							</c:if>
							<c:if test="${ent.target =='23'}">
								<td>代理商、学习中心</td>
							</c:if>
							<c:if test="${ent.target =='123'}">
								<td>学生、代理商、学习中心</td>
							</c:if>
	                    	<td>${ent.description}</td>
	                    	<td>${ent.num}</td>
	                        <td><a href="javascript:void(0);" onclick="delmsg('${ent.id}');" class="delete">删除</a><a href="javascript:void(0);"onclick="updateDate('${ent.id}');" class="check">修改</a></td>
	                    </tr>
	                    
	                      <tr id="tr_from_${ent.id}" style="display: none">
	                    	<td>${ent.createtime}</td>
							<c:if test="${ent.target =='1'}">
								<td>学生</td>
							</c:if>
							<c:if test="${ent.target =='2'}">
								<td>代理商</td>
							</c:if>
							<c:if test="${ent.target =='3'}">
								<td>学习中心</td>
							</c:if>
							<c:if test="${ent.target =='12'}">
								<td>学生、代理商</td>
							</c:if>
							<c:if test="${ent.target =='13'}">
								<td>学生、学习中心</td>
							</c:if>
							<c:if test="${ent.target =='23'}">
								<td>代理商、学习中心</td>
							</c:if>
							<c:if test="${ent.target =='123'}">
								<td>学生、代理商、学习中心</td>
							</c:if>
	                    	<td><textarea id="description_${ent.id}">${ent.description}</textarea></td>
	                    	<td>${ent.num}</td>
	                        <td><a href="javascript:void(0);" onclick="hideDate('${ent.id}');" class="delete">取消</a>
	                        <a  href="javascript:void(0);"  onclick="modDate('${ent.id}');"  class="check">修改</a></td>
	                    </tr>
	                    
					</c:forEach>
                    
                  </tbody>
        	</table>
    	</div>
    	<div style="clear: both; height: 10px;"></div>
        <p:pageTag action="admin/newsManage" name="pageEnt"/>
   </div>
   
   <div style="clear: both; height: 10px;"></div>
   
   <!--弹出层-->
    <div class="tab_zz hide"></div>
    <div class="tab hide" id="hfyj">
    	<div class="tab_info">
        	<div class="hfyj">
        		<h2><a href="javascript:void(0)" class="t_close"></a></h2>
        		<p>回复意见：</p>
                <div class="text_div"><textarea></textarea></div>
                <div class="btn_div"><input type="button" value="" class="tab_btn1"/><input type="button" value="" class="tab_btn2"/></div>
        	</div>
        </div>
    
    
    </div>
    
<script type="text/javascript">
var booldel =true;
var boolmod =true;
var booladd =true;
function updateDate(id){
	$("#tr_date_"+id).hide();
	$("#tr_from_"+id).show();
}
function hideDate(id){
	$("#tr_date_"+id).show();
	$("#tr_from_"+id).hide();
}
function modDate(id){
	if(boolmod){
		boolmod = false;
		jQuery.post("admin/addmodmsg", 
				{"description":$("#description_"+id).val(),"msgid":id}, 
				function(data) {
					booladd = true;
				alert(data.msg);
				if(data.status==1){
					  location.replace(location);
				}
		}, "json");
	}
}

function addmodmsg(id){
		var target ="";
		 if($("#checkbox_obj_1").attr("checked")=="checked"){
	    	 target+="1";
	     }
		 if($("#checkbox_obj_2").attr("checked")=="checked"){
	    	 target+="2";
	     }
		 if($("#checkbox_obj_3").attr("checked")=="checked"){
	    	 target+="3";
	     }
		if(target==""){
			alert("请选择发送对象");
		}else if($("#description").val()==""){
			alert("请填写发送内容");
		}else{
			if(booladd){
				booladd = false;
				jQuery.post("admin/addmodmsg", 
						{"description":$("#description").val(),"target":target,"msgid":id}, 
						function(data) {
							booladd = true;
						alert(data.msg);
						if(data.status==1){
							  location.replace(location);
						}
				}, "json");
			}
			
		}
} 
	
function delmsg(id){
		if(booldel){
			booldel =false;
			jQuery.post("admin/delmsg", {"msgid":id}, function(data) {
				booldel =true;
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