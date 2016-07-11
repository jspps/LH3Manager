<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/tags" prefix="p"%>
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
<title>错误反馈</title>
<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>

</head>
<body class="body_bg" menuName="错误反馈">
 	<!--头部-->
	<jsp:include page="../../common/agent/header.jsp"></jsp:include>
    <!--内容-->
    
    <div class="j_content">
    <!--菜单-->
    <jsp:include page="../../common/agent/meun.jsp"></jsp:include>
    <div class="user_content">
        <div class="uc_content" style="overflow:visible;">
    	<div class="div_table f_st div_table_2">
        
        	<table cellspacing="0" cellpadding="0" border="0" class="table">
        		<thead>
                <tr>
        			<th width="85">时间</th>
                    <th width="85">提交人</th>
                    <th width="25%">课程名称</th>
                    <th width="85">题目序号</th>
                    <th>主要问题</th>
                    <th width="55">操作</th>
                </tr>
        		</thead>
                <tbody>
                	<tr>
                    	<td>2012-12-12</td>
                    	<td>季大珠</td>
                    	<td>企业人力资源管理师一级基础理论章节练习</td>
                    	<td>232题</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td><a class="a color1" href="javascript:void(0)">修改</a></td>
                    </tr>
                    	<tr>
                    	<td>2012-12-12</td>
                    	<td>季大珠</td>
                    	<td>企业人力资源管理师一级基础理论章节练习</td>
                    	<td>232题</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td><a class="a color1" href="javascript:void(0)">修改</a></td>
                    </tr>
                    
                    	<tr>
                    	<td>2012-12-12</td>
                    	<td>季大珠</td>
                    	<td>企业人力资源管理师一级基础理论章节练习</td>
                    	<td>232题</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td><a class="a color1" href="javascript:void(0)">修改</a></td>
                    </tr>
                    
                    	<tr>
                    	<td>2012-12-12</td>
                    	<td>季大珠</td>
                    	<td>企业人力资源管理师一级基础理论章节练习</td>
                    	<td>232题</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td><a class="a color1" href="javascript:void(0)">修改</a></td>
                    </tr>
                    
                    
                    	<tr>
                    	<td>2012-12-12</td>
                    	<td>季大珠</td>
                    	<td>企业人力资源管理师一级基础理论章节练习</td>
                    	<td>232题</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td><a class="a color1" href="javascript:void(0)">修改</a></td>
                    </tr>
                    
                    	<tr>
                    	<td>2012-12-12</td>
                    	<td>季大珠</td>
                    	<td>企业人力资源管理师一级基础理论章节练习</td>
                    	<td>232题</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td><a class="a color1" href="javascript:void(0)">修改</a></td>
                    </tr>
                    
                    	<tr>
                    	<td>2012-12-12</td>
                    	<td>季大珠</td>
                    	<td>企业人力资源管理师一级基础理论章节练习</td>
                    	<td>232题</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td><a class="a color1" href="javascript:void(0)">修改</a></td>
                    </tr>
                    	<tr>
                    	<td>2012-12-12</td>
                    	<td>季大珠</td>
                    	<td>企业人力资源管理师一级基础理论章节练习</td>
                    	<td>232题</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td><a class="a color1" href="javascript:void(0)">修改</a></td>
                    </tr>
                    	<tr>
                    	<td>2012-12-12</td>
                    	<td>季大珠</td>
                    	<td>企业人力资源管理师一级基础理论章节练习</td>
                    	<td>232题</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td><a class="a color1" href="javascript:void(0)">修改</a></td>
                    </tr>
                    	<tr>
                    	<td>2012-12-12</td>
                    	<td>季大珠</td>
                    	<td>企业人力资源管理师一级基础理论章节练习</td>
                    	<td>232题</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td><a class="a color1" href="javascript:void(0)">修改</a></td>
                    </tr>
                    	<tr>
                    	<td>2012-12-12</td>
                    	<td>季大珠</td>
                    	<td>企业人力资源管理师一级基础理论章节练习</td>
                    	<td>232题</td>
                    	<td>企业人力资源管理师一级基础理论章节练习企业人力资源管理师一级基础理论章节练习</td>
                    	<td><a class="a color1" href="javascript:void(0)">修改</a></td>
                    </tr>
                    
                  </tbody>
                    <tfoot>
                    <tr>
                    	<td class="al" colspan="7">
                        	<span class="cufk_span">合计错误题目数：15个</span>
                            <span class="cufk_span">总题目数：1578个</span>
                            <span class="cufk_span">错误率：38%</span>
                        
                        </td>
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