<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'alumniStatistics.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" 
    href="css/admin.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap.min.css">
 <link rel="stylesheet" type="text/css" 
    href="css/jquery-ui.min.css">
<style>
.ui-widget-header{
     background: #a72b29 url("images/ui-bg_gloss-wave_35_f6a828_500x100.png") 50% 50% repeat-x;
}
.ui-state-active, .ui-widget-content .ui-state-active, .ui-widget-header .ui-state-active {
  border: 1px solid #a72b29;
  background: #fff url("images/ui-bg_glass_65_ffffff_1x400.png") 50% 50% repeat-x;
  font-weight: bold;
  color: #a72b29;
}
.ui-state-highlight, .ui-widget-content .ui-state-highlight, .ui-widget-header .ui-state-highlight {
  border: 1px solid #a72b29;
  background: #a72b29 url("images/ui-bg_highlight-soft_75_ffe45c_1x100.png") 50% top repeat-x;
  color: #363636;
}
</style>
</head>
<body>
	<div class="col-xs-12">
		<span class="select-span">统计条件：</span>
		<div class="sel-all">
			<select id="select_one" class="select-css">
				<option selected="selected">选择条件</option>
				<option>行业</option>
				<option>院级</option>
				<option>学历</option>
				<option>生源地</option>
				<option>工作地区</option>
				<option>新增校友</option>
			</select> <select id="select_second" class="select-css" style="display: none;">
				<option selected="selected">学院选择</option>
			</select> <select id="select_third" class="select-css" style="display: none;">
				<option selected="selected">年级选择</option>
			</select> <select id="select_fouth" class="select-css" style="display: none;">
				<option selected="selected">班级选择</option>
			</select>
			<input type="text" id="beginTime" name="beginTime" class="input-time" readonly="readonly" value="开始时间选择">
			<input type="text" id="endTime" name="endTime"  class="input-time" readonly="readonly" value="截止时间选择">
			<button class="btn btn-primary btn-statistics">统计</button>
		</div>
	</div>
    
    <!-- 统计结果数据显示 -->
    <div class="col-xs-12 col-md-8 col-md-offset-2"">
	</div>
	
	<!-- 图表显示 -->
	<div class="col-xs-12 col-md-8 col-md-offset-2"
		style="margin-top: 15px;">
		<img src="images/test.png" width="100%"> <br>
		<button class="btn btn-primary" style="float: right;">打印</button>
	</div>

	<div></div>

	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script src="js/jquery-1.11.3.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.datepicker-zh-CN.js"></script>
	<script type="text/javascript">
		var sel_OptionVal;
		var options1, options2, options3;
		options1 = $("#select_second")[0].options;
		options2 = $("#select_third")[0].options;
		options3 = $("#select_fouth")[0].options;
		$("#select_one").change(function() {
			sel_OptionVal = $("#select_one").val();
			if (sel_OptionVal == "行业" || sel_OptionVal == "院级") {
				options1[0].text = "选择全校/学院";
				options2[0].text = "选择全部/年级";
				options3[0].text = "选择全部/班级";
				$("#select_second").show();
				$("#select_third").show();
				$("#select_fouth").show();
				$('input[name="beginTime"]').hide();
				$('input[name="endTime"]').hide();
			} else if (sel_OptionVal == "学历") {
				options1[0].text = "入学时间";
				options2[0].text = "毕业时间";
				$("#select_second").show();
				$("#select_third").show();
				$("#select_fouth").hide();
				$('input[name="beginTime"]').hide();
				$('input[name="endTime"]').hide();
			} else if (sel_OptionVal == "生源地" || sel_OptionVal == "工作地区") {
				//根据区或者县显示图表
				$("#select_second").hide();
				$("#select_third").hide();
				$("#select_fouth").hide();
				$('input[name="beginTime"]').hide();
				$('input[name="endTime"]').hide();
			} else if (sel_OptionVal == "新增校友") {
				$("#select_second").hide();
				$("#select_third").hide();
				$("#select_fouth").hide();
				
				//显示统计的时间区间选择
				$('input[name="beginTime"]').show();
				$('input[name="endTime"]').show();
				$( "#beginTime" ).datepicker({dateFormat:"yy-mm-dd",regional:$.datepicker.regional['zh-CN']});
				$( "#endTime" ).datepicker({dateFormat:"yy-mm-dd",regional:$.datepicker.regional['zh-CN']});
			}
		});
	</script>
</body>
</html>
