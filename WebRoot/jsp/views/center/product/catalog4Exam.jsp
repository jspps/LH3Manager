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

<!--试卷目录-->
<c:forEach items="${list}" var="item">
	<div class="txsz_info f_st" style="margin-left: 20px;width: 96%;">
		<div class="txsz_bg" typecss="1" parsid="${item.id}">
			<div><h2>${item.serial }、${item.bigtypes }</h2></div>
			<h3>${item.title }</h3>
			<br />
			<c:if test="${item.catalogType != 7}">
			<span>题目总数：${item.num }题</span>
			<span>已录入：${item.numQues }道题</span> 
			<span>每题分值：${item.everyScore }分</span> 
			<span>本题分值：${item.everyScore * item.num}分</span>
			</c:if>
			<span class="fr">
				<a href="javascript:void(0);" onclick="click2DelExamCatalog(${item.id});">删除</a>
				<a href="javascript:void(0);" onclick="click2CancelModify('parsid',${item.id},1);">修改设置</a>
				<c:if test="${item.catalogType != 7 || item.gid > 0}">
					<a href="javascript:void(0);" onclick="click2AddQuestion(${item.examid},${item.id},${item.catalogType},0);">录入试题</a>
				</c:if>
			</span>
			<c:if test="${item.catalogType == 7}">
			<div class="txsz_info f_st">
				<form action="center/newExamCatalog" method="post" onsubmit="return click2NewExamCatalog4Child(this);">
				<input type="hidden" name="examid" value="${item.examid}"/>
				<input type="hidden" name="serial" value="七"/>
				<input type="hidden" name="bigtypes" value="案例分析题"/>
				<input type="hidden" name="isSubjective" value="false"/>
				<input type="hidden" name="parentid" value="${item.id}"/>
            	<h2>设置子类别</h2>
            	<div class="txsz_bg txsz_bg_1" style="padding-left:0px;">
                	<span class="span">
                	<select class="txsz_input txsz_input_1" name="gid"> 
                   		<option value="0">请选择题型</option>
                   		<option value="1">单选题</option>
                   		<option value="2">多选题</option>
                   		<option value="3">判断题</option>
                   		<option value="4">填空题</option>
                   		<option value="5">简答题</option>
                   		<option value="6">论述题</option>
                   	</select>
               	  </span>
               	  <br/>
               	  <div>
               	  	题干：<textarea style="resize:none; white-space:normal;width: 99%;" name="title" keval="1" id="ct_7_gid_txtid_${item.id}" ></textarea>
               	  </div>
                </div>
                <div class="txsz_bg" style="padding-left:0px;">
                	<span>题目总数：<input type="text" class="txsz_input txsz_input_2" name="num"/></span>
                	<span>每题分值：<input type="text" class="txsz_input txsz_input_2" name="everyScore"/></span>
                    <span><input type="submit" value="确定" style="width:60px; height:30px; line-height:30px;"></span>
                </div>
                </form>
                
                <c:forEach items="${item.listChild}" var="child">
                <div>
                	<h2>
		       			<c:if test="${child.gid == 1}">单选题</c:if>
		       			<c:if test="${child.gid == 2}">多选题</c:if>
		       			<c:if test="${child.gid == 3}">判断题</c:if>
		       			<c:if test="${child.gid == 4}">填空题</c:if>
		       			<c:if test="${child.gid == 5}">简答题</c:if>
		       			<c:if test="${child.gid == 6}">论述题</c:if>
		       		</h2>
                </div>
                <div class="txsz_bg" style="padding-left:0px; border:0px; padding-bottom:0px; height:30px;">
                    <span class="fr">
                    	<a href="javascript:void(0);" onclick="click2DelExamCatalog(${child.id});">删除</a>
						<a href="javascript:void(0);" onclick="click2CancelModify('clild',${child.id},1);">修改设置</a>
                    	<a href="javascript:void(0);" onclick="click2AddQuestion(${child.examid},${child.id},7,${child.gid});">录入试题</a>
                    </span>
                	<span class="span">${child.title }</span>
                </div>
                <div class="txsz_bg" style="padding-left:0px;" typecss="1" clild="${child.id}">
                	<span>题目总数：${child.num }题</span>
                    <span>已录入：${child.numQues }道题</span>
                    <span>每题分值：${child.everyScore }分</span>
                    <span>本题分值：${child.everyScore * child.num}分</span>
                </div>
                
                <div class="txsz_bg" style="display: none;padding-left:0px;" typecss="2" clild="${child.id}">
                	<textarea rows="2" cols="100" name="title" style="display: none;">${child.title }</textarea>
                	<c:if test="${child.gid != 1 && child.gid != 2 && child.gid != 3 }">
                	<div style="margin: 5px 0px">
                		<select name="isSubjective" class="subjective_sel">
                			<c:choose>
							<c:when test="${child.isSubjective }">
							<option value="1">主观题（如简答题，需学生填写答案并手动评分）</option>
							<option value="0">客观题（如选择题，系统可自动评分）</option>
							</c:when>
							<c:otherwise>
							<option value="0">客观题（如选择题，系统可自动评分）</option>
							<option value="1">主观题（如简答题，需学生填写答案并手动评分）</option>
							</c:otherwise>
							</c:choose>
                		</select>
		            </div>
		            </c:if>
		            
		            <div style="line-height: 30px; min-height: 30px;">
	                	<span>题目总数：<input value="${child.num }" name="allNum" style="width: 40px;" onchange="changeExamCatalog(this);"/>题</span>
	                    <span>已录入：${child.numQues }道题</span>
	                    <span>每题分值：<input value="${child.everyScore }" name="eveScore" style="width: 40px;" onchange="changeExamCatalog(this);"/>分</span>
	                    <span name="allScore">本题分值：${child.everyScore * child.num}分</span>
	                    
	                    <span class="fr">
							<a href="javascript:void(0);" onclick="click2CancelModify('clild',${child.id},2);">取消</a>
							<a href="javascript:void(0);" onclick="click2SureModify('clild',${child.id});">确定</a>
						</span>
					</div>
                </div>
            	</c:forEach>
            
            </div>
			</c:if>
		</div>
		<div class="txsz_bg" style="display: none" typecss="2" parsid="${item.id}">
			<div>
				<h2>
					<select class="txsz_input txsz_input_1"> 
						<option value="${item.serial }">${item.serial }</option>
						<option value="一">一</option>
						<option value="二">二</option>
						<option value="三">三</option>
						<option value="四">四</option>
						<option value="五">五</option>
						<option value="六">六</option>
						<option value="七">七</option>
						<option value="八">八</option>
						<option value="九">九</option>
						<option value="十">十</option>
						<option value="十一">十一</option>
						<option value="十二">十二</option>
						<option value="十三">十三</option>
					</select>、${item.bigtypes }
				</h2>
			</div>
			<div style="margin: 5px 0px"><textarea rows="2" cols="100" name="title" style="resize:none;">${item.title }</textarea></div>
			<c:if test="${item.catalogType != 1 && item.catalogType != 2 && item.catalogType != 3 }">
			<div style="margin: 5px 0px">
				<select name="isSubjective" class="subjective_sel">
           			<c:choose>
					<c:when test="${item.isSubjective }">
					<option value="1">主观题（如简答题，需学生填写答案并手动评分）</option>
					<option value="0">客观题（如选择题，系统可自动评分）</option>
					</c:when>
					<c:otherwise>
					<option value="0">客观题（如选择题，系统可自动评分）</option>
					<option value="1">主观题（如简答题，需学生填写答案并手动评分）</option>
					</c:otherwise>
					</c:choose>
           		</select>
            </div>
            </c:if>
            <div style="line-height: 30px; min-height: 30px;">
            	<c:if test="${item.catalogType != 7}">
				<span>题目总数：<input value="${item.num }" name="allNum" style="width: 40px;" onchange="changeExamCatalog(this);"/>题</span>
				<span>已录入：${item.numQues }道题</span> 
				<span>每题分值：<input value="${item.everyScore }" name="eveScore" style="width: 40px;" onchange="changeExamCatalog(this);"/>分</span> 
				<span name="allScore">本题分值：${item.everyScore * item.num}分</span>
				</c:if>
				
				<span class="fr">
					<a href="javascript:void(0);" onclick="click2CancelModify('parsid',${item.id},2);">取消</a>
					<a href="javascript:void(0);" onclick="click2SureModify('parsid',${item.id});">确定</a>
				</span>
			</div>
		</div>
	</div>
</c:forEach>

<form id="fm_addquesion" action="" method="post">
	<input type="hidden" name="examid" value="0" id="fm_add_examid"/>
	<input type="hidden" name="examcatalogid" value="0" id="fm_add_examcatalogid"/>
	<input type="hidden" name="type" value="0" id="fm_add_type"/>
</form>

<script type="text/javascript">
	function click2DelExamCatalog(examcatalogid){
		var url = '<%=basePath%>'+"center/delExamCatalog";
		var data = {};
		data.examcatalogid = examcatalogid;
		var callFun = function(backData){
			if(backData && backData.status){
				alert(backData.msg);
				if(backData.status == 1){
					getExamCatalogs(true);
				};
			}
		};
		$.post(url,data,callFun,"json");
	};
	
	function click2CancelModify(attr,parsid,typeCss){
		if(!attr || attr == "")
			attr = "parsid";
		$("div["+attr+"='"+parsid+"']").each(function(){
			var attr = $(this).attr("typecss");
			if(attr == typeCss){
				$(this).hide();
			}else{
				$(this).show();
			};
		});
	};
	
	function changeExamCatalog(that){
		var jqDiv = $(that).parent().parent();
		var allNum = jqDiv.find("input[name='allNum']").val();
		var eveScore = jqDiv.find("input[name='eveScore']").val();
		jqDiv.find("span[name='allScore']").html("本题分值：" + (allNum * eveScore)+"分");
	};
	
	function click2SureModify(attr,examcatalogid){
		if(!attr || attr == "")
			attr = "parsid";
		var jqDiv = $("div[typecss='2']["+attr+"='"+examcatalogid+"']");
		var serial = jqDiv.find("select.txsz_input_1").val();
		var allNum = jqDiv.find("input[name='allNum']").val();
		var eveScore = jqDiv.find("input[name='eveScore']").val();
		var jqTxt = jqDiv.find("textarea");
		var txtId = jqTxt.attr("id");
		syncKEOne(txtId);
		var title = jqTxt.val();
		var isSubjective = -1;
		// jqDiv.find(":radio").each(function(){
			// var isBl = $(this).is(":checked");
			// if(isBl){
				// isSubjective = $(this).val();
			// };
		// });
		isSubjective = jqDiv.find("select.subjective_sel option:selected").val();
		isSubjective = parseInt(isSubjective,10);
		if(isNaN(isSubjective)){
			isSubjective = -1;
		};
		
		var url = '<%=basePath%>'+"center/modifyExamCatalog";
		var data = {};
		data.serial = serial;
		data.examcatalogid = examcatalogid;
		data.everyScore = eveScore;
		data.num = allNum;
		data.title = title;
		data.isSubjective = isSubjective;
		
		var callFun = function(backData){
			if(backData && backData.status){
				alert(backData.msg);
				if(backData.status == 1){
					getExamCatalogs(true);
				};
			}
		};
		$.post(url,data,callFun,"json");
	};
	
	function click2AddQuestion(examid,examcatalogid,catalogType,gid){
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
	
	// 添加子分类
	function click2NewExamCatalog4Child(that){
		var jqForm = $(that);
		var parentid = jqForm.children("input:hidden[name='parentid']").val();
		var titleId = "ct_7_gid_txtid_"+parentid;
		syncKEOne(titleId);
		
	    var url = '<%=basePath%>'+jqForm.attr("action");
		var data = jqForm.serialize();
		var callFun = function(backData){
			jQuery.messager.progress('close');
			if(backData && backData.status){
				alert(backData.msg);
				if(backData.status == 1){	
					getExamCatalogs();
				};
			}
		};
		
		jQuery.messager.progress({
				title:'请等待',
				text:'提交数据中...',
				interval:700
		});
		$.post(url,data,callFun,"json");
		return false;
	};
	
	// ========= 设置界面编辑器 begin ==========
	function isCreateKE(){
		var jqTxts = $("textarea[keval]");
		return jqTxts.length > 0;
	}
	
	function syncKEOne(id){
		var tmp = tmpKEObj[id];
		if(tmp){
			tmp.sync();
		}
	}
	
	function createKEOne(K,options,id){
		if(null != options && options && typeof(options) == "object"){
			return K.create('#'+id,options);
		}
		return K.create('#'+id);
	}
	
	function createKEFunc(K,options){
		var jqTxts = $("textarea[keval]");
		var ret = null;
		jqTxts.each(function(ind){
			var id = $(this).attr("id");
			tmpKEObj[id] = createKEOne(K,options,id);
			ret = tmpKEObj[id]; 
		});
		return ret;
	}
	// ========= 设置界面编辑器 end ==========
</script>
<!--试卷目录 end -->