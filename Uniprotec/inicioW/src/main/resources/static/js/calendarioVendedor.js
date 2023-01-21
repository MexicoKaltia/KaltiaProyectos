/**
 * Archivo de control JS para Modulo Calendario 
 */

$(document).ready(function() {

	var asignaFechaCalendario ;
	var asignaClienteTexto ;
	var asignaCursoTexto ;
	var asignaInstructorTexto ;
	var asignaHorarioInicio ;
	var asignaHorarioFinal ;
	var asignaHorasEfectivas ;
	var asignaParticipantes ;
	var asignaNivel ;
	var asignaObservaciones ;
	var asignaArchivos ;
	var asignaStatus;
	var zonaCliente ;
	var asignaUserCreateAsignacion;
	var costoHotel;
	
	const identificadorUsuario = idUsuario;
//	console.log(clientes);
	var preAsignacionesFiltro = new Array();
	
	calendario(preAsignacionesAE, new Array(), identificadorUsuario);
	preAsignacionesFiltro.length =0;
		
}); // fin JQuery


	function calendario(preAsignacionesAE, filtroInstructores, identificadorUsuario){
		$('#calendar').empty();
		var eventos = new Array();
		eventos = publicaEventos(preAsignacionesAE, filtroInstructores);
		var calendarEl = document.getElementById('calendar');
		var today = hoy();
		var calendar = new FullCalendar.Calendar(calendarEl, {
			plugins : [ 'interaction', 'dayGrid', 'timeGrid', 'list'],
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
			},
			
			eventClick : function(info){
//				//console.log(info.event.title);
				abrirModal(info.event.title, identificadorUsuario);
//				$('#modalPagoCobro').modal();
			},
			
			defaultDate : today,
			navLinks : true, // can click day/week names to navigate views
			businessHours : true, // display business hours
			locale : 'es',
			events : eventos
		});

		calendar.render();
	}

	
	function abrirModal(item, identificadorUsuario){
//		console.log(item);
		item = item.split('-');
		$('#fechaPagoVendedorInicio').text(valoresFecha(formatoFecha(getFechaFinalPago(item[4]))));
		$('#fechaPagoVendedorFin').text(valoresFecha(formatoFecha(getFechaFinalPago(item[5]))));
		$('#fechaCobroFactura').text(valoresFecha(formatoFecha(getFecha(item[5]))));
		$('#modalNombreCliente').text(item[1]);
		var idPreAsignacion = item[0];
		$('#idAsignacion').text(idPreAsignacion);
		
		var montoPagoVendedorInicio;
		var montoPagoVendedorFin;
		var montoCobroFactura;
		var nombreVendedor;
		var vendedorFechaAsignacion;
		var vendedorCursoAsignacion;
		var vendedorInstructorAsignacion;
		var clienteNombreCompleto;
		var clienteRFC;
		var clienteRepresentanteEmpresa;
		var clienteContacto;
		var clienteEmail;
		var clienteTelefono;
		
		var cliente
		
		
//		$('#pagoComisionTable').bootstrapTable({data : dataPagoComision});
//		$('#cobroFacturaTable').bootstrapTable({data : dataCobroFactura});
		$('#registroCobroFactura').empty();
		$('#registroPagoComision').empty();
		$('#divVendedoresDatosEconomicos').empty();
		for(var a in asignaciones){
			var preAsignacion = asignaciones[a];
			if(idPreAsignacion*1 === preAsignacion.idAsignacion*1){
				nombreVendedor = preAsignacion.userCreateAsignacionTexto;
				vendedorFechaAsignacion = valoresFecha(preAsignacion.fechaAsignacion);
				vendedorCursoAsignacion = preAsignacion.cursoAsignacion;
				vendedorInstructorAsignacion = preAsignacion.instructorAsignacion;
				preAsignacionLogica = preAsignacion.idAsignacionLogica;
				nombreFactura = preAsignacion.nombreFactura;
				for(var e in preAsignacionesAE){
					var preAsignacionAE = preAsignacionesAE[e];
					if(preAsignacionAE.formAEidPreAsignacion*1 === preAsignacion.idAsignacion*1){	
						montoPagoVendedorInicio = validaComisionReal(preAsignacionAE.formAEComisionVendedor, preAsignacionAE.formAERegla3PorcentajeNuevaComisionReal);
						montoPagoVendedorFin = validaComisionReal(preAsignacionAE.formAEComisionVendedor, preAsignacionAE.formAERegla3PorcentajeNuevaComisionReal);
						montoCobroFactura = preAsignacionAE.formAEPrecioVentaReal;
						
						/*
						 * imprime datos vendedoresDatosEconomicos
						 */
						for (var i in vendedoresDatosEconomicos){
							var vendedorDE = vendedoresDatosEconomicos[i];
							if(vendedorDE.idDatosEconomicos*1 === preAsignacionAE.idPreAsignacionAE*1){
								var registroVendedorDE = '<span>Resumen de Pago Vendedor : </span><strong><span>'+vendedorDE.nombreVendedor+'</span></strong>\
			                   		<div class="row container">\
			               			<div class="col-md-6">\
			               				<div class="alert alert-success">\
										  <h6><span>Fecha Pago 1 </span><br><span> Recibo de Factura : </span>\
										  <strong><span>'+valoresFecha(formatoFecha(getFechaFinalPago(item[4])))+'</span></strong><br>\
										  <span>Monto : </span><strong><span id="montoPagoVendedorInicioAA">'+vendedorDE.comisionRealVendedor+'</span></strong></h6>\
										</div>\
			               			</div>\
			               			<div class="col-md-6">\
			               				<div class="alert alert-info">\
										  <h6><span>Fecha Pago 2 </span><br><span> Pago de Factura : </span>\
										  <strong><span id="fechaPagoVendedorFinAA">'+valoresFecha(formatoFecha(getFechaFinalPago(item[5])))+'</span></strong><br>\
										  <span>Monto : </span><strong><span id="montoPagoVendedorFinAA">'+vendedorDE.comisionRealVendedor+'</span></strong></h6>\
										</div>\
			               			</div>\
			               		</div>'; 
										
			               		$('#divVendedoresDatosEconomicos').append(registroVendedorDE);
							}
						}
						
						
						/*
						 *  //tabla de Cobro Factura 
						 */
						
						var listFechasPromesa = stringToList(preAsignacionAE.formAEListFechaPromesaPago);
						var listFechasConfirmacion = stringToList(preAsignacionAE.formAEListFechaConfirmacion);
						
						var fechaConfirmacionLast = listFechasConfirmacion[listFechasConfirmacion.length - 1];
						console.log(fechaConfirmacionLast);
						var registroConfirmacion = "<tr><td>"+preAsignacionAE.idPreAsignacionAE+
							"</td><td>"+valoresFecha(fechaConfirmacionLast)+
			    			"</td><td>"+"Recibo de Factura"+
			    			"</tr>";
							$('#registroCobroFactura').append(registroConfirmacion);

						for(var a in listFechasPromesa){
							var fechaPago = listFechasPromesa[a];
							console.log(fechaPago);
							var registro = "<tr><td>"+preAsignacionAE.idPreAsignacionAE+
			    			"</td><td>"+valoresFecha(fechaPago)+
			    			"</td><td>"+validaCumplimiento(fechaPago)+
			    			"</tr>";
							$('#registroCobroFactura').append(registro);
						}
						

						/*
						 *  //tabla de Pago Comision 
						 */
						//tabla de Pago Comision

						var registroComision = "<tr><td>"+preAsignacionAE.idPreAsignacionAE+
		    			"</td><td>"+nombreVendedor+
		    			"</td><td>"+"1er Pago Comision"+
		    			"</td><td>"+valoresFecha(formatoFecha(getFechaFinalPago(fechaConfirmacionLast)))+
		    			"</tr>";
						$('#registroPagoComision').append(registroComision);
						
						for(var a in listFechasPromesa){
							var registro = "<tr><td>"+preAsignacionAE.idPreAsignacionAE+
			    			"</td><td>"+nombreVendedor+
			    			"</td><td>"+validaPagoComision(listFechasPromesa[a])+
			    			"</td><td>"+valoresFecha(formatoFecha(getFechaFinalPago(listFechasPromesa[a])))+
			    			"</tr>";
			
							$('#registroPagoComision').append(registro);
						}
					}
				}
				
				
				for(var i in clientes){
					var cliente = clientes[i];
					if(cliente.idCliente === preAsignacion.idClienteAsignacion){
						 clienteNombreCompleto = cliente.nombreCompletoCliente;
						 clienteRFC = cliente.rfcCliente;
						 clienteRepresentanteEmpresa = cliente.representanteEmpresaCliente;
						 clienteContacto = cliente.nombreContactoRecibeCliente;
						 clienteEmail = cliente.emailCliente;
						 clienteTelefono = cliente.telefonoCliente;
					}
				}
//				$('#seguimientoBitacoraCalendario').empty();
//				$('#seguimientoBitacoraCalendario').append(preAsignacion.seguimiento);
				break;
			}
		} //fin abrirModal
		
		
		$('#montoPagoVendedorInicio').text("$ "+montoPagoVendedorInicio);
		$('#montoPagoVendedorFin').text("$ "+montoPagoVendedorFin);
		$('#montoCobroFactura').text("$ "+montoCobroFactura);
		$('#modalNombreVendedor').text(nombreVendedor);
		
		$('#vendedorFechaAsignacion').text(vendedorFechaAsignacion);
		$('#vendedorCursoAsignacion').text(vendedorCursoAsignacion);
		$('#vendedorInstructorAsignacion').text(vendedorInstructorAsignacion);
		
		$('#clienteNombreCompleto').text(clienteNombreCompleto);
		$('#clienteRFC').text(clienteRFC);
		$('#clienteRepresentanteEmpresa').text(clienteRepresentanteEmpresa);
		$('#clienteContacto').text(clienteContacto);
		$('#clienteEmail').text(clienteEmail);
		$('#clienteTelefono').text(clienteTelefono);
		
		$('#descargaFactura').html("<a href='/uploads/fileAsignacionFactura/"+preAsignacionLogica+"/"+nombreFactura+"' id='linkFileFactura'>"+nombreFactura+"</a>");
		
		$('#modalPagoCobro').modal();
		
	}
	
	function validaComisionReal(formAEComisionVendedorReal, formAERegla3PorcentajeNuevaComisionReal){
//		console.log(formAERegla3PorcentajeNuevaComisionReal);
//		if(formAERegla3PorcentajeNuevaComisionReal*1 < 100){
//			return 0;
//		}else{
			return (formAEComisionVendedorReal / 2);
//		}
		
	}
	
	function formatoFecha(fecha){
		fecha = fecha.split("T");
		fecha0 = fecha[0].split("-");
		return fecha0[1] +"/"+fecha0[2]+"/"+fecha0[0];
//		return fecha[0];
	}
	
	
	function hoy() {
		var d = new Date();
		var dia = d.getDate();
		var mes = (d.getMonth() + 1);
		var anio = d.getFullYear();
		if (dia < 10)
			dia = "0" + dia.toString();
		if (mes < 10)
			mes = "0" + mes.toString();
		var today = anio + '-' + mes + '-' + dia;
//		//console.log(today);
		return today;
	}
	
	
	function valoresFecha(fecha){
		 var f = new Date(fecha);
			const MESES = [
			  "Enero",
			  "Febrero",
			  "Marzo",
			  "Abril",
			  "Mayo",
			  "Junio",
			  "Julio",
			  "Agosto",
			  "Septiembre",
			  "Octubre",
			  "Noviembre",
			  "Diciembre",
			];
			const DIA = [
				  "Domingo",
				  "Lunes",
				  "Martes",
				  "Miercoles",
				  "Jueves",
				  "Viernes",
				  "Sabado",
				];
				
//			$("#diaControl").html("<i>"+DIA[f.getDay()]+" "+f.getDate()+" de "+ MESES[f.getMonth()]+ " "+ f.getFullYear()+"</i>");
			return DIA[f.getDay()]+" "+f.getDate()+" de "+ MESES[f.getMonth()]+ " "+ f.getFullYear();
	}


	function publicaEventos(preAsignacionesAE, instructoresFiltro){
		
		var items = new Array();
		for(var a in asignaciones){
			var asignacion = asignaciones[a];
			for(var e in preAsignacionesAE){
				var preAsignacion  = preAsignacionesAE[e];
				if(preAsignacion.formAEidPreAsignacion*1 === asignacion.idAsignacion*1){
					if(preAsignacion.formAEFechaPromesaPagoFormat){
						var inicio = getInicio(preAsignacion.formAEFechaPromesaPagoFormat.toString());
						var fin = getFinal(preAsignacion.formAEFechaPromesaPagoFormat.toString());
						var color = "purple"; //getColor("blue");
						var item = {
								'title' : asignacion.idAsignacion +"-"+ asignacion.clienteAsignacion +"-"+ asignacion.userCreateAsignacionTexto +"-"+ asignacion.idCursoAsignacion +"-"+ preAsignacion.formAEFechaConfirmacionFormat +"-"+ preAsignacion.formAEFechaPromesaPagoFormat ,
								'start' : inicio,
								'end' : fin,
								'constraint' : 'businessHours',
								'color' : color,
								'textColor': 'white'
						}
						items.push(item);
						 inicio = getFinalPago(preAsignacion.formAEFechaConfirmacionFormat.toString());
						 fin = getFinalPago(preAsignacion.formAEFechaConfirmacionFormat.toString());
						 color = "green"; //getColor("blue");
						var item0 = {
								'title' : asignacion.idAsignacion +"-"+ asignacion.clienteAsignacion +"-"+ asignacion.userCreateAsignacionTexto +"-"+ asignacion.idCursoAsignacion +"-"+ preAsignacion.formAEFechaConfirmacionFormat +"-"+ preAsignacion.formAEFechaPromesaPagoFormat ,
								'start' : inicio,
								'end' : fin,
								'constraint' : 'businessHours',
								'color' : color,
								'textColor': 'white'
						}
						items.push(item0);
						 inicio = getFinalPago(preAsignacion.formAEFechaPromesaPagoFormat.toString());
						 fin = getFinalPago(preAsignacion.formAEFechaPromesaPagoFormat.toString());
						 color = "blue"; //getColor("blue");
						var item1 = {
								'title' : asignacion.idAsignacion +"-"+ asignacion.clienteAsignacion +"-"+ asignacion.userCreateAsignacionTexto +"-"+ asignacion.idCursoAsignacion +"-"+ preAsignacion.formAEFechaConfirmacionFormat +"-"+ preAsignacion.formAEFechaPromesaPagoFormat ,
								'start' : inicio,
								'end' : fin,
								'constraint' : 'businessHours',
								'color' : color,
								'textColor': 'white'
						}
						items.push(item1);	
					}
				}
			}
		}
		return items;
	}
	
	function getInicio(fechaAsignacion){
		return getFecha(fechaAsignacion);
	}

	function getFinal(fechaAsignacion){
		return getFecha(fechaAsignacion);
	}
	function getInicioPago(fechaAsignacion){
		return getFechaInicioPago(fechaAsignacion);
	}
	function getFinalPago(fechaAsignacion){
		return getFechaFinalPago(fechaAsignacion);
	}
	
		
	function getFecha(fechaValor){
		var d = new Date(fechaValor);
		var dia = d.getDate();
		var mes = (d.getMonth() + 1);
		var anio = d.getFullYear();
		if (dia < 10)
			dia = "0" + dia.toString();
		if (mes < 10)
			mes = "0" + mes.toString();
		var fecha = anio + '-' + mes + '-' + dia+ 'T';
		return fecha + "00:00";
	}
	
	function getFechaInicioPago(fechaValor){
		var d = new Date(fechaValor);
		var dia = d.getDate();
		var mes = (d.getMonth() + 1);
		var anio = d.getFullYear();
		if (dia < 15){
			dia = diasMes(mes);
		}else{
			dia = "15";
		}
		if (mes < 10)
			mes = "0" + mes.toString();
		
		var fecha = anio + '-' + mes + '-' + dia+ 'T';
		
		return fecha + "00:00";
	}
	
	function getFechaFinalPago(fechaValor){
		var d = new Date(fechaValor);
		var dia = d.getDate();
		var mes = (d.getMonth() + 1);
		var anio = d.getFullYear();
		if (dia < 15){
			dia = "15";
		}else{
			dia = diasMes(mes);
		}
		if (mes < 10)
			mes = "0" + mes.toString();
		
		var fecha = anio + '-' + mes + '-' + dia+ 'T';
		
		return fecha + "00:00";
	}
	
	function diasMes(mes){
		var diaFindeMes = 0;
		if(mes*1 === 1 || mes*1 === 3 || mes*1 === 5 || mes*1 === 7 || mes*1 === 8 || mes*1 === 10 || mes*1 === 12){
			diaFindeMes = 31;
		}else if(mes*1 === 2 ){
			diaFindeMes = 28;
		}else{
			diaFindeMes = 30;
		}
		return diaFindeMes;
	}
	
	
	function cambiaFormatoFecha(fecha){
		fecha = fecha.split("/");
		return fecha[1]+"/"+fecha[0]+"/"+fecha[2];
	}
	
	function validaCumplimiento(fecha){
		var fechaHoy = hoy();
		var tipoFecha = "";
		if(Date.parse(fecha) < Date.parse(fechaHoy)){
			tipoFecha = "No cumplimiento";
		}else{
			tipoFecha = "Pendiente";
		}
		return tipoFecha;
	}
	
	function validaPagoComision(fecha){
		var fechaHoy = hoy();
		var tipoFecha = "";
		if(Date.parse(fecha) < Date.parse(fechaHoy)){
			tipoFecha = "No cumplimiento";
		}else{
			tipoFecha = "2o Pago Comision";
		}
		return tipoFecha;
	}
	
	function stringToList(cadena){
		return cadena.split(";");
	}
	// fin de documento
	
	function hoy() {
		var d = new Date();
		var dia = d.getDate();
		var mes = (d.getMonth() + 1);
		var anio = d.getFullYear();
		if (dia < 10)
			dia = "0" + dia.toString();
		if (mes < 10)
			mes = "0" + mes.toString();
		var today = anio + '-' + mes + '-' + dia;
//		//console.log(today);
		return today;
	}