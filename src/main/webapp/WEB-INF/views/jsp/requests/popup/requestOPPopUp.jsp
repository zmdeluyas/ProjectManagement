<!-- created by SHARIE MANIPON 11.21.2017 -->
<div id="requestOPsPopupModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="requestOPsPopupModal" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button id="closeOperationManagerPBtn" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">Operation Manager</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-xs-12">
          	<table id="OPPopupTb" class="table table-hover common-table table-sm table-striped table-bordered dt-responsive nowrap" style="width: 100%">
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
        <button id="okOPPbtn" type="button" class="btn btn-default">Ok</button>
        <button id="closeOperationManagerPBtn" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
	oppopup = $.parseJSON(nvl('${OPManagerList}', '[]')); 
	initOPPopUpVariable();
    initPopupDt(popUpOPModal,$oppopuptb,oppopup,assignedOPs,opUserId,opEmail,'okOPPbtn',"closeOperationManagerPBtn");
</script>