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
	console.log(clientes);
	var preAsignacionesFiltro = new Array();
	
	calendario(preAsignaciones, new Array(), identificadorUsuario);
	preAsignacionesFiltro.length =0;
		
}); // fin JQuery


	function calendario(preAsignaciones, filtroInstructores, identificadorUsuario){
		$('#calendar').empty();
		var eventos = new Array();
		eventos = publicaEventos(preAsignaciones, filtroInstructores);
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
		item = item.split('-');
		$('#fechaPagoVendedorInicio').text(valoresFecha(formatoFecha(getFechaPago(item[5]))));
		$('#fechaPagoVendedorFin').text(valoresFecha(formatoFecha(getFechaPago(item[4]))));
		$('#fechaCobroFactura').text(valoresFecha(formatoFecha(getFechaPago(item[4]))));
		$('#modalNombreCliente').text(item[1]);
		
		var idPreAsignacion = item[0];
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
		
		for(var a in preAsignaciones){
			var preAsignacion = preAsignaciones[a];
			if(idPreAsignacion*1 === preAsignacion.idPreAsignacion){
				nombreVendedor = preAsignacion.userCreateAsignacionTexto;
				vendedorFechaAsignacion = valoresFecha(preAsignacion.fechaAsignacion);
				vendedorCursoAsignacion = preAsignacion.cursoAsignacion;
				vendedorInstructorAsignacion = preAsignacion.instructorAsignacion;
				preAsignacionLogica = preAsignacion.idAsignacionLogica;
				nombreFactura = preAsignacion.nombreFactura;
				for(var e in preAsignacionesAE){
					var preAsignacionAE = preAsignacionesAE[e];
					if(preAsignacion.idPreAsignacionAE === preAsignacionAE.idPreAsignacionAE){
						montoPagoVendedorInicio = validaComisionReal(preAsignacionAE.formAEComisionVendedorReal, preAsignacionAE.formAERegla3PorcentajeNuevaComisionReal);
						montoPagoVendedorFin = validaComisionReal(preAsignacionAE.formAEComisionVendedorReal, preAsignacionAE.formAERegla3PorcentajeNuevaComisionReal);
						montoCobroFactura = preAsignacionAE.formAEPrecioVentaReal;
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
				break;
			}
		}
		
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


	function publicaEventos(preAsignaciones, instructoresFiltro){
		var preAsignacion;
		
		
		var items = new Array();
		for(e in preAsignaciones){
			var preAsignacion  = preAsignaciones[e];
			if(preAsignacion.fechaFinFactura){
				var inicio = getInicio(preAsignacion.fechaFinFactura.toString());
				var fin = getFinal(preAsignacion.fechaFinFactura.toString());
				var color = "purple"; //getColor("blue");
			
				var item = {
						'title' : preAsignacion.idPreAsignacion +"-"+ preAsignacion.clienteAsignacion +"-"+ preAsignacion.userCreateAsignacionTexto +"-"+ preAsignacion.idCursoAsignacion +"-"+ preAsignacion.fechaFinFactura +"-"+ preAsignacion.fechaHoy,
						'start' : inicio,
						'end' : fin,
						'constraint' : 'businessHours',
						'color' : color,
						'textColor': 'white'
				}
				items.push(item);
				 inicio = getInicioPago(preAsignacion.fechaHoy.toString());
				 fin = getInicioPago(preAsignacion.fechaHoy.toString());
				 color = "green"; //getColor("blue");
				var item0 = {
						'title' : preAsignacion.idPreAsignacion +"-"+ preAsignacion.clienteAsignacion +"-"+ preAsignacion.userCreateAsignacionTexto +"-"+ preAsignacion.idCursoAsignacion +"-"+ preAsignacion.fechaFinFactura +"-"+ preAsignacion.fechaHoy,
						'start' : inicio,
						'end' : fin,
						'constraint' : 'businessHours',
						'color' : color,
						'textColor': 'white'
				}
				items.push(item0);
				 inicio = getInicioPago(preAsignacion.fechaFinFactura.toString());
				 fin = getInicioPago(preAsignacion.fechaFinFactura.toString());
				 color = "blue"; //getColor("blue");
				var item1 = {
						'title' : preAsignacion.idPreAsignacion +"-"+ preAsignacion.clienteAsignacion +"-"+ preAsignacion.userCreateAsignacionTexto +"-"+ preAsignacion.idCursoAsignacion +"-"+ preAsignacion.fechaFinFactura +"-"+ preAsignacion.fechaHoy,
						'start' : inicio,
						'end' : fin,
						'constraint' : 'businessHours',
						'color' : color,
						'textColor': 'white'
				}
				items.push(item1);	
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
		return getFechaPago(fechaAsignacion);
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
	
	function getFechaPago(fechaValor){
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
	// fin de documento