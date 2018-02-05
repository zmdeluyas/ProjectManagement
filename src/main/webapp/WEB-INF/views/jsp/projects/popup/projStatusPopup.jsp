<div id="projStatusPopupModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="projStatusPopupModal" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header modal-header-primary">
        <button id="closePLP" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">Project Status</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-xs-12">
          	<table id="projStatusPopupTb" class="table table-hover common-table table-sm table-striped dt-responsive nowrap" style="width: 100%">
				<thead class="tblHead">
					<tr>
						<th>Project Status</th>
					</tr>
				</thead>
			</table>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button id="okPSPbtn" type="button" class="btn btn-info">Ok</button>
        <button id="closePSP" type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
	projstatuspopup = $.parseJSON(nvl('[{"projStatus": "Assigned Project Manager"},{"projStatus": "For Budget"},{"projStatus": "Planning"},{"projStatus": "On-going Development"},{"projStatus": "System Integration Testing"},{"projStatus": "User Acceptance Testing"},{"projStatus": "Delivered"},{"projStatus": "For Enhancement"},{"projStatus": "Maintenance and Support"}]', '[]'));
	initProjStatusPopup();
</script>