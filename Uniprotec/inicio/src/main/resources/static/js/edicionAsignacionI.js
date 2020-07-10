
   	
$(document).ready(function(){
	
	console.log(asignacionItem);

	 $.asignaFecha = ordenaFecha(asignacionItem.fechaAsignacion);
	 $.asignaFecha2 ="";
	 $.asignaFechaCalendario = ""; 
	 $.asignaCliente =asignacionItem.idClienteAsignacion ;
	 $.asignaClienteTexto =asignacionItem.clienteAsignacion ;
	 $.asignaCurso=asignacionItem.idCursoAsignacion  ;
	 $.asignaCursoTexto=asignacionItem.cursoAsignacion;
	 $.asignaInstructor=asignacionItem.idInstructorAsignacion  ;
	 $.asignaInstructorTexto=asignacionItem.instructorAsignacion;
	 $.asignaHorario=horario(asignacionItem.horarioAsignacion);
	 $.asignaParticipantes=asignacionItem.participantesAsignacion;
	 $.asignaParticipantesTexto=asignacionItem.participantesAsignacion;
	 $.asignaNivel=asignacionItem.nivelAsignacion;
	 $.asignaNivelTexto=asignacionItem.nivelAsignacion;
	 $.asignaObservaciones=asignacionItem.observacionesAsignacion;
	 $.asignaArchivos=asignacionItem.archivosAsignacion;
	 $.asignaIdRegion=asignacionItem.idRegionAsignacion;
	 $.asignaNombreRegion=asignacionItem.nombreRegionAsignacion;
	 $.asignaTipoCurso=asignacionItem.tipoCursoAsignacion;
	 $.asignaStatus=asignacionItem.statusAsignacion;
	 var nombreRegion = asignacionItem.nombreRegionAsignacion;
	 var regionCliente = regionCliente($.asignaIdRegion);
	 
		function ordenaFecha(fecha){
			fecha = fecha.split("/");
			return fecha[1]+"/"+fecha[0]+"/"+fecha[2];
		}

	 
		function horario(horario){
			var hr = horario.split(";")
			var hrInicio = horaSel(hr[0]);
			var hrFinal = horaSel(hr[1]);
			var hrDesInicio = hr[2];
			var hrDesFinal = hr[3];
			var hrEfectivas = hr[4];
			if(hrDesInicio >0 ){
				return hrInicio +":00 - "+ hrFinal +":00.  Receso:"+ hrDesInicio +":00-"+ hrDesFinal+":00.  Hrs Efectivas:"+hrEfectivas
			}else{
				return hrInicio +":00 - "+ hrFinal +":00.  Hrs Efectivas:"+hrEfectivas
			}
		}
		
		function horaSel(horarioAsignacion){
			return horarioAsignacion.slice(0,2);
		}
		
		
		function regionCliente(idRegion){
			switch (idRegion){
			case 1:
				zonaCliente = '<div class="zona" style="background:yellow; color:blue">'+nombreRegion+'</div>';
				break;
			case 2:
				zonaCliente = '<div class="zona" style="background:blue">'+nombreRegion+'</div>';
				break;
			case 3:
				zonaCliente = '<div class="zona" style="background:fuchsia">'+nombreRegion+'</div>';
				break;
			case 4:
				zonaCliente = '<div class="zona" style="background:lime; color:blue">'+nombreRegion+'</div>';
				break;
			case 5:
				zonaCliente = '<div class="zona" style="background:gray">'+nombreRegion+'</div>';
				break;
			case 6:
				zonaCliente = '<div class="zona" style="background:coral; color:blue">'+nombreRegion+'</div>';
				break;
			case 7:
				zonaCliente = '<div class="zona" style="background:chocolate">'+nombreRegion+'</div>';
				break;
			case 8:
				zonaCliente = '<div class="zona" style="background:purple">'+nombreRegion+'</div>';
				break;
			}
			$.asignaIdRegion = idRegion;
			$.asignaNombreRegion = nombreRegion;
			return zonaCliente;
		}
	
	//--------------------------------------------------
	$('#fechaAsignacion').append("<h4><b>"+$.asignaFecha+"</b></h4><br>");
	//--------------------------------------------------
	$('#clienteAsignacion').html($.asignaClienteTexto);
//	$('#clienteAsignacion2').html($.regionCliente);
	//--------------------------------------------------
	$('#cursoAsignacion').append("<h4><b>"+$.asignaCursoTexto+" : "+$.asignaTipoCurso+"</b></h4>");
	//--------------------------------------------------
	$('#instructorAsignacion').append("<h4><b>"+$.asignaInstructorTexto+"</b></h4>");
	//--------------------------------------------------
	$('#horarioAsignacion').append("<h4><b>"+$.asignaHorario+"</b></h4>");
	//--------------------------------------------------
	$('#participantesAsignacion').append("<h4><b>"+$.asignaParticipantes+"</b></h4>");
	//--------------------------------------------------
	$('#nivelAsignacion').append("<h4><b>"+$.asignaNivel+"</b></h4>");
	//--------------------------------------------------
	$('#observacionesAsignacion').append("<h4><b>"+$.asignaObservaciones+"</b></h4>");
	//--------------------------------------------------
	$('#archivosAsignacion').append("<a id='link'><h4><b>"+$.asignaArchivos+"</b></h4></a>");
	//--------------------------------------------------
	$("#link").attr('href', '/uploads/fileAsignacion/'+asignacionItem.idAsignacionLogica+'/'+asignacionItem.archivosAsignacion)
//    $("#link").html('<b>'+asignacionItem.archivosAsignacion+'</b>');
	//--------------------------------------------------
	$('#statusAsignacion1').html($.asignaStatus);
//	$('#statusAsignacion').append("<a id='link'><h4><b>"+$.asignaStatus+"</b></h4></a>");
	
	 
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
   	/*
   	 * ASIGNAR MODAL CLIENTE asignacionCliente
   	 */
	console.log(asignacionCliente);
	$('#nombreCortoCliente').html('<b>'+asignacionCliente.nombreCortoCliente+'</b>');
	$('#regionCliente').html('<b>'+asignacionCliente.regionCliente.nombreRegion+'</b>');
	$('#domicilioCliente').html('<b>'+asignacionCliente.domicilioCliente+'</b>');
	$('#telefonoCliente').html('<b>'+asignacionCliente.telefonoCliente+'</b>');
	$('#googleMapsCliente').html('<b><a href="'+asignacionCliente.googleMapsCliente+'" target="_blank">Ver Mapa</b>');
	$('#emailCliente').html('<b>'+asignacionCliente.emailCliente+'</b>');
	$('#documentosAccesoCliente').html('<b>'+asignacionCliente.documentosAccesoCliente+'</b>');
	$('#reglasAccesoCliente').html('<b>'+asignacionCliente.reglasAccesoCliente+'</b>');
	$('#representanteEmpresaCliente').html('<b>'+asignacionCliente.representanteEmpresaCliente+'</b>');
	$('#representanteTrabajadorCliente').html('<b>'+asignacionCliente.representanteTrabajadorCliente+'</b>');
	$('#nombreContactoRecibeCliente').html('<b>'+asignacionCliente.nombreContactoRecibeCliente+'</b>');
	$('#pautaEntregableCliente').html('<b>'+asignacionCliente.pautaEntregableCliente+'</b>');
	$('#pautaGeneralCliente').html('<b>'+asignacionCliente.pautaGeneralCliente+'</b>');
	$('#pautaOperativaCliente').html('<b>'+asignacionCliente.pautaOperativaCliente+'</b>');
	$('#materialDidacticoCliente').html('<b>'+asignacionCliente.materialDidacticoCliente+'</b>');
	$('#informacionPaqueteriaCliente').html('<b>'+asignacionCliente.informacionPaqueteriaCliente+'</b>');
	$('#notaCliente').html('<b>'+asignacionCliente.notaCliente+'</b>');
	
	$('#imagenLogoCliente').html('<b>'+asignacionCliente.imagenLogoCliente+'</b>');
	$("#linkLogo").attr('href', '/uploads/img/'+asignacionCliente.idCliente+'/'+asignacionCliente.imagenLogoCliente)
    
	$('#archivosCliente').html('<b>'+asignacionCliente.archivosCliente+'</b>');
	$("#linkFile").attr('href', '/uploads/file/'+asignacionCliente.idCliente+'/'+asignacionCliente.archivosCliente)
    
	
	
	/*
   	 * EDICION STATUS MODAL 
   	 */
	$.sigStatus="";
	
	if($.asignaStatus === "Curso Asignado" || $.asignaStatus === "Curso Editado" ){
		$.sigStatus = "Confirmado Instructor";
		$('#modalStatus').html('<b>'+$.asignaStatus+'</b>');
		$('#consirmarStatus').html('<button type="submit" id="asignaConfirmar" class="btn btn-success pull-center btn-lg" >'+$.sigStatus+'</button>');
	}else if($.asignaStatus === "Confirmado Instructor"){
		if(validaHoy(asignacionItem.fechaAsignacion)){
			$.sigStatus = "Curso Completado";
			$('#modalStatus').html('<b>'+$.asignaStatus+'</b>');
			$('#consirmarStatus').html('<button type="submit" id="asignaConfirmar" class="btn btn-success pull-center btn-lg" >'+$.sigStatus+'</button>');
		}else{
			$('#modalStatus').html('<b>'+$.asignaStatus+'</b>');
			$('#consirmarStatus').html('<b>Debe de cumplir la fecha de evento: '+$.asignaFecha+'</b>');
		}
		
	}
	
	function validaHoy(fechaAsignacion){
		var hoy = new Date();
		var asignacion = new Date(fechaAsignacion)
		if(asignacion < hoy){
//			console.log(asignacion)
			return true;
		}else
			return false;
	}
	
	
	
	console.log($.sigStatus);
	
	
//		$('#idAsignacion').val(asignacionItem.idAsignacion);
//		$('#idAsignacionLogica').val(asignacionItem.idAsignacionLogica);
//		$('#fechaAsignacion').val($.asignaFecha);
//		$('#idClienteAsignacion').val($.asignaCliente);
//		$('#clienteAsignacion').val($.asignaClienteTexto);
//		$('#idCursoAsignacion').val($.asignaCurso);
//		$('#cursoAsignacion').val($.asignaCursoTexto);
//		$('#idInstructorAsignacion').val($.asignaInstructor);
//		$('#instructorAsignacion').val($.asignaInstructorTexto);
//		$('#horarioAsignacion').val(asignacionItem.horarioAsignacion);
//		$('#participantesAsignacion').val(asignacionItem.participantesAsignacion);
//		$('#nivelAsignacion').val(asignacionItem.nivelAsignacion);
//		$('#archivosAsignacion').val(asignacionItem.archivosAsignacion);
//		$('#archivosAsignacionTexto').val(asignacionItem.archivosAsignacionTexto);
//		$('#observacionesAsignacion').val(asignacionItem.observacionesAsignacion);
//		$('#idRegionAsignacion').val($.asignaIdRegion);
//		$('#nombreRegionAsignacion').val($.asignaNombreRegion);
//		$('#tipoCursoAsignacion').val($.asignaTipoCurso);
		$('#statusAsignacion').val($.sigStatus);
	
	
});  // fin de documento JQuery


//  JScript
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
 *  valores Submit  185755083081
 */

	
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

//   FIN  JScript