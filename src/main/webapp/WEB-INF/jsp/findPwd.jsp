<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在此处插入标题</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Reg.css"/>
<script type="text/javascript">

</script>
</head>
<body>
<div align="center" >
	<jsp:include page="header.jsp"></jsp:include>
		<div style="background:url('${pageContext.request.contextPath}/img/BGfocus.jpg');width:100%;height:300px">
		<%
		String findPwdSuccess = (String)request.getSession().getAttribute("findPwdSuccess");         // 获取错误属性
		String findPwdMsg = (String)request.getSession().getAttribute("findPwdMsg");
		if(findPwdSuccess != null) {
		%>
			<script type="text/javascript" language="javascript">
				alert("<%=findPwdSuccess%>");  
			</script>              
			<%
			}else if(findPwdMsg!=null){
				%>
			<script type="text/javascript" language="javascript">	
				alert("<%=findPwdMsg%>");  
			</script> 
			<%     			
			}
			%>
		<h1>找回密码信息认证</h1>
		<form method="post" action="findPwdCheck">
			<label>请填写用户名:</label><input class="acc" type="text" name="name"><br><br>
			<label>&nbsp;请填写邮箱:</label><input class="acc" type="text" name="email"><br><br>
			<label>&nbsp;新&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;码:</label><input class="acc" type="password" name="password"><br><br>
			<label>确认新密码:</label><input class="acc" type="password" name="confirmpassword"><br><br>			
			<input class="submit" type="submit" name="Submit" value="确认">
			<input class="submit" type="reset" name="Reset" value="取消"><br><br>
		</form>
		</div>
	</div>
</body>
</html>