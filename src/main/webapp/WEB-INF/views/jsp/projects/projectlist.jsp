<table id="projectlist" class="table table-hover common-table table-sm dt-responsive nowrap bodyBackground" style="width: 100%">
	<thead class="tblHead">
		<tr>
			<th>Project No.</th>
			<th>Project Name</th>
			<th>Business Unit</th>
			<th>Description</th>
			<th>Status</th>
			<th>Project Health</th>
		</tr>
	</thead>
</table>
<script>
	if(nvl('${projNo}', '') == ''){
		projectlist = $.parseJSON(nvl('${projectList}', '[]'));
		initProjectList();
	}
</script>