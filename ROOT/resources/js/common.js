$(function() {
	$('.header .nav .menu .item,.header .nav .nav-name').hover(function() {
		$(this).children('.pitem').show();
		$(this).children('.glyphicon-chevron-down').addClass('glyphicon-chevron-up').removeClass('glyphicon-chevron-down');
	}, function() {
		$(this).children('.pitem').hide();
		$(this).children('.glyphicon-chevron-up').addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-up');
	});
	$('.header .nav .menu-button').click(function(){
		var obj=$('.header .nav .menu');
		obj.toggle();
		//if(obj.css('display')=='none')obj.show();else if(obj.css('display')=='block') obj.hide();
	});
});
function ajaxContent(url){
	$('.content').load(url);
}
function updatePwd(){
	var newpwd=document.getElementById("newpwd").value;
	var newpwd2=document.getElementById("newpwd2").value;
	if(newpwd==newpwd2){
		$('#updatepwd').submit();
	}
}
$('.dropdown-toggle').dropdown();