<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>学习中心管理后台_基本信息</title>
<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg1">
	<!--头部-->
		<jsp:include page="../../common/center/header.jsp"></jsp:include>
    
    <!--内容-->
    
    <div class="j_content">
    
     <!--菜单-->
    <jsp:include page="../../common/center/meun.jsp"></jsp:include>

    <div class="user_content">
    	<div class="user_info f_st">
        	<div class="user_img fr">
            	<div>
	            	<span class="span_img"><img src="${img1}" width="136" height="84" id="lh_logo" /></span>
	            	<span class="upload_ts">Logo:长136像*宽84像素</span>
	            	<div style="height:40px;">
	            		<input type="file" name="uploadImg" id="logo_img_id" style="margin:10px 0 0 0;float:left;width:75px;height:25px;">
	                	<input value="上传Logo" type="button" class="btn1" onclick="uploadImg('logo_img_id','lh_logo');">
	            	</div>
                </div>
            	<br />
            	<P>审核状态：
		 		<c:choose>
		 		<c:when test="${user.examineStatus == 2}"><font color="red">不通过</font></c:when>
		 		<c:when test="${user.examineStatus == 3}"><font color="green">已通过</font></c:when>
		 		<c:otherwise><font color="blue">审核中</font></c:otherwise>
		 		</c:choose>
            	</P>
            	<a href="javascript:void(0);" id="btn_no"  onclick="qxuser();" class="cszf" style="display: none">取消修改</a>
                <a href="javascript:void(0);"  id="btn_ok" onclick="operating();" class="xgxx" style="display: none">确定修改</a>
                <a href="javascript:void(0);" id="btn_mod" onclick="moduser();" class="xgxx">修改信息</a>
            </div>
        
       	  <table id="show_userinfo">
            <tr>
            	<th>学习中心名称：</th>
            	<td>${user.name}</td>
            </tr>
            <tr>
            	<th>类型：</th>
            	<td>
            	<c:if test="${user.type=='1'}">
		 		 个人
		 		 </c:if>
		 		 <c:if test="${user.type=='2'}">
		 		机构
		 		 </c:if>
            	</td>
            </tr>
            <tr>
            	<th>销售模式：</th>
		 		<td>
		 			<c:choose>
		 			<c:when test="${user.salesmode=='1'}">代理</c:when>
		 			<c:otherwise>自行</c:otherwise>
		 			</c:choose>
		 		</td>
            </tr>
            <tr>
            	<th>联系人：</th>
            	<td>${user.uname}</td>
            </tr>
            <tr>
            	<th>登录用户名：</th>
            	<td>${account.phone}</td>
            </tr>
            <tr>
            	<th>地址：</th>
            	<td>${user.province}${user.city}${user.seat}</td>
            </tr>
            <tr>
            	<th>创建时间：</th>
            	<td><p:fmtDate parttern="yyyy-MM-dd HH:mm:ss" value="${user.createtime}"/></td>
            </tr>
            <tr>
            	<th>支付宝账号：</th>
            	<td>${user.alipay} </td>
            </tr>
           </table>
           <form action="center/modLearnhub" id="modLearnhubform">
           <table style="display: none" id="mod_userinfo">
            <tr>
            	<th>学习中心名称：</th>
            	<td><input name="name" value="${user.name}"  nullmsg="学习中心名称不能为空" /></td>
            </tr>
            <tr>
            	<th>类型：</th>
            	<td>
            	<c:choose>
            	<c:when test="${user.type=='1'}">
            	 个人
            	</c:when>
            	<c:otherwise>
            	机构
            	</c:otherwise>
            	</c:choose>
            	</td>
            </tr>
            <tr>
            	<th>销售模式：</th>
		 		<td>
		 		<c:choose>
            	<c:when test="${user.salesmode=='1'}">
            	 代理
            	</c:when>
            	<c:otherwise>
            	自行
            	</c:otherwise>
            	</c:choose>
		 		</td>
            </tr>
            <tr>
            	<th>联系人：</th>
            	<td><input name="uname" value="${user.uname}" nullmsg="联系人不能为空" /></td>
            </tr>
            <tr>
            	<th>联系电话：</th>
            	<td><input name="account_phone" value="${account.phone}" zz="电话" nullmsg="联系电话不能为空" /></td>
            </tr>
            <tr>
            	<th>省： </th>
            	<td>
            		<input name="province" value="${user.province}" nullmsg="省不能为空" /> 
            		市： <input name="city" value="${user.city}" nullmsg="市不能为空" />
            		街道：<input name="seat" value="${user.seat}" nullmsg="街道不能为空" />
            	</td>
            </tr>
            <tr>
            	<th>创建时间：</th>
            	<td><p:fmtDate parttern="yyyy-MM-dd HH:mm:ss" value="${user.createtime}"/></td>
            </tr>
            <tr>
            	<th>支付宝账号：</th>
            	<td><input name="account_alipay" value="${user.alipay}" disabled="disabled" /> </td>
            </tr>
           </table>
           </form>
           <i class="clear"></i>
           <c:choose>
           <c:when test="${user.isVerifyAlipay }">
           <div style="color: rgb(40,160,40);">支付宝测试状态：通过</div>
           </c:when>
           <c:otherwise>
           <div style="color: rgb(255,99,09);">支付宝测试状态：尚未测试</div>
           </c:otherwise>
           </c:choose>
        </div>
        
         <div class="uc_content">
             <h2 class="h2"><span>测试支付宝：支付1元</span></h2>
       		 <div class="uc_p" style="color: rgb(255,99,99);">为确保您能及时收到代理佣金，请务必进行支付宝测试，付款后重新登录,如果测试状态为“通过”,表示已可以正常收取佣金。</div>
             <div class="uc_p"><a href="javascript:void(0)" class="cszf" onclick="click2TestAlipay();">测试支付</a></div>
             
             <h2 class="h2"><span>简介</span></h2>
       		 <div class="uc_p">
       		 	<textarea id="lhub_desc" style="resize:none;width: 96%;height: 80px;">${user.descr}</textarea>
       		 </div>
       		 <div class="uc_p"><a href="javascript:void(0)" class="cszf" onclick="changeDesc();">修改简介</a></div>
              
             <h2 class="h2"><span>宣传图片</span></h2>
             <div class="upload_img">
             	<div class="upload">
                	 <input type="file" name="uploadImg" id="fine_img_id">
                	 <input value="上传" type="button" class="btn1" onclick="uploadImg('fine_img_id');">
               		 <span class="upload_ts">图片:长800像*宽300像素</span>
                </div>
     
                <ul class="upimg_list">
                    <c:forEach items="${listimg}" var="url" varStatus="status">
                         <li id="img_li_${status.index + 1}">
	                    	<div class="upimg_div fr">
	                        	<a href="javascript:void(0)" onclick="exchange(${status.index + 1},${status.index});">向上</a>
	                        	<a href="javascript:void(0)" onclick="exchange(${status.index + 1},${status.index + 2});">向下</a>
	                        	<a href="javascript:void(0)" onclick="exchange(${status.index + 1},1);" class="fm">设为<br/>封面</a>
	                        	<a href="javascript:void(0)" onclick="exchange(${status.index + 1},'del');">删除</a>
	                        </div>
	                    	<img class="imgs_url" src="${url}" width="800" height="300"/>
                    	</li>
                    </c:forEach>
                </ul>
             </div>
    	</div>
    
    </div>
    <i class="clear"></i>
</div> 
<form action="testPay4Alipay" method="post" id="pay_4_test_alipay">
	<input type="hidden" name="type" value="1"/>
	<input type="hidden" name="id" value="${user.lhid}"/>
	<input type="hidden" name="money" value="1"/>
	<input type="hidden" name="name" value="${user.name}"/>
</form>
<script type="text/javascript">
function exchange(a,b){
 
	var aobj = $("#img_li_"+a);
	var bobj = $("#img_li_"+b);
	if(b=="del"){
		aobj.remove();
	}else{
		if(a>b){
	    	aobj.insertBefore(bobj);
	    }else if(a<b){
	    	aobj.insertAfter(bobj);
	    };
	}
    
   var urlimgs = "";
    $(".imgs_url").each(function(){
    	urlimgs+=$(this).attr("src")+",";
    });
    modimg(urlimgs);
}; 

function uploadImg(fileId,logoId){
  if($("#"+fileId).val()==""){
	   alert("请选择要上传的图片");
   }else{
 	    //图片上传
        $.ajaxFileUpload({
            url:"center/uploadImg", 
            secureuri:false, 
            fileElementId:fileId, 
            dataType: 'xml',
            success: function (data, status){
            	databackImg(data,fileId,logoId);
            },
            error: function (data, status, e){
            	databackImg(data,fileId,logoId);
            }
        });   
   }
}
	
 function databackImg(data,fileId,logoId){
	var val = data.responseText;
	var aa= 5;
	if(window.navigator.userAgent.indexOf("Chrome") !== -1){
		aa = 59;
	}
	data = val.substring(aa,val.length-6);
	data = jQuery.parseJSON(data);
	if(data.status==1){
		var url=data.msg;
		if(!!logoId){
			upLogoVal(url);
		}else{
			var imgs = "${user.imgr4Cover}";
			imgs = imgs+url+",";
			modimg(imgs);
		}
    }else{
        alert(data.msg);
    }
}
 
function upLogoVal(imgurl){
	jQuery.messager.progress({
			title:'请等待',
			text:'提交数据中...',
			interval:700
		});
		jQuery.post("center/logo", {"imgurl":imgurl}, function(data) {
			jQuery.messager.progress('close');
				if(data.status==1){
					location.replace(location);
				}
		}, "json");
}
 
function modimg(imgs){
		jQuery.messager.progress({
			title:'请等待',
			text:'提交数据中...',
			interval:700
		});
		jQuery.post("center/modimg", {"imgs":imgs}, function(data) {
			jQuery.messager.progress('close');
				if(data.status==1){
					location.replace(location);
				}
		}, "json");
}
 
 
function moduser(){
	$("#btn_mod").hide();
	$("#show_userinfo").hide();
	$("#mod_userinfo").show();
	$("#btn_no").show();
	$("#btn_ok").show();
}

function qxuser(){
	$("#btn_mod").show();
	$("#show_userinfo").show();
	$("#mod_userinfo").hide();
	$("#btn_no").hide();
	$("#btn_ok").hide();
}

function operating(){
	var formId = "modLearnhubform";
		if(jiaoyan('#'+formId+' :input')) {
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

function changeDesc(){
	//关闭编辑器
	window.editor.sync();
	var desc = $("#lhub_desc").val();
	if(desc == ""){
		alert("简介不能为空!");
		return;
	}
	
	var url = '<%=basePath%>' + "center/modDesc";
	var data = {"desc":desc};
	var callBack = function(back){
		alert(back.msg);
	};
	$.post(url,data,callBack,"json");
}

function click2TestAlipay(){
	$("#pay_4_test_alipay").submit();
};

// ========= 设置界面编辑器 begin ==========
function isCreateKE(){
	return true;
}

function createKEFunc(K,options){
	if(null != options && options && typeof(options) == "object"){
		return K.create('#lhub_desc',options);
	}
	return K.create('#lhub_desc');
}
// ========= 设置界面编辑器 end ==========
</script> 
</body>
</html>