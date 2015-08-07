<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String uid = request.getParameter("uid");
	String pwd = request.getParameter("pwd");
	if (uid == null)
		uid = "";
	if (pwd == null)
		pwd = "";
	if (uid != "" || pwd != "") {
		out.println("<div style='width:100%;height:40px;line-height:40px;background-color:#f00;color:white;text-align:center;'>");
		if (uid == "")
			out.println(" 帐号不能为空 ");
		else if (pwd == "")
			out.println(" 密码不能为空 ");
		else
			out.println("帐号或密码错误");
		out.println("</div>");
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>登录-校友信息管理系统</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<div class="session-info">
		Session id:<%=session.getId()%><br>
		role:${roletype}
	</div>
	<div class="login-size col-xs-12 col-sm-6 col-sm-offset-3">
		<div class="top-border">校友信息管理系统--登录</div>
		<form action="Public/login" id="login-form" method="post">
			<label>用户名：</label>
			<input name="uid" class="form-input" type="text" id="uid" value="${uid }">
			<a href="Register">注册账号</a>
			<br>
			<label>密&emsp;码：</label>
			<input name="pwd" class="form-input" type="password" id="pwd" value="${pwd }">
			<a href="PwdFind">找回密码</a>
			<br>
			<label>验证码：</label>
			<input class="form-input-code" type="text" id="vcode">
			<img id="verfy-code" class="code-img" onclick="ChangeVerfy();" src="VerifyCodeServlet">
			<a href="javascript:void(0);" onclick="ChangeVerfy();">换一张</a>
			<br>
			<input class="login-submit" type="submit" onclick="submitForm();" value="登录" />
		</form>
	</div>
	<script type="text/javascript">
		function ChangeVerfy() {
			var verfy = document.getElementById("verfy-code");
			verfy.src = "VerifyCodeServlet?a=" + new Date().getTime();
		}
		function submitForm() {
			document.getElementById("login-form").submit();
		}
	</script>
</body>
</html>
