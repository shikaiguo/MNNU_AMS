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
<style>
* {
	margin: 0;
	padding: 0;
}

.login-div {
	max-width: 540px;
	border: 1px solid #ccc;
	height: 276px;
	margin: auto;
	position: absolute !important;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	border-radius: 0 0 10px 10px;
	box-shadow: 0 4px 10px #ccc;
}

.title {
	width: 100%;
	height: 48px;
	background-color: #a72b29;
	margin: 0 auto;
	text-align: center;
	line-height: 48px;
	color: #fff;
}

.login-div form{
	margin: 0 auto;
	width: 320px;
	height: 230px;
}

.form-input {
	margin: 12px auto;
	width: 160px;
	height: 30px;
	text-align: left;
	line-height: 30px;
}

.form-input-code {
	margin: 12px auto;
	width: 80px;
	height: 30px;
	text-align: left;
	line-height: 30px;
	vertical-align: center;
}

.code-img {
	width: 73px;
	height: 30px;
	vertical-align: middle;
}

.login-submit {
	float: right;
	width: 100px;
	height: 30px;
	margin: 12px auto;
}
</style>
</head>
<body>
	<div class="login-div">
		<div class="title">校友信息管理系统--登录</div>
		<form action="Public/login" id="login-form" method="post" onsubmit="return checkInfo();">
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
			<img id="verify-code" class="code-img" onclick="ChangeVerify();" src="VerifyCodeServlet">
			<a href="javascript:;" onclick="ChangeVerfy();">换一张</a>
			<input class="login-submit" type="submit" onclick="submitForm();" value="登录" />
		</form>
	</div>
	<script type="text/javascript">
		function checkInfo() {
			var str = document.getElementById("uid").value;
			if (str == "") {
				alert("用户名不能为空");
				return false;
			}
			str = document.getElementById("pwd").value;
			if (str == "") {
				alert("密码不能为空");
				return false;
			}
			str = document.getElementById("vcode").value;
			if (str == "") {
				//alert("请填写验证码");
				//return false;
			}
			return true;
		}
		function ChangeVerify() {
			var verfy = document.getElementById("verify-code");
			verfy.src = "VerifyCodeServlet?a=" + new Date().getTime();
		}
	</script>
</body>
</html>
