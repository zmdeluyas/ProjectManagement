<div id="projManagerPopupModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="projManagerPopupModal" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header modal-header-primary">
        <button id="closePMP" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">Project Manager</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-xs-12">
          	<table id="projManagerPopupTb" class="table table-hover common-table table-sm table-striped dt-responsive nowrap" style="width: 100%">
				<thead class="tblHead">
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
        <button id="okPMPbtn" type="button" class="btn btn-info">Ok</button>
        <button id="closePMP" type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
	projmanagerpopup = $.parseJSON(nvl('[{"pmName": "Karnik", "position": "Senior Project Manager"},' + 
										'{"pmName": "Roman", "position": "Senior Project Manager"},' +
										'{"pmName": "Steven", "position": "Senior Project Manager"},' +
										'{"pmName": "David", "position": "Senior Project Manager"},' +
										'{"pmName": "Susan", "position": "Senior Project Manager"}]', '[]'));
	initProjManagerPopup();
</script>