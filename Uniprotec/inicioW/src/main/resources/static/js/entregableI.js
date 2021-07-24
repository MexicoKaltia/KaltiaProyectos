/**
 * Archivo de control JS para Modulo Calendario 
 */

$(document).ready(function() {

	var asignacion;
	var asignaFechaCalendario ;
	var asignaClienteTexto ;
	var asignaCursoTexto ;
	var asignaInstructorTexto ;
	var asignaHorarioInicio ;
	var asignaHorarioFinal ;
	var asignaParticipantes ;
	var asignaNivel ;
	var asignaObservaciones ;
	var asignaArchivos ;
	var asignaStatus;
	var zonaCliente ;
	var item;
	
	function abrirModal(item){
		item = item.split('-');	
		for(i in asignaciones){
			asignacion = asignaciones[i]; 
			
			if(asignacion.idAsignacion.toString() === item[0].toString()){
				asignaFechaCalendario = cambiaFormatoFecha(asignacion.fechaAsignacion);
				asignaClienteTexto = asignacion.clienteAsignacion;
				asignaCursoTexto = asignacion.cursoAsignacion;
				asignaInstructorTexto = asignacion.instructorAsignacion;
				asignaHorarioInicio = horaInicio(asignacion.horarioAsignacion.toString());
				asignaHorarioFinal = horaFin(asignacion.horarioAsignacion.toString());
				asignaParticipantes = asignacion.participantesAsignacion;
				asignaNivel = asignacion.nivelAsignacion;
				asignaObservaciones = asignacion.observacionesAsignacion;
				asignaArchivos = asignacion.archivosAsignacion;
				zonaCliente = colorZonaCliente(asignacion.idRegionAsignacion, asignacion.nombreRegionAsignacion);
				asignacionTipoCurso = asignacion.tipoCursoAsignacion;
				asignaHorasEfectivas = asignacion.horarioAsignacion.split(";");
				asignaStatus = asignacion.statusAsignacion;
				asignaArchivoParticipantes = asignacion.archivoParticipantes;
				//console.log(asignacion);	
				asignaCamposSubmit(asignacion);
				break;
			}
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
//		$('#modalStatus').html('<b>'+asignaStatus+'</b>');
		if(asignaStatus ==="Entregable Enviado") {
			$('#modalStatus').html('<b>'+asignaStatus+'</b>');
			$('#modalStatus').append('<div class="alert alert-success" role="alert" >Guía Paqueteria : <b>'+asignacion.guiaEntregable+' <b></div>');
		}else{
			$('#modalStatus').html('<b>'+asignaStatus+'</b>');
		}
		$('#modalVentas').html('<b>'+asignacion.userCreateAsignacionTexto+'</b>');
		$('#modalFechaPago').html('<b>'+asignacion.fechaPago+'</b>');
		$('#modalFactura').html('<b>'+asignacion.numeroFactura+'</b>');
		$('#modalArchivoParticipantes').html('<b>'+asignacion.archivoParticipantes+'</b>');

		
		$('#myModal').modal();
		
		
	}
	
	
	var eventos = new Array();
	eventos = publicaEventos(asignaciones);
	var calendarEl = document.getElementById('calendar');
	var today = hoy();
	var calendar = new FullCalendar.Calendar(calendarEl, {
		plugins : [ 'list'],
		header	: {
		        left: 'prev,next today',
		        center: 'title',
		        right: 'listDay,listWeek,listMonth,listYear'
	      },
      views: {
          listDay: { buttonText: 'dia' },
          listWeek: { buttonText: 'semana' },
          listMonth : { buttonText: 'mes' },  
            list: { buttonText: 'todos' }  
        },

        defaultView: 'listMonth',   
        editable: true,
        eventLimit: true, // allow "more" link when too many events
		eventClick : function(info){
//			alert(info.event.title);
			abrirModal(info.event.title)
		},		
		defaultDate : today,
		navLinks : true, // can click day/week names to navigate views
		businessHours : true, // display business hours
		locale : 'es',
		events : eventos
	});

	calendar.render();
	
});


document.addEventListener('DOMContentLoaded', function() {
	
});


	function asignaCamposSubmit(asignacionSub){
//		//console.log(asignacionSub)
		$('#idAsignacion').val(asignacionSub.idAsignacion);
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
//			$('#userCreateAsignacion').val(idUsuario);userCreateAsignacion
//			$('#userCreateAsignacionTexto').val(nombreUsuario);userCreateAsignacionTexto
		$('#userCreateAsignacion').val(asignacionSub.userCreateAsignacion);
		$('#userCreateAsignacionTexto').val(asignacionSub.userCreateAsignacionTexto);
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

	function publicaEventos(asignaciones){
		
		var asignacion;
		var item;
		var inicio;
		var fin;
		var color;
		var items = new Array();
		for(i in asignaciones){
			asignacion = asignaciones[i];
			if((asignacion.idInstructorAsignacion * 1) === (operacionId * 1)){
				if(validaHoy(asignacion.fechaAsignacion.toString())){
					inicio = getInicio(asignacion.fechaAsignacion.toString(), asignacion.horarioAsignacion.toString());
					fin = getFinal(asignacion.fechaAsignacion.toString(), asignacion.horarioAsignacion.toString());
					color = getColor(asignacion.statusAsignacion);
					item = {
							'title' : asignacion.idAsignacion +"-"+ asignacion.clienteAsignacion +"-"+ asignacion.instructorAsignacion +"-"+ asignacion.cursoAsignacion ,
							'start' : inicio,
							'end' : fin,
							'constraint' : 'businessHours',
							'color' : color
					}
					items.push(item);
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
		var parse =horario[0].slice(0, 2);
		parse = parse + ":00:00";// + horario[0].slice(2, 2) + ":00" ;
//		//console.log(parse);
		return parse;
	}
	
	function horaFin(horario){
		//"1200;2300;undefined;undefined;11:00"
		//end : '2020-05-03T19:00:00',
		horario = horario.split(';');
		var parse =horario[1].slice(0, 2);
		parse = parse + ":00:00";// + horario[1].slice(2, 2) + ":00" ;
//		//console.log(parse);
		return parse;
	}
	
	
	function getColor(status){
		var zonaCliente;	
		switch (status){
		case "Curso Asignado":
			zonaCliente = 'cyan';
			break;
		case "Confirmado Instructor":
			zonaCliente = 'olive';
			break;
		case "Curso Editado":
			zonaCliente = 'silver';
			break;
		case "Curso Completado":
			zonaCliente = 'maroon';
			break;
		case "Evento Cancelado":
			zonaCliente = 'red';
			break;
		case "Entregables Validado":
			zonaCliente = 'orange';
			break;
		case "Entregable Enviado":
			zonaCliente = 'green';
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
	
	function validaHoy(fechaAsignacion){
		var hoy = new Date();
		var asignacion = new Date(fechaAsignacion)
		if(asignacion < hoy){
//			//console.log(asignacion)
			return true;
		}else
			return false;
	}
	// fin de documento