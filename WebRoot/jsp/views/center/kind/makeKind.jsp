<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<base href="<%=basePath%>" />
	<title>学习中心管理后台_套餐设置</title>
	<jsp:include page="../../../common/common_css.jsp"></jsp:include>
	<jsp:include page="../../../common/common_easyui.jsp"></jsp:include>
	
	<!-- 引用图片裁剪的插件 -->
	<script type="jsp/jcrop/jquery.Jcrop.js"></script>
	<link rel="stylesheet" type="text/css" href="jsp/jcrop/jquery.Jcrop.css"/>
</head>
<body class="body_bg1">
	<!--头部-->
	<jsp:include page="../../../common/center/header.jsp"></jsp:include>
    
    <!--内容-->
    
    <div class="j_content">
    
     <!--菜单-->
    <jsp:include page="../../../common/center/meun.jsp"></jsp:include>

    <div class="user_content">
    	<jsp:include page="../product/cursor.jsp" />

        <div class="uc_content">
			<h2 class="h2"><span>${kindname}</span> <label>考试范围【${areaname}】</label></h2>
			
            <div class="zktype f_st" id="div_kind_class">
	            <c:forEach items="${kindclass}" var="ent">
	   		 	<div class="fl zktype_div">
	   		 		<c:choose>
		            <c:when test="${kindclassId==ent.id}">
		            	<a class="zk_tit" href="javascript:void(0);" kcid="${ent.id}" style="background: green;color: white;">${ent.name}</a>
		            </c:when>
		            <c:otherwise>
		            	<a class="zk_tit" href="javascript:void(0);" kcid="${ent.id}">${ent.name}</a>
		            </c:otherwise>
		            </c:choose>
	   		 	</div>
				</c:forEach>
            </div>
            
            <div class="zk_kec f_st">
             	<h2>课程编号：SXT88861${coursesid}</h2>
                <span>套餐价格：</span>
                <input type="text"  id="max_price" nullmsg="套餐价格不能为空" zz="价格" value="${kind.price}" class="input1 mr_10 w_1"/>
                <span>优惠价格：</span>
                <input type="text" id="max_discount" nullmsg="优惠价格不能为空"   zz="价格" value="${kind.discount}" class="input1 mr_10 w_1"/>
                <span>有效期(天)：</span>
                <input type="text" id="max_validity" nullmsg="有效期不能为空" zz="正数"  value="${kind.validity}" class="input1 mr_10 w_1"/>
                <span>获得考币数量：</span>
                <input type="text" id="max_kbi" value="${kind.kbi}" nullmsg="考币数不能为空" zz="正数" class="input1 mr_10 w_2"/>
            </div>
            
            <div style="clear: both;"></div>
            
            <div class="zk_zjlx">
            	<ul id="examtypes_ul">
                <c:forEach items="${list}" var="exts" varStatus="status">
					<li catalogid="${exts.id}">
			        <h2>
			        	${exts.name}
			        </h2>
			        <p class="txt_cen">
			        	<c:choose>
			        	<c:when test="${exts.id != 6 && exts.id != 5}">【${exts.num}道题】</c:when>
			        	<c:otherwise>&nbsp;</c:otherwise>
			        	</c:choose>
			        </p>
			        </li>
                </c:forEach>
             	</ul>
			</div>
			
			<div class="zk_zjlx_list" id="div_exams">
            <c:forEach items="${list}" var="exts" varStatus="status">
               	<ul class="hide etshover" id="ul_exam_${exts.id}">
					<li>
						<c:choose>
			        	<c:when test="${exts.id != 6 && exts.id != 5}">
			        	<input type="checkbox" name="examtypeid" value="${exts.id}"/> 全选
			        	</c:when>
			        	<c:otherwise>
			        	<input type="checkbox" name="examtypeid" value="${exts.id}" <c:if test="${exts.isHas}"> checked="checked" </c:if> />是否包含
			        	</c:otherwise>
			        	</c:choose>
			        </li>
			        <c:forEach items="${exts.exams }" var="exam">
	        		<li>
	        			<input type="checkbox" name="examid" value="${exam.id}" <c:if test="${exam.isHas}"> checked="checked" </c:if> />
	        			<a href="javascript:void(0)">${exam.name}</a>
	        		</li>
		        	</c:forEach>
		        </ul>
            </c:forEach>
			</div>
			
            <div class="tk_sc">
            	<a href="javascript:void(0)" class="current" id="wenzi" onclick="sheditor('1');">课程详情</a>
             	<a href="javascript:void(0)" id="tupian" onclick="sheditor('2');">课程封面</a>
             	<a href="javascript:void(0)" id="custbaoz" onclick="sheditor('3');">消费者保障</a>
            </div>
            
            <div class="kc_tab ">
            	<div class="wz_text" id="div_wenzi" >
                	<textarea nullmsg="文字描述不能为空"  id="max_textarea">${product.descr}</textarea>
                </div> 
                 		 
                <div id="div_tupian" class="kc_tab upload_img  hide">
		        	<div class="upload">
		            	<input name="imgUrl" nullmsg="封面必须上传" value="${product.imgurl}" type="hidden" id="img_value_id" />
		                <input type="file" name="uploadImg" id="fine_img_id" />
		                <input value="上传" type="button" class="btn1" onclick="uploadImg('fine_img_id','img_value_id','img_url_id');" />
		                <span class="upload_ts">图片:长270像*宽210像素</span>
					</div>
		            <ul class="upimg_list">
		            	<li>
		                <c:if test="${product.imgurl == ''}">
		                	<img src="../images/test.jpg" class="hide" id="img_url_id" width="270" height="210" />
						</c:if>
		                <c:if test="${product.imgurl != ''}">
		                	<img src="${product.imgurl}" id="img_url_id" width="270" height="210" />
		                </c:if>
		                </li>
		            </ul>
                </div>
			</div>
			
			<div class="wz_text hide" id="div_wenzi4cust" >
				<textarea nullmsg="消费保障不能为空"  id="txt4cust">${product.protection}</textarea>
            </div>  
            <div class="kcszbtn"> <input class="btn" onclick="operatingMaxKind();" type="button" value="保存" /></div>
		</div>
		<i class="clear"></i>
		</div>
	</div>

<form action="center/makeKind" method="post" id="fm_make">
	<input type="hidden" value="${productid}" name="productid" />
	<input type="hidden" value="${kindclassId}" name="kindclassId" id="fm_make_kcid"/>
</form>

<script>   
function uploadImg(fileId,hiddenId,ImgId){
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
				databackImg(data,fileId,hiddenId,ImgId);
			},
			error: function (data, status, e){
				databackImg(data,fileId,hiddenId,ImgId);
			}
		});   
	}
}

function databackImg(data,fileId,hiddenId,ImgId){
	var val = data.responseText;
	var aa= 5;
	if(window.navigator.userAgent.indexOf("Chrome") !== -1){
		aa = 59;
	}
	data = val.substring(aa,val.length-6);
	data = jQuery.parseJSON(data);
	if(data.status==1){
         var url=data.msg;
         $("#"+hiddenId).val(url);
         $("#"+ImgId).attr("src",url);
         $("#"+ImgId).show();
         alert("成功");
    }else{
        alert(data.msg);
    }
}

function  sheditor(id){
	$("#wenzi").removeClass("current");
	$("#custbaoz").removeClass("current");
	$("#tupian").removeClass("current");
	$("#div_wenzi").hide();
	$("#div_tupian").hide();
	$("#div_wenzi4cust").hide();
	if(id=="1"){
		$("#wenzi").addClass("current");
		$("#div_wenzi").show();
	}else if(id=="2"){
		$("#tupian").addClass("current");
		$("#div_tupian").show();
	}else if(id=="3"){
		$("#custbaoz").addClass("current");
		$("#div_wenzi4cust").show();
	}
} 

function operatingMaxKind(){
	//关闭编辑器
	window.editor.sync();
	
	var kindclassid = "${kindclassId}";
	var kindid = "${kind.id}";
	var productid = "${productid}";
	var kbi = $("#max_kbi").val();
	var validity = $("#max_validity").val();
	var price = $("#max_price").val();
	var discount = $("#max_discount").val();
	var imgurl = $("#img_value_id").val();
	var descr =  $("#max_textarea").val();
	var txt4cust = $("#txt4cust").val();
	
	var jy = jiaoyan("#max_kbi")&&jiaoyan("#max_validity")&&jiaoyan("#max_price")&&jiaoyan("#max_discount")
	 			&&jiaoyan("#max_textarea")&&jiaoyan("#img_value_id");
	if(jy){
		var examtypes = "";
		var mapTmp = {};
		$(":checkbox[name='examtypeid']").each(function(){
			var isChecked = $(this).is(":checked");
			var val = $(this).val();
			if(isChecked){
				mapTmp[val] = true;
				examtypes += val +",";
			}
		});
		
		var exercisesnum = 0;// 数量(1, '章节练习')
		var ahentinum = 0;// 数量(2, '历年真题')
		var simulationnum = 0;// 数量(3, '全真模拟')
		var vastnum = 0;// 数量(4, '考前押题')
		
		var examids = "";
		$(":checkbox[name='examid']").each(function(){
			var isChecked = $(this).is(":checked");
			if(isChecked){
				examids += $(this).val()+",";
				
				// 判断该套餐是否包含examtypes
				var parent = $(this).parent().parent();
				var curetypeid = (parent.find(":checkbox[name='examtypeid']")).val();
				if(!(mapTmp[curetypeid])){
					mapTmp[curetypeid] = true;
					examtypes += curetypeid +",";
				}
				
				// 计算试卷数值
				if(curetypeid == "1"){
					exercisesnum++;
				}else if(curetypeid == "2"){
					ahentinum++;
				}else if(curetypeid == "3"){
					simulationnum++;
				}else if(curetypeid == "4"){
					vastnum++;
				}  
			}
		});
		
		
		if(examids == "" && examtypes == ""){
			alert("请选择该套餐可包含的试卷!");
			return;
		}
		
		if(price < 5){
			alert("价格不能少于5元");
			return;
		}
		
		if(discount < 5){
			alert("优惠价格不能少于5元");
			return;
		}
		
		discount = parseInt(discount,10);
		price = parseInt(price,10);
		if(discount > price){
			alert("优惠价格不能高于原价 discount = "+discount+",price = "+price);
			return;
		}
			   
		var date = {
			"examtypes":examtypes,
			"examids":examids,
			"kbi":kbi,
			"validity":validity,
			"price":price,
			"discount":discount,
			"productid":productid,
			"kclassid":kindclassid,
			"descr":descr,
			"exercisesnum":exercisesnum,
			"ahentinum":ahentinum,
			"simulationnum":simulationnum,
			"vastnum":vastnum,
			"kindid":kindid,
			"txt4cust":txt4cust,
			"imgurl":imgurl
		};
		
		jQuery.messager.progress({
			title:'请等待',
			text:'提交数据中...',
			interval:700
		});
		
		jQuery.post("center/modifyKind", date, function(data) {
			jQuery.messager.progress('close');
			alert(data.msg);
			if(data.status==1){
				window.location.reload();
			}
		}, "json");
	}
}

$(function(){
	$("#div_exams ul").eq(0).show();
	$("#examtypes_ul li").eq(0).addClass("current");	
	$("#examtypes_ul li").click(function(){
		$("#examtypes_ul li").removeClass("current");
		$(".etshover").hide();
		$("#ul_exam_"+$(this).attr("catalogid")).show();
	    $(this).addClass("current");
	});
	
	// 单击全选
	$(":checkbox[name='examtypeid']").click(function(){
		var parent = $(this).parent().parent();
		var isChecked = $(this).is(":checked");
		parent.find(":checkbox").each(function(){
			($(this)[0]).checked = isChecked;
		});
	});
	
	// 单击改变某个试卷状态
	$(":checkbox[name='examid']").click(function(){
		var parent = $(this).parent().parent();
		var isChecked = true;
		
		parent.find(":checkbox[name='examid']").each(function(){
			var isTmp = $(this).is(":checked");
			if(!isTmp){
				isChecked = false;
			}
		});
		
		var allCheckBox = parent.find(":checkbox[name='examtypeid']");
		(allCheckBox[0]).checked = isChecked;
	});
	
	var curkindclassid = "${kindclassId}";
	// 单击套餐
	$("#div_kind_class a.zk_tit").click(function(){
		var kindclassid = $(this).attr("kcid");
		if(curkindclassid != kindclassid){
			$("#fm_make_kcid").val(kindclassid);
			$("#fm_make").submit();
		}
	});
});	

// ========= 设置界面编辑器 begin ==========
function isCreateKE(){
	return true;
}

function createKEFunc(K,options){
	if(null != options && options && typeof(options) == "object"){
		return K.create('#max_textarea',options);
	}
	return K.create('#max_textarea');
}
// ========= 设置界面编辑器 end ==========
</script>
</body>
</html>