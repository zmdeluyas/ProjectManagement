<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container" id="reqinfo-div">
	<div class="panel panel-primary">
		<div class="panel-heading">Create Request</div>
		<div class="panel-body">
			<div class= "container-fluid">
				<div class="row">
					<div class="col-xs-3">
						<label for="reqNo" class="top5 common-label" style="width: 38.5% !important;">Request No.</label>
						<input id="reqNo" type="text" class="form-control common-input" readonly="readonly" style="width:50% !important;">
					</div>
					<div class="col-xs-4">
						<label for="dateSubmitted" class="top5 common-label" style="width: 29% !important;">Date Submitted</label>
						<input id="dateSubmitted" type="text" class="form-control common-input" readonly="readonly" style="width:55.5% !important;">
					</div>
					<div class="col-xs-5">
						<label for="ravBy" class="top5 common-label" style="width: 40% !important;">Reviewed and Verified by</label>
						<input id="ravBy" type="text" class="form-control common-input" readonly="readonly">
					</div>
				</div>
				<div class="row top10">
					<div class="col-xs-7">
						<label for="reqSummary" class="top5 common-label" style="width: 15% !important;">Summary</label>
						<input id="reqSummary" type="text" class="form-control common-input margin-left-3" style="width: 76% !important;" readonly="readonly">
					</div>
					<div class="col-xs-5">
						<label for="assignedDev" class="top5 common-label" style="width: 40% !important;">Developer Manager</label>
						<input type="hidden" id="assignedDevId" value="">
						<input type="hidden" id="assignedDevEmail" value="">
						<input id="assignedDev" type="text" class="form-control common-input" readonly="readonly">
						<span id="seachDeveloper" class="glyphicon glyphicon-search common-search" data-toggle="modal" data-target="#requestDevPopupModal"></span>
					</div>
				</div>
				<div class="row top10">
					<div class="col-xs-7">
						<label for="reqDesc" class="top5 common-label" style="width: 15% !important; vertical-align: top;">Description</label>
						<textarea id="reqDesc" class="form-control common-textarea margin-left-3" style="width: 76% !important;" readonly="readonly"></textarea>
					</div>
						<div class="col-xs-5">
							<label for="assignedBA" class="top5 common-label" style="width: 40% !important;">Business Analyst</label>
							<input type="hidden" id="assignedBAId" value="">
							<input type="hidden" id="assignedBAEmail" value="">
							<input id="assignedBA" type="text" class="form-control common-input" readonly="readonly">
							<span id="searchBA" class="glyphicon glyphicon-search common-search" data-toggle="modal" data-target="#requestBAPopupModal"></span>
							
							<label for="assignedQA" class="top5 common-label" style="width: 40% !important;">QA Manager</label>
							<input type="hidden" id="assignedQAId" value="">
							<input type="hidden" id="assignedQAEmail" value="">
							<input id="assignedQA" type="text" class="form-control common-input" readonly="readonly" style="margin-top: 7px;">
							<span id="searchQA" class="glyphicon glyphicon-search common-search" data-toggle="modal" data-target="#requestQAPopupModal"></span>
						<!-- <label for="reqRequestor" class="top5 common-label" style="width: 39% !important;">Requestor</label>
						<input id="reqRequestor" type="text" class="form-control common-input margin-left-3" readonly="readonly"> -->
					</div>
					
				</div>
				<div class="row top10">
					<div class="col-xs-7">
						<label for="reqRemarks" class="top5 common-label" style="width: 15% !important;">Remarks</label>
						<input id="reqRemarks" type="text" class="form-control common-input margin-left-3" style="width: 76% !important;" readonly="readonly">
					</div>
					<div class="col-xs-5">
						<label for="assignedOPs" class="top5 common-label" style="width: 40% !important;">Operation Manager</label>
						<input type="hidden" id="assignedOPsId" value="">
						<input type="hidden" id="assignedOPsEmail" value="">
						<input id="assignedOPs" type="text" class="form-control common-input" readonly="readonly">
						<span id="searchOPs" class="glyphicon glyphicon-search common-search" data-toggle="modal" data-target="#requestOPsPopupModal"></span>
						
					</div>
				</div>
				<div class="row top10">
					<div id="attachDiv" class="col-xs-7">
						<label class="top5 common-label" style="width: 15% !important;">Attach File/s:</label>
						<input id="reqFile" type="file" class="form-control-file common-input" style="margin-top: 5px;">
						<a href="#uploadModal" class="btn btn-info"  id="uploadBtn" data-toggle="modal" data-target="#uploadModal"><span class="glyphicon glyphicon-paperclip" ></span></a>
					</div>
					<div class="col-xs-5">
						<label for="reqRequestor" class="top5 common-label" style="width: 39.5% !important;">Requestor</label>
						<input id="reqRequestor" type="text" class="form-control common-input margin-left-3" readonly="readonly">
					</div>
				</div>
				<div class="row top10">
					<div class="col-xs-12" style="text-align: center;">
						<button id="reqSaveBtn" type="button" class="btn btn-success" disabled="disabled">Submit</button>
  						<button id="reqUpdateBtn" type="button" class="btn btn-primary" disabled="disabled">Update</button>
  						<button id="reqCancelBtn" type="button" class="btn btn-danger">Cancel</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="popup/requestDeveloperPopup.jsp"></jsp:include>
<jsp:include page="popup/requestBAPopUp.jsp"></jsp:include>
<jsp:include page="popup/requestQAPopup.jsp"></jsp:include>
<jsp:include page="popup/requestOPPopUp.jsp"></jsp:include> <!-- added by SHARIE MANIPON 11.21.2017 -->
<jsp:include page="popup/upload.jsp"></jsp:include>
<script>
	initProjBtn();
	initReqInfo();
</script>