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


	  
	  
$(document).ready(function(){
	
		
	/*
	 * Carga la Tabla inicial
	 */
	
	window.operateEventsUpdate = {
		    'click .like': function (e, value, row, index) {
//		      alert('You click like action, row: ' + JSON.stringify(row.regionCliente.nombreRegion) + ' ' + JSON.stringify(row.regionCliente.idRegion));
		      $('#idCliente').val(row.idCliente);
		      $('#nombreCortoCliente').val(row.nombreCortoCliente);
		      $('#nombreCompletoCliente').val(row.nombreCompletoCliente);
		      $('#emailCliente').val(row.emailCliente);
		      $('#telefonoCliente').val(row.telefonoCliente);
		      $('#rfcCliente').val(row.rfcCliente);
		      $('#idRegionCliente').append('<option value="'+row.regionCliente.idRegion+'" selected >'+row.regionCliente.nombreRegion+'</option>');
		      $('#domicilioCliente').val(row.domicilioCliente);      
		    }
		   }
	
	window.operateEventsUpdateInstructor = {
		    'click .like': function (e, value, row, index) {
//		      alert('You click like action, row: ' + row.idUserEmpresa);//JSON.stringify(row))
		      $('#idInstructor').val(row.idInstructor);
		      $('#nombreInstructor').val(row.nombreInstructor);
//		      $('#idRegionCliente').val(row.idRegionCliente);
		      $('#notaInstructor').val(row.notaInstructor);
		    }
		   }

	
	window.operateEventsUpdateCurso = {
		    'click .like': function (e, value, row, index) {
//		      alert('You click like action, row: ' + row.idUserEmpresa);//JSON.stringify(row))
		      $('#idCurso').val(row.idCurso);
		      $('#nombreCurso').val(row.nombreCurso);
		      $('#notaCurso').val(row.notaCurso);
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
//		$table.bootstrapTable('load', data)
	

	
	
	
	
	/*
	 *  funciones
	 */
	  


	
/*
 * Rest jquery
 */	
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



