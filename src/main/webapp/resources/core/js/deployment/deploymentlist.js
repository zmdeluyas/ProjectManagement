var $deploymentlist;
var $deploymentlistdt;

function initDeploymentList(){
	$deploymentlist = $('#deploymentlist');
	$deploymentlist.dataTable({
		data: deploymentlist,
		columns: [{
			data: 'reqNo'
		}, {
			data: 'summary'
		}, {
			data: 'projNoName'
		}, {
			data: 'dateSubmitted'
		}, {
			data: 'status'
		}],
		columnDefs : [ {
			targets : [ 0 ],
			width : "58px",
			className : "text-center"
		},{
			targets : [ 3 ]/*,
			type : "date-eu"*/
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
		openReqInfo(rowData.reqNo.substring(2), rowData.projNoName.substring(0,6),"deployment");
	});
}

/*function openReqInfo2(reqNo, projNo){
	loadProjInfo(null, projNo, 'openReqInfo');
	loadProjAdtlInfo('projinfo-div', projNo);
	loadReqInfo('projadtlinfo-div', reqNo);
	makeFieldsUneditable(); //SHA
	$('#projcost-col').remove();
	$("#additionalInformation-div").remove();
	loadReqStatusMain('reqinfo-div', reqNo);
	$back.removeClass('hide');
}*/

function initDeploymentButtons(){
	if (useraccess == "op"){
		$("#btnDeploy").removeAttr("disabled");
		$("#btnDeploy").removeClass("hide");
		$("#reqSaveBtn").addClass("hide");
	}
	
	$("#btnDeploy").click(function(){
		var host = $("#txtHostAddress").val();
		var port = $("#txtHostPort").val();
		var userame = $("#txtWsUsername").val();
		var password = $("#txtWsPassword").val();
		if((host == "" || host == null) || (port == "" || port == null) || (username == "" || username == null) || (password == "" || password == null)){
			alert("Please fill out all fields!!");
		} else {
			//var param = {"reqNo":$("#reqNo").val(),"currRsNo":9};
			$.ajax({
				url: contextPath + "/jenkins/deploy/status",
				method: "POST",
				data : {
					reqNo: $("#reqNo").val(),
					currRsNo: 9
				},
				async: false,
				success: function(){
					
				}
			});
		}
	});
}

