<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'master.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
	    href="css/bootstrap-dialog.min.css">
    <link rel="stylesheet" type="text/css" 
        href="css/admin.css">
    <link rel="stylesheet" type="text/css"
	    href="jqGrid/themes/smoothness/jquery.ui.core.css">
    <link rel="stylesheet" type="text/css"
	    href="jqGrid/themes/smoothness/jquery.ui.theme.css">
    <link rel="stylesheet" type="text/css"
	    href="jqGrid/themes/ui.jqgrid.css">

  </head>
  
  <body>
    <div style="float:right; font-size:0;">
		<div class="nav-menu" id="query">查询</div>
		<div class="nav-menu" id="selectFilter">字段</div>
		<div class="nav-menu" id="sendEmails">邮件</div>
		<div class="nav-menu" id="printInfo">打印</div>
	</div>
	<div style="clear:both;"></div>
	
	<!-- jqgrid 校长权限 -->
	<table id="list"></table>
	<div id="gridPager"></div>
	
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
					$("#list").jqGrid(
							{
								url : "Admin/jqgridAllDate",
								datatype : "json", //数据来源
								contentType : "application/json;charse=UTF-8",
								mtype : "POST",//提交方式
								height : 320,//高度，表格高度。可为数值、百分比或'auto'
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
									name : 'dept',
									index : 'dept',
									width : 100,
									editable : true
								}, {
									name : 'major',
									index : 'major',
									width : 100,
									editable : true
								}, {
									name : 'cls',
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
									name : 'provincefrom',
									index : 'provincefrom',
									width : 120,
									editable : true
								}, {
									name : 'cityfrom',
									index : 'cityfrom',
									width : 120,
									editable : true
								}, {
									name : 'districtfrom',
									index : 'districtfrom',
									width : 120,
									editable : true
								}, {
									name : 'degree',
									index : 'degree',
									width : 120,
									editable : true
								}, {
									name : 'gratime',
									index : 'gratime',
									width : 120,
									editable : true
								}, {
									name : 'provincework',
									index : 'provincework',
									width : 120,
									editable : true
								}, {
									name : 'citywork',
									index : 'citywork',
									width : 120,
									editable : true
								}, {
									name : 'districtwork',
									index : 'districtwork',
									width : 120,
									editable : true
								}, {
									name : 'workunit',
									index : 'workunit',
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
									name : 'prefession',
									index : 'prefession',
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
									name : 'accsoc1job',
									index : 'accsoc1job',
									width : 100,
									editable : true
								}, {
									name : 'accsoc2',
									index : 'accsoc2',
									width : 120,
									editable : true
								}, {
									name : 'accsoc2job',
									index : 'accsoc2job',
									width : 120,
									editable : true
								} ],
								viewrecords : true,//是否在浏览导航栏显示记录总数
								rowNum : 50,//每页显示记录数  在grid上显示记录条数，这个参数是要被传递到后台
								rowList : [ 15, 20, 25 ],//用于改变显示行数的下拉列表框的元素数组。
								//altRows:true,//设置为交替行表格,默认为false
								sortname : 'sysId', //默认的排序列。可以是列名称或者是一个数字，这个参数会被提交到后台
								//sortorder:'asc',
								viewrecords : true, //定义是否要显示总记录数
								emptyrecords : "总记录为0",//当返回的数据行数为0时显示的信息。只有当属性 viewrecords 设置为ture时起作用
								prmNames : {
									search : "search"
								}, //Default valuesprmNames: {page:“page”,rows:“rows”, sort: “sidx”,order: “sord”, search:“_search”, nd:“nd”, npage:null} 当参数为null时不会被发到服务器端
								//altRows : true,
								multiboxonly : true,
								multiselect : true,
								rownumbers : false, //如果为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'.
								shrinkToFit : false,
								autoScroll : true,
								jsonReader : { //跟服务器端返回的数据做对应
									root : "gridModel",
								//	rows : "rows", // 每页中的记录行数
									record : "record", // 总记录数
									page : "page",// 当前页码数
									total : "total", // 总页数
								//	sord : "sord",// 排序的方式
								//	sidx : "sidx",// 用于排序的列名
								//	search : "search", // 是否用于查询的请求							
									repeatitems : false
								//指明每行的数据是可以重复的，如果设为false，则会从返回的数据中按名字来搜索元素，这个名字就是colModel中的名字
								},
								pager : "#gridPager",
								width:$('.container').width()//这个宽度不能为百分比
							});
				});
		$(function(){
            $(window).resize(function(){   
            $("#list").setGridWidth($('.container').width());
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
	</script>
  </body>
</html>
