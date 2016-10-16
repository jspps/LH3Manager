<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://page.bowlong.com/jsp/tags" prefix="p"%>

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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<base href="<%=basePath%>"/>
<title>学习中心管理后台_题型设置</title>
<jsp:include page="../../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg1">
	<!--头部-->
	<jsp:include page="../../../common/center/header.jsp" />
	
	<!--内容-->
	<div class="j_content">
	    <!--菜单-->
		<jsp:include page="../../../common/center/meun.jsp" />
		<div class="user_content">
			<div class="user_list">
				<jsp:include page="cursor.jsp" />
				<div>
					<div class="txsz f_st">
		            	<h2 style="font-weight:bold;">第一项：试卷说明（可以不填，如章节练习） </h2>
		                <table cellpadding="0" cellspacing="0" border="0" class="txsz_table">
		            		<tr>
		                    	<th width="80">试卷题目：</th>
		                        <td colspan="3"><input type="text" class="txsz_input" value="${exam.name }" id="exam_name"/></td>
		                    </tr>
		                    <tr>
		                    	<th width="80">试卷说明：</th>
		                        <td colspan="3"><textarea class="txsz_text" style="resize:none;" id="exam_desc">${exam.descstr }</textarea></td>
		                    </tr>
		                    <tr>
		                    	<th width="80">考试时间：</th>
		                        <td><input type="text" class="txsz_input txsz_input_1" value="${exam.examtime }" id="exam_examtime"/><span class="txsz_span">&nbsp;分钟</span></td>
		                    	<th width="80">总分：</th>
		                        <td width="200"><input type="text" class="txsz_input txsz_input_1" value="${exam.score }" id="exam_score"/></td>
		                    </tr>
		                	 <tr>
		                        <td colspan="4"><div class="txsz_add"><a class="a_add" href="javascript:void(0)" onclick="click2ChangeExamInfo();">确定修改</a></div></td>
		                    </tr>
		                </table>
		            </div>
		            
		            <div class="txsz f_st">
		            	<h2 style="font-weight:bold;">第二项：题型设置  </h2>
		                <table cellpadding="0" cellspacing="0" border="0" class="txsz_table">
		            		<tr>
		                    	<th width="80">大题序号：</th>
		                        <td>
		                        	<select class="txsz_input txsz_input_1" id="exam_catalog_serial"> 
		                        		<option value="0">请选择题号</option>
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
		                        	</select>
		                        </td>
		                    </tr>
		                    <tr>
		                    	<th width="80">大题类型：</th>
		                    	<td>
		                        	<select class="txsz_input txsz_input_1" id="exam_catalog_bigtypes" onchange="chgJudgeBigtypes(this.value)">
		                        		<option value="0">请选择题型</option>
		                        		<option value="单选题">单选题</option>
		                        		<option value="多选题">多选题</option>
		                        		<option value="判断题">判断题</option>
		                        		<option value="填空题">填空题</option>
		                        		<option value="简答题">简答题</option>
		                        		<option value="论述题">论述题</option>
		                        		<option value="案例分析题">案例分析题</option>
		                        	</select>
		                        </td>
		                    </tr>
		                    <tr>
		                    	<th colspan="4" id="exam_catalog_isSubjective">
			                        <span style="float:left; padding-left:80px; margin-right:50px; margin-bottom:20px;">
			                        	<input name="exam_catalog_isSubjective" type="radio" checked="checked" value="0" />客观题（如选择题，系统可自动评分）
			                        </span>
			                        <span style="float:left; margin-bottom:20px;">
			                        	<input name="exam_catalog_isSubjective" type="radio" value="1" />主观题（如简答题，需学生填写答案并手动评分）
			                        </span>
		                        </th>
		                    </tr>
		                    <tr id = "exam_catalog_num">
		                    	<th colspan="4">
		                       		<div class="txsz_bg" style="float:left; border:0px; padding-left:15px;">
		                                <span style="float:left; margin-right:180px;">
		                                	题目总数：<input type="text" class="txsz_input txsz_input_2" id="exam_catalog_num"/>
		                                </span>
		                                <span style="float:left;">
		                                	每题分值：<input type="text" class="txsz_input txsz_input_2" id="exam_catalog_everyScore"/>
		                                </span>
		                                <span style="color:#8e8e8e; float:left;" id="exam_catalog_allScore">本题分值：0分</span> 
		                            </div>
		                        </th>
		                    </tr>
		                     <tr>
		                    	<th width="80">大题题干：</th>
		                        <td><textarea class="txsz_text" id="exam_catalog_title"></textarea></td>
		                    </tr>
		                     <tr>
		                    	<th width="80"></th>
		                        <td><div class="txsz_add"><a class="a_add" href="javascript:void(0)" onclick="click2NewExamCatalog()">添加</a></div></td>
		                    </tr>
		                </table>
		            </div>
		            <script type="text/javascript">
	                	function click2ChangeExamInfo(){
	                		var url = "center/changeExam";
							var data = {};
							data.examid = "${exam.id}";
							data.name = $("#exam_name").val();
							data.desc = $("#exam_desc").val();
							data.examtime = $("#exam_examtime").val();
							data.score = $("#exam_score").val();
							var callFun = function(backData){
								if(backData && backData.status){
									alert(backData.msg);
								}
							};
							$.post(url,data,callFun,"json");
						};
						
		            	function changeSerial(value){
		            		// onchange="changeSerial(this.value);"
		            		var jqBigtypes = $("#exam_catalog_bigtypes");
		            		jqBigtypes.empty();
		            		switch(value){
		            			case "一":
		            				jqBigtypes.html('<option value="单选题">单选题</option>');
		            				break;
		            			case "二":
		            				jqBigtypes.html('<option value="多选题">多选题</option>');
		            				break;
		            			case "三":
		            				jqBigtypes.html('<option value="判断题">判断题</option>');
		            				break;
		            			case "四":
		            				jqBigtypes.html('<option value="填空题">填空题</option>');
		            				break;
		            			case "五":
		            				jqBigtypes.html('<option value="简答题">简答题</option>');
		            				break;
		            			case "六":
		            				jqBigtypes.html('<option value="论述题">论述题</option>');
		            				break;
		            			default:
		            				jqBigtypes.html('<option value="0">请选择题型</option>');
		            				break;
		            		};
		            	};
		            	
		            	function click2NewExamCatalog(){
	                		var url = '<%=basePath%>'+"center/newExamCatalog";
							var data = {};
							data.examid = "${exam.id}";
							data.serial = $("#exam_catalog_serial").val();
							data.bigtypes = $("#exam_catalog_bigtypes").val();
							var radio = $("#exam_catalog_isSubjective input:radio:checked").val();
							data.isSubjective = radio == 1 ? true : false;
							var num = $("#exam_catalog_num").val();
							var score = $("#exam_catalog_everyScore").val();
							num = parseInt(num,10);
							score = parseInt(score,10);
							if(isNaN(num))
								num = 0;
							if(isNaN(score))
								score = 0;
							data.num = num;
							data.everyScore = score;
							data.title = $("#exam_catalog_title").val();
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
						};
						
						function reExcamCatalogAll(){
							window.location.reload();
						}
						
						function getExamCatalogs(isMsg){
							isMsg = !!isMsg;
	                		var url = '<%=basePath%>'+"center/examCatalogs";
							var data = {};
							data.examid = "${exam.id}";
							var callFun = function(backData){
								$("#exam_catalogs_alls").html(backData);
								jQuery.messager.progress('close');
							};
							if(isMsg){
								jQuery.messager.progress({
										title:'请等待',
										text:'提交数据中...',
										interval:700
								});
							}
							$.post(url,data,callFun);
						};
						
						function chgJudgeBigtypes(val){
							if("案例分析题" == val){
								$("#exam_catalog_num").hide();
							}else{
								$("#exam_catalog_num").show();
							}
						}
		            </script>
				</div>
			</div>
			
			<div id="exam_catalogs_alls">
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
			                </div>
			                <div class="txsz_bg" style="padding-left:0px; border:0px; padding-bottom:0px; min-height:30px;">
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
								// getExamCatalogs(true);
								reExcamCatalogAll();
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
								// getExamCatalogs(true);
								reExcamCatalogAll();
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
					var data = jqForm.serializeArray();
					var callFun = function(backData){
						jQuery.messager.progress('close');
						if(backData && backData.status){
							alert(backData.msg);
							if(backData.status == 1){	
								// getExamCatalogs();
								reExcamCatalogAll();
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
				
				var tmpKEObj = {};
				
				function syncKEOne(id){
					var tmp = tmpKEObj[id];
					if(tmp){
						tmp.sync('#'+id);
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
			
			</div>
			<i class="clear"></i>
			<div class="lrst_btn">
	    		<input type="button" value="设置试题销售套餐" class="lrbtn_2" onclick="click2SetKind();" />&nbsp;&nbsp;&nbsp;&nbsp;试题添加完毕后方可设置试题销售套餐！
	        </div>
			<i class="clear"></i>
		</div>
	</div>
	<form action="center/makeKind" method="post" id="fm_go2_makekind">
	   	<input type="hidden" value="${exam.productid}" name="productid" />
	   	<input type="hidden" value="1" name="kindclassId" />
   	</form>
	<script type="text/javascript">
		function click2SetKind(){
			$("#fm_go2_makekind").submit();
		};
		
		$(document).ready(function(){
			$("#exam_catalog_num").change(function(){
				var val = $(this).val();
				var score = $("#exam_catalog_everyScore").val();
				resum(val,score);
			});
			
			$("#exam_catalog_everyScore").change(function(){
				var score = $(this).val();
				var val = $("#exam_catalog_num").val();
				resum(val,score);
			});
			
			function resum(num,score){
				num = parseInt(num,10);
				score = parseInt(score,10);
				if(isNaN(num))
					num = 0;
				if(isNaN(score))
					score = 0;
				var sum = num * score;
				$("#exam_catalog_allScore").html("本题分值："+sum+"分");
			};
			
			// getExamCatalogs();
		});
	</script>
</body>
</html>