<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"> 
 <link rel="stylesheet" type="text/css"  href="css/jquery-ui.min.css">
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
<form  id="myform" class="form-horizontal">  
  <div class="form-group" >
  <div class="col-sm-12">
     开始时间：<input type="text" name="dateBegin" id="dateBegin" value="请选择日期..."><br><br>
     发送频率：
     <select>
        <option>每周</option>
        <option>每月</option>
        <option>每年</option>
     </select><br><br>
     结束时间：<input type="text" name="dateEnd" id="dateEnd" value="请选择日期..."><br><br>
     <span style="color: red;">备注：定期发送时间将默认为开始时间后的每周的周一/每月的1号/每年的1月1号</span>
  </div>
  </div>
</form>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/jquery.datepicker-zh-CN.js"></script>
<script type="text/javascript">
$(function() {
	$( "#dateBegin" ).datepicker({dateFormat:"yy-mm-dd",regional:$.datepicker.regional['zh-CN']});
	$( "#dateEnd" ).datepicker({dateFormat:"yy-mm-dd",regional:$.datepicker.regional['zh-CN']});
});
</script>