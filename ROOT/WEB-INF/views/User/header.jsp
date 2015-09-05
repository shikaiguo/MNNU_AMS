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
<title>${title}</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.structure.min.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.theme.min.css">
<link rel="stylesheet" type="text/css" href="jqGrid/themes/smoothness/jquery.ui.core.css">
<link rel="stylesheet" type="text/css" href="jqGrid/themes/smoothness/jquery.ui.theme.css">
<link rel="stylesheet" type="text/css" href="jqGrid/themes/ui.jqgrid.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
</head>
<body>
	<div class="container">
		<!-- end tag in footer -->
		<!-- 页头 -->
		<div class="row header">
			<div class="col-xs-12">
				<!-- 横幅 -->
				<img src="images/head.png" class="banner">
			</div>
			<div class="col-xs-12">
				<div class="nav">
					<div class="nav-name dropdown">
						<button class="dropdown-toggle" type="button" id="dropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							用户：${username}
							<span class="glyphicon glyphicon-chevron-down"> </span>
						</button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu">
							<li>
								<a id="update-pwd" href="javascript:;" onclick="modalShow('Public/updatePwd')" data-toggle="modal" data-target=".modal-bh">密码修改</a>
							</li>
							<li>
								<a href="logout">注销</a>
							</li>
						</ul>
					</div>
					<div class="menu-button glyphicon glyphicon-menu-hamburger"></div>
					<div class="menu">
						<div class="titem"></div>
						<div class="item">
							<a href="User/index">主页</a>
						</div>
						<div class="item">
							<a href="User/myProfile">个人信息</a>
						</div>
						<div class="item">
							<a href="User/index">查询校友</a>
						</div>
						<div class="item">
							<a href="User/index">校友园地</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row pageContent">
			<!-- end tag in footer -->