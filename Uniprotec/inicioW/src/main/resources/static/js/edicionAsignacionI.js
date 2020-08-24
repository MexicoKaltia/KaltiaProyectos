
   	
$(document).ready(function(){
	
	console.log(asignacionItem);

	 $.asignaFecha = valoresFecha(asignacionItem.fechaAsignacion);
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
	 $.asignaArchivosTexto=asignacionItem.archivosAsignacionTexto;
	 $.asignaIdRegion=asignacionItem.idRegionAsignacion;
	 $.asignaNombreRegion=asignacionItem.nombreRegionAsignacion;
	 $.asignaTipoCurso=asignacionItem.tipoCursoAsignacion;
	 $.asignaStatus=asignacionItem.statusAsignacion;
	 $.asignaGuia=asignacionItem.guiaEntregable
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
	$("#link").attr('href', '/uploads/fileAsignacion/'+asignacionItem.idAsignacionLogica+'/'+asignacionItem.archivosAsignacion)
	//--------------------------------------------------
	$('#statusAsignacion1').html($.asignaStatus);
	$('#statusAsignacion0').val($.asignaStatus);
	
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
	$("#linkLogo").attr('href', '/uploads/img/'+asignacionCliente.rfcCliente+'/'+asignacionCliente.imagenLogoCliente)
    
	$('#archivosCliente').html('<b>'+asignacionCliente.archivosCliente+'</b>');
	$("#linkFile").attr('href', '/uploads/file/'+asignacionCliente.rfcCliente+'/'+asignacionCliente.archivosCliente)
    
	
	
	/*
   	 * EDICION STATUS MODAL 
   	 */
	$.sigStatus="";
	$('#modalStatus').html('<b>'+$.asignaStatus+'</b>');
	if(perfilUsuario === "Instructor"){
		if($.asignaStatus === "Curso Asignado" || $.asignaStatus === "Curso Editado" ){
			$.sigStatus = "Confirmado Instructor";
//			$('#modalStatus').html('<b>'+$.asignaStatus+'</b>');
			$('#consirmarStatus').html('<button type="submit" id="asignaConfirmar" class="btn btn-success pull-center btn-lg" >'+$.sigStatus+'</button>');
		}else if($.asignaStatus === "Confirmado Instructor"){
			if(validaHoy(asignacionItem.fechaAsignacion)){
				$.sigStatus = "Curso Completado";
//				$('#modalStatus').html('<b>'+$.asignaStatus+'</b>');
				$('#consirmarStatus').html('<button type="submit" id="asignaConfirmar" class="btn btn-success pull-center btn-lg" >'+$.sigStatus+'</button>');
			}else{
//				$('#modalStatus').html('<b>'+$.asignaStatus+'</b>');
				$('#consirmarStatus').html('<div class="alert alert-warning" role="alert" id="dataError"><b>Importante : </b><u>  Debe de cumplir la fecha de evento: '+$.asignaFecha+' </u></div>');
			}	
		}
	}
	
	
	if(perfilUsuario === "Operacion" || perfilUsuario === "Direccion"){
		if($.asignaStatus === "Curso Completado"){
			$.sigStatus = "Entregables Validado";
//			$('#modalStatus').html('<b>'+$.asignaStatus+'</b>');
			$('#verificarEntregable').val(true);
			$('#consirmarStatus').html('<button type="submit" id="asignaConfirmar" class="btn btn-success pull-center btn-lg" >'+$.sigStatus+'</button>');
		}else if($.asignaStatus === "Entregables Validado"){
			$.sigStatus = "Entregable Enviado";
//			$('#modalStatus').html('<b>'+$.asignaStatus+'</b>');
			$('#procesoEvento').html('<li class="list-group-item list-group-item-info">Status Actual : <span id="modalStatus"></span></li><li class="list-group-item list-group-item-info">Capturar Guía de Entregable : <input type="text" class="form-control"  id="guiaEntregable" name="guiaEntregable" placeholder="Capture guía entregable " value=""  maxlength="100" th:field="*{guiaEntregable}" required></li><li class="list-group-item list-group-item-info">Avanzar Etapa : <span id="consirmarStatus"></span></li>');
			$('#consirmarStatus').html('<button type="submit" id="asignaConfirmar" class="btn btn-success pull-center btn-lg" >'+$.sigStatus+'</button>');
			
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
	$('#statusAsignacion').val($.sigStatus);
	$('#archivosAsignacionTexto').val(asignacionItem.archivosAsignacion);
	$('#archivosAsignacionTexto0').val(asignacionItem.archivosAsignacion);
//	$('#guiaEntregable').val(asignacionItem.guiaEntregable);
//	$('#fechaPago').val(asignacionItem.fechaPago);
//	$('#numeroFactura').val(asignacionItem.numeroFactura);
//	$('#guiaEntregable0').val(asignacionItem.guiaEntregable);
//	$('#fechaPago0').val(asignacionItem.fechaPago);
//	$('#numeroFactura0').val(asignacionItem.numeroFactura);
	
	if(perfilUsuario !== "Administracion"){
		$('#edicionAsignacion0').hide();
	}else{
		var elementoPicker = $datepicker.pickadate('picker');	
		$.asignaFecha = elementoPicker.get('select', 'dd/mm/yyyy');
		$.asignaFechaCalendario = $('#fechaPago').val();
	}
	
	
});  // fin de documento JQuery


//  JScript
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
			
//		$("#diaControl").html("<i>"+DIA[f.getDay()]+" "+f.getDate()+" de "+ MESES[f.getMonth()]+ " "+ f.getFullYear()+"</i>");
		return DIA[f.getDay()]+" "+f.getDate()+" de "+ MESES[f.getMonth()]+ " "+ f.getFullYear();
}
	
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

//   FIN  JScript