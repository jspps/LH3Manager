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
<title>成交情况</title>
<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>



</head>
<body class="body_bg" menuName="成交情况">
 	<!--头部-->
	<jsp:include page="../../common/agent/header.jsp"></jsp:include>
    <!--内容-->
    
    <div class="j_content">
    <!--菜单-->
    <jsp:include page="../../common/agent/meun.jsp"></jsp:include>
    <div class="user_content">
        <div class="uc_content" style="overflow:visible">
        <div class="kcszbtn">
        	<input type="button" value="导出数据" class="btn">
        </div>
        
    	<div class="div_table f_st">
        
        	<table cellspacing="0" cellpadding="0" border="0" class="table">
        		<thead>
                <tr>
        			<th width="150">时间</th>
                    <th width="100">购买用户</th>
                    <th width="100">IP所在地</th>
                    <th width="">购买课程</th>
                    <th width="85">成交价</th>
                    <th width="85">题库提成</th>
                    <th width="85">错误率</th>
                </tr>
        		</thead>
                <tbody>
                	<tr>
                    	<td>2012-12-12 12:12</td>
                    	<td>13696314562</td>
                    	<td>四川省成都市</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td>100</td>
                        <td>10%</td>
                        <td>10%</td>
                    </tr>
                    <tr>
                    	<td>2012-12-12 12:12</td>
                    	<td>13696314562</td>
                    	<td>四川省成都市</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td>100</td>
                        <td>10%</td>
                        <td>10%</td>
                    </tr>
                    <tr>
                    	<td>2012-12-12 12:12</td>
                    	<td>13696314562</td>
                    	<td>四川省成都市</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td>100</td>
                        <td>10%</td>
                        <td>10%</td>
                    </tr>
                    <tr>
                    	<td>2012-12-12 12:12</td>
                    	<td>13696314562</td>
                    	<td>四川省成都市</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td>100</td>
                        <td>10%</td>
                        <td>10%</td>
                    </tr>
                    <tr>
                    	<td>2012-12-12 12:12</td>
                    	<td>13696314562</td>
                    	<td>四川省成都市</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td>100</td>
                        <td>10%</td>
                        <td>10%</td>
                    </tr>
                    <tr>
                    	<td>2012-12-12 12:12</td>
                    	<td>13696314562</td>
                    	<td>四川省成都市</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td>100</td>
                        <td>10%</td>
                        <td>10%</td>
                    </tr>
                    <tr>
                    	<td>2012-12-12 12:12</td>
                    	<td>13696314562</td>
                    	<td>四川省成都市</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td>100</td>
                        <td>10%</td>
                        <td>10%</td>
                    </tr><tr>
                    	<td>2012-12-12 12:12</td>
                    	<td>13696314562</td>
                    	<td>四川省成都市</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td>100</td>
                        <td>10%</td>
                        <td>10%</td>
                    </tr><tr>
                    	<td>2012-12-12 12:12</td>
                    	<td>13696314562</td>
                    	<td>四川省成都市</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td>100</td>
                        <td>10%</td>
                        <td>10%</td>
                    </tr>
                    <tr>
                    	<td>2012-12-12 12:12</td>
                    	<td>13696314562</td>
                    	<td>四川省成都市</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td>100</td>
                        <td>10%</td>
                        <td>10%</td>
                    </tr>
                 
                    
                  </tbody>
                   <tfoot>
                    <tr>
                    	<th>合计</th>
                    	<td></td>
                    	<td></td>
                    	<td></td>
                    	<td></td>
                    	<td></td>
                    	<td><strong class="c_ff7900">100</strong></td>
                    </tr>
                    
                    </tfoot>
                    
                    
                
                
                
                
                
        	</table>
    	</div>
        
        <div class="page">
        
        	<a class="fy" href="">&lt;</a>
            <a href="javascript:void(0)">1</a>
            <a href="javascript:void(0)">2</a>
            <a href="javascript:void(0)">3</a>
            <a href="javascript:void(0)">4</a>
            <a href="javascript:void(0)">5</a>
            <span>6</span>
            <a href="javascript:void(0)">8</a>
            <a href="javascript:void(0)">9</a>
            <a href="javascript:void(0)">...</a>
        	<a class="fy" href="">&gt;</a>
        
        </div>
          </div> 
    	</div>
    
    </div>
    <i class="clear"></i>
    </div>
</body>
</html>