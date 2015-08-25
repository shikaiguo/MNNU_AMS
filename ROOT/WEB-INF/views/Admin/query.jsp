<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div class="col-xs-12 query">
	<div class="btn-group amm" style="margin-bottom:3px">
		<button id="query" type="button" class="btn btn-bh" data-toggle="modal" data-target=".modal-bh">查询</button>
		<button type="button" class="btn btn-bh">字段</button>
		<button type="button" class="btn btn-bh">邮件</button>
		<button type="button" class="btn btn-bh">增加</button>
		<button type="button" class="btn btn-bh">删除</button>
		<button type="button" class="btn btn-bh">修改</button>
		<button id="import" type="button" class="btn btn-bh" data-toggle="modal" data-target=".modal-bh">导入</button>
		<button type="button" class="btn btn-bh">导出</button>
		<button type="button" class="btn btn-bh" onclick="javascript:printme()">打印</button>
	</div>
	<!-- jqGrid -->
	<div>
		<code id="show">查询条件:无</code>
	</div>
	<div id="print-body">
		<table class="table" id="list"></table>
		<div id="gridPager"></div>
	</div>
	<!-- jqGrid -->
	<script>
		var global_Html = "";
		function printme() {
			global_Html = document.body.innerHTML;
			document.body.innerHTML = document.getElementById('print-body').innerHTML;
			window.print();
			window.setTimeout(function() {
				document.body.innerHTML = global_Html;
			}, 1500);
		}
	</script>
</div>
<%@ include file="footer.jsp"%>
