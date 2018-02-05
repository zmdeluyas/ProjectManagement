function saveReqAttachment(){
	$('#btnSave').click(function(){
		var reqNo = $('#reqNo').val();
		var formData = new FormData();
	    $.each($('#fileUpload')[0].files, function(k, value)
	            {
	                formData.append(k, value);
	            });
/*	 if(fileUpload == "" || fileUpload == null){
	    	alert("Please select file.");
	    } else if(/^[0-9a-zA-Z-_. ]+$/.test(fileUpload) == false) {
	    	alert("Your filename contains illegal characters. Please rename it before uploading.");
	    } else{
	    	//$('.modal').css({"z-index":"1040"});
	    	//$('#waitingModal').css({"z-index":"1041"});  
	    	alert("Please wait until files successfully uploaded.")*/
	      $.ajax({
	            type: "POST",
	            url : contextPath + "/request/upload?reqNo="+reqNo,
	            async: true,
	            data: formData,
	            enctype: 'multipart/form-data',
	            contentType: false,
	            processData: false,
	            success: function(msg) {
	            	alert("Success!");
	            	/*$("#fileDiv").empty();*/
	            	getReqAttachmentList(reqNo);
	            },
	            error:function(msg) {
	                //$("#upload-error").html("Couldn't upload file");
	            	alert("Error");
	            }
	        });
	   // }
	}); 
}

function saveProjAttachment(){
	$('#btnSave').click(function(){
		var projNo = $('#projNo').val();
		var formData = new FormData();
	    $.each($('#fileUpload')[0].files, function(k, value)
	            {
	                formData.append(k, value);
	            });
/*	 if(fileUpload == "" || fileUpload == null){
	    	alert("Please select file.");
	    } else if(/^[0-9a-zA-Z-_. ]+$/.test(fileUpload) == false) {
	    	alert("Your filename contains illegal characters. Please rename it before uploading.");
	    } else{
	    	//$('.modal').css({"z-index":"1040"});
	    	//$('#waitingModal').css({"z-index":"1041"});  
	    	alert("Please wait until files successfully uploaded.")*/
	      $.ajax({
	            type: "POST",
	            url : contextPath + "/project/upload?projNo="+projNo,
	            async: true,
	            data: formData,
	            enctype: 'multipart/form-data',
	            contentType: false,
	            processData: false,
	            success: function(msg) {
	            	alert("Success!");
	            	//$("#fileDiv").empty();
	            	getProjAttachmentList(projNo);
	                
	            },
	            error:function(msg) {
	                //$("#upload-error").html("Couldn't upload file");
	            	alert("Error");
	            }
	        });
	   // }
	}); 
}