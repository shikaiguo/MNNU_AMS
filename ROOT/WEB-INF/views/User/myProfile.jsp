<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!-- 页面内容 -->
<div class="col-xs-12 content">
	<%
		if(request.getAttribute("ai")!=null){
	%>
	
	<div class="col-xs-6">学号：{$ai.sno}</div>
	<div class="col-xs-6">学号：{$ai.sno}</div>
	<div class="col-xs-6">学号：{$ai.sno}</div>
	<div class="col-xs-6">学号：{$ai.sno}</div>
	<div class="col-xs-6">学号：{$ai.sno}</div>
	<div class="col-xs-6">学号：{$ai.sno}</div>
	<div class="col-xs-6">学号：{$ai.sno}</div>
	<div class="col-xs-6">学号：{$ai.sno}</div>
	<%
		}else{
	%>
	您好，您当前未绑定校友信息，您可以<a href="javascript:;">立即绑定</a>校友信息
	<%
		}
	%>
</div>
<%@ include file="footer.jsp"%>