<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- 级联选择课程 -->
<th>
<select name="departid" id="departid_th" onchange="changeDepart();">
	<option value="-1">全部类别</option>
	<c:forEach items="${adqdepartments}" var = "ent">
	<option value="${ent.did}">${ent.name}</option>
	</c:forEach>
</select>
</th>

<th>
<select name="nmMajor" id="nmMajor_th" onchange="changeMajor();">
	<option value="-1">专业</option>
</select>
</th>

<th>
<select name="nmLevel" id="nmLevel_th" onchange="changeLevel();">
	<option value="-1">层次</option>
</select>
</th>

<th>
<select name="nmSub" id="nmSub_th" onchange="changeSub();">
	<option value="-1">科目</option>
</select>
</th>

<th>
<select name="nmArea" id="nmArea_th">
	<option value="-1">考试范围</option>
</select>
</th>

<script type="text/javascript">
	function resetSelVal(type){
		if(type <= 1){
			$("#nmMajor_th").html('<option value="-1">专业</option>');
		}
		
		if(type <= 2){
			$("#nmLevel_th").html('<option value="-1">层次</option>');
		}
		
		if(type <= 3){
			$("#nmSub_th").html('<option value="-1">科目</option>');
		}
		
		if(type <= 4){
			$("#nmArea_th").html('<option value="-1">考试范围</option>');
		}
	}
	
	function changeDepart() {
		var val = $("#departid_th").val();
		resetSelVal(1);
		if(val == "-1"){
			return;
		}
		var url = '<%=basePath%>' + "getNmMajors";
		var data = {"departid":val};
		var callBackInFun = function(back){
			var list = back.data;
			if(list && list.length > 0){
				var lens = list.length;
				for(var i = 0; i < lens;i++){
					var mapMajor = list[i];
					$("#nmMajor_th").append($("<option>",{"text":mapMajor.nmMajor}));
				};
			};
		};
		$.post(url,data,callBackInFun,"json");
	};
	
	function changeMajor() {
		resetSelVal(2);
		var val = $("#departid_th").val();
		var nmMajor = $("#nmMajor_th").val();
		if(val == "-1" || nmMajor == "-1"){
			return;
		}
		
		var url = '<%=basePath%>' + "getNmLevels";
		var data = {"departid":val,"nmMajor":nmMajor};
		var callBackInFun = function(back){
			var names = back.data;
			if(names && names.length > 0){
				var lens = names.length;
				for(var i = 0; i < lens;i++){
					$("#nmLevel_th").append($("<option>",{"text":names[i]}));
				};
			};
		};
		$.post(url,data,callBackInFun,"json");
	};
	
	function changeLevel() {
		resetSelVal(3);
		if(val == -1){
			return;
		}
		
		var val = $("#departid_th").val();
		var nmMajor = $("#nmMajor_th").val();
		var nmLevel = $("#nmLevel_th").val();
		if(val == "-1" || nmMajor == "-1" || nmLevel == "-1"){
			return;
		}
		
		var url = '<%=basePath%>' + "getNmSubs";
		var data = {"departid":val,"nmMajor":nmMajor,"nmLevel":nmLevel};
		var callBackInFun = function(back){
			var names = back.data;
			if(names && names.length > 0){
				var lens = names.length;
				for(var i = 0; i < lens;i++){
					$("#nmSub_th").append($("<option>",{"text":names[i]}));
				};
			};
		};
		$.post(url,data,callBackInFun,"json");
	};
	
	function changeSub() {
		resetSelVal(4);
		if(val == -1){
			return;
		}
		
		var val = $("#departid_th").val();
		var nmMajor = $("#nmMajor_th").val();
		var nmLevel = $("#nmLevel_th").val();
		var nmSub = $("#nmSub_th").val();
		if(val == "-1" || nmMajor == "-1" || nmLevel == "-1" || nmSub == "-1"){
			return;
		}
		
		var url = '<%=basePath%>' + "getNmAreas";
		var data = {"departid":val,"nmMajor":nmMajor,"nmLevel":nmLevel,"nmSub":nmSub};
		var callBackInFun = function(back){
			var names = back.data;
			if(names && names.length > 0){
				var lens = names.length;
				for(var i = 0; i < lens;i++){
					$("#nmArea_th").append($("<option>",{"text":names[i]}));
				};
			};
		};
		$.post(url,data,callBackInFun,"json");
	};
</script>