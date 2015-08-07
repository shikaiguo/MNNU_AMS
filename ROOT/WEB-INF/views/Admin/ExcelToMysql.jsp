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

.modal-content {
	overflow: hidden;
}

.my-dialog .content {
	padding: 12px;
}

.my-dialog input {
	width: auto;
}
</style>
</head>
<body>
	<div class="row">
		<div class="col-xs-12">
			<div class="my-dialog">
				<div class="title">Excel导入</div>
				<div class="content">
					<form id="etm" action="Admin/ExcelToMysql" enctype="multipart/form-data" method="post">
						<input type="file" name="excelFile">
						<input type="button" onclick="etm();" value="提交">
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function etm() {
			document.getElementById("etm").submit();
			document.getElementById("etm-block").innerHTML = "数据导入处理中……请勿关闭或刷新页面。";
		}
	</script>
</body>
</html>