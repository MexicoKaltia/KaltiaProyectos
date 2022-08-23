
   	
$(document).ready(function(){
	
//	//console.log(asignacionItem);

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
	 $.asignaArchivoParticipantes=asignacionItem.archivoParticipantes;
	 
	 
	 
	 
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
	 
	procesoInicial(0);
	
	
		
	
	//--------------------------------------------------
	var elementoPicker = $datepicker.pickadate('picker');
	elementoPicker.set('select', new Date(asignacionItem.fechaAsignacion));
	//--------------------------------------------------
	$('#asignaCliente').append('<option value="'+asignacionItem.idClienteAsignacion+'" selected >'+asignacionItem.clienteAsignacion+'</option>');
	//--------------------------------------------------
	$('#asignaCurso').append('<option value="'+asignacionItem.idCursoAsignacion+'" selected >'+asignacionItem.cursoAsignacion+'</option>');
	if(asignacionItem.tipoCursoAsignacion === 'ON LINE'){
		var tipoCurso = true;
	}else{
		var tipoCurso = false;
	}
	checkTipoCurso()
	//--------------------------------------------------
//	$('#asignaInstructor').append('<option value="'+asignacionItem.idInstructorAsignacion+'" selected >'+asignacionItem.instructorAsignacion+'</option>');
	$('#asignaInstructor').empty();
	$('#asignaInstructor').append('<option value="" selected  >Selecciona Instructor</option>');
	//--------------------------------------------------
	
	$('#asignaHorarioInicio').append('<option value="'+hr[0]+'" selected >'+$.asignaHorarioInicio+'</option>');
	$('#asignaHorarioFinal').append('<option value="'+hr[1]+'" selected >'+$.asignaHorarioFinal+'</option>');
	for(var i = 0; i < 24 ; i++){
		if((i) < 10){
			$("#asignaHorarioInicio").append('<option value="0'+(i)+'00">0'+(i)+':00</option>');
			$("#asignaHorarioInicio").append('<option value="0'+(i)+'30">0'+(i)+':30</option>');
		}else{
			$("#asignaHorarioInicio").append('<option value="'+(i)+'00">'+(i)+':00</option>');
			$("#asignaHorarioInicio").append('<option value="'+(i)+'30">'+(i)+':30</option>');
		}
		
	}
	$('#horasEfectivas').append('<option value="'+$.horasEfectivasTexto+'" selected >'+$.horasEfectivasTexto+'</option>');
	//--------------------------------------------------
	$('#asignaParticipantes').append('<option value="'+asignacionItem.participantesAsignacion+'" selected >'+asignacionItem.participantesAsignacion+'</option>');
	//--------------------------------------------------
	$('#asignaNivel').append('<option value="'+asignacionItem.nivelAsignacion+'" selected >'+asignacionItem.nivelAsignacion+'</option>');
	//--------------------------------------------------

	$('#statusAsignacionbtn').html($.asignaStatusAsignacion);
	$('#asignaObservaciones').val($.asignaObservaciones);
	//--------------------------------------------------
	$("#linkFile").attr('href', '/uploads/fileAsignacion/'+asignacionItem.idAsignacionLogica+'/'+asignacionItem.archivosAsignacion)
    $("#linkFile").html('<b>'+asignacionItem.archivosAsignacion+'</b>');
	//--------------------------------------------------
	
	
	 
	 
	$('#procesoFecha').click(function(){	
		$(".listaProceso").empty();
		procesoInicial(1, $.asignaFecha);
//		$(".listaProceso").append(procesoFecha);
	});
	$('#procesoCliente').click(function(){
		$(".listaProceso").empty();
		procesoInicial(2, $.asignaClienteTexto);
//		$(".listaProceso").append(procesoFecha);
	});
	$('#procesoCurso').click(function(){
		$(".listaProceso").empty();
		procesoInicial(3, $.asignaCursoTexto);
//		$(".listaProceso").append(procesoFecha);
//		$(".listaProceso").append(procesoCliente);
	});
	$('#procesoInstructor').click(function(){
		$(".listaProceso").empty();
		procesoInicial(4, $.asignaInstructorTexto);
//		$(".listaProceso").append(procesoFecha);
//		$(".listaProceso").append(procesoCliente);
//		$(".listaProceso").append(procesoCurso);
	})
	$('#procesoHorario').click(function(){
		$(".listaProceso").empty();
		procesoInicial(5, procesoHorarioF($.asignaHorarioInicial, $.asignaHorarioFinal ));
//		$(".listaProceso").append(procesoFecha);
//		$(".listaProceso").append(procesoCliente);
//		$(".listaProceso").append(procesoCurso);
//		$(".listaProceso").append(procesoInstructor);
	});
	$('#procesoParticipantes').click(function(){
		$(".listaProceso").empty();
		procesoInicial(6, $.asignaParticipantes);
		procesoInicial(7, $.asignaNivel);
//		$(".listaProceso").append(procesoFecha);
//		$(".listaProceso").append(procesoCliente);
//		$(".listaProceso").append(procesoCurso);
//		$(".listaProceso").append(procesoInstructor);
//		$(".listaProceso").append(procesoHorario);
		
	});
	$('#procesoObservaciones').click(function(){
		$(".listaProceso").empty();
		procesoInicial(8, $.asignaObservaciones);
		procesoInicial(9, $.asignaArchivos);
//		$(".listaProceso").append(procesoFecha);
//		$(".listaProceso").append(procesoCliente);
//		$(".listaProceso").append(procesoCurso);
//		$(".listaProceso").append(procesoInstructor);
//		$(".listaProceso").append(procesoHorario);
//		$(".listaProceso").append(procesoParticipantes);
	});
	
	for(var i = 1 ; i < 50 ; i++)
		$('#asignaParticipantes').append('<option value="'+(i)+'">'+(i)+'</option>');
	$('#asignaParticipantes').append('<option value="51">51 -70</option>');
	$('#asignaParticipantes').append('<option value="71">71 -100</option>');
	$('#asignaParticipantes').append('<option value="101">101 -150</option>');
	$('#asignaParticipantes').append('<option value="151">151 ...</option>');
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
   	/*
   	 * ASIGNAR
   	 */
	if($.asignaStatusAsignacion === "Curso Asignado" || $.asignaStatusAsignacion === "Confirmado Instructor" || $.asignaStatusAsignacion ==="Curso Editado"){
		$('#asignar').attr("disabled", false);
	}
	
	$('#asignar').click(function(){
		
		alertaEmpty ="";
		if($.asignaFecha === "" || $.asignaFecha === null){
			alertaEmpty = alertaEmpty + "<li>Campo: <b> Fecha Inválido</b></li>";
		}else{
			$('#modalFecha').html('<b>'+$.asignaFechaCalendario+'</b>'); 
		}
		if($.asignaCliente === "" || $.asignaCliente === null){
			alertaEmpty = alertaEmpty + "<li>Campo: <b> Cliente Inválido </b></li>";
		}else{
			$('#modalCliente').html('<b>'+$.asignaClienteTexto+'</b>');
		}
		if($.asignaCurso === "" || $.asignaCurso === null){
			alertaEmpty = alertaEmpty + "<li>Campo: <b> Curso Inválido, favor de confirmar </b></li>";
		}else{
			$('#modalCurso').html('<b>'+$.asignaCursoTexto+'</b>'+" : <i><u><b>"+tipoCursoVal+"</b></u></i>");
		}
		if($.asignaInstructor === "" || $.asignaInstructor === null){
			alertaEmpty = alertaEmpty + "<li>Campo: <b> Instructor Inválido , favor de confirmar</b></li>";
		}else{
			$('#modalInstructor').html('<b>'+$.asignaInstructorTexto+'</b>');
		}
		if($.asignaHorarioInicio === "" || $.asignaHorarioInicio === null){
			alertaEmpty = alertaEmpty + "<li>Campo: <b> Horario Inicio Inválido </b></li>";
		}else{
			$('#modalHorarioInicio').html('<b>'+$.asignaHorarioInicio+'</b>'); 
		}
		if($.asignaHorarioFinal === "" || $.asignaHorarioFinal=== null || $.horasEfectivas === "" || $.horasEfectivasTexto === "" ){
			alertaEmpty = alertaEmpty + "<li>Campo: <b> Confirmar Horario</b></li>";
		}else{
			$('#modalHorario').html("<b>"+ $.asignaHorarioInicioTexto+"-"+$.asignaHorarioFinalTexto+"</b> - Horas Efectivas: <b>"+$.horasEfectivasTexto+"</b>"); 
		}
		if($.asignaParticipantes === "" || $.asignaParticipantes === null){
			alertaEmpty = alertaEmpty + "<li>Campo: <b> Participantes Inválido </b></li>";
		}else{
			$('#modalParticipantes').html('<b>'+$.asignaParticipantes+'</b>'); 
		}
		if($.asignaNivel === "" || $.asignaNivel === null){
			alertaEmpty = alertaEmpty + "<li>Campo: <b> Nivel Inválido </b></li>";
		}else{
//			//console.log($.asignaNivel)
			$('#modalNivel').html('<b>'+$.asignaNivel+'</b>'); 
		}
		
		validaObservaciones();
		$('#modalObservaciones').html('<b>'+$.asignaObservaciones+'</b>');
		$('#modalArchivos').html('<b>'+$.asignaArchivos+'</b>');
		$('#modalVentas').html('<b>'+$.asignaUserCreateAsignacionTexto+'</b>');
		
		if($.asignaStatusAsignacion==="Evento Cancelado"){
			$('#modalStatus').html('<span style="background:red; color:white"><b>'+$.asignaStatusAsignacion+'</b></span>');
		}else{
			$.asignaStatusAsignacion="Curso Editado";
			$('#modalStatus').html('<span style="background:silver; color:black"><b>'+$.asignaStatusAsignacion+'</b></span>');
		}
		$('#modalArchivoParticipantes').html('<b>'+$.asignaArchivoParticipantes+'</b>');
		
		/*
		 * asignar valores al formulario 
		 */
		asignaCamposSubmit();
		
		if(alertaEmpty === "" || alertaEmpty === null){
//			//console.log("Avanza al modal");
			$("#procesoVacio").remove();
			return true;
		}else{
			alertaFadeVacio()
			$("#listaProcesoVacio").empty();
			$("#listaProcesoVacio").append(alertaEmpty);
			return false;
			
		}
		
	}) // Fin de Asignar
	
	function procesoInicial(item, valor){
		switch (idRegion){
		case 1:
			procesoFecha="<li>Edicion Fecha : <b>"+ valor +"</b></li>";
			break;
		case 2:
			procesoCliente="<li>Edicion Cliente : <b>"+ valor +"</b></li>";
			break;
		case 3:
			procesoCurso="<li>Edicion Curso : <b>"+ valor +"</b></li>";
			break;
		case 4:
			procesoInstructor="<li>Edicion Instructor : <b>"+ valor  +"</b></li>";
			break;
		case 5:
			procesoHorario=valor;
			break;
		case 6:
			procesoParticipantes="<li>Edicion Participantes : <b>"+ valor  +"</b></li>";
			break;
		case 7:
			procesoNivel="<li>Edicion Nivel : <b>"+ valor  +"</li>";
			break;
		case 8:
			procesoObservaciones="<li>Edicion Observaciones : <b>"+ valor +"</b></li>";
			break;
		case 9:
			procesoArchivo="<li>Edicion Archivo : <b>"+ valor +"</b></li>";
			break;
		default :
			procesoFecha="<li>Edicion Fecha: <b>"+ $.asignaFecha +"</b></li>";
			procesoCliente="<li>Edicion Cliente : <b>"+ $.asignaClienteTexto +"</b></li>";
			procesoCurso="<li>Edicion Curso : <b>"+ $.asignaCursoTexto +"</b></li>";
			procesoInstructor="<li>Edicion Instructor : <b>"+ $.asignaInstructorTexto  +"</b></li>";
			procesoHorario="<li>Edicion Horario : <b>"+ $.asignaHorarioInicioTexto +" - "+ $.asignaHorarioFinalTexto +"</b> - Horas Efectivas : <b>"+ $.horasEfectivasTexto +"</b></li>";
			procesoParticipantes="<li>Edicion Participantes : <b>"+ $.asignaParticipantesTexto  +"</b></li>";
			procesoNivel="<li>Edicion Nivel : <b>"+ $.asignaNivelTexto  +"</li>";
			procesoObservaciones="<li>Edicion Observaciones : <b>"+ $.asignaObservaciones +"</b></li>";
			procesoArchivo="<li>Edicion Archivo : <b>"+ $.asignaArchivos +"</b></li>";
		break;
		}
		
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
	
	}

	function procesoHorarioF(asignaHorarioInicial, asignaHorarioFinal ){
		return "<li>Edicion Horario : <b>"+ asignaHorarioInicial+" - "+asignaHorarioFinal+"</b> - Horas Efectivas : <b>"+ $.horasEfectivasTexto +"</b></li>";
	}
	
	function horaSel(horarioAsignacion){
//		return horarioAsignacion.slice(0,2);
		return horarioAsignacion.substring(0, 2) +":"+ horarioAsignacion.substring(2);
	}
	
	var clientesVendedor = new Array();
//	//console.log(idVendedor+":"+perfilVendedor+":"+operacionId);
	if(perfilUsuario ==="Vendedor"){
		$('#asignaCliente').empty();
		clientesVendedor = vendedorCliente(operacionId);
		$('#asignaCliente').append("<option value='' selected  >Selecciona Cliente</option>");
		for(i in clientesVendedor){
			$('#asignaCliente').append("<option value='"+clientesVendedor[i].idCliente+"'>"+clientesVendedor[i].nombreCortoCliente+"</option>");
		}
		
	}
	
	
	function modal(){
		$('#myModalProcess').modal();
		$("#asignaConfirmar").hide();
		return true;
	}
	$("#asignaConfirmar").click(function(){
		modal();
		return true;
	})
	
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
		$('#archivoParticipantes').val($.asignaArchivoParticipantes);
		$('#archivoParticipantesTexto').val($.asignaArchivoParticipantes);
		
		$('#fechaPago').val(asignacionItem.fechaPago);
		$('#guiaEntregable').val(asignacionItem.guiaEntregable);
		$('#numeroFactura').val(asignacionItem.numeroFactura);
		$('#verificarEntregable').val(asignacionItem.verificarEntregable);
		$('#costoHotel').val(asignacionItem.costoHotel);
		
	}
	
	const zonabase = {"11":true,"12":true,"13":true,"14":true,"15":true,"16":true,"17":false,"18":false,"19":false,"21":true,"22":true,"23":true,"24":true,"25":false,"26":true,"27":true,"28":false,"29":false,"31":true,"32":true,"33":true,"34":true,"35":false,"36":true,"37":false,"38":false,"39":false,"41":true,"42":true,"43":true,"44":true,"45":false,"46":false,"47":false,"48":false,"49":false,"51":true,"52":false,"53":false,"54":false,"55":true,"56":false,"57":false,"58":false,"59":false,"61":true,"62":true,"63":true,"64":false,"65":false,"66":true,"67":true,"68":false,"69":false,"71":false,"72":true,"73":false,"74":false,"75":false,"76":true,"77":true,"78":false,"79":false,"81":false,"82":false,"83":false,"84":false,"85":false,"86":false,"87":false,"88":false,"89":false,"91":false,"92":false,"93":false,"94":false,"95":false,"96":false,"97":false,"98":false,"99":true};
	var alerta;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	function limpiaCurso(){
		$('#asignaCurso').val("");
//		$('#asignaCurso').attr("disabled", true);
		$('#asignaInstructor').val("");
		$('#asignaCurso option[value=""]');
		$("#asignaCurso").trigger("chosen:updated");
//		$.asignaCursoTexto = null;
//		$.asignaInstructorTexto = null;
		$.asignaCurso = null;
//		$.asignaInstructor = null;
	}
/*
 * ValidaFECHA
 */
	
	
	function validaFecha(inputAsignaFecha){
		var elementoPicker = $datepicker.pickadate('picker');	
		$.asignaFecha = elementoPicker.get('select', 'dd/mm/yyyy');
		$.asignaFecha2 = elementoPicker.get('select', 'mm/dd/yyyy');
//   	    //console.log($.asignaFecha);
   	    $.asignaFechaCalendario = $('#asignaFecha').val();
//		//console.log("asignaFecha:"+ $.asignaFechaCalendario);
   		$('#alertaFecha').remove();
   		
   		limpiaCurso()
   		
   		if($.asignaFechaCalendario === null || $.asignaFechaCalendario === ""){
   			alerta="<div class='alert alert-danger' id='alertaFecha' role='alert'>Seleccione Fecha</div>";
			alertaFade(alerta);
			$('#btnAsignaFecha').attr("disabled", true);
   		}else{
   			$('#btnAsignaFecha').attr("disabled", false);
   		}
   		procesoFecha="<li>Edicion Fecha B: <b>"+ $.asignaFechaCalendario +"</b></li>";
   	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * ValidaCLIENTE
	 */
	function validaCliente(){
		limpiaCurso()
		$.asignaCliente = $('#asignaCliente').val()
		$.asignaClienteTexto = $("#asignaCliente option:selected").text();
//		//console.log("asignaCliente:"+ $.asignaCliente);
		$('#alertaFecha').remove();
		$('#alertaCliente').remove();
		
		if($.asignaCliente === null || $.asignaCliente === ""){
   			alerta="<div class='alert alert-danger' id='alertaCliente' role='alert'>Seleccione Cliente</div>";
			alertaFade(alerta);
			$('#btnAsignaCliente').attr("disabled", true);
   		}else{
   			$('#btnAsignaCliente').attr("disabled", false);
   		}
		
		var zonaCliente = colorZonaCliente($.asignaCliente);
//		zonaCliente = '<div class="zona" style="background-color:yellow">1</div>';
		procesoCliente="<li>Edicion Cliente : <b>"+ $.asignaClienteTexto +"</b>"+zonaCliente+"</li>";
	}
	
	function vendedorCliente(idV){
//		//console.log("idVendedorFirmado:"+idV);
		var idVendedorCliente;
		var clientes= new Array();
		for(i in asignacionClientes){
			idVendedorCliente = asignacionClientes[i].vendedorCliente.idVendedor;
//			//console.log(idVendedorCliente+":"+asignacionClientes[i].nombreCortoCliente);
			if((idVendedorCliente *1) === (idV*1))
				clientes.push(asignacionClientes[i]);
		}
//		//console.log(clientes);
		return clientes;
	}
	
	var zonaCliente;
	var idRegion;
	var nombreRegion;
	function colorZonaCliente(cliente){
		cliente = (cliente * 1);
		var idRegion;
		var nombreRegion;
		
		var idCliente
		for(i in asignacionClientes){
//			//console.log(asignacionClientes[i]);
			idCliente = (asignacionClientes[i].idCliente * 1);
			if(idCliente === cliente){
				idRegion = (asignacionClientes[i].regionCliente.idRegion *1 );
				nombreRegion = asignacionClientes[i].regionCliente.nombreRegion;
			}
		}
			
//		//console.log(idRegion+":"+nombreRegion);
		
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
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * ValidaCURSO
	 */
	var tipoCurso ;
	var tipoCursoVal = "";
	var arrayInstructores = new Array();
	var instructoresDiaSelect = new Array();
	var instructoresDmin1 = new Array();
	var instructoresDmas1 = new Array();
	var instructoresDiaAyer = new Array();
	var instructoresDiaMan = new Array();
	
	function checkTipoCurso(){
		
		if(tipoCurso){
			$('#tipoCurso').html("ON LINE");
			$('#tipoCurso').removeClass("btn-alternate");
			$('#tipoCurso').addClass("btn-warning");
			tipoCurso = false;
			tipoCursoVal = "ON LINE";
		}else{
			$('#tipoCurso').html("PRESENCIAL");
			$('#tipoCurso').removeClass("btn-warning");
			$('#tipoCurso').addClass("btn-alternate");
			tipoCurso = true;
			tipoCursoVal = "PRESENCIAL";
		}
		$('#asignaCurso').attr("disabled", false);
	}
	
	
	
	function validaCurso(){
		/*
		 * Validacion ValorCampo
		 */
	   	 arrayInstructores.length = 0; 
		 instructoresDiaSelect.length = 0;
		 instructoresDmin1.length = 0;
		 instructoresDmas1.length = 0;
		 instructoresDiaAyer.length = 0;
		 instructoresDiaMan.length = 0;
		 $.asignaCurso = $('#asignaCurso').val();
		
		$('#alertaFecha').remove();
		$('#alertaCliente').remove();
		$('#alertaCurso').remove();
		
		if($.asignaCurso === null || $.asignaCurso === ""){
   			alerta="<div class='alert alert-danger' id='alertaCurso' role='alert'>Seleccione Curso</div>";
			alertaFade(alerta);
			$('#btnAsignaCurso').attr("disabled", true);
   		}else{
   			$('#btnAsignaCurso').attr("disabled", false);
   		}
		
		/* 
		 * No VALIDAR Esquemas de movilidad para Perfil Operacion y Direccion
		 */
		if(perfilUsuario === "Operacion" || perfilUsuario === "Direccion"){
			
			var valorCurso = $.asignaCurso * 1;
			asignacionInstructores = asignacionInstructoresOperacion;
			for (i in asignacionInstructores){				
				var arrayCursosInstructor = asignacionInstructores[i].cursosInstructor.replace('"','').replace('"','').replace(' ','').split(',');
				var instructor = asignacionInstructores[i];
				for( e in arrayCursosInstructor){
					arrayCursosInstructor[e] = arrayCursosInstructor[e].replace('[','');
					arrayCursosInstructor[e] = arrayCursosInstructor[e].replace(']','');
					arrayCursosInstructor[e] = arrayCursosInstructor[e].replace(' ','') * 1;
					if(arrayCursosInstructor[e] === valorCurso){
						$('#asignaInstructor').append('<option value="'+instructor.idInstructor+'">'+instructor.nombreInstructor+'</option>');
					}
				}
			}
		}else{
			/*
			 * Filtra Instructores por Curso
			 */
			var valorCurso = $.asignaCurso * 1;
			for (i in asignacionInstructores){				
				var arrayCursosInstructor = asignacionInstructores[i].cursosInstructor.replace('"','').replace('"','').replace(' ','').split(',');
				for( e in arrayCursosInstructor){
					arrayCursosInstructor[e] = arrayCursosInstructor[e].replace('[','');
					arrayCursosInstructor[e] = arrayCursosInstructor[e].replace(']','');
					arrayCursosInstructor[e] = arrayCursosInstructor[e].replace(' ','') * 1;
					if(arrayCursosInstructor[e] === valorCurso){
						arrayInstructores.push(asignacionInstructores[i])
					}
				}
			}
//			//console.log(arrayInstructores);
			/*
			 * Valida dias de Ausencia
			 */
			var instructorDiaAusencia;
			var instructoresDiaAusencia = new Array();;
			for(a in arrayInstructores){
				instructorDiaAusencia = arrayInstructores[a]; 
				if(validaDiaAusencia(instructorDiaAusencia)){
					instructoresDiaAusencia.push(instructorDiaAusencia);
				}
			}
			arrayInstructores = instructoresDiaAusencia ;
//			//console.log(arrayInstructores);
			
			var regionInstructor;
			var regionCliente;
			var instructor;
			var idInstructor;
	
			$('#asignaInstructor').empty();
			$('#asignaInstructor').append('<option value="" selected  >Selecciona Instructor</option>');
			if(tipoCurso){

				/*
				 * Obtener ZonaCliente 
				 */
				if($('#asignaCliente').val() === null || $('#asignaCliente').val() === ""){
		   			alerta="<div class='alert alert-danger' id='alertaCliente' role='alert'>Seleccione Cliente</div>";
					alertaFade(alerta);
					$('#btnAsignaCurso').attr("disabled", true);
				}else{
					var jsonCliente;
					for (i in asignacionClientes){
						if(asignacionClientes[i].idCliente === ($('#asignaCliente').val() * 1)){
							jsonCliente = asignacionClientes[i];
						}
					}
					regionCliente = jsonCliente.regionCliente.idRegion;
//					//console.log(jsonCliente);
				}

				/*
				 * Consultar D-1 y D+1 Instructores
				 */
				
				/*
				 * validar dia seleccion
				 */
				for(i in arrayInstructores){
					 instructor = arrayInstructores[i];
					 idInstructor = instructor.idInstructor;
					 nombreInstructor = instructor.nombreInstructor
					if(validaDiaSelect(idInstructor)){
						instructoresDiaSelect.push(instructor);
					}
				}
//					 //console.log(instructoresDiaSelect);
				/*
				 * libre ayer
				 */
				for(e in instructoresDiaSelect){
					instructor = instructoresDiaSelect[e];
					idInstructor = instructor.idInstructor;
					 nombreInstructor = instructor.nombreInstructor
					if(!validaDiaAyer(idInstructor)){
						idRegionOrigen = getRegionOrigen(idInstructor);
						if(validaZonaBase(idRegionOrigen, regionCliente)){
							instructoresDiaAyer.push(instructor);
						}

					}else{
						if(validaDmin1(regionCliente, idInstructor)){
							instructoresDmin1.push(instructor);
							instructoresDiaAyer.push(instructor);
						}
					}
				}
//				//console.log(instructoresDiaAyer);
				/*
				 * libre mañana 
				 */
				for(a in instructoresDiaAyer){
					instructor = instructoresDiaAyer[a];
					 idInstructor = instructor.idInstructor
					 nombreInstructor = instructor.nombreInstructor
					if(!validaDiaMan(idInstructor)){					
						instructoresDiaMan.push(instructor);
					}else{
						if(validaDmas1(regionCliente, idInstructor)){
							instructoresDiaMan.push(instructor);
						}
					}
				}
//				//console.log(instructoresDiaMan);
				for(i in instructoresDiaMan){
					instructor = instructoresDiaMan[i];
					$('#asignaInstructor').append('<option value="'+instructor.idInstructor+'">'+instructor.nombreInstructor+'</option>');
				}
				
			}else{
				//validar dia seleccion
				for(i in arrayInstructores){
					 instructor = arrayInstructores[i];
					 idInstructor = instructor.idInstructor
					 nombreInstructor = instructor.nombreInstructor
					if(validaDiaSelect(idInstructor)){
						$('#asignaInstructor').append('<option value="'+idInstructor+'">'+nombreInstructor+'</option>');
					}
				}
			}
			validaAtributosPrimarios()
		}	
		$.asignaCursoTexto = $("#asignaCurso option:selected").text();
		procesoCurso="<li>Prospecto Curso : <b>"+ $.asignaCursoTexto +" : <i><u>"+tipoCursoVal+"</u></i></b></li>";
		$.asignaTipoCurso = tipoCursoVal;
//		$('#asignaInstructor').append('<option value="'+$.asignaInstructor+'" selected>'+$.asignaInstructorTexto+'</option>');
	}  // fin metodo validaCurso
	
	function validaAtributosPrimarios(){
//		//console.log(asignacionItem.instructorAsignacion);
//		//console.log($.asignaFecha);
//		//console.log($.asignaCliente);
//		//console.log($.asignaCurso);
		
		var fechaAsignada = ordenaFecha(asignacionItem.fechaAsignacion);
//		//console.log(fechaAsignada);
		
		if(fechaAsignada.toString() === $.asignaFecha.toString() && 
				asignacionItem.idClienteAsignacion.toString() === $.asignaCliente.toString() && 
				asignacionItem.idCursoAsignacion.toString() === $.asignaCurso.toString() && 
				asignacionItem.tipoCursoAsignacion.toString() === $.asignaTipoCurso.toString() ){
			
			$('#asignaInstructor').append('<option value="'+asignacionItem.idInstructorAsignacion+'" selected >'+asignacionItem.instructorAsignacion+'</option>');
			
		}
		
	}
	
	function ordenaFecha(fecha){
		var fechaArray = new Array();
		fechaArray = fecha.split("/");
		return fecha[1]+"/"+fecha[0]+"/"+fecha[2];
	}
	
	function validaDiaAusencia(instructor){
		
		var fechaDisponible = true;
		var fechaAusente;
		var fechaSelect = new Date($.asignaFecha2);
		var fechasAusente = new Array();
//		//console.log(instructor);
		if(instructor.listFechas){
			fechasAusente = instructor.listFechas.toString().split(";");
//			fechasAusente = stringToList(instructor.listFechas);
//			//console.log(fechasAusente)
			for(e in fechasAusente){
//				//console.log(fechasAusente[e]);
				fechaAusente = new Date(fechasAusente[e]);
//				//console.log(fechaAusente);
//				//console.log(fechaSelect);
				if(fechaAusente.toString() === fechaSelect.toString()){
					fechaDisponible = false;
					break;
				}
			}			
		}
		return fechaDisponible;
	}
	
	
	function stringToList(cadena){
		return cadena.split(";");
	}
	
	function stringToList(cadena, separador){
		return cadena.split(separador);
	}
	
	
	function validaDiaAyer(idInstructor){
		var asignacion;
		var asignacionFecha;
		var asignacionInstructor;
		var idRegionOrigen;
		
		var asignaFechaMin1 = $.asignaFecha.split("/");
		var dayer = new Date(asignaFechaMin1[2] +"/"+ asignaFechaMin1[1] +"/"+ asignaFechaMin1[0]);
		dayer.setDate(dayer.getDate() - 1);
		var dia = dayer.getDate();
		var mes = (dayer.getMonth()+1);
		var anio =dayer.getFullYear();
		if(dia<10)
			dia = "0"+dia.toString();
		if(mes<10)
			mes = "0"+mes.toString();
		var dayerTexto = mes +"/"+ dia +"/"+ anio ;
		for(i in asignacionAsignaciones){
			asignacion = asignacionAsignaciones[i];
			asignacionFecha = asignacion.fechaAsignacion;
			asignacionInstructor = asignacion.idInstructorAsignacion;
			if(asignacionFecha === dayerTexto && (asignacionInstructor === idInstructor)){
				if(asignacion.tipoCursoAsignacion === "PRESENCIAL"){
					return true;
				}
			}
		}
		return false;
	}
	
	function validaDiaMan(idInstructor){
		var asignacion;
		var asignacionFecha;
		var asignacionInstructor;
		var idRegionAsignado;
		
		var asignaFechaMin1 = $.asignaFecha.split("/");
		var dman = new Date(asignaFechaMin1[2] +"/"+ asignaFechaMin1[1] +"/"+ asignaFechaMin1[0]);
		dman.setDate(dman.getDate() + 1);
		var dia = dman.getDate();
		var mes = (dman.getMonth()+1);
		var anio =dman.getFullYear();
		if(dia<10)
			dia = "0"+dia.toString();
		if(mes<10)
			mes = "0"+mes.toString();
		var dManTexto = mes +"/"+ dia +"/"+ anio ;
		
		for(i in asignacionAsignaciones){
			asignacion = asignacionAsignaciones[i];
			asignacionFecha = asignacion.fechaAsignacion;
			asignacionInstructor = asignacion.idInstructorAsignacion;
//			//console.log(dManTexto+":"+asignacionFecha);
			if((asignacionFecha.toString() === dManTexto.toString()) && (asignacionInstructor.toString() === idInstructor.toString())){
				if(asignacion.tipoCursoAsignacion === "PRESENCIAL"){
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	function validaDiaSelect(idInstructor){
		var fechaDisponible = true;
		var asignacion;
		var asigna;
		var dia;
		for(i in asignacionAsignaciones){
			asignacion = asignacionAsignaciones[i];
			asigna = asignacion.fechaAsignacion.toString().split("/");
			dia = asigna[1]+"/"+asigna[0]+"/"+asigna[2];
			if((dia === $.asignaFecha.toString()) && (asignacion.idInstructorAsignacion.toString() === idInstructor.toString())){
				fechaDisponible = false;
				break;
			}
		}
		return fechaDisponible;
	}
	
	function validaDmin1(regionCliente, idInstructor){
//		var flagDiaAnterior;
		var asignacion;
		var asignacionFecha;
		var asignacionInstructor;
		var idRegionAsignado;
		var asignacionesDmin1 = new Array();
		var asignaFechaMin1 = $.asignaFecha.split("/");
		var dmin1 = new Date(asignaFechaMin1[2] +"/"+ asignaFechaMin1[1] +"/"+ asignaFechaMin1[0]);
		dmin1.setDate(dmin1.getDate() - 1);
		var dia = dmin1.getDate();
		var mes = (dmin1.getMonth()+1);
		var anio =dmin1.getFullYear();
		if(dia<10)
			dia = "0"+dia.toString();
		if(mes<10)
			mes = "0"+mes.toString();
		var dmin1Texto = mes +"/"+ dia +"/"+ anio ;
//		//console.log(dmin1Texto);
//		//console.log(asignacionAsignaciones);
		for(i in asignacionAsignaciones){
			asignacion = asignacionAsignaciones[i];
//			//console.log(asignacion.idAsignacion);
			asignacionFecha = asignacion.fechaAsignacion;
			asignacionInstructor = asignacion.idInstructorAsignacion;
			if(asignacionFecha === dmin1Texto && (asignacionInstructor === idInstructor)){
				idRegionAsignado = getRegionAsignado(asignacion.idClienteAsignacion);
//				//console.log(idRegionAsignado);
				return validaZonaBase(regionCliente, idRegionAsignado);
			}
		}
		return true;
	}
	
	function validaDmas1(regionCliente, idInstructor){
//		//console.log("regionCliente:"+regionCliente);
//		//console.log("idInstructor:"+idInstructor);
//		var flagDiaAnterior;
		var asignacion;
		var asignacionFecha;
		var asignacionInstructor;
		var idRegionAsignado;
		var asignacionesDmin1 = new Array();
		var asignaFechaMin1 = $.asignaFecha.split("/");
		var dmas1 = new Date(asignaFechaMin1[2] +"/"+ asignaFechaMin1[1] +"/"+ asignaFechaMin1[0]);
		dmas1.setDate(dmas1.getDate() + 1);
		var dia = dmas1.getDate();
		var mes = (dmas1.getMonth()+1);
		var anio =dmas1.getFullYear();
		if(dia<10)
			dia = "0"+dia.toString();
		if(mes<10)
			mes = "0"+mes.toString();
		var dmas1Texto = mes +"/"+ dia +"/"+ anio ;
//		//console.log("dmas1Texto:"+dmas1Texto);
//		//console.log(asignacionAsignaciones);
		for(i in asignacionAsignaciones){
			asignacion = asignacionAsignaciones[i];
			asignacionFecha = asignacion.fechaAsignacion;
			asignacionInstructor = asignacion.idInstructorAsignacion;
//			//console.log(asignacionFecha);
			if((asignacionFecha === dmas1Texto) && (asignacionInstructor === idInstructor)){
				idRegionAsignado = getRegionAsignado(asignacion.idClienteAsignacion);
//				//console.log(idRegionAsignado);
				return validaZonaBase(regionCliente, idRegionAsignado);
			}
		}
		return true;
	}
	
	function validaZonaBase(regionCliente, regionInstructor){
		var claveZB = regionCliente.toString() + regionInstructor.toString();
//		//console.log(claveZB);
//		//console.log(zonabase[claveZB]);
		return zonabase[claveZB];
	}
	
	function getRegionAsignado(idClienteAsignacion){
		for(i in asignacionClientes){
			if(asignacionClientes[i].idCliente === idClienteAsignacion)
				return asignacionClientes[i].regionCliente.idRegion;
		}
		
	}
	
	function getRegionOrigen(idInstructor){
		var instructor;
		for(i in instructoresDiaSelect){
			instructor = instructoresDiaSelect[i];
			if(idInstructor === instructor.idInstructor){
				return instructor.regionInstructor.idRegion.toString();
			}
		}
			
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * ValidaINSTRUCTOR
	 */
	function validaInstructor(){
		
		
		$.asignaInstructor = $('#asignaInstructor').val()
//		//console.log("asignaInstructor:"+ $.asignaInstructor);
		
		$('#alertaFecha').remove();
		$('#alertaCliente').remove();
		$('#alertaCurso').remove();
		$('#alertaInstructor').remove();
		
		if($.asignaInstructor === null || $.asignaInstructor === ""){
   			alerta="<div class='alert alert-danger' id='alertaInstructor' role='alert'>Seleccione Instructor</div>";
			alertaFade(alerta);
			$('#btnAsignaInstructor').attr("disabled", true);
   		}else{
   			$('#btnAsignaInstructor').attr("disabled", false);
   		}
		$.asignaInstructorTexto = $("#asignaInstructor option:selected").text();
		procesoInstructor="<li>Prospecto Instructor : <b>"+ $.asignaInstructorTexto +"</b></li>";
	}
	
	

	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * ValidaHORARIO
	 */
	 $.asignaHorarioInicio;
	 $.asignaHorarioFinal;
	 $.asignaRecesoInicio;
	 $.asignaRecesoFinal;
	 $.horasEfectivas;
	 $.asignaHorarioInicioTexto;
	 $.asignaHorarioFinalTexto;
	 $.horasEfectivasTexto;
	
	function validaHorarioInicio(){
		$.asignaHorarioInicio = $('#asignaHorarioInicio').val();
		
		$('#alertaFecha').remove();
		$('#alertaCliente').remove();
		$('#alertaCurso').remove();
		$('#alertaInstructor').remove();
		$('#alertaHorarioInicio').remove();
		
		if($.asignaHorarioInicio === null || $.asignaHorarioInicio === ""){
   			alerta="<div class='alert alert-danger' id='alertaHorarioInicio' role='alert'>Seleccione Horario</div>";
			alertaFade(alerta);
			$('#btnAsignaHorario').attr("disabled", true);
//			//console.log("Horario INVALIDO");
   		}else{
//   			$("#asignaHorarioFinal").empty();
//   			$("#asignaRecesoInicio").empty();
   			reinicioHorario();
   			
   			$("#asignaHorarioFinal").append('<option value="">Horario Final</option>');
   			if($.asignaHorarioInicio.substring($.asignaHorarioInicio.length-2,$.asignaHorarioInicio.length) === "00"){
   				for(var i = ($.asignaHorarioInicio/100); i < 24 ; i++){
   					if((i*1) < 9){
   						$("#asignaHorarioFinal").append('<option value="0'+(i)+'30">0'+(i)+':30</option>');
   						$("#asignaHorarioFinal").append('<option value="0'+(i+1)+'00">0'+(i+1)+':00</option>');
   					}else{
   						$("#asignaHorarioFinal").append('<option value="'+(i)+'30">'+(i)+':30</option>');
   						$("#asignaHorarioFinal").append('<option value="'+(i+1)+'00">'+(i+1)+':00</option>');
   					}
   				}
   			}else{
   				for(var i = ($.asignaHorarioInicio/100); i < 23 ; i++){
   					o = i - 0.3;
   					if((i*1) < 9){
   						$("#asignaHorarioFinal").append('<option value="0'+(o+1)+'00">0'+(o+1)+':00</option>');
   	   					$("#asignaHorarioFinal").append('<option value="0'+(o+1)+'30">0'+(o+1)+':30</option>');
   					}else{
   						$("#asignaHorarioFinal").append('<option value="'+(o+1)+'00">'+(o+1)+':00</option>');
   	   					$("#asignaHorarioFinal").append('<option value="'+(o+1)+'30">'+(o+1)+':30</option>');
   					}
   				}
   				$("#asignaHorarioFinal").append('<option value="2400">24:00</option>');
	   		}
   			$('#asignaHorarioFinal').attr("disabled", false);
		}
//		//console.log("Horario Inicio:"+$.asignaHorarioInicio);
	}
	
	
	
	
	function validaHorarioFinal(){
		$.asignaHorarioFinal = $('#asignaHorarioFinal').val();
		$.horasEfectivas = "";
		procesoHorario="";
		$('#confirmarHorario').attr('disabled', true);
		$('#btnAsignaHorario').attr("disabled", true);
		if($.asignaHorarioFinal > 0){
			horasEfectivas(sumaHorasReceso());
//			$.asignaHorarioInicioTexto = $("#asignaHorarioInicio option:selected").text();
//			procesoHorarioInicio="<li>Prospecto HorarioInicio : <b>"+ $.asignaHorarioInicioTexto +"</b></li>";
//			//console.log("Horario Final:"+$.asignaHorarioFinal)
		}else{
			reinicioHorario()
		}
	}
	
	function sumaHorasReceso(){
		var asignaHorarioInicio	= ($.asignaHorarioInicio * 1);
		var asignaHorarioFinal	= ($.asignaHorarioFinal * 1);	
		var efectivas = (asignaHorarioFinal - asignaHorarioInicio);
		var hi = $.asignaHorarioInicio;
		var e = (efectivas)/100;
		if(Number.isInteger(e)){
			if(e<10){
				e= "0"+e;
			}
			$.horasEfectivas = e + ":00"; 
			
			return $.horasEfectivas;
		}else{
			if(e<10){
				e= "0"+e;
			}
			e = e+"";
			e = e.split(".");
			$.horasEfectivas = e[0] + ":30"; 
//			procesoHorario="<li>Prospecto Horario: <b>"+ $.asignaHorarioInicio.substring(0,2)+":"+$.asignaHorarioInicio.substring(2)+" - "+$.asignaHorarioFinal.substring(0,2)+":"+$.asignaHorarioFinal.substring(2)+"</b> - Horas Efectivas: <b>"+$.horasEfectivas+"</b></li>";
			return $.horasEfectivas;
		}
		
	}
	
	function horasEfectivas(horaEfectiva){
		$.horasEfectivas=""
		$("#horasEfectivas").attr('disabled', false);
		$("#horasEfectivas").empty();
		$("#horasEfectivas").append('<option value="" selected  >Selecciona Horas Efectivas</option>');
//		$("#horasEfectivas").append('<option value="'+horaEfectiva.substring(0,2)+":"+horaEfectiva.substring(3,2)+'" >'+horaEfectiva+'</option>');
		var hrEf = horaEfectiva.split(":");
		var inicioEfe = 0;
//		if(inicioEfe < ((hrEf[0]*1)-)){
//			inicioEfe = (hrEf[0]*1)-4;
//		}
		if(hrEf[1] === "00"){
				for(var i = inicioEfe; i <((hrEf[0]*1)+ 0) ; i++){
						$("#horasEfectivas").append('<option value="'+(i)+'30">'+(i)+':30</option>');
						$("#horasEfectivas").append('<option value="'+(i+1)+'00">'+(i+1)+':00</option>');
					}
			}else{
				for(var i = inicioEfe; i <((hrEf[0]*1)+ 0) ; i++){
					o = i - 0.3;
//					//console.log(o);
					$("#horasEfectivas").append('<option value="'+(i+1)+'00">'+(i+1)+':00</option>');
					$("#horasEfectivas").append('<option value="'+(i+1)+'30">'+(i+1)+':30</option>');
				}
   		}
		
	}
	
	$("#horasEfectivas").change(function(){
		var horasE = $("#horasEfectivas").val(); 
//		//console.log(horasE);
		if( horasE > 0){
			$('#confirmarHorario').attr('disabled', false);
		}else{
			reinicioHorario()
		}
	});
	
function reinicioHorario(){
		
		$.horasEfectivas = "";
		$.asignaHorarioFinal ="";
		procesoHorario="";
		$("#asignaHorarioFinal").empty();
		$("#asignaRecesoInicio").empty();
		$("#horasEfectivas").attr('disabled', true);
		$("#horasEfectivas").empty();
		$('#confirmarHorario').attr('disabled', true);
		$('#btnAsignaHorario').attr("disabled", true);
	}
	
	function horSin(hora){
		return hora.replace(":", "");
	}
	
	$('#confirmarHorario').click(function(){
		
		$.horasEfectivas = $("#horasEfectivas").val();
		$("#horasEfectivas").attr("disabled", true);
		$('#btnAsignaHorario').attr("disabled", false);
		$.asignaHorarioInicioTexto = $("#asignaHorarioInicio option:selected").text();
		 $.asignaHorarioFinalTexto = $("#asignaHorarioFinal option:selected").text();
		 $.horasEfectivasTexto = $("#horasEfectivas option:selected").text();
		procesoHorario="<li>Prospecto Horario: <b>"+ $.asignaHorarioInicioTexto +" - "+ $.asignaHorarioFinalTexto +"</b> - Horas Efectivas: <b>"+ $.horasEfectivasTexto +"</b></li>";
		
	})
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * ValidaParticipantes
	 */
	function validaParticipantes(){
		
		$.asignaParticipantes = $('#asignaParticipantes').val();
//		//console.log("asignaParticipantes:"+ $.asignaParticipantes);
		
		$('#alertaFecha').remove();
		$('#alertaCliente').remove();
		$('#alertaCurso').remove();
		$('#alertaInstructor').remove();
		$('#alertaHorarios').remove();
		$('#alertaParticipantes').remove();
		
		if($.asignaParticipantes === null || $.asignaParticipantes === ""){
   			alerta="<div class='alert alert-danger' id='alertaParticipantes' role='alert'>Seleccione Participantes</div>";
			alertaFade(alerta);
			$('#asignaNivel').attr("disabled", true);
			$('#btnAsignaParticipantes').attr("disabled", true);
   		}else{
   			$('#asignaNivel').attr("disabled", false);
   		}
		
	}
	function validaNivel(){
		$.asignaNivel = $('#asignaNivel').val();
//		//console.log("asignaNivel:"+ $.asignaNivel);
		$('#alertaFecha').remove();
		$('#alertaCliente').remove();
		$('#alertaCurso').remove();
		$('#alertaInstructor').remove();
		$('#alertaHorarios').remove();
		$('#alertaParticipantes').remove();
		$('#alertaNivel').remove();
		
		if(($.asignaNivel === null || $.asignaNivel === "") || ($.asignaParticipantes === null || $.asignaParticipantes === "")){
   			alerta="<div class='alert alert-danger' id='alertaNivel' role='alert'>Seleccione Nivel</div>";
			alertaFade(alerta);
			$('#btnAsignaParticipantes').attr("disabled", true);
   		}else{
   			$('#btnAsignaParticipantes').attr("disabled", false);
   		}
		$.asignaParticipantesTexto = $("#asignaParticipantes option:selected").text();
		$.asignaNivelTexto = $("#asignaNivel option:selected").text();
		$.asignaParticipantes = $('#asignaParticipantes').val();
		procesoParticipantes="<li>Edicion Participantes: <b>"+ $.asignaParticipantesTexto +"</b></li><li>Edicion Nivel: <b>"+ $.asignaNivelTexto +"</b></li>";

		
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * ValidaObservaciones
	 */
	function checkStatusAsignacion(){
//		//console.log($.asignaStatusAsignacion);
		if($.asignaStatusAsignacion === "Curso Asignado" || $.asignaStatusAsignacion === "Confirmado Instructor" || $.asignaStatusAsignacion ==="Curso Editado"){
			var r = confirm("Seguro de Cancelar el Evento, esta operacion no se puede reversar, tendría que crear un Evento nuevo.")
			if(r){
				$('#statusAsignacionbtn').html("Evento Cancelado");
				$('#statusAsignacionbtn').removeClass("btn-info");
				$('#statusAsignacionbtn').addClass("btn-danger");
				$.asignaStatusAsignacion="Evento Cancelado";
			}	
		}	
	}

	function validaObservaciones(){
		$('#alertaFecha').remove();
		$('#alertaCliente').remove();
		$('#alertaCurso').remove();
		$('#alertaInstructor').remove();
		$('#alertaHorarios').remove();
		$('#alertaParticipantes').remove();
		$('#alertaNivel').remove();
		
		$.asignaObservaciones = $('#asignaObservaciones').val();
		if($('#asignaArchivos').val() === null || $('#asignaArchivos').val() === ""){
//			//console.log($('#asignaArchivos').val());
		}else{
			$.asignaArchivos = $('#asignaArchivos').val();
			while($.asignaArchivos.includes("C:\\fakepath\\") ){
				$.asignaArchivos = $.asignaArchivos.replace("C:\\fakepath\\", "")	
			}
		}
	}

	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
/*
 * UTILIDADES
 */
	
   	function alertaFade(alerta){
   		
		$(alerta).insertAfter($('.alerta_in'));
		  $('.alerta').fadeIn();
		  $('.alerta').fadeOut(5000);
	  
		  $('#alertaFecha').remove();
			$('#alertaCliente').remove();
			$('#alertaCurso').remove();
			$('#alertaInstructor').remove();
			$('#alertaHorarios').remove();
			$('#alertaParticipantes').remove();
			$('#alertaNivel').remove();
	}
   	
	function alertaFadeVacio(alerta){
		$("#procesoVacio").remove();
		procesoVacio="<div class='alert alert-danger' id='procesoVacio' role='alert'>Verificar los campos:<ul id='listaProcesoVacio'></ul></div>";
		$(procesoVacio).insertAfter($('.alerta_inVacio'));
	}
	
	function ordenaFecha(fecha){
		fecha = fecha.split("/");
		return fecha[1]+"/"+fecha[0]+"/"+fecha[2];
	}

   	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
   	 * FILEUPLOAD
   	 */
   	function validaArchivos(archivosCampo){
   	  
        enviaFile(asignacionItem.idAsignacionLogica);
        var files = archivosCampo.files;
        for (var i = 0; i < files.length; i++) {           
            var file = files[i];
//            //console.log(file);          
            }        
        }
   	
   	function fechaArchivo(fecha){
   		fecha = fecha.toString();
   		fecha = fecha.split("/");
   		return fecha[0]+fecha[1]+fecha[2];	
   	}
   	
   	
   	
  	function enviaFile(rfcCliente){
		

  		limpiaAlerta();

//		//console.log("envio idAsignacion:"+rfcCliente);
		var alerta="";
		 var form = $('#actualizaAsignacion')[0]; //$('#formImagenLogoCliente').attr('files'),
        var data = new FormData(form);
//        //console.log(data);
		  $.ajax({
			url: "fileAsignacion/"+rfcCliente,
		    type: "POST",
		    data: data,
		    enctype: 'multipart/form-data',
		    processData: false,
		    contentType: false,
		    cache: false,
		    success: 	function(data){
		    	if(data.codigo===0){
		    		if(data.codigo===0){
		  			  alerta="<div class='alert alert-success' role='alert'>Éxito carga</div>";
		  			  $(alerta).insertAfter($('.alertaFile'));
//		  			  //console.log("envio ok");
		  	    	}else{
		  	    		alerta="<div class='alert alert-warning' role='alert'>imagen : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
		  				  $(alerta).insertAfter($('.alertaFile'));
//		  	    		//console.log("envio Nok");
		  	    	}
		    	  } 
		    	},
		    error: function () {
		    	alerta="<div class='alert alert-danger' role='alert'>Error en carga de Archivo</div>";
				  $(alerta).insertAfter($('.alertaFile'));
//		  	//console.log("envio error");
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
	
	function modalClose(){
		 $("#modalIngresa .close").click();
		 $("#modalCita .close").click();
		 $("#modalRegistro .close").click();
		 $(".modal .close").click();
		 $("body,html").animate({
		        scrollTop: 0
		    }, 600);
	
	}
	
	
	function avisaAlertaImagen(data){
		
	}
	function errorAlertaImagen(){
		
	}




//   FIN  JScript