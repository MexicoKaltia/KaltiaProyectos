
   	
$(document).ready(function(){
	 $.asignaFecha ="";
	 $.asignaFecha2 ="";
	 $.asignaFechaCalendario; 
	 $.asignaCliente ="";
	 $.asignaCurso="" ;
	 $.asignaCursoTexto="";
	 $.asignaInstructor="";
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
	 $.asignaUserCreateAsignacion=nombreUsuario;
	 $.asignacionMismoDia;
	 
	 
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
		if($.asignaHorarioFinal === "" || $.asignaHorarioFinal=== null || $.horasEfectivas === "" ){
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
		$('#modalVentas').html('<b>'+$.asignaUserCreateAsignacion+'</b>');
		
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
		
	})

	var clientesVendedor = new Array();
	clientesVendedor = vendedorCliente(operacionId, perfilUsuario);
	if(perfilUsuario ==="Vendedor"){
		$('#asignaCliente').empty();
		$('#asignaCliente').append("<option value='' selected  >Selecciona Cliente</option>");
		for(i in clientesVendedor){
			$('#asignaCliente').append("<option value='"+clientesVendedor[i].idCliente+"'>"+clientesVendedor[i].nombreCortoCliente+"</option>");
		}
		$("#asignaCliente").trigger("chosen:updated");
	}
	
	$("#asignaHorarioInicio").append('<option value="" selected  >Selecciona Horario Inicio</option>');
	for(var i = 0; i < 24 ; i++){
			if((i) < 10){
				$("#asignaHorarioInicio").append('<option value="0'+(i)+'00">0'+(i)+':00</option>');
				$("#asignaHorarioInicio").append('<option value="0'+(i)+'30">0'+(i)+':30</option>');
			}else{
				$("#asignaHorarioInicio").append('<option value="'+(i)+'00">'+(i)+':00</option>');
				$("#asignaHorarioInicio").append('<option value="'+(i)+'30">'+(i)+':30</option>');
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
	});
	
	
});  // fin de documento JQuery


var proceso="<div class='alert alert-info' id='proceso' role='alert'>Resumen de Proceso:<ul id='listaProceso'></ul></div>";
var procesoVacio="";
var alertaEmpty;
var procesoFecha;//="<li>Prospecto Fecha:"+ $.asignaCliente +"</li>";
var procesoCliente;//="<li>Prospecto Cliente"+ $.asignaCliente +"</li>";
var procesoCurso;//="<li>Prospecto Curso"+ $.asignaCurso +"</li>";
var procesoInstructor;//="<li>Prospecto Instructor"+ $.asignaInstructor +"</li>";
var procesoHorario;//="<li>Prospecto Horario: Horario</li>";
var procesoParticipantes;
var procesoObservaciones;//="<li>Prospecto Observaciones"+ $.asignaObservaciones +"</li>";
	

//const zonabase = {"11":true,"12":true,"13":true,"14":true,"15":true,"16":true,"17":false,"18":false,"19":false,"21":true,"22":true,"23":true,"24":true,"25":false,"26":true,"27":true,"28":false,"29":false,"31":true,"32":true,"33":true,"34":true,"35":false,"36":true,"37":false,"38":false,"39":false,"41":true,"42":true,"43":true,"44":true,"45":false,"46":false,"47":false,"48":false,"49":false,"51":true,"52":false,"53":false,"54":false,"55":true,"56":false,"57":false,"58":false,"59":false,"61":true,"62":true,"63":true,"64":false,"65":false,"66":true,"67":true,"68":false,"69":false,"71":false,"72":true,"73":false,"74":false,"75":false,"76":true,"77":true,"78":false,"79":false,"81":false,"82":false,"83":false,"84":false,"85":false,"86":false,"87":false,"88":false,"89":false,"91":false,"92":false,"93":false,"94":false,"95":false,"96":false,"97":false,"98":false,"99":true};
const zonabase = JSON.parse(asignacionZonaBase.zonaBase);
//console.log(jsonZonaBase);
var alerta, proceso;



//  JScript
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
 *  valores Submit
 */

	function asignaCamposSubmit(){
		$('#fechaAsignacion').val($.asignaFecha2);
		$('#idClienteAsignacion').val($.asignaCliente);
		$('#clienteAsignacion').val($.asignaClienteTexto);
		$('#idCursoAsignacion').val($.asignaCurso);
		$('#cursoAsignacion').val($.asignaCursoTexto);
		$('#idInstructorAsignacion').val($.asignaInstructor);
		$('#instructorAsignacion').val($.asignaInstructorTexto);
		$('#horarioAsignacion').val($.asignaHorarioInicio +";"+ $.asignaHorarioFinal +";"+ $.asignaRecesoInicio +";"+ $.asignaRecesoFinal +";"+ $.horasEfectivasTexto);
		$('#participantesAsignacion').val($.asignaParticipantesTexto);
		$('#nivelAsignacion').val($.asignaNivelTexto);
		$('#archivosAsignacionTexto').val($.asignaArchivos);
		$('#archivosAsignacion').html($.asignaArchivos);
		$('#observacionesAsignacion').val($.asignaObservaciones);
		$('#idRegionAsignacion').val($.asignaIdRegion);
		$('#nombreRegionAsignacion').val($.asignaNombreRegion);
		$('#tipoCursoAsignacion').val($.asignaTipoCurso);
		
		if(perfilUsuario === "Vendedor"){
			$('#userCreateAsignacion').val(idUsuario);
			$('#userCreateAsignacionTexto').val(nombreUsuario);
		}else{
			var realVendedor = validaUserCaptura();
			$('#userCreateAsignacion').val(realVendedor.vendedorCliente.idVendedor);
			$('#userCreateAsignacionTexto').val(realVendedor.vendedorCliente.nombreVendedor);
		}
		
		$('#realCapturaId').val(idUsuario);
		$('#realCapturaNombre').val(nombreUsuario);
		
		
	}
	
	function validaUserCaptura(){
		var jsonCliente;
		for(var a in asignacionClientes){
			var cliente = asignacionClientes[a];
			if((cliente.idCliente * 1) === ($('#asignaCliente').val() * 1)){
				jsonCliente = cliente;
			}
		}
		return jsonCliente;
	}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	function limpiaCurso(){
		$('#asignaCurso').val("");
		$('#asignaCurso option[value=""]');
		$("#asignaCurso").trigger("chosen:updated");
		
		$('#asignaInstructor').val("");
		$.asignaCursoTexto= null;
		$.asignaInstructorTexto= null;
		$.asignaCurso= null;
		$.asignaInstructor= null;
	}
/*
 * ValidaFECHA
 */
	
	function validaFecha(inputAsignaFecha){
		var elementoPicker = $datepicker.pickadate('picker');	
		$.asignaFecha = elementoPicker.get('select', 'dd/mm/yyyy');
		$.asignaFecha2 = elementoPicker.get('select', 'mm/dd/yyyy');
   	    $.asignaFechaCalendario = $('#asignaFecha').val();
		
		limpiaCurso();
		$('#asignaInstructor').empty();
		
   		if($.asignaFechaCalendario === null || $.asignaFechaCalendario === ""){
   			alerta="<div class='alert alert-danger' id='alertaFecha' role='alert'>Seleccione Fecha</div>";
			alertaFade(alerta);
			$('#btnAsignaFecha').attr("disabled", true);
   		}else{
   			$('#btnAsignaFecha').attr("disabled", false);
   		}
   		procesoFecha="<li>Prospecto Fecha : <b>"+ $.asignaFechaCalendario +"</b></li>";
   	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * ValidaCLIENTE
	 */
	function validaCliente(){
		limpiaCurso();
		
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
		procesoCliente="<li>Prospecto Cliente : <b>"+ $.asignaClienteTexto +"</b>"+zonaCliente+"</li>";
	
	}
	
	
	
	function vendedorCliente(idV, perfil){
		var idVendedorCliente;
		var clientes= new Array();
		for(i in asignacionClientes){
			var cliente = asignacionClientes[i];
			idVendedorCliente = cliente.vendedorCliente.idVendedor;
			if(cliente.statusCliente !== "Baja"){
				if(perfil === "Vendedor"){
					if((idVendedorCliente *1) === (idV*1))
						clientes.push(cliente);
				}else{
					clientes.push(cliente);
				}
			}		
		}
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
	var tipoCurso = true;
	var tipoCursoVal = "PRESENCIAL";
	var arrayInstructores = new Array();
	var instructoresDiaSelect = new Array();
	var instructoresDmin1 = new Array();
	var instructoresDmas1 = new Array();
	var instructoresDiaAyer = new Array();
	var instructoresDiaMan = new Array();
	var instructoresFinal = new Array();
	
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
	
	
	 var asignacionAsignaciones = new Array();
	 var asignacion;
	 var asignacionesAyer  = new Array();
	 var horarioInstructorDisponible;
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
		 instructoresFinal.length = 0;
		 asignacionesAyer.length = 0;
		 horarioInstructorDisponible ="";
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
   			$('#asignaInstructor').empty();
			$('#asignaInstructor').append('<option value="" selected  >Selecciona Instructor</option>');
   		}
		
		 
		 for(a in asignacionAsignacionesTotal){
			 asignacion = asignacionAsignacionesTotal[a];
			 if(asignacion.statusAsignacion !== "Evento Cancelado"){
				 asignacionAsignaciones.push(asignacion);
			 }
		 }
		 
		 
		/* 
		 * No VALIDAR Esquemas de movilidad para Perfil Operacion y Direccion
		 */
		if(perfilUsuario === "Operacion" || perfilUsuario === "Direccion"){
			var valorCurso = $.asignaCurso * 1;
				for (i in asignacionInstructoresOperacion){				
					var arrayCursosInstructor = asignacionInstructoresOperacion[i].cursosInstructor.replace('"','').replace('"','').replace(' ','').split(',');
					instructor = asignacionInstructoresOperacion[i];
					for( e in arrayCursosInstructor){
						arrayCursosInstructor[e] = arrayCursosInstructor[e].replace('[','');
						arrayCursosInstructor[e] = arrayCursosInstructor[e].replace(']','');
						arrayCursosInstructor[e] = arrayCursosInstructor[e].replace(' ','') * 1;
						if(arrayCursosInstructor[e] === valorCurso){
							arrayInstructores.push(asignacionInstructores[i])
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
			//console.log(arrayInstructores);
			/*
			 * Valida dias de Ausencia
			 */
//************			
			var instructoresDiaAusencia = new Array();;
			for(a in arrayInstructores){
				var instructorDiaAusencia = arrayInstructores[a]; 
				if(validaDiaAusencia(instructorDiaAusencia)){
					instructoresDiaAusencia.push(instructorDiaAusencia);
				}
			}
			arrayInstructores.length = 0;
			arrayInstructores = instructoresDiaAusencia ;
			
			var regionInstructor;
			var regionCliente;
			var instructor;
			var idInstructor;
			var jsonCliente;
			
				/*
				 * Obtener ZonaCliente 
				 */
				if($('#asignaCliente').val() === null || $('#asignaCliente').val() === ""){
		   			alerta="<div class='alert alert-danger' id='alertaCliente' role='alert'>Seleccione Cliente</div>";
					alertaFade(alerta);
					$('#btnAsignaCurso').attr("disabled", true);
				}else{
					for (var o in asignacionClientes){
						var idCliente = asignacionClientes[o].idCliente;
						var cliente = asignacionClientes[o];
						if((idCliente * 1) === ($('#asignaCliente').val() * 1)){
							jsonCliente = cliente;
						}
					}
					regionCliente = jsonCliente.regionCliente.idRegion;
					//console.log(jsonCliente);
				}
				
				if(tipoCurso){

				/*
				 * Consultar D-1 y D+1 Instructores
				 */
				
				/*
				 * validar dia seleccion
				 */
				for(u in instructoresDiaAusencia){
					 instructor = instructoresDiaAusencia[u];
					 idInstructor = instructor.idInstructor;
					 nombreInstructor = instructor.nombreInstructor
					if(validaDiaSelect(idInstructor, jsonCliente)){
						instructoresDiaSelect.push(instructor);
					}
				}
//					console.log($.asignacionMismoDia);
				/*
				 * libre ayer
				 */
				for(aa in instructoresDiaSelect){
					instructor = instructoresDiaSelect[aa];
					idInstructor = instructor.idInstructor;
					nombreInstructor = instructor.nombreInstructor;
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
//				console.log(instructoresDiaAyer);
				/*
				 * libre mañana 
				 */
				for(ae in instructoresDiaAyer){
					instructor = instructoresDiaAyer[ae];
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
				
				/*
				 * elimina instructor Juan alberto Zuñiga, limitar solamente en su zona 
				 */
//				console.log("idRegionOrigen : " + regionCliente);
				for(ai in instructoresDiaMan){
					instructor = instructoresDiaMan[ai];
					var regionInstructor = instructor.regionInstructor.idRegion;
					if(instructor.idInstructor == 8){
						if(regionInstructor === regionCliente){
							instructoresFinal.push(instructor);
						}
					}else{
						instructoresFinal.push(instructor);
					}
				}

				for(ae in instructoresFinal){
					instructor = instructoresFinal[ae];
//					console.log(instructor);
					$('#asignaInstructor').append('<option value="'+instructor.idInstructor+'">'+instructor.nombreInstructor+'</option>');
				}
				
				
			}else{
				//validar dia seleccion
				console.log(arrayInstructores);
				for(i in arrayInstructores){
					 var instructor = arrayInstructores[i];
					 var idInstructor = instructor.idInstructor
					 var nombreInstructor = instructor.nombreInstructor
					if(validaDiaSelect(idInstructor, jsonCliente)){
						$('#asignaInstructor').append('<option value="'+idInstructor+'">'+nombreInstructor+'</option>');
					}
				}
			}
		}	
		$.asignaCursoTexto = $("#asignaCurso option:selected").text();
		procesoCurso="<li>Prospecto Curso : <b>"+ $.asignaCursoTexto +" : <i><u>"+tipoCursoVal+"</u></i></b></li>";
		$.asignaTipoCurso = tipoCursoVal;
	}  // fin metodo validaCurso
	
	function validaDiaAusencia(instructor){
		var fechaDisponible = true;
		var fechaSelect = new Date($.asignaFecha2);
		var fechasAusente = new Array();
		
		if(instructor.listFechas){
			fechasAusente = instructor.listFechas.toString().split(";");
			for(e in fechasAusente){
				var fechaAusente = fechaTransforma(fechasAusente[e]); 
				if(fechaAusente.toString() === $.asignaFecha2.toString()){
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
			if((asignacionFecha === dayerTexto) && (asignacionInstructor === idInstructor)){
				if(asignacion.tipoCursoAsignacion === "PRESENCIAL"){
					asignacionesAyer.push(asignacion);			 
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
			if((asignacionFecha.toString() === dManTexto.toString()) && (asignacionInstructor.toString() === idInstructor.toString())){
				if(asignacion.tipoCursoAsignacion === "PRESENCIAL"){
					return true;
				}
			}
		}
		return false;
	}
	
 
	
	function validaDiaSelect(idInstructor, cliente){
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
				if(asignacion.idClienteAsignacion === cliente.idCliente){
//					console.log(idInstructor.toString());
					$.asignacionMismoDia = asignacion;
					fechaDisponible = true;
				}
				break;
			}
		}
		return fechaDisponible;
	}
	
	
	function validaMismoDia(idInstructor, cliente){
		var fechaDisponible = false;
		var asignacion;
		var asigna;
		var dia;
		console.log(cliente);
		for(i in asignacionAsignaciones){
			asignacion = asignacionAsignaciones[i];
			asigna = asignacion.fechaAsignacion.toString().split("/");
			dia = asigna[1]+"/"+asigna[0]+"/"+asigna[2];
			console.log(dia);
			console.log(idInstructor);
			if((dia === $.asignaFecha.toString()) && (asignacion.idInstructorAsignacion.toString() === idInstructor.toString())){
				console.log("if mismo dia");
				if(cliente.idCliente === asignacion.idCliente){
					console.log("if mismo cliente");
					fechaDisponible = true;
					break;
				}
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
	
	function fechaTransforma(fecha){
		var fechaSelect = new Date(fecha);
		var dia = fechaSelect.getDate();
		var mes = (fechaSelect.getMonth()+1);
		var anio =fechaSelect.getFullYear();
		if(dia<10)
			dia = "0"+dia.toString();
		if(mes<10)
			mes = "0"+mes.toString();
		var fechaTexto = mes +"/"+ dia +"/"+ anio ;
		return fechaTexto ;
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
		horarioInstructorDisponible = 0;
		$('#horarioInstructorNoDisponible').empty();
		$('#asignaHorarioInicio').val("");
		validaHorarioInstructor($.asignaInstructor);
		validarHorarioMismoDia($.asignacionMismoDia, $.asignaInstructor);
	}
	
	function validaHorarioInstructor(idInstructor){
		for(a in asignacionesAyer){
			var asignacion = asignacionesAyer[a];
			if(asignacion.idInstructorAsignacion*1 === idInstructor*1){
				var horario = asignacion.horarioAsignacion.split(";");
				if(horario[1] > 1400){
					horarioInstructorDisponible = (horario[1]*1)-1400;
				}
			}
		}
	}
	
	function validarHorarioMismoDia(asignacionMismoDia, idInstructor){
		if(asignacionMismoDia){
			console.log(asignacionMismoDia);
			
			var horario = asignacionMismoDia.horarioAsignacion.split(";");
			
			if($.asignacionMismoDia.idInstructorAsignacion === idInstructor*1){
				var horario = $.asignacionMismoDia.horarioAsignacion.split(";");
				horarioInstructorDisponible = ((horarioInstructorDisponible*1) + (horario[1]*1));
			}
		}
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
	 
	 $('#asignaHorarioInicio').change(function(){
			if($.asignaHorarioInicio.substring(0,2)*100 < horarioInstructorDisponible*1){
					$('#horarioInstructorNoDisponible').empty();
					$('#horarioInstructorNoDisponible').append("<div class='alert alert-warning'  role='alert'><b>Horario no disponible para Instructor : "+$.asignaInstructorTexto+",</b> debe de cumplir un horario mayor a 10 horas despues de su última asignación o cumplir horario de capacitación con mismo cliente despues de su asignación, en caso de ser necesario reporte al staff de Operación para autorización.<br><b>Horario disponible apartir de : "+horarioInstructorDisponible/100+"hrs</b></div>");
					$("#asignaHorarioFinal").attr("disabled", true);
				}else{
					$('#horarioInstructorNoDisponible').empty();
					$("#asignaHorarioFinal").attr("disabled", false);
				}
		});
	 
	 
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
			//console.log("Horario INVALIDO");
   		}else{
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

		var hrEf = horaEfectiva.split(":");
		var inicioEfe = 0;
		if(hrEf[1] === "00"){
				for(var i = inicioEfe; i <((hrEf[0]*1)+ 0) ; i++){
						$("#horasEfectivas").append('<option value="'+(i)+'30">'+(i)+':30</option>');
						$("#horasEfectivas").append('<option value="'+(i+1)+'00">'+(i+1)+':00</option>');
					}
			}else{
				for(var i = inicioEfe; i <((hrEf[0]*1)+ 0) ; i++){
					o = i - 0.3;
					$("#horasEfectivas").append('<option value="'+(i+1)+'00">'+(i+1)+':00</option>');
					$("#horasEfectivas").append('<option value="'+(i+1)+'30">'+(i+1)+':30</option>');
				}
   		}
	}
	
	$("#horasEfectivas").change(function(){
		var horasE = $("#horasEfectivas").val(); 
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
		
		procesoParticipantes="<li>Prospecto Participantes: <b>"+ $.asignaParticipantesTexto +"</b></li><li>Prospecto Nivel: <b>"+ $.asignaNivelTexto +"</b></li>";

		
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
   	  var fecha = fechaArchivo($.asignaFecha);
  	  var idAsignacion = fecha +"-"+$.asignaCliente+"-"+$.asignaInstructor+"-"+$.asignaCurso

        enviaFile(idAsignacion);
        var files = archivosCampo.files;
        for (var i = 0; i < files.length; i++) {           
            var file = files[i];
            //console.log(file);          
            }        
        }
   	
   	function fechaArchivo(fecha){
   		fecha = fecha.toString();
   		fecha = fecha.split("/");
   		return fecha[0]+fecha[1]+fecha[2];
   		
   	}
  	  
  	 

  	function enviaFile(rfcCliente){
		
  		limpiaAlerta();

		//console.log("envio idAsignacion:"+rfcCliente);
		var alerta="";
		 var form = $('#asignaForm')[0]; //$('#formImagenLogoCliente').attr('files'),
        var data = new FormData(form);
        //console.log(data);
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
		  			  //console.log("envio ok");
		  	    	}else{
		  	    		alerta="<div class='alert alert-warning' role='alert'>imagen : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
		  				  $(alerta).insertAfter($('.alertaFile'));
		  	    		//console.log("envio Nok");
		  	    	}
		    	  } 
		    	},
		    error: function () {
		    	alerta="<div class='alert alert-danger' role='alert'>Error en carga de Archivo</div>";
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
	
	function modalClose(){
		 $("#modalIngresa .close").click();
		 $("#modalCita .close").click();
		 $("#modalRegistro .close").click();
		 $(".modal .close").click();
		 $("body,html").animate({
		        scrollTop: 0
		    }, 600);
	
	}
	
	




//   FIN  JScript