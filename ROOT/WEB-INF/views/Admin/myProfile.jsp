<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>个人帐号信息</title>
<style type="text/css">
.my-dialog .title {
	height: 50px;
	line-height: 50px;
	color: white;
	background-color: #a72b29;
	font-size: 20px;
	padding-left: 12px;
	padding-right: 12px;
}
.modal-content{
	overflow:hidden;
}
.my-dialog .content {
	padding: 12px;
}
.my-dialog input{
	width:auto;
}
</style>
</head>
<body>
	<div class="row">
		<div class="col-xs-12">
			<div class="my-dialog">
				<div class="title">个人信息${uid }</div>
				<div class="content">
					帐号：
					<input value="${uid }" readonly>
					<br>
					角色：
					<input value="${role }" readonly>
					<br>
					权限：
					<input value="${auth }" readonly>
					<br>
					<hr>
					<form action="/Admin/saveProfile" method="post">
						用户名：
						<input name="name" value="${name }">
						<br>
						原密码：
						<input id="oldpwd" name="oldpwd" type="password">
						<span style="color:red;"> *</span>
						<input id="updatePwd" type="button" value="修改密码">
						<br>
						<div style="display:none">
						新密码：
						<input id="newpwd"  name="newpwd" type="password" placeholder="如不需要更改密码，此处请留空"><span style="color:red;"> *</span>
						<br>
						确认新密码：
						<input id="newpwd2"  type="password" placeholder="如不需要更改密码，此处请留空"><span style="color:red;"> *</span>
						<br>
						</div>
						<input type="button" value="保存">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
