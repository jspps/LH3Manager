<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://page.bowlong.com/jsp/tags" prefix="p"%>

<!--产品目录-->
<c:choose>
<c:when test="${catalog4ProductType == 5 }">
<textarea name="cruces_val" cols="" rows="" class="kcszbtn_wby" style="resize:none;width: 99%;" id="cruces_val">
	${product.cruces }
</textarea>
<!-- 
<span style="margin-right:30px;">
	<input type="button" value="取消" style="width:100px; height:30px; margin-top:20px;">
</span>
 -->
<span style="float:right;">
	<input type="button" value="提交" style="width:100px; height:30px; margin-top:20px;" onclick="click2ChangeKnowledge();">
</span>
<script type="text/javascript">
	function click2ChangeKnowledge(){
		var url = '<%=basePath%>'+"center/changeCruces";
		var data = {};
		data.cruces = $("#cruces_val").val();
		data.productid = "${product.id}";
		var callFun = function(backData){
			if(backData && backData.status){
				alert(backData.msg);
			}
		};
		$.post(url,data,callFun,"json");
	};
</script>
</c:when>
<c:otherwise>
<div class="kcszbtn_list">
    <span style="float:left; margin-right:30px;">设置购买金额：<input type="text" id="buymoney" class="txsz_input txsz_input_1" value="${productCatalog != null ? productCatalog.buymoney : 0 }"/></span>
	<span style="float:left; margin-right:30px;">设置所需多少个朋友购买：<input type="text" id="friend" class="txsz_input txsz_input_1" value="${productCatalog != null ? productCatalog.friend : 0 }"/></span>
	<span style="float:left; margin-right:30px;">设置包含考币数量：<input type="text" id="kbi" class="txsz_input txsz_input_1" value="${productCatalog != null ? productCatalog.kbi : 0 }"/></span>
	<span style="float:left;"><input type="button" value="确定" style="width:80px; height:30px;" onclick="click2ChangeCatalog();" /></span>
	<script type="text/javascript">
		function click2ChangeCatalog(){
			var url = '<%=basePath%>'+"center/changeProCatalog";
			var data = {};
			data.catalogid = "${productCatalog != null ? productCatalog.id : 0 }";
			data.productid = "${product.id}";
			data.extypeid = "${catalog4ProductType}";
			data.buymoney = $("#buymoney").val();
			data.friend = $("#friend").val();
			data.kbi = $("#kbi").val();
			var callFun = function(backData){
				if(backData && backData.status){
					alert(backData.msg);
					if(backData.status == 1){
						//window.location.reload();
						getHtml4Catalog(data.extypeid);
					};
				}
			};
			$.post(url,data,callFun,"json");
		};
	</script>
</div>
<c:if test="${productCatalog != null }">
<div class="kcszbtn_list_div">
	<span style="float:left;"><input type="text" id="new_exam_name" class="txsz_input" style="width:700px;" value="请输入试卷名称" onfocus="this.value=''" onblur="if(this.value=='') this.value='请输入试卷名称';"/></span>
	<span style="float:left; padding-left:30px;"><input type="button" value="添加" style="width:80px; height:30px;" onclick="click2AddNewExam();" /></span>
</div>
<div id="div_exams">
	<c:forEach items="${pageEnt.listPages }" var="item">
	<div class="kcszbtn_list_div" examid="${item.id }">
		<div typeCss="1">
			<span style="float:left;width:700px; text-align:left; overflow:hidden;">${item.name }</span>
			<span style="float:left;">${item.numQues}道题</span>
			<span style="float:right;">
				<a href="javascript:void(0);" onclick="click2ModifyExamStatus(${item.id },1);">修改标题</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="click2DelExam(${item.id });">删除试卷</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="click2ExamCatalog(${item.id });">编辑试卷</a>
			</span>
		</div>
		<div typeCss="2" style="display: none; text-align: left;">
			<input type="text" value="${item.name }" class="txsz_input" style="width:700px;"/>
			<input type="button" value="修改" onclick="click2ModifyExam(${item.id },this);" style="margin-left: 27px;width:80px; height:30px;"/>
			<input type="button" value="取消" onclick="click2ModifyExamStatus(${item.id },2);" style="margin-left: 50px;width:80px; height:30px;"/>
		</div>
	</div>
	</c:forEach>
	<i class="clear" style="height : 10px;"></i>
	<p:pageTag name="pageEnt" action="center/catalog4Product?productid=${product.id}&ctype=${catalog4ProductType}" wrapid="div_cruces"/>
	<script type="text/javascript">
		function click2ModifyExam(examid,curThis){
			var name = $(curThis).prev().val();
			var url = '<%=basePath%>'+"center/changeExamName";
			var data = {};
			data.examid = examid;
			data.examname = name;
			var callFun = function(backData){
				if(backData && backData.status){
					alert(backData.msg);
					if(backData.status == 1){
						//window.location.reload();
						getHtml4Catalog("${catalog4ProductType}");
					};
				}
			};
			$.post(url,data,callFun,"json");
		};
		function click2ModifyExamStatus(examid,tpHide){
			$("div[examid='"+examid+"']").children().each(function(){
				var attr = $(this).attr("typeCss");
				if(attr == tpHide){
					$(this).hide();
				}else{
					$(this).show();
				}
			});
		};
		
		function click2DelExam(examid){
			var url = "center/delExam";
			var data = {};
			data.examid = examid;
			var callFun = function(backData){
				if(backData && backData.status){
					alert(backData.msg);
					if(backData.status == 1){
						//window.location.reload();
						getHtml4Catalog("${catalog4ProductType}");
					};
				}
			};
			$.post(url,data,callFun,"json");
		};
		
		function click2ExamCatalog(examid){
			window.location.href = '<%=basePath%>'+"center/go2ExamCatalog?examid="+examid;
		};
		
		function click2AddNewExam(){
			var url = '<%=basePath%>'+"center/newExam";
			var data = {};
			data.catalogid = "${productCatalog.id}";
			data.newname = $("#new_exam_name").val();
			if(data.newname == "请输入试卷名称"){
				data.newname = "";
			}
			
			var callFun = function(backData){
				if(backData && backData.status){
					alert(backData.msg);
					if(backData.status == 1){
						//window.location.reload();
						getHtml4Catalog("${catalog4ProductType}");
					};
				}
			};
			$.post(url,data,callFun,"json");
		};
	</script>
	<i class="clear" style="height : 10px;"></i>
</div>
</c:if>
</c:otherwise>
</c:choose>
<i class="clear"></i>