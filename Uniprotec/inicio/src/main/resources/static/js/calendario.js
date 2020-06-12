/**
 * Archivo de control JS para Modulo Calendario 
 */

$(document).ready(function() {

	var eventos = new Array();
	eventos = publicaEventos(asignaciones);
	var calendarEl = document.getElementById('calendar');
	var today = hoy();
	var calendar = new FullCalendar.Calendar(calendarEl, {
		plugins : [ 'interaction', 'dayGrid', 'timeGrid', 'list' ],
		header : {
			left : 'prev,next today',
			center : 'title',
			right : 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
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
		console.log(today);
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
			console.log(asignacion);
			inicio = getInicio(asignacion.fechaAsignacion.toString(), asignacion.horarioAsignacion.toString());
			fin = getFinal(asignacion.fechaAsignacion.toString(), asignacion.horarioAsignacion.toString());
			color = getColor(asignacion.idRegionAsignacion);
			item = {
					'title' : asignacion.idAsignacion +"-"+ asignacion.cursoAsignacion ,
					'start' : inicio,
					'end' : fin,
					'constraint' : 'businessHours',
					'color' : color
			}
			items.push(item);
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
		console.log(fecha);
		return fecha;
	}
	
	function horaInicio(horario){
		//"1200;2300;undefined;undefined;11:00"
		//start : '2020-05-03T11:00:00',
		horario = horario.split(';');
		var parse =horario[0].slice(0, 2);
		parse = parse + ":00:00";// + horario[0].slice(2, 2) + ":00" ;
		console.log(parse);
		return parse;
	}
	
	function horaFin(horario){
		//"1200;2300;undefined;undefined;11:00"
		//end : '2020-05-03T19:00:00',
		horario = horario.split(';');
		var parse =horario[1].slice(0, 2);
		parse = parse + ":00:00";// + horario[1].slice(2, 2) + ":00" ;
		console.log(parse);
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
	
	
	// fin de documento