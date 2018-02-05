<div id="projHealthPopupModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="projHealthPopupModal" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header madal-header-primary">
        <button id="closePHP" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">Project Health</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-xs-12">
          	<table id="projHealthPopupTb" class="table table-hover common-table table-sm table-striped dt-responsive nowrap" style="width: 100%">
				<thead class="tblHead">
					<tr>
						<th>Health</th>
						<th>Description</th>
					</tr>
				</thead>
			</table>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button id="okPHPbtn" type="button" class="btn btn-info">Ok</button>
        <button id="closePHP" type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
	projhealthpopup = $.parseJSON(nvl('[{"health":"Completed", "desc":"Healthy / On-Track"},{"health":"Failed", "desc":"Critical"},{"health":"On-going", "desc":"Warning"}]', '[]'));
	initProjHealthPopup();
</script>