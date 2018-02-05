<div id="projRegPopupModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="projRegPopupModal" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header modal-header-primary">
        <button id="closePRP" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">Region</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-xs-12">
          	<table id="projRegPopupTb" class="table table-hover common-table table-sm table-striped dt-responsive nowrap" style="width: 100%">
				<thead class="tblHead">
					<tr>
						<th>Region</th>
						<th>Description</th>
					</tr>
				</thead>
			</table>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button id="okPRPbtn" type="button" class="btn btn-info">Ok</button>
        <button id="closePRP" type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
	projregpopup = $.parseJSON(nvl('[{"regName": "NCSA", "regDesc": "North, Central and South America"},{"regName": "NORAM", "regDesc": "North America (Canada, the United States of America and Mexico)"},{"regName": "LATAM", "regDesc": "Latin America"},{"regName": "EMEA", "regDesc": "Europe, the Middle East and Africa"},{"regName": "APAC  ", "regDesc": "Asia-Pacific North"},{"regName": "SEA", "regDesc": "South-East Asia"}]', '[]'));
	initProjRegPopup();
</script>