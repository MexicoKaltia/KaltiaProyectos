
   	
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
	 $.asignaArchivoParticipantes=asignacionItem.archivoParticipantes;
	 $.asignaArchivoParticipantesTexto=asignacionItem.archivoParticipantes;
	 $.asignaCostoHotel=asignacionItem.costoHotel;
	 
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
			
			var hrInicioF = horaSelFin(hr[0]);
			var hrFinalF = horaSelFin(hr[1]);
			
			var hrDesInicio = hr[2];
			var hrDesFinal = hr[3];
			var hrEfectivas = hr[4];
			if(hrDesInicio >0 ){
				return hrInicio +":"+hrInicioF+" - "+ hrFinal +":"+ hrFinalF +".  Receso:"+ hrDesInicio +":00-"+ hrDesFinal+":00.  Hrs Efectivas:"+hrEfectivas
			}else{
				return hrInicio +":"+ hrInicioF +" -  "+ hrFinal +":"+ hrFinalF +".  Hrs Efectivas : "+hrEfectivas;
			}
		}
		
		function horaSel(horarioAsignacion){
			return horarioAsignacion.slice(0,2);
		}
		function horaSelFin(horarioAsignacion){
			return horarioAsignacion.slice(2,4);
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
			case 9:
				
				zonaCliente = '<div class="zona" style="background:cyan; color:blue">'+nombreRegion+'</div>';
				break;
			}
			$.asignaIdRegion = idRegion;
			$.asignaNombreRegion = nombreRegion;
			return zonaCliente;
		}
		
	$('#idAsignacion').val(asignacionItem.idAsignacion);
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
	//--------------------------------------------------
	$('#archivoParticipantes').append("<a id='linkArchivoParticipantes'><h4><b>"+$.asignaArchivoParticipantes+"</b></h4></a>");
	$("#linkArchivoParticipantes").attr('href', '/uploads/fileAsignacion/V'+asignacionItem.idAsignacionLogica+'/'+asignacionItem.archivoParticipantes)
	
//	$('#statusAsignacion').append("<a id='link'><h4><b>"+$.asignaStatus+"</b></h4></a>");
	
	 
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
   	/*
   	 * ASIGNAR MODAL CLIENTE asignacionCliente
   	 */
	
	while(asignacionCliente.pautaEntregableCliente.includes('<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>') ){
		asignacionCliente.pautaEntregableCliente = asignacionCliente.pautaEntregableCliente.replace('<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>', '')	
	}
	
	while(asignacionCliente.pautaOperativaCliente.includes('<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>') ){
		asignacionCliente.pautaOperativaCliente = asignacionCliente.pautaOperativaCliente.replace('<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>', '')	
	}
	
	
	
	
	
	//console.log(asignacionCliente);
	$('#nombreCortoCliente').html('<b>'+asignacionCliente.nombreCortoCliente+'</b>');
	$('#regionCliente').html('<b>'+asignacionCliente.regionCliente.nombreRegion+'</b>');
	$('#nombreCompletoCliente').html('<b>'+asignacionCliente.nombreCompletoCliente+'</b>');
	$('#rfcCliente').html('<b>'+asignacionCliente.rfcCliente+'</b>');
	$('#domicilioCliente').html('<b>'+asignacionCliente.domicilioCliente+'</b>');
	$('#telefonoCliente').html('<b>'+asignacionCliente.telefonoCliente+'</b>');
	$('#googleMapsCliente').html('<b><a href="'+asignacionCliente.googleMapsCliente+'" target="_blank">Ver Mapa</b>');
	$('#emailCliente').html('<b>'+asignacionCliente.emailCliente+'</b>');
	$('#documentosAccesoCliente').html('<b>'+asignacionCliente.documentosAccesoCliente+'</b>');
	$('#reglasAccesoCliente').html('<b>'+asignacionCliente.reglasAccesoCliente+'</b>');
	$('#representanteEmpresaCliente').html('<b>'+asignacionCliente.representanteEmpresaCliente+'</b>');
	$('#representanteTrabajadorCliente').html('<b>'+asignacionCliente.representanteTrabajadorCliente+'</b>');
	$('#nombreContactoRecibeCliente').html('<b>'+asignacionCliente.nombreContactoRecibeCliente+'</b>');
	$('#pautaEntregableCliente').html(asignacionCliente.pautaEntregableCliente);
	$('#pautaGeneralCliente').html('<b>'+asignacionCliente.pautaGeneralCliente+'</b>');
	$('#pautaOperativaCliente').html(asignacionCliente.pautaOperativaCliente);
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
	

	if(perfilUsuario === "Instructor"){
		$('#edicionAsignacion0').hide();
		if($.asignaStatus === "Curso Asignado" || $.asignaStatus === "Curso Editado" ){
			$.sigStatus = "Confirmado Instructor";
			$('#consirmarStatus').html('<button type="submit" id="asignaConfirmar" class="btn btn-success pull-center btn-lg" >'+$.sigStatus+'</button>');
		}else if($.asignaStatus === "Confirmado Instructor"){
			if(validaHoy(asignacionItem.fechaAsignacion)){
				$.sigStatus = "Curso Completado";
				$('#consirmarStatus').html('<button type="submit" id="asignaConfirmar" class="btn btn-success pull-center btn-lg" >'+$.sigStatus+'</button>');
			}else{
				$('#consirmarStatus').html('<div class="alert alert-warning" role="alert" id="dataError"><b>Importante : </b><u>  Debe de cumplir la fecha de evento: '+$.asignaFecha+' </u></div>');
			}	
		}else if($.asignaStatus === "Curso Completado"){
			$.sigStatus = "Elaborar Entregable";
			$('#verificarEntregable').val(true);
			
			$('#consirmarStatus').html('<button type="submit" id="elaborarEntregable" class="btn btn-success pull-center btn-lg" >'+$.sigStatus+'</button>');
			$('#elaborarEntregable').click(function(){
//				$("#formEntregables").attr("action", "AEntregable");
			})
		}
	}
	
	if(perfilUsuario === "Operacion" || perfilUsuario === "Direccion"){
		if($.asignaStatus === "Curso Completado"){
			$.sigStatus = "Entregables Validado";//$.sigStatus = "Elaborar Entregable";
			$('#verificarEntregable').val(true);
			
			$('#consirmarStatus').html('<button type="submit" id="elaborarEntregable" class="btn btn-success pull-center btn-lg" >'+$.sigStatus+'</button>');
			$('#elaborarEntregable').click(function(){
//				$("#formEntregables").attr("action", "AEntregable");
			})
		}
		if($.asignaStatus === "Elaborar Entregable"){//if($.asignaStatus === "Elaborar Entregable"){
			$.sigStatus = "Entregables Validado";
			console.log($.sigStatus);
			$('#consirmarStatus').html('<button type="submit" id="asignaConfirmar" class="btn btn-success pull-center btn-lg" >'+$.sigStatus+'</button>');
		}
		if($.asignaStatus === "Entregables Validado"){//if($.asignaStatus === "Elaborar Entregable"){
			$.sigStatus = "Entregable Enviado";
			console.log(expedientesEntregables.entregables.length);
			console.log($.sigStatus);
			
			$('#expedienteEntregable').empty();
//			if(expedientesEntregables.entregables.length > 0){
				$('#procesoEvento').html('<li class="list-group-item list-group-item-info">Estatus Actual : <span id="modalStatus"></span></li><li class="list-group-item list-group-item-info">Capturar Guía de Entregable : \
						<select id="expedienteEntregable"  name="expedienteEntregable" class="form-control"  required onchange="expedienteEntregableFun()"></select><hr>\
						<input type="text" class="form-control"  id="guiaEntregableZ" name="guiaEntregableZ" placeholder="Capture guía entregable " value=""  maxlength="100" required/>\
						<input type="text" class="form-control"  id="idEntregableZ"  name="idEntregableZ" placeholder="Capture Id Expediente Entregable " value=""  maxlength="100"  required/></li>\
						<li class="list-group-item list-group-item-info">Avanzar Etapa : <span id="consirmarStatus"></span></li>');
				var options = "";
				for(i in expedientesEntregables.entregables){
					var entregable = expedientesEntregables.entregables[i]
					options = options + "<option value='"+entregable.idEntregable+"'>"+entregable.idEntregable+" - "+entregable.formACurso+"</option>";
				}
				$('#expedienteEntregable').append("<option value='' selected  >Selecciona Expediente Entregable</option>");
				$('#expedienteEntregable').append("<option value='0' >Nuevo Expediente Entregable</option>");
				$('#expedienteEntregable').append(options);
				
//			}else{
//				$('#procesoEvento').html('<li class="list-group-item list-group-item-info">Estatus Actual : <span id="modalStatus"></span></li><li class="list-group-item list-group-item-info">Capturar Guía de Entregable : \
//						<input type="text" class="form-control"  id="guiaEntregableZ" name="guiaEntregableZ" placeholder="Capture guía entregable " value=""  maxlength="100" required/>\
//						<input type="text" class="form-control"  id="idEntregableZ"  name="idEntregableZ" placeholder="Capture Id Expediente Entregable " value=""  maxlength="100"  required/></li>\
//						<li class="list-group-item list-group-item-info">Avanzar Etapa : <span id="consirmarStatus"></span></li>');
//			}
//			$('#guiaEntregableZ').attr("disabled", true);
//			$('#idEntregableZ').attr("disabled", true);		
			
			$('#asignaEntregable').append('<option value="'+asignacionItem.idClienteAsignacion+'" selected >'+asignacionItem.clienteAsignacion+'</option>');
			
		}
	}
	
	$('#modalStatus').html('<b>'+$.asignaStatus+'</b>');
	
	
	
	function validaHoy(fechaAsignacion){
		var hoy = new Date();
		var asignacion = new Date(fechaAsignacion)
		if(asignacion < hoy){
//			//console.log(asignacion)
			return true;
		}else
			return false;
	}
	
	
	
	//console.log($.sigStatus);
	$('#statusAsignacion').val($.sigStatus);
	$('#archivosAsignacionTexto').val(asignacionItem.archivosAsignacion);
	$('#archivosAsignacionTexto0').val(asignacionItem.archivosAsignacion);
	$('#archivoParticipantes').val($.asignaArchivoParticipantes);
	$('#archivoParticipantesTexto').val($.asignaArchivoParticipantes);
	$('#archivoParticipantes1').val($.asignaArchivoParticipantes);
	$('#archivoParticipantesTexto1').val($.asignaArchivoParticipantes);
	
	
	if(perfilUsuario !== "Administracion"){
		$('#formAdministracion').hide();
	}else{
		var elementoPicker = $datepicker.pickadate('picker');	
		$.asignaFecha = elementoPicker.get('select', 'dd/mm/yyyy');
		$.asignaFechaCalendario = $('#fechaPago').val();
	}
	
	if(perfilUsuario !== "Operacion"){
		$('#formOperacion').hide();
	}
	
	
	

	
});  // fin de documento JQuery

function expedienteEntregableFun(){
//	var option = $("#expedienteEntregable option:selected").text();
	var option = $("#expedienteEntregable").val();
console.log(option);
	var entregables = asignacionItem.guiaEntregable;
	$('#guiaEntregableZ').val("");
	$('#idEntregableZ').val("");
	if(option == ""){
		$('#guiaEntregableZ').attr("disabled", true);
		$('#idEntregableZ').attr("disabled", true);
		$('#asignaConfirmar').attr("disabled", true);
		$('#consirmarStatus').html('');
	}
	
	if(option == "0" || option != ""){
		$('#guiaEntregableZ').attr("disabled", false);
		$('#idEntregableZ').attr("disabled", false);
		$('#asignaConfirmar').attr("disabled", false);
		$('#consirmarStatus').html('<button type="submit" id="asignaConfirmar" class="btn btn-success pull-center btn-lg"  onclick="validaEntregable()">'+$.sigStatus+'</button>');
		if(option != "0"){
			var guias = entregables.split("||");
			for(var a in guias){
				var guia = guias[a];
				var kitGuia = guia.split("&&");
				var guiaPaqueteria = kitGuia[0];
				var idEntregable = kitGuia[1];
//				console.log(guiaPaqueteria);
				if(option.toString() === idEntregable.toString()){
					$('#guiaEntregableZ').val(guiaPaqueteria);
					$('#idEntregableZ').val(idEntregable);
					break;
				}
			}
		}
	}
}

function validaEntregable(){
	var guiaEntregable = $('#guiaEntregableZ').val();
	var idEntregable = $('#idEntregableZ').val();
	
	var valoresGuias = asignacionItem.guiaEntregable;
	var tmp = valoresGuias.split("||");
	var valoresGuiasC = "";
	var flag = false;
	for(a in tmp){
		var e = tmp[a].split("&&");
		if(e[1] === idEntregable){
			e[0] = guiaEntregable;
			flag = true;
		}
		if(e[0]){
			valoresGuiasC = valoresGuiasC + e[0] + "&&" + e[1] + "||";
		}
	}
	if(!flag){
		valoresGuiasC = valoresGuiasC + guiaEntregable + "&&" + idEntregable + "||";
	}
	valoresGuias = valoresGuiasC;
	
//	valoresGuias = valoresGuias + guiaEntregable + "&&" + idEntregable + "||";
	$('#guiaEntregable').val(valoresGuias);
}
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