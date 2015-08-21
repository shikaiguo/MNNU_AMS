	$('.header .nav .menu .item,.header .nav .nav-name').hover(function() {
		$(this).children('.pitem').show();
		$(this).children('.glyphicon-chevron-down').addClass('glyphicon-chevron-up').removeClass('glyphicon-chevron-down');
	}, function() {
		$(this).children('.pitem').hide();
		$(this).children('.glyphicon-chevron-up').addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-up');
	});
	
	$('.header .nav .menu-button').click(function() {
		var obj = $('.header .nav .menu');
		obj.toggle();
	});
	// ---------------------查询--------------------------------------------------------------------
	$('.amm #query').click(function() {
		// $('.modal-content').load("Admin/queryDialog");
		$('.modal-content').html("<div class=\"row\"><div class=\"col-xs-12\"><div class=\"title\">查询条件</div><div class=\"content\"><span id=\"add\" class=\"glyphicon glyphicon-plus\" title=\"增加一个条件\"></span></div><hr><button id=\"submit\" class=\"btn btn-default\" data-dismiss=\"modal\">提交</button><div id=\"tip\">条件为空时，点击提交查询所有数据，或点击上方的 <span class=\"glyphicon glyphicon-plus\"></span> 图标设置查询条件<div></div></div>");
	});
	
	var colNames = [ '', 'ID', '学院', '专业', '班级', '学号', '姓名', '性别', '生源地省', '生源地市', '生源地区', '学历', '毕业时间', '工作省份', '工作市', '工作地区', '工作单位', '职务', '职称', '行业', '联系电话', '固定电话', '邮箱', 'QQ号', '通讯地址', '邮编', '备注', '所属校友总会', '校友总会职务', '所属校友分会', '校友分会职务' ];
	var colModel = [ { name : 'sysId', width : 60, hidden : true, key : true}, { name : 'id', index : 'id', width : 70,}, { name : 'dept', index : 'dept', width : 100, editable : true}, { name : 'major', index : 'major', width : 100, editable : true}, { name : 'cls', index : 'cls', width : 120, editable : true}, { name : 'sno', index : 'sno', width : 120, editable : true}, { name : 'sname', index : 'sname', width : 120, editable : true}, { name : 'sex', index : 'sex', width : 120, editable : true}, { name : 'provincefrom', index : 'provincefrom', width : 120, editable : true}, { name : 'cityfrom', index : 'cityfrom', width : 120, editable : true}, { name : 'districtfrom', index : 'districtfrom', width : 120, editable : true}, { name : 'degree', index : 'degree', width : 120, editable : true}, { name : 'gratime', index : 'gratime', width : 120, editable : true}, { name : 'provincework', index : 'provincework', width : 120, editable : true}, { name : 'citywork', index : 'citywork', width : 120, editable : true}, { name : 'districtwork', index : 'districtwork', width : 120, editable : true}, { name : 'workunit', index : 'workunit', width : 240, editable : true}, { name : 'duty', index : 'duty', width : 120, editable : true}, { name : 'job', index : 'job', width : 120, editable : true}, { name : 'profession', index : 'profession', width : 120, editable : true}, { name : 'phone1', index : 'phone1', width : 120, editable : true}, { name : 'phone2', index : 'phone2', width : 120, editable : true}, { name : 'mail', index : 'mail', width : 120, editable : true}, { name : 'qq', index : 'qq', width : 120, editable : true}, { name : 'address', index : 'address', width : 240, editable : true}, { name : 'zip', index : 'zip', width : 120, editable : true}, { name : 'note', index : 'note', width : 120, editable : true}, { name : 'assoc1', index : 'assoc1', width : 120, editable : true}, { name : 'assoc1job', index : 'assoc1job', width : 100, editable : true}, { name : 'assoc2', index : 'assoc2', width : 120, editable : true}, { name : 'assoc2job', index : 'assoc2job', width : 120, editable : true} ];
	$("#list").jqGrid({
	    url: "Admin/jqgridAllData", // 请求路径
	    datatype: "json", // 数据类型
	    contentType: "application/json;charset=UTF-8", //编码
	    mtype: "POST", //请求类型
	    height: 330, // 高度，表格高度。可为数值、百分比或'auto'
	    colNames: colNames, //列名
	    colModel: colModel, //列样式
	    rowNum: 50, // 每页显示记录数
	    rowList: [50, 100, 200], // 用于改变显示行数的下拉列表框的元素数组。
	    //altRows:true,//设置为交替行表格,默认为false
	    loadonce: true, // 设置后台只加载一次，前台分页
	    sortname: 'id', // 默认的排序列。可以是列名称或者是一个数字，这个参数会被提交到后台
	    sortorder: 'asc', //排序方式
	    sortable: true, //
	    // caption:"",//标题
	    viewrecords: true, // 定义是否要显示总记录数
	    emptyrecords: "总记录为0", // 当返回的数据行数为0时显示的信息。只有当属性
	    // viewrecords 设置为ture时起作用
	    // prmNames : {search : "search"}, 
	    //Default valuesprmNames: {page:“page”,rows:“rows”,sort: “sidx”,order: “sord”, search:“_search”, nd:“nd”,npage:null} //当参数为null时不会被发到服务器端
	    //altRows : true,
	    //multiboxonly : true,
	    multiselect: true,
	    //rownumbers : true,
	    //如果为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'.
	    shrinkToFit: false,
	    autoScroll: true,
	    jsonReader: { // 跟服务器端返回的数据做对应
	        root: "gridModel",
	        records: "record", // 总记录数
	        id: "id",
	        // page : "page",// 当前页码数
	        // total : "total", // 总页数
	        // sord : "sord",// 排序的方式
	        // sidx : "sidx",// 用于排序的列名
	        // search : "search", // 是否用于查询的请求
	        repeatitems: false
	            // 指明每行的数据是可以重复的，如果设为false，则会从返回的数据中按名字来搜索元素，这个名字就是colModel中的名字
	    },
	    pager: $("#gridPager"),
	    onPaging: uppage,
	    width: $('.container').width(), // 这个宽度不能为百分比
	    // 排序
	    onSortCol: function(index, iCol, sortorder) {},
	    onSelectRow: function(id) {}, // 点击某行时的操作
	    editurl: "Admin/saveEdit"
	});
	// 显示增删改查按钮
//	$("#list").jqGrid('navGrid', "#gridPager", {
//		edit : true, add : true, del : true, search : true
//	});
	$(document).on('click', '.modal-content #submit', function() {
		var obj = $('.content #condition').first();
		var qs = "";
		var s = "";
		while (true) {
			if (obj.find("#index").length > 0) {
				switch (parseInt(obj.find("#index option:selected").index())) {
					case 0:k = "dept";break;
					case 1:k = "major";break;
					case 2:k = "cls";break;
					case 3:k = "sno";break;
					case 4:k = "sname";break;
					case 5:k = "sex";break;
					case 6:k = "provincefrom";break;
					case 7:k = "cityfrom";break;
					case 8:k = "districtfrom";break;
					case 9:k = "degree";break;
					case 10:k = "gratime";break;
					case 11:k = "provincework";break;
					case 12:k = "citywork";break;
					case 13:k = "districtwork";break;
					case 14:k = "workunit";break;
					case 15:k = "duty";break;
					case 16:k = "job";break;
					case 17:k = "profession";break;
					case 18:k = "phone1";break;
					case 19:k = "phone2";break;
					case 20:k = "mail";break;
					case 21:k = "qq";break;
					case 22:k = "address";break;
					case 23:k = "zip";break;
					case 24:k = "note";break;
					case 25:k = "assoc1";break;
					case 26:k = "assoc1job";break;
					case 27:k = "assoc2";break;
					case 28:k = "assoc2job";break;
				}
				switch (parseInt(obj.find("#eq option:selected").index())) {
					case 0:v = " = '" + obj.find("input").val() + "'";break;
					case 1:v = " != '" + obj.find("input").val() + "'";break;
					case 2:v = " LIKE '%" + obj.find("input").val() + "%'";break;
				}
				s += obj.find("#index").val() + " " + obj.find("#eq").val() + " '" + obj.find("input").val() + "' ";
				qs += k + v + "&";
			} else break;
			obj = obj.next();
		}
		if(qs==""){
			s="全部";
			qs="all";
		}
		$('#show').html("查询条件:"+s);
		$("#list").jqGrid("setGridParam", {
			datatype : 'json', postData : {
				'val' : qs
			},
		}).trigger("reloadGrid");
	});
	$(window).resize(function() {
		$("#list").setGridWidth($('.container').width());
	});
	$(document).on('click','.modal-content #add',function() {
	    $(this).before("<div id=\"condition\" class=\"input-group\"><select id=\"index\"><option>学院</option><option>专业</option><option>班级</option><option>学号</option><option>姓名</option><option>性别</option><option>生源地省</option><option>生源地市</option><option>生源地区</option><option>学历</option><option>毕业时间</option><option>工作省份</option><option>工作市</option><option>工作地区</option><option>工作单位</option><option>职务</option><option>职称</option><option>行业</option><option>联系电话</option><option>固定电话</option><option>邮箱</option><option>QQ号</option><option>通讯地址</option><option>邮编</option><option>备注</option><option>所属校友总会</option><option>校友总会职务</option><option>所属校友分会</option><option>校友分会职务</option></select><select id=\"eq\"><option>=</option><option>!=</option><option>like</option></select><input><span id=\"del\" class=\"glyphicon glyphicon-remove\" title=\"删除本行条件\"></span></div>");
	});
	$(document).on('click', '.modal-content #del', function() {
		$(this).parent().remove();
	});
	function uppage(pgButton) {
		var page = jQuery("#gridTable").jqGrid('getGridParam', 'page');
		jQuery("#gridTable").setGridParam({
			page : page
		}).trigger("reloadGrid");
	}
	//-----------------------------------------------------------导入--------------------------------------
	$('.amm #import').click(function(){
		$('.modal-content').html("<div class=\"row\"><div class=\"col-xs-12\"><div class=\"title\">导入Excel到数据库</div><div class=\"content\"><input id=\"file\" type=\"file\" name=\"excelFile\"></div><hr><button onclick=\"etm();\" class=\"btn btn-default\" data-dismiss=\"modal\">提交</button><div id=\"tip\">选择*.xls文件导入到数据库</div></div></div>");
	});
	function etm() {
		// "数据导入处理中……请勿关闭或刷新页面。";
		var formdata = new FormData();
		var fileObj = document.getElementById("file").files;
		formdata.append("excelFile", fileObj[0]);
		$.ajax({
			type : 'POST', url : 'Admin/ExcelToMysql', data : formdata, contentType : false, processData : false, success : function(res) {
				document.getElementById("uploadRes").innerHTML = res;
			}
		});
	}
	/*
	function ajaxContent(url) {
		$('.content').load(url);
	}
	function updatePwd() {
		var newpwd = document.getElementById("newpwd").value;
		var newpwd2 = document.getElementById("newpwd2").value;
		if (newpwd == newpwd2) {
			$('#updatepwd').submit();
		}
	}*/
	$('#statistics #condition').change(function(){
		var s="";
		switch(parseInt($(this).find("option:selected").index())){
			case 1:s="asdf";break;
			case 2:break;
			case 3:break;
			//case 4:break;
			case 5:break;
			case 6:break;
			default:s="";
		}
		$('#statistics #detail').html(s);
	});
	$('#statistics button').click(function(){
		switch(parseInt($(this).parent().find("option:selected").index())){
			case 0:alert("请选择统计条件");break;
			case 4:
				$.ajax({
					url:"Admin/getFromStatisticsAll",
					type:"post",
					dataType:"json",
					success:function(data){
						$("#res").html(data);
					},
					error:function(){
						alert("请求失败");
					}
				});
				break;
			default:alert("未完成");
		}
	});
