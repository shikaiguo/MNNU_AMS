/**
 * 用户管理
 */
$('.amm #add_user').click(function() {
	str=$(this).text();
	$('.modal-content').html("<div class=\"row add\"><div class=\"col-xs-12\"><div class=\"title\">"+str+"</div><div class=\"content row\"><div class=\"col-xs-12\"><span>用户名：<input id=\"username\" type=\"text\"></span></div><div class=\"col-xs-12\"><span>密码：<input id=\"password\" type=\"password\"></span><span>重复密码：<input id=\"repassword\" type=\"password\"></span></div><div class=\"col-xs-12\"><span>邮箱：<input id=\"email\" type=\"text\"></span><span>绑定校友ID信息：<input id=\"aid\" type=\"text\"></span></div></div><hr><button id=\"submit\" class=\"btn btn-default\">提交</button><div id=\"tip\"></div></div></div>");
});
$(document).on('click', '.modal-content .add #submit', function() {
	username=$.trim($('#username').val());
	password=$.trim($('#password').val());
	repassword=$.trim($('#repassword').val());
	email=$.trim($('#email').val());
	aid=$.trim($('#aid').val());
	if(username==''){
		$('.modal-content .add #tip').html("<span style=\"color:red\">用户名不能为空</span>");
		return;
	}
	if(password==''||password!=repassword||password.length<6){
		$('.modal-content .add #tip').html("<span style=\"color:red\">密码填写不正确</span>");
		return;
	}
	if(confirm("确认添加该用户？")==0){
		return;
	}
	$.ajax({
		url:'Admin/addUser',
		type:'get',
		data:{
			username:username,
			password:password,
			repassword:repassword,
			email:email,
			aid:aid
		},
		success:function(d){
			if(d==-1){
				console.log(-1);
			}else if(d==-2){
				console.log(-2);
			}else if(d==-3){
				console.log(-3);
			}else{
				alert("添加成功");
			}
		}
	});
	$('.modal-bh').modal('toggle');
});