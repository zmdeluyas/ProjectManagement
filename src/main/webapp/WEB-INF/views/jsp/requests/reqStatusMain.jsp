<div class="container" id="reqstatusmain-div">
	<div class="panel panel-primary">
		<div class="panel-heading">Request Status</div>
		<div class="panel-body">
			<div class="container-fluid" id="req-status-body">
				<%-- <div class="row">
					<jsp:include page="reqStatus.jsp"></jsp:include>
				</div> --%>
			</div>
		</div>
	</div>
</div>
<div id="timer-div" class="hide"></div>
<script>
	status_jsp = '<jsp:include page="reqStatus.jsp"></jsp:include>';
	reqHist = $.parseJSON(nvl('${reqHist}', '[]'));
</script>