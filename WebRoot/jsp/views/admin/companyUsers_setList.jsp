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
<title>尚学在线后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="尚学后台管理">
<meta http-equiv="description" content="集团账户">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg" menuName="集团帐户">
	<!--头部-->
    <div class="header">
	   <jsp:include page="../../common/admin/header.jsp"></jsp:include>
	   <!-- 菜单 -->
	   <jsp:include page="../../common/admin/meun.jsp"></jsp:include>
       <div class="header_child">
	       <div class="h_content">
	         <div class="list">
	           	<a href="admin/companyUsers" >体验账户</a>
	           	<a href="admin/companyUsers_setList" class="current">批量设置</a>
	          </div>
	       </div>
       </div>
    </div>
    
    <!--内容-->
    <div class="w_content kcsz">
    	<div class="kcszbtn">
       <form action="admin/companyUsers_setList" id="learnCenterManage" method="post">
    		<input name="inp_fm_page" type="hidden" value="${pageEnt.page}">
        	<div class="fl reseach">
	        	<input type="text" class="r_txt" placeholder="请输入联系电话" value="${phone}" name="phone" />
	        	<input value="" class="r_btn" type="submit"/>
        	</div>
        </form>
        <input class="btn" onclick="OutXls('companyUsers_setListOutXls');" type="button" value="导出数据"/>
        </div>
        
        <div class="div_table f_st">
        	<table class="table" border="0" cellpadding="0" cellspacing="0">
                <thead class="thead">
                    <tr>
                        <th>学习中心</th>
                        <th>课程类别</th>
                        <th>专业名称</th>
                        <th>层次</th>
                        <th>科目</th>
                        <th>考试范围</th>
                        <th>套餐</th>
                        <th>账户数</th>
                        <th>入账金额</th>
                        <th>所属集团客户</th>
                        <th>联系人</th>
                        <th>联系电话</th>
                        <th>时间</th>
                        <th width="100">操作</th>
                    </tr>
                
        		</thead>
                <tbody>
               <form action="admin/modifyOpenKind4Third" id="form_add">
               <input id="kindid_add" name="kindid"  nullmsg="请选择套餐" type="hidden"/>
               <tr id="tr_from_add">
                   <td> <a href="javascript:void(0);" onclick="openWindow('add');" id="uu_val1_add">请选择</a></td>
                   <td> <a href="javascript:void(0);" onclick="openWindow('add');" id="uu_val2_add">请选择</a></td>
                   <td> <a href="javascript:void(0);" onclick="openWindow('add');" id="uu_val3_add">请选择</a></td>
                   <td> <a href="javascript:void(0);" onclick="openWindow('add');" id="uu_val4_add">请选择</a></td>
                   <td> <a href="javascript:void(0);" onclick="openWindow('add');" id="uu_val5_add">请选择</a></td>
                   <td> <a href="javascript:void(0);" onclick="openWindow('add');" id="uu_val6_add">请选择</a></td>
                   <td> <a href="javascript:void(0);" onclick="openWindow('add');" id="uu_val7_add">请选择</a></td>
                   <td><input class="kc_text_2" type="text" name="num" nullmsg="账户数不能为空"/></td>
                   <td><input class="kc_text_2" type="text" name="money"  nullmsg="入账金额不能为空" /></td>
                   <td><input class="kc_text_2" type="text" name="jtname"  nullmsg="所属集团客户不能为空" /></td>
                   <td><input class="kc_text_2" type="text" name="lxname"  nullmsg="联系人不能为空" /></td>
                   <td><input class="kc_text_2" type="text" name="lxphone"  nullmsg="联系电话不能为空" /></td>
                   <td  widtd="100"></td>
                   <td widtd="100">
                   	<a href="javascript:void(0);" onclick="operating('add');" class="check">添加</a>
                   </td>
               </tr> 
               </form>
                 
               <c:forEach items="${pageEnt.listPages}" var = "ent" varStatus="">
                <tr id="tr_date_${ent.id}">
                	<td>${ent.kindEn.nmLhub}</td>
                   	<td>${ent.nmDepart} </td>
                   	<td>${ent.course.nmMajor}</td>
                   	<td>${ent.course.nmLevel}</td>
                   	<td>${ent.course.nmSub}</td>
                   	<td>${ent.course.nmArea}</td>
                   	<td>${ent.kindEn.nmKClass}</td>
                 	<td>${ent.num}</td>
                 	<td>${ent.money}</td>
                 	<td>${ent.nmThird}</td>
                 	<td>${ent.nmContact}</td>
                 	<td>${ent.phone}</td>
                 	<td><p:fmtDate parttern="yyyy-MM-dd" value="${ent.createtime}"/></td>
                 	<td>
                 		<a href="javascript:void(0);" onclick="delfromexamt('${ent.id}');" class="delete">删除</a>
                 		<a href="javascript:void(0);" onclick="showFrom('${ent.id}')" class="check">修改</a>
                 	</td>
               </tr>
               
               <tr id="tr_from_${ent.id}" style="display: none">
                <form action="admin/modifyOpenKind4Third" id="form_${ent.id}">
                
                <input name="okthirdid" value="${ent.id}" type="hidden"/>
                <input id="kindid_${ent.id}" value="${ent.kindid}" name="kindid" type="hidden"/>
                   
                <td><a href="javascript:void(0);" onclick="openWindow('${ent.id}');" id="uu_val1_add">${ent.kindEn.nmLhub}</a></td>
                <td><a href="javascript:void(0);" onclick="openWindow('${ent.id}');" id="uu_val2_add">${ent.nmDepart}</a></td>
                <td><a href="javascript:void(0);" onclick="openWindow('${ent.id}');" id="uu_val3_add">${ent.course.nmMajor}</a></td>
                <td><a href="javascript:void(0);" onclick="openWindow('${ent.id}');" id="uu_val4_add">${ent.course.nmLevel}</a></td>
                <td><a href="javascript:void(0);" onclick="openWindow('${ent.id}');" id="uu_val5_add">${ent.course.nmSub}</a></td>
                <td><a href="javascript:void(0);" onclick="openWindow('${ent.id}');" id="uu_val6_add">${ent.course.nmArea}</a></td>
                <td><a href="javascript:void(0);" onclick="openWindow('${ent.id}');" id="uu_val7_add">${ent.kindEn.nmKClass}</a></td>
                   
                <td><input class="kc_text_2" type="text" name="num" value="${ent.num}" nullmsg="账户数不能为空"/></td>
                <td><input class="kc_text_2" type="text" name="money" value="${ent.money}" nullmsg="入账金额不能为空" /></td>
                <td><input class="kc_text_2" type="text" name="jtname"  nullmsg="所属集团客户不能为空" value="${ent.nmThird}" /></td>
                <td><input class="kc_text_2" type="text" name="lxname"  nullmsg="联系人不能为空" value="${ent.nmContact}" /></td>
                <td><input class="kc_text_2" type="text" name="lxphone"  nullmsg="联系电话不能为空" value="${ent.phone}" /></td>
                <td><p:fmtDate parttern="yyyy-MM-dd" value="${ent.createtime}"/></td>
                <td widtd="100">
                 	 <a href="javascript:void(0);" onclick="hideFrom('${ent.id}')"  class="delete">取消</a>
                     <a href="javascript:void(0);" onclick="operating('${ent.id}')" class="check">确定</a>
                </td>	
               </form>                   	
               </tr>
				</c:forEach>
              </tbody>
        	</table>
    	</div>
    	
        <p:pageTag name="pageEnt" action="admin/companyUsers_setList"/>
    </div>
    
	<div id="window" style="padding:5px;background: #fafafa ; display: none;" >
		<iframe id="operate" width="100%" scrolling="auto" height="100%" frameborder="0"  src=""></iframe>
	</div>
	
	<script type="text/javascript">
		//学习中心	课程类别	专业名称	层次	科目	考试范围	套餐
	function showFrom(id){
		$("#tr_date_"+id).hide();
		$("#tr_from_"+id).show();
	}
	
	function hideFrom(id){
		$("#tr_date_"+id).show();
		$("#tr_from_"+id).hide();
	}
	
	function delfromexamt(id){
			jQuery.messager.progress({
					title:'请等待',
					text:'提交数据中...',
					interval:700
			});
			jQuery.post("admin/delagentitem",{"agentitemid":id}, function(data) {
					jQuery.messager.progress('close');
					alert(data.msg);
					if(data.status==1){
						  window.location.reload();
					}
			}, "json");
	}
	
	function closeWindow(kindid,val1,val2,val3,val4,val5,val6,val7,uuid){
		$("#uu_val1_"+uuid).text(val1);
		$("#uu_val2_"+uuid).text(val2);
		$("#uu_val3_"+uuid).text(val3);
		$("#uu_val4_"+uuid).text(val4);
		$("#uu_val5_"+uuid).text(val5);
		$("#uu_val6_"+uuid).text(val6);
		$("#uu_val7_"+uuid).text(val7);
		$("#kindid_"+uuid).val(kindid);
		jQuery('#window').window('close');
	}
	
   function openWindow(uuid){
		    jQuery("#operate").attr("src","admin/productList?uuid="+uuid);
			jQuery('#window').show();
			jQuery('#window').window({
				"title" : "选择套餐",
				width : jQuery("body").width()/1.5,
				height : jQuery("body").height(),
				modal : true,
				closable : true,
				minimizable : false,
				maximizable : false,
				shadow: false,
				resizable:  false,
				draggable:  false,
				collapsible : false 
			});
	};	 
	
	function operating(id){
		var kindid = $("#kindid_"+id).val();
		if(kindid==null || kindid==""){
			alert("请选择套餐");
		}else{
			if(jiaoyan('#tr_from_'+id+' :input')){
				jQuery.messager.progress({
						title:'请等待',
						text:'提交数据中...',
						interval:700
				});
				 
				jQuery.post($("#form_"+id).attr('action'), $("#form_"+id).serialize(), function(data) {
						jQuery.messager.progress('close');
						alert(data.msg);
						if(data.status==1){
							  window.location.reload();
						}
				}, "json");
				}
		}
	
			
		};
</script>
</body>
</html>