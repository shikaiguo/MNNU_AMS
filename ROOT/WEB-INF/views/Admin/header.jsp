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
<link rel="stylesheet" type="text/css" href="css/bootstrap-dialog.min.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">
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
					<!-- 用户信息 -->
					<div class="nav-name">
						&nbsp;&nbsp;${role }：${name }&nbsp;
						<span class="glyphicon glyphicon-chevron-down"> </span>
						&nbsp;
						<div class="pitem">
							<div>
								<a title="个人信息" href="javascript:" onclick="modalShow('Admin/myProfile')">个人信息</a>
							</div>
							<div>
								<a href="logout">注销</a>
							</div>
						</div>
					</div>
					<div class="menu-button glyphicon glyphicon-menu-hamburger"></div>
					<div class="menu">
						<div class="titem"></div>
						<div class="item">
							<a href="Admin/index">主页</a>
						</div>
						<div class="item">
							用户管理
							<div class="pitem">
								<%
									String role = session.getAttribute("role").toString();
									if (role.equals("超级管理员")) {
								%>
								<div>子管理员</div>
								<%
									}
								%>
								<div>用户</div>
								<div>
									<a href="Admin/authorityAssign">权限分配</a>
								</div>
							</div>
						</div>
						<div class="item">
							校友管理
							<div class="pitem">
								<div>
									<a href="Admin/import">校友信息导入导出</a>
								</div>
								<div>
									<a href="Admin/query">校友信息查询</a>
								</div>
								<div onclick="changePage('Admin/query2')">校友信息查询2</div>
								<div>校友信息维护</div>
								<div onclick="changePage('Admin/emailsMenage')">邮件推送</div>
							</div>
						</div>
						<div class="item" onclick="changePage('Admin/alumniStatistics')">校友统计</div>
						<div class="item">校友园地</div>
						<div class="item">
							系统维护
							<div class="pitem">
								<div>数据备份</div>
								<div>数据恢复</div>
								<div>日志管理</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row pageContent">
			<!-- end tag in footer -->