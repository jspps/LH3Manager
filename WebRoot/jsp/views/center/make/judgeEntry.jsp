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
<title>学习中心管理后台_录入试题_判断题</title>
<jsp:include page="../../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg">
	<!--头部-->
	<jsp:include page="../../../common/center/header.jsp"></jsp:include>

    <div class="uc_content txt_cen">
    	<h2 class="h2 h2_1"><span class="span">判断题</span></h2>
    </div>
    
    
    <!--内容-->
   <div class="w_content" style="min-width: 1260px;"> 
   
   <jsp:include page="includeEntry.jsp"></jsp:include>
   
   <div class="user_content user_content_1">
   	<jsp:include page="../product/cursor.jsp" />
   	
    <form action="center/addoptquestion" id="addoptquestion">
    	<input name="type" type="hidden" value="${type}">
     	<input name="gid" type="hidden" value="3">
        <input name="examid" type="hidden" value="${examid}"><!-- 所属试卷id -->  
        
    	<div class="sjsm">
        	<p>输入内容：</p>
        	<div class="text text_1"><textarea  nullmsg="内容不能为空"  name="content">${optq.content}</textarea></div>
        </div>
        
        <!-- 
        <div class="sjsm">
        	<h2>本题得分</h2>
        	<input type="text" name="score" nullmsg="得分不能为空" zz="正数" value="0">
         </div>
        -->
        
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
      	
        <div class="sc_sp f_st"> 
           <div class="fl sc_1">
                <a href="javascript:void(0);" class="a_2 fr" id="delIdMp4">删除</a>
               <div class="upload-frame a_1 fr">
					<a href="javascript:void(0);" class="btn_addPic" id="btn_addPic2">
				        <span id="liulan" class="a_1">上传</span>
				        <input type="file" id="fileIdMp4" name="uploadMp4"  class="filePrew picuterUpLoad">
				    </a>
				</div>
            	<span>上传视频:</span>
            	<input name="voiceurl" id="voiceurlMp4" type="hidden" value="${optq.voiceurl}"  value="1"><!-- 视频 -->
                <a href="javascript:void(0);"  target="_blank" class="a_3 sp_shangchuan" id="mp4"></a>
            </div>
            <div class="fr sc_1" >
                <a href="javascript:void(0);" class="a_2 fr"  id="delIdMp3">删除</a>
             	<div class="upload-frame a_1 fr">
					<a href="javascript:void(0);" class="btn_addPic" id="btn_addPic2">
				        <span id="liulan" class="a_1">上传</span>
				        <input type="file" id="fileIdMp3" name="uploadMp3"  class="filePrew picuterUpLoad">
				    </a>
				</div>
            	<span>上传音频:</span>
            	 
            	<input name="videourl" id="videourlMp3" value="${optq.videourl}" type="hidden" value="1"><!-- 视频 -->
                <a href="javascript:void(0);"  target="_blank" class="a_3 yp_shangchuan" id="mp3"></a>
            </div>
        	<i class="clear"></i>
      	</div>
      	
         <div class="sc_sp f_st">
            <div class="sc_2">
            	<div class="sc_text">教材位置：<input type="text"    value="${optq.position}"  name="position" class="input1"/></div>
            	<span>正确答案:</span>
                <input type="radio"  name="right" class="checkboxright"  id="right_A" value="A"/><span class="span">对</span>
                <input type="radio" name="right"  class="checkboxright" id="right_B"  value="B"/><span class="span">错</span>
            </div>
        	<i class="clear"></i>
      	</div>
        
       <div class="sjsm">
        	<p>本题解析：</p>
        	<div class="text text_1"><textarea   name="analyse">${optq.analyse}</textarea></div>
        </div>
        
        <div class="lrst_btn">
         <input name="examcatalogid" value="${examcatalogid}" type="hidden">
           <input name="optquestion" value="${optq.optid}" type="hidden">
        	        	 <c:if test="${optq !=null}">
            <input type="button" class="lrbtn_2" onclick="operating('addoptquestion');" value="确定修改"/>
            </c:if>
            <c:if test="${optq==null}">
        	  <input type="button" class="lrbtn_2" onclick="operating('addoptquestion');" value="保存并录入下一题"/>
        	<input type="button" class="lrbtn_1" value="完成大题录入" onclick="saveDoudle();"/>
        	</c:if>
         
        </div>
    </form>
    </div>
    <i class="clear"></i>
  </div>
<form action="center/judgeEntry" method="post" id="reload_self_fm">
	<input type="hidden" value="${examid}" name="examid"/>
	<input type="hidden" value="${type}" name="type"/>
	<input name="gid" type="hidden" value="3">
	<input type="hidden" value="${optq.optid}" name="optquestion"/>
	<input type="hidden" value="${examcatalogid}" name="examcatalogid"/>
</form>
  <script type="text/javascript">
	jQuery(function(){ 
		uploadMp34("fileIdImgPic","imgPicInpHidden","imgPicA", "center/uploadImg");
	    delMp34("delIdImgPic","fileIdImgPic","imgPicInpHidden","imgPicA", "center/uploadImg");
	    
  	    uploadMp34("fileIdMp3","videourlMp3","mp3", "center/uploadMp3");
  	    delMp34("delIdMp3","fileIdMp3","videourlMp3","mp3", "center/uploadMp3");
  	    
  	    uploadMp34("fileIdMp4","voiceurlMp4","mp4", "center/uploadMp4");
	    delMp34("delIdMp4","fileIdMp4","voiceurlMp4","mp4", "center/uploadMp4");
		$("#right_${optq.right_2}").attr("checked","true");    
  	});
  	
  	function saveDoudle(){
  		window.location.href= '<%=basePath%>'+"center/go2ExamCatalog?examid=${examid}";
  	}
  	
	function operating(formId){
		if(jiaoyan('#'+formId+' :input')) {
			var bool = true;
			$(".checkboxright").each(function(){
				 if($(this).attr("checked")=="checked"){
			    	 bool = false;
			     }
			 });
			if(bool){
				alert("请选择正确答案");
			}else{
				jQuery.messager.progress({
					title:'请等待',
					text:'提交数据中...',
					interval:700
			});
			jQuery.post($("#"+formId).attr('action'), $("#"+formId).serialize(), function(data) {
					jQuery.messager.progress('close');
					alert(data.msg);
					if(data.status==1){
						 $("#reload_self_fm").submit();
					}
			}, "json");
			}
			
		}
	}
	</script>
</body>
</html>