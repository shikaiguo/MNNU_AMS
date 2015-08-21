<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
</div>
<!-- pageContent end -->
<!-- 页脚 -->
<div class="row footer">
	<div class="col-xs-12">
		<div class=" footer-span">CopyRight &copy; 闽南师范大学 2015</div>
	</div>
</div>

<div class="modal fade modal-bh" role="dialog">
	<div class="modal-dialog">
	<div class="modal-content">载入中...</div>
	</div>
</div>
</div>
<!-- container end -->
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="jqGrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="jqGrid/js/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="jqGrid/js/jquery-ui-1.10.4.custom.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-dialog.min.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript">
	function modalShow(url) {
		$('.modal-content').load(url);
		$('.modal-button').click();
	}
	function changePage(url) {
		$(".pageContent").load(url);
	}
	//增加数据
	$("#addInfo").click(function() {
		$("#list").jqGrid('editGridRow', "new", {
			height : 700,
			width : 700,
			addCaption : "编辑记录",
			recreateForm : true,
			closeAfterAdd : true
		});
	});

	//删除选中行
	$("#delInfo").click(function() {
		var selectedRowIds = $("#list").jqGrid("getGridParam", "selarrrow");
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
				onclickSubmit : function() {

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
