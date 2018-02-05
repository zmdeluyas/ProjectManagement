<div id="projListPopupModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="projListPopupModal" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header modal-header-primary">
        <button id="closePLP" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">Projects</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-xs-12">
          	<table id="projListPopupTb" class="table table-hover common-table table-sm table-striped dt-responsive nowrap" style="width: 100%">
				<thead class="tblHead">
					<tr>
						<th>Project No.</th>
						<th>Project Name</th>
					</tr>
				</thead>
			</table>
          </div>
        </div>
      </div>
      <div class="modal-footer">
      	<button id="newProjPLPbtn" type="button" class="btn btn-primary">New Project</button>
        <button id="okPLPbtn" type="button" class="btn btn-info">Ok</button>
        <button id="closePLP" type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
	projlistpopup = $.parseJSON(nvl('${projListPopup}', '[]'));
	initProjListPopup();
</script>