<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
</div><!-- pageContent end -->
<!-- 页脚 -->
<div class="row footer">
	<div class="col-xs-12">
		<div class=" footer-span">CopyRight &copy; 闽南师范大学 2015</div>
	</div>
</div>

<button class="modal-button" style="display:none" data-toggle="modal" data-target=".bs-example-modal-lg"></button>
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">...</div>
	</div>
</div>
</div><!-- container end -->
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript">
	function modalShow(url) {
		$('.modal-content').load(url);
		$('.modal-button').click();
	}
	function changePage(url) {
		$(".pageContent").load(url);
	}
</script>
</body>
</html>
