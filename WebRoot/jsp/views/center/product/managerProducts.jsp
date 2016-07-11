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
<title>学习中心管理后台_题库管理</title>
<jsp:include page="../../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg1">
	<!--头部-->
	<jsp:include page="../../../common/center/header.jsp" />
    
    <!--内容-->
    
    <div class="j_content">
    
     <!--菜单-->
    <jsp:include page="../../../common/center/meun.jsp" />

    <div class="user_content">
    <div class="user_list">
    	<jsp:include page="cursor.jsp" />
    	<div class="kcszbtn u_tkgl">
        	<div class="u_tkgl_content">
        	<form action="center/addProduct" id="addProduct" method="post">
            	<jsp:include page="../../stepCourses.jsp" />
                <input class="btn" type="button" onclick="operating('addProduct');" value="增加" /> 
             </form>
            </div>
            <!-- 
            <SPAN class="fr">
        		<span class="">没有您想要添加的课程类别？请提出您的需求！</span>
        		<input class="btn" type="button"  value="提交需求">
            </SPAN>
             -->
            <i class="clear"></i>
        </div>
        
        <div class="div_table f_st">
        	<table class="table" border="0" cellpadding="0" cellspacing="0">
        		<thead>
                <tr>
                <form action="center/go2ManagerProducts" method="post">
					<jsp:include page="../../admin/thCourses.jsp" />
                    <th width="60">题库提成</th>
                    <th width="60">质量押金</th>
                    <th width="60">错误率</th>
                    <th width="130">题库状态</th>
                    <th width="190">
						<input type="submit" value="进行筛选" />
					</th>
					</form>
                </tr>
        		</thead>
                <tbody>
                    <c:forEach items="${pageEnt.listPages}" var = "ent" varStatus="">
	                    <tr id="tr_date_${ent.id}">
	                        <td>${ent.coures.adqdepartmentFkDepartid.name} </td>
	                    	<td>${ent.coures.nmMajor}</td>
	                    	<td>${ent.coures.nmLevel}</td>
	                    	<td>${ent.coures.nmSub}</td>
	                    	<td>${ent.coures.nmArea}</td>
	                        <td>${ent.coures.profitOwner}%</td>
	                    	<td>${ent.coures.deposit}%</td>
	                    	<td>${ent.coures.wrong}%</td>
		                   	<td>
		                   	<input type="radio" name="complete_${ent.id}" <c:if test="${ent.complete == 1 }">checked="checked"</c:if>  onclick="completeproduct(${ent.id},1);">完成
		                   	&nbsp;&nbsp;
		                   	<input type="radio" name="complete_${ent.id}" <c:if test="${ent.complete == 0 }">checked="checked"</c:if>    onclick="completeproduct(${ent.id},0);">未完成 </td>
		                   	<td style="text-align: center;">
		                   		<div style="float: left;width: 45%;">
		                   		<c:choose>
			                   	<c:when test="${ent.examineStatus == 0 ||  ent.examineStatus == 2}">
				                   	<a href="javascript:void(0);" style="width: 96%;display: block;color:#f65109;" onclick="delProduct(${ent.id});" class="check">删除题库</a>
				                </c:when>
				                <c:otherwise>
				                &nbsp;&nbsp;
				                </c:otherwise>
				                </c:choose>
				                </div>
				                <div style="float: left;width: 45%;">
		                   		<c:if test="${ent.examineStatus != 1 }">
				                   	<a href="center/go2ProExamType?productid=${ent.id }" style="width: 96%;display: block;" class="check">编辑题库</a>
				                   	<a href="center/makeKind?productid=${ent.id}&kindclassId=1" style="width: 96%;display: block;" class="check">设置套餐</a>
			                   	</c:if>
			                   	<!--  审核状态 0 初始化 1审核中 2 审核不通过 3 审核通过-->
			                   	<c:choose>
			                   	<c:when test="${ent.examineStatus ==0 }">
			                   		<a href="javascript:void(0);" style="width: 96%;display: block;" class="check" onclick="examineStatusProduct(${ent.id});">提交审核</a>
			                   	</c:when>
			                   	<c:when test="${ent.examineStatus == 1 }">
			                   		<span style="width: 96%;display: block;" class="check">审核中</span>
			                   	</c:when>
			                   	<c:when test="${ent.examineStatus == 2 }">
			                   		<a href="javascript:void(0);" style="width: 96%;display: block;" title="${ent.examineDes}" class="check" onclick="examineStatusProduct(${ent.id});" > 审核不通过再次提交审核 </a>
			                   	</c:when>
			                   	<c:when test="${ent.examineStatus == 3 }">
			                   		<span style="width: 96%;display: block;" title="${ent.examineDes}"  class="check">审核通过</span>
			                   	</c:when>
			                   	</c:choose>
			                   	</div>
		                    </td>
	                   </tr>
					</c:forEach>
                  
                
                </tbody>
        	</table>
    	</div>
    	<div style="padding-top: 10px;">
        	<p:pageTag name="pageEnt" action="center/go2ManagerProducts"/>
        </div>
    </div>
   </div>
   </div>
 
    <!--e内容-->
	<script type="text/javascript">
	function goPaging(form,page){
		$("#"+form+" :input[name='page']").val(page);
		$("#"+form).submit();
	}
	
	function operating(formId){
	   if (jQuery('#' + formId).form('validate')) {
			jQuery.messager.progress({
					title:'请等待',
					text:'提交数据中...',
					interval:700
			});
			jQuery.post($("#"+formId).attr('action'), $("#"+formId).serialize(), function(data) {
					jQuery.messager.progress('close');
					alert(data.msg);
					if(data.status==1){
						  window.location.reload();
					}
			}, "json");
		}
	}
	
	function delProduct(id){
		if(confirm("删除是不可恢复的，你确认要删除吗？")){
			jQuery.messager.progress({
				title:'请等待',
				text:'提交数据中...',
				interval:700
			});
			jQuery.post('<%=basePath%>'+"center/delProduct", {"productid":id}, function(data) {
					jQuery.messager.progress('close');
					alert(data.msg);
					location.replace(location);
			}, "json");
		}
	}
	
	function examineStatusProduct(id){
		jQuery.messager.progress({
			title:'请等待',
			text:'提交数据中...',
			interval:700
		});
		jQuery.post('<%=basePath%>'+"center/examineStatusProduct", {"productid":id}, function(data) {
				jQuery.messager.progress('close');
				alert(data.msg);
				location.reload();
		}, "json");
	}
	
	function completeproduct(id,complete){
		jQuery.messager.progress({
			title:'请等待',
			text:'提交数据中...',
			interval:700
		});
		jQuery.post('<%=basePath%>'+"center/completeproduct", {"productid":id,"complete":complete}, function(data) {
				jQuery.messager.progress('close');
				alert(data.msg);
				location.replace(location);
		}, "json");
	}
</script>
</body>
</html>