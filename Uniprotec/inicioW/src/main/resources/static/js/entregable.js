/**
 * Archivo de control JS para Modulo Calendario 
 */

$(document).ready(function() {
	
//	console.log(asignaciones);

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
	var asignaCostoHotel
	var zonaCliente ;
	var item;
	var idAsignacion;
	const identificadorUsuario = idUsuario;
	
	//check box filtro entregables
	var check = true;
	var checkI = true;
	var filtroEntregables = new Array();
	var filtroInstructores = new Array();
	var asignacionesFiltro = new Array();
	
	$('#btnFiltroEntregables').click(function(){
		$('#todosEntregables').prop( "checked", check );
		$('.checkboxFiltro').prop( "checked", check );
		$('.checkboxFiltroI').prop( "checked", check );
	});
	
	$( '#todosEntregables' ).on( 'click', function() {
		check = $('#todosEntregables').prop( "checked");
		$('.checkboxFiltro').prop( "checked", check );
	});
	
	$( '#todosInstructores' ).on( 'click', function() {
		checkI = $('#todosInstructores').prop( "checked");
		$('.checkboxFiltroI').prop( "checked", checkI );
	});
	
	
	
	
	$('#btnFiltro').click(function(){
		
		$('.checkboxFiltro:checked').each(function(){
			if($(this).attr('checked',true)){
				var idEntregable = $(this).attr('id');
				filtroEntregables.push(idEntregable);
			}
		});
		
		$('.checkboxFiltroI:checked').each(function(){
			if($(this).attr('checked',true)){
				var idInstructor = $(this).attr('id');
//				console.log(idInstructor);
				filtroInstructores.push(idInstructor);
			}
		});
		
		for(var e in asignaciones){
			var asignacionA  = asignaciones[e];
			for(var a in filtroEntregables){
				var entregableFiltro = filtroEntregables[a];
				var strEstatusEntregable = getStatus(entregableFiltro);
				if((asignacionA.statusAsignacion) === (strEstatusEntregable)){
					for(var i in filtroInstructores){
						var filtroInstructor = filtroInstructores[i]
						if((asignacionA.idInstructorAsignacion*1) === (filtroInstructor*1)){
							asignacionesFiltro.push(asignacionA);
						}
					}
				}
			}
		}
		calendario(asignacionesFiltro);
		filtroEntregables.length = 0;
		filtroInstructores.length = 0;
		asignacionesFiltro.length =0;
		
	});
	
	function getStatus(idEntregable){
		var estatusEntregable;	
		switch (idEntregable){
		case "cursoAsignado":
			estatusEntregable = 'Curso Asignado';
			break;
		case "confirmadoInstructor":
			estatusEntregable = 'Confirmado Instructor';
			break;
		case "cursoEditado":
			estatusEntregable = 'Curso Editado';
			break;
		case "cursoCompletado":
			estatusEntregable = 'Curso Completado';
			break;
		case "eventoCancelado":
			estatusEntregable = 'Evento Cancelado';
			break;
		case "elaborarEntregable":
			estatusEntregable = 'Elaborar Entregable';
			break;
		case "entregablesValidado":
			estatusEntregable = 'Entregables Validado';
			break;
		case "omitirEntregable":
			estatusEntregable = 'Omitir Entregable';
			break;
		case "entregableEnviado":
			estatusEntregable = 'Entregable Enviado';
			break;
		case "entregablesValidado":
			estatusEntregable = 'Entregables Validado';
			break;
		}
		return estatusEntregable;
	}
	
	function calendario(asignaciones){
		$('#calendar').empty();
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
//				alert(info.event.title);
				abrirModal(info.event.title)
			},		
			defaultDate : today,
			navLinks : true, // can click day/week names to navigate views
			businessHours : true, // display business hours
			locale : 'es',
			events : eventos
		});

		calendar.render();
	}
	
	
	
	function abrirModal(item){
		$('#asignaConfirmar').attr("disabled", true);
		$('#edicionEntregable').attr("disabled", true);
		
		item = item.split('-');	
		for(i in asignaciones){
			asignacion = asignaciones[i]; 
			
			if(asignacion.idAsignacion.toString() === item[0].toString()){
				idAsignacion = asignacion.idAsignacion;
				asignaFechaCalendario = cambiaFormatoFecha(asignacion.fechaAsignacion);
				asignaIdAsignacionLogica = asignacion.idAsignacionLogica;
				asignaClienteTexto = asignacion.clienteAsignacion;
				asignaCursoTexto = asignacion.cursoAsignacion;
				asignaInstructorTexto = asignacion.instructorAsignacion;
				asignaHorarioInicio = horaInicio(asignacion.horarioAsignacion.toString());
				asignaHorarioFinal = horaFin(asignacion.horarioAsignacion.toString());
				asignaParticipantes = asignacion.participantesAsignacion;
				asignaNivel = asignacion.nivelAsignacion;
				asignaObservaciones = asignacion.observacionesAsignacion;
				asignaArchivos = asignacion.archivosAsignacion;
				asignaArchivosTexto = asignacion.archivosAsignacionTexto;
				zonaCliente = colorZonaCliente(asignacion.idRegionAsignacion, asignacion.nombreRegionAsignacion);
				asignacionTipoCurso = asignacion.tipoCursoAsignacion;
				asignaHorasEfectivas = asignacion.horarioAsignacion.split(";");
				asignaStatus = asignacion.statusAsignacion;
				asignaArchivoParticipantes = asignacion.archivoParticipantes;
				asignaCostoHotel = asignacion.costoHotel;
				console.log(asignacion);	
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
		$('#modalStatus').empty();
		if(asignaStatus ==="Entregable Enviado") {
			$('#modalStatus').html('<b>'+asignaStatus+'</b>');
			if(asignacion.guiaEntregable.includes("||")){
				var arrayGuia = asignacion.guiaEntregable.split("||"); 
				for(var guia in arrayGuia){
					var valor = arrayGuia[guia].split("&&");
					if(valor[0]){
						$('#modalStatus').append('<div class="alert alert-success" role="alert" >Guía Paqueteria : <b>'+valor[0]+'</b> -- Id Expediente Entregable : <b>'+valor[1]+'</b> </div>');
					}
				}
			}else{
				$('#modalStatus').append('<div class="alert alert-success" role="alert" >Guía Paqueteria : <b>'+asignacion.guiaEntregable+' <b></div>');
			}
			
			
//			$('#asignaConfirmar').attr("disabled", false);
			$('#edicionEntregable').attr("disabled", false);
			$("#formEntregables").attr("action", "AEntregable");
		}else{
			$('#modalStatus').html('<b>'+asignaStatus+'</b>');
		}

		if(perfilUsuario === "Operacion" || perfilUsuario === "Direccion"){
			if(asignaStatus ==="Curso Completado" || asignaStatus ==="Elaborar Entregable" || asignaStatus ==="Entregables Validado" || asignaStatus ==="Entregable Generado" || asignaStatus ==="Omitir Entregable" ){ 
				$('#asignaConfirmar').attr("disabled", false);
				$('#edicionEntregable').attr("disabled", false);
				$('#edicionEntregable').click(function(){
					$("#formEntregables").attr("action", "AEntregable");
				})
				
			}
			else if(asignaStatus ==="Evento Cancelado") {
				$('#modalStatus').append('<div class="alert alert-warning" role="alert" id="dataError"><b>Importante : </b><u>  El curso es Cancelado, no se realiza Edición. </u></div>');
			}
		}else{
			$('#modalStatus').append('<div class="alert alert-warning" role="alert" id="dataError"><b>Importante : </b><u>  El Status de ser CURSO COMPLETADO y Perfil OPERACION / DIRECCION, para realizar Validación de Entregable. </u></div>');
		}
		
		
		$('#modalVentas').html('<b>'+asignacion.userCreateAsignacionTexto+'</b>');
		$('#modalFechaPago').html('<b>'+asignacion.fechaPago+'</b>');
		$('#modalFactura').html('<b>'+asignacion.numeroFactura+'</b>');
		$('#modalArchivoParticipantes').html('<b>'+asignacion.archivoParticipantes+'</b>');
		$('#modalCostoHotel').html('<b>'+asignacion.costoHotel+'</b>');
		if(perfilUsuario === "Instructor" || perfilUsuario === "Ventas" ){
			$('#oper').hide();
		}
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
	
}); //fin JQRY


document.addEventListener('DOMContentLoaded', function() {
	
});


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
		 $('#archivosAsignacionTexto').val(asignacionSub.archivosAsignacionTexto);
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
		 $('#costoHotel').val(asignacionSub.costoHotel);
		 $('#errorProceso').val(asignacionSub.errorProceso);
		 $('#nombreFirmaInstructor').val(getNombreFirmaInstructor(asignacionSub.idInstructorAsignacion));
		 
	}

	function getNombreFirmaInstructor(idInstructorAsignacion){
		for(var a in instructores){
			var instructor = instructores[a];
			if((instructor.idInstructor*1) === (idInstructorAsignacion*1)){
				return instructor.firmaInstructor;
			}
		}
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
//			if(asignacion.statusAsignacion !== "Evento Cancelado"){
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
//			}
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
		case "Elaborar Entregable":
			zonaCliente = 'blue';
			break;
		case "Omitir Entregable":
			zonaCliente = 'orange';
			break;
		case "Entregable Enviado":
			zonaCliente = 'green';
			break;
		case "Entregables Validado":
			zonaCliente = 'yellow';
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
		case 9:
			
			zonaCliente = '<div class="zona" style="background:cyan; color:blue">'+nombreRegion+'</div>';
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