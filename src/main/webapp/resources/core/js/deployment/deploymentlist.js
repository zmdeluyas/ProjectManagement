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

function prepareDeployParameters(){
	var deploy = {};
	
	deploy.host = $("#txtHostAddress").val();
	deploy.port = $("#txtHostPort").val();
	deploy.username = $("#txtWsUsername").val();
	deploy.password = $("#txtWsPassword").val();
	deploy.contextPath = $("#txtContextPath").val();
	deploy.projName = $("#projName").val().replace(/ /g,"");
	deploy.reqNo = $("#reqNo").val();
	deploy.omEmail = $("#assignedOPsEmail").val();
	deploy.version = $("#txtVersion").val();
	
	return deploy;
}

function initDeploymentButtons(){
	if (useraccess == "op"){
		$("#btnDeploy").removeAttr("disabled");
		$("#btnDeploy").removeClass("hide");
		$("#reqSaveBtn").addClass("hide");
	}
	
	$("#btnDeploy").click(function(){
		var deploy = prepareDeployParameters();
		if((deploy.host == "" || deploy.host == null) || (deploy.port == "" || deploy.port == null) || (deploy.username == "" || deploy.username == null) || (deploy.password == "" || deploy.password == null) || (deploy.contextPath == "" || deploy.contextPath == null)){
			alert("Please fill out all fields!!");
		} else {
			$.ajax({
				url: contextPath + "/jenkins/deploy",
				method: "POST",
				data : deploy,
				async: false,
				success: function(result){
					if(result = 'success'){
						$('#deployProjNo').html('Request No. ' + $('#reqNo').val() + ' is now being deploy..');
					}else{
						$('#deployProjNo').html('Deploying Request No. ' + $('#projNo').val() + '  failed!');
					}
					$('#deploymentSuccess').modal('show');
					$("#closeDeployPopup").click(function (){
						$('#deploymentSuccess').modal('hide');
						$('body').removeClass('modal-open');
						$("body").removeAttr('style');
						$('.modal-backdrop').remove();
						loadMainPage();
						loadProjectList();
					});
				}
			});
		}
	});
}

