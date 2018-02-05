<div id="projOSPopupModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="projOSPopupModal" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header madal-header-primary">
        <button id="closePOSP" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">Operating System</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-xs-12">
          	<table id="projOSPopupTb" class="table table-hover common-table table-sm table-striped dt-responsive nowrap" style="width: 100%">
				<thead class="tblHead">
					<tr>
						<th>Operating Systems</th>
					</tr>
				</thead>
			</table>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button id="okPOSPbtn" type="button" class="btn btn-info">Ok</button>
        <button id="closePOSP" type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
	projospopup = $.parseJSON(nvl('[{"os":"Windows Server 2012"},{"os":"Windows Server 2012 R2"},{"os":"Windows Server 2008"},{"os":"Windows Server 2008 R2"},{"os":"Windows 10"},{"os":"Windows 8"},{"os":"Windows 7"},{"os":"Windows Vista"},{"os":"Enterprise Linux 7"},{"os":"Enterprise Linux 6"},{"os":"Enterprise Linux 5"},{"os":"Ubuntu 14.04 LTS"},{"os":"Ubuntu 12.04 LTS"},{"os":"Mac OS X 10.5 Leopard"},{"os":"Mac OS X 10.6 Snow Leopard"},{"os":"Mac OS X 10.7 Lion"}]', '[]'));
	initProjOSPopup();
</script>