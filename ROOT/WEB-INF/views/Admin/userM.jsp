<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>
<!-- 页面内容 -->
<div class="col-xs-12 content">
	<div class="btn-group amm" style="margin-bottom:3px">
		<button id="add_user" type="button" class="btn btn-bh" data-toggle="modal" data-target=".modal-bh">添加${m_type}</button>
	</div>
	<table class="table table-hover table-condensed">
		<thead>
			<tr>
				<td>#</td>
				<td>用户名</td>
				<td>邮箱</td>
				<td>最后登录时间</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="p" varStatus="status">
				<tr>
					<td>${p.uid}</td>
					<td><a href="javascript:;" onclick="modalInfo('${p.username}');">${p.username}</a></td>
					<td>${p.mail}</td>
					<td>${p.lastlogintime}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="footer.jsp"%>
<script src="js/examineVerify.js"></script>