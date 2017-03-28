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
<title>学习中心管理后台_录入试题_多项选择</title>
<jsp:include page="../../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg">
	<!--头部-->
	<jsp:include page="../../../common/center/header.jsp"></jsp:include>

    <div class="uc_content txt_cen">
    	<h2 class="h2 h2_1"><span class="span">多项选择题</span></h2>
    </div>
    
    
    <!--内容-->
   <div class="w_content" style="min-width: 1260px;"> 
   
   
   <jsp:include page="includeEntry.jsp"></jsp:include>
   
   <div class="user_content user_content_1">
    <jsp:include page="../product/cursor.jsp" />
    
    <form action="center/addoptquestion" id="addoptquestion">
        <input name="type" type="hidden" value="${type}">
     	<input name="gid" type="hidden" value="2">
        <input name="examid" type="hidden" value="${examid}"><!-- 所属试卷id -->    
    	<div class="sjsm">
        	<p>输入内容：</p>
        	<div class="text text_1"><textarea  nullmsg="内容不能为空"  name="content" id="question_content" keval="1">${optq.content}</textarea></div>
        </div>
        
        <jsp:include page="up_pic.jsp" />
      	
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
            	<div class="sc_text">教材位置：<input type="text"    value="${optq.position}" name="position" class="input1"/></div>
            	<span>正确答案:</span>
                <input type="checkbox" class="checkboxright"  value="A" id="right_A"/><span class="span">A</span>
                <input type="checkbox" class="checkboxright" value="B" id="right_B"/><span class="span">B</span>
                <input type="checkbox" class="checkboxright" value="C" id="right_C"/><span class="span">C</span>
                <input type="checkbox" class="checkboxright" value="D" id="right_D"/><span class="span">D</span>
                <input type="checkbox" class="checkboxright" value="E" style="display: none" id="right_E"/><span id="span_e" class="span"  style="display: none">E</span>               
                <input type="checkbox" class="checkboxright" value="F" style="display: none" id="right_F"/><span id="span_f" class="span"  style="display: none">F</span>                
                <input type="checkbox" class="checkboxright"  value="G" style="display: none" id="right_G"/><span id="span_g" class="span"  style="display: none">G</span>
                <a href="javascript:void(0);" class="a_1" onclick="addRight();">增加选项</a>
                <a href="javascript:void(0);" class="a_1" onclick="reduceRight();">减少选项</a>
            </div>
        	<i class="clear"></i>
      	</div>
       <div class="sjsm">
        	<p>本题解析：</p>
        	<div class="text text_1"><textarea name="analyse" id="question_analyse" keval="1">${optq.analyse}</textarea></div>
        </div>
        <div class="lrst_btn">
         <input name="answernum" id="answernum_opt" value="4" type="hidden">
         
          <input name="examcatalogid" value="${examcatalogid}" type="hidden">
          <input name="optquestion" value="${optq.optid}" type="hidden">
        	<input name="right" id="hiddenright" value="${optq.right_2}" type="hidden">
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
<form action="center/doubleEntry" method="post" id="reload_self_fm">
	<input type="hidden" value="${examid}" name="examid"/>
	<input type="hidden" value="${type}" name="type"/>
	<input name="gid" type="hidden" value="2">
	<input type="hidden" value="${optq.optid}" name="optquestion"/>
	<input type="hidden" value="${examcatalogid}" name="examcatalogid"/>
</form>
  	<script type="text/javascript">
  	var rifth = "e";
	jQuery(function(){ 
		uploadMp34("fileIdImgPic","imgPicInpHidden","imgPicA", "center/uploadImg");
	    delMp34("delIdImgPic","fileIdImgPic","imgPicInpHidden","imgPicA", "center/uploadImg");
	    
  	    uploadMp34("fileIdMp3","videourlMp3","mp3", "center/uploadMp3");
  	    delMp34("delIdMp3","fileIdMp3","videourlMp3","mp3", "center/uploadMp3");
  	    
  	    uploadMp34("fileIdMp4","voiceurlMp4","mp4", "center/uploadMp4");
	    delMp34("delIdMp4","fileIdMp4","voiceurlMp4","mp4", "center/uploadMp4");
	    
	    reInit();
  	});
  	
  	function reInit(){
 	 	var isNotNull = "${optq !=null}";
 	 	if(isNotNull == "true"){
 	 		var num = "${optq.answernum}";
 	 		num = parseInt(num,10);
 	 		if(isNaN(num))
 	 			return;
 	 		if(num >= 7){
 	 			rifth = "h";
 	 			$("#h2_e,#div_e,#right_E,#span_e").show();
 	 			$("#h2_f,#div_f,#right_F,#span_f").show();
 	 			$("#h2_g,#div_g,#right_G,#span_g").show();
 	 		}else if(num >= 6){
 	 			$("#h2_e,#div_e,#right_E,#span_e").show();
 	 			$("#h2_f,#div_f,#right_F,#span_f").show();
 	 			rifth = "g";
 	 		}else if(num >= 5){
 	 			$("#h2_e,#div_e,#right_E,#span_e").show();
 	 			rifth = "f";
 	 		}else{
 	 			rifth = "e";
 	 		}
 	 		
 	 		var examtypes ="${optq.right_2}";
			var splitstr= new Array();
			splitstr = examtypes.split(",");
			for(n=0;n<splitstr.length;n++){
				 $("#right_"+splitstr[n]).attr("checked","true");
			}
 	 	}else{
 	 	}
 	}
 	
  	function saveDoudle(){
  		window.location.href= '<%=basePath%>'+"center/go2ExamCatalog?examid=${examid}";
  	}
  	
	function operating(formId){
		syncKEOne("question_content");
		syncKEOne("question_analyse");
		if(jiaoyan('#'+formId+' :input')) {
			var right ="";
			$(".checkboxright").each(function(){
			     if($(this).attr("checked")){
			    	 right+=$(this).val()+",";
			     }
			 });
			if(right ==""){
				alert("请选择正确答案");
			}else{
				$("#hiddenright").val(right);
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
  	
	function addRight(){
		if(rifth == "e"){
			$("#h2_e,#div_e,#right_E,#span_e").show();
			rifth="f";
			$("#answernum_opt").val("5");
			return true;
		}
		if(rifth == "f"){
			$("#h2_f,#div_f,#right_F,#span_f").show();
			rifth="g";
			$("#answernum_opt").val("6");
			return true;
		}
		if(rifth == "g"){
			$("#h2_g,#div_g,#right_G,#span_g").show();
			rifth="h";
			$("#answernum_opt").val("7");
			return true;
		}
		if(rifth == "h"){
			alert("最多只能添加7个选项");
			return true;
		}
	}
	
	function reduceRight(){
		if(rifth == "e"){
			alert("至少添加4个选项");
			return true;
		}
		if(rifth == "f"){
			$("#h2_e,#div_e,#right_E,#span_e").hide();
			rifth="e";
			$("#answernum_opt").val("4");
			return true;
		}
		if(rifth == "g"){
			$("#h2_f,#div_f,#right_F,#span_f").hide();
			rifth="f";
			$("#answernum_opt").val("5");
			return true;
		}
		if(rifth == "h"){
			$("#h2_g,#div_g,#right_G,#span_g").hide();
			rifth="g";
			$("#answernum_opt").val("6");
			return true;
		}
	}
	</script>
</body>
</html>