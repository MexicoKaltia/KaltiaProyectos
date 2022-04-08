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
	$.idAsignacion;
	$.registro;
	
//	console.log(idUsuario);
//	console.log(perfilUsuario);
//	console.log(operacionId);
	
	if(perfilUsuario !== "Vendedor"){
		$data = $preAsignaciones;
		$('#btnEliminarPreAsignacion').hide();
		$('#btnAEEdicionSubmit').hide();
		$('#divAsignacion').hide();
		$('#divAgregarFactura').hide();
		$('#divAgregarFechas').hide();
	}else{
		$data = getPREVendedor();
		$('#btnAprobarPreAsignacion').hide();
		$('#divAgregarFactura').hide();
	}
	
	
	
	
	window.operateEventsViewExpedientePreAsignacion = {
		    'click .like': function (e, value, row, index) {
		    	console.log(row);
		    	$('#modalCliente').html('<b>'+row.clienteAsignacion+'</b>');
		    	$('#modalCurso').html('<b>'+row.cursoAsignacion+'</b>');
		    	$('#modalInstructor').html('<b>'+row.instructorAsignacion+'</b>');
		    	$('#modalFecha').html('<b>'+fechaModal(row.fechaAsignacion)+'</b>');
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
		    			
		    			$('#btnAEEdicionSubmit').click(function(){
			    			$('#idAsignacionEdicion').val(row.idPreAsignacion);
			    			$('#fechaAsignacionEdicion').val(row.fechaAsignacion);
							$('#idClienteAsignacionEdicion').val(row.idClienteAsignacion);
							$('#clienteAsignacionEdicion').val(row.clienteAsignacion);
							$('#idCursoAsignacionEdicion').val(row.idCursoAsignacion);
							$('#cursoAsignacionEdicion').val(row.cursoAsignacion);
							$('#idInstructorAsignacionEdicion').val(row.idInstructorAsignacion);
							$('#instructorAsignacionEdicion').val(row.instructorAsignacion);
							$('#horarioAsignacionEdicion').val(row.horarioAsignacion);
							$('#participantesAsignacionEdicion').val(row.participantesAsignacion);
							$('#nivelAsignacionEdicion').val(row.nivelAsignacion);
							$('#archivosAsignacionTextoEdicion').val(row.archivosAsignacionTexto);
							$('#archivosAsignacionEdicion').html(row.archivosAsignacion);
							$('#observacionesAsignacionEdicion').val(row.observacionesAsignacion);
							$('#idRegionAsignacionEdicion').val(row.idRegionAsignacion);
							$('#nombreRegionAsignacionEdicion').val(row.nombreRegionAsignacion);
							$('#tipoCursoAsignacionEdicion').val(row.tipoCursoAsignacion);
							$('#userCreateAsignacionEdicion').val(row.userCreateAsignacion);
							$('#userCreateAsignacionTextoEdicion').val(row.userCreateAsignacionTexto);
							
							if(row.idPreAsignacionAE*1 > 0){
								$('#idPreAsignacionAEEdicion').val(row.idPreAsignacionAE);
							}
								
							
							$('#btnAEEdicionSubmit').submit();
			    		});
		    		}
		    	}
		    	if(!flag){
		    		$('#modalDivAE').hide();
		    		$('#modalDivNuevoAE').show();
		    		$('#modalAE').removeClass("modal-xl");
		    		$('#modalAE').addClass("modal-lg");
		    		console.log(row);
		    		$('#btnAENuevoSubmit').click(function(){
		    			
		    			$('#idAsignacionAlta').val(row.idPreAsignacion);
		    			$('#fechaAsignacionAlta').val(row.fechaAsignacion);
						$('#idClienteAsignacionAlta').val(row.idClienteAsignacion);
						$('#clienteAsignacionAlta').val(row.clienteAsignacion);
						$('#idCursoAsignacionAlta').val(row.idCursoAsignacion);
						$('#cursoAsignacionAlta').val(row.cursoAsignacion);
						$('#idInstructorAsignacionAlta').val(row.idInstructorAsignacion);
						$('#instructorAsignacionAlta').val(row.instructorAsignacion);
						$('#horarioAsignacionAlta').val(row.horarioAsignacion);
						$('#participantesAsignacionAlta').val(row.participantesAsignacion);
						$('#nivelAsignacionAlta').val(row.nivelAsignacion);
						$('#archivosAsignacionTextoAlta').val(row.archivosAsignacionTexto);
						$('#archivosAsignacionAlta').html(row.archivosAsignacion);
						$('#observacionesAsignacionAlta').val(row.observacionesAsignacion);
						$('#idRegionAsignacionAlta').val(row.idRegionAsignacion);
						$('#nombreRegionAsignacionAlta').val(row.nombreRegionAsignacion);
						$('#tipoCursoAsignacionAlta').val(row.tipoCursoAsignacion);
						$('#userCreateAsignacionAlta').val(row.userCreateAsignacion);
						$('#userCreateAsignacionTextoAlta').val(row.userCreateAsignacionTexto);
						
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
		    	console.log(row);
		    	$.registro = row;
		    	$('#seccionPautaOperativa').html(row.seguimiento);
		    	$('#validaClienteProspecto').empty();
		    	$('#validaInstructor').empty();
		    	$('#divAgregarNotificacion').empty();
		    	$('#divAprobarPreAsignacion').empty();
		    	$('#divAsignacion').empty();
		    	$('#divAgregarFechas').empty();
		    	$('#modaltextAreaSeguimiento').val("");
		    	
//		    	var btnAgregarFechas ='<button  id="btnAgregarFechas" class="btn btn-info" data-toggle="modal" data-target="#modalFechas">Agregar Fechas Cobro Factura</button>';
		    	var btnAgregarFechas = '<div class="col-md-3"></div><div id="btnAgregarFechas" class="col-md-6" data-toggle="modal" data-target="#modalFechas">  <img width="50%" src="images/calendario.png"alt=""><small class="form-text text-muted">Click en calendario para agregar Fechas de Cobro Facturacion</small></div><div class="col-md-3">';
		    	
		    if(row.idStatusAsignacion == 1 || row.idStatusAsignacion == 3){
		    	console.log("alta preAsignacion || revision");
		    }else if(row.idStatusAsignacion == 2){
		    		if(row.clienteStatus !== "PROSPECTO"){
		    			if(perfilUsuario !== "Vendedor"){
		    				var btnAprobarPreAsignacion ='<button type="submit" id="btnAprobarPreAsignacion" class="btn btn-success">Aprobar PreAsignación</button>';
			    			$('#divAprobarPreAsignacion').append(btnAprobarPreAsignacion);
				    		$('#btnAprobarPreAsignacion').click(function(){
				    			var mensajeSeguimiento = $('#modaltextAreaSeguimiento').val();
					    		$('#idPreAsignacion').val(row.idPreAsignacion);
					    		$('#nombreUsuarioSeguimiento').val(nombreUsuario);
					    		$('#perfilUsuarioSeguimiento').val(perfilUsuario);
					    		$('#modaltextAreaSeguimiento').val("APROBADO PREASIGNACION. "+ mensajeSeguimiento);
					    		$('#statusAsignacion').val('APROBADO');
					    		$('#idStatusAsignacion').val(4);
					    	});
		    			}
		    		}else{
						var alert = '<div class="alert alert-warning" role="alert" ><b>Revisión : </b><u>No se puede aprobar PreAsignacion, debido que el Cliente tiene estatus <b>PROSPECTO</b>. Notifique a personal administración y/o operación</u></div>';
						$('#validaClienteProspecto').append(alert);				
		    		}
		    	}else if(row.idStatusAsignacion*1 == 4){
		    		var jsonEvalua = evaluaPreAsignacion(
					    				row.fechaAsignacion,
					    				row.idClienteAsignacion,
					    				row.clienteAsignacion,
					    				row.idCursoAsignacion,
					    				row.cursoAsignacion,
					    				row.tipoCursoAsignacion,
					    				row.idRegionAsignacion,
					    				row.idInstructorAsignacion,
					    				row.instructorAsignacion);
					    		
		    		if(jsonEvalua.codigo == 0){
		    			
		    			var alert = '<div class="alert alert-success" role="alert" ><b>Éxito : </b><u>El instructor '+row.instructorAsignacion+' se encuentra disponible para Asignación.</u></div>';
		    			if(perfilUsuario === "Vendedor"){
			    			var btnAsignacion ='<button type="submit" id="btnAsignacion" class="btn btn-success">Asignar Evento Calendario</button>';
			    			$('#divAsignacion').append(btnAsignacion);
			    			$("#formNotificacionNuevo").attr("action", "/altaAsignacion");
			    			
			    			$('#divAgregarFechas').append(btnAgregarFechas);
			    			
			    			$('#btnAsignacion').click(function(){
			    				operacionSubmit(row);	    				
					    	});
		    			}
		    			
		    		}else{
		    			var alert = '<div class="alert alert-warning" role="alert" ><b>Revisión : </b><u>El instructor '+row.instructorAsignacion+', </u><b>NO</b> se encuentra disponible para Asignación. Por favor seleccione otro candidato</div>';
		    		}
		    		$('#validaInstructor').append(alert);
		    	}else if(row.idStatusAsignacion*1 == 5){
		    		console.log("ASIGNACION");
		    		$.idAsignacion = row.idAsignacionLogica;
		    		operacionSubmit(row);
		    		if(perfilUsuario !== "Vendedor"){
		    			console.log("divAgregarFactura Show");
	    				$('#divAgregarFactura').show();
	    			}else{
			    		$('#divAgregarFechas').append(btnAgregarFechas);
			    	}
//		    		var divAgregarFactura = '\
//		    			<label for="validationCustom02">Adjuntar Archivos Facturacion</label>\
//						<br/>\
//	            		<div class="alertaFile">\
//							<hiden class="alerta_inFile"></hiden>\
//						</div>\
//	        			<input id="agregarFactura" name="agregarFactura[]" th:field="*{agregarFactura}"  type="file" class="file"    data-show-upload="false" multiple  onchange="validaArchivos(this,'+row.idAsignacionLogica+')"/>\
//						<br/>\
//	    				<small class="form-text text-muted">Adjuntar documento de facturacion cliente.</small>';
//		    		
//		    		$('#divAgregarFactura').append(divAgregarFactura);
//		    		   $("#agregarFactura").fileinput({
//		    		        browseClass: "btn btn-info",
//		    		        browseLabel: "Seleccionar Archivos",
//		    		        browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
//		    		        removeClass: "btn btn-warning",
//		    		        removeLabel: "Remover",
//		    		 	   showCaption: false, 
//		    		 	   dropZoneEnabled: false
//		    		 	  });
		    	}else{
		    		$('#divAgregarFactura').empty();
		    		$('#divAgregarFechas').append(btnAgregarFechas);
		    	}
		    	
		    	$('#btnAgregarNotificacion').click(function(){
		    		var mensajeSeguimiento = $('#modaltextAreaSeguimiento').val();
		    		$('#idPreAsignacion').val(row.idPreAsignacion);
		    		$('#nombreUsuarioSeguimiento').val(nombreUsuario);
		    		$('#perfilUsuarioSeguimiento').val(perfilUsuario);
		    		$('#statusAsignacion').val('REVISION');
		    		$('#modaltextAreaSeguimiento').val("REVISION. "+ mensajeSeguimiento);
		    		$('#idStatusAsignacion').val(3);
		    		if(row.idStatusAsignacion > 3){
		    			$('#idStatusAsignacion').val(99);
		    		}
		    		 
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
	      deleteUserEmpresa(row.idUserEmpresa);
	    }
	  }
	
	
	$('#preAsignacionesTable').bootstrapTable({data : $data})

	$('#btnCargaFactura').click(function(){
		    			operacionSubmit(row);
		    			console.log(btnAsignacion);
		    			$('#formNotificacionNuevo').submit();
		    		});
	
	
	$('#btnAgregarFechaModal').click(function(){
		console.log($.registro);
		var fechaInicioFactura = $('#fechaInicioFactura').val();
		var fechaFinFactura = $('#fechaFinFactura').val();
		var elementoPicker = $datepicker.pickadate('picker');
		console.log(fechaInicioFactura );
		$('#fechaInicioFacturaFecha').val(fechaInicioFactura);
		$('#fechaFinFacturaFecha').val(elementoPicker.get('select', 'mm/dd/yyyy'));
		$('#fechaHoy').val(hoy());
		$('#idPreAsignacionFecha').val($.registro.idPreAsignacion);
		$('#nombreUsuarioSeguimientoFecha').val(nombreUsuario);
		$('#perfilUsuarioSeguimientoFecha').val(perfilUsuario);
		$('#mensajeSeguimientoFecha').val("Fecha Cobro Factura : <b>"+ fechaInicioFactura + "</b>");
		$('#formFechasFactura').submit();
	});
	
	/*
	 * funciones
	 */
	
	function hoy() {
		var d = new Date();
		var dia = d.getDate();
		var mes = (d.getMonth() + 1);
		var anio = d.getFullYear();
		if (dia < 10)
			dia = "0" + dia.toString();
		if (mes < 10)
			mes = "0" + mes.toString();
		var today = mes + '/' + dia + '/' + anio;
//		//console.log(today);
		return today;
	}
	function getPREVendedor(){
		
		var preAsignaciones = new Array();
		for(var a in $preAsignaciones){
			var preAsignacion = $preAsignaciones[a]
			
			if(preAsignacion.userCreateAsignacion === idUsuario){
				var fecha = getFecha(preAsignacion.createAtAsignacion);
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
		return parse;
	}
	
	function operacionSubmit(row){
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
		$('#archivosAsignacionTexto').val(row.archivosAsignacion);
		$('#archivosAsignacion').html(row.archivosAsignacion);
		$('#observacionesAsignacion').val(row.observacionesAsignacion);
		$('#idRegionAsignacion').val(row.idRegionAsignacion);
		$('#nombreRegionAsignacion').val(row.nombreRegionAsignacion);
		$('#tipoCursoAsignacion').val(row.tipoCursoAsignacion);
		$('#userCreateAsignacion').val(idUsuario);
		$('#userCreateAsignacionTexto').val(nombreUsuario);

		var mensajeSeguimiento = $('#modaltextAreaSeguimiento').val();
		$('#idPreAsignacion').val(row.idPreAsignacion);
		$('#idAsignacion').val(row.idPreAsignacion);
		$('#idPreAsignacionAE').val(row.idPreAsignacionAE);
		$('#nombreUsuarioSeguimiento').val(nombreUsuario);
		$('#perfilUsuarioSeguimiento').val(perfilUsuario);
		
		if(row.idStatusAsignacion*1 == 4){
			$('#modaltextAreaSeguimiento').val("EVENTO ASIGNADO. "+ mensajeSeguimiento);
			$('#statusAsignacion').val('EVENTO ASIGNADO');
			$('#idStatusAsignacion').val(5);
		}else if(row.idStatusAsignacion*1 == 5){
			$('#modaltextAreaSeguimiento').val("FACTURA INTEGRADA ASIGNACION. "+ mensajeSeguimiento);
			$('#statusAsignacion').val('FACTURA INTEGRADA ASIGNACION');
			$('#idStatusAsignacion').val(6);
		}
		
		$('#seguimiento').val(row.seguimiento);
	}

	
	
}); // Fin documento jquery

function fechaModal(fecha){
	var fechaModal = fecha.split("/");
	return  fechaModal[1] +"/"+ fechaModal[0] +"/"+ fechaModal[2];
	
}


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
			$('#btnAgregarNotificacion').attr("disabled", false);
		}else{
			$('#btnAgregarNotificacion').attr("disabled", true);
		}
	}
	
	
	
	/*
	 * FileUpload
	 */
	function validaArchivos(archivosCampo){
//	   	  var fecha = fechaArchivo($.asignaFecha);
//	  	  var idAsignacion = fecha +"-"+$.asignaCliente+"-"+$.asignaInstructor+"-"+$.asignaCurso
		console.log($.idAsignacion);
	        enviaFile($.idAsignacion);
	        var files = archivosCampo.files;
	        for (var i = 0; i < files.length; i++) {           
	            var file = files[i];
//	            console.log(file);          
	        }        
	   }
	
	
	function enviaFile(idAsignacion){
		
  		limpiaAlerta();

		//console.log("envio idAsignacion:"+rfcCliente);
		var alerta="";
		 var form = $('#formNotificacionNuevo')[0]; //$('#formImagenLogoCliente').attr('files'),
        var data = new FormData(form);
//        console.log(data);
		  $.ajax({
			  url: "fileAsignacionFactura/"+idAsignacion,
			    type: "POST",
			    data: new FormData($("#formNotificacionNuevo")[0]),
//			    data: data,
//			    data: new FormData($("#formImagenLogoCliente")[0]),
			    enctype: 'multipart/form-data',
			    processData: false,
			    contentType: false,
			    cache: false,
		    success: 	function(data){
		    	if(data.codigo===0){
		    		if(data.codigo===0){
		  			  alerta="<div class='alert alert-success' role='alert'>imagen : 0 - Exito carga</div><br><button type='submit' id='btnCargaFactura' class='btn btn-success'>Actualizar Factura</button>";
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
		    	alerta="<div class='alert alert-danger' role='alert'>Error en carga de Archivo</div>";
				  $(alerta).insertAfter($('.alertaFile'));
		  	//console.log("envio error");
		    }
		  });

	}

	
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
//			 $("#alerta").click();
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
	