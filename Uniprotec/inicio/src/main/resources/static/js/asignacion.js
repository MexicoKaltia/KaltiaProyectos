
   	
$(document).ready(function(){
	$.asignaFecha ;
	 $.asignaFechaCalendario; 
	 $.asignaCliente;
	 $.asignaCurso ;
	 $.asignaInstructor; 
	 $.asignaHorario; 
	 $.asignaObservaciones; 
	 $.asignaArchivos;
	 
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
	$('#procesoObservaciones').click(function(){
		$(".listaProceso").empty();
		$(".listaProceso").append(procesoFecha);
		$(".listaProceso").append(procesoCliente);
		$(".listaProceso").append(procesoCurso);
		$(".listaProceso").append(procesoInstructor);
		$(".listaProceso").append(procesoHorario);
	})


	
});  // fin de documento JQuery


var proceso="<div class='alert alert-info' id='proceso' role='alert'>Resumen de Proceso:<ul id='listaProceso'></ul></div>";
var procesoFecha;//="<li>Prospecto Fecha:"+ $.asignaCliente +"</li>";
var procesoCliente;//="<li>Prospecto Cliente"+ $.asignaCliente +"</li>";
var procesoCurso;//="<li>Prospecto Curso"+ $.asignaCurso +"</li>";
var procesoInstructor;//="<li>Prospecto Instructor"+ $.asignaInstructor +"</li>";
var procesoHorario;//="<li>Prospecto Horario"+ $.asignaHorario +"</li>";
var procesoObservaciones;//="<li>Prospecto Observaciones"+ $.asignaObservaciones +"</li>";

const zonabase = {"11":true,"12":true,"13":true,"14":true,"15":true,"16":true,"17":false,"18":false,"21":true,"22":true,"23":true,"24":true,"25":false,"26":true,"27":false,"28":false,"31":true,"32":true,"33":true,"34":true,"35":false,"36":true,"37":false,"38":false,"41":true,"42":true,"43":true,"44":true,"45":false,"46":false,"47":false,"48":false,"51":true,"52":false,"53":false,"54":false,"55":true,"56":false,"57":false,"58":false,"61":true,"62":true,"63":true,"64":false,"65":false,"66":true,"67":true,"68":false,"71":false,"72":true,"73":false,"74":false,"75":false,"76":true,"77":true,"78":false,"81":false,"82":false,"83":false,"84":false,"85":false,"86":false,"87":false,"88":false}
var alerta, proceso;


//  JScript

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
 * ValidaFECHA
 */
	function validaFecha(inputAsignaFecha){
		var elementoPicker = $datepicker.pickadate('picker');
		$.asignaFecha = elementoPicker.get('select', 'dd/mm/yyyy');
   	    console.log($.asignaFecha);
   	    $.asignaFechaCalendario = $('#asignaFecha').val();
		console.log("asignaFecha:"+ $.asignaFechaCalendario);
   		$('#alertaFecha').remove();
   		
   		if($('#asignaFecha').val() === null || $('#asignaFecha').val() === ""){
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
		$.asignaCliente = $('#asignaCliente').val()
		$.asignaClienteTexto = $("#asignaCliente option:selected").text();
		console.log("asignaCliente:"+ $.asignaCliente);
		$('#alertaFecha').remove();
		$('#alertaCliente').remove();
		
		if($('#asignaCliente').val() === null || $('#asignaCliente').val() === ""){
   			alerta="<div class='alert alert-danger' id='alertaCliente' role='alert'>Seleccione Cliente</div>";
			alertaFade(alerta);
			$('#btnAsignaCliente').attr("disabled", true);
   		}else{
   			$('#btnAsignaCliente').attr("disabled", false);
   		}
		procesoCliente="<li>Prospecto Cliente : <b>"+ $.asignaClienteTexto +"</b></li>";
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * ValidaCURSO
	 */
	var tipoCurso = true;
	function checkTipoCurso(){
		
		if(tipoCurso){
			$('#tipoCurso').html("ON LINE");
			$('#tipoCurso').removeClass("btn-alternate");
			$('#tipoCurso').addClass("btn-warning");
			tipoCurso = false;
		}else{
			$('#tipoCurso').html("PRESENCIAL");
			$('#tipoCurso').removeClass("btn-warning");
			$('#tipoCurso').addClass("btn-alternate");
			tipoCurso = true;
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
		
		if($('#asignaCurso').val() === null || $('#asignaCurso').val() === ""){
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
		var regionInstructor;
		var regionCliente;
		var instructor;
		var idInstructor;

//		console.log("tipoCurso:"+tipoCurso);
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
		$.asignaCursoTexto = $("#asignaCurso option:selected").text();
		procesoCurso="<li>Prospecto Curso : <b>"+ $.asignaCursoTexto +"</b></li>";
	}  // fin metodo validaCurso
	
	function validaDiaSelect(idInstructor){
		for(i in asignacionAsignaciones){
			if((asignacionAsignaciones[i].fechaAsignacion === $.asignaFecha) && (asignacionAsignaciones[i].idInstructorAsignacion === idInstructor)){
				return false;
			}else{
				return true;
			}
		}
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
		
		if($('#asignaInstructor').val() === null || $('#asignaInstructor').val() === ""){
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
 * UTILIDADES
 */
	
   	function alertaFade(alerta){
   		
		$(alerta).insertAfter($('.alerta_in'));
		  $('.alerta').fadeIn();
		  $('.alerta').fadeOut(5000);
	  
			$('#alertaFecha').remove();
			$('#alertaCliente').remove();
			$('#alertaCurso').remove();
	}

   	function procesoAlerta(proceso){
   	   	
		$(proceso).insertAfter($('.proceso_in'));
		  $('.proceso').fadeIn();
			$('#procesoFecha').remove();
			$('#procesoCliente').remove();
			$('#procesoCurso').remove();
	}

	
	






//   FIN  JScript