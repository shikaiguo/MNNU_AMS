<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<div class="col-xs-12">
	<div class="btn-group">
		<a class="btn btn-danger dropdown-toggle" data-toggle="dropdown" href="#">
			选择角色
			<span class="caret"></span>
		</a>
		<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
			<li>
				<a tabindex="1" href="#">校友办</a>
			</li>
			<li>
				<a tabindex="2" href="#">院长</a>
			</li>
			<li>
				<a tabindex="3" href="#">地区总会</a>
			</li>
			<li>
				<a tabindex="4" href="#">班级理事</a>
			</li>
		</ul>
	</div>
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
</div>
<jsp:include page="footer.jsp"></jsp:include>