<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>校友信息查询</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="css/jquery-ui.min.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-dialog.min.css">
<link rel="stylesheet" type="text/css" 
    href="css/query.css">
<link rel="stylesheet" type="text/css"
	href="jqGrid/themes/jquery.ui.core.css">
<link rel="stylesheet" type="text/css"
	href="jqGrid/themes/jquery.ui.theme.css">
<link rel="stylesheet" type="text/css"
	href="jqGrid/themes/ui.jqgrid.css">
</head>
<body>
	<div style="float:right; font-size:0;">
		<div class="nav-menu" id="query">查询</div>
		<div class="nav-menu" id="selectFilter">字段</div>
		<div class="nav-menu" id="sendEmails">邮件</div>
		<div class="nav-menu" id="addInfo">增加</div>
		<div class="nav-menu" id="delInfo">删除</div>
		<div class="nav-menu" id="altInfo">修改</div>
		<div class="nav-menu" id="leadingXls">导入</div>
		<div class="nav-menu" id="exportXls">导出</div>
		<div class="nav-menu" id="printInfo">打印</div>
	</div>

	<div style="clear:both;"></div>

	<!-- jqGrid -->
	<table id="list"></table>
	<div id="gridPager"></div>
	<!-- jqGrid -->

	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript"
		src="jqGrid/js/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript"
		src="jqGrid/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript"
		src="jqGrid/js/jquery-ui-1.10.4.custom.min.js"></script>
	<script type="text/javascript" 
	    src="js/query.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-dialog.min.js"></script>
	<script type="text/javascript">
		//jqGrid
		$(document).ready(
				function() {
					$("#list").jqGrid(
							{
								url : "",
								datatype : "json", //数据来源，本地数据
								mtype : "POST",//提交方式
								height : 320,//高度，表格高度。可为数值、百分比或'auto'
								//width:1000,//这个宽度不能为百分比
								colNames : [ '', '学院', '专业', '班级', '学号', '姓名',
										'性别', '生源地省', '生源地市', '生源地区', '学历',
										'毕业时间', '工作省份', '工作市', '工作地区', '工作单位',
										'职务', '职称', '行业', '联系电话', '固定电话', '邮箱',
										'QQ号', '通讯地址', '邮编', '备注', '所属校友总会',
										'校友总会职务', '所属校友分会', '校友分会职务' ],
								colModel : [ {
									name : 'sysId',
									width : 60,
									hidden : true,
									key : true
								}, {
									name : 'department',
									index : 'department',
									width : 100,
									editable : true
								}, {
									name : 'specialty',
									index : 'specialty',
									width : 100,
									editable : true
								}, {
									name : 'grades',
									index : 'grades',
									width : 120,
									editable : true
								}, {
									name : 'userId',
									index : 'userId',
									width : 120,
									editable : true
								}, {
									name : 'name',
									index : 'name',
									width : 120,
									editable : true
								}, {
									name : 'gender',
									index : 'gender',
									width : 120,
									editable : true
								}, {
									name : 'oosProvince',
									index : 'oosProvince',
									width : 120,
									editable : true
								}, {
									name : 'oosCity',
									index : 'oosCity',
									width : 120,
									editable : true
								}, {
									name : 'oosArea',
									index : 'oosArea',
									width : 120,
									editable : true
								}, {
									name : 'degree',
									index : 'degree',
									width : 120,
									editable : true
								}, {
									name : 'graduateDate',
									index : 'graduateDate',
									width : 120,
									editable : true
								}, {
									name : 'workProvince',
									index : 'workProvince',
									width : 120,
									editable : true
								}, {
									name : 'workCity',
									index : 'workCity',
									width : 120,
									editable : true
								}, {
									name : 'workArea',
									index : 'workArea',
									width : 120,
									editable : true
								}, {
									name : 'workUnit',
									index : 'workUnit',
									width : 240,
									editable : true
								}, {
									name : 'post',
									index : 'post',
									width : 120,
									editable : true
								}, {
									name : 'title',
									index : 'title',
									width : 120,
									editable : true
								}, {
									name : 'vocation',
									index : 'vocation',
									width : 120,
									editable : true
								}, {
									name : 'tel',
									index : 'tel',
									width : 120,
									editable : true
								}, {
									name : 'fixedtel',
									index : 'fixedtel',
									width : 120,
									editable : true
								}, {
									name : 'email',
									index : 'email',
									width : 120,
									editable : true
								}, {
									name : 'qq',
									index : 'qq',
									width : 120,
									editable : true
								}, {
									name : 'adress',
									index : 'adress',
									width : 240,
									editable : true
								}, {
									name : 'zipCode',
									index : 'zipCode',
									width : 120,
									editable : true
								}, {
									name : 'demo',
									index : 'demo',
									width : 120,
									editable : true
								}, {
									name : 'club',
									index : 'club',
									width : 120,
									editable : true
								}, {
									name : 'clubTitle',
									index : 'clubTitle',
									width : 100,
									editable : true
								}, {
									name : 'branchClub',
									index : 'branchClub',
									width : 120,
									editable : true
								}, {
									name : 'branchClubTitle',
									index : 'branchClubTitle',
									width : 120,
									editable : true
								} ],
								//prmNames:prmNames,
								viewrecords : true,//是否在浏览导航栏显示记录总数
								rowNum : 15,//每页显示记录数
								//rowList:rowList,
								//altRows:true,//设置为交替行表格,默认为false
								//sortname:'createDate',
								//sortorder:'asc',
								altRows : true,
								multiboxonly : true,
								multiselect : true,
								rownumbers : false,
								shrinkToFit : false,
								autoScroll : true,
								rowList : [ 15, 20, 25 ],//用于改变显示行数的下拉列表框的元素数组。
								jsonReader : {
									id : "blackId",//设置返回参数中，表格ID的名字为blackId
									repeatitems : false
								},
								pager : $('#gridPager')
							});
				});

		//字段显示选择对话框
		$("#selectFilter").click(function() {
			BootstrapDialog.show({
				title : '字段显示',
				message : function(dialog) {
					var $message = $('<div></div>');
					var pageToLoad = dialog.getData('pageToLoad');
					$message.load(pageToLoad);

					return $message;
				},
				data : {
					'pageToLoad' : 'Admin/selectFilter'
				}
			});
		});

		//发送邮件对话框
		$("#sendEmails").click(function() {
			var dialog = new BootstrapDialog({
				title : '发送邮件',
				message : function(dialog) {
					var $message = $('<div></div>');
					var pageToLoad = dialog.getData('pageToLoad');
					$message.load(pageToLoad);
					return $message;
				},
				data : {
					'pageToLoad' : 'Admin/sendEmails'
				},
				buttons : [ {
					label : '定期发送',
					cssClass : 'btn-default',
					id : 'sendRegular'
				}, {
					label : '发送',
					cssClass : 'btn-danger',
				} ]
			});
			dialog.realize();
			var btn = dialog.getButton('sendRegular');
			btn.click(function(event) {
				// 定期发送邮件
				BootstrapDialog.show({
					title : '选择定期发送时间',
					message : function(dialog) {
						var $message = $('<div></div>');
						var pageToLoad = dialog.getData('pageToLoad');
						$message.load(pageToLoad);
						return $message;
					},
					data : {
						'pageToLoad' : 'Admin/sendRegular'
					},
					buttons : [ {
						label : '保存设置',
						cssClass : 'btn-danger',
					} ]

				});
			});
			dialog.open();
		});

		//导入对话框
		$("#leadingXls")
				.click(
						function() {
							BootstrapDialog
									.show({
										title : '请选择导入文件',
										message : '<input class="form-control" id="filename" type="file" name="file">',
										buttons : [ {
											label : '导入',
											cssClass : 'btn-danger',
										} ]
									});
						});

		//导出对话框
		$("#exportXls")
				.click(
						function() {
							BootstrapDialog
									.show({
										title : '请选择导出路径',
										message : '<input class="form-control" id="filename" type="file" name="file">',
										buttons : [ {
											label : '导出',
											cssClass : 'btn-danger',
										} ]
									});
						});
	</script>
</body>
</html>
