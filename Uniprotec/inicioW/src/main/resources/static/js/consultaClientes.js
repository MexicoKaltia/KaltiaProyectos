/*
 * funciones de table Genericas
 */

	/*
	 * Acciones de EVENTOS userEmpresaTable
	 */

 function operateFormatterExpediente(value, row, index) {
	    return [
	      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalExpediente">',
	      '<i class="fa fa-2x fa-address-book"></i>',
	      '</a>'
	    ].join('')
	  }
 function operateFormatterAsignaciones(value, row, index) {
	    return [
	      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalAsignaciones">',
	      '<i class="fa fa-2x fa-history"></i>',
	      '</a>'
	    ].join('')
	  }
 function operateFormatterParticipantes(value, row, index) {
	    return [
	      '<a class="like" href="javascript:void(0)" title="Descarga" id="UserUpdate" data-toggle="modal" data-target="#modalParticipantesDescarga">',
	      '<i class="fa fa-users  fa-2x"></i>',
	      '</a>'
	    ].join('')
	  }

 function operateFormatterExpedienteAsignacion(value, row, index) {
	    return [
	      '<a class="like" href="javascript:void(0)" title="Consultar"  data-toggle="modal" data-target="#modalAsignacion">',
	      '<i class="fa fa-2x fa-indent"></i>',
	      '</a>'
	    ].join('')
	  }
	  
	  function operateFormatterDelete(value, row, index) {
		    return [
		      '<a class="remove" href="javascript:void(0)" title="Eliminar">',
		      '<i class="fa fa-2x fa-history">',
		      '</a>'
		    ].join('')
		  }
	  
	  

	
	
$(document).ready(function(){
		
	/*
	 * Carga la Tabla inicial
	 */
	
	$.statusInstructor="";
	$.statusCurso="";
	$.listFechas="";
	
	console.log(asignaciones);
	arrayClientesInstructor = new Array();
	if(perfilUsuario === "Instructor"){
		var idInstructor = operacionId;
		for(a in $asignaciones){
			var asignacion = $asignaciones[a];
    		for(var i in asignacion){
	    		if(idInstructor === asignacion[i].idInstructorAsignacion){
	    			arrayClientesInstructor.push(asignacion[i].idClienteAsignacion);
	    		}
    		}
		}
//		//console.log(arrayClientesInstructor);
		var arrayClientes = new Array();
		for(e in $data){
			var cliente = $data[e];
			for(i in arrayClientesInstructor){
				if(cliente.idCliente === arrayClientesInstructor[i]){
					arrayClientes.push(cliente);
				}
			}
		}
		var clientes = new Array();
		var idClientes = new Array();
		for(a in arrayClientes){
			if(!idClientes.includes(arrayClientes[a].idCliente)){
				clientes.push(arrayClientes[a]);
				idClientes.push(arrayClientes[a].idCliente)
			}
		}
		$data.length = 0;
		$data = clientes;
//		//console.log($data);
	}else if(perfilUsuario === "Vendedor"){
		var arrayClientes = new Array();
		var idVendedor = operacionId;
		for(e in $data){
			var cliente = $data[e];
			if(cliente.vendedorCliente.idVendedor === idVendedor){
					arrayClientes.push(cliente);
			}
		}
		$data.length = 0;
		$data = arrayClientes;
	}
	
	window.operateEventsExpediente = {
		    'click .like': function (e, value, row, index) {
//		    	//console.log(row);
//		    	//console.log(row.nombreCortoCliente);
		    	while(row.pautaEntregableCliente.includes('<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>') ){
		    		row.pautaEntregableCliente = row.pautaEntregableCliente.replace('<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>', '')	
		    	}
		    	
		    	while(row.pautaOperativaCliente.includes('<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>') ){
		    		row.pautaOperativaCliente = row.pautaOperativaCliente.replace('<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>', '')	
		    	}
		    	
//		    	console.log(row);
		    	$('#nombreCortoCliente').html('<b>'+row.nombreCortoCliente+'</b>');
		    	$('#regionCliente').html('<b>'+row.regionCliente.nombreRegion+'</b>');
		    	$('#nombreCompletoCliente').html('<b>'+row.nombreCompletoCliente+'</b>');
		    	$('#rfcCliente').html('<b>'+row.rfcCliente+'</b>');
		    	$('#domicilioCliente').html('<b>'+row.domicilioCliente+'</b>');
		    	$('#telefonoCliente').html('<b>'+row.telefonoCliente+'</b>');
		    	$('#googleMapsCliente').html('<b><a href="'+row.googleMapsCliente+'" target="_blank">Ver Mapa</b>');
		    	$('#emailCliente').html('<b>'+row.emailCliente+'</b>');
		    	$('#documentosAccesoCliente').html('<b>'+row.documentosAccesoCliente+'</b>');
		    	$('#reglasAccesoCliente').html('<b>'+row.reglasAccesoCliente+'</b>');
		    	$('#vendedorCliente').html('<b>'+row.vendedorCliente.nombreVendedor+'</b>');
		    	$('#representanteEmpresaCliente').html('<b>'+row.representanteEmpresaCliente+'</b>');
		    	$('#representanteTrabajadorCliente').html('<b>'+row.representanteTrabajadorCliente+'</b>');
		    	$('#nombreContactoRecibeCliente').html('<b>'+row.nombreContactoRecibeCliente+'</b>');
		    	$('#pautaEntregableCliente').html(row.pautaEntregableCliente);
		    	$('#pautaGeneralCliente').html('<b>'+row.pautaGeneralCliente+'</b>');
		    	$('#pautaOperativaCliente').html(row.pautaOperativaCliente);
		    	$('#materialDidacticoCliente').html('<b>'+row.materialDidacticoCliente+'</b>');
		    	$('#informacionPaqueteriaCliente').html('<b>'+row.informacionPaqueteriaCliente+'</b>');
		    	$('#notaCliente').html('<b>'+row.notaCliente+'</b>');
		    	
		    	$('#imagenLogoCliente').html('<b>'+row.imagenLogoCliente+'</b>');
		    	$("#linkLogo").attr('href', '/uploads/img/'+row.rfcCliente+'/'+row.imagenLogoCliente)
		        
		    	$('#archivosCliente').html('<b>'+row.archivosCliente+'</b>');
		    	$("#linkFile").attr('href', '/uploads/file/'+row.rfcCliente+'/'+row.archivosCliente)
		        
	      
		    }
		   }
	
	var	asignacion;
	$.asignacionesArray = new Array();
	
	
	window.operateEventsAsignaciones = {
		    'click .like': function (e, value, row, index) {
		    	var idCliente = row.idCliente;
		    	$.asignacionesArray = new Array();
		    	$('#regsitroAsignacion').empty();
		    	$('#asignacionesDownload').val("");
		    	var clienteAsignacion = "";
		    	for(var a in asignaciones){
					asignacion = asignaciones[a];
					var idClienteAsignacion = asignacion.idClienteAsignacion;
					if((idClienteAsignacion*1) === (idCliente*1)){
						asignacion.fechaAsignacionNew = transformaDia(asignacion.fechaAsignacion.toString())
						$.asignacionesArray.push(asignacion);
			    		var clienteAsignacion = asignacion.clienteAsignacion;
					}
				}
		    	$('#nombreCliente').html(clienteAsignacion);
	    		$('#numAsignaciones').html($.asignacionesArray.length)
	    		console.log($.asignacionesArray);
//		    	
	    		var $asignacionesTable = $('#asignacionesTable')
	    		$('#asignacionesTable').bootstrapTable('load', $.asignacionesArray);
	    	    $('#asignacionesTable').bootstrapTable({data : $.asignacionesArray});

		    	var arrayAsignaciones = new Array();
		      	var lstArrayAsignaciones = new Array();
		    	for(var a in $.asignacionesArray){
		    		arrayAsignaciones.push(JSON.stringify($.asignacionesArray[a]));
				}
	    	    $('#strAsignacionesDescargas').val(arrayAsignaciones);
		   }
	}
	
	window.operateEventsParticipantes = {
		    'click .like': function (e, value, row, index) {
		    	var idCliente = row.idCliente;
		    	console.log(row);
		    }
	}
	
	window.operateEventsExpedienteAsignacion= {
		    'click .like': function (e, value, row, index) {
		    	console.log("ExpedienteAsignacion");
		    	var idAsignacion = row.idAsignacion;
		    	var asignacion = row;
//		    	console.log(asignacion);
    			$('#modalFecha').html('<b>'+transformaDia(asignacion.fechaAsignacion)+'</b>'); 
    			$('#modalCliente').html('<b>'+asignacion.clienteAsignacion+' : </b>'+asignacion.nombreRegionAsignacion);
    			$('#modalCurso').html('<b>'+asignacion.cursoAsignacion+'</b>'+" : <i><u><b>"+asignacion.tipoCursoAsignacion+"</b></u></i>");
    			$('#modalInstructor').html('<b>'+asignacion.instructorAsignacion+'</b>');
    			var asignaHorasEfectivas = asignacion.horarioAsignacion.split(";");
    			$('#modalHorario').html("<b>"+ horaInicio(asignacion.horarioAsignacion)+"-"+horaFin(asignacion.horarioAsignacion)+"</b> - Horas Efectivas: <b>"+asignaHorasEfectivas[4]+"</b>");
    			$('#modalParticipantes').html('<b>'+asignacion.participantesAsignacion+'</b>'); 
    			$('#modalNivel').html('<b>'+asignacion.nivelAsignacion+'</b>');
    			$('#modalObservaciones').html('<b>'+asignacion.observacionesAsignacion+'</b>');
    			$('#modalArchivos').html("<a id='link'><b>"+asignacion.archivosAsignacion+"</b></a>");
    			$("#link").attr('href', '/uploads/fileAsignacion/'+asignacion.idAsignacionLogica+'/'+asignacion.archivosAsignacion)
    			if(asignacion.statusAsignacion ==="Entregable Enviado") {
    				$('#modalStatus').html('<b>'+asignacion.statusAsignacion+'</b>');
    				$('#modalStatus').append('<div class="alert alert-success" role="alert" >Guía Paqueteria : <b>'+asignacion.guiaEntregable+' <b></div>');
    			}else{
    				$('#modalStatus').html('<b>'+asignacion.statusAsignacion+'</b>');
    			}
    			$('#modalVentas').html('<b>'+asignacion.userCreateAsignacionTexto+'</b>');
    			$('#modalUsuarioRegistro').html('<b>'+asignacion.realCapturaNombre+'</b>');
    			
    			$('#modalFechaPago').html('<b>'+asignacion.fechaPago+'</b>');
    			$('#modalFactura').html('<b>'+asignacion.numeroFactura+'</b>');
    			$('#modalArchivoParticipantes').html("<a id='linkArchivoParticipantes'><b>"+asignacion.archivoParticipantes+"</b></a>");
    			$("#linkArchivoParticipantes").attr('href', '/uploads/fileAsignacion/V'+asignacion.idAsignacionLogica+'/'+asignacion.archivoParticipantes)
		    			
    			$('#modalCostoHotel').html('<b>'+asignacion.costoHotel+'</b>');
    			if(perfilUsuario !== "Administracion"){
    				$('#admon').hide();
    			}
		    	
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
	
	$('#clientesTable').bootstrapTable({data : $data})
//	$('#asignacionesTable').bootstrapTable({data : $.asignacionesArray})
//	//console.log(typeof $data);
//	//console.log(typeof $asignaciones);
	
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
	

	function stringToList(cadena){
		return cadena.split(";");
	}
	
	
		
	
}); // Fin documento

function transformaDia(fechaSelectString){
	var fechaSelect = new Date(fechaSelectString);
	dia = fechaSelect.getDate();
	mes = fechaSelect.getMonth() + 1;
	anio = fechaSelect.getFullYear();
	sem = getDia(fechaSelect.getDay());
	
//	return fechaSelect;
//	return new Date(anio , mes , dia);
	return (sem + ", " + dia + "/"+ mes + "/" + anio);
}
function getDia(dia){
	var semana= ["Domingo", "Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"];
	return semana[dia];
}

function expedienteAsignacion(idAsignacion) {
//	//console.log(idAsignacion);
	for(i in $.asignacionesArray){
		var asignacion = $.asignacionesArray[i];
		if((idAsignacion*1) === (asignacion.idAsignacion*1)){
			$('#modalFecha').html('<b>'+transformaDia(asignacion.fechaAsignacion)+'</b>'); 
			$('#modalCliente').html('<b>'+asignacion.clienteAsignacion+' : </b>'+asignacion.nombreRegionAsignacion);
			$('#modalCurso').html('<b>'+asignacion.cursoAsignacion+'</b>'+" : <i><u><b>"+asignacion.tipoCursoAsignacion+"</b></u></i>");
			$('#modalInstructor').html('<b>'+asignacion.instructorAsignacion+'</b>');
			var asignaHorasEfectivas = asignacion.horarioAsignacion.split(";");
			$('#modalHorario').html("<b>"+ horaInicio(asignacion.horarioAsignacion)+"-"+horaFin(asignacion.horarioAsignacion)+"</b> - Horas Efectivas: <b>"+asignaHorasEfectivas[4]+"</b>");
			$('#modalParticipantes').html('<b>'+asignacion.participantesAsignacion+'</b>'); 
			$('#modalNivel').html('<b>'+asignacion.nivelAsignacion+'</b>');
			$('#modalObservaciones').html('<b>'+asignacion.observacionesAsignacion+'</b>');
//			$('#modalArchivos').html('<b>'+asignacion.archivosAsignacion+'</b>');
			$('#modalArchivos').html("<a id='link'><b>"+asignacion.archivosAsignacion+"</b></a>");
			$("#link").attr('href', '/uploads/fileAsignacion/'+asignacion.idAsignacionLogica+'/'+asignacion.archivosAsignacion)
			if(asignacion.statusAsignacion ==="Entregable Enviado") {
				$('#modalStatus').html('<b>'+asignacion.statusAsignacion+'</b>');
				$('#modalStatus').append('<div class="alert alert-success" role="alert" >Guía Paqueteria : <b>'+asignacion.guiaEntregable+' <b></div>');
			}else{
				$('#modalStatus').html('<b>'+asignacion.statusAsignacion+'</b>');
			}
			$('#modalVentas').html('<b>'+asignacion.userCreateAsignacionTexto+'</b>');
			$('#modalFechaPago').html('<b>'+asignacion.fechaPago+'</b>');
			$('#modalFactura').html('<b>'+asignacion.numeroFactura+'</b>');
//			$('#modalArchivoParticipantes').html('<b>'+asignacion.archivoParticipantes+'</b>');
			$('#modalArchivoParticipantes').html("<a id='linkArchivoParticipantes'><b>"+asignacion.archivoParticipantes+"</b></a>");
			$("#linkArchivoParticipantes").attr('href', '/uploads/fileAsignacion/V'+asignacion.idAsignacionLogica+'/'+asignacion.archivoParticipantes)
			
			$('#modalCostoHotel').html('<b>'+asignacion.costoHotel+'</b>');
			if(perfilUsuario !== "Administracion"){
//				$('#resumenAsignacionModal').
				$('#admon').hide();
			}
		}
	}
	
 }

function horaInicio(horario){
	horario = horario.split(';');
	var parse =horario[0].substring(0, 2) +":"+ horario[0].substring(2);
	return parse;
}

function horaFin(horario){
	horario = horario.split(';');
	var parse =horario[1].substring(0, 2) +":"+ horario[1].substring(2);	
	return parse;
}

