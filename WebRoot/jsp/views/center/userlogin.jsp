<%@page import="java.text.SimpleDateFormat"%>
<%
    pageContext.setAttribute("appPath", request.getContextPath());
%>
<script type="text/javascript">
		window.location.href = "${appPath}";
</script>