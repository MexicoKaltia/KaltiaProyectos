console.log(asignacionesHistorico);

function operateFormatterExpediente(value, row, index) {
    return [
      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalExpedienteAsignacion">',
      '<i class="fa fa-2x fa-indent"></i>',
      '</a>'
    ].join('')
  }
  
//var $data = new Array();

$(document).ready(function(){
	
	window.operateEventsExpediente = {
		    'click .like': function (e, value, row, index) {
		    	console.log(row);
				$('#modalFecha').html('<b>'+transformaDia(row.fechaAsignacion)+'</b>'); 
				$('#modalCliente').html('<b>'+row.clienteAsignacion+' : </b>'+row.nombreRegionAsignacion);
				$('#modalCurso').html('<b>'+row.cursoAsignacion+'</b>'+" : <i><u><b>"+row.tipoCursoAsignacion+"</b></u></i>");
				$('#modalInstructor').html('<b>'+row.instructorAsignacion+'</b>');
				var asignaHorasEfectivas = row.horarioAsignacion.split(";");
				$('#modalHorario').html("<b>"+ asignaHorasEfectivas[0] +"-"+ asignaHorasEfectivas[1] +"</b> - Horas Efectivas: <b>"+ asignaHorasEfectivas[4] +"</b>");
				$('#modalParticipantes').html('<b>'+row.participantesAsignacion+'</b>'); 
				$('#modalNivel').html('<b>'+row.nivelAsignacion+'</b>');
				$('#modalObservaciones').html('<b>'+row.observacionesAsignacion+'</b>');
				$('#modalArchivos').html("<a id='link'><b>"+row.archivosAsignacion+"</b></a>");
				$("#link").attr('href', '/uploads/fileAsignacion/'+row.idAsignacionLogica+'/'+row.archivosAsignacion)
				if(row.statusAsignacion ==="Entregable Enviado") {
					$('#modalStatus').html('<b>'+row.statusAsignacion+'</b>');
					$('#modalStatus').append('<div class="alert alert-success" role="alert" >Guía Paqueteria : <b>'+row.guiaEntregable+' <b></div>');
				}else{
					$('#modalStatus').html('<b>'+row.statusAsignacion+'</b>');
				}
				$('#modalVentas').html('<b>'+row.userCreateAsignacionTexto+'</b>');
				$('#modalFechaPago').html('<b>'+row.fechaPago+'</b>');
				$('#modalFactura').html('<b>'+row.numeroFactura+'</b>');
				$('#modalArchivoParticipantes').html("<a id='linkArchivoParticipantes'><b>"+row.archivoParticipantes+"</b></a>");
				$("#linkArchivoParticipantes").attr('href', '/uploads/fileAsignacion/V'+row.idAsignacionLogica+'/'+row.archivoParticipantes)
				
				$('#modalCostoHotel').html('<b>'+row.costoHotel+'</b>');
		        
		    }
		   }
	$('#instructorAsignacionesTable').bootstrapTable({data : asignacionesHistorico})
	
}); //fin jQuery

function asignaData(datosEconomicos){
	var dataTable = new Array();
	for(var a in datosEconomicos){
		var datoEconomico = datosEconomicos[a];
		if(datoEconomico.formAEidPreAsignacion !== ""  || datoEconomico.formAEListAsignaciones !== "" ){
			var asignacionSelect   = findAsignacion(datoEconomico);
			if(asignacionSelect.length > 0){
//				console.log(asignacionSelect);
				var idAsignacion       = findIdAsignacion(asignacionSelect);
				var cliente            = findCliente(asignacionSelect[0].idAsignacion);
				var idCliente          = cliente.idCliente;
				var clienteRFC         = cliente.rfcCliente;
				var clienteRazonSocial = cliente.nombreCompletoCliente;
				var clienteNombreCorto = cliente.nombreCortoCliente;

				var registro = {
						idExpedienteEconomico : datoEconomico.idPreAsignacionAE,
						Factura :  asignacionSelect[0].numeroFactura,
						Asignacion : idAsignacion,
						Cliente : cliente.nombreCortoCliente,
						PorcentajeVentaReal : datoEconomico.formAEPorcentajeVentaReal,
						Monto : datoEconomico.formAEPrecioVentaReal,
						Estatus : datoEconomico.estatusDatoEconomico,
						FechaConfirmacion : datoEconomico.formAEFechaConfirmacion,
						FechaPromesaPago : datoEconomico.formAEFechaPromesaPago,
						FechaEmision : datoEconomico.fechaEmision,
						FechaPago : datoEconomico.fechaPago,
						FechaConfirmacionFormat : datoEconomico.formAEFechaConfirmacionFormat,
						FechaPromesaPagoFormat : datoEconomico.formAEFechaPromesaPagoFormat,
						FechaEmisionFormat : datoEconomico.fechaEmisionFormat,
						FechaPagoFormat : datoEconomico.fechaPagoFormat,
						RFC : cliente.rfcCliente,
						RazonSocial : cliente.rfcCliente, 
						NombreCorto : cliente.nombreCortoCliente,
						IdAsignacion : idAsignacion,
						NombreComercial : cliente.nombreCompletoCliente,
						Viaticos : datoEconomico.formAEViaticosTotal,
						Observaciones : datoEconomico.formAEObservaciones
				};
				dataTable.push(registro);
			}
		}
	}	
	return dataTable;
//	console.log($data);
//	$('#facturaTable').bootstrapTable({data : dataTable})
}

function funcionVendedoresDatosEconomicos(item){
	//Vendedores
	$('#divVendedoresEdicion').empty();
//	console.log("vendedores");
//	console.log(item);
	var idDatosEconomicos = item.idExpedienteEconomico;
//	console.log(idDatosEconomicos);
	var vendedoresDE = new Array();
	for(var a in vendedoresDatosEconomicos){
		var vendedor = vendedoresDatosEconomicos[a];
		if(vendedor.idDatosEconomicos*1 === idDatosEconomicos*1){
			vendedoresDE.push(vendedor);
//			console.log("vendedor");console.log(vendedor);
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
}

function findAsignacion(datoEconomico){
	var listaAsignacion = new Array();
	for(var a in asignaciones){
		var asignacion = asignaciones[a];
		if(asignacion.statusAsignacion !== "Evento Cancelado"){
			if(datoEconomico.formAEidPreAsignacion !== "" || datoEconomico.formAEidPreAsignacion !== null){  
				if(asignacion.idAsignacion*1 === datoEconomico.formAEidPreAsignacion*1){
					listaAsignacion.push(asignacion);
				}
			}else{
			 	if(datoEconomico.formAEListAsignaciones !== null || datoEconomico.formAEListAsignaciones !== ""){
					var listAsignaciones = split(datoEconomico.formAEListAsignaciones,",");
					for(var e in listAsignaciones){
						if(asignacion.idAsignacion === listAsignaciones[e]){
							listaAsignacion.push(asignacion);
						}
					}
			 	}
			}	
		}
	}
	return listaAsignacion;
}



function findIdAsignacion(asignaArray){
//	console.log(asignaArray);
	var asignacion = "";
	for(var a in asignaArray){
		asignacion = asignacion + asignaArray[a].idAsignacion + "-";
	}
	asignacion = asignacion.substring(0, asignacion.length - 1);
	return asignacion;
}

function findCliente(idAsignacion){
	var idCliente;
//	console.log(idAsignacion);
	for(var a in asignaciones){
		var asignacion = asignaciones[a];
		if(idAsignacion === asignacion.idAsignacion){
			idCliente = asignacion.idClienteAsignacion;
		}
	}
//	console.log(idCliente);
	for(var e in clientes){
		var cliente = clientes[e];
		if(idCliente === cliente.idCliente){
			return cliente;
		}
	}
	
}



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

const formatter = new Intl.NumberFormat('es-MX', {
    style: 'currency',
    currency: 'MXN',
    minimumFractionDigits: 0
  });
