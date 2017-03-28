<%@page import="com.bowlong.lang.NumEx"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div>
<!-- 以前处理上传图片的地方 -->
<c:set var="isUpPic" value="false"></c:set>
<c:choose>
<c:when test="${isUpPic == true }">
<div class="sc_sp f_st"> 
   <div class="fl sc_1">
       <a href="javascript:void(0);" class="a_2 fr" id="delIdImgPic">删除</a>
       <div class="upload-frame a_1 fr">
			<a href="javascript:void(0);" class="btn_addPic" id="btn_addPic2">
		        <span id="liulan" class="a_1">上传</span>
		        <input type="file" id="fileIdImgPic" name="uploadImg"  class="filePrew picuterUpLoad">
		    </a>
		</div>
    	<span>上传图片:</span>
    	<input name="imgPic" id="imgPicInpHidden" type="hidden" value="${optq.imgPic}">
        <a href="javascript:void(0);"  target="_blank" class="a_3 sp_shangchuan" id="imgPicA">
        <c:if test="${optq.imgPic != ''}">
        <img src="${optq.imgPic}" name="img_look" />
        </c:if>
        </a>
    </div>
	<i class="clear"></i>
</div>

<script type="text/javascript">
function initUpPicEvent(){
	uploadMp34("fileIdImgPic","imgPicInpHidden","imgPicA", "center/uploadImg");
	delMp34("delIdImgPic","fileIdImgPic","imgPicInpHidden","imgPicA", "center/uploadImg");
}

jQuery(function(){
	 initUpPicEvent();
});
</script>
</c:when>
<c:otherwise>
<script type="text/javascript">
// ========= 设置界面编辑器 begin ==========
function isCreateKE(){
	var jqTxts = $("textarea[keval]");
	return jqTxts.length > 0;
}

function createKEFunc(K,options){
	var jqTxts = $("textarea[keval]");
	var ret = null;
	jqTxts.each(function(ind){
		var id = $(this).attr("id");
		ret = createKEOne(K,options,id);
	});
	return ret;
}
// ========= 设置界面编辑器 end ==========
</script>
</c:otherwise>
</c:choose>
</div>