
   	
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
	$('#statusAsignacion').append("<a id='link'><h4><b>"+$.asignaStatus+"</b></h4></a>");
	
	 
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
   	/*
   	 * ASIGNAR MODAL CLIENTE asignacionCliente
   	 */
	console.log(asignacionCliente);
	$('#nombreCortoCliente').html('<b>'+asignacionCliente.nombreCortoCliente+'</b>');
	$('#regionCliente').html('<b>'+asignacionCliente.regionCliente.nombreRegion+'</b>');
	$('#domicilioCliente').html('<b>'+asignacionCliente.domicilioCliente+'</b>');
	$('#telefonoCliente').html('<b>'+asignacionCliente.telefonoCliente+'</b>');
	$('#googleMapsCliente').html('<b>'+asignacionCliente.googleMapsCliente+'</b>');
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
	$('#archivosCliente').html('<b>'+asignacionCliente.archivosCliente+'</b>');

	
	
	
	
	
	
	
	
	
	
	
	$('#asignar').click(function(){
		
		alertaEmpty ="";
		if($.asignaFecha === "" || $.asignaFecha === null){
			alertaEmpty = alertaEmpty + "<li>Campo: <b> Fecha Inválido</b></li>";
		}else{
			 
		}
		if($.asignaCliente === "" || $.asignaCliente === null){
			alertaEmpty = alertaEmpty + "<li>Campo: <b> Cliente Inválido </b></li>";
		}else{
			$('#modalCliente').html('<b>'+$.asignaClienteTexto+'</b>'+zonaCliente);
		}
		if($.asignaCurso === "" || $.asignaCurso === null){
			alertaEmpty = alertaEmpty + "<li>Campo: <b> Curso Inválido </b></li>";
		}else{
			$('#modalCurso').html('<b>'+$.asignaCursoTexto+'</b>'+" : <i><u><b>"+tipoCursoVal+"</b></u></i>");
		}
		if($.asignaInstructor === "" || $.asignaInstructor === null){
			alertaEmpty = alertaEmpty + "<li>Campo: <b> Instructor Inválido </b></li>";
		}else{
			$('#modalInstructor').html('<b>'+$.asignaInstructorTexto+'</b>');
		}
		if($.asignaHorarioInicio === "" || $.asignaHorarioInicio === null){
			alertaEmpty = alertaEmpty + "<li>Campo: <b> Horario Inicio Inválido </b></li>";
		}else{
			$('#modalHorarioInicio').html('<b>'+$.asignaHorarioInicio+'</b>'); 
		}
		if($.asignaHorarioFinal === "" || $.asignaHorarioFinal=== null){
			alertaEmpty = alertaEmpty + "<li>Campo: <b> Horario Final Inválido </b></li>";
		}else{
			$('#modalHorario').html("<b>"+ $.asignaHorarioInicio+"-"+$.asignaHorarioFinal+"</b>- Horas Efectivas: <b>"+$.horasEfectivas+"</b>"); 
		}
		if($.asignaParticipantes === "" || $.asignaParticipantes === null){
			alertaEmpty = alertaEmpty + "<li>Campo: <b> Participantes Inválido </b></li>";
		}else{
			$('#modalParticipantes').html('<b>'+$.asignaParticipantes+'</b>'); 
		}
		if($.asignaNivel === "" || $.asignaNivel === null){
			alertaEmpty = alertaEmpty + "<li>Campo: <b> Nivel Inválido </b></li>";
		}else{
//			console.log($.asignaNivel)
			$('#modalNivel').html('<b>'+$.asignaNivel+'</b>'); 
		}
		
		validaObservaciones();
		$('#modalObservaciones').html('<b>'+$.asignaObservaciones+'</b>');
		$('#modalArchivos').html('<b>'+$.asignaArchivos+'</b>');
		
		/*
		 * asignar valores al formulario 
		 */
		asignaCamposSubmit();
		
		if(alertaEmpty === "" || alertaEmpty === null){
//			console.log("Avanza al modal");
			$("#procesoVacio").remove();
			return true;
		}else{
			alertaFadeVacio()
			$("#listaProcesoVacio").empty();
			$("#listaProcesoVacio").append(alertaEmpty);
			return false;
			
		}
		
	}) // Fin de Asignar
	
	
	
});  // fin de documento JQuery


//  JScript
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
 *  valores Submit
 */

	function asignaCamposSubmit(){
		$('#idAsignacion').val(asignacionItem.idAsignacion);
		$('#idAsignacionLogica').val(asignacionItem.idAsignacionLogica);
		$('#fechaAsignacion').val($.asignaFecha2);
		$('#idClienteAsignacion').val($.asignaCliente);
		$('#clienteAsignacion').val($.asignaClienteTexto);
		$('#idCursoAsignacion').val($.asignaCurso);
		$('#cursoAsignacion').val($.asignaCursoTexto);
		$('#idInstructorAsignacion').val($.asignaInstructor);
		$('#instructorAsignacion').val($.asignaInstructorTexto);
		$('#horarioAsignacion').val($.asignaHorarioInicio +";"+ $.asignaHorarioFinal +";"+ $.asignaRecesoInicio +";"+ $.asignaRecesoFinal +";"+ $.horasEfectivas);
		$('#participantesAsignacion').val($.asignaParticipantesTexto);
		$('#nivelAsignacion').val($.asignaNivelTexto);
		$('#archivosAsignacion').val($.asignaArchivos);
		$('#archivosAsignacionTexto').val($.asignaArchivos);
		$('#observacionesAsignacion').val($.asignaObservaciones);
		$('#idRegionAsignacion').val($.asignaIdRegion);
		$('#nombreRegionAsignacion').val($.asignaNombreRegion);
		$('#tipoCursoAsignacion').val($.asignaTipoCurso);
	}
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

//   FIN  JScript