<%@page import="com.bowlong.lang.NumEx"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String num = request.getParameter("num");
	int vNum = NumEx.stringToInt(num,0) + 1;
	
	String strType = request.getParameter("type");
	int type1_7 = NumEx.stringToInt(strType,1);
	
	String strGid = request.getParameter("gid");
	int gid0_6 = NumEx.stringToInt(strGid,1);
	
	String imgPic = request.getParameter("imgPic");
	String voiceurl = request.getParameter("voiceurl");
	String videourl = request.getParameter("videourl");
	String content = request.getParameter("content");
	String right_2 = request.getParameter("right_2");
	if(type1_7 == 2 || (type1_7 == 7 && gid0_6 == 2)){
		right_2 = right_2.replaceAll(",", "");
	}else if(type1_7 == 3 || (type1_7 == 7 && gid0_6 == 3)){
		right_2 = "A".equals(right_2) ? "对" : "错";
	}
	String position = request.getParameter("position");
	String analyse = request.getParameter("analyse");
%> 

<div class="yp_div">
<%if(voiceurl != null && !voiceurl.isEmpty()){%>
    <a href="<%=voiceurl%>" class="a_yp">音频</a>
<%}%>

<%if(videourl != null && !videourl.isEmpty()){%>
    <a href="<%=videourl%>" class="a_sp">视频</a>
<%}%>
</div>
<div style="word-wrap:break-word;">
<pre>
<%=vNum%>.<%=content%>
</pre>
<%if(imgPic != null && !imgPic.isEmpty()){%>
<br/>
<img src="<%=imgPic%>" />
<%}%>
</div>
<% if(!(type1_7 == 7 && gid0_6 == 0)){ %>
<div>    
	<p class="span">
		<span>正确答案：【<%=right_2%>】</span>
		<span>教材位置：<%=position%></span>
		<span><a href="javascript:void(0)"></a></span>
	</p>
</div>
<div>
	<p>本题解析：</p>
	<div class="btjx"> <%=analyse%></div>
</div>
<%}%>