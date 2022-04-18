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
//	//console.log("id usuario sesion:"+idUsuario)
	var filtroInstructores = new Array();
	var asignacionesFiltro = new Array();
	
	
	
	
	if($('#todosInstructores').prop('checked')){
		calendario(asignaciones, filtroInstructores, identificadorUsuario);
	}
	
	//check box filtro Instructores
	var check = false;
	$( '#todosInstructores' ).on( 'click', function() {
		if(check === false){
			check = true;
		}else{
			check = false;
		}
		$('.checkboxFiltro').prop( "checked", check );
		
	});
	
	$('#btnFiltroInstructores').click(function(){
		$('#todosInstructores').prop( "checked", false );
	});
	
	$('#btnFiltro').click(function(){
		
		$('.checkboxFiltro:checked').each(function(){
			if($(this).attr('checked',true)){
				idInstructor = $(this).attr('id')
				filtroInstructores.push(idInstructor);
			}
		});
//		//console.log(filtroInstructores);
		
		for(e in asignaciones){
			var asignacionA  = asignaciones[e];
			for(a in filtroInstructores){
				var instructorFiltro = filtroInstructores[a];
				if((asignacionA.idInstructorAsignacion * 1) === (instructorFiltro * 1)){
					asignacionesFiltro.push(asignacionA);
				}
			}
		}
		calendario(asignacionesFiltro, filtroInstructores, identificadorUsuario);
		filtroInstructores.length = 0;
		asignacionesFiltro.length =0;
		
	});
	
	
		
});


	function calendario(asignaciones, filtroInstructores, identificadorUsuario){
		$('#calendar').empty();
		var eventos = new Array();
		eventos = publicaEventos(asignaciones, filtroInstructores);
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
		if(item.length == 1){
			//console.log("instructor dia de ausencia");
			return null;
		}
		
		var asignacion0="";
		for(i in asignaciones){
			var asignacion01= asignaciones[i];
			asignacion0 = asignacion01;
			if((asignacion01.idAsignacion*1) === (item[0]*1)){
//				//console.log(asignacion01);
				break;
			}
		}
		
		var status = asignacion0.statusAsignacion;
//		alert(status);
//		alert(perfilUsuario);
		
			$("#divOperacion").empty();
			$("#divEdicionVentas").empty();
			$("#divArchivoParticipantes").empty();
			$("#divErrorProceso").empty();
			$('#seccionErrorProceso').html("");
			if(perfilUsuario === "Vendedor"){
				if(identificadorUsuario === asignacion0.userCreateAsignacion){
					console.log(identificadorUsuario);
					$("#divOperacion").append('<button type="submit" id="asignaConfirmar0" class="btn btn-primary pull-left"  >Revision Expediente Asignación / Cliente</button>');
					$("#divArchivoParticipantes").append('<button type="submit" id="archivoParticipantes" class="btn btn-info pull-right"  value="">Adjuntar Archivo Participantes</button>');
					
					if(status === "Curso Asignado" || status === "Confirmado Instructor" || status === "Curso Editado"){
						$("#divEdicionVentas").append('<button type="submit" id="asignaConfirmar" class="btn btn-success pull-right btn-lg"  value="">Edicion Atributos Asignación</button>');
					}
					
					$("#divOperacion").click(function(){
						$("#edicionAsignacion").attr("action", "/BAsignacionI");
					})
					$("#divArchivoParticipantes").click(function(){
						$("#edicionAsignacion").attr("action", "/BAsignacionV");
					})
				}
			}
			
			if(perfilUsuario === "Administracion"){
				$("#edicionAsignacion").attr("action", "/BAsignacionI");
				$("#btnSubmit").empty();
				$("#btnSubmit").append('<button type="submit" id="asignaConfirmarA" class="btn btn-success pull-right btn-lg"  value="">Edición Fecha de Pago y Numero de Factura</button>');
				$("#asignaConfirmar").hide();
			}else if(perfilUsuario === "Operacion" || perfilUsuario === "Direccion"){
				$("#divOperacion").empty();
				$("#divOperacion").append('<button type="submit" id="asignaConfirmarO" class="btn btn-primary pull-center"  >Revision Expediente Asignación / Cliente</button>');
				$("#divArchivoParticipantes").append('<button type="submit" id="archivoParticipantes" class="btn btn-info pull-right"  value="">Adjuntar Archivo Participantes</button>');
				
				if(status === "Curso Asignado" || status === "Confirmado Instructor" || status === "Curso Editado"){
					$("#divEdicionVentas").append('<button type="submit" id="asignaConfirmar" class="btn btn-success pull-right btn-lg"  value="">Edicion Atributos Asignación</button>');
				}
				
				$("#divOperacion").click(function(){
					$("#edicionAsignacion").attr("action", "/BAsignacionI");
				})
				$("#divArchivoParticipantes").click(function(){
					$("#edicionAsignacion").attr("action", "/BAsignacionV");
				})
				/*
				 * btn errorProceso
				 */
				if(asignacion0.statusAsignacion !== "Curso Asignado" && asignacion0.statusAsignacion !== "Confirmado Instructor" && asignacion0.statusAsignacion !== "Curso Editado"){
					$("#divErrorProceso").empty();	
					$("#divErrorProceso").append('<button class="btn btn-focus" id="btnErrorProceso" type="button" data-toggle="modal" data-target="#modalFormErrorProceso" >Error Proceso</button>');
					$('#seccionErrorProceso').html("");
			        $('#seccionErrorProceso').html(asignacion0.errorProceso);
				}
				
			}else{
				$("#btnSubmit").empty();
//				$("#asignaConfirmar").hide();
				$("#oper").hide();
			}
			
			$("#btnSubmit").append('');
			
			if(asignacion0.idAsignacion.toString() === item[0].toString()){
				asignaFechaCalendario = cambiaFormatoFecha(asignacion0.fechaAsignacion);
				asignaClienteTexto = asignacion0.clienteAsignacion;
				asignaCursoTexto = asignacion0.cursoAsignacion;
				asignaInstructorTexto = asignacion0.instructorAsignacion;
				asignaHorarioInicio = horaInicio(asignacion0.horarioAsignacion.toString());
				asignaHorarioFinal = horaFin(asignacion0.horarioAsignacion.toString());
				asignaHorasEfectivas = horaFin(asignacion0.horarioAsignacion.toString());
				asignaParticipantes = asignacion0.participantesAsignacion;
				asignaNivel = asignacion0.nivelAsignacion;
				asignaObservaciones = asignacion0.observacionesAsignacion;
				asignaArchivos = asignacion0.archivosAsignacion;
				zonaCliente = colorZonaCliente(asignacion0.idRegionAsignacion, asignacion0.nombreRegionAsignacion);
				asignacionTipoCurso = asignacion0.tipoCursoAsignacion;
				asignaHorasEfectivas = asignacion0.horarioAsignacion.split(";");
				asignaStatus = asignacion0.statusAsignacion;
				asignaUserCreateAsignacion = asignacion0.userCreateAsignacionTexto
				asignaFechaPago= asignacion0.fechaPago;
				asignaFactura = asignacion0.numeroFactura;
				archivoParticipantes=asignacion0.archivoParticipantes;
				costoHotel=asignacion0.costoHotel;
				errorProceso=asignacion0.errorProceso;
//				console.log(asignacion0);	
				asignaCamposSubmit(asignacion0);
				valErrorProceso(asignacion0);
				
//				break;
			}

		$('#modalFecha').html('<b>'+asignaFechaCalendario+'</b>'); 
		$('#modalCliente').html('<b>'+asignaClienteTexto+'</b>'+zonaCliente);
		$('#modalCurso').html('<b>'+asignaCursoTexto+'</b>'+" : <i><u><b>"+asignacionTipoCurso+"</b></u></i>");
		$('#modalInstructor').html('<b>'+asignaInstructorTexto+'</b>');
		$('#modalHorarioInicio').html('<b>'+asignaHorarioInicio+'</b>');
		if((asignaHorasEfectivas[3] * 1) > 0)
			$('#modalHorario').html("<b>"+ asignaHorarioInicio+"-"+asignaHorarioFinal+"</b> - Horario Receso: <b>"+asignaHorasEfectivas[2]+"-"+asignaHorasEfectivas[3]+"</b> - Horas Efectivas: <b>"+asignaHorasEfectivas[4]+"</b>");
		else
			$('#modalHorario').html("<b>"+ asignaHorarioInicio+"-"+asignaHorarioFinal+"</b> - Horas Efectivas: <b>"+asignaHorasEfectivas[4]+"</b>");
		$('#modalParticipantes').html('<b>'+asignaParticipantes+'</b>'); 
		$('#modalNivel').html('<b>'+asignaNivel+'</b>');
		$('#modalObservaciones').html('<b>'+asignaObservaciones+'</b>');
		$('#modalArchivos').html('<b>'+asignaArchivos+'</b>');
		if(asignaStatus ==="Entregable Enviado") {
			$('#modalStatus').html('<b>'+asignaStatus+'</b>');
			$('#modalStatus').append('<div class="alert alert-success" role="alert" >Guía Paqueteria : <b>'+asignacion0.guiaEntregable+' <b></div>');
		}else{
			$('#modalStatus').html('<b>'+asignaStatus+'</b>');
		}
		$('#modalVentas').html('<b>'+asignaUserCreateAsignacion+'</b>');
		$('#modalFechaPago').html('<b>'+asignaFechaPago+'</b>');
		$('#modalFactura').html('<b>'+asignaFactura+'</b>');
		$('#modalArchivoParticipantes').html('<b>'+archivoParticipantes+'</b>');
		$('#modalCostoHotel').html('<b>'+costoHotel+'</b>');
		if(errorProceso !== "" && errorProceso){
//			//console.log(errorProceso);
			$('#liErrorProceso').remove();
			liErrorProceso = '<li id="liErrorProceso" class="list-group-item list-group-item-info">Error Proceso : <span id="modalErrorProceso"></span></li>'
			$('#resumenAsignacionModal').append(liErrorProceso);	
			$('#modalErrorProceso').html('<b>'+errorProceso+'</b>');
		}else{
			$('#liErrorProceso').remove();
		}
		if(perfilUsuario !== "Administracion"){
			$('#admon').hide();
		}
		
		
		$('#myModal').modal();
		
		
	}
	
	
	function asignaCamposSubmit(asignacionSub){
//		//console.log(asignacionSub)
		$('#idAsignacion').val(asignacionSub.idAsignacion);
		$('#idAsignacionLogica').val(asignacionSub.idAsignacionLogica);
		$('#fechaAsignacion').val(asignacionSub.fechaAsignacion);
		$('#idClienteAsignacion').val(asignacionSub.idClienteAsignacion);
		$('#clienteAsignacion').val(asignacionSub.clienteAsignacion);
		$('#idCursoAsignacion').val(asignacionSub.idCursoAsignacion);
		$('#cursoAsignacion').val(asignacionSub.cursoAsignacion);
		$('#idInstructorAsignacion').val(asignacionSub.idInstructorAsignacion);
		$('#instructorAsignacion').val(asignacionSub.instructorAsignacion);
		$('#horarioAsignacion').val(asignacionSub.horarioAsignacion);
		$('#participantesAsignacion').val(asignacionSub.participantesAsignacion);
		$('#nivelAsignacion').val(asignacionSub.nivelAsignacion);
		$('#archivosAsignacion').val(asignacionSub.archivosAsignacion);
		$('#observacionesAsignacion').val(asignacionSub.observacionesAsignacion);
		$('#idRegionAsignacion').val(asignacionSub.idRegionAsignacion);
		$('#nombreRegionAsignacion').val(asignacionSub.nombreRegionAsignacion);
		$('#tipoCursoAsignacion').val(asignacionSub.tipoCursoAsignacion);
		$('#statusAsignacion').val(asignacionSub.statusAsignacion);
		$('#guiaEntregable').val(asignacionSub.guiaEntregable);
		$('#fechaPago').val(asignacionSub.fechaPago);
		$('#numeroFactura').val(asignacionSub.numeroFactura);
		$('#userCreateAsignacion').val(asignacionSub.userCreateAsignacion);
		$('#userCreateAsignacionTexto').val(asignacionSub.userCreateAsignacionTexto);
		
		$('#archivoParticipantes').val(asignacionSub.archivoParticipantes);
		$('#archivoParticipantesTexto').val(asignacionSub.archivoParticipantes);
		
		$('#verificarEntregable').val(asignacionSub.verificarEntregable);
		$('#costoHotel').val(asignacionSub.costoHotel);
		
		$('#errorProceso').val(asignacionSub.errorProceso);
		
	}
	
	/*
	 * Submit Error de Proceso
	 */
	function valErrorProceso(asignacionSub){		
//		 //console.log(asignacionSub);
//		 //console.log($('#seccionErrorProceso').html());
//		 
		$('#idAsignacion1').val(asignacionSub.idAsignacion);
		$('#idAsignacionLogica1').val(asignacionSub.idAsignacionLogica);
		$('#fechaAsignacion1').val(asignacionSub.fechaAsignacion);
		$('#idClienteAsignacion1').val(asignacionSub.idClienteAsignacion);
		$('#clienteAsignacion1').val(asignacionSub.clienteAsignacion);
		$('#idCursoAsignacion1').val(asignacionSub.idCursoAsignacion);
		$('#cursoAsignacion1').val(asignacionSub.cursoAsignacion);
		$('#idInstructorAsignacion1').val(asignacionSub.idInstructorAsignacion);
		$('#instructorAsignacion1').val(asignacionSub.instructorAsignacion);
		$('#horarioAsignacion1').val(asignacionSub.horarioAsignacion);
		$('#participantesAsignacion1').val(asignacionSub.participantesAsignacion);
		$('#nivelAsignacion1').val(asignacionSub.nivelAsignacion);
		$('#archivosAsignacion1').val(asignacionSub.archivosAsignacion);
		$('#observacionesAsignacion1').val(asignacionSub.observacionesAsignacion);
		$('#idRegionAsignacion1').val(asignacionSub.idRegionAsignacion);
		$('#nombreRegionAsignacion1').val(asignacionSub.nombreRegionAsignacion);
		$('#tipoCursoAsignacion1').val(asignacionSub.tipoCursoAsignacion);
		$('#statusAsignacion1').val(asignacionSub.statusAsignacion);
		$('#guiaEntregable1').val(asignacionSub.guiaEntregable);
		$('#fechaPago1').val(asignacionSub.fechaPago);
		$('#numeroFactura1').val(asignacionSub.numeroFactura);
		$('#userCreateAsignacion1').val(asignacionSub.userCreateAsignacion);
		$('#userCreateAsignacionTexto1').val(asignacionSub.userCreateAsignacionTexto);
		
		$('#archivoParticipantes1').val(asignacionSub.archivoParticipantes);
		$('#archivoParticipantesTexto1').val(asignacionSub.archivoParticipantes);
		
		$('#verificarEntregable1').val(asignacionSub.verificarEntregable);
		$('#costoHotel1').val(asignacionSub.costoHotel);
		
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

	function publicaEventos(asignaciones, instructoresFiltro){
		var asignacion;
		var item;
		var inicio;
		var fin;
		var color;
		var items = new Array();
		var listaFechasAusencia = new Array();
		var instructor ;
		var fechaAusencia ;
//		//console.log(instructores);
		for(i in asignaciones){
			asignacion = asignaciones[i];
//			//console.log(asignacion);
			if(asignacion.statusAsignacion !== "Evento Cancelado"){
				inicio = getInicio(asignacion.fechaAsignacion.toString(), asignacion.horarioAsignacion.toString());
				fin = getFinal(asignacion.fechaAsignacion.toString(), asignacion.horarioAsignacion.toString());
				color = getColor(asignacion.idRegionAsignacion);
				if(color === "blue" || color ==="fuchsia" || color ==="chocolate" || color ==="purple"){
					item = {
							'title' : asignacion.idAsignacion +"-"+ asignacion.clienteAsignacion +"-"+ asignacion.instructorAsignacion +"-"+ asignacion.cursoAsignacion ,
							'start' : inicio,
							'end' : fin,
							'constraint' : 'businessHours',
							'color' : color,
							'textColor': 'white'
					}
				}else{
					item = {
							'title' : asignacion.idAsignacion +"-"+ asignacion.clienteAsignacion +"-"+ asignacion.instructorAsignacion +"-"+ asignacion.cursoAsignacion ,
							'start' : inicio,
							'end' : fin,
							'constraint' : 'businessHours',
							'color' : color
					}
				}
				
				items.push(item);
			}
			
		}
		/*
		 * Recolecta los instructores y asigna los items dias de ausencia por Instructor y dia
		 */
		var banderaInstructores = 0;
		if(instructoresFiltro.length > 0){
			banderaInstructores = 1;
		}
		
//		//console.log(banderaInstructores);
		if(banderaInstructores == 0){
			for(e in instructores){
				 listaFechasAusencia = new Array();
				 instructor = instructores[e];
				if(instructor.listFechas){
//					//console.log(instructor.nombreInstructor)
					 listaFechasAusencia = instructor.listFechas.split(";");
					 for(a in listaFechasAusencia){
						 fechaAusencia = getFecha(listaFechasAusencia[a]);
						 item = {
								'title' : instructor.nombreInstructor ,
								'start' : fechaAusencia+'03:00',
								'end' : fechaAusencia+'03:00',
								'color' : 'red',
								'textColor': 'white'
								}
						 items.push(item);
					 } 
				 }
			}
		}else{
			for(e in instructores){
				 listaFechasAusencia = new Array();
				 instructor = instructores[e];
				 for(o in instructoresFiltro){
						if(instructor.idInstructor == instructoresFiltro[o]){
							if(instructor.listFechas){
//								 //console.log(instructor.nombreInstructor)
								 listaFechasAusencia = instructor.listFechas.split(";");
								 for(a in listaFechasAusencia){
									 fechaAusencia = getFecha(listaFechasAusencia[a]);
//									 //console.log(fechaAusencia);
									 item = {
												'title' : instructor.nombreInstructor ,
												'start' : fechaAusencia+'00:00',
												'end' : fechaAusencia+'00:00',
//												'constraint' : 'businessHours',
												'color' : 'red',
												'textColor': 'white'
										}
									 items.push(item);
								 } 
							 }
						 }
					}
			}
		}
		
		return items;
	}
	
	function getInicio(fechaAsignacion, horarioAsignacion){
		
		return getFecha(fechaAsignacion) + horaInicio(horarioAsignacion);
	}

	function getFinal(fechaAsignacion, horarioAsignacion){
		
		return getFecha(fechaAsignacion) + horaFin(horarioAsignacion);
	}

		
	function getFecha(fechaValor){
//		fechaValor = fechaValor.split('/')
		var d = new Date(fechaValor);
		var dia = d.getDate();
		var mes = (d.getMonth() + 1);
		var anio = d.getFullYear();
		if (dia < 10)
			dia = "0" + dia.toString();
		if (mes < 10)
			mes = "0" + mes.toString();
		var fecha = anio + '-' + mes + '-' + dia+ 'T';
//		//console.log(fecha);
		return fecha;
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
	
	function horaFin(horario){
		//"1200;2300;undefined;undefined;11:00"
		//end : '2020-05-03T19:00:00',
		horario = horario.split(';');
		var parse =horario[1].substring(0, 2) +":"+ horario[1].substring(2);
		
		return parse;
	}
	

	
	
	function getColor(cliente){
		var zonaCliente;	
		cliente = (cliente * 1)
		switch (cliente){
		case 1:
			zonaCliente = 'yellow';
			break;
		case 2:
			zonaCliente = 'blue';
			break;
		case 3:
			zonaCliente = 'fuchsia';
			break;
		case 4:
			zonaCliente = 'lime';
			break;
		case 5:
			zonaCliente = 'gray';
			break;
		case 6:
			zonaCliente = 'coral';
			break;
		case 7:
			zonaCliente = 'chocolate';
			break;
		case 8:
			zonaCliente = 'purple';
			break;
		}
		return zonaCliente;
	}
	
	function colorZonaCliente(idRegion, nombreRegionAsignacion){
		
		var nombreRegion = nombreRegionAsignacion;
		switch (idRegion){
		case 1:
			
			zonaCliente = '<div class="zona" style="background:yellow; color:blue">'+nombreRegion+'</div>';
			break;
		case 2:
			
			zonaCliente = '<div class="zona" style="background:blue; color:white">'+nombreRegion+'</div>';
			break;
		case 3:
			
			zonaCliente = '<div class="zona" style="background:fuchsia; color:white">'+nombreRegion+'</div>';
			break;
		case 4:
			
			zonaCliente = '<div class="zona" style="background:lime; color:blue">'+nombreRegion+'</div>';
			break;
		case 5:
			
			zonaCliente = '<div class="zona" style="background:gray; color:blue">'+nombreRegion+'</div>';
			break;
		case 6:
			
			zonaCliente = '<div class="zona" style="background:coral; color:blue">'+nombreRegion+'</div>';
			break;
		case 7:
			
			zonaCliente = '<div class="zona" style="background:chocolate; color:white">'+nombreRegion+'</div>';
			break;
		case 8:
			
			zonaCliente = '<div class="zona" style="background:purple; color:white">'+nombreRegion+'</div>';
			break;
		}
		$.asignaIdRegion = idRegion;
		$.asignaNombreRegion = nombreRegion;
		return zonaCliente;
				
	}
	
	function horasEfectivas(horarioAsignacion){
		horariosAsignacion = horariosAsignacion.split(";");
		return horariosAsignacion[4];
	}
	
	function cambiaFormatoFecha(fecha){
		fecha = fecha.split("/");
		return fecha[1]+"/"+fecha[0]+"/"+fecha[2];
	}
	// fin de documento