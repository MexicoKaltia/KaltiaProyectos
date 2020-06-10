/**
 * Archivo de control JS para la pagina de Modulo 
 */

/*
 * funciones para Upload Files
 */
$(document).ready(function(){
	

	
	
});
  function imagenCliente(archivosCampo, rfcCliente){
	  var $idCliente = rfcCliente
	  $idCliente = $(rfcCliente).val();
	                
	        var files = archivosCampo.files;
	        for (var i = 0; i < files.length; i++) {           
	            var file = files[i];
	            enviaImagen(archivosCampo, $idCliente);
	            }
//	        console.log(archivosCampo.file.name);
//	        console.log($(archivosCampo).val());
	        }
  
  function archivoCliente(archivosCampo, rfcCliente){
	  var $idCliente = rfcCliente
	  $idCliente = $(rfcCliente).val();
      enviaFile(archivosCampo, $idCliente);
      var files = archivosCampo.files;
      for (var i = 0; i < files.length; i++) {           
          var file = files[i];
          console.log(file);
          
          }        
      }


	 
	  
//	  $("#archivosCliente").fileinput({
//	        browseClass: "btn btn-info",
//	        browseLabel: "Seleccionar Archivos",
//	        browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
//	        removeClass: "btn btn-warning",
//	        removeLabel: "Remover",
//	 	   showCaption: false, 
//	 	   dropZoneEnabled: false
//	 	  });
//	    $("#imagenLogoCliente").fileinput({
//	        browseClass: "btn btn-success",
//	        browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
//	        browseLabel: "Selecciona Imagen",
//	        removeClass: "btn btn-warning",
//	        removeLabel: "Remover",
//	 	   showCaption: false, 
//	 	   dropZoneEnabled: false
//	 	  });

	    
	    
	    /*
	     * REST
	     */

	    var  urlUpload = "http://localhost:8015/"
	    	function enviaImagen(idImagenForm, rfcCliente){
	    		
	    		limpiaAlerta(),
//	    			console.log("Comineza envio imagenBody:"+idImagenForm);
	    			console.log("Comineza envio idCliente:"+rfcCliente);
//	    			rfcCliente ="nuevo";
//	    			console.log("Comineza envio rfcCliente:"+rfcCliente);
	    			var alerta="";
        			 var form = $('#altaCliente')[0]; //$('#formImagenLogoCliente').attr('files'),
    		        var data = new FormData(form);
    		        console.log(data);
	    			  $.ajax({
	    				url: "imageUpload/"+rfcCliente,
	    			    type: "POST",
	    			    data: new FormData($("#altaCliente")[0]),
//	    			    data: data,
//	    			    data: new FormData($("#formImagenLogoCliente")[0]),
	    			    enctype: 'multipart/form-data',
	    			    processData: false,
	    			    contentType: false,
	    			    cache: false,
	    			    success: 	function(data){
	    			    	if(data.codigo===0){
	    			    		if(data.codigo===0){
	    			  			  alerta="<div class='alert alert-success' role='alert'>imagen : 0 - Exito carga</div>";
	    			  			  $(alerta).insertAfter($('.alerta'));
	    			  			  console.log("envio ok");
	    			  	    	}else{
	    			  	    		alerta="<div class='alert alert-warning' role='alert'>imagen : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
	    			  				  $(alerta).insertAfter($('.alerta'));
	    			  	    		console.log("envio Nok");
	    			  	    	}
	    			    	  } 
	    			    	},
	    			    error: function () {
	    			    	alerta="<div class='alert alert-danger' role='alert'>error de carga de imagen</div>";
	    					  $(alerta).insertAfter($('.alerta'));
	    			  	console.log("envio error");
	    			    }
	    			  });

	    	}
	    
	    
		function enviaFile(idImagenForm, rfcCliente){
    		
    		limpiaAlerta(),

    			console.log("Comineza envio idCliente:"+rfcCliente);
    			var alerta="";
    			 var form = $('#altaCliente')[0]; //$('#formImagenLogoCliente').attr('files'),
		        var data = new FormData(form);
		        console.log(data);
    			  $.ajax({
    				url: "fileUpload/"+rfcCliente,
    			    type: "POST",
//    			    data: $("#archivosCliente").attr("file"),
    			    data: data,
//    			    data: new FormData($("#formImagenLogoCliente")[0]),
    			    enctype: 'multipart/form-data',
    			    processData: false,
    			    contentType: false,
    			    cache: false,
    			    success: 	function(data){
    			    	if(data.codigo===0){
    			    		if(data.codigo===0){
    			  			  alerta="<div class='alert alert-success' role='alert'>imagen : 0 - Exito carga</div>";
    			  			  $(alerta).insertAfter($('.alertaFile'));
    			  			  console.log("envio ok");
    			  	    	}else{
    			  	    		alerta="<div class='alert alert-warning' role='alert'>imagen : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
    			  				  $(alerta).insertAfter($('.alertaFile'));
    			  	    		console.log("envio Nok");
    			  	    	}
    			    	  } 
    			    	},
    			    error: function () {
    			    	alerta="<div class='alert alert-danger' role='alert'>error de carga de imagen</div>";
    					  $(alerta).insertAfter($('.alertaFile'));
    			  	console.log("envio error");
    			    }
    			  });

    	}


	  
		/*
		 * ********* ALERTAS  ***********
		 */
		function limpiaAlerta(){
			$( "div" ).remove( "#limpiaAlerta" );
		}
		
		function avisaAlertaEdicion(data){
			limpiaAlerta();
			 if(data.codigo===0){
				  location.reload();
			  }
		}
		
		function avisaAlerta(data){
			limpiaAlerta();
			 if(data.codigo===0){
				 modalClose();
//				 $("#alerta").click();
				 alerta="<div id='limpiaAlerta' class='alert alert-success' role='alert'>"+data.codigo+" "+data.mensaje.toString()+"</div>";
				 alertaFade(alerta); 
			 }else{
				 modalClose();
				  alerta="<div id='limpiaAlerta' class='alert alert-warning' role='alert'>"+data.codigo+" "+data.mensaje.toString()+"</div>";
				  alertaFade(alerta); 
			  }
		}
		
		function errorAlerta(){
			alerta="<div id='limpiaAlerta' class='alert alert-danger' role='alert'>Error de Enlace</div>";
			$(alerta).insertAfter($('.alerta_in'));
		}
		
		function modalClose(){
			 $("#modalIngresa .close").click();
			 $("#modalCita .close").click();
			 $("#modalRegistro .close").click();
			 $(".modal .close").click();
			 $("body,html").animate({
			        scrollTop: 0
			    }, 600);
		
		}
		function alertaFade(alerta){
			$(alerta).insertAfter($('.alerta_in'));
			  $('.alerta').fadeIn();
//			  $('.alerta').delay(2500).fadeOut();
			  $('.alerta').fadeOut( 4000);
//				 $('.alerta').hide( "drop", { direction: "down" }, "slow" );
		}
		
		
		function avisaAlertaImagen(data){
			
		}
		function errorAlertaImagen(){
			
		}
