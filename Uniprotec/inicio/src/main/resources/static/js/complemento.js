/**
 * Archivo de control JS para la pagina de Modulo 
 */

	/*
	 * Acciones de EVENTOS userEmpresaTable
	 */

	  function operateFormatterUpdate(value, row, index) {
	    return [
	      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalRegistro">',
	      '<i class="fa fa-2x fa-user-edit"></i>',
	      '</a>'
	    ].join('')
	  }
	  
	  function operateFormatterDelete(value, row, index) {
		    return [
		      '<a class="remove" href="javascript:void(0)" title="Eliminar">',
		      '<i class="fa fa-2x fa-address-book">',
		      '</a>'
		    ].join('')
		  }
	  

	  function alerta(){
	  	alert("prueba js invocada desde html");
	  }


	  
	  
$(document).ready(function(){
	
		
	/*
	 * Carga la Tabla inicial
	 */
	
	window.operateEventsUpdate = {
		    'click .like': function (e, value, row, index) {
//		    	alert('You click like action, row: ' + JSON.stringify(row));
//		      alert('You click like action, row: ' + JSON.stringify(row.regionCliente.nombreRegion) + ' ' + JSON.stringify(row.regionCliente.idRegion));
		      $('#idCliente').val(row.idCliente);
		      $('#nombreCortoCliente').val(row.nombreCortoCliente);
		      $('#nombreCompletoCliente').val(row.nombreCompletoCliente);
		      $('#emailCliente').val(row.emailCliente);
		      $('#telefonoCliente').val(row.telefonoCliente);
		      $('#rfcCliente').val(row.rfcCliente);
		      $('#idRegionCliente').append('<option value="'+row.regionCliente.idRegion+'" selected >'+row.regionCliente.nombreRegion+'</option>');
		      $('#idVendedorCliente').append('<option value="'+row.vendedorCliente.idVendedor+'" selected >'+row.vendedorCliente.nombreVendedor+'</option>');
		      $('#domicilioCliente').val(row.domicilioCliente);      
		      $('#nombreContactoRecibeCliente').val(row.nombreContactoRecibeCliente);
		      $('#googleMapsCliente').val(row.googleMapsCliente);
		      $('#pautaOperativaCliente').val(row.pautaOperativaCliente);
		      $('#reglasAccesoCliente').val(row.reglasAccesoCliente);
		      $('#documentosAccesoCliente').val(row.documentosAccesoCliente);
		      $('#materialDidacticoCliente').val(row.materialDidacticoCliente);
		      $('#pautaGeneralCliente').val(row.pautaGeneralCliente);
		      $('#pautaEntregableCliente').val(row.pautaEntregableCliente);
		      $('#representanteEmpresaCliente').val(row.representanteEmpresaCliente);
		      $('#representanteTrabajadorCliente').val(row.representanteTrabajadorCliente);
		      $('#informacionPaqueteriaCliente').val(row.informacionPaqueteriaCliente);
		      $('#imagenLogoCliente').val(row.imagenLogoCliente);
		      $('#archivosCliente').val(row.archivosCliente);
		      
		    }
		   }
	
	window.operateEventsUpdateCurso = {
		    'click .like': function (e, value, row, index) {
		    	
//		      alert('You click like action, row: ' + JSON.stringify(row));//+row.instructores.idInstructor+'  selected:'+row.instructores.nombreInstructor);
		      $('#idCurso').val(row.idCurso);
		      $('#nombreCurso').val(row.nombreCurso);
//		      $('#listInstructores').multiSelect();
		      $('#listInstructores').multiSelect({
				  selectableHeader: "<div class='custom-header'>Instructores</div>",
				  selectionHeader: "<div class='custom-header'>Instructores Participantes</div>"
				  });
		      $('#listInstructores').multiSelect('deselect_all');
		      const $instructores = $instructoresTotal;
		      var $cursoInstructor = row.instructores;
		      var $instructoresParticipantes =[];
		      
		      $($cursoInstructor).each(function(index, element){
		    	  $($instructores).each(function(index2, element2){
	    			  $('#listInstructores').multiSelect('addOption', {
	    				  value: element2.idInstructor,
	    				  text: element2.nombreInstructor
	    			  });

		    		  if(element === element2.idInstructor){
		    			  $instructoresParticipantes.push(element2.idInstructor.toString());
		    		  }
		    	  })

		      });
			  $('#listInstructores').multiSelect('select', $instructoresParticipantes);
			  
		      $('#notaCurso').val(row.notaCurso);
		      
//				$('.removeParticipantes').click(function(index, element){
//					console.log('removeParticipantes')
//					var elemento = $(this);
//					var value = elemento.attr('value');
//					var texto = elemento.text();
//					 $('#listInstructores').prepend('<li><a class ="removeParticipantes instructores" value="'+value+'" >'+texto+'</a></li>');
//					 $(this).addClass('elimina');
//					 $(".elimina").remove();
//					 console.log(element);
//					 $($instructoresParticipantes).each(function(index, element){
//			    		  if(element.idInstructor === $instructoresParticipantes.idInstructor){
//			    			  $instructoresParticipantes.splice(index, index);
//		    			  }   			  
//		    		  })
//		    		  reorganiza();
//				});
//				
//				function reorganiza(){
//					$($instructoresParticipantes).each(function(index, element){
//						  $('#instructoresParticipantes').append('<li><a class ="removeParticipantes instructores" value="'+$instructor.idInstructor+'">'+$instructor.nombreInstructor+'</a></li>');
//					});
//				}

		    }
		   }

	
	window.operateEventsUpdateInstructor = {
		    'click .like': function (e, value, row, index) {
//		      alert('You click like action, row: ' +JSON.stringify(row));
		      $('#idInstructor').val(row.idInstructor);
		      $('#nombreInstructor').val(row.nombreInstructor);
		      $('#regionInstructor').append('<option value="'+row.regionInstructor.idRegion+'" selected >'+row.regionInstructor.nombreRegion+'</option>');
		      $('#emailInstructor').val(row.emailInstructor);
		      $('#emailGmailInstructor').val(row.emailGmailInstructor);
		      $('#listCursoInstructor').multiSelect({
				  selectableHeader: "<div class='custom-header'>Cursos</div>",
				  selectionHeader: "<div class='custom-header'>Cursos Asignados</div>"
				  });
		      $('#listCursoInstructor').multiSelect('deselect_all');
		      const $cursos = $cursosTotal;
		      var $instructorCursos = row.cursosInstructor.replace('[','').replace(']','').replace(' ','').split(',');
//		      $instructorCursos = $instructorCursos.split(',');
//		      console.log($instructorCursos);
		      var $cursosAsignados =[];
		      
		      $($instructorCursos).each(function(index, element){
		    	  element = element.replace(' ','');
		    	  element = (element * 1);
		    	  $($cursos).each(function(index2, element2){
	    			  $('#listCursoInstructor').multiSelect('addOption', {
	    				  value: element2.idCurso,
	    				  text: element2.nombreCurso
	    			  });

		    		  if(element === element2.idCurso){
		    			  $cursosAsignados.push(element2.idCurso.toString());
		    		  }
		    	  })

		      });
			  $('#listCursoInstructor').multiSelect('select', $cursosAsignados);
		      $('#notaInstructor').val(row.notaInstructor);
		    }
		   }
	
	window.operateEventsUpdateVendedor = {
		    'click .like': function (e, value, row, index) {
//		      alert('You click like action, row: ' +JSON.stringify(row));
		      $('#idVendedor').val(row.idVendedor);
		      $('#nombreVendedor').val(row.nombreVendedor);
		      $('#emailVendedor').val(row.emailVendedor);
		      $('#emailGmailVendedor').val(row.emailGmailVendedor);
		      $('#notaVendedor').val(row.notaVendedor);
		    }
		   }

	
	window.operateEventsDelete = {
	    'click .remove': function (e, value, row, index) {
	    	confirm("Estás seguro de Eliminar el Registro : "+row.nombreRegistro);
	    	$('#clientesTable').bootstrapTable('remove', {
	        field: 'idCliente',
	        values: [row.idCliente]
	      });
	      console.log(row);
	      deleteUserEmpresa(row.idUserEmpresa);
	    }
	  }

	$('#clientesTable').bootstrapTable({data : $data})
	$('#cursosTable').bootstrapTable({data : $data})
	$('#instructoresTable').bootstrapTable({data : $data})
	$('#vendedoresTable').bootstrapTable({data : $data})
//		$table.bootstrapTable('load', data)
	

	
	
	
	
	/*
	 *  funciones
	 */

	


	
/*
 * 
 * 
 * Rest jquery
 * 
 * 
 */
	$('#imagenLogoCliente').on('change', function(){ 
		
		enviaImagen($('imagenLogoCliente')); 
	
	});
	var  urlUpload = "http://localhost:8015/"
	function enviaImagen(idImagenForm){
		
		limpiaAlerta(),
			console.log("Comineza envio imagenBody:"+idImagenForm);
			var alerta="";
			  $.ajax({
//			    url: "http://localhost:8010/fileUpload",
				url: urlUpload+"fileUpload/"+$.action,
			    type: "POST",
//			    data: new FormData($("#upload-file-form")[0]),
			    data: new FormData($("#"+idImagenForm)[0]),
			    enctype: 'multipart/form-data',
			    processData: false,
			    contentType: false,
			    cache: false,
			    success: 	function(data){
			    	if(data.codigo==="00"){
			    		if(data.codigo==="00"){
			  			  alerta="<div class='alert alert-success' role='alert'>imagen : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
			  			  $(alerta).insertAfter($('.'+idImagenForm));
			  			  console.log("envio ok");
			  	    	}else{
			  	    		alerta="<div class='alert alert-warning' role='alert'>imagen : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
			  				  $(alerta).insertAfter($('.'+idImagenForm));
			  	    		console.log("envio Nok");
			  	    	}
			    	  } 
			    	},
			    error: function () {
			    	alerta="<div class='alert alert-danger' role='alert'>error de carga de imagen</div>";
					  $(alerta).insertAfter($('.'+idImagenForm));
			  	console.log("envio error");
			    }
			  });
			  //$(alerta).insertAfter($('.'+idImagenForm));
	}
	
	
	
	
	
	$('#btnSaveRegistro').click(function(){
		limpiaAlerta();
		console.log($('#idUserEmpresa').val());

	});
	
	
	
	
	
	
	
	
	
	
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
		
		
		

				

		
}); // Fin documento


