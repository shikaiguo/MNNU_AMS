<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'emailsMenage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/admin.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

  </head>
  <body>
     <div>
       <button class="btn btn-primary"  onclick="changePage('Admin/query')">发邮件</button><span class="note-span">（将跳转至查询界面选择收件人）</span><br>
       <br>
       <div>
       <span class="sel-span">已发送邮件：</span><br>
       <textarea class="textarea-email" id="text-send"></textarea>
       </div>
       <br>
       <div>
       <span class="sel-span">定期推送邮件：</span><br>
       <textarea class="textarea-email" id="text-regular"></textarea>
       </div>       
     </div>
  
  <script type="text/javascript" src="js/bootstrap.min.js"></script>
  </body>
</html>
