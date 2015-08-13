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

<title>My JSP 'authorityAssign.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/admin.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
</head>

<body>
	<div class="btn-group">
		<a class="btn btn-danger dropdown-toggle" data-toggle="dropdown" href="#">
			 选择角色
			 <span class="caret"></span>
		</a>
		<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
			<li><a tabindex="-1" href="#">校友办</a></li>
			<li><a tabindex="-1" href="#">院长</a></li>
			<li><a tabindex="-1" href="#">地区总会</a></li>
			<li><a tabindex="-1" href="#">班级理事</a></li>
		</ul>
	</div>
	<div style="height:2%;"></div>
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<td>用户名</td>
				<td>查看权限</td>
				<td>读写权限</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>akjdh</td>
				<td>akdjks</td>
				<td>asjhajhxh</td>
			</tr>
		</tbody>
	</table>
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$('.dropdown-toggle').dropdown();
	</script>
</body>
</html>
