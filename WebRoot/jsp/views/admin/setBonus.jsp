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
<title>尚学在线后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="尚学后台管理">
<meta http-equiv="description" content="奖金设置">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
-->

<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>
</head>
<body class="body_bg" menuName="代理商管理">
	<!--头部-->
    <div class="header">
	   <jsp:include page="../../common/admin/header.jsp"></jsp:include>
	   <!-- 菜单 -->
	   <jsp:include page="../../common/admin/meun.jsp"></jsp:include>

            
            <div class="header_child">
            <div class="h_content">
              <div class="list">
                	<a href="admin/adminAgentManage">代理管理</a>
                	<a href="admin/agentTops">代理排行榜</a>
                	<a href="admin/setBonus" class="current">奖金设置</a>
               </div>
            </div>
            </div>
    </div>
    
    <!--内容-->
    <div class="w_content kcsz">
    	<div class="jxsz f_st">
        	<span>设置时间</span>
        	<select class="kc_sel_1"><option>选择时间</option></select>
        	<span>设置奖金提取比例</span>
        	<input type="text" class="kc_text_1" value=""/>
            <span>%（占成交额的比例）</span>
        	<span class="jxsz_span">奖金总额为：12000.00</span>
        </div>
        <div class="jzsz_h2">设置前10名奖金分配比例：</div>
        <table border="0" cellpadding="0" cellspacing="0" class="jzsz_table f_st">
        	<thead>
            	<tr>
                	<td width="200">名次</td>
                	<td width="600">奖金比例</td>
                	<td width="200">资金额</td>
                	<td style="text-align:right"><input type="button" class="jzsz_add" value="添加"/></td>
            	</tr>
            </thead>
            <tbody>
            	<tr>
                	<td>1</td>
                	<td><input type="text" class="kc_text_1"/>%<span>（占成交额的比例）</span></td>
                	<td><span>100.00元</span></td>
                	<td></td>
            	</tr>
                <tr>
                	<td>2</td>
                	<td><input type="text" class="kc_text_1"/>%<span>（占成交额的比例）</span></td>
                	<td><span>100.00元</span></td>
                	<td></td>
            	</tr>
                <tr>
                	<td>3</td>
                	<td><input type="text" class="kc_text_1"/>%<span>（占成交额的比例）</span></td>
                	<td><span>100.00元</span></td>
                	<td></td>
            	</tr>
                <tr>
                	<td>4</td>
                	<td><input type="text" class="kc_text_1"/>%<span>（占成交额的比例）</span></td>
                	<td><span>100.00元</span></td>
                	<td></td>
            	</tr>
                <tr>
                	<td>5</td>
                	<td><input type="text" class="kc_text_1"/>%<span>（占成交额的比例）</span></td>
                	<td><span>100.00元</span></td>
                	<td></td>
            	</tr>
                <tr>
                	<td>6</td>
                	<td><input type="text" class="kc_text_1"/>%<span>（占成交额的比例）</span></td>
                	<td><span>100.00元</span></td>
                	<td></td>
            	</tr><tr>
                	<td>7</td>
                	<td><input type="text" class="kc_text_1"/>%<span>（占成交额的比例）</span></td>
                	<td><span>100.00元</span></td>
                	<td></td>
            	</tr><tr>
                	<td>8</td>
                	<td><input type="text" class="kc_text_1"/>%<span>（占成交额的比例）</span></td>
                	<td><span>100.00元</span></td>
                	<td></td>
            	</tr><tr>
                	<td>9</td>
                	<td><input type="text" class="kc_text_1"/>%<span>（占成交额的比例）</span></td>
                	<td><span>100.00元</span></td>
                	<td></td>
            	</tr><tr>
                	<td>10</td>
                	<td><input type="text" class="kc_text_1"/>%<span>（占成交额的比例）</span></td>
                	<td><span>100.00元</span></td>
                	<td></td>
            	</tr>
                
                <tr>
                	<td colspan="4" style="text-align:center"><input type="button" value="确定提交" class="jzsz_btn"/></td>
            	</tr>
                
                
                
            
        	</tbody>
        
        </table>
        
        
    
    </div>
    <!--e内容-->
    
    
    
    
    
    
    
    
    


	

 
 
</body>
</html>
