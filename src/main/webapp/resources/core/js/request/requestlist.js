var $requestlist;
var $requestlistdt;

var requestlist = [];

function initRequestList(){
	$requestlist = $('#requestlist');
	$requestlist.dataTable({
		data: requestlist,
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
	     },buttons: [
             {
                 text: 'Create a Request',
                 className: 'btn-info btn-md',
                 action: function ( e, dt, node, config ) {
                	 createRequest();
                 }
             }
         ],
	     dom: "<'row'<'col-xs-3'f><'col-xs-6'><'col-xs-3'B>><'row'<'col-xs-12'tr>><'row'<'col-xs-6'><'col-xs-6'p>>"
	});
	$requestlistdt = $requestlist.DataTable();
	$('#requestlist tbody').on('dblclick', 'tr', function(){
		var rowData = $requestlistdt.row(this).data();
		openReqInfo(rowData.reqNo.substring(2), rowData.projNoName.substring(0,6));
	});
}

//NEW -Ronnie
function openReqInfo(reqNo, projNo){
	loadProjInfo(null, projNo, 'openReqInfo');
	loadProjAdtlInfo('projinfo-div', projNo);
	loadReqInfo('projadtlinfo-div', reqNo);
	makeFieldsUneditable(); //SHA
	$("#additionalInformation-div").remove();
	loadReqStatusMain('reqinfo-div', reqNo);
	$back.removeClass('hide');
}

//ARVIC
var $devpopuptb;
var developerpopup = [];
var assignedDev;
var emailDev;
var devUserId;
var popUpDevModal;

var assignedBA;
var emailBA;
var baUserId;
var $bapopuptb;
var bAnalystPopUp = [];
var popUpBAModal;

var assignedQA;
var emailQA;
var qaUserId;
var $qapopuptb;
var qaManagerPopUp = [];
var popUpQAModal;

/* added by SHARIE MANIPON 11.20.2017 */
var $oppopuptb;
var oppopup = [];
var assignedOPs;
var opEmail;
var opUserId;
var popUpOPModal;

function initDevPopUpVariable(){
	$devpopuptb = "developerPopupTb";
 	popUpDevModal = 'requestDevPopupModal';
 	assignedDev = "assignedDev";
 	emailDev = "assignedDevEmail"
 	devUserId = "assignedDevId";
}

function initQAPopUpVariable(){
	$qapopuptb = "QAPopupTb";
 	popUpQAModal = 'requestQAPopupModal';
 	assignedQA = "assignedQA";
 	qaEmail = "assignedQAEmail";
 	qaUserId = "assignedQAId";
}

function initBAPopUpVariable(){
	$bapopuptb = "bAnalystPopupTb";
 	popUpBAModal = 'requestBAPopupModal';
 	assignedBA = "assignedBA";
 	baEmail = "assignedBAEmail";
 	baUserId = "assignedBAId";
}

/*added by SHARIE MANIPON 11.20.2017*/
function initOPPopUpVariable(){
	$oppopuptb = "OPPopupTb";
	popUpOPModal = 'requestOPsPopupModal';
 	assignedOPs = "assignedOPs";
 	opEmail = "assignedOPEmail"
 	opUserId = "assignedOPsId";
}

//remove ARVIC
/*function initPopup(modalName,popuptb,popuptd,assigned,email,okbtn,closebtn){
	initPopupDt(modalName,popuptb,popuptd,assigned,email,okbtn,closebtn);
	//initPopupBtn(modalName,popuptb,assigned,email,okbtn);
}*/

function initPopupDt(modalName,popuptb,popuptd,assigned,txtUserId,email,okbtn,closebtn){
	var popupdt;
	$('#'+popuptb).dataTable({
		data: popuptd,
		columns: [{
			/*data: 'firstName '+ 'lastName'*/
			data: function (data, type, dataToSet) {
		        return data.firstName + " " + data.lastName;
		    }
		}, {
			data: 'position'
		}],
	     language: {
	         search: "_INPUT_",
	         searchPlaceholder: "Search"
	     },
	     dom: "<'row'<'col-xs-3'f><'col-xs-6'><'col-xs-3'>><'row'<'col-xs-12'tr>><'row'<'col-xs-6'><'col-xs-6'p>>"
	});
	popupdt = $('#'+popuptb).DataTable();
	$('#'+popuptb+' tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			$(this).addClass('selected').siblings().removeClass("selected");
		}
	}).on('dblclick', 'tr', function(){
		var rowData = popupdt.row(this).data();
		popupdt.$('tr.selected').removeClass('selected');
		$('#'+modalName).modal('hide');
		//$('#assignedDev').val(rowData.devName);
		$('#'+assigned).val(rowData.firstName+" "+rowData.lastName);
		$('#'+email).val(rowData.email);
		$('#'+txtUserId).val(rowData.userId);
	});
	
	$('#'+okbtn).click(function(){
		var rowData = popupdt.row(popupdt.$('tr.selected')).data();
		$('#'+modalName).modal('hide');
		popupdt.$('tr.selected').removeClass('selected');
		if(nvl(rowData, '') != '') {
			$('#'+assigned).val(rowData.firstName +" "+ rowData.lastName);
			$('#'+email).val(rowData.email);
			$('#'+txtUserId).val(rowData.userId);
		}
	});
	
	$('button[id='+closebtn+']').each(function(){
		$(this).click(function(){
			popupdt.$('tr.selected').removeClass('selected');
		});
	});
}


 
 //Rochelle
/*var $devpopuptb;
var $devpopupdt;
var developerpopup = [];

 function initDeveloperPopup(){
	initDevPopupDt();
	initDevPopupBtn();
}

function initDevPopupDt(){
	$devpopuptb = $('#developerPopupTb');
	$devpopuptb.dataTable({
		data: developerpopup,
		columns: [{
			data: 'firstName'
		}, {
			data: 'position'
		}],
	     language: {
	         search: "_INPUT_",
	         searchPlaceholder: "Search"
	     },
	     dom: "<'row'<'col-xs-3'f><'col-xs-6'><'col-xs-3'>><'row'<'col-xs-12'tr>><'row'<'col-xs-6'><'col-xs-6'p>>"
	});
	$devpopupdt = $devpopuptb.DataTable();
	$('#developerPopupTb tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			$(this).addClass('selected').siblings().removeClass("selected");
		}
	}).on('dblclick', 'tr', function(){
		var rowData = $devpopupdt.row(this).data();
		$devpopupdt.$('tr.selected').removeClass('selected');
		$('#requestDevPopupModal').modal('toggle');
		//$('#assignedDev').val(rowData.devName);
		$('#assignedDev').val(rowData.firstName);
		$('#assignedDevEmail').val(rowData.email);
	});
}

function initDevPopupBtn(){
	$('button[id="closeDevP"]').each(function(){
		$(this).click(function(){
			$devpopupdt.$('tr.selected').removeClass('selected');
		});
	});
	
	$('#okDevPbtn').click(function(){
		var rowData = $devpopupdt.row($devpopupdt.$('tr.selected')).data();
		$('#requestDevPopupModal').modal('toggle');
		$devpopupdt.$('tr.selected').removeClass('selected');
		if(nvl(rowData, '') != '') $('#assignedDev').val(rowData.devName);
	});
}
*/