<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'alumniGarden.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
  <link  type="text/css" rel="stylesheet" href="css/alumniGarden.css">
  </head> 
  <body>
   <div class="container">
    <div class="messagect">
     <div class="toplabel">
      <span><a href="#" >留言者：</a></span>
      <span><a  id="liuyanzhe">hahah</a></span>
     </div>
     <div class="centerlabel">
      <div class="centercontent" id="textcontent">&nbsp;&nbsp; 你的留言信息在这里显示：</div>  
     </div>
     <div class="bottomlabel">
      <ul class="rightcon">
      <li><span><a href="#">回复</a></span></li>
      <li><span><a >时间</a></span></li>
      </ul>
     </div>
     </div>
    </div>
   
   <form role="form">
    <div class="form-group formgroup">
     <label for="name">请输入你的留言</label>
     <textarea class="form-control" rows="3"></textarea>
    </div>
    <div class="btnposition"style="text-align:right">
     <button type="submit" class="btn btn-primary" >提交</button>
    </div>
   </form>

  <div>
    
  </div>
   <script src="js/jquery.min.js"></script>
   <script src="js/bootstrap.min.js"></script>
  </body>
</html>
