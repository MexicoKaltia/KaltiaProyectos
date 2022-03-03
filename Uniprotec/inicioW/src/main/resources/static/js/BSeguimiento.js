/*
 * funciones de table Genericas
 */

	/*
	 * Acciones de EVENTOS userEmpresaTable
	 */

 function operateFormatterViewPreAsignacion(value, row, index) {
	    return [
	      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalPreAsignacion">',
	      '<i class="fa fa-2x fa-folder-open"></i>',
	      '</a>'
	    ].join('')
	  }
 
 function  operateFormatterViewPreAsignacionAE(value, row, index) {
	    return [
	      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalPreAsignacionAE">',
	      '<i class="fa fa-2x fa-rocket"></i>',
	      '</a>'
	    ].join('')
	  }

 function operateFormatterViewCliente(value, row, index) {
	    return [
	      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalCliente">',
	      '<i class="fa fa-2x fa-id-card"></i>',
	      '</a>'
	    ].join('')
	  }
 
 function operateFormatterViewSeguimiento(value, row, index) {
	    return [
	      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalSeguimiento">',
	      '<i class="fa fa-2x fa-list-alt"></i>',
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
//	  	alert("prueba js invocada desde html");
	  }
	 
	  
	
$(document).ready(function(){
		
	/*
	 * Carga la Tabla inicial
	 */
	
	console.log($preAsignaciones);
	console.log($preAsignacionesAE);
	
//	console.log(idUsuario);
//	console.log(perfilUsuario);
//	console.log(operacionId);
	
	if(perfilUsuario !== "Vendedor"){
		$data = $preAsignaciones;
	}else{
		$data = getPREVendedor();
	}
	
	
	window.operateEventsViewExpedientePreAsignacion = {
		    'click .like': function (e, value, row, index) {
		    	console.log(row);
		    	$('#modalCliente').html('<b>'+row.clienteAsignacion+'</b>');
		    	$('#modalCurso').html('<b>'+row.cursoAsignacion+'</b>');
		    	$('#modalInstructor').html('<b>'+row.instructorAsignacion+'</b>');
		    	$('#modalFecha').html('<b>'+row.fechaAsignacion+'</b>');
		    	$('#modalHorario').html('<b>'+horaInicio(row.horarioAsignacion)+'</b>');
		    	$('#modalParticipantes').html('<b>'+row.participantesAsignacion+'</b>');
		    	$('#modalNivel').html('<b>'+row.nivelAsignacion+'</b>');
		    	$('#modalObservaciones').html('<b>'+row.observacionesAsignacion+'</b>');
		    	$('#modalArchivos').html('<b>'+row.archivosAsignacion+'</b>');
		    	$('#modalVentas').html('<b>'+row.userCreateAsignacionTexto+'</b>');
		    	
		    	$('#btnEliminarPreAsignacion').click(function(){
		    		console.log("btnModalPreAsignacion");
		    		$("#formPreAsignacion").attr("action", "/DPreAsignacion/"+row.idPreAsignacion);
		    		$("#formPreAsignacion").submit();
		    	});
	      
		    }
		   }
	
	window.operateEventsViewExpedientePreAsignacionAE = {
		    'click .like': function (e, value, row, index) {
		    	console.log(row);
		    	var flag = false;
		    	for(var a in $preAsignacionesAE){
		    		var preAsignacionAE = $preAsignacionesAE[a];
		    		
		    		if((preAsignacionAE.formAEidPreAsignacion*1) === row.idPreAsignacion){
		    			$('#formAECurso').text(preAsignacionAE.formAECurso);
		    			$('#formAEEmpresa').text(preAsignacionAE.formAEEmpresa);
		    			$('#formAEHorasEfectivas').text(preAsignacionAE.formAEHorasEfectivas);
		    			$('#formAESesiones').text(preAsignacionAE.formAESesiones);
		    			$('#formAEParticipantes').text(preAsignacionAE.formAEParticipantes);
		    			$('#formAEFechaCotizacion').text(preAsignacionAE.formAEFechaCotizacion);
		    			$('#formAESede').text(preAsignacionAE.formAESede);
		    			$('#formAENivelCurso').text(preAsignacionAE.formAENivelCurso);

		    			$('#formAENumInstructor').text(preAsignacionAE.formAENumInstructor);
		    			$('#formAETotalHoras').text(preAsignacionAE.formAETotalHoras);
		    			$('#formAECostoHoraInstructor').text(preAsignacionAE.formAECostoHoraInstructor);
		    			$('#formAETotalImparticion').text(preAsignacionAE.formAETotalImparticion);
		    			$('#formAEViaticosTotal').text(preAsignacionAE.formAEViaticosTotal);

		    			$('#formAESumaImparticionViaticos').text(preAsignacionAE.formAESumaImparticionViaticos);
		    			$('#formAECostoCursoRecomendado').text(preAsignacionAE.formAECostoCursoRecomendado);
		    			$('#formAECostoHoraRecomendada').text(preAsignacionAE.formAECostoHoraRecomendada);

		    			$('#formAEImparticion').text(preAsignacionAE.formAEImparticion);
		    			$('#formAEImparticionPorcentaje').text(preAsignacionAE.formAEImparticionPorcentaje);
		    			$('#formAEComisionVendedor').text(preAsignacionAE.formAEComisionVendedor);
		    			$('#formAEComisionVendedorPorcentaje').text(preAsignacionAE.formAEComisionVendedorPorcentaje);
		    			$('#formAEViaticos').text(preAsignacionAE.formAEViaticos);
		    			$('#formAEViaticosPorcentaje').text(preAsignacionAE.formAEViaticosPorcentaje);
		    			$('#formAEGastosFijos').text(preAsignacionAE.formAEGastosFijos);
		    			$('#formAEGastosFijosPorcentaje').text(preAsignacionAE.formAEGastosFijosPorcentaje);
		    			$('#formAEGananciaCurso').text(preAsignacionAE.formAEGananciaCurso);
		    			$('#formAEGananciaCursoPorcentaje').text(preAsignacionAE.formAEGananciaCursoPorcentaje);
		    			$('#formAETotales').text(preAsignacionAE.formAETotales);
		    			$('#formAETotalesPorcentaje').text(preAsignacionAE.formAETotalesPorcentaje);

		    			$('#formAEPrecioVentaReal').text(preAsignacionAE.formAEPrecioVentaReal);
		    			$('#formAEComisionVendedorReal').text(preAsignacionAE.formAEComisionVendedorReal);
		    			$('#formAEGastosFijosReal').text(preAsignacionAE.formAEGastosFijosReal);
		    			$('#formAEUtilidadReal').text(preAsignacionAE.formAEUtilidadReal);
		    			$('#formAENuevaComisionReal').text(preAsignacionAE.formAENuevaComisionReal);

		    			$('#formAERegla3PorcentajeNuevaComisionReal').text(preAsignacionAE.formAERegla3PorcentajeNuevaComisionReal);
		    			$('#formAERegla3PorcentajeNuevaComision').text(preAsignacionAE.formAERegla3PorcentajeNuevaComision);

		    			$('#formAEObservaciones').text(preAsignacionAE.formAEObservaciones);
		    			
		    			$.regla3PorcentajeNuevaComisionReal = $('#formAERegla3PorcentajeNuevaComisionReal').text();
		    			$('#formAERegla3PorcentajeNuevaComisionRealDiv').attr("aria-valuenow", $.regla3PorcentajeNuevaComisionReal);
		    			$('#formAERegla3PorcentajeNuevaComisionRealDiv').css("width", $.regla3PorcentajeNuevaComisionReal+"%");
		    			$('#formAERegla3PorcentajeNuevaComisionDiv').attr("aria-valuenow", $.regla3PorcentajeNuevaComision);
		    			$('#formAERegla3PorcentajeNuevaComisionDiv').css("width", $.regla3PorcentajeNuevaComision+"%");
		    			
		    			if($.regla3PorcentajeNuevaComisionReal < 100){
		    				$('#formAERegla3PorcentajeNuevaComisionRealDiv').removeClass("bg-success");
		    				$('#formAERegla3PorcentajeNuevaComisionRealDiv').addClass("bg-danger");
		    				$('#formAERegla3PorcentajeNuevaComisionDiv').removeClass("bg-info");
		    				$('#formAERegla3PorcentajeNuevaComisionDiv').addClass("bg-danger");
		    				
		    				$('#formAERegla3PorcentajeNuevaComisionReal').removeClass("text-success");
		    				$('#formAERegla3PorcentajeNuevaComisionReal').addClass("text-danger");
		    				$('#formAERegla3PorcentajeNuevaComision').removeClass("text-info");
		    				$('#formAERegla3PorcentajeNuevaComision').addClass("text-danger");
		    				
		    				$('#formAENuevaComisionRealDiv').removeClass("text-success");
		    				$('#formAENuevaComisionRealDiv').addClass("text-danger");
		    				$('#formAETotalesDiv').removeClass("text-success");
		    				$('#formAETotalesDiv').addClass("text-danger");
		    				
		    			}else{
		    				$('#formAERegla3PorcentajeNuevaComisionRealDiv').removeClass("bg-danger");
		    				$('#formAERegla3PorcentajeNuevaComisionRealDiv').addClass("bg-success");
		    				$('#formAERegla3PorcentajeNuevaComisionDiv').removeClass("bg-danger");
		    				$('#formAERegla3PorcentajeNuevaComisionDiv').addClass("bg-info");
		    				
		    				$('#formAERegla3PorcentajeNuevaComisionReal').removeClass("text-danger");
		    				$('#formAERegla3PorcentajeNuevaComisionReal').addClass("text-success");
		    				$('#formAERegla3PorcentajeNuevaComision').removeClass("text-danger");
		    				$('#formAERegla3PorcentajeNuevaComision').addClass("text-info");
		    				
		    				$('#formAENuevaComisionRealDiv').removeClass("text-danger");
		    				$('#formAENuevaComisionRealDiv').addClass("text-success");
		    				$('#formAETotalesDiv').removeClass("text-danger");
		    				$('#formAETotalesDiv').addClass("text-success");
		    			}
		    			var flag = true;
		    			$('#modalDivNuevoAE').hide();
		    			$('#modalDivAE').show();
		    			$('#modalAE').removeClass("modal-lg");
		    			$('#modalAE').addClass("modal-xl");
		    		}
		    	}
		    	if(!flag){
		    		$('#modalDivAE').hide();
		    		$('#modalDivNuevoAE').show();
		    		$('#modalAE').removeClass("modal-xl");
		    		$('#modalAE').addClass("modal-lg");
		    		
		    		$('#btnAENuevoSubmit').click(function(){
		    			$('#idAsignacion').val(row.idPreAsignacion);
		    			alert(row.idPreAsignacion);
		    			$('#fechaAsignacion').val(row.fechaAsignacion);
						$('#idClienteAsignacion').val(row.idClienteAsignacion);
						$('#clienteAsignacion').val(row.clienteAsignacion);
						$('#idCursoAsignacion').val(row.idCursoAsignacion);
						$('#cursoAsignacion').val(row.cursoAsignacion);
						$('#idInstructorAsignacion').val(row.idInstructorAsignacion);
						$('#instructorAsignacion').val(row.instructorAsignacion);
						$('#horarioAsignacion').val(row.horarioAsignacion);
						$('#participantesAsignacion').val(row.participantesAsignacion);
						$('#nivelAsignacion').val(row.nivelAsignacion);
						$('#archivosAsignacionTexto').val("archivos asignacion Texto");
						$('#archivosAsignacion').html(row.archivosAsignacion);
						$('#observacionesAsignacion').val(row.observacionesAsignacion);
						$('#idRegionAsignacion').val(row.idRegionAsignacion);
						$('#nombreRegionAsignacion').val(row.nombreRegionAsignacion);
						$('#tipoCursoAsignacion').val(row.tipoCursoAsignacion);
						$('#userCreateAsignacion').val(row.userCreateAsignacion);
						$('#userCreateAsignacionTexto').val(row.userCreateAsignacionTexto);
						
						$('#formPreAsignacionAENuevo').submit();
		    		});
		    	}	      
		    }
		   }

	
	window.operateEventsViewExpedienteCliente = {
		    'click .like': function (e, value, row, index) {
		    	
		    }
		   }
	
	window.operateEventsViewExpedienteSeguimiento = {
			
		    'click .like': function (e, value, row, index) {
		    	$('#seccionPautaOperativa').html(row.seguimiento);
		    	
		    	$('#btnAgregarNotificacion').click(function(){
//		    		console.log($('#modaltextAreaSeguimiento').val());
		    		$('#idPreAsignacion').val(row.idPreAsignacion);
		    		$('#nombreUsuarioSeguimiento').val(nombreUsuario);
		    		$('#perfilUsuarioSeguimiento').val(perfilUsuario);
		    		
		    	});
		    }
		   }
	
	

	
	window.operateEventsDelete = {
	    'click .remove': function (e, value, row, index) {
	    	confirm("Estás seguro de Eliminar el Registro : "+row.nombreRegistro);
	    	$('#clientesTable').bootstrapTable('remove', {
	        field: 'idCliente',
	        values: [row.idCliente]
	      });
	      //console.log(row);
	      deleteUserEmpresa(row.idUserEmpresa);
	    }
	  }
	
	
	$('#preAsignacionesTable').bootstrapTable({data : $data})

	
/*
 * 
 * 
 * Rest jquery
 * 
 * 
 */
	
	
	
	
	/*
	 * funciones
	 */
	function getPREVendedor(){
		
		var preAsignaciones = new Array();
		for(var a in $preAsignaciones){
			var preAsignacion = $preAsignaciones[a]
			
			if(preAsignacion.userCreateAsignacion === idUsuario){
				var fecha = getFecha(preAsignacion.createAtAsignacion);
//				var fecha = preAsignacion.userCreateAsignacion;
				preAsignacion.createAtAsignacion = fecha;
				preAsignaciones.push(preAsignacion);
			}
		}
		return preAsignaciones;
	}
	
	

	function stringToList(cadena){
		return cadena.split(";");
	}
	
	function horaInicio(horario){
		//"1200;2300;undefined;undefined;11:00"
		//start : '2020-05-03T11:00:00',
		horario = horario.split(';');
		var parse =horario[0].substring(0, 2) +":"+ horario[0].substring(2);
//		parse = parse + ":00";// + horario[0].slice(2, 2) + ":00" ;
//		//console.log(parse);
		return parse;
	}
	
	
	var contarPrimerNivel = 0 ;
    var contarSegundoNivel = 0;
    var contarTercerNivel = 0;
    
//    $('#seccionPautaOperativa').each(function(){
//    	contarPrimerNivel++;
//    	$('#listPrimerNivel'+contarPrimerNivel).each(function(){
//    		contarSegundoNivel++;
//    	})
//    })
//    
//    $('#btnPrimerNivel').click(function(){
//        
//        $('#seccionPautaOperativa').append('<div class="alert alert-ligth alert-dismissible fade show" role="alert"><ul id="listPrimerNivel'+contarPrimerNivel+'"></ul><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>');
//        
//        var primerNivel = $('#txtPrimerNivel').val();
//        $('#listPrimerNivel'+contarPrimerNivel).append('<li style="list-style-type: disc;" ><ul id="itemPrimerNivel'+contarPrimerNivel+'"><b>'+primerNivel+' </b></ul></li>');
//        $('#txtSegundoNivel').attr('disabled', false);
//        $('#btnPrimerNivel').attr('disabled', true);
//        $('#btnSegundoNivel').attr('disabled', false);
//        $('#txtPrimerNivel').val("");
//        $('#txtPrimerNivel').attr('disabled', true);
//        $('#agregarPautaOperativa').attr('disabled', false);
//        contarPrimerNivel++;
//        contarSegundoNivel++;
//    });
//    
//    $('#btnSegundoNivel').click(function(){
//        var segundoNivel = $('#txtSegundoNivel').val();
//        
//        if(segundoNivel.length > 0){
//            
//            $('#itemPrimerNivel'+(contarPrimerNivel-1)).append('<li style="list-style-type: circle;"  ><ul id="itemSegundoNivel'+contarSegundoNivel+'">'+segundoNivel+' </ul></li>');
//            $('#txtPrimerNivel').attr('disabled', true);
//            $('#txtTercerNivel').attr('disabled', false);
//            $('#btnTercerNivel').attr('disabled', false);
//            $('#txtSegundoNivel').val("");
//            contarSegundoNivel++;
//        }
//    });

	
	
}); // Fin documento jquery


function getFecha(fechaValor){
	var fechaArray = fechaValor.split('-');
	var dia = fechaArray[2].substring(0,2);
	var mes = fechaArray[1];
	var anio = fechaArray[0];
	
	var fecha = dia + '-' + mes + '-' + anio;	
	return fecha;
}

	function minLenghtTextArea(element){
		var valor = element.value;
		if(valor.length > 4){
			console.log(valor.length);
			$('#btnAgregarNotificacion').attr("disabled", false);
		}else{
			$('#btnAgregarNotificacion').attr("disabled", true);
		}
	}