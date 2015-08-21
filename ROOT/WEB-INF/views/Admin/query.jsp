<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<div class="col-xs-12">
	<div class="btn-group amm" style="margin-bottom:3px">
		<button id="query1" type="button" class="btn btn-bh" data-toggle="modal" data-target=".modal-bh" >查询</button>
		<button type="button" style="background-color:lightgray;" class="btn btn-bh">字段</button>
		<button type="button" style="background-color:lightgray;" class="btn btn-bh">邮件</button>
		<button type="button" style="background-color:lightgray;" class="btn btn-bh">增加</button>
		<button type="button" style="background-color:lightgray;" class="btn btn-bh">删除</button>
		<button type="button" style="background-color:lightgray;" class="btn btn-bh">修改</button>
		<button type="button" style="background-color:lightgray;" class="btn btn-bh">导入</button>
		<button type="button" style="background-color:lightgray;" class="btn btn-bh">导出</button>
		<button type="button" style="background-color:lightgray;" class="btn btn-bh">打印</button>
	</div>
	<!-- jqGrid -->
	<div><code id="show">查询条件:无</code></div>
	<table class="table" id="list"></table>
	<div id="gridPager"></div>
	<!-- jqGrid -->
</div>
<%@ include file="footer.jsp"%>
