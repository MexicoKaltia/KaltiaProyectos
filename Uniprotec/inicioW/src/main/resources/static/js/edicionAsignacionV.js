
   	
$(document).ready(function(){
	
	//console.log(asignacionItem);

	 $.asignaFecha = ordenaFecha(asignacionItem.fechaAsignacion);
	 $.asignaFecha2 ="";
	 $.asignaFechaCalendario = ""; 
	 $.asignaCliente =asignacionItem.idClienteAsignacion ;
	 $.asignaClienteTexto =asignacionItem.clienteAsignacion ;
	 $.asignaCurso=asignacionItem.idCursoAsignacion  ;
	 $.asignaCursoTexto=asignacionItem.cursoAsignacion;
	 $.asignaInstructor=asignacionItem.idInstructorAsignacion  ;
	 $.asignaInstructorTexto=asignacionItem.instructorAsignacion;
	 	var hr = asignacionItem.horarioAsignacion.split(";")
	 $.asignaHorarioInicio = horaSel(hr[0]);
	 $.asignaHorarioFinal = horaSel(hr[1]);
	 $.horasEfectivas = horaSel(hr[4]);
	 $.horasEfectivasTexto = hr[4];
	 $.asignaHorarioInicioTexto=$.asignaHorarioInicio;
	 $.asignaHorarioFinalTexto=$.asignaHorarioFinal;
	 $.asignaParticipantes=asignacionItem.participantesAsignacion;
	 $.asignaParticipantesTexto=asignacionItem.participantesAsignacion;
	 $.asignaNivel=asignacionItem.nivelAsignacion;
	 $.asignaNivelTexto=asignacionItem.nivelAsignacion;
	 $.asignaObservaciones=asignacionItem.observacionesAsignacion;
	 $.asignaArchivos=asignacionItem.archivosAsignacion;
	 $.asignaIdRegion=asignacionItem.idRegionAsignacion;
	 $.asignaNombreRegion=asignacionItem.nombreRegionAsignacion;
	 $.asignaTipoCurso=asignacionItem.tipoCursoAsignacion;
	 $.asignaUserCreateAsignacion=asignacionItem.userCreateAsignacion;
	 $.asignaUserCreateAsignacionTexto=asignacionItem.userCreateAsignacionTexto;
	 $.asignaStatusAsignacion=asignacionItem.statusAsignacion;
	 $.asignaArchivoParticipantes="";
	 $.asignaArchivoParticipantesTexto="";
	 
	 
	 
	 var proceso="<div class='alert alert-secondary' id='proceso' role='alert'>Resumen de Proceso de Edicion:<ul id='listaProceso'></ul></div>";
		var procesoVacio="";
		var alertaEmpty;
		var procesoFecha="";
		var procesoCliente="";
		var procesoCurso="";
		var procesoInstructor="";
		var procesoHorario="";
		var procesoParticipantes="";
		var procesoNivel="";
		var procesoObservaciones="";
		var procesoArchivo="";
	 
		procesoFecha="<li>Edicion Fecha: <b>"+ $.asignaFecha +"</b></li>";
		procesoCliente="<li>Edicion Cliente : <b>"+ $.asignaClienteTexto +"</b></li>";
		procesoCurso="<li>Edicion Curso : <b>"+ $.asignaCursoTexto +"</b></li>";
		procesoInstructor="<li>Edicion Instructor : <b>"+ $.asignaInstructorTexto  +"</b></li>";
		procesoHorario="<li>Edicion Horario : <b>"+ $.asignaHorarioInicioTexto +" - "+ $.asignaHorarioFinalTexto +"</b> - Horas Efectivas : <b>"+ $.horasEfectivasTexto +"</b></li>";
		procesoParticipantes="<li>Edicion Participantes : <b>"+ $.asignaParticipantesTexto  +"</b></li>";
		procesoNivel="<li>Edicion Nivel : <b>"+ $.asignaNivelTexto  +"</li>";
		procesoObservaciones="<li>Edicion Observaciones : <b>"+ $.asignaObservaciones +"</b></li>";
		procesoArchivo="<li>Edicion Archivo : <b>"+ $.asignaArchivos +"</b></li>";
	
	$(".listaProceso").empty();
	$(".listaProceso").append(procesoFecha);
	$(".listaProceso").append(procesoCliente);
	$(".listaProceso").append(procesoCurso);
	$(".listaProceso").append(procesoInstructor);
	$(".listaProceso").append(procesoHorario);
	$(".listaProceso").append(procesoParticipantes);
	$(".listaProceso").append(procesoNivel);
	$(".listaProceso").append(procesoObservaciones);
	$(".listaProceso").append(procesoArchivo);
	
	
		
	
	//--------------------------------------------------
	$("#linkFile").attr('href', '/uploads/fileAsignacion/'+asignacionItem.idAsignacionLogica+'/'+asignacionItem.archivosAsignacion)
    $("#linkFile").html('<b>'+asignacionItem.archivosAsignacion+'</b>');
	//--------------------------------------------------
	
	
	 
	 
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
   	/*
   	 * ASIGNAR
   	 */
	
	
	
	function ordenaFecha(fecha){
		fecha = fecha.split("/");
		return fecha[1]+"/"+fecha[0]+"/"+fecha[2];
	}
	
	function horaSel(horarioAsignacion){
		return horarioAsignacion.substring(0, 2) +":"+ horarioAsignacion.substring(2);
	}
	
});  // fin de documento JQuery


//  JScript
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
 *  valores Submit
 */

	function asignaCamposSubmit(){
		$('#asignar').attr('disabled', false)
		$('#idAsignacion').val(asignacionItem.idAsignacion);
		$('#idAsignacionLogica').val(asignacionItem.idAsignacionLogica);
		$('#fechaAsignacion').val(asignacionItem.fechaAsignacion);
		$('#idClienteAsignacion').val($.asignaCliente);
		$('#clienteAsignacion').val($.asignaClienteTexto);
		$('#idCursoAsignacion').val($.asignaCurso);
		$('#cursoAsignacion').val($.asignaCursoTexto);
		$('#idInstructorAsignacion').val($.asignaInstructor);
		$('#instructorAsignacion').val($.asignaInstructorTexto);
		$('#horarioAsignacion').val(horSin($.asignaHorarioInicio) +";"+ horSin($.asignaHorarioFinal) +";"+ $.asignaRecesoInicio +";"+ $.asignaRecesoFinal +";"+ $.horasEfectivasTexto);
		$('#participantesAsignacion').val($.asignaParticipantesTexto);
		$('#nivelAsignacion').val($.asignaNivelTexto);
		$('#archivosAsignacion').val($.asignaArchivos);
		$('#archivosAsignacionTexto').val($.asignaArchivos);
		$('#observacionesAsignacion').val($.asignaObservaciones);
		$('#idRegionAsignacion').val($.asignaIdRegion);
		$('#nombreRegionAsignacion').val($.asignaNombreRegion);
		$('#tipoCursoAsignacion').val($.asignaTipoCurso);
		$('#statusAsignacion').val($.asignaStatusAsignacion);
		$('#userCreateAsignacion').val($.asignaUserCreateAsignacion);
		$('#userCreateAsignacionTexto').val($.asignaUserCreateAsignacionTexto);
		$('#archivoParticipantes1').val($.asignaArchivoParticipantes);
		$('#archivoParticipantesTexto').val($.asignaArchivoParticipantes);
		
		$('#fechaPago').val(asignacionItem.fechaPago);
		$('#guiaEntregable').val(asignacionItem.guiaEntregable);
		$('#numeroFactura').val(asignacionItem.numeroFactura);
		$('#verificarEntregable').val(asignacionItem.verificarEntregable);
		$('#costoHotel').val(asignacionItem.costoHotel);
		
		
		
	}
	
	function horSin(hora){
		return hora.replace(":", "");
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
   	 * FILEUPLOAD
   	 */
   	function validaArchivos(archivosCampo){
   	  
   		if($('#archivoParticipantes').val() === null || $('#archivoParticipantes').val() === ""){
			//console.log($('#archivoParticipantes').val());
		}else{
//			//console.log($('#archivoParticipantes').val());
			$.asignaArchivoParticipantes = $('#archivoParticipantes').val();
			var asignaArchivoParticipantes = $.asignaArchivoParticipantes;
			while(asignaArchivoParticipantes.includes("C:\\fakepath\\") ){
				asignaArchivoParticipantes = asignaArchivoParticipantes.replace("C:\\fakepath\\", "")	
			}
			$.asignaArchivoParticipantes = asignaArchivoParticipantes;
			$.asignaArchivoParticipantesTexto = $.asignaArchivoParticipantes;
	        enviaFile("V" + asignacionItem.idAsignacionLogica);
	        var files = archivosCampo.files;
	        for (var i = 0; i < files.length; i++) {           
	            var file = files[i];
//	            //console.log(file);          
	            }
		}
    }
   	
   	
  	function enviaFile(rfcCliente){
		limpiaAlerta(),

		//console.log("envio idAsignacion:"+rfcCliente);
		var alerta="";
		 var form = $('#actualizaAsignacion')[0]; //$('#formImagenLogoCliente').attr('files'),
        var data = new FormData(form);
        //console.log(data);
		  $.ajax({
			url: "fileAsignacionV/"+rfcCliente,
		    type: "POST",
		    data: data,
		    enctype: 'multipart/form-data',
		    processData: false,
		    contentType: false,
		    cache: false,
		    success: 	function(data){
		    	if(data.codigo===0){
		    		if(data.codigo===0){
		  			  alerta="<div class='alert alert-success alert-dismissible fade show' role='alert'><button type='button' class='close' data-dismiss='alert'>&times;</button>Éxito carga</div>";
		  			  $(alerta).insertAfter($('.alertaFile'));
		  			  asignaCamposSubmit();
		  			  //console.log("envio ok");
		  	    	}else{
		  	    		alerta="<div class='alert alert-warning alert-dismissible fade show' role='alert'><button type='button' class='close' data-dismiss='alert'>&times;</button>archivo : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
		  				  $(alerta).insertAfter($('.alertaFile'));
		  	    		//console.log("envio Nok");
		  	    	}
		    	  } 
		    	},
		    error: function () {
		    	alerta="<div class='alert alert-danger alert-dismissible fade show' role='alert'><button type='button' class='close' data-dismiss='alert'>&times;</button>Error en carga de Archivo</div>";
				  $(alerta).insertAfter($('.alertaFile'));
		  	//console.log("envio error");
		    }
		  });

	}

  	/*
	 * ********* ALERTAS  ***********
	 */
	function limpiaAlerta(){
		$( "div" ).remove( "#limpiaAlerta" );
	}
	
	function avisaAlertaEdicion(data){
		limpiaAlerta();
		 if(data.codigo===0){
			  location.reload();
		  }
	}
	
	function avisaAlerta(data){
		limpiaAlerta();
		 if(data.codigo===0){
			 modalClose();
//			 $("#alerta").click();
			 alerta="<div id='limpiaAlerta' class='alert alert-success' role='alert'>"+data.codigo+" "+data.mensaje.toString()+"</div>";
			 alertaFade(alerta); 
		 }else{
			 modalClose();
			  alerta="<div id='limpiaAlerta' class='alert alert-warning' role='alert'>"+data.codigo+" "+data.mensaje.toString()+"</div>";
			  alertaFade(alerta); 
		  }
	}
	
	function errorAlerta(){
		alerta="<div id='limpiaAlerta' class='alert alert-danger' role='alert'>Error de Enlace</div>";
		$(alerta).insertAfter($('.alerta_in'));
	}
	




//   FIN  JScript