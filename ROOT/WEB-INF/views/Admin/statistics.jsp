<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div class="col-xs-12">
	<span>统计条件：</span>
	<div id="statistics">
		<select id="condition">
			<option>请选择条件</option>
			<option>行业</option>
			<option>院级</option>
			<option>学历</option>
			<option>生源地</option>
			<option>工作地区</option>
			<option>新增校友</option>
		</select>
		<div id="detail"></div>
		<button class="btn btn-primary">统计</button>
		<div id="res"></div>
	</div>
</div>
<%@ include file="footer.jsp"%>
