<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'PwdFind.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="resource/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="resource/css/admin.css">
  </head>
  
  <body>
 	<div class="container">
      <div class="row head">
			<div class="col-xs-12">
				<img src="resource/images/head.png" class="banner">
			</div>
			<div class="col-xs-12" style="	margin-top:5px; border-top:5px solid #a72b29; position:relative; z-index:2147483647;"></div>    
       </div>
       <div class="col-xs-12 col-md-12" >
       <div class="col-xs-12" id="emailVerify" style="display: block">
         <h2>验证邮箱：</h2>
         <div class="col-xs-12 col-md-offset-1">
                  <h4>验证码已发送至您的邮箱asdhjns@163.com.</h4><br>
                  请输入邮箱验证码：<input type="text">&nbsp;<input type="submit" style="display:inline-block;" onclick="submitVerify()">
         </div>
       </div>
       <div class="col-xs-12 col-md-4  text-center col-md-offset-4" style="display: none; border:2px solid #ddd;" id="changePsw">
         设置新密码：<input type="password"><br>
         确认新密码：<input type="password"><br>
         <input type="submit">
       </div>
       </div>
     </div>
  <script type="text/javascript">
  function submitVerify(){
	   document.getElementById("emailVerify").style.display="none";
	   document.getElementById("changePsw").style.display="block";	   
}
  </script>
  </body>
</html>
