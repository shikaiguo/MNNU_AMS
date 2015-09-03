//子菜单控制
$('.header .nav .menu .item').hover(function() {
	$(this).children('.pitem').show();
}, function() {
	$(this).children('.pitem').hide();
});
//
function modalShow(url) {
	$('.modal-content').load(url);
}