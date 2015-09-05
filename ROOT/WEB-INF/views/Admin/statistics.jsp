<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div class="col-xs-12">
	<span>统计条件：</span>
	<div id="statistics">
		<select id="condition">
			<option>请选择条件</option>
			<option>行业</option>
			<option>院系</option>
			<option>生源地</option>
			<option>工作地区</option>
			<option>新增校友</option>
		</select>
		<span id="detail"></span>
		<button id="statisticsbtn" class="btn btn-primary">统计</button>
		<button id="print" class="btn btn-primary" onclick='javascript:window.print()'>打印</button>
		<div id="res"></div>
		<div id="main"></div>
	</div>
</div>
<%@ include file="footer.jsp"%>
<script src="js/echarts.js"></script>
<script src="js/statistics.js"></script>