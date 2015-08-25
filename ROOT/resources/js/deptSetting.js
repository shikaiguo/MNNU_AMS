$('.dept-setting #add').click(function() {
	var html = "学院代号：<input id=\"deptid\" name=\"id\">";
	html += "学院名称：<input id=\"deptname\" name=\"name\">";
	html += "<span class=\"glyphicon glyphicon-remove\" title=\"取消\"></span>";
	html += "<button class=\"btn btn-primary submit\">确定</button>";
	$('.dept-setting #handle').html(html);
});
$(document).on('click', '.dept-setting .glyphicon-remove', function() {
	$('.dept-setting #handle').html("");
});
$(document).on('click', '.dept-setting .submit', function() {
	var id = document.getElementById("deptid").value;
	var name = document.getElementById("deptname").value;
	var obj = $('.dept-list').find('div').first();
	while (obj.length > 0) {
		var i = obj.find('span').first().text();
		if (parseInt(id) < parseInt(i)) {
			obj.before('<div><input type=\"checkbox\"><span>' + id + '</span><span>' + name + '</span></div>');
			break;
		}
		if(obj.next().length<=0){
			obj.after('<div><input type=\"checkbox\"><span>' + id + '</span><span>' + name + '</span></div>');
			break;
		}
		obj = obj.next();
	}
	
});