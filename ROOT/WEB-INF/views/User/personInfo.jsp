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

<link rel="stylesheet" type="text/css" href="css/admin.css">
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="css/personInfo.css">

</head>
<body>
	<div class="table-responsive">
		<table class="table table-bordered table-hover">
			<thead class="row">
				<tr>
					<th class="col-md-1" colspan="4">基本信息</th>
				</tr>
			</thead>
			<tbody class="row">
				<tr>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">姓名：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入姓名" readonly>
					</td>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">学号：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入学号" readonly>
					</td>
				</tr>
				<tr>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">学院：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入学院" readonly>
					</td>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">专业：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入专业" readonly>
					</td>
				</tr>
				<tr>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">班级：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入班级" readonly>
					</td>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">性别：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入性别" readonly>
					</td>
				</tr>
				<tr>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">入学时间：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入入学时间" readonly>
					</td>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">毕业时间：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入毕业时间" readonly>
					</td>
				</tr>
				<tr>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">学历：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入学历" readonly>
					</td>
				</tr>
			</tbody>
		</table>

		<table class="table table-bordered table-hover">
			<thead class="row">
				<tr>
					<th class="col-md-1" colspan="4">工作情况</th>
				</tr>
			</thead>
			<tbody class="row">
				<tr>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">工作省份：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入工作省份" readonly>
					</td>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">工作市：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入工作市" readonly>
					</td>
				</tr>
				<tr>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">工作地区：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入工作地区" readonly>
					</td>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">工作单位：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入工作单位" readonly>
					</td>
				</tr>
				<tr>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">职称：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入职称" readonly>
					</td>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">职务：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入职务" readonly>
					</td>
				</tr>
				<tr>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">行业：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入行业" readonly>
					</td>
				</tr>
			</tbody>
		</table>

		<table class="table table-bordered table-hover">
			<thead class="row">
				<tr>
					<th class="col-md-1" colspan="4">联系方式</th>
				</tr>
			</thead>
			<tbody class="row">
				<tr>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">联系电话：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入联系电话" readonly>
					</td>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">固定电话：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入固定电话" readonly>
					</td>
				</tr>
				<tr>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">通讯地址：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入通讯地址" readonly>
					</td>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">QQ号：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入工作单位" readonly>
					</td>
				</tr>
				<tr>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">邮箱：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入邮箱" readonly>
					</td>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">邮编：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入邮编" readonly>
					</td>
			</tbody>
		</table>

		<table class="table table-bordered table-hover">
			<thead class="row">
				<tr>
					<th class="col-md-1" colspan="4">其他情况</th>
				</tr>
			</thead>
			<tbody class="row">
				<tr>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">所属校友总会：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入所属校友总会" readonly>
					</td>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">校友总会职务：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入校友总会职务" readonly>
					</td>
				</tr>
				<tr>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">所属校友分会：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="请输入所属校友分会" readonly>
					</td>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">校友分会职务：</td>
					<td class="col-md-3 col-md-5">
						<input class="form-control" type="text" placeholder="校友分会职务" readonly>
					</td>
				</tr>
				<tr>
					<td class="col-md-3 col-md-1" style="text-align:right;height:30px;line-height:30px">备注：</td>
					<td class="col-md-1 " colspan="4">
						<input class="form-control" type="text" placeholder="请输入备注" readonly>
					</td>
			</tbody>
		</table>
	</div>
	<div style="text-align:center">
		<button type="button" class="btn btn-primary active" id="myButton">修改</button>
		<button type="submit" class="btn btn-primary active" id="mySubmit">提交</button>
	</div>
</body>
</html>
