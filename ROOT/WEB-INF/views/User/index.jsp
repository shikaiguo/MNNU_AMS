<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>My JSP 'personInfo.jsp' starting page</title>

<link rel="stylesheet" type="text/css" href="css/admin.css">
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="css/personInfo.css">
<style type="text/css">
</style>
</head>
<body>
	<div class="container">
		<div class="row head">
			<div class="col-xs-12">
				<img src="images/head.png" class="banner">
			</div>
			<div class="col-xs-12 col-lg-12 col-md-12 col-sm-12">
				<div class="nav">
					<div class="nav-name">
						${role }:${name }
						<a href="logout">注销</a>
					</div>
					<div class="menu-button glyphicon glyphicon-menu-hamburger"></div>
					<div class="menu">
						<div class="titem"></div>
						<div class="item">主页</div>
						<%
							if (request.getAttribute("role").toString().equals("校友")) {
						%>
						<div class="item"><a href="javascript:void(0);" onclick="modalShow('User/personInfo')">个人信息</a></div>
						<%
							}else{
						%>
						<div class="item"><a href="User/Query">查询校友</a></div>
						<%
							}
						%>
						<div class="item">校友园地</div>
					</div>
				</div>
			</div>
		</div>
		<div class="content row">
		<div class="col-xs-12">
		<pre>
Session id:<%=session.getId()%>
roletype:${roletype }
		
		
		
		
		
		
		
		
		</pre>
		</div>
		</div>
		<button class="modal-button" style="display:none" data-toggle="modal" data-target=".bs-example-modal-lg"></button>
		<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
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
	</script>
</body>
</html>
