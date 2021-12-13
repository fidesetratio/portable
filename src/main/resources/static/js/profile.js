$(document).ready(function () {
	

	formProfileCreate();
	uploadImage();	
	
	
	
	
});

function uploadImage(){
var uploadImage = $("#upload-image");
var formUploadImage = $("#fileUploadForm");
var token = $('#_csrf').attr('content');
var header = $('#_csrf_header').attr('content');
var containterModalFooter = $("#container-modal-footer");
var modalReport = $("#modal-report");
uploadImage.on('click',function(e){
		$('#imgupload').trigger('click');
		
});

$("#imgupload").change(function(){
	     var file=$(this).val().replace(/C:\\fakepath\\/ig,'');
	     var displayImage = $("#display-image");
	     var buttonCancel = $("#button-cancel");
	     var buttonSave = $("#button-save");
	     var userId = $("#userId");
	     var form = formUploadImage[0];
	     var data = new FormData(form);
	     var path = "/imgu/"+file;
	     var profileUser = $("#profileUser");
	     var warnmessage = $("#warn-message");
		 var form = $("form#profile-form");
	     buttonCancel.on('click',function(e){
			e.preventDefault(); 
    
	        containterModalFooter.find('#visible-asks').show();
            containterModalFooter.find('#visible-yes').hide();
        		
	     });
	     
	    buttonSave.on('click',function(e){
			e.preventDefault(); 
           	modalReport.modal('hide');
	       	profileUser.attr("src",path);
		    $.ajax({
			type:'POST',
			contentType: "application/json; charset=utf-8",
			url:destinationPictureProfile,
			data:JSON.stringify({"userId":userId.val(),"photo":file}),
			dataType:'json',
			beforeSend: function(xhr) {
                            xhr.setRequestHeader(header, token);
            },
            success: function(data){
            
              var error = data.errors;
              var err = data.error;
              var successMessage = data.message;
	     	
	      	 	form.find('#showalert').find('#successfulMessage').html(successMessage);
				form.find('#showalert').fadeIn().delay(2000).fadeOut('slow');
         
             },
			error: function(e){
				alert(JSON.stringify(e));
			}
			});
	
	        
	        
	        
	        
	        
	        
	        
			
			
			            
	       	
	      });
	     
	     $.ajax({
	        type: "POST",
    	    enctype: 'multipart/form-data',
        	url: "/rest/uploadfile",
        	data: data,
       		beforeSend: function(xhr) {
                          xhr.setRequestHeader(header, token);
        	},
        processData: false,
        contentType: false,
        cache: false,
        timeout: 1000000,
        success: function(data, textStatus, jqXHR) {
            console.log("SUCCESS : ", data);
            displayImage.attr("src",path);
            containterModalFooter.find('#visible-asks').hide();
            containterModalFooter.find('#visible-yes').show();
            
        },
        error: function(jqXHR, textStatus, errorThrown) {  
            console.log("ERROR : ", jqXHR.responseText);

        }
    });
	     
	     
	         
			

});
}


function formProfileCreate(){
var changeProfile = $("#changeProfile");
var containterModalFooter = $("#container-modal-footer");
changeProfile.click(function(e){
    e.preventDefault(); 
    containterModalFooter.find('#visible-asks').show();
    containterModalFooter.find('#visible-yes').hide();
    $("#modal-report").modal('show');
});







var form = $("form#profile-form");
	form.find('button').on('click',function(e){
		e.preventDefault(); 
		var button = $(this);
		button.text('Submitting...');
		var obj = form.jform();
		var token = $('#_csrf').attr('content');
		var header = $('#_csrf_header').attr('content');
		$.ajax({
			type:'POST',
			contentType: "application/json; charset=utf-8",
			url:destination,
			data:obj,
			dataType:'json',
			beforeSend: function(xhr) {
                            xhr.setRequestHeader(header, token);
            },
            success: function(data){
              var error = data.errors;
              var err = data.error;
              var successMessage = data.message;
	      	  button.text('Submit');                            
              form.find('input').each(function(index,item){
						$(item).removeClass('is-invalid');
              });

              if(error>0){
              		if(err.length>0){
							$.each(err, function(index, item){
									var fieldName = item.field;
									var errorMsg = item.errorMsg;
									form.find("input[name="+fieldName+"]").addClass('form-control is-invalid');
									form.find('#'+fieldName+'Error').text(errorMsg);
							});
							
					
					}
              }else{
			               form.find('input').each(function(index,item){
			                		if(index>0){
										$(item).addClass('form-control is-valid');
									};
			            	  });
			            	form.find('#showalert').find('#successfulMessage').html('Your profile has been saved!');
			            	form.find('#showalert').fadeIn().delay(2000).fadeOut('slow');
			            	window.setTimeout(function(){
			            	form.find('input').each(function(index,item){
			                		if(index>0){
										$(item).removeClass('is-valid');
									};
			            	  });
			            	
			            	}, 2000 );  	
            	
              }


			},
			error: function(e){
				alert(JSON.stringify(e));
			}
		});
		
		
		
	});
}