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
<title>代理商管理后台信息</title>
<jsp:include page="../../common/common_css.jsp"></jsp:include>
<jsp:include page="../../common/common_easyui.jsp"></jsp:include>


</head>
<body class="body_bg" menuName="基本信息">
	<!--头部-->
	<jsp:include page="../../common/agent/header.jsp"></jsp:include>
    <!--内容-->
    
    <div class="j_content">
    <!--菜单-->
    <jsp:include page="../../common/agent/meun.jsp"></jsp:include>
    
    
    <div class="user_content">
    	<div style="width: 100%;color: white;background-color: rgba(154, 176, 192, 255);line-height: 40px;text-align: center;font-size: 16px;height: 40px;">
    		<c:choose>
    		<c:when test="${isRnk}"><span>今年销售总量：${money}元,在代理商中排名：第${indexs}名</span></c:when>
    		<c:otherwise><span>您尚未进行排行!</span></c:otherwise>
    		</c:choose>
    	</div>
    	<div class="user_info f_st">
        	<div class="user_img fr">
            	<span class="span_img"><img src="jsp/imgs/test.jpg" width="147" height="177"/></span>
            	<P>
            		审核状态：
            		<c:choose>
			 		<c:when test="${agent.examineStatus == 2}">不通过</c:when>
			 		<c:when test="${agent.examineStatus == 3}">已通过</c:when>
			 		<c:otherwise>审核中</c:otherwise>
			 		</c:choose>
				</P>
                 <a href="javascript:void(0);" id="btn_no"  onclick="qxuser();" class="cszf" style="display: none">取消修改</a>
                <a href="javascript:void(0);"  id="btn_ok" onclick="operating();" class="xgxx" style="display: none">确定修改</a>
                <a href="javascript:void(0);" id="btn_mod" onclick="moduser();" class="xgxx">修改信息</a>
            </div>
        
       	    <table id="show_userinfo">
       	    	<tr><th>联系人：</th><td>${agent.uname}</td></tr>
            	<!-- <tr><th>类型：</th><td>个人</td></tr> -->
            	<tr><th>优势：</th><td>${agent.goodness}</td></tr>
            	<tr><th>登录账号：</th><td>${agent.accountFkAccountid.lgid}</td></tr>
            	<tr><th>推荐号（联系电话）：</th><td>${agent.accountFkAccountid.phone}</td></tr>
            	<tr><th>地址：</th><td>${agent.province} ${agent.city} ${agent.seat}  </td></tr>
            	<tr><th>qq邮箱：</th><td>${agent.qq}</td></tr>
            	<tr><th>有效期至：</th><td>${agent.endtime}</td></tr>
            	<tr><th>支付宝账号：</th><td>${agent.alipay} </td></tr>
            </table>
              <form action="agent/modAgentInfo" id="modLearnhubform">
                 <table style="display: none" id="mod_userinfo">
	            	<tr><th>联系人：</th><td><input name="uname" value="${agent.uname}" nullmsg="联系人不能为空"></td></tr>
	            	<!-- <tr><th>类型：</th><td>个人</td></tr> -->
	            	<tr><th>优势：</th><td><input name="goodness" value="${agent.goodness}" ></td></tr>
	            	<tr><th>登录账号：</th><td><input name="account_lgid" value="${agent.accountFkAccountid.lgid}" nullmsg="登录帐号不能为空"></td></tr>
	            	<tr><th>推荐号（联系电话）：</th><td><input name="account_phone" value="${agent.accountFkAccountid.phone}" zz="电话" nullmsg="联系电话不能为空"></td></tr>
	            	<tr><th>省： </th>
            	   <td>
            	<input name="province" value="${agent.province}" nullmsg="省不能为空"> 
            	市： <input name="city" value="${agent.city}" nullmsg="市不能为空">
            	街道：<input name="seat" value="${agent.seat}" nullmsg="街道不能为空"></td></tr>
	            	<tr><th>qq邮箱：</th><td><input name="qq" value="${agent.qq}" ></td></tr>
	            	<tr><th>有效期至：</th><td>${agent.endtime}</td></tr>
	            	<tr><th>支付宝账号：</th><td>${agent.alipay} </td></tr>
               </table>
              </form>
            <i class="clear"></i>
           <c:choose>
           <c:when test="${agent.isVerifyAlipay }">
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
             <!-- 
             <h2 class="h2"><span>其他代理项目需求</span></h2>
       		 <div class="uc_p">为确保您能及时收到代理佣金，请测试，付款后如能返回支付宝账户0.90元，表示账户正常。如您的支付宝没有收到相应金额，请确认支付宝账号是否正确。剩余0.10元将累加至代理商奖励基金池。</div>
       		  -->
    	</div>
    
    </div>
    <i class="clear"></i>
</div>
<form action="testPay4Alipay" method="post" id="pay_4_test_alipay">
	<input type="hidden" name="type" value="2"/>
	<input type="hidden" name="id" value="${agent.agid}"/>
	<input type="hidden" name="money" value="1"/>
	<input type="hidden" name="name" value="${agent.uname}"/>
</form>
<script type="text/javascript">
function  moduser(){
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
function click2TestAlipay(){
	$("#pay_4_test_alipay").submit();
}
</script>    
</body>
</html>