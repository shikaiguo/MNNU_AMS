<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="row show">
	<div class="col-xs-12">
		<div class="title">用户信息</div>
		<div class="content row">
			<div class="col-xs-12">
				<div class="col-xs-4">用户：${puid }</div>
				<div class="col-xs-4">姓名：${pname }</div>
				<div class="col-xs-4">学院：${pdept }</div>
				<div class="col-xs-4">专业：${pmajor }</div>
				<div class="col-xs-4">学号：${psno }</div>
			</div>
		</div>
		<hr>
		<button id="submit" class="btn btn-default" data-dismiss="modal">关闭</button>
	</div>
</div>