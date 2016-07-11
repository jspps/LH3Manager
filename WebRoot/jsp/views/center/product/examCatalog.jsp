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
			
			<div id="exam_catalogs_alls"></div>
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
	
		var tmpKEObj = {};
		
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
			
			getExamCatalogs();
		});
	</script>
</body>
</html>