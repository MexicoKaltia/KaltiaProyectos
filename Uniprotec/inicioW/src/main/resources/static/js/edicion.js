/**
 * Archivo de control JS para la pagina de Modulo 
 */

/*
 * funciones para Upload Files
 */
$(document).ready(function(){
	/*
	 * Pauta Operativa y Entregable
	 */

	var contarPrimerNivel = 0 ;
    var contarSegundoNivel = 0;
    var contarTercerNivel = 0;
    
    $('#seccionPautaOperativa').each(function(){
    	contarPrimerNivel++;
    	$('#listPrimerNivel'+contarPrimerNivel).each(function(){
    		contarSegundoNivel++;
    	})
    })
    
    $('#btnPrimerNivel').click(function(){
        
        $('#seccionPautaOperativa').append('<div class="alert alert-ligth alert-dismissible fade show" role="alert"><ul id="listPrimerNivel'+contarPrimerNivel+'"></ul><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>');
        
        var primerNivel = $('#txtPrimerNivel').val();
        $('#listPrimerNivel'+contarPrimerNivel).append('<li style="list-style-type: disc;" ><ul id="itemPrimerNivel'+contarPrimerNivel+'"><b>'+primerNivel+' </b></ul></li>');
        $('#txtSegundoNivel').attr('disabled', false);
        $('#btnPrimerNivel').attr('disabled', true);
        $('#btnSegundoNivel').attr('disabled', false);
        $('#txtPrimerNivel').val("");
        $('#txtPrimerNivel').attr('disabled', true);
        $('#agregarPautaOperativa').attr('disabled', false);
        contarPrimerNivel++;
        contarSegundoNivel++;
    });
    
    $('#btnSegundoNivel').click(function(){
        var segundoNivel = $('#txtSegundoNivel').val();
        
        if(segundoNivel.length > 0){
            
            $('#itemPrimerNivel'+(contarPrimerNivel-1)).append('<li style="list-style-type: circle;"  ><ul id="itemSegundoNivel'+contarSegundoNivel+'">'+segundoNivel+' </ul></li>');
            $('#txtPrimerNivel').attr('disabled', true);
            $('#txtTercerNivel').attr('disabled', false);
            $('#btnTercerNivel').attr('disabled', false);
            $('#txtSegundoNivel').val("");
            contarSegundoNivel++;
        }
    });
    
    $('#btnTercerNivel').click(function(){
        
        var tercerNivel = $('#txtTercerNivel').val();
        if(tercerNivel.length > 0){
//            //console.log(tercerNivel);
//            //console.log(contarSegundoNivel);
            contarTercerNivel = contarSegundoNivel - 1 ;
            //console.log($("#itemSegundoNivel"+contarTercerNivel).val());
        
            $("#itemSegundoNivel"+contarTercerNivel+"").append('<li style="list-style-type: square;">'+tercerNivel+' </li>');
            //$('#txtSegundoNivel').attr('disabled', true);
            //$('#btnSegundoNivel').attr('disabled', true);
            $('#txtTercerNivel').val("");
        }
    });
    
    $('#agregarPautaOperativa').click(function(){
//        //console.log("agregarInstruccion");
        $('#btnPrimerNivel').attr('disabled', false);
        $('#txtPrimerNivel').attr('disabled', false);
        $('#btnSegundoNivel').attr('disabled', true);
        $('#txtSegundoNivel').attr('disabled', true);
        $('#btnTercerNivel').attr('disabled', true);
        $('#txtTercerNivel').attr('disabled', true);
               
    });
    
    
    /*
     *  Pauta Entregable
     */
	var contarPrimerNivel1 = 0 ;
    var contarSegundoNivel1 = 0;
    var contarTercerNivel1 = 0;
    
    $('#seccionPautaEntregable').each(function(){
    	contarPrimerNivel1++;
    	$('#listPrimerNivel1'+contarPrimerNivel1).each(function(){
    		contarSegundoNivel1++;
    	});
    })
    
    $('#btnPrimerNivel1').click(function(){
        
        $('#seccionPautaEntregable').append('<div  class="alert alert-ligth alert-dismissible fade show" role="alert"><ul id="listPrimerNivel1'+contarPrimerNivel1+'"></ul><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>');
        
        var primerNivel = $('#txtPrimerNivel1').val();
        $('#listPrimerNivel1'+contarPrimerNivel1).append('<li style="list-style-type: disc;"><ul id="itemPrimerNivel1'+contarPrimerNivel1+'"><b>'+primerNivel+'</b> </ul></li>');
        $('#txtSegundoNivel1').attr('disabled', false);
        $('#btnPrimerNivel1').attr('disabled', true);
        $('#btnSegundoNivel1').attr('disabled', false);
        $('#txtPrimerNivel1').val("");
        $('#txtPrimerNivel1').attr('disabled', true);
        $('#agregarPautaEntregable').attr('disabled', false);
        contarPrimerNivel1++;
        contarSegundoNivel1++;
    });
    
    $('#btnSegundoNivel1').click(function(){
        var segundoNivel = $('#txtSegundoNivel1').val();
        
        if(segundoNivel.length > 0){
            
            $('#itemPrimerNivel1'+(contarPrimerNivel1-1)).append('<li style="list-style-type: circle;" ><ul id="itemSegundoNivel1'+contarSegundoNivel1+'">'+segundoNivel+' </ul></li>');
            $('#txtPrimerNivel1').attr('disabled', true);
            $('#txtTercerNivel1').attr('disabled', false);
            $('#btnTercerNivel1').attr('disabled', false);
            $('#txtSegundoNivel1').val("");
            contarSegundoNivel1++;
        }
    });
    
    $('#btnTercerNivel1').click(function(){
        
        var tercerNivel = $('#txtTercerNivel1').val();
        if(tercerNivel.length > 0){
//            //console.log(tercerNivel);
//            //console.log(contarSegundoNivel1);
            contarTercerNivel1 = contarSegundoNivel1 - 1 ;
            //console.log($("#itemSegundoNivel1"+contarTercerNivel1).val());
        
            $("#itemSegundoNivel1"+contarTercerNivel1+"").append('<li style="list-style-type: square;" >'+tercerNivel+' </li>');
            //$('#txtSegundoNivel1').attr('disabled', true);
            //$('#btnSegundoNivel1').attr('disabled', true);
            $('#txtTercerNivel1').val("");
        }
    });
    
    $('#agregarPautaEntregable').click(function(){
//        //console.log("agregarInstruccion");
        $('#btnPrimerNivel1').attr('disabled', false);
        $('#txtPrimerNivel1').attr('disabled', false);
        $('#btnSegundoNivel1').attr('disabled', true);
        $('#txtSegundoNivel1').attr('disabled', true);
        $('#btnTercerNivel1').attr('disabled', true);
        $('#txtTercerNivel1').attr('disabled', true);
               
    });

    
});

	
	function pautaAlta(){
		
		$('#pautaOperativaCliente').val($('#seccionPautaOperativa').html());
		$('#pautaEntregableCliente').val($('#seccionPautaEntregable').html());
		}
	
	function pautaEdicion(){
		
		$('#pautaOperativaClienteTxt').val($('#seccionPautaOperativa').html());
		$('#pautaEntregableClienteTxt').val($('#seccionPautaEntregable').html());
	}

  function imagenCliente(archivosCampo, rfcCliente){
	  var $idCliente = rfcCliente
	  $idCliente = $(rfcCliente).val();
	        var files = archivosCampo.files;
	        for (var i = 0; i < files.length; i++) {           
	            var file = files[i];
	            enviaImagen(archivosCampo, $idCliente);
	            }
	        //console.log(archivosCampo.file.name);
//	        //console.log($(archivosCampo).val());
	        }
  
  function archivoCliente(archivosCampo, rfcCliente){
	  var $idCliente = rfcCliente
	  $idCliente = $(rfcCliente).val();
      enviaFile(archivosCampo, $idCliente);
      var files = archivosCampo.files;
      for (var i = 0; i < files.length; i++) {           
          var file = files[i];
          //console.log(file);
          
          }        
      }
  
  function firmaInstructorF(archivosCampo){
//	  var $idInstructor = rfcCliente
	  var $idInstructor = $("#idInstructor").val();
	  var firma = $("#firmaInstructor").val();
	  while(firma.includes("C:\\fakepath\\") ){
			firma  = firma .replace("C:\\fakepath\\", "")	
		}
	  $("#firmaInstructorH").val(firma);
	  var files = archivosCampo.files;
	  for (var i = 0; i < files.length; i++) {           
		  var file = files[i];
	      enviaFirmaInstructor(archivosCampo, $idInstructor);
	      }
	        
  }

  function validaEspacio(element){
	  var tmp = element.value;
	  if(tmp.includes(" ") || tmp.includes("\t") || tmp.includes("\n")){
		  element.value = "";
		  alert("Verifique el RFC no contenga espacios, regularmente al final de la cadena.");
	  }
  }
	    
	    
	    /*
	     * REST
	     */

	    
	    	function enviaImagen(idImagenForm, rfcCliente){
	    		
	    		limpiaAlerta();
	    			var alerta="";
        			 var form = $('#altaCliente')[0]; //$('#formImagenLogoCliente').attr('files'),
    		        var data = new FormData(form);
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
	    			  			  //console.log("envio ok");
	    			  	    	}else{
	    			  	    		alerta="<div class='alert alert-warning' role='alert'>imagen : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
	    			  				  $(alerta).insertAfter($('.alerta'));
	    			  	    		//console.log("envio Nok");
	    			  	    	}
	    			    	  } 
	    			    	},
	    			    error: function () {
	    			    	alerta="<div class='alert alert-danger' role='alert'>error de carga de imagen</div>";
	    					  $(alerta).insertAfter($('.alerta'));
	    			  	//console.log("envio error");
	    			    }
	    			  });

	    	}
	    	
	    	
	    	function enviaFirmaInstructor(idImagenForm, idInstructor){
	    		limpiaAlerta();
	    			var alerta="";
        			var form = $('#actualizaInstructor')[0]; //$('#formImagenLogoCliente').attr('files'),
    		        var data = new FormData(form);
    		        //console.log(data);
	    			  $.ajax({
	    				url: "enviaFirmaInstructor/"+idInstructor,
	    			    type: "POST",
	    			    data: new FormData($("#actualizaInstructor")[0]),
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
	    			  			  //console.log("envio ok");
	    			  	    	}else{
	    			  	    		alerta="<div class='alert alert-warning' role='alert'>imagen : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
	    			  				  $(alerta).insertAfter($('.alerta'));
	    			  	    		//console.log("envio Nok");
	    			  	    	}
	    			    	  } 
	    			    	},
	    			    error: function () {
	    			    	alerta="<div class='alert alert-danger' role='alert'>error de carga de imagen</div>";
	    					  $(alerta).insertAfter($('.alerta'));
	    			  	//console.log("envio error");
	    			    }
	    			  });

	    	}
	    
	    
		function enviaFile(idImagenForm, rfcCliente){
    		
    		limpiaAlerta();

    			//console.log("Comineza envio idCliente:"+rfcCliente);
    			var alerta="";
    			var form = $('#altaCliente')[0]; //$('#formImagenLogoCliente').attr('files'),
		        var data = new FormData(form);
		        //console.log(data);
    			  $.ajax({
    				url: "fileUpload/"+rfcCliente,
    			    type: "POST",
    			    data: data,
    			    enctype: 'multipart/form-data',
    			    processData: false,
    			    contentType: false,
    			    cache: false,
    			    success: 	function(data){
    			    	if(data.codigo===0){
    			    		if(data.codigo===0){
    			  			  alerta="<div class='alert alert-success' role='alert'>imagen : 0 - Exito carga</div>";
    			  			  $(alerta).insertAfter($('.alertaFile'));
    			  			  //console.log("envio ok");
    			  	    	}else{
    			  	    		alerta="<div class='alert alert-warning' role='alert'>imagen : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
    			  				  $(alerta).insertAfter($('.alertaFile'));
    			  	    		//console.log("envio Nok");
    			  	    	}
    			    	  } 
    			    	},
    			    error: function () {
    			    	alerta="<div class='alert alert-danger' role='alert'>error de carga de imagen</div>";
    					  $(alerta).insertAfter($('.alertaFile'));
    			  	//console.log("envio error");
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
