<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/admin.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<form id="myform" class="form-horizontal">
	<div class="form-group">
		<div class="col-xs-12">
			<select class="col-xs-12 col-sm-4 dialog-select">
               <option>学院</option>
               <option>专业</option>
               <option>班级</option>
               <option>学号</option>
               <option>姓名</option>
               <option>性别</option>
               <option>生源地省</option>
               <option>生源地市</option>
               <option>生源地区</option>
               <option>学历</option>
               <option>毕业时间</option>
               <option>工作省份</option>
               <option>工作市</option>
               <option>工作地区</option>
               <option>工作单位</option>
               <option>职务</option>
               <option>职称</option>
               <option>行业</option>
               <option>联系电话</option>
               <option>固定电话</option>
               <option>邮箱</option>
               <option>QQ号</option>
               <option>通讯地址</option>
               <option>邮编</option>
               <option>备注</option>
               <option>所属校友总会</option>
               <option>校友总会职务</option>
               <option>所属校友分会</option>
               <option>校友分会职务</option>               
            </select>
            <select class="col-xs-12 col-sm-2 dialog-select">
               <option>等于</option>
               <option>不等于</option>
            </select>
            <input type="text" class="col-xs-12 col-sm-4 dialog-input"  placeholder="请输入匹配值....."  id="conditonVal" > 
		</div>
	</div>
</form>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript">
   $("#conditonVal").click(function(){
	   this.Text(null);
   });
</script>