<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp"%>
<div class="col-xs-12 examine-verify">
	<div class="btn-group amm" style="margin-bottom:3px">
		<button type="button" class="btn btn-default" onclick="history.back(-1);">返回</button>
		<button type="button" class="btn btn-bh" onclick="location.reload();">
			刷新
			<span class="glyphicon glyphicon-refresh"></span>
		</button>
		<button id="pass" type="button" class="btn btn-bh">
			通过审批
			<span class="glyphicon glyphicon-ok"></span>
		</button>
		<button id="refuse" type="button" class="btn btn-bh">
			拒绝修改
			<span class="glyphicon glyphicon-remove"></span>
		</button>
		<button id="flush" type="button" class="btn btn-bh" title="指内容未变更项(或重复的请求)">
			清除无效项
			<span class="glyphicon glyphicon-scissors"></span>
		</button>
	</div>
	<div class="table-responsive"></div>
	<table class="table table-hover table-condensed">
		<thead>
			<tr>
				<td>#</td>
				<td>用户信息</td>
				<td>变更项</td>
				<td>原先内容</td>
				<td>变更内容</td>
				<td>备注</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="p" varStatus="status">
				<tr>
					<td>${p.id}</td>
					<td><a href="javascript:;" onclick="modalInfo('${p.userid}');">${p.userid}</a></td>
					<td>${p.filed}</td>
					<td>${p.oldcontent}</td>
					<td>${p.content}</td>
					<td>${p.note}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@include file="footer.jsp"%>
<script src="js/examineVerify.js"></script>