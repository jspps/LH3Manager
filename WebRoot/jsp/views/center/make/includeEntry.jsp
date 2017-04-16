<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<c:set var="num" value="0"></c:set>
<div class="bjsj_left">
<!-- 试卷编写左边数据列表 -->
<a href="javascript:void(0);" class="a_yl" onclick="viewExam();">预览本试卷</a>
<p class="p_1">
	<a class="fr a_1" href="javascript:void(0);" onclick="viewExamCatalog();">试卷修改</a>
	<div style="text-align: center;" id="print_cursor">
		<a class="a_add" href="javascript:void(0)" onclick="OnClickPrintView()">打印试卷</a>
		<form action="center/printView" name="fm_printView" id="fm_printView" method="post">
		<input name="unqid" value=${exam != null ? exam.id : examid} type="hidden" />
		</form>
	</div>
</p>
<c:forEach items="${examcatalogs}" var="ent">
	<div class="tim f_st"> 
       	<div class="p_2">
       		<div>
	       		<a href="javascript:void(0);" onclick="addQuestion4Left(${ent.examid},${ent.id},${ent.catalogType},${ent.gid});" onmouseover="catalogOnMouseOver(${ent.id});" onmouseout="catalogOnMouseOut(${ent.id});">
	       			${ent.titleEllipsis}
	       		</a>
	       		<a href="javascript:void(0);" class="del"></a>
       		</div>
       		<i class="clear"></i>
       	</div>
       	<div style="display: none;" name="${ent.id}">
      			${ent.title}
      	</div>
       	<c:forEach items="${ent.listChild}" var = "child">
	       	<div class="p_3">&nbsp;&nbsp;&nbsp;&nbsp;${num + 1}、
	            <c:set var="num" value="${num + 1}"></c:set>
			    <a class="fr a_1" href="javascript:void(0);" onclick="go2Edit(${child.type},${child.gid},${child.examid},${child.optid});">修改</a>
			    <a class="fr a_2"  href="javascript:void(0);" onclick="deloptquestion('${child.optid}');">删除</a> 
			</div>
       	</c:forEach>
    </div>
</c:forEach>
</div>
<form id="fm_go2Edit" action="" method="post">
	<input type="hidden" value="0" name="examid" id="fm_go2Edit_examid"/>
	<input type="hidden" value="0" name="optquestion" id="fm_go2Edit_optid"/>
</form>
<form id="fm_go2ExamCatalog" action="center/go2ExamCatalog" method="post">
	<input type="hidden" value="${examid}" name="examid"/>
</form>
<form id="fm_go2EditPapers" action="center/editPapers" method="post">
	<input type="hidden" value="${examid}" name="examid"/>
</form>
<form id="fm_addquesion" action="" method="post">
	<input type="hidden" name="examid" value="0" id="fm_add_examid"/>
	<input type="hidden" name="examcatalogid" value="0" id="fm_add_examcatalogid"/>
	<input type="hidden" name="type" value="0" id="fm_add_type"/>
</form>
<script type="text/javascript">
function go2Edit(type,gid,examid,optid) {
	var cval = type;
	if(type == 7){
		cval = gid;
	}
	var action = "";
	switch(cval){
		case 1:
			action = "center/singleEntry";
		break;
		case 2:
			action = "center/doubleEntry";
		break;
		case 3:
			action = "center/judgeEntry";
		break;
		case 4:
			action = "center/fillBlankentry";
		break;
		case 5:
			action = "center/shortAnswerentry";
		break;
		case 6:
			action = "center/discussEntry";
		break;
		default:
		break;
	};
	
	if(!action || action == ""){
		alert("数据异常，不能录入题！");
		return;
	}
	$("#fm_go2Edit_examid").val(examid);
	$("#fm_go2Edit_optid").val(optid);
	var jqform_edit = $("#fm_go2Edit");
	jqform_edit.attr("action",action);
	jqform_edit.submit();
}

function deloptquestion(id){
	jQuery.messager.progress({
		title:'请等待',
		text:'提交数据中...',
		interval:700
	});
	jQuery.post("center/deloptquestion", {"optqid":id}, function(data) {
			jQuery.messager.progress('close');
			alert(data.msg);
			if(data.status==1){
				var jqFormSelf = $("#reload_self_fm");
				if(jqFormSelf.length > 0){
					jqFormSelf.submit();
				}else{
					window.location.reload();
				}
			}
	}, "json");
}

function viewExam(){
	$("#fm_go2EditPapers").submit();
}

function viewExamCatalog(){
	$("#fm_go2ExamCatalog").submit();
}

function addQuestion4Left(examid,examcatalogid,catalogType,gid){
		$("#fm_add_examid").val(examid);
		$("#fm_add_examcatalogid").val(examcatalogid);
		$("#fm_add_type").val(catalogType);
		var jqForm = $("#fm_addquesion");
		var action = "";
		var cval = catalogType;
		if(catalogType == 7){
			cval = gid;
		}
		switch(cval){
			case 1:
				action = "center/singleEntry";
			break;
			case 2:
				action = "center/doubleEntry";
			break;
			case 3:
				action = "center/judgeEntry";
			break;
			case 4:
				action = "center/fillBlankentry";
			break;
			case 5:
				action = "center/shortAnswerentry";
			break;
			case 6:
				action = "center/discussEntry";
			break;
			default:
			break;
		};
		
		if(!action || action == ""){
			alert("数据异常，不能录入题！");
			return;
		}
		jqForm.attr("action",action);
		jqForm.submit();
	};
	
	function catalogOnMouseOver(catalogid){
		$("div[name="+catalogid+"]").show();
	};
	
	function catalogOnMouseOut(catalogid){
		$("div[name="+catalogid+"]").hide();
	};
	
	function OnClickPrintView(){
		$("#fm_printView").submit();
	}
</script>