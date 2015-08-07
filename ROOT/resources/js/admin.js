$(function() {
	$('.nav .menu .item').hover(function() {
		$(this).find('.pitem').show();
	}, function() {
		$(this).find('.pitem').hide();
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