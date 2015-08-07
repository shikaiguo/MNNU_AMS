<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<form  id="myform" class="form-horizontal">  
  <div class="form-group" >
  <div class="col-sm-12">
    <div class="col-sm-12">
    收件人：
    <input type="text" style="width: 100%;">
    </div>
    <div class="col-sm-12">
    主题：
    <input type="text" style="width: 100%;"><br>
    <a href="javascript:void(0)" onclick="">
      <span>添加附件</span>
      <span style="display:none;">继续添加</span>
    </a>
    </div>
    <div class="col-sm-12">
    正文：
    <textarea name="email-textarea" style="width:100%;height:35%;"></textarea>
    </div>
  </div>
  </div>
</form>