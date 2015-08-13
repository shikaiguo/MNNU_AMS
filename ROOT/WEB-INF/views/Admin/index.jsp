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
<meta charset="utf-8">
<title>Admin - MNNU-AMS</title>
<base href=" <%=basePath%>">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/admin.css">
</head>

<body>
	<div class="container">
		<!-- 页头 -->
		<div class="row header">
			<div class="col-xs-12">
				<img src="images/head.png" class="banner">
			</div>
			<div class="col-xs-12">
				<div class="nav">
					<div class="nav-name">
						${role }：${name } <a href="logout">注销</a>
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
									String role = request.getAttribute("role").toString();
									if (role.equals("超级管理员")) {
								%>
								<div>管理员帐号</div>
								<%
									}
								%>
								<div>用户帐号</div>
								<div onclick="changePage('Admin/authorityAssign')">权限分配</div>
								<div>
									<a href="javascript:" onclick="modalShow('Admin/myProfile')">个人信息</a>
								</div>
							</div>
						</div>
						<div class="item">
							校友管理
							<div class="pitem">
								<div>
									<a href="javascript:;"
										onclick="modalShow('Admin/ExcelToMysql')">校友信息导入导出</a>
								</div>
								<div onclick="changePage('Admin/query')">校友信息查询</div>
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
		<!-- 页面内容 -->
		<div style="height: 2%"></div>
		<div class="row" id="pageContent">
			<div class="col-xs-12 content">
				<pre>
Session id:<%=session.getId()%>
roletype:${roletype }

















</pre>
			</div>
		</div>
		<div style="height: 2%"></div>
		<button class="modal-button" style="display:none" data-toggle="modal"
			data-target=".bs-example-modal-lg"></button>
		<div class="modal fade bs-example-modal-lg" tabindex="-1"
			role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">...</div>
			</div>
		</div>
		<!-- 页脚 -->
		<div class="row footer">
			<div class="col-xs-12">
				<div class=" footer-span">CopyRight &copy; 闽南师范大学 2015</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/admin.js"></script>
	<script type="text/javascript">
		function modalShow(url) {
			$('.modal-content').load(url);
			$('.modal-button').click();
		}
		function changePage(url) {
			$("#pageContent").load(url);
		}
	</script>
</body>
</html>
