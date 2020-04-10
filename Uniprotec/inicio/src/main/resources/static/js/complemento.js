/**
 * Archivo de control JS para la pagina de Modulo 
 */

	/*
	 * Acciones de EVENTOS userEmpresaTable
	 */

	  function operateFormatterUpdate(value, row, index) {
	    return [
	      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalRegistro">',
	      '<i class="fa fa-2x fa-pencil-square-o"></i>',
	      '</a>  '
	    ].join('')
	  }
	  
	  function operateFormatterDelete(value, row, index) {
		    return [
		      '<a class="remove" href="javascript:void(0)" title="Eliminar">',
		      '<i class="fa fa-2x fa-user-times"></i>',
		      '</a>'
		    ].join('')
		  }


	  
	  
$(document).ready(function(){
	
	/*
	 * Variables Globales
	 */
	var url = "http://31.220.63.183:8016/";
//	var urlUpload= "http://31.220.63.183:8011/";
//	var urlCita = "http://31.220.63.183:8012/";
//	var url = "http://localhost:8010/";
//	var urlUpload= "http://localhost:8011/";
//	var urlCita = "http://localhost:8012/";
	
	$.idEmpresa = $("#idEmpresa").val();
	$.action = $("#idAction").val();
	$.idUserEmpresa ="";
	$.url2 = url+"readUserEmpresa/"+$.action+"/"+$.idUserEmpresa
	
	var $userEmpresa = $(function(){
		readIdUserEmpresa("0");
	});
	
	/*
	 * Carga la Tabla inicial
	 */
	$( function() {
//		var $table = $('#userEmpresaTable');
//		//$('#userEmpresaTable').bootstrapTable('load', $.userEmpresa);
//		$table.bootstrapTable({data : JSON.stringify(readIdUserEmpresa("0"))})
//		console.log("function load data option");
//		$('#userEmpresaTable').bootstrapTable('load', $userEmpresa);
//		console.log("function load data metodo");
//		$('#userEmpresaTable').bootstrapTable('refresh');
//		console.log("function refresh data");
		
	} );       

	 
	 
	window.operateEventsUpdate = {
		    'click .like': function (e, value, row, index) {
//		      alert('You click like action, row: ' + row.idUserEmpresa);//JSON.stringify(row))
		      $('#idUserEmpresa').val(row.idUserEmpresa);
		      $('#nombreRegistro').val(row.nombreRegistro);
		      $('#apellidoRegistro').val(row.apellidoRegistro);
		      $('#emailRegistro').val(row.emailRegistro);
		      $('#telefonoRegistro').val(row.telefonoRegistro);
		      $('#usuarioRegistro').val(row.usuarioRegistro);
		      $('#passRegistro1').val(row.passRegistro1);
		      $('#passRegistro2').val(row.passRegistro2);
		      $('#messageRegistro').val(row.messageRegistro);
		    }
		   }
		  
	
	window.operateEventsDelete = {
	    'click .remove': function (e, value, row, index) {
	    	confirm("Estás seguro de Eliminar el Registro : "+row.nombreRegistro);
	    	$('#userEmpresaTable').bootstrapTable('remove', {
	        field: 'idUserEmpresa',
	        values: [row.idUserEmpresa]
	      });
	      console.log(row);
	      deleteUserEmpresa(row.idUserEmpresa);
	    }
	  }
		  
	
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



