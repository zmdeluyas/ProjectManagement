<div id="proj-plan-infra" class="container">
	<div class="row">
		<div class="col-xs-6">
			<div class="panel panel-default">
				<div class="panel-heading">Project Period </div>
				<div class="panel-body">
					<table id="periodlisttb" class="table table-sm table-striped table-bordered dt-responsive nowrap" style="width: 100%">
						<thead>
							<tr>
								<th>Start Date</th>
								<th>Finish Date</th>
								<th>Region</th>
								<th>Phase</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
		<div class="col-xs-6">
		<div class="panel panel-default">
				<div class="panel-heading">Infrastructure</div>
				<div class="panel-body">
					<table id="infralisttb" class="table table-sm table-striped table-bordered dt-responsive nowrap" style="width: 100%">
						<thead>
							<tr>
								<th>Operating System</th>
								<th>Middleware</th>
								<th>Application</th>
								<th>CPUs and Memory</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	initPeriodList();
	initInfraList();
</script>