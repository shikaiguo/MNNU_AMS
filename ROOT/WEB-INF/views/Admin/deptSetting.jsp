<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<div class="col-xs-12 dept-setting">
	<!-- 按钮组 显示 增，改，删，刷新，保存等 -->
	<div class="btn-group" style="margin-bottom:3px">
		<button id="add" type="button" class="btn btn-bh">新增院系</button>
		<button type="button" class="btn btn-bh">院系修改或合并</button>
		<button type="button" class="btn btn-bh">删除院系</button>
		<button type="button" class="btn btn-bh">重新加载</button>
		<button type="button" class="btn btn-bh">保存修改</button>
	</div>
	<div id="handle"></div>
	<!-- 显示院系 id 和 name -->
	<div class="dept-list">
		<c:forEach items="${list}" var="dept" varStatus="vs">
			<div>
				<input type="checkbox">
				<span>${dept.id}</span>
				<span>${dept.name}</span>
			</div>
		</c:forEach>
	</div>
	<div id="show"></div>
</div>
<%@ include file="footer.jsp"%>
<script src="js/deptSetting.js"></script>