<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<div class="col-xs-12">
	<input id="file" type="file" name="excelFile">
	<input type="button" onclick="etm();" value="提交">
	<div id="uploadRes"></div>
</div>
<%@include file="footer.jsp"%>