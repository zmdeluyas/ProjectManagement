var $deploymentlist;
var $deploymentlistdt;

function initDeploymentList(){
	$deploymentlist = $('#deploymentlist');
	$deploymentlist.dataTable({
		data: deploymentlist,
		columns: [{
			data: 'projNo'
		}, {
			data: 'name'
		}, {
			data: 'businessUnit'
		}, {
			data: 'desc'
		}, {
			data: 'status'
		}, {
			data: 'health',
			render : function(data, type, row) {
				if ( data == 'Completed' ){
					return '<span class="img-responsive proj-health health-g" title="1"></span>';
				}else if ( data == 'Failed' ){
					return '<span class="img-responsive proj-health health-r" title="3"></span>';
				}else if ( data == 'On-going' ){
					return '<span class="img-responsive proj-health health-y" title="2"></span>';
				}
				return '<span class="img-responsive proj-health"></span>';
			},
			type: 'alt-numeric'
		}],
		columnDefs : [ {
			targets : [ 0, 5 ],
			width : "58px",
			className : "text-center"
		} ],
	     bLengthChange: false,
	     language: {
	         search: "_INPUT_",
	         searchPlaceholder: "Search"
	     },
	     buttons: [
         ],
         dom: "<'row'<'col-xs-1'f><'col-xs-8'><'col-xs-3'B>><'row'<'col-xs-12'tr>><'row'<'col-xs-6'><'col-xs-6'p>>"
	});
	$deploymentlistdt = $deploymentlist.DataTable();
	$('#deploymentlist tbody').on('dblclick', 'tr', function(){
		var rowData = $deploymentlistdt.row(this).data();
		loadUpdateProj(rowData.projNo);
	});
}


