<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div class="col-xs-12 query">
	<div class="btn-group amq" style="margin-bottom:3px">
		<button id="query" type="button" class="btn btn-bh" data-toggle="modal" data-target=".modal-bh">查询</button>
		<button id="col-ctrl" type="button" class="btn btn-bh" data-toggle="modal" data-target=".modal-bh">字段</button>
		<button id="mail" type="button" class="btn btn-bh">邮件</button>
		<button id="addInfo" type="button" class="btn btn-bh">增加</button>
		<button id="delInfo" type="button" class="btn btn-bh">删除</button>
		<button id="altInfo" type="button" class="btn btn-bh">修改</button>
		<button id="import" type="button" class="btn btn-bh" data-toggle="modal" data-target=".modal-bh">导入</button>
		<button type="button" class="btn btn-bh">导出</button>
		<!-- 		<button type="button" class="btn btn-bh">打印</button> -->
	</div>
	<!-- jqGrid -->
	<div>
		<code id="show">查询条件:无</code>
	</div>
	<table class="table" id="gridTable"></table>
	<div id="grid-Pager"></div>
	<!-- jqGrid -->
</div>
<%@ include file="footer.jsp"%>
<script src="jqGrid/js/jquery.jqGrid.min.js"></script>
<script src="jqGrid/js/i18n/grid.locale-cn.js"></script>
<script src="js/query.js"></script>