<div id="requestQAPopupModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="requestQAPopupModal" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button id="closeQAP" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">QA Manager</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-xs-12">
          	<table id="QAPopupTb" class="table table-hover common-table table-sm table-striped table-bordered dt-responsive nowrap" style="width: 100%">
				<thead>
					<tr>
						<th>Name</th>
						<th>Position</th>
					</tr>
				</thead>
			</table>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button id="okQAnalystPbtn" type="button" class="btn btn-default">Ok</button>
        <button id="closeQAnalystPbtn" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
	qaManagerPopUp = $.parseJSON(nvl('${qaManagerList}', '[]'));
 	initQAPopUpVariable();
 	initPopupDt(popUpQAModal,$qapopuptb,qaManagerPopUp,assignedQA,qaUserId,qaEmail,'okQAnalystPbtn','closeQAnalystPbtn');
</script>