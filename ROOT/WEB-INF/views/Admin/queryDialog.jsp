<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="row">
	<div class="col-xs-12">
		<div class="title">查询条件</div>
		<div class="content">
			<div id="condition" class="input-group">
				<select id="index">
					<option>学院</option>
					<option>专业</option>
					<option>班级</option>
					<option>学号</option>
					<option>姓名</option>
					<option>性别</option>
					<option>生源地省</option>
					<option>生源地市</option>
					<option>生源地区</option>
					<option>学历</option>
					<option>毕业时间</option>
					<option>工作省份</option>
					<option>工作市</option>
					<option>工作地区</option>
					<option>工作单位</option>
					<option>职务</option>
					<option>职称</option>
					<option>行业</option>
					<option>联系电话</option>
					<option>固定电话</option>
					<option>邮箱</option>
					<option>QQ号</option>
					<option>通讯地址</option>
					<option>邮编</option>
					<option>备注</option>
					<option>所属校友总会</option>
					<option>校友总会职务</option>
					<option>所属校友分会</option>
					<option>校友分会职务</option>
				</select>
				<select id="eq">
					<option>=</option>
					<option>!=</option>
					<option>like</option>
				</select>
				<input>
				<span id="del" class="glyphicon glyphicon-remove" onclick="del()" title="删除本条件"></span>
			</div>
			<span id="add" class="glyphicon glyphicon-plus" onclick="add()" title="增加一个条件"></span>
		</div>
		<hr>
		<button id="submit" class="btn btn-default" data-dismiss="modal">提交</button>
	</div>
</div>