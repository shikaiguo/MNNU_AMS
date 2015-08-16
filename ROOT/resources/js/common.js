$(function() {
	$('.header .nav .menu .item,.header .nav .nav-name').hover(
			function() {
				$(this).children('.pitem').show();
				$(this).children('.glyphicon-chevron-down').addClass(
						'glyphicon-chevron-up').removeClass(
						'glyphicon-chevron-down');
			},
			function() {
				$(this).children('.pitem').hide();
				$(this).children('.glyphicon-chevron-up').addClass(
						'glyphicon-chevron-down').removeClass(
						'glyphicon-chevron-up');
			});
	$('.header .nav .menu-button').click(function() {
		var obj = $('.header .nav .menu');
		obj.toggle();
	});
	$('#query1').click(function(){
		$('.modal-content').load("Admin/queryDialog");
	});
});
function etm() {
	// document.getElementById("etm").submit();
	// document.getElementById("etm-block").innerHTML =
	// "数据导入处理中……请勿关闭或刷新页面。";
	var formdata = new FormData();
	var fileObj = document.getElementById("file").files;
	formdata.append("excelFile", fileObj[0]);
	$.ajax({
		type : 'POST',
		url : 'Admin/ExcelToMysql',
		data : formdata,
		contentType : false,
		processData : false,
		success : function(res) {
			document.getElementById("uploadRes").innerHTML = res;
		}
	});
}
function ajaxContent(url) {
	$('.content').load(url);
}
function updatePwd() {
	var newpwd = document.getElementById("newpwd").value;
	var newpwd2 = document.getElementById("newpwd2").value;
	if (newpwd == newpwd2) {
		$('#updatepwd').submit();
	}
}
$('.dropdown-toggle').dropdown();