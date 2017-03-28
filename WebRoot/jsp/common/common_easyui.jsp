<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("appPath", request.getContextPath());
%>
<link rel="stylesheet" type="text/css" href="${appPath}/jsp/js/themes/default2/easyui.css">
<script type="text/javascript" src="${appPath}/jsp/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${appPath}/jsp/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${appPath}/jsp/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${appPath}/jsp/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${appPath}/jsp/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${appPath}/jsp/js/baseUtil.js"></script>
<!-- 
<script charset="utf-8" src="${appPath}/jsp/js/kindeditor-4.1.10/kindeditor-min.js"></script>
<script charset="utf-8" src="${appPath}/jsp/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
 -->
<script type="text/javascript" src="${appPath}/jsp/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${appPath}/jsp/js/kindeditor4.1.11/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${appPath}/jsp/js/kindeditor4.1.11/lang/zh-CN.js"></script>
<script type="text/javascript">
var tmpKEObj = {};

function isCreateKE(){
	return false;
}

function createKEOne(K,options,id){
	var ret = null;
	if(null != options && options && typeof(options) == "object"){
		ret = K.create('#'+id,options);
	}else{
		ret = K.create('#'+id);
	}
	
	tmpKEObj[id] = ret;
	return ret;
}

//关闭编辑器
function syncKEOne(id){
	var tmp = tmpKEObj[id];
	if(tmp){
		tmp.sync();
	}else{
		if(window.editor){
			window.editor.sync();
		}
	}
}
	
function createKEFunc(K,options){
	return null;
}

function ready4KE(){
	var isHas = isCreateKE();
	if(isHas){
		var options = {
			uploadJson : "${appPath}/jsp/js/kindeditor4.1.11/jsp/upload_json.jsp",
			fileManagerJson : "${appPath}/jsp/js/kindeditor4.1.11/jsp/file_manager_json.jsp",
			allowFileManager : true
		};
		// 设置界面编辑器
		KindEditor.ready(function(K) {
		    // window.editor = K.create('#lhub_desc');
		    window.editor = createKEFunc(K,options);
		});
	}
}

$(document).ready(function(){
	ready4KE();
});
</script>