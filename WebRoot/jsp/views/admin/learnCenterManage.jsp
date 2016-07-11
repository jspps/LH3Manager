<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.bowlong.security.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://page.bowlong.com/jsp/tags" prefix="p"%>
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
<meta http-equiv="description" content="学习中心管理">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg" menuName="学习中心管理">
	<!--头部-->
    <div class="header">
	   <jsp:include page="../../common/admin/header.jsp"></jsp:include>
	   <!-- 菜单 -->
	   <jsp:include page="../../common/admin/meun.jsp"></jsp:include>
       <div class="header_child">
         <div class="list">
           	<a href="admin/learnCenterManage" class="current">学习中心管理（${pageEnt.totalRecords}）</a>
           	<a href="admin/errorBack">错误反馈</a>
          </div>
       </div>
    </div>
    
    <!--内容-->
    <div class="w_content kcsz">
    	<div class="kcszbtn">
        	
        	<div class="fl reseach">
        	<form action="admin/learnCenterManage" method="post">
	        	<input type="text" placeholder="请输入学习中心" value="${name}" name="name" class="r_txt" />
	        	<input value="" class="r_btn" type="submit"/> 
			</form>
	        </div>
        
        	<input class="btn btn_2" onclick="OutXls('learnCenterManageOutXls');" type="button" value="导出数据"/>
        </div>
        <div class="div_table f_st">
        	<table class="table" border="0" cellpadding="0" cellspacing="0">
        		<thead>
                <tr>
        			<th width="80">入住时间</th>
                    <th width="70">学习中心</th>
                    <th width="70">联系人</th>
                    <th width="50">类型</th>
                    <th width="80">QQ邮箱</th>
                    <th width="120">所在地</th>
                    <th width="80">登录用户名</th>
                    <th width="50">成交量</th>
                    <th width="60">成交金额</th>
                    <th width="60">题库提成</th>
                    <th width="60">质量提成</th>
                    <th width="60">错误率</th>
                    <th width="85">自主管理权限</th>
                    <th width="90">学习中心状态</th>
                    <th width="80">操作</th>
                </tr>
        		</thead>
                <tbody>
                	
                	<c:forEach items="${pageEnt.listPages}" var = "learnhub" varStatus="">
                	<tr>
						<td><p:fmtDate parttern="yyyy-MM-dd" value="${learnhub.createtime}" /></td>
						<td onclick="skip2Lhub(${learnhub.accountid})">${learnhub.name}</td>
						<td>${learnhub.uname}</td>
				 		<td>
					 		 <c:if test="${learnhub.type=='1'}">
					 		 个人
					 		 </c:if>
					 		 <c:if test="${learnhub.type=='2'}">
					 		   机构
					 		 </c:if>
				 	    </td>
				 		<td>${learnhub.qq}</td>
				 		<td>${learnhub.seat}</td> 
				 		<td>${learnhub.accountFkAccountid.phone}</td>
				 		<td>${learnhub.volume}</td>
				 		<td>${learnhub.moneyAll}</td>
				 		<td></td>
				 		<td></td>
				 		<td></td>
				 		<td>
				 			<input type="radio" name="isselfadmin_${learnhub.lhid}" onclick="selfadmin('${learnhub.lhid}','false');"  <c:if test="${learnhub.isselfadmin=='false'}"> checked="true"<s </c:if>>关
				 			 &nbsp;
				 			<input type="radio"  name="isselfadmin_${learnhub.lhid}" onclick="selfadmin('${learnhub.lhid}','true');" <c:if test="${learnhub.isselfadmin=='true'}"> checked="true" </c:if>>开 
				 		</td>
				 		<td>
				 		<c:choose>
				 		<c:when test="${learnhub.examineStatus == 2}">不通过</c:when>
				 		<c:when test="${learnhub.examineStatus == 3}">已通过</c:when>
				 		<c:otherwise>审核中</c:otherwise>
				 		</c:choose>
				 		</td>
		 		   	 	<td>
		 		   	 		<a href="javascript:void(0);" class="delete" onclick="deleteLearnhub('${learnhub.lhid}')">删除</a>
                  			<a href="javascript:void(0);" class="check" onclick="checkpid('${learnhub.lhid}')">审核</a>
                    	</td>
                    </tr>
					</c:forEach>

                <!--  </tbody>
                    <tfoot>
                    <tr>
                    	<th>合计</th>
                    	<td></td>
                    	<td></td>
                    	<td><strong>233</strong></td>
                    	<td></td>
                    	<td></td>
                    	<td></td>
                    	<td><strong>233</strong></td>
                    	<td><strong>233</strong></td>
                    	<td></td>
                    	<td></td>
                    	<td></td>
                    	<td></td>
                    	<td></td>
                    	<td></td>
                    </tr>
                    </tfoot>  -->
        	</table>
    	</div>
    <!--弹出层-->
    <div id="tab_zz_check" class="tab_zz hide"></div>
    <div id="tab_check" class="tab hide" id="hfyj">
    	<div class="tab_info">
        	<div class="hfyj">
        		<h2><a href="javascript:void(0)" onclick="closecheck();" class="t_close"></a></h2>
        		<p>回复意见：</p>
                <div class="text_div">
                <input id="checkpid"  type="hidden"/>
                <textarea id="neirong"></textarea>
                </div>
                <div class="btn_div">
                <input type="button" onclick="examineStatus(2);"  class="tab_btn1"/>
                <input type="button" onclick="examineStatus(3);" class="tab_btn2"/></div>
        	</div>
        </div>
    </div>
    
    <p:pageTag name="pageEnt" action="admin/learnCenterManage?name=${name}"/>
    </div>
    <!--e内容-->
	<script type="text/javascript">
	function goPaging(form,page){
		$("#"+form+" :input[name='page']").val(page);
		$("#"+form).submit();
	}
	
	//删除
	function deleteLearnhub(id){
		if(confirm("删除是不可恢复的，你确认要删除吗？")){
			jQuery.messager.progress({
				title:'请等待',
				text:'提交数据中...',
				interval:700
			});
			jQuery.post("admin/deleteLearnhub", {lhid:id}, function(data) {
				jQuery.messager.progress('close');
				alert(data.msg);
				if(data.status==1){
					  location.replace(location);
				}
			}, "json");
		}
	}
	
	function closecheck(){
		$("#tab_zz_check").hide();
		$("#tab_check").hide();
	}
	
	function checkpid(pid){
		$("#tab_zz_check").show();
		$("#tab_check").show();
		$("#checkpid").val(pid);
		
	}
	
	// 0不通过 1 通过
	function examineStatus(es){
		var examineDes  = $("#neirong").val();
		var pid = $("#checkpid").val();
		if(neirong==null || neirong==""){
			alert("回复意见不能为空！");
		}else{
			jQuery.messager.progress({
				title:'请等待',
				text:'提交数据中...',
				interval:700
			});
			jQuery.post("admin/updateLearnhub", {examineDes:examineDes,lhid:pid,examineStatus:es}, function(data) {
				jQuery.messager.progress('close');
				alert(data.msg);
				if(data.status==1){
					  location.replace(location);
				}
		    }, "json");
		}
	}
	
	//自主管理权限
	function selfadmin(id,isselfadmin){
			jQuery.post("admin/updateLearnhub", {lhid:id,isselfadmin:isselfadmin}, function(data) {
				alert(data.msg);
				if(data.status==-1){
					  location.replace(location);
				}
			}, "json");
	}
	
	// 去学习中心
	function skip2Lhub(id){
		window.open('<%=basePath%>'+"admin/skip2Lhub?id="+id);
	}
	
	$(function(){
		$(".kcdp_close").live("click",function(){
			$(this).parent().hide();
		});
		$(".r_txt").Ipt_defult_word({"word":"输入姓名、代理号或手机号码查找"});
	});
	</script>
</body>
</html>