$(document).ready(function(){
	
});  // fin de documento JQuery

//  JScript

const zonabase = {"11":true,"12":true,"13":true,"14":true,"15":true,"16":true,"17":false,"18":false,"21":true,"22":true,"23":true,"24":true,"25":false,"26":true,"27":false,"28":false,"31":true,"32":true,"33":true,"34":true,"35":false,"36":true,"37":false,"38":false,"41":true,"42":true,"43":true,"44":true,"45":false,"46":false,"47":false,"48":false,"51":true,"52":false,"53":false,"54":false,"55":true,"56":false,"57":false,"58":false,"61":true,"62":true,"63":true,"64":false,"65":false,"66":true,"67":true,"68":false,"71":false,"72":true,"73":false,"74":false,"75":false,"76":true,"77":true,"78":false,"81":false,"82":false,"83":false,"84":false,"85":false,"86":false,"87":false,"88":false}
var asignaFecha;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
 * ValidaFECHA
 */
	function validaFecha(inputAsignaFecha){
		console.log("asignaFecha:"+ $('#asignaFecha').val());
   		$('#alertaFecha').remove();
   		
   		if($('#asignaFecha').val() === null || $('#asignaFecha').val() === ""){
   			alerta="<div class='alert alert-danger' id='alertaFecha' role='alert'>Seleccione Fecha</div>";
			alertaFade(alerta);
			$('#btnAsignaFecha').attr("disabled", true);
   		}else{
   			$('#btnAsignaFecha').attr("disabled", false);
   		}
   		
   	    var elementoPicker = $datepicker.pickadate('picker');
   	    asignaFecha = elementoPicker.get('select', 'yyyy/mm/dd');
//   	    console.log(elementoPicker.get('select', 'yyyy/mm/dd'));
   	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * ValidaCLIENTE
	 */
	function validaCliente(){
		console.log("asignaCliente:"+ $('#asignaCliente').val());
		$('#alertaFecha').remove();
		$('#alertaCliente').remove();
		
		if($('#asignaCliente').val() === null || $('#asignaCliente').val() === ""){
   			alerta="<div class='alert alert-danger' id='alertaCliente' role='alert'>Seleccione Cliente</div>";
			alertaFade(alerta);
			$('#btnAsignaCliente').attr("disabled", true);
   		}else{
   			$('#btnAsignaCliente').attr("disabled", false);
   		}
		
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
		console.log("asignaCurso:"+ $('#asignaCurso').val());
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
//				console.log(jsonCliente);
//				console.log(jsonCliente.regionCliente.idRegion);
			}
			
			/*
			 * Consultar D-1 y D+1 Instructores
			 */
			var regionInstructor;
			var regionCliente = jsonCliente.regionCliente.idRegion;
			var instructoresDmin1 = new Array();
			var instructoresDmas1 = new Array();
			for(i in arrayInstructores){
				regionInstructor = arrayInstructores[i].regionInstructor.idRegion;
				if(validaDmin1(asignaFecha, regionCliente, regionInstructor)){
					instructoresDmin1.push(arrayInstructores[i]);
				}
			}
			console.log(instructoresDmin1);
			for(e in instructoresDmin1){
				regionInstructor = instructoresDmin1[e].regionInstructor.idRegion;
				if(validaDmas1(asignaFecha, regionCliente, regionInstructor)){
					instructoresDmas1.push(instructoresDmin1[e]);
				}
			}
			console.log(instructoresDmas1);
			
		}else{
			//si debe de validar que por lo menos ese dia no tengan curso
			arrayInstructores = validarFechaSeleccionada(arrayInstructores, asignaFecha);
			for(i in arrayInstructores){
				$('#idInstructor').append('<option value="'+arrayInstructores[i].idInstructor+'">'+arrayInstructores[i].nombreInstructor+'</option>');
			}
		}
		
	
	}  // fin metodo validaCurso
	
	function validaDmin1(asignaFecha, regionCliente, regionInstructor){
		return true
	}
	
	function validarFechaSeleccionada(arrayInstructores, asignaFecha){
		return arrayInstructores;
	}
	
	function validaDmas1(asignaFecha, regionCliente, regionInstructor){
		return true
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * ValidaINSTRUCTOR
	 */
	function validaInstructor(){
		console.log("asignaInstructor:"+ $('#asignaInstructor').val());
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
		
	
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
/*
 * UTILIDADES
 */
	
   	function alertaFade(alerta){
   		
		$(alerta).insertAfter($('.alerta_in'));
		  $('.alerta').fadeIn();
		  $('.alerta').fadeOut( 4000);
		  
			$('#alertaFecha').remove();
			$('#alertaCliente').remove();
			$('#alertaCurso').remove();

	
	}
























//   FIN  JScript