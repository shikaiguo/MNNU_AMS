$(document).on('click','tbody tr',function(){
	$(this).toggleClass('checked');
});
function modalInfo(id){
	$('.modal-bh').modal();
	$('.modal-content').load('Admin/simpleUserInfo/'+id);
	event.stopPropagation();
}
	
$('.examine-verify #pass').click(function(){
	var obj=$('.examine-verify table tbody').find('tr').first();
	var count=0;
	var s="";
	while(obj.length>0){
		if(obj.hasClass('checked')==true){
			if(count)
				s+=",";
			s+=obj.find('td').first().text();
			count++;
		}
		obj=obj.next();
	}
	if(count==0){
		alert("请先选中要通过的条目");
		return;
	}
	if(confirm("确认允许选中项的更新？将更新校友信息表！")==false)
		return;
	$.ajax({
		url:'Admin/passEV',
		type:'post',
		data:"arr="+s,
		success:function(res){
			location.reload();
		}
	});
});
$('.examine-verify #refuse').click(function(){
	var obj=$('.examine-verify table tbody').find('tr').first();
	var count=0;
	var s="";
	while(obj.length>0){
		if(obj.hasClass('checked')==true){
			if(count)
				s+=",";
			s+=obj.find('td').first().text();
			count++;
		}
		obj=obj.next();
	}
	if(count==0){
		alert("请先选中要拒绝的条目");
		return;
	}
	if(confirm("确认拒绝选中项的更新？将删除此请求！")==false)
		return;
	$.ajax({
		url:'Admin/refuseEV',
		type:'post',
		data:"arr="+s,
		success:function(res){
			location.reload();
		}
	});
});
$('.examine-verify #flush').click(function(){
	if(confirm("清除修改内容与原内容相同的条目？")==false)
		return;
	$.ajax({
		url:'Admin/flushEV',
		type:'post',
		success:function(){
			location.reload();
		}
	});
});