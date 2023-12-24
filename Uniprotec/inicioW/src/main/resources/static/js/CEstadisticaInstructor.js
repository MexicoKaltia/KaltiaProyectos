//console.log(asignacionesHistorico);
//console.log(cursos);
//console.log(clientes);

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
				$('#modalUsuarioRegistro').html('<b>'+row.realCapturaNombre+'</b>');
				$('#modalFechaPago').html('<b>'+row.fechaPago+'</b>');
				$('#modalFactura').html('<b>'+row.numeroFactura+'</b>');
				$('#modalArchivoParticipantes').html("<a id='linkArchivoParticipantes'><b>"+row.archivoParticipantes+"</b></a>");
				$("#linkArchivoParticipantes").attr('href', '/uploads/fileAsignacion/V'+row.idAsignacionLogica+'/'+row.archivoParticipantes)
				
				$('#modalCostoHotel').html('<b>'+row.costoHotel+'</b>');
		        
		    }
		   }
	
}); //fin jQuery



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
