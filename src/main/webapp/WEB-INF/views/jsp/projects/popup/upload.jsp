<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id= "uploadModal" class="modal fade">
    <div class="modal-dialog">
<div class="modal-content" >
	<div class="modal-header modal-header-info" style="text-align: left;">
		<button id="btnAccountCLose" type="button" class="close"
			data-dismiss="modal" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<div id ="modalLabel"> <h4 class="modal-title" id="myModalLabel"></h4></div>
	</div>
	<div class="modal-body">
		<form id="upload-form" class="upload-box form-group">
			<div>
			<div  id = "fileDiv"  style="height: auto; max-height:300px; overflow:auto; padding: 1em 2em 1em;">
				<%-- <c:if test="${fileNames eq null}">
					<label id="something">No files have been uploaded.</label>
				</c:if>
					<c:forEach var="files" items="${fileNames}">
								<div class="col-md-10">
									<label id="allFiles">${files}</label>
								</div>
					</c:forEach> --%>
			</div>

						<div class="form-group">
							<span>Select File to Upload:</span> 
							<input type="file" name="fileName" id="fileUpload" multiple> 
							<span id="upload-error" class="error">${uploadError}</span>
						</div>				
					</div>
		</form>
		<div class="modal-footer">
			<button class="btn btn-primary" id="btnSave" >Save</button>
			<button type="button" class="btn btn-default" data-dismiss="modal"
				id="btnClose">Close</button>
			<div id="saveErrorMsg"
				style="color: #A94442; font-weight: bold; text-align: center"></div>
		</div>
</div>
</div>
</div>
</div>
<a href="" id="downloadLinkTag"></a>

<script>
	saveProjAttachment();
	$('#modalLabel').text('Project Details');
	console.log($('#modalLabel').val());
	$(document).ready(function() {
		$('.close').click(function() {
			$("body").removeClass("modal-open");
		});
		$('#btnClose').click(function() {
			$("body").removeClass("modal-open");
		});
		/* $("#checkAll").click(function () {
		     $('input:checkbox').not(this).prop('checked', this.checked);
		}); */
	});
</script>
