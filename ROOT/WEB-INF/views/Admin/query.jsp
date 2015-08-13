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
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-dialog.min.css">
<link rel="stylesheet" type="text/css" href="css/admin.css">
<link rel="stylesheet" type="text/css"
	href="jqGrid/themes/smoothness/jquery.ui.core.css">
<link rel="stylesheet" type="text/css"
	href="jqGrid/themes/smoothness/jquery.ui.theme.css">
<link rel="stylesheet" type="text/css"
	href="jqGrid/themes/ui.jqgrid.css">
<style>
.ui-jqgrid .ui-pg-input,.ui-jqgrid .ui-jqgrid-toppager .ui-pg-input {
	height: 18px;
	width: auto;
	font-size: .9em;
	margin: 0;
	line-height: inherit;
	border: none;
	padding: 3px 2px;
}

.ui-jqgrid .ui-pg-selbox,.ui-jqgrid .ui-jqgrid-toppager .ui-pg-selbox {
	font-size: .9em;
	line-height: inherit;
	display: block;
	height: 22px;
	margin: 0;
	padding: 3px 0px;
	border: none;
}

#gridPager_center {
	white-space: pre;
	width: 289px !important;
}
</style>
</head>
<body>

	<div class="col-xs-12">
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
		<table class="table" id="list"></table>
		<div id="gridPager"></div>
		<!-- jqGrid -->
	</div>

	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="jqGrid/js/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="jqGrid/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="jqGrid/js/jquery-ui-1.10.4.custom.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-dialog.min.js"></script>
	<script type="text/javascript">
		//jqGrid
		$(document).ready(
				function() {
					var colNames = [ '', 'ID', '学院', '专业', '班级', '学号', '姓名',
							'性别', '生源地省', '生源地市', '生源地区', '学历', '毕业时间', '工作省份',
							'工作市', '工作地区', '工作单位', '职务', '职称', '行业', '联系电话',
							'固定电话', '邮箱', 'QQ号', '通讯地址', '邮编', '备注', '所属校友总会',
							'校友总会职务', '所属校友分会', '校友分会职务' ];
					var colModel = [ {
						name : 'sysId',
						width : 60,
						hidden : true,
						key : true
					}, {
						name : 'id',
						index : 'id',
						width : 70,
					}, {
						name : 'dept',
						index : 'dept',
						width : 100,
						editable : true
					//默认false
					}, {
						name : 'major',
						index : 'major',
						width : 100,
						editable : true
					}, {
						name : 'class',
						index : 'cls',
						width : 120,
						editable : true
					}, {
						name : 'sno',
						index : 'sno',
						width : 120,
						editable : true
					}, {
						name : 'sname',
						index : 'sname',
						width : 120,
						editable : true
					}, {
						name : 'sex',
						index : 'sex',
						width : 120,
						editable : true
					}, {
						name : 'province_from',
						index : 'province_from',
						width : 120,
						editable : true
					}, {
						name : 'city_from',
						index : 'city_from',
						width : 120,
						editable : true
					}, {
						name : 'district_from',
						index : 'district_from',
						width : 120,
						editable : true
					}, {
						name : 'degree',
						index : 'degree',
						width : 120,
						editable : true
					}, {
						name : 'gra_time',
						index : 'gra_time',
						width : 120,
						editable : true
					}, {
						name : 'province_work',
						index : 'province_work',
						width : 120,
						editable : true
					}, {
						name : 'city_work',
						index : 'city_work',
						width : 120,
						editable : true
					}, {
						name : 'district_work',
						index : 'district_work',
						width : 120,
						editable : true
					}, {
						name : 'work_unit',
						index : 'work_unit',
						width : 240,
						editable : true
					}, {
						name : 'duty',
						index : 'duty',
						width : 120,
						editable : true
					}, {
						name : 'job',
						index : 'job',
						width : 120,
						editable : true
					}, {
						name : 'profession',
						index : 'profession',
						width : 120,
						editable : true
					}, {
						name : 'phone1',
						index : 'phone1',
						width : 120,
						editable : true
					}, {
						name : 'phone2',
						index : 'phone2',
						width : 120,
						editable : true
					}, {
						name : 'mail',
						index : 'mail',
						width : 120,
						editable : true
					}, {
						name : 'qq',
						index : 'qq',
						width : 120,
						editable : true
					}, {
						name : 'address',
						index : 'address',
						width : 240,
						editable : true
					}, {
						name : 'zip',
						index : 'zip',
						width : 120,
						editable : true
					}, {
						name : 'note',
						index : 'note',
						width : 120,
						editable : true
					}, {
						name : 'accsoc1',
						index : 'accsoc1',
						width : 120,
						editable : true
					}, {
						name : 'accsoc1_job',
						index : 'accsoc1_job',
						width : 100,
						editable : true
					}, {
						name : 'accsoc2',
						index : 'accsoc2',
						width : 120,
						editable : true
					}, {
						name : 'accsoc2_job',
						index : 'accsoc2_job',
						width : 120,
						editable : true
					} ];
					$("#list").jqGrid({
						url : "Admin/jqgridAllDate",
						datatype : "json", //数据来源
						contentType : "application/json;charset=UTF-8",
						mtype : "POST",//提交方式
						height : 330,//高度，表格高度。可为数值、百分比或'auto'
						colNames : colNames,
						colModel : colModel,
						rowNum : 50,//每页显示记录数  在grid上显示记录条数，这个参数是要被传递到后台
						rowList : [ 50, 100, 200 ],//用于改变显示行数的下拉列表框的元素数组。
						//altRows:true,//设置为交替行表格,默认为false
						loadonce : true, //设置后台只加载一次，前台分页
						sortname : 'id', //默认的排序列。可以是列名称或者是一个数字，这个参数会被提交到后台
						sortorder : 'asc',
						sortable : true,
						//		caption:"",
						viewrecords : true, //定义是否要显示总记录数
						emptyrecords : "总记录为0",//当返回的数据行数为0时显示的信息。只有当属性 viewrecords 设置为ture时起作用
						//	prmNames : {
						//			search : "search"
						//	}, //Default valuesprmNames: {page:“page”,rows:“rows”, sort: “sidx”,order: “sord”, search:“_search”, nd:“nd”, npage:null} 当参数为null时不会被发到服务器端
						//altRows : true,
						//		multiboxonly : true,
						multiselect : true,
						//	rownumbers : true, //如果为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'.
						shrinkToFit : false,
						autoScroll : true,
						jsonReader : { //跟服务器端返回的数据做对应
							root : "gridModel",
							records : "record", // 总记录数
							id:"id",
							//		page : "page",// 当前页码数
							//		total : "total", // 总页数
							//	sord : "sord",// 排序的方式
							//	sidx : "sidx",// 用于排序的列名
							//	search : "search", // 是否用于查询的请求							
							repeatitems : false
						//指明每行的数据是可以重复的，如果设为false，则会从返回的数据中按名字来搜索元素，这个名字就是colModel中的名字
						},
						pager : $("#gridPager"),
						onPaging : uppage,
						width : $('.container').width(),//这个宽度不能为百分比
						//排序
						onSortCol : function(index, iCol, sortorder) {
						},
						onSelectRow : function(id) {
							//点击某行时的操作
						},
						editurl : "Admin/saveEdit"
					});
					jQuery("#list").jqGrid('navGrid', "#gridPager", {
						//显示增删改查按钮
						edit : true,
						add : true,
						del : true,
						search : true,
					});
				});

		$(function() {
			//jqgrid 宽度自适应大小
			$(window).resize(function() {
				$("#list").setGridWidth($('.container').width());
			});
		});
		//增加数据
		$("#addInfo").click(function() {
			jQuery("#list").jqGrid('editGridRow', "new", {
				height : 700,
				width : 700,
				addCaption : "编辑记录",
				recreateForm : true,
				closeAfterAdd : true
			});
		});

		//删除选中行
		$("#delInfo").click(
				function() {
					var selectedRowIds = $("#list").jqGrid("getGridParam",
							"selarrrow");
					var len = selectedRowIds.length;
					if (len == 0) {
						alert("请勾选要删除的数据行");
					} else {
						for (var i = 0; i < len; i++) {
							$("#list").jqGrid("delRowData", selectedRowIds[0]);
						}
					}
				});

		//编辑选中数据行		
		$("#altInfo").click(function() {
			/*		jQuery("#list").jqGrid('editGridRow', "new", {
						height : 700,
						width : 700,
						addCaption : "编辑记录",
						recreateForm : true,
						closeAfterAdd : true
					}); */

			var ids = $("#list").jqGrid('getGridParam', 'selarrrow');
			if (ids.length == 0) {
				alert("请勾选要编辑的数据行");
			} else if (ids.length > 1) {
				alert("一次只能编辑一行数据");
			} else {
				$("#list").jqGrid('editGridRow', ids[0], {
					onclickSubmit : function(  ) {
						
					}
				});
			}
		});

		//翻页
		function uppage(pgButton) {
			var page = jQuery("#gridTable").jqGrid('getGridParam', 'page');
			jQuery("#gridTable").setGridParam({
				page : page
			}).trigger("reloadGrid");
		}

		//查询对话框
		$("#query").click(function() {
			BootstrapDialog.show({
				title : '请选择查询条件',
				message : function(dialog) {
					var $message = $('<div></div>');
					var pageToLoad = dialog.getData('pageToLoad');
					$message.load(pageToLoad);
					return $message;
				},
				data : {
					'pageToLoad' : 'Admin/queryDialog'
				},
				buttons : [ {
					label : '查询',
					cssClass : 'btn-danger',
					id : 'searchBtn',
				} ]
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
											id : 'exlToMysql',
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
