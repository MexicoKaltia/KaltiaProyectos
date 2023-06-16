
 function operateFormatterUpdate(value, row, index) {
	    return [
	      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalEdicionDatosEconomicos">',
	      '<i class="fa fa-2x fa-braille"></i>',
	      '</a>'
	    ].join('')
	  }

 function operateFormatterGet(value, row, index) {
	    return [
	      '<a class="like" href="javascript:void(0)" title="Editar"  data-toggle="modal" data-target="#modalExpedienteAsignacion">',
	      '<i class="fa fa-2x fa-braille"></i>',
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
	
	$arrayDatosEconomicosSin = new Array();
	var asignacionSelect = "";
	
	for(var a in datosEconomicos){
		var datoEconomico = datosEconomicos[a];
		if(datoEconomico.status === "SIN_ASIGNACION"){
			$arrayDatosEconomicosSin.push(datoEconomico);
		}
	}
	
	console.log($arrayDatosEconomicosSin);
	console.log(asignaciones); 
	
	window.operateEventsUpdateDE = {
			
			'click .like': function (e, value, row, index) {
		    	console.log(row);
		    	asignacionSelect = row;
		    	$arrayAsignacionesSin = new Array();
		    	$('#labelNumFacturaEdicion').text(row.formAENumFactura);
		    	$('#labelProspectoClienteEdicion').text(row.formAEClienteTexto);
		    	$('#labelVentaRealEdicion').text(formatter.format(row.formAEPrecioVentaReal));
		    	var porcentajeVenta = row.formAEPorcentajeVentaReal;
		    	if(porcentajeVenta < 99){
					$('#divPorcentajeVentaEdicion').removeClass("text-success");
					$('#divPorcentajeVentaEdicion').addClass("text-danger");
		    	}else{
					$('#divPorcentajeVentaEdicion').removeClass("text-danger");
					$('#divPorcentajeVentaEdicion').addClass("text-success");
				}
		    	$('#labelPorcentajeVentaEdicion').text(porcentajeVenta+"%");
		    	$('#labelFechaConfirmacionEdicion').text(row.formAEFechaConfirmacion);
		    	$('#labelFechaPromesaPagoEdicion').text(row.formAEFechaPromesaPago);
		    	$('#labelViaticosTotalesEdicion').text(formatter.format(row.formAEViaticosTotal));
		    	$('#labelObservacionesEdicion').text(row.formAEObservaciones);
		    	//Vendedores
		    	$('#divVendedoresEdicion').empty();
		    	var idDatosEconomicos = row.idPreAsignacionAE;
		    	var vendedoresDE = new Array();
		    	for(var a in vendedoresDatosEconomicos){
		    		var vendedor = vendedoresDatosEconomicos[a];
		    		if(vendedor.idDatosEconomicos === idDatosEconomicos){
		    			vendedoresDE.push(vendedor);
		    			var registroVendedor = '<div class="row"><div class="col-md-12">Nombre Vendedor : <label class="widget-numbers mt-0 fsize-2 text-primary pull-left">'+vendedor.nombreVendedor+'</label></div></div>\
		    			<div class="row">\
		    				<div class="col-md-6">\
		    					Porcentaje Comision : <label class="widget-numbers mt-0 fsize-2 text-primary pull-left">'+vendedor.porcentajeComisionVendedor+'%</label>\
		    				</div>\
		    				<div class="col-md-6">\
		    					Monto Comisión : <label class="widget-numbers mt-0 fsize-2 text-primary pull-left">'+formatter.format(vendedor.comisionRealVendedor)+'</label>\
		    				</div>\
		    			</div><br>';
		    			$('#divVendedoresEdicion').append(registroVendedor);
		    		}
		    	}
		    	
		    	// Registros asignacionesOutDETable
		    	$arrayAsignacionesSin.length = 0;
		    	$('#bodyAsignacion').empty();
		    	var idCliente = row.formAECliente;
		    	for(var a in asignaciones){
		    		var asignacion = asignaciones[a];
		    		if(asignacion.statusAsignacion !== "Evento Cancelado"){
		    			if(idCliente*1 === asignacion.idClienteAsignacion*1){
			    			if(!validateDatosEconomicos(asignacion.idAsignacion)){
			    				$arrayAsignacionesSin.push(asignacion);
			    				var registro = "<tr><td ><input type='checkbox'  class='checkAsignacion' id='"+asignacion.idAsignacion+"' value='"+asignacion.idAsignacion+"'>" +
			    				"</td><td>"+asignacion.idAsignacion+
				    			"</td><td>"+transformaDia(asignacion.fechaAsignacion.toString())+
				    			"</td><td>"+asignacion.cursoAsignacion+
				    			"</td><td>"+asignacion.instructorAsignacion+
				    			"</td><td>"+asignacion.statusAsignacion+
				    			"</td><td><a class='like' id='"+asignacion.idAsignacion+"' href='javascript:function(0)' onclick='expedienteAsignacion("+asignacion.idAsignacion+")' title='Consultar'  data-toggle='modal' data-target='#modalAsignacion'><i class='fa fa-2x fa-indent'></i></a></td></tr>";
    			
			    				$('#bodyAsignacion').append(registro);
			    			}
			    		}
		    		}
		    	}
		    	console.log($arrayAsignacionesSin);
		    	$('.checkAsignacion').click(function(){
		    		$("#btnAsignacionDatosEconomicos").attr("disabled", true);
		    		$('.checkAsignacion:checked').each(function(){
		    			if($(this).attr('checked',true)){
			    			$("#btnAsignacionDatosEconomicos").attr("disabled", false);
			    		}
		    		});
		    	});
		    	
		    	$("#btnAsignacionDatosEconomicos").click(function(){
		    		var asignacionesArray = new Array();
		    		$('.checkAsignacion:checked').each(function(){
		    			if($(this).attr('checked',true)){			
		    				var idAsignacion= $(this).attr('id')
		    				asignacionesArray.push(idAsignacion);
		    			}
		    		});
//		    		$("#edicionDatosEconomicos").attr("action", "/BDatosEconomicos/"+row.idPreAsignacionAE);
		    		$('#idDatosEconomicosEdicion').val(row.idPreAsignacionAE);
		    		$('#listAsignaciones').val(asignacionesArray);
		    	});
		    	
		    	$("#btnEliminarDatosEconomicos").click(function(){
		    		console.log("btnEliminarDatosEconomicos");
		    		console.log(asignacionSelect);
		    		
		    		$("#edicionDatosEconomicos").attr("action", "/DDatosEconomicos/"+row.idPreAsignacionAE);
		    		
		    		alert("Se Elimina Expediente Económico");
		    		
		    	});

	    }
		    	
   	}

	window.operateEventsUpdateAsignacion = {
			
			'click .like': function (e, value, row, index) {
		    	console.log(row);
			}
   	}
	
	
/*
 * funciones form
 */
	


	
	

	
	$('#datosEconomicosSinTable').bootstrapTable({data : $arrayDatosEconomicosSin});
	
    
}); // fin jquery



	function validateDatosEconomicos(idAsignacion){
		var flag = false;
		for(var a in datosEconomicos){
			var datoEconomico = datosEconomicos[a];
			if(datoEconomico.formAEidPreAsignacion != null){
				if(datoEconomico.formAEidPreAsignacion*1 === idAsignacion*1){
					flag = true;
					break;
				}
			}else{
				if(datoEconomico.formAEListAsignaciones){
					var arrayAsignaciones = new Array();
					if(datoEconomico.formAEListAsignaciones.includes(";")){
						arrayAsignaciones = datoEconomico.formAEListAsignaciones.split(";");
						for(var e in arrayAsignaciones){
							var valueAsig = arrayAsignaciones[e]
							if(valueAsig*1 === idAsignacion*1){
								flag = true;
								break;
							}
						}
					}
				}
			}
		}
		return flag;
	}
	
	function expedienteAsignacion(idAsignacion) {
		console.log(idAsignacion);
		for(var i in $arrayAsignacionesSin){
			var asignacion = $arrayAsignacionesSin[i];
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
				$('#modalArchivoParticipantes').html("<a id='linkArchivoParticipantes'><b>"+asignacion.archivoParticipantes+"</b></a>");
				$("#linkArchivoParticipantes").attr('href', '/uploads/fileAsignacion/V'+asignacion.idAsignacionLogica+'/'+asignacion.archivoParticipantes)
				
				$('#modalCostoHotel').html('<b>'+asignacion.costoHotel+'</b>');
				if(perfilUsuario !== "Administracion"){
					$('#admon').hide();
				}
			}
		}
	 }

	/*
	 * Privates
	 */
	function transformaDia(fechaSelectString){
		var fechaSelect = new Date(fechaSelectString);
		dia = fechaSelect.getDate();
		mes = fechaSelect.getMonth() + 1;
		anio = fechaSelect.getFullYear();
		sem = getDia(fechaSelect.getDay());
		
		return (sem + ", " + dia + "/"+ mes + "/" + anio);
	}
	function getDia(dia){
		var semana= ["Domingo", "Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"];
		return semana[dia];
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


	
	
	
	
	
	
	
	
	
	