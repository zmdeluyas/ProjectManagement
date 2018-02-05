var $projectlist;
var $projectlistdt;
var $projlistpopuptb;
var $projlistpopupdt
var $periodlisttb;
var $infralisttb;
var $infralistdt;
var projRepo; //ARviC

var projectlist = [];
var projlistpopup = [];
var periodlist = [];
var infralist = [];

function initProjectList(){
	$projectlist = $('#projectlist');
	$projectlist.dataTable({
		data: projectlist,
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
             /* 	EDIT: RJVILLARUZ
              * {
                 text: 'Request Project',
                 action: function ( e, dt, node, config ) {
                	 loadAddProj();
                 },
                 enabled: useraccess == 'pm'
             }
             
             */
         ],
	     dom: "<'row'<'col-xs-3'f><'col-xs-6'><'col-xs-3'B>><'row'<'col-xs-12'tr>><'row'<'col-xs-6'><'col-xs-6'p>>"
	});
	$projectlistdt = $projectlist.DataTable();
	$('#projectlist tbody').on('dblclick', 'tr', function(){
		var rowData = $projectlistdt.row(this).data();
		loadUpdateProj(rowData.projNo);
	});
}

function initProjListPopup(){
	initProjPopupDt();
	initPLPopupBtn();
}

function initProjPopupDt(){
	$projlistpopuptb = $('#projListPopupTb');
	$projlistpopuptb.dataTable({
		data: projlistpopup,
		columns: [{
			data: 'projNo'
		}, {
			data: 'name'
		}],
		columnDefs : [ {
			targets : [ 0 ],
			className : "text-center"
		} ],
	    language: {
	         search: "_INPUT_",
	         searchPlaceholder: "Search"
	    },
	     dom: "<'row'<'col-xs-3'f><'col-xs-6'><'col-xs-3'>><'row'<'col-xs-12'tr>><'row'<'col-xs-6'><'col-xs-6'p>>"
	});
	$projlistpopupdt = $projlistpopuptb.DataTable();
	$('#projListPopupTb tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			$(this).addClass('selected').siblings().removeClass("selected");
		}
	}).on('dblclick', 'tr', function(){
		var rowData = $projlistpopupdt.row(this).data();
		$projlistpopupdt.$('tr.selected').removeClass('selected');
		$('#projListPopupModal').modal('toggle');
		projPopupActions(rowData.projNo);
	});
	
	
}

function initPLPopupBtn(){
	$('button[id="closePLP"]').each(function(){
		$(this).click(function(){
			$projlistpopupdt.$('tr.selected').removeClass('selected');
		});
	});
	
	$('#okPLPbtn').click(function(){
		var rowData = $projlistpopupdt.row($projlistpopupdt.$('tr.selected')).data();
		$('#projListPopupModal').modal('toggle');
		$projlistpopupdt.$('tr.selected').removeClass('selected');
		projPopupActions(rowData.projNo);
	});
	console.log('useraccess = ' + useraccess);
	/*if(useraccess == 'ba'){
		$('#newProjPLPbtn').attr('disabled', true);
	}*/
	// ROCHELLENEW
	$('#newProjPLPbtn').click(function(){
		
		$('#projListPopupModal').modal('toggle');
		$('.modal-backdrop').removeClass();
		createProjRequest();
		$back.removeClass('hide');
	});
	
	
}

function initPeriodList(){
	$periodlisttb = $('#periodlisttb');
	$periodlisttb.dataTable({
		data: periodlist,
		ordering: false,
		columns: [{
			data: 'plannedStartDate'
		}, {
			data: 'plannedFinishDate'
		}, {
			data: 'region'
		}, {
			data: 'phase'
		}],
	     dom: "<'row'<'col-xs-12'>><'row'<'col-xs-12'tr>><'row'<'col-xs-6'><'col-xs-6'>>"
	});
	$periodlistdt = $periodlisttb.DataTable();
}

function initInfraList(){
	$infralisttb = $('#infralisttb');
	$infralisttb.dataTable({
		data: infralist,
		columns: [{
			data: 'os'
		}, {
			data: 'middleware'
		}, {
			data: 'application'
		}, {
			data: 'cpuMemory'
		}],
	     dom: "<'row'<'col-xs-12'>><'row'<'col-xs-12'tr>><'row'<'col-xs-6'><'col-xs-6'>>"
	});
	$infralistdt = $infralisttb.DataTable();
}

function reloadPeriodList(data){
	$periodlistdt.clear();
	$periodlistdt.rows.add(nvl(data, []));
	$periodlistdt.draw();
}

function reloadInfraList(data){
	//projRepo = data[0]["repository"];
	$infralistdt.clear();
	$infralistdt.rows.add(nvl(data, []));
	$infralistdt.draw();
}

function projPopupActions(projNo){
	
	populateProjInfo(getProjInfByNo(projNo));
	/*reloadPeriodList(listPeriod(projNo));
	reloadInfraList(listInfra(projNo));
	disableReqSave(false);
	if(useraccess == "bu") {
		disableInvolved(true);
		disableReqInfo(false);
	} else {
		disableInvolved(false);
		disableReqInfo(false);
	}*/
	
	/*added by SHARIE MANIPON 11.22.2017*/
	loadProjAdtlInfo('projinfo-div', projNo);
	loadReqInfo('projadtlinfo-div');
	$back.removeClass('hide');
	
	if(useraccess != "pm"){
		$('#projCostStatus').remove();
	}
	
	$('#seachProj').hide();
	$('#seachProjManager').removeClass('hide');
	$('#seachbusUnit').removeClass('hide');
	$('#seachStatus').removeClass('hide');
	userPreviliges();
	disableReqSave(false);
	if(useraccess == "bu") {
		disableInvolved(true);
		disableReqInfo(false);
	} else {
		disableInvolved(false);
		disableReqInfo(false);
	}
	
	$("#additionalInformation-div").remove();
}

var $projmanagerpopuptb;
var $projmanagerpopupdt;
var projmanagerpopup = [];

function initProjManagerPopup(){
	initPMPPopupDt();
	initPMPopupBtn();
}

function initPMPPopupDt(){
	$projmanagerpopuptb = $('#projManagerPopupTb');
	$projmanagerpopuptb.dataTable({
		data: projmanagerpopup,
		columns: [{
			data: 'pmName'
		}, {
			data: 'position'
		}],
	     language: {
	         search: "_INPUT_",
	         searchPlaceholder: "Search"
	     },
	     dom: "<'row'<'col-xs-3'f><'col-xs-6'><'col-xs-3'>><'row'<'col-xs-12'tr>><'row'<'col-xs-6'><'col-xs-6'p>>"
	});
	$projmanagerpopupdt = $projmanagerpopuptb.DataTable();
	$('#projManagerPopupTb tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			$(this).addClass('selected').siblings().removeClass("selected");
		}
	}).on('dblclick', 'tr', function(){
		var rowData = $projmanagerpopupdt.row(this).data();
		$projmanagerpopupdt.$('tr.selected').removeClass('selected');
		$('#projManagerPopupModal').modal('toggle');
		$('#projManager').val(rowData.pmName);
	});
}

function initPMPopupBtn(){
	$('button[id="closePMP"]').each(function(){
		$(this).click(function(){
			$projmanagerpopupdt.$('tr.selected').removeClass('selected');
		});
	});
	
	$('#okPMPbtn').click(function(){
		var rowData = $projmanagerpopupdt.row($projmanagerpopupdt.$('tr.selected')).data();
		$('#projManagerPopupModal').modal('toggle');
		$projmanagerpopupdt.$('tr.selected').removeClass('selected');
		if(nvl(rowData, '') != '') $('#projManager').val(rowData.pmName);
	});
}

var $businessupopuptb;
var $businessupopupdt;
var businessupopup = [];

function initBusinessUPopup(){
	initBUPPopupDt();
	initBUPopupBtn();
}

function initBUPPopupDt(){
	$businessupopuptb = $('#businessUPopupTb');
	$businessupopuptb.dataTable({
		data: businessupopup,
		columns: [{
			data: 'buName'
		}],
	     language: {
	         search: "_INPUT_",
	         searchPlaceholder: "Search"
	     },
	     dom: "<'row'<'col-xs-3'f><'col-xs-6'><'col-xs-3'>><'row'<'col-xs-12'tr>><'row'<'col-xs-6'><'col-xs-6'p>>"
	});
	$businessupopupdt = $businessupopuptb.DataTable();
	$('#businessUPopupTb tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			$(this).addClass('selected').siblings().removeClass("selected");
		}
	}).on('dblclick', 'tr', function(){
		var rowData = $businessupopupdt.row(this).data();
		$('#businessUModal').modal('toggle');
		$businessupopupdt.$('tr.selected').removeClass('selected');
		$('#bussinessUnit').val(rowData.buName);
	});
}

function initBUPopupBtn(){
	$('button[id="closeBUP"]').each(function(){
		$(this).click(function(){
			$businessupopupdt.$('tr.selected').removeClass('selected');
		});
	});
	
	$('#okBUPbtn').click(function(){
		var rowData = $businessupopupdt.row($businessupopupdt.$('tr.selected')).data();
		$('#businessUModal').modal('toggle');
		$businessupopupdt.$('tr.selected').removeClass('selected');
		if(nvl(rowData, '') != '') $('#bussinessUnit').val(rowData.buName);
	});
}

var $projstatuspopuptb;
var $projstatuspopupdt;
var projstatuspopup = [];

function initProjStatusPopup(){
	initPSPPopupDt();
	initPSPopupBtn();
}

function initPSPPopupDt(){
	$projstatuspopuptb = $('#projStatusPopupTb');
	$projstatuspopuptb.dataTable({
		data: projstatuspopup,
		columns: [{
			data: 'projStatus'
		}],
	     language: {
	         search: "_INPUT_",
	         searchPlaceholder: "Search"
	     },
	     dom: "<'row'<'col-xs-3'f><'col-xs-6'><'col-xs-3'>><'row'<'col-xs-12'tr>><'row'<'col-xs-6'><'col-xs-6'p>>"
	});
	$projstatuspopupdt = $projstatuspopuptb.DataTable();
	$('#projStatusPopupTb tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			$(this).addClass('selected').siblings().removeClass("selected");
		}
	}).on('dblclick', 'tr', function(){
		var rowData = $projstatuspopupdt.row(this).data();
		$projstatuspopupdt.$('tr.selected').removeClass('selected');
		$('#projStatusPopupModal').modal('toggle');
		$('#projStatus').val(rowData.projStatus);
	});
}

function initPSPopupBtn(){
	$('button[id="closePSP"]').each(function(){
		$(this).click(function(){
			$projstatuspopupdt.$('tr.selected').removeClass('selected');
		});
	});
	
	$('#okPSPbtn').click(function(){
		var rowData = $projstatuspopupdt.row($projstatuspopupdt.$('tr.selected')).data();
		$('#projStatusPopupModal').modal('toggle');
		$projstatuspopupdt.$('tr.selected').removeClass('selected');
		if(nvl(rowData, '') != '') $('#projStatus').val(rowData.projStatus);
	});
}

var $projregpopuptb;
var $projregpopupdt;
var projregpopup = [];

function initProjRegPopup(){
	initPRPPopupDt();
	initPRPopupBtn();
}

function initPRPPopupDt(){
	$projregpopuptb = $('#projRegPopupTb');
	$projregpopuptb.dataTable({
		data: projregpopup,
		columns: [{
			data: 'regName'
		},{
			data: 'regDesc'
		}],
	     language: {
	         search: "_INPUT_",
	         searchPlaceholder: "Search"
	     },
	     dom: "<'row'<'col-xs-3'f><'col-xs-6'><'col-xs-3'>><'row'<'col-xs-12'tr>><'row'<'col-xs-6'><'col-xs-6'p>>"
	});
	$projregpopupdt = $projregpopuptb.DataTable();
	$('#projRegPopupTb tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			$(this).addClass('selected').siblings().removeClass("selected");
		}
	}).on('dblclick', 'tr', function(){
		var rowData = $projregpopupdt.row(this).data();
		$projregpopupdt.$('tr.selected').removeClass('selected');
		$('#projRegPopupModal').modal('toggle');
		$('#projRegion').val(rowData.regName);
	});
}

function initPRPopupBtn(){
	$('button[id="closePRP"]').each(function(){
		$(this).click(function(){
			$projregpopupdt.$('tr.selected').removeClass('selected');
		});
	});
	
	$('#okPRPbtn').click(function(){
		var rowData = $projregpopupdt.row($projregpopupdt.$('tr.selected')).data();
		$('#projRegPopupModal').modal('toggle');
		$projregpopupdt.$('tr.selected').removeClass('selected');
		if(nvl(rowData, '') != '') $('#projRegion').val(rowData.regName);
	});
}

var $projospopuptb;
var $projospopupdt;
var projospopup = [];

function initProjOSPopup(){
	initPOSPPopupDt();
	initPOSPopupBtn();
}

function initPOSPPopupDt(){
	$projospopuptb = $('#projOSPopupTb');
	$projospopuptb.dataTable({
		data: projospopup,
		columns: [{
			data: 'os'
		}],
	     language: {
	         search: "_INPUT_",
	         searchPlaceholder: "Search"
	     },
	     dom: "<'row'<'col-xs-3'f><'col-xs-6'><'col-xs-3'>><'row'<'col-xs-12'tr>><'row'<'col-xs-6'><'col-xs-6'p>>"
	});
	$projospopupdt = $projospopuptb.DataTable();
	$('#projOSPopupTb tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			$(this).addClass('selected').siblings().removeClass("selected");
		}
	}).on('dblclick', 'tr', function(){
		var rowData = $projospopupdt.row(this).data();
		$projospopupdt.$('tr.selected').removeClass('selected');
		$('#projOSPopupModal').modal('toggle');
		$('#projOS').val(rowData.os);
	});
}

function initPOSPopupBtn(){
	$('button[id="closePOSP"]').each(function(){
		$(this).click(function(){
			$projospopupdt.$('tr.selected').removeClass('selected');
		});
	});
	
	$('#okPOSPbtn').click(function(){
		var rowData = $projospopupdt.row($projospopupdt.$('tr.selected')).data();
		$('#projOSPopupModal').modal('toggle');
		$projospopupdt.$('tr.selected').removeClass('selected');
		if(nvl(rowData, '') != '') $('#projOS').val(rowData.os);
	});
}

var $projmwpopuptb;
var $projmwpopupdt;
var projmwpopup = [];

function initProjMWPopup(){
	initPMWPPopupDt();
	initPMWPopupBtn();
}

function initPMWPPopupDt(){
	$projmwpopuptb = $('#projMWPopupTb');
	$projmwpopuptb.dataTable({
		data: projmwpopup,
		columns: [{
			data: 'middleware'
		}],
	     language: {
	         search: "_INPUT_",
	         searchPlaceholder: "Search"
	     },
	     dom: "<'row'<'col-xs-3'f><'col-xs-6'><'col-xs-3'>><'row'<'col-xs-12'tr>><'row'<'col-xs-6'><'col-xs-6'p>>"
	});
	$projmwpopupdt = $projmwpopuptb.DataTable();
	$('#projMWPopupTb tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			$(this).addClass('selected').siblings().removeClass("selected");
		}
	}).on('dblclick', 'tr', function(){
		var rowData = $projmwpopupdt.row(this).data();
		$projmwpopupdt.$('tr.selected').removeClass('selected');
		$('#projMWPopupModal').modal('toggle');
		$('#projMW').val(rowData.middleware);
	});
}

function initPMWPopupBtn(){
	$('button[id="closePMWP"]').each(function(){
		$(this).click(function(){
			$projmwpopupdt.$('tr.selected').removeClass('selected');
		});
	});
	
	$('#okPMWPbtn').click(function(){
		var rowData = $projmwpopupdt.row($projmwpopupdt.$('tr.selected')).data();
		$('#projMWPopupModal').modal('toggle');
		$projmwpopupdt.$('tr.selected').removeClass('selected');
		if(nvl(rowData, '') != '') $('#projMW').val(rowData.middleware);
	});
}

var $projapppopuptb;
var $projapppopupdt;
var projapppopup = [];

function initProjAppPopup(){
	initPAPPopupDt();
	initPAPopupBtn();
}

function initPAPPopupDt(){
	$projapppopuptb = $('#projAppPopupTb');
	$projapppopuptb.dataTable({
		data: projapppopup,
		columns: [{
			data: 'app'
		}],
	     language: {
	         search: "_INPUT_",
	         searchPlaceholder: "Search"
	     },
	     dom: "<'row'<'col-xs-3'f><'col-xs-6'><'col-xs-3'>><'row'<'col-xs-12'tr>><'row'<'col-xs-6'><'col-xs-6'p>>"
	});
	$projapppopupdt = $projapppopuptb.DataTable();
	$('#projAppPopupTb tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			$(this).addClass('selected').siblings().removeClass("selected");
		}
	}).on('dblclick', 'tr', function(){
		var rowData = $projapppopupdt.row(this).data();
		$projapppopupdt.$('tr.selected').removeClass('selected');
		$('#projAppPopupModal').modal('toggle');
		$('#projApp').val(rowData.app);
	});
}

function initPAPopupBtn(){
	$('button[id="closePAP"]').each(function(){
		$(this).click(function(){
			$projapppopupdt.$('tr.selected').removeClass('selected');
		});
	});
	
	$('#okPAPbtn').click(function(){
		var rowData = $projapppopupdt.row($projapppopupdt.$('tr.selected')).data();
		$('#projAppPopupModal').modal('toggle');
		$projapppopupdt.$('tr.selected').removeClass('selected');
		if(nvl(rowData, '') != '') $('#projApp').val(rowData.app);
	});
}

var $projhealthpopuptb;
var $projhealthpopupdt;
var projhealthpopup = [];

function initProjHealthPopup(){
	initPHPPopupDt();
	initPHPopupBtn();
}

function initPHPPopupDt(){
	$projhealthpopuptb = $('#projHealthPopupTb');
	$projhealthpopuptb.dataTable({
		data: projhealthpopup,
		columns: [{
			data: 'health',
			render : function(data, type, row) {
				if ( data == 'Completed' ){
					return '<span class="img-responsive proj-health health-g"></span>';
				}else if ( data == 'Failed' ){
					return '<span class="img-responsive proj-health health-r"></span>';
				}else if ( data == 'On-going' ){
					return '<span class="img-responsive proj-health health-y"></span>';
				}
				return '<span class="img-responsive proj-health"></span>';
			}
		},{
			data: 'desc'
		}],
	     language: {
	         search: "_INPUT_",
	         searchPlaceholder: "Search"
	     },
	     dom: "<'row'<'col-xs-3'f><'col-xs-6'><'col-xs-3'>><'row'<'col-xs-12'tr>><'row'<'col-xs-6'><'col-xs-6'p>>"
	});
	$projhealthpopupdt = $projhealthpopuptb.DataTable();
	$('#projHealthPopupTb tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			$(this).addClass('selected').siblings().removeClass("selected");
		}
	}).on('dblclick', 'tr', function(){
		var rowData = $projhealthpopupdt.row(this).data();
		$projhealthpopupdt.$('tr.selected').removeClass('selected');
		$('#projHealthPopupModal').modal('toggle');
		if ( rowData.health == 'Completed' ){
			$('#projInfoHealth').removeClass('health-g health-y health-r').addClass('health-g');
		}else if ( rowData.health == 'Failed' ){
			$('#projInfoHealth').removeClass('health-g health-y health-r').addClass('health-r');
		}else if ( rowData.health == 'On-going' ){
			$('#projInfoHealth').removeClass('health-g health-y health-r').addClass('health-y');
		}
	});
}

function initPHPopupBtn(){
	$('button[id="closePAP"]').each(function(){
		$(this).click(function(){
			$projhealthpopupdt.$('tr.selected').removeClass('selected');
		});
	});
	
	$('#okPHPbtn').click(function(){
		var rowData = $projhealthpopupdt.row($projhealthpopupdt.$('tr.selected')).data();
		$('#projHealthPopupModal').modal('toggle');
		$projhealthpopupdt.$('tr.selected').removeClass('selected');
		if(nvl(rowData, '') != ''){
			if ( rowData.health == 'Completed' ){
				$('#projInfoHealth').removeClass('health-g health-y health-r').addClass('health-g');
			}else if ( rowData.health == 'Failed' ){
				$('#projInfoHealth').removeClass('health-g health-y health-r').addClass('health-r');
			}else if ( rowData.health == 'On-going' ){
				$('#projInfoHealth').removeClass('health-g health-y health-r').addClass('health-y');
			}
		}
	});
}

function prepareProjParameter(){
	var repoParam = {};
	
	repoParam.projName = $("#projName").val().replace(/ /g, "-");
	repoParam.assignedDev = $("#assignedDevEmail").val();
	repoParam.assignedBa = $("#assignedBAEmail").val();
	repoParam.assignedQa = $("#assignedQAEmail").val();
	repoParam.assignedPm = $("#assignedPmEmail").val();
	repoParam.projNo = $("#projNo").val();
	return repoParam;
}

function prepareVMParameter(){
	var vmConfig = {};
	
	vmConfig.operatingSys = $("#projOS").val();
	vmConfig.middleWare = $("#projMW").val();
	vmConfig.cpu = $("#projCPU").val();
	vmConfig.app = $("#projApp").val();
	vmConfig.memory = $("#projMemory").val();
	
	return vmConfig;
}