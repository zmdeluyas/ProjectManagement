<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container" id="projadtlinfo-div">
	<div class="panel panel-primary box">
		<div class="panel-heading box">Project Period</div>
		<div class="panel-body" id="proj-period">
			<div class="container-fluid">
				<div class="row" style="margin-left: 0px;">
					<div class="col-xs-4">
						<label for="projPSD" class="top5 common-label">Planned
							Start Date</label>
						<div class="input-group date date-picker" id="psdPicker">
							<input id="projPSD" type="text"
								class="form-control common-input date"> <span
								class="input-group-addon date"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					<div class="col-xs-4">
						<label for="projASD" class="top5 common-label">Actual
							Start Date</label>
						<div class="input-group date date-picker" id="asdPicker">
							<input id="projASD" type="text"
								class="form-control common-input date"> <span
								class="input-group-addon date"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					<div class="col-xs-4">
						<label for="projRegion" class="top5 common-label"
							style="width: 20%;">Region</label> <input id="projRegion"
							type="text"
							class="form-control common-input pad-right-25 common-editable-fields"
							style="width: 60%" readonly="readonly"> <span
							id="seachProjRegion"
							class="glyphicon glyphicon-search common-search"
							data-toggle="modal" data-target="#projRegPopupModal"></span>
					</div>
				</div>
				<div class="row top10" style="margin-left: 0px;">
					<div class="col-xs-4">
						<label for="projPFD" class="top5 common-label">Planned
							Finish Date</label>
						<div class="input-group date date-picker" id="pfdPicker">
							<input id="projPFD" type="text"
								class="form-control common-input date"> <span
								class="input-group-addon date"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					<div class="col-xs-4">
						<label for="projACD" class="top5 common-label">Actual
							Complete Date</label>
						<div class="input-group date date-picker" id="acdPicker">
							<input id="projACD" type="text"
								class="form-control common-input date"> <span
								class="input-group-addon date"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					<div class="col-xs-4">
						<label for="projPhase" class="top5 common-label"
							style="width: 20%;">Phase</label> <select
							class="form-control common-input" id="projPhase"
							style="width: 60%;">
							<option value=""></option>
							<option value="Project">Project</option>
							<option value="Implementation">Implementation</option>
							<option value="Support and Maintenance">Support and
								Maintenance</option>
							<option value="Enhancement">Enhancement</option>
						</select>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-6" id="projInfra">
			<div class="panel panel-primary box">
				<div class="panel-heading box">Infrastructure</div>
				<div class="panel-body">
					<div class= "container-fluid" >
						<div class="row top10">
							<div class="col-xs-1"></div>
							<div class="col-xs-10">
								<label for="projOS" class="top5 common-label">Operating
									System</label> <input id="projOS" type="text"
									class="form-control common-input common-editable-fields"
									readonly="readonly"> <span id="seachProjOS"
									class="glyphicon glyphicon-search common-search"
									data-toggle="modal" data-target="#projOSPopupModal"></span>
							</div>
						</div>
						<div class="row top10">
							<div class="col-xs-1"></div>
							<div class="col-xs-10">
								<label for="projMW" class="top5 common-label">Middleware</label>
								<input id="projMW" type="text"
									class="form-control common-input common-editable-fields"
									readonly="readonly"> <span id="seachProjMW"
									class="glyphicon glyphicon-search common-search"
									data-toggle="modal" data-target="#projMWPopupModal"></span>
							</div>
						</div>
						<div class="row top10">
							<div class="col-xs-1"></div>
							<div class="col-xs-10">
								<label for="projApp" class="top5 common-label">Application</label>
								<input id="projApp" type="text"
									class="form-control common-input common-editable-fields"
									readonly="readonly"> <span id="seachProjApp"
									class="glyphicon glyphicon-search common-search"
									data-toggle="modal" data-target="#projAppPopupModal"></span>
							</div>
						</div>
						<!-- <div class="row top10">
							<div class="col-xs-1"></div>
							<div class="col-xs-10">
								<label for="projRepo" class="top5 common-label">Repository</label>
								<input id="projRepo" type="text"
									class="form-control common-input common-editable-fields">
							</div>
						</div> -->
						<div class="row top10">
							<div class="col-xs-1"></div>
							<div class="col-xs-5" style="padding-right: 0px; width: 45%">
								<div class="form-group" style="margin-bottom: 4px;">
									<label for="projCPU" class="top5 common-label"
										style="width: 48%; margin-bottom: 0px;">CPU/s</label> <select
										class="form-control common-input" id="projCPU"
										style="width: 46%;">
										<option value=""></option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
									</select>
								</div>
							</div>
							<div class="col-xs-5">
								<div class="form-group" style="margin-bottom: 4px;">
									<label for="projMemory" class="top5 common-label"
										style="width: 32%; margin-bottom: 0px;">Memory</label> <select
										class="form-control common-input" id="projMemory"
										style="width: 55%;">
										<option value=""></option>
										<option value="1024">1GB</option>
										<option value="2048">2GB</option>
										<option value="4096">4GB</option>
										<option value="8192">8GB</option>
										<option value="16384">16GB</option>
										<option value="32768">32GB</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-6" id="projcost-col">
			<div class="panel panel-primary box">
				<div class="panel-heading box">Project Cost</div>
				<div class="panel-body">
					<div class="container-fluid">
						<div class="row top30">
							<div class="col-xs-1"></div>
							<div class="col-xs-10">
								<label for="projTotBudget" class="common-label">Total
									Budget</label> <input id="projTotBudget" type="text"
									class="form-control common-input currency" value="0.00">
							</div>
						</div>
						
						<!-- Added By: Bryan Balino 11/17/2017 -->
						<div class="row top17" id="projCostStatus">
							<div class="col-xs-1"></div>
							<div class="col-xs-10">
								<label class="common-label">Status</label> 
								<label class="common-label"><input id="rBtnApproved" type="radio" name="optradio" value="1">Approved</label>
								<label class="common-label"><input id="rBtnDisApproved" type="radio" name="optradio" value="0">Disapproved</label>
							</div>
						</div>
						
						<!-- Removed By: Bryan Balino 11/17/2017 -->
						<!-- <div class="row top17">
							<div class="col-xs-1"></div>
							<div class="col-xs-10">
								<label for="projBudToDate" class="top5 common-label">Budget
									to Date</label> <input id="projBudToDate" type="text"
									class="form-control common-input currency" value="0.00">
							</div>
						</div>
						<div class="row top17">
							<div class="col-xs-1"></div>
							<div class="col-xs-10">
								<label for="projActToDate" class="top5 common-label">Actual
									to Date</label> <input id="projActToDate" type="text"
									class="form-control common-input currency" value="0.00">
							</div>
						</div> -->
						
						<div class="row top17">
							<!-- <div class="col-xs-12" style="text-align:center;">
								<button id="projCostDtls" type="button" class="btn btn-default">Cost Details</button>
							</div> -->
						</div>
						
						<div class="row top17"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="additionalInformation-div">
		<div class="panel panel-primary box">
			<div class="panel-heading box">Additional Information</div>
			<div class="panel-body">
				<div class="container-fluid">
					<div class="row">
						<div class="col-xs-7">
							<label for="projRemarks" class="top5"
								style="width: 11%; font-weight: normal;">Remarks</label> <input
								id="projRemarks" type="text"
								class="form-control common-input pad-right-25"> <span
								id="projRemarksEditor"
								class="glyphicon glyphicon-pencil common-search"
								data-toggle="modal" data-target="#"></span>
						</div>
						<div class="col-xs-5">
							<label class="top5 common-label">Attach File/s:</label> <a
								href="#uploadModal" class="btn btn-info" id="uploadBtn"
								data-toggle="modal" data-target="#uploadModal"><span 
								class="glyphicon glyphicon-paperclip"></span></a> 
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row margin-bottom-20">
			<div class="col-xs-12" style="text-align: center;">
				<div class="btn-group hide" id="grpbtnApproval">
					<button class="btn btn-default" style="height: .36in;"
						type="button" id="drpdownApproval" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="true">
						<span class="caret"></span>
					</button>
					<!-- ROCHELLE -->
					<!-- <ul class="dropdown-menu" aria-labelledby="drpdownApproval">
			    <li><a id="dropApproved"><span id="approved" class="glyphicon glyphicon-ok" style="color: black;"></span>Approved</a></li>
			    <li><a id="dropRejected"><span id="rejected" class="glyphicon glyphicon-ok" style="color: white;"></span>Rejected</a></li>
			  </ul> -->
					<button class="btn btn-default" data-toggle="dropdown"
						style="width: 1.25in; text-align: left;">
						<span class="glyphicon glyphicon-check"></span>&nbsp;<span
							id="lblFilter">&nbsp;</span>
					</button>
				</div>
				<button id="projSaveBtn" type="button" class="btn btn-success">Save</button>
				<!-- <button id="projSubmitBtn" type="button" class="btn btn-default">Submit</button> -->
				<button id="projCancelBtn" type="button" class="btn btn-danger">Cancel</button>
			</div>
		</div>
	</div>
</div>
<jsp:include page="popup/upload.jsp"></jsp:include>
<script>
	initDatePicker();
	initCurrency();
	initProjBtn();
</script>
<jsp:include page="popup/projRegPopup.jsp"></jsp:include>
<jsp:include page="popup/projOSPopup.jsp"></jsp:include>
<jsp:include page="popup/projMiddlewarePopup.jsp"></jsp:include>
<jsp:include page="popup/projAppPopup.jsp"></jsp:include>
<jsp:include page="popup/upload.jsp"></jsp:include>

<c:if test="${projNo != 0}">
	<script>
		var projPeriod = $.parseJSON(nvl('${projPeriod}', '{}'));
		var projInfra = $.parseJSON(nvl('${projInfra}', '{}'));
		var projCost = $.parseJSON(nvl('${projCost}', '{}'));
		var projAdtlInfo = {
			"projPeriod" : projPeriod,
			"projInfra" : projInfra,
			"projCost" : projCost
		};
		populateProjAdtlInfo(projAdtlInfo);
		// 		loadProjStatusMain('projadtlinfo-div', projNo);
	</script>
</c:if>