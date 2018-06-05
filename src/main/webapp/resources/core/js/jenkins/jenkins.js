function createVM(){
	var vmConfigParam = prepareVMParameter();
	var repParam = prepareProjParameter();
		$.ajax({
		url: contextPath + '/jenkins/vmcreation',
		method: "POST",
		data: {
			vmConfig: JSON.stringify(vmConfigParam),
			repoParam: JSON.stringify(repParam),
			reqNo: $('#reqNo').val()
		},
		async: false,
		success: function(response){
			if(response == "success"){
				console.log("creating repository....");
			} else{
				console.log(false);
			}	
		}
	});
}

function prepareProjParameter(){
	var repoParam = {};
	repoParam.projName = $("#projName").val().replace(/ /g, "");
	repoParam.assignedDev = $("#assignedDevEmail").val();
	repoParam.assignedBa = $("#assignedBAEmail").val();
	repoParam.assignedQa = $("#assignedQAEmail").val();
	repoParam.assignedPm = $("#assignedPmEmail").val();
	repoParam.assignedOm = $("#assignedOPsEmail").val();
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