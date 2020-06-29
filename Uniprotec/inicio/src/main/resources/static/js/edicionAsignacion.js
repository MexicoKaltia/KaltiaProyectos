
   	
$(document).ready(function(){
	
	console.log(asignacionItem);
	var proceso="<div class='alert alert-secondary' id='proceso' role='alert'>Resumen de Proceso de Edicion:<ul id='listaProceso'></ul></div>";
	var procesoVacio="";
	var alertaEmpty;
	var procesoFecha;//="<li>Edicion Fecha:"+ $.asignaCliente +"</li>";
	var procesoCliente;//="<li>Edicion Cliente"+ $.asignaCliente +"</li>";
	var procesoCurso;//="<li>Edicion Curso"+ $.asignaCurso +"</li>";
	var procesoInstructor;//="<li>Edicion Instructor"+ $.asignaInstructor +"</li>";
	var procesoHorario;//="<li>Edicion Horario: Horario</li>";
	var procesoParticipantes;
	var procesoObservaciones;//="<li>Edicion Observaciones"+ $.asignaObservaciones +"</li>";

	const zonabase = {"11":true,"12":true,"13":true,"14":true,"15":true,"16":true,"17":false,"18":false,"21":true,"22":true,"23":true,"24":true,"25":false,"26":true,"27":false,"28":false,"31":true,"32":true,"33":true,"34":true,"35":false,"36":true,"37":false,"38":false,"41":true,"42":true,"43":true,"44":true,"45":false,"46":false,"47":false,"48":false,"51":true,"52":false,"53":false,"54":false,"55":true,"56":false,"57":false,"58":false,"61":true,"62":true,"63":true,"64":false,"65":false,"66":true,"67":true,"68":false,"71":false,"72":true,"73":false,"74":false,"75":false,"76":true,"77":true,"78":false,"81":false,"82":false,"83":false,"84":false,"85":false,"86":false,"87":false,"88":false}
	var alerta;


	
	$('#procesoObservaciones').on(function(){
		$(".listaProceso").empty();
		$(".listaProceso").append(procesoFecha);
		$(".listaProceso").append(procesoCliente);
		$(".listaProceso").append(procesoCurso);
		$(".listaProceso").append(procesoInstructor);
		$(".listaProceso").append(procesoHorario);
		$(".listaProceso").append(procesoParticipantes);
	})
	
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
	$('#asignaInstructor').append('<option value="'+asignacionItem.idInstructorAsignacion+'" selected >'+asignacionItem.instructorAsignacion+'</option>');
	//--------------------------------------------------
	hr = asignacionItem.horarioAsignacion.split(";")
	hrInicio = horaSel(hr[0]);
	hrFinal = horaSel(hr[1]);
	$('#asignaHorarioInicio').append('<option value="'+hrInicio+'00" selected >'+hrInicio+':00</option>');
	$('#asignaHorarioFinal').append('<option value="'+hrFinal+'00" selected >'+hrFinal+':00</option>');
	if((hr[3] * 1) > 0){
		hrDInicio = horaSel(hr[2]);
		hrDFinal = horaSel(hr[3]);
		$('#asignaRecesoInicio').append('<option value="'+hrDInicio+'00" selected >'+hrDInicio+':00</option>');
		$('#asignaRecesoFinal').append('<option value="'+hrDFinal+'00" selected >'+hrDFinal+':00</option>');
	}
	$('#horasEfectivas').text(hr[4])
	//--------------------------------------------------
	$('#asignaParticipantes').append('<option value="'+asignacionItem.participantesAsignacion+'" selected >'+asignacionItem.participantesAsignacion+'</option>');
	//--------------------------------------------------
	$('#asignaNivel').append('<option value="'+asignacionItem.nivelAsignacion+'" selected >'+asignacionItem.nivelAsignacion+'</option>');
	//--------------------------------------------------
	$('#asignaObservaciones').text(asignacionItem.observacionesAsignacion);
	//--------------------------------------------------
	$("#linkFile").attr('href', '/uploads/fileAsignacion/'+asignacionItem.idAsignacionLogica+'/'+asignacionItem.archivosAsignacion)
    $("#linkFile").html('<b>'+asignacionItem.archivosAsignacion+'</b>');
	//--------------------------------------------------
	
	
	 $.asignaFecha =asignacionItem.fechaAsignacion;
	 $.asignaFecha2 ="";
	 $.asignaFechaCalendario; 
	 $.asignaCliente =asignacionItem.idClienteAsignacion;
	 $.asignaCurso=asignacionItem.idCursoAsignacion ;
	 $.asignaCursoTexto="";
	 $.asignaInstructor=asignacionItem.idInstructorAsignacion;
	 $.asignaInstructorTexto="";
	 $.asignaHorarioInicio="";
	 $.asignaHorarioFinal="";
	 $.asignaParticipantes="";
	 $.asignaParticipantesTexto="";
	 $.asignaNivel="";
	 $.asignaNivelTexto="";
	 $.asignaObservaciones="";
	 $.asignaArchivos="";
	 $.asignaIdRegion="";
	 $.asignaNombreRegion="";
	 $.asignaTipoCurso="";
	 
	$('#procesoFecha').click(function(){	
	})
	$('#procesoCliente').click(function(){
		$(".listaProceso").empty();
		$(".listaProceso").append(procesoFecha);
	})
	$('#procesoCurso').click(function(){
		$(".listaProceso").empty();
		$(".listaProceso").append(procesoFecha);
		$(".listaProceso").append(procesoCliente);
	})
	$('#procesoInstructor').click(function(){
		$(".listaProceso").empty();
		$(".listaProceso").append(procesoFecha);
		$(".listaProceso").append(procesoCliente);
		$(".listaProceso").append(procesoCurso);
	})
	$('#procesoHorario').click(function(){
		$(".listaProceso").empty();
		$(".listaProceso").append(procesoFecha);
		$(".listaProceso").append(procesoCliente);
		$(".listaProceso").append(procesoCurso);
		$(".listaProceso").append(procesoInstructor);
	})
	$('#procesoParticipantes').click(function(){
		$(".listaProceso").empty();
		$(".listaProceso").append(procesoFecha);
		$(".listaProceso").append(procesoCliente);
		$(".listaProceso").append(procesoCurso);
		$(".listaProceso").append(procesoInstructor);
		$(".listaProceso").append(procesoHorario);
		
	})
	$('#procesoObservaciones').click(function(){
		$(".listaProceso").empty();
		$(".listaProceso").append(procesoFecha);
		$(".listaProceso").append(procesoCliente);
		$(".listaProceso").append(procesoCurso);
		$(".listaProceso").append(procesoInstructor);
		$(".listaProceso").append(procesoHorario);
		$(".listaProceso").append(procesoParticipantes);
	})
	
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
		
	})

	
	
	function horaSel(horarioAsignacion){
		return horarioAsignacion.slice(0,2);
	}
	
});  // fin de documento JQuery


//  JScript
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
 *  valores Submit
 */

	function asignaCamposSubmit(){
		$('#idAsignacion').val(asignacionItem.idAsignacion);
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
		$('#observacionesAsignacion').val($.asignaObservaciones);
		$('#idRegionAsignacion').val($.asignaIdRegion);
		$('#nombreRegionAsignacion').val($.asignaNombreRegion);
		$('#tipoCursoAsignacion').val($.asignaTipoCurso);
	}
	
	$('#procesoObservaciones').on(function(){
		$(".listaProceso").empty();
		$(".listaProceso").append(procesoFecha);
		$(".listaProceso").append(procesoCliente);
		$(".listaProceso").append(procesoCurso);
		$(".listaProceso").append(procesoInstructor);
		$(".listaProceso").append(procesoHorario);
		$(".listaProceso").append(procesoParticipantes);
	})


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
 * ValidaFECHA
 */
	
	
	function validaFecha(inputAsignaFecha){
		var elementoPicker = $datepicker.pickadate('picker');	
		$.asignaFecha = elementoPicker.get('select', 'dd/mm/yyyy');
		$.asignaFecha2 = elementoPicker.get('select', 'mm/dd/yyyy');
   	    console.log($.asignaFecha);
   	    $.asignaFechaCalendario = $('#asignaFecha').val();
		console.log("asignaFecha:"+ $.asignaFechaCalendario);
   		$('#alertaFecha').remove();
   		
   		if($.asignaFechaCalendario === null || $.asignaFechaCalendario === ""){
   			alerta="<div class='alert alert-danger' id='alertaFecha' role='alert'>Seleccione Fecha</div>";
			alertaFade(alerta);
			$('#btnAsignaFecha').attr("disabled", true);
   		}else{
   			$('#btnAsignaFecha').attr("disabled", false);
   		}
   		procesoFecha="<li>Edicion Fecha : <b>"+ $.asignaFechaCalendario +"</b></li>";
   	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * ValidaCLIENTE
	 */
	function validaCliente(){
		$.asignaCliente = $('#asignaCliente').val()
		$.asignaClienteTexto = $("#asignaCliente option:selected").text();
		console.log("asignaCliente:"+ $.asignaCliente);
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
	
	var zonaCliente;
	var idRegion;
	var nombreRegion;
	function colorZonaCliente(cliente){
		cliente = (cliente * 1);
		var idRegion;
		var nombreRegion;
		
		var idCliente
		for(i in asignacionClientes){
//			console.log(asignacionClientes[i]);
			idCliente = (asignacionClientes[i].idCliente * 1);
			if(idCliente === cliente){
				idRegion = (asignacionClientes[i].regionCliente.idRegion *1 );
				nombreRegion = asignacionClientes[i].regionCliente.nombreRegion;
			}
		}
			
//		console.log(idRegion+":"+nombreRegion);
		
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
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * ValidaCURSO
	 */
	
	var tipoCursoVal = "";
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
		$.asignaCurso = $('#asignaCurso').val();
		console.log("asignaCurso:"+ $.asignaCurso);
		
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
		 * Filtra Instructores por Curso
		 */
		var valorCurso = $('#asignaCurso').val() * 1;
		var arrayInstructores = new Array();
		for (i in asignacionInstructores){
//			console.log(asignacionInstructores[i]);
//			console.log(asignacionInstructores[i].idInstructor);
			var arrayCursosInstructor = asignacionInstructores[i].cursosInstructor.replace('"','').replace('"','').replace(' ','').split(',');
			for( e in arrayCursosInstructor){
				arrayCursosInstructor[e] = arrayCursosInstructor[e].replace(' ','') * 1;
				if(arrayCursosInstructor[e] === valorCurso){
//					console.log(asignacionInstructores[i].idInstructor);
//					console.log(asignacionInstructores[i]);
					arrayInstructores.push(asignacionInstructores[i])
				}
			}
		}
		console.log(arrayInstructores);
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
		console.log(arrayInstructores);
		
		var regionInstructor;
		var regionCliente;
		var instructor;
		var idInstructor;

//		console.log("tipoCurso:"+tipoCurso);
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
				console.log(asignacionClientes);
				for (i in asignacionClientes){
					if(asignacionClientes[i].idCliente === ($('#asignaCliente').val() * 1)){
						jsonCliente = asignacionClientes[i];
					}
				}
				regionCliente = jsonCliente.regionCliente.idRegion;
//				console.log(jsonCliente);
//				console.log(jsonCliente.regionCliente.idRegion);
			}
			
			/*
			 * Consultar D-1 y D+1 Instructores
			 */
			var instructoresDiaSelect = new Array();
			var instructoresDmin1 = new Array();
			var instructoresDmas1 = new Array();
			//validar dia seleccion
			for(i in arrayInstructores){
				 instructor = arrayInstructores[i];
				 idInstructor = instructor.idInstructor
				 nombreInstructor = instructor.nombreInstructor
				if(validaDiaSelect(idInstructor)){
					instructoresDiaSelect.push(instructor);
				}
			}
				 console.log(instructoresDiaSelect);
				//validar D-1
			for(i in instructoresDiaSelect){
				 instructor = instructoresDiaSelect[i];
				 idInstructor = instructor.idInstructor
				 nombreInstructor = instructor.nombreInstructor
				regionInstructor = instructor.regionInstructor.idRegion;
				if(validaDmin1(regionCliente, idInstructor)){
					instructoresDmin1.push(instructor);
				}
			}
			console.log(instructoresDmin1);
			//validar D+1
			for(e in instructoresDmin1){
				instructor = instructoresDmin1[e];
				 idInstructor = instructor.idInstructor
				 nombreInstructor = instructor.nombreInstructor
				regionInstructor = instructor.regionInstructor.idRegion;
				regionInstructor = instructor.regionInstructor.idRegion;
				if(validaDmas1(asignaFecha, regionCliente, regionInstructor)){
					instructoresDmas1.push(instructoresDmin1[e]);
					$('#asignaInstructor').append('<option value="'+instructor.idInstructor+'">'+instructor.nombreInstructor+'</option>');
				}
			}
			console.log(instructoresDmas1);
			
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
		$('#asignaInstructor').append('<option value="'+asignacionItem.idInstructorAsignacion+'" selected >'+asignacionItem.instructorAsignacion+'</option>');
		$.asignaCursoTexto = $("#asignaCurso option:selected").text();
		procesoCurso="<li>Edicion Curso : <b>"+ $.asignaCursoTexto +" : <i><u>"+tipoCursoVal+"</u></i></b></li>";
		$.asignaTipoCurso = tipoCursoVal;
	}  // fin metodo validaCurso
	
	function validaDiaAusencia(instructor){
		
		var fechaDisponible = true;
		var fechaAusente;
		var fechaSelect = new Date($.asignaFecha2);
		var fechasAusente = new Array();
//		console.log(instructor);
		if(instructor.listFechas){
			fechasAusente = stringToList(instructor.listFechas)
			for(e in fechasAusente){
				fechaAusente = new Date(fechasAusente[e]);
//				console.log(fechaAusente);
//				console.log(fechaSelect);
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
		var dmin1Texto = dia +"/"+ mes +"/"+ anio ;
//		console.log(dmin1Texto);
//		console.log(asignacionAsignaciones);
		for(i in asignacionAsignaciones){
			asignacion = asignacionAsignaciones[i];
//			console.log(asignacion.idAsignacion);
			asignacionFecha = asignacion.fechaAsignacion;
			asignacionInstructor = asignacion.idInstructorAsignacion;
			if(asignacionFecha === dmin1Texto && (asignacionInstructor === idInstructor)){
				idRegionAsignado = getRegionAsignado(asignacion.idClienteAsignacion);
				console.log(idRegionAsignado);
				return validaZonaBase(regionCliente, idRegionAsignado);
			}
		}
		return true;
	}
	
	function validaDmas1(asignaFecha, regionCliente, regionInstructor){
		return true
	}
	
	function validaZonaBase(regionCliente, regionInstructor){
		var claveZB = regionCliente.toString() + regionInstructor.toString();
//		console.log(claveZB);
//		console.log(zonabase[claveZB]);
		return zonabase[claveZB];
	}
	
	function getRegionAsignado(idClienteAsignacion){
		for(i in asignacionClientes){
			if(asignacionClientes[i].idCliente === idClienteAsignacion)
				return asignacionClientes[i].regionCliente.idRegion;
		}
		
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * ValidaINSTRUCTOR
	 */
	function validaInstructor(){
		
		$.asignaInstructor = $('#asignaInstructor').val()
		console.log("asignaInstructor:"+ $.asignaInstructor);
		
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
		procesoInstructor="<li>Edicion Instructor : <b>"+ $.asignaInstructorTexto +"</b></li>";
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
	
	function validaHorarioInicio(){
		$.asignaHorarioInicio = $('#asignaHorarioInicio').val();
		console.log("asignaHorarioInicio:"+ $.asignaHorarioInicio+".");
		
		$('#alertaFecha').remove();
		$('#alertaCliente').remove();
		$('#alertaCurso').remove();
		$('#alertaInstructor').remove();
		$('#alertaHorarioInicio').remove();
		
		if($.asignaHorarioInicio === null || $.asignaHorarioInicio === ""){
   			alerta="<div class='alert alert-danger' id='alertaHorarioInicio' role='alert'>Seleccione Horario</div>";
			alertaFade(alerta);
			$('#btnAsignaHorario').attr("disabled", true);
			console.log("Horario INVALIDO");
   		}else{
   			$("#asignaHorarioFinal").empty();
   			$("#asignaRecesoInicio").empty();
   			$("#asignaHorarioFinal").append('<option value="">Horario Final</option>');
   			for(var i = ($.asignaHorarioInicio/100); i < 24 ; i++){
   				if((i+1) < 10){
   					$("#asignaHorarioFinal").append('<option value="0'+(i+1)+'00">0'+(i+1)+':00</option>');
   				}else{
   					$("#asignaHorarioFinal").append('<option value="'+(i+1)+'00">'+(i+1)+':00</option>');
   				}
   				
   			}
   			$('#asignaHorarioFinal').attr("disabled", false);
   		}
		$.asignaHorarioInicioTexto = $("#asignaHorarioInicio option:selected").text();
		procesoHorarioInicio="<li>Edicion HorarioInicio : <b>"+ $.asignaHorarioInicioTexto +"</b></li>";
	}
	
	function validaHorarioFinal(){
		$.asignaHorarioFinal = $('#asignaHorarioFinal').val();
		
		$("#asignaRecesoInicio").empty();
		$("#asignaRecesoFinal").empty();
		$("#asignaRecesoInicio").append('<option value="">Receso Inicio</option>');
		for(var i = (($.asignaHorarioInicio/100)); i < (($.asignaHorarioFinal/100)-1) ; i++){
			if((i+1) < 10){
				$("#asignaRecesoInicio").append('<option value="0'+(i+1)+'00">'+(i+1)+':00</option>');
			}else{
				$("#asignaRecesoInicio").append('<option value="0'+(i+1)+'00">'+(i+1)+':00</option>');
			}
				
			}
		if(($.asignaHorarioFinal  === null || $.asignaHorarioFinal === "") || ($.asignaHorarioInicio === null || $.asignaHorarioInicio === "")){
   			alerta="<div class='alert alert-danger' id='alertaHorario' role='alert'>Seleccione Horario</div>";
			alertaFade(alerta);
			$('#btnAsignaHorario').attr("disabled", true);
   		}else{
   			$('#btnAsignaHorario').attr("disabled", false);
   		}
		$('#horasEfectivas').html(sumaHoras());
		$('#asignaRecesoInicio').attr("disabled", false);
	}
	
	function validaRecesoInicio(){
		$.asignaRecesoInicio = $('#asignaRecesoInicio').val();
		$("#asignaRecesoFinal").empty();
		$("#asignaRecesoFinal").append('<option value="">Receso Final</option>');
		for(var i = ($.asignaRecesoInicio/100); i < (($.asignaHorarioFinal/100)-0) ; i++){
			if((i+1) < 10){
				$("#asignaRecesoFinal").append('<option value="0'+(i+1)+'00">'+(i+1)+':00</option>');
			}else{
				$("#asignaRecesoFinal").append('<option value="'+(i+1)+'00">'+(i+1)+':00</option>');
			}
				
			}
		$('#asignaRecesoFinal').attr("disabled", false);
	}
	
	function validaRecesoFinal(){
		
		$.asignaHorarioInicio = $('#asignaHorarioInicio').val();
		$.asignaHorarioFinal = $('#asignaHorarioFinal').val();
		$.asignaRecesoInicio = $('#asignaRecesoInicio').val();
		$.asignaRecesoFinal = $('#asignaRecesoFinal').val();
		$('#horasEfectivas').text(sumaHorasReceso());
	}
	
	function sumaHoras(){
		var asignaHorarioInicio	= ($.asignaHorarioInicio  *1);
		var asignaHorarioFinal	= ($.asignaHorarioFinal  *1);
		$.horasEfectivas = ((asignaHorarioFinal - asignaHorarioInicio)/100)+":00"; 
		procesoHorario="<li>Edicion Horario: <b>"+ $.asignaHorarioInicio+"-"+$.asignaHorarioFinal+"</b>- Horas Efectivas: <b>"+$.horasEfectivas+"</b></li>";
		return $.horasEfectivas;
	}
	function sumaHorasReceso(){
		var asignaHorarioInicio	= ($.asignaHorarioInicio  *1);
		var asignaHorarioFinal	= ($.asignaHorarioFinal  *1);
		var asignaRecesoInicio	= ($.asignaRecesoInicio  *1);
		var asignaRecesoFinal	= ($.asignaRecesoFinal  *1);
		if(asignaRecesoInicio === null || asignaRecesoInicio === "")
			asignaRecesoInicio = 0;
		if(asignaRecesoFinal === null || asignaRecesoFinal === "")
			asignaRecesoFinal = 0;
		
		var efectivas = (asignaHorarioFinal - asignaHorarioInicio );
		var receso = (asignaRecesoFinal - asignaRecesoInicio); 
		$.horasEfectivas = ((efectivas-receso)/100)+":00"; 
		procesoHorario="<li>Edicion Horario: <b>"+ $.asignaHorarioInicio+"-"+$.asignaHorarioFinal+"</b>- Receso: <b>"+$.asignaRecesoInicio+"-"+$.asignaRecesoFinal+"</b>- Horas Efectivas: <b>"+$.horasEfectivas+"</b></li>";
		return $.horasEfectivas;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * ValidaParticipantes
	 */
	function validaParticipantes(){
		
		$.asignaParticipantes = $('#asignaParticipantes').val();
		console.log("asignaParticipantes:"+ $.asignaParticipantes);
		
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
		console.log("asignaNivel:"+ $.asignaNivel);
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
	function validaObservaciones(){
		$('#alertaFecha').remove();
		$('#alertaCliente').remove();
		$('#alertaCurso').remove();
		$('#alertaInstructor').remove();
		$('#alertaHorarios').remove();
		$('#alertaParticipantes').remove();
		$('#alertaNivel').remove();
		
		$.asignaObservaciones = $('#asignaObservaciones').val();
		$.asignaArchivos = $('#asignaArchivos').val()
		while($.asignaArchivos.includes("C:\\fakepath\\") ){
			$.asignaArchivos = $.asignaArchivos.replace("C:\\fakepath\\", "")	
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

   	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
   	 * FILEUPLOAD
   	 */
   	function validaArchivos(archivosCampo){
   	  
        enviaFile(asignacionItem.idAsignacionLogica);
        var files = archivosCampo.files;
        for (var i = 0; i < files.length; i++) {           
            var file = files[i];
            console.log(file);          
            }        
        }
   	
   	function fechaArchivo(fecha){
   		fecha = fecha.toString();
   		fecha = fecha.split("/");
   		return fecha[0]+fecha[1]+fecha[2];	
   	}
   	
   	
   	
  	function enviaFile(rfcCliente){
		

  		limpiaAlerta(),

		console.log("envio idAsignacion:"+rfcCliente);
		var alerta="";
		 var form = $('#actualizaAsignacion')[0]; //$('#formImagenLogoCliente').attr('files'),
        var data = new FormData(form);
        console.log(data);
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
		  			  alerta="<div class='alert alert-success' role='alert'>imagen : 0 - Exito carga</div>";
		  			  $(alerta).insertAfter($('.alertaFile'));
		  			  console.log("envio ok");
		  	    	}else{
		  	    		alerta="<div class='alert alert-warning' role='alert'>imagen : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
		  				  $(alerta).insertAfter($('.alertaFile'));
		  	    		console.log("envio Nok");
		  	    	}
		    	  } 
		    	},
		    error: function () {
		    	alerta="<div class='alert alert-danger' role='alert'>Error en carga de Archivo</div>";
				  $(alerta).insertAfter($('.alertaFile'));
		  	console.log("envio error");
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