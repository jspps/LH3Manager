<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://page.bowlong.com/jsp/tags" prefix="p"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<base href="<%=basePath%>">
<title>尚学在线后台管理系统-题库管理-课程设置</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="尚学后台管理">
<meta http-equiv="description" content="题库管理--课程设置">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg" menuName="题库管理">
	<!--头部-->
    <div class="header">
	   <jsp:include page="../../common/admin/header.jsp"></jsp:include>
	   <!-- 菜单 -->
	   <jsp:include page="../../common/admin/meun.jsp"></jsp:include>
            <div class="header_child">
            <div class="h_content">
              <div class="list">
                	<a href="admin/questionManage_set" class="current">课程设置</a>
                	<a href="admin/questionManage_comment">课程点评</a>
                	<a href="admin/questionManage_list">题库列表</a>
               </div>
            </div>
            </div>
    </div>
    
    <!--内容-->
    <div class="w_content kcsz">
    	<div class="kcszbtn"><input class="btn"  onclick="OutXls('questionManage_setOutXls');"  type="button" value="导出数据"/></div>
        <div class="div_table f_st">
        	<table class="table" border="0" cellpadding="0" cellspacing="0">
        		<thead>
                <tr>
                <form action="admin/questionManage_set" method="post">
        			<jsp:include page="thCourses.jsp" />
                    <th>代理佣金</th>
                    <th>题库提成</th>
                    <th>质量押金</th>
                    <th>错误率</th>
                    <th>代理奖金</th>
                    <th>程序员</th>
                    <th>美工</th>
                    <th><input type="submit" value="进行查询" /></th>
                </form>
                </tr>
        		</thead>
                <tbody>
                	<tr id="tr_from_add">
                	<form action="admin/addquestionManage" id="addquestionManage"> 
                    	<td>
	                    	<select name="adqdepartmentId" >
	                    		<c:forEach items="${adqdepartments}" var = "adqdepartment" varStatus="adqdepartmentst">
	                    		<option value="${adqdepartment.did}">${adqdepartment.name}</option>
	                    		</c:forEach>
	                    	</select>
                    	</td>
                    	<td><input name="adqmajor" type="text" nullmsg="专业不能为空"   class="t_txt" /></td>
                    	<td><input name="adqlevel" type="text"  nullmsg="层次不能为空" class="t_txt" /></td>
                    	<td><input name="adqsubject" type="text" nullmsg="科目不能为空"  class="t_txt" /></td>
                    	<td><input name="adqarea" type="text" nullmsg="考试范围不能为空"  class="t_txt" /></td>
                    	<td><input  name="profitAgent" type="text" nullmsg="代理佣金不能为空" zz="比例"  class="t_txt" /></td>
                    	<td><input  name="profitOwner" type="text"  nullmsg="题库提成不能为空"  zz="比例"  class="t_txt" /></td>
                    	<td><input  name="deposit" type="text" nullmsg="质量押金不能为空" zz="比例"   class="t_txt" /></td>
                    	<td><input  name="wrong" type="text"    class="t_txt" /></td>
                    	<td><input  name="bonus" type="text"  nullmsg="代理奖金不能为空" zz="比例" class="t_txt" /></td>
                    	<td><input name="program"  type="text"  nullmsg="程序员不能为空" zz="价格" class="t_txt" /></td>
                    	<td><input  name="art" type="text"  nullmsg="美工不能为空" zz="价格" class="t_txt" /></td>
                    	<td><input type="button" class="b_add" onclick="operating('addquestionManage','add')" value="确定新增"/></td>
                    </form>
                    </tr>
                 	<c:forEach items="${pageEnt.listPages}" var = "ent" varStatus="">
                 	  <tr id="tr_date_${ent.cid}">
	                    	<td>${ent.adqdepartmentFkDepartid.name} </td>
	                    	<td>${ent.nmMajor}</td>
	                    	<td>${ent.nmLevel}</td>
	                    	<td>${ent.nmSub}</td>
	                    	<td>${ent.nmArea}</td>
	                    	<td>${ent.profitAgent}%</td>
	                    	<td>${ent.profitOwner}%</td>
	                    	<td>${ent.deposit}%</td>
	                    	<td>${ent.wrong}%</td>
	                    	<td>${ent.bonus}%</td>
	                    	<td>${ent.program}元/套餐</td>
	                    	<td>${ent.art}元/套餐</td>
							<td>
								<a href="javascript:void(0);" onclick="deleteDate(${ent.cid});" class="delete">删除</a>
								<a href="javascript:void(0);" onclick="updateDate(${ent.cid});"  class="update">修改</a>
							</td>       
					</tr>
                    
                	<tr id="tr_from_${ent.cid}" style="display: none">
	                	<form action="admin/modquestionManage" id="modquestionManage_${ent.cid}"> 
	                    	<td>
		                    	<select name="adqdepartmentId" id="adqdepartmentId_${ent.cid}">
		                    		<c:forEach items="${adqdepartments}" var = "adqdepartment" varStatus="adqdepartmentst">
		                    			<option value="${adqdepartment.did}">${adqdepartment.name}</option>
		                    		</c:forEach>
		                    	</select>
		                    	<script>
		                    	jQuery("#adqdepartmentId_${ent.cid}").val('${ent.adqdepartmentFkDepartid.did}')
								</script>
								<input name="cid" value="${ent.cid}" type="hidden"  />
	                    	</td>
	                    	<td><input nullmsg="专业不能为空"  name="adqmajor" value="${ent.nmMajor}" type="text"    class="t_txt" /></td>
	                    	<td><input nullmsg="层次不能为空" name="adqlevel" value="${ent.nmLevel}" type="text"    class="t_txt" /></td>
	                    	<td><input nullmsg="科目不能为空" name="adqsubject" value="${ent.nmSub}" type="text"    class="t_txt" /></td>
	                    	<td><input nullmsg="考试范围不能为空" name="adqarea"  value="${ent.nmArea}"  type="text"    class="t_txt" /></td>
	                    	<td><input nullmsg="代理佣金不能为空" zz="比例" name="profitAgent" value="${ent.profitAgent}" type="text"  class="t_txt" /></td>
	                    	<td><input  nullmsg="题库提成不能为空" zz="比例" name="profitOwner" value="${ent.profitOwner}"  type="text"  class="t_txt" /></td>
	                    	<td><input nullmsg="质量押金不能为空" zz="比例" name="deposit"  value="${ent.deposit}" type="text"  class="t_txt" /></td>
	                    	<td><input  name="wrong"  value="${ent.wrong}" type="text"  class="t_txt" /></td>
	                    	<td><input  nullmsg="代理奖金不能为空" zz="比例" name="bonus"  value="${ent.bonus}" type="text"  class="t_txt" /></td>
	                    	<td><input  nullmsg="程序员不能为空" zz="价格" name="program"   value="${ent.program}" type="text"  class="t_txt" /></td>
	                    	<td><input nullmsg="美工不能为空" zz="价格"  name="art" value="${ent.art}"  type="text"  class="t_txt" /></td>
	                    	<td><a href="javascript:void(0);" onclick="hideDate(${ent.cid});" class="update">取消</a>
								<a href="javascript:void(0);" onclick="operating('modquestionManage_${ent.cid}','${ent.cid}');"  class="delete">修改</a>
							</td>
	                    </form>
                    </tr>
					</c:forEach>
                </tbody>
        	</table>
    	</div>
        
        <p:pageTag name="pageEnt" action="admin/questionManage_set"/>
    </div>
 
    <!--e内容-->
	<script type="text/javascript">
		function goPaging(form,page){
			$("#"+form+" :input[name='page']").val(page);
			$("#"+form).submit();
		}
		function updateDate(id){
			$("#tr_date_"+id).hide();
			$("#tr_from_"+id).show();
		}
		function hideDate(id){
			$("#tr_date_"+id).show();
			$("#tr_from_"+id).hide();
		}
		function deleteDate(id){
			if(confirm("删除是不可恢复的，你确认要删除吗？")){
				jQuery.messager.progress({
					title:'请等待',
					text:'提交数据中...',
					interval:700
				});
				jQuery.post("admin/delquestionManage", {"cid":id}, function(data) {
					jQuery.messager.progress('close');
					alert(data.msg);
					if(data.status==1){
						  location.replace(location);
					}
				}, "json");
			}
		}
		function operating(formId,id){
			if (jiaoyan('#tr_from_'+id+' :input')) {
				jQuery.messager.progress({
						title:'请等待',
						text:'提交数据中...',
						interval:700
				});
				jQuery.post($("#"+formId).attr('action'), $("#"+formId).serialize(), function(data) {
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