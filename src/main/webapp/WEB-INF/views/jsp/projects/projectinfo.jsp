<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container" id="projinfo-div">
	<div class="panel panel-primary box">
		<div class="panel-heading box">Project Information</div>
		<div class="panel-body">
			<div class= "container-fluid">
				<div class="row margin-left-35 margin-right-minus-35">
					<div class="col-xs-5">
						<label for="projNo" class="top5 common-label">Project No.</label>
						<input id="projNo" type="text" class="form-control common-input pad-right-25" style="width: 45% !important;" readonly="readonly">
						<span id="seachProj" class="glyphicon glyphicon-search common-search" data-toggle="modal" data-target="#projListPopupModal"></span>
						<label for="projNo" class="top5 margin-left-20" style="font-weight: normal;">Health</label>
						<span id="projInfoHealth" class="img-responsive proj-health margin-left-15"style=" display:inline-block; position: relative; top: 5px;"></span>
					</div>
					<div class="col-xs-1"></div>
					<div class="col-xs-5">
						<label for="createdBy" class="top5 common-label">Created by</label>
						<!-- <input id="createdBy" type="text" class="form-control common-input"  style="width: 45% !important;" readonly="readonly"> -->
						<input id="createdBy" type="text" class="form-control common-input" readonly="readonly">
						<button id="projMoreInfoBtn" type="button" class="btn btn-default hide">More Information</button>
					</div>
				</div>
				<div class="row top10 margin-left-35 margin-right-minus-35">
					<div class="col-xs-5">
						<label for="projName" class="top5 common-label">Project Name</label>
						<input id="projName" type="text" class="form-control common-input" readonly="readonly">
					</div>
					<div class="col-xs-1"></div>
					<div class="col-xs-5">
						<label for="projManager" class="top5 common-label">Project Manager</label>
						<input type="hidden" id="assignedPmEmail">
						<input id="projManager" type="text" class="form-control common-input common-editable-fields" readonly="readonly">
						<span id="seachProjManager" class="glyphicon glyphicon-search common-search hide" data-toggle="modal" data-target="#projManagerPopupModal"></span>
					</div>
				</div>
				<div class="row top10 margin-left-35 margin-right-minus-35">
					<div class="col-xs-5">
						<label for="bussinessUnit" class="top5 common-label">Business Unit</label>
						<input id="bussinessUnit" type="text" class="form-control common-input common-editable-fields" readonly="readonly">
						<span id="seachbusUnit" class="glyphicon glyphicon-search common-search hide" data-toggle="modal" data-target="#businessUModal"></span>
					</div>
					<div class="col-xs-1"></div>
					<div class="col-xs-5">
						<label for="projStatus" class="top5 common-label">Status</label>
						<input id="projStatus" type="text" class="form-control common-input common-editable-fields" readonly="readonly">
						<span id="seachStatus" class="glyphicon glyphicon-search common-search hide" data-toggle="modal" data-target="#projStatusPopupModal"></span>
					</div>
				</div>
				<div class="row top10 margin-left-35" style="margin-right: -41px;">
					<div class="col-xs-11">
						<label for="projDesc" class="top5" style="width: 11%; font-weight: normal;">Description</label>
						<input id="projDesc" type="text" class="form-control common-input" style="width: 87.5% !important;" readonly="readonly">
						<span id="projDescEditor" class="glyphicon glyphicon-pencil common-search hide" data-toggle="modal" data-target="#"></span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="savingSuccess" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="savingSuccess" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button id="closeBUP" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">Saving</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-xs-12" id="saveProjNo">
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button id="closeBUP" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<c:if test="${projNo == null && 'createReq' == page}">
	<jsp:include page="popup/projListPopup.jsp"></jsp:include>
	<!-- SHARIE -->
	<jsp:include page="popup/projManagerPopup.jsp"></jsp:include>
	<jsp:include page="popup/businessUPopup.jsp"></jsp:include>
	<jsp:include page="popup/projStatusPopup.jsp"></jsp:include>
</c:if>
<c:if test="${(projNo == null && 'addProj' == page) || (projNo != null && 'updateProj' == page)}">
	<jsp:include page="popup/projHealthPopup.jsp"></jsp:include>
	<jsp:include page="popup/projManagerPopup.jsp"></jsp:include>
	<jsp:include page="popup/businessUPopup.jsp"></jsp:include>
	<jsp:include page="popup/projStatusPopup.jsp"></jsp:include>
</c:if>