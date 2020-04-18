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
	 * Variables Globales
	 */

	
	/*
	 * Carga la Tabla inicial
	 */
	window.operateEventsUpdate = {
		    'click .like': function (e, value, row, index) {
//		      alert('You click like action, row: ' + row.idUserEmpresa);//JSON.stringify(row))

		      $('#nombreCortoCliente').val(row.nombreCortoCliente);
		      $('#nombreCompletoCliente').val(row.nombreCompletoCliente);
		      $('#emailCliente').val(row.emailCliente);
		      $('#telefonoCliente').val(row.telefonoCliente);
		      $('#rfcCliente').val(row.rfcCliente);
		      $('#idRegionCliente').val(row.idRegionCliente);
		      $('#domicilioCliente').val(row.domicilioCliente);
		      
		    }
		   }
		  
	
	window.operateEventsDelete = {
	    'click .remove': function (e, value, row, index) {
	    	confirm("Estás seguro de Eliminar el Registro : "+row.nombreRegistro);
	    	$('#clientesTable').bootstrapTable('remove', {
	        field: 'idUserEmpresa',
	        values: [row.idUserEmpresa]
	      });
	      console.log(row);
	      deleteUserEmpresa(row.idUserEmpresa);
	    }
	  }
	
	
	
	/*
	 *  funciones
	 */
	var $table = $('#clientesTable')
	var $button = $('#selectId')

	  
	 $('.selectId').click(function () {
	      alert('getSelections: ' + JSON.stringify($table.bootstrapTable('getSelections')));
	      alert('jsonCompleto: ' + username);
//	      alert('jsonIndividual: ' + cliente.nombreCortoCliente);
	    });
		  
	  
	    var $data = clientes
//	    console.log(JSON.stringify(clientes));
//	    console.log($data);
	    $table.bootstrapTable({data: $data});
//	    $('#clientesTable').bootstrapTable('load', clientes);
	  


	
/*
 * Rest jquery
 */	
	$('#btnSaveRegistro').click(function(){
		limpiaAlerta();
		console.log($('#idUserEmpresa').val());
		if($('#idUserEmpresa').val()==null || $('#idUserEmpresa').val()==''){
		valoresRegistro = $('#nombreRegistro').val()+"++"+$('#apellidoRegistro').val()+"++"+$('#emailRegistro').val()+"++"+$('#telefonoRegistro').val()+"++"+$('#usuarioRegistro').val()+"++"+$('#passRegistro1').val()+"++"+$('#messageRegistro').val();
		 
			registroJson = { action : $.action,
				 idEmpresa : $.idEmpresa,	
//				 seccion : "bodySeccionArray1",
				 valoresFinales : valoresRegistro}
			console.log(registroJson);

		$.ajax({
		   	  url: url +"createUserEmpresa/",//+ context,//+finalJson.action+"/"+finalJson[1],
		      dataType: 'json',
			  type: 'POST',
			  contentType: "application/json",
			  data: JSON.stringify(registroJson),
			  headers: {  'Access-Control-Allow-Origin': url, 'Access-Control-Allow-Methods': 'POST, GET, OPTIONS', 'Access-Control-Allow-Headers': 'X-PINGOTHER' },
			  crossDomain: true,
			  success: 	function(data){					  
				  console.log(data);
				  avisaAlerta(data);
				  setTimeout(function() {
					  readIdUserEmpresa(0);
					}, 8000);
				},
			  error: function(){
				  errorAlerta();
			  }
			});
		}else{
			
			valoresRegistro = $('#idUserEmpresa').val()+"++"+$('#nombreRegistro').val()+"++"+$('#apellidoRegistro').val()+"++"+$('#emailRegistro').val()+"++"+$('#telefonoRegistro').val()+"++"+$('#usuarioRegistro').val()+"++"+$('#passRegistro1').val()+"++"+$('#messageRegistro').val();
			 
			registroJson = { action : $.action,
				 idEmpresa : $.idEmpresa,	
//				 seccion : "bodySeccionArray1",
				 valoresFinales : valoresRegistro}
			console.log(registroJson);

		$.ajax({
		   	  url: url +"updateUserEmpresa/",//+ context,//+finalJson.action+"/"+finalJson[1],
		      dataType: 'json',
			  type: 'POST',
			  contentType: "application/json",
			  data: JSON.stringify(registroJson),
			  headers: {  'Access-Control-Allow-Origin': url, 'Access-Control-Allow-Methods': 'POST, GET, OPTIONS', 'Access-Control-Allow-Headers': 'X-PINGOTHER' },
			  crossDomain: true,
			  success: 	function(data){					  
				  console.log(data);
				  avisaAlerta(data);
				  setTimeout(function() {
					  readIdUserEmpresa(0);
					}, 8000);
				},
			  error: function(){
				  errorAlerta();
			  }
			});

			
		}
	});
	
	
		function readIdUserEmpresa(idUserEmpresa){
		limpiaAlerta();
		valoresRegistro = $('#readidUserEmpresa').val();
		$.idUserEmpresa = idUserEmpresa
		 
			registroJson = { idAction : $.action,
							idUserEmpresa : $.idUserEmpresa,	
				 }
			console.log(registroJson);

		$.ajax({
		   	  url: url +"crud/clientes",
		      dataType: 'json',
			  type: 'GET',
			  contentType: "application/json",
//			  data: JSON.stringify(registroJson),
			  headers: {  'Access-Control-Allow-Origin': url, 'Access-Control-Allow-Methods': 'POST, GET, OPTIONS', 'Access-Control-Allow-Headers': 'X-PINGOTHER' },
			  crossDomain: true,
			  success: 	function(data){
				  if(data.length > 0){
    				  console.log(data);
//	    			  var $table = $('#userEmpresaTable');
		   			//$('#userEmpresaTable').bootstrapTable('load', $.userEmpresa);
//					$('#userEmpresaTable').bootstrapTable({data : data})
//					console.log("function load data option");
					$('#userEmpresaTable').bootstrapTable('load', data);
//					console.log("function load data metodo");
					$('#userEmpresaTable').bootstrapTable('refresh');
//					console.log("function refresh data");
//					  console.log(data.length);
				  }else{
					  alert("Registros Usuarios: 0");
					  console.log("No existen usuarios de empresa");
				  }
				},
			  error: function(){
				  errorAlerta();
			  }
			});
	}
		
		function deleteUserEmpresa(idUserEmpresa){
			
			limpiaAlerta();
			
			$.idUserEmpresa = idUserEmpresa
			registroJson = { action : $.action,
								valoresFinales : $.idUserEmpresa,	
					 }
				console.log("deleteUSerEmpresa");
				console.log(registroJson);

			$.ajax({
			   	  url: url +"deleteUserEmpresa",
			      dataType: 'json',
				  type: 'POST',
				  contentType: "application/json",
				  data: JSON.stringify(registroJson),
				  headers: {  'Access-Control-Allow-Origin': url, 'Access-Control-Allow-Methods': 'POST, GET, OPTIONS', 'Access-Control-Allow-Headers': 'X-PINGOTHER' },
				  crossDomain: true,
				  success: 	function(data){					  
					  console.log(data);
					  avisaAlerta(data);
					  if(data.codigo === 0){
						  console.log("Registro User Eliminado");
							$('#userEmpresaTable').bootstrapTable('refresh');
//						  location.reload();
						  
						  }else{
							  alert("Registros Usuarios: 0");
							  console.log("No existen usuarios de empresa");
						  }
					},
				  error: function(){
					  errorAlerta();
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

				

		
}); // Fin documento



