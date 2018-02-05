<table id="requestlist" class="table table-hover common-table table-sm dt-responsive nowrap" style="width: 100%">
	<thead class="tblHead">
		<tr>
			<th>Request No.</th>
			<th>Request Summary</th>
			<th>Project No. - Project Name</th>
			<th>Date Submitted</th>
			<th>Status</th>
		</tr>
	</thead>
</table>
<script>
	requestlist = $.parseJSON(nvl('${requestList}', '[]'));
	initRequestList();
</script>