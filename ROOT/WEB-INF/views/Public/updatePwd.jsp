<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style>
	.modal-dialog{
		width:350px;
	}
	.update-pwd .content{
		padding:0 auto;
	}.update-pwd .content>div{
		width:calc(5em +  200px);
	}
	.update-pwd .content>div span{
		display:inline-block;
		width:5em;
	}
	.update-pwd .content>div input{
		width:200px;
	}
</style>
<div class="row update-pwd">
    <div class="col-xs-12">
        <div class="title">修改密码</div>
        <div class="content row center-block">
        	<div>用户：${username}</div>
        	<div><span>当前密码：</span><input type="password"  id="oldpwd" maxLength="16"></div>
        	<div><span>新密码：</span><input type="password" id="newpwd" maxLength="16"></div>
        	<div><span>确认密码：</span><input type="password" id="newpwd2" maxLength="16"></div>
        </div>
        <hr>
        <button class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="submit" class="btn btn-default">提交</button>
        <div id="tip">密码请小心保存</div>
    </div>
</div>
<script>
	$('#submit').click(function(){
		var opwd=$('#oldpwd').val();
		var npwd=$('#newpwd').val();
		var npwd2=$('#newpwd2').val();
		if(opwd.length==0||npwd.length==0||npwd2.length==0){
			$('#tip').css("color","red");
			$('#tip').text("请输入完整");
		}else if(npwd.length<4||npwd.length<4||npwd2.length<4){
			$('#tip').css("color","red");
			$('#tip').text("密码过短");
		}
		else if(npwd!=npwd2){
			$('#tip').css("color","red");
			$('#tip').text("两次输入的密码不一致");
		}
		else{
			$.ajax({
				url:"Public/updatePwd",
				type:"post",
				data:"opwd="+opwd+"&npwd="+npwd,
				success:function(res){
					if(res==1){
						$('#tip').css("color","green");
						$('#tip').text("修改成功");
						return;
					}
					$('#tip').css("color","red");
					$('#tip').text("修改密码失败，请重试，多次失败请联系管理员");
				}
			});
		}
	});
</script>