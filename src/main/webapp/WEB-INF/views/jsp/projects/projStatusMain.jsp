<div class="container" id="projstatusmain-div">
	<div class="panel panel-default">
		<div class="panel-heading">Project Status</div>
		<div class="panel-body">
			<div class="container-fluid" id="proj-status-body">
				<%-- <div class="row">
					<jsp:include page="reqStatus.jsp"></jsp:include>
				</div> --%>
			</div>
		</div>
	</div>
</div>
<div id="timer-div" class="hide"></div>
<script>
	proj_status_jsp = '<jsp:include page="projStatus.jsp"></jsp:include>';
	projHist = $.parseJSON(nvl('${projHist}', '[]'));
</script>