<div id="requestDevPopupModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="requestDevPopupModal" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button id="closeDevP" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">Developer</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-xs-12">
          	<table id="developerPopupTb" class="table table-hover common-table table-sm table-striped table-bordered dt-responsive nowrap" style="width: 100%">
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
        <button id="okDevPbtn" type="button" class="btn btn-default">Ok</button>
        <button id="closeDevP" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
	/* developerpopup = $.parseJSON(nvl('[{"devName": "Kathy", "position": "Senior Developer"},'+
	                                    '{"devName": "Kirin", "position": "Senior Developer"},'+
	                                    '{"devName": "Peter", "position": "Senior Developer"},'+
	                                    '{"devName": "Valerie", "position": "Senior Developer"}]', '[]')); */
	developerpopup = $.parseJSON(nvl('${devManagerList}', '[]')); 
    initDevPopUpVariable();
    initPopupDt(popUpDevModal,$devpopuptb,developerpopup,assignedDev,devUserId,emailDev,'okDevPbtn',"closeDevP");
	//initDeveloperPopup();
</script>