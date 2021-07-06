$(document).ready(function() {
	
	// -  console.log(modulos);
	// -  console.log(moduloCurso);

	var moduloSel ;
	$moduloSel="";
	$elementosFinal="";
	$idCurso="";
	$moduloNombre="";
	$cursoNombre="";
	var arrayCursosFinal = new Array();
	$val = val;
	// -  console.log("modulos ready");
	$('#seccionOperacion').hide();
	$('#moduloAltaForm').hide();
	$('#moduloConfiguracionForm').hide();
	$('#moduloConsultaForm').hide();
	
	$('#moduloAlta').click(function(){
		// -  console.log("moduloAlta");
		$(this).addClass("activeItem");
		$('#moduloConfiguracion').removeClass("activeItem");
		$('#moduloConsulta').removeClass("activeItem");
		
		$('#seccionOperacion').empty();
		$('#moduloAltaForm').show();
		$('#moduloConfiguracionForm').hide();
		$('#moduloConsultaForm').hide();
		
		// dar de alta el encabezado en #seccionOperacion
		var seccionOperacion = '<fieldset><legend class="bienvenido">FORMULARIO ALTA MÓDULO DIDÁCTICO</legend><div class="form-group"><label for="exampleSelect1" class="nombre">CAPTURAR LOS CAMPOS REQUERIDOS PARA SU MEJOR APROVECHAMIENTO</label></div></fieldset>';
		$('#seccionOperacion').append(seccionOperacion);
		$('#seccionOperacion').show();
		
	});
	
	
	$('#moduloConfiguracion').click(function(){
		// -  console.log("moduloConfiguracion");
		$(this).addClass("activeItem");
		$('#moduloAlta').removeClass("activeItem");
		$('#moduloConsulta').removeClass("activeItem");
		$('#seccionOperacion').empty();
		$('#moduloAltaForm').hide();		
		$('#moduloConsultaForm').hide();
		
		// dar de alta el encabezado en #seccionOperacion
		var seccionOperacion = '<fieldset><legend class="bienvenido">FORMULARIO CONFIGURACIÓN MODULO DIDÁCTICO</legend><div class="form-group"><label for="exampleSelect1" class="nombre">SELECCIONAR MODULO DIDACTICO</label><select class="elementoInput form-control" id="selModulo"><option value="" selected>Seleciona Modulo Didáctico</option></select></div></fieldset>';
		$('#seccionOperacion').append(seccionOperacion);
		$('#seccionOperacion').show();
		for(i in modulos){
			var modulo = modulos[i];
			var option = "<option value='"+modulo.idModuloDidactico+"'>"+modulo.moduloDidacticoNombre+"</option>";
			$('#selModulo').append(option);
		}
		var idModuloSel ;
		
		
		$('#selModulo').change(function(){
			$arrayElementosTotal.length = 0;
			$('#cursosAsignados').empty();
			$('#selectCursosNoAsignados').empty();
			
			idModuloSel = $('#selModulo').val();
			$moduloNombre= $('#selModulo option:selected').text();
			for(i in modulos){
				var modulo = modulos[i];
				if((idModuloSel * 1) === (modulo.idModuloDidactico * 1)){
					moduloSel = modulo; 
					$moduloSel = modulo;
				}
			}
			$('#selectCursosNoAsignados').append('<option value="" selected>Selecciona Curso</option>');
			var arrayCursos = stringToArray(moduloSel.moduloDidacticoCursos);
			var arrayCursosNoAsignados = new Array();
			$arrayElementosTotal.length = 0;
			for(i in arrayCursos){
				for(e in cursos){
					if((arrayCursos[i] * 1) === (cursos[e].idCurso * 1)){
//						// -  console.log("cursoAsignado");
						arrayCursosFinal.push(arrayCursos[i]*1);
						var alertCursosAsignados = '<div class="alert alert-dismissible alert-success">\
							  <button type="button" class="close" data-dismiss="alert">&times;</button>\
							  <strong><a href="" data-toggle="modal" data-target="#modalConfiguraModulo" onclick=clickConfigurarModulo(this)>'+cursos[e].nombreCurso+'</a></strong>\
							  </div>';
						$('#cursosAsignados').append(alertCursosAsignados);
						$idCurso = cursos[e].idCurso
					}
				}
			}
			for(e in cursos){
				if(!arrayCursosFinal.includes(cursos[e].idCurso)){
					var option = "<option value='"+ cursos[e].idCurso +"'>"+ cursos[e].nombreCurso +"</option>";
					$('#selectCursosNoAsignados').append(option);
					$('#selectCursosNoAsignados').selectpicker('refresh');
				}
			}
//			
			$('#moduloConfiguracionForm').show();
		});
		
		
		$('#btnConfiguraModulo').click(function(){
			// -  console.log("clickConfigurarModulo2");
			$('#listaNuevosAsignacion').empty();
			$('#divCarrusel').empty();
			$('#nombreModulo').html(moduloSel.moduloDidacticoNombre);
			$('#nombreCurso').html($('#selectCursosNoAsignados option:selected').text());
			$cursoNombre = $('#selectCursosNoAsignados option:selected').text();
			
			$('#descripcionModulo').html(moduloSel.moduloDidacticoDescripcion);
			var tmp = $('#selectCursosNoAsignados').val();
			$idCurso = tmp;
			arrayCursosFinal.push(tmp);
			$arrayElementosTotal.length = 0;
		})
		
		
		var arrayElementos = new Array();
//		$('#btnAgregarElemento').click(function(){
//			 var elementoA = $('#elementoA').val();
//			 var elementoB = $('#elementoB').val();
//			 var elemento = {
//					'elementoA':elementoA,
//					'elementoB':elementoB
//			 } 
//			arrayElementos.push(elemento);	
//			 var alertCursosAsignados = '<div class="alert alert-dismissible alert-success">\
//				  <button type="button" class="close" data-dismiss="alert">&times;</button>\
//				  <strong>'+elemento.elementoA +'</strong> : '+ elemento.elementoB +'\
//				  </div>';
//			 $('#listaNuevosAsignacion').append(alertCursosAsignados);
//			 $('#elementoA').val("");
//			 $('#elementoB').val("");
//		});
		
		$('#btnAgregarConfiguracion').click(function(){
			// -  console.log($moduloNombre);
			// -  console.log($cursoNombre);
//			// -  console.log($idCurso);
			var finalJson ="";
			finalJson = {
					elementos : arrayToString($elementosFinal),
					modulo : $moduloSel.idModuloDidactico.toString(),
					idCurso : $idCurso.toString(),
					val : $val.toString(),
					arrayCursos : arrayToStringCursos(arrayCursosFinal),
					moduloNombre : $moduloNombre,
					cursoNombre : $cursoNombre
			}
			enviaDataEdicion(finalJson);
		});
	});
	
	
	
	$('#moduloConsulta').click(function(){
		// -  console.log("moduloConsulta1");
		$(this).addClass("activeItem");
		$('#moduloConfiguracion').removeClass("activeItem");
		$('#moduloAlta').removeClass("activeItem");
		
		$('#seccionOperacion').empty();
		$('#moduloAltaForm').hide();
		$('#moduloConfiguracionForm').hide();
//		
		
		// dar de alta el encabezado en #seccionOperacion
		var seccionOperacion = '<fieldset><legend class="bienvenido">CONSULTA GENERAR MODULO DIDÁCTICO</legend><div class="form-group"><label for="exampleSelect1" class="nombre">SELECCIONA UN MODULO EN PARTICULAR PARA CONSULTA MODULO-CURSOS</label></div></fieldset>';
		$('#seccionOperacion').append(seccionOperacion);
		$('#seccionOperacion').show();
		$('#moduloConsultaForm').show();
		
	});
//	// -  console.log("b");
	$('#selectCursosNoAsignados').selectpicker();
	$('#selectCursosNoAsignados').selectpicker('refresh');
    // fin de documento
})

function imagenCliente(archivosCampo, rfcCliente){
//	  var $idCliente = rfcCliente
//	  $idCliente = $(rfcCliente).val();
	var date = new Date();
	var idImagen = $('#moduloDidacticoNombre').val();
	var array = idImagen.split(" ");
	idImagen = array[0];
	if(idImagen.length<5){
		var indice = 5;
		indice = indice - idImagen.length;
		for(i = 0 ; i < indice; i++){
			idImagen = idImagen +"0";
		}
	}
//	// -  console.log(idImagen)
	idImagen = idImagen + date.getHours() + date.getMinutes() + date.getSeconds();
//	// -  console.log(idImagen)
	$('#moduloDidacticoIdImagen').val(idImagen);
	        var files = archivosCampo.files;
	        for (var i = 0; i < files.length; i++) {           
	            var file = files[i];
	            enviaImagen(archivosCampo, idImagen);
	            }
//	        // -  console.log(archivosCampo.file.name);
//	        // -  console.log($(archivosCampo).val());
	 }

function enviaImagen(idImagenForm, rfcCliente){
	
	limpiaAlerta();
		// -  console.log("Comineza envio idCliente:"+rfcCliente);
		var alerta="";
		 var form = $('#altaModulo')[0]; //$('#formImagenLogoCliente').attr('files'),
        var data = new FormData(form);
        // -  console.log(data);
		  $.ajax({
			url: "imageUpload/"+rfcCliente,
		    type: "POST",
		    data: new FormData($("#altaModulo")[0]),
		    enctype: 'multipart/form-data',
		    processData: false,
		    contentType: false,
		    cache: false,
		    success: 	function(data){
		    	if(data.codigo===0){
		    		if(data.codigo===0){
		  			  alerta="<div class='alert alert-success' role='alert'>imagen : 0 - Exito carga</div>";
		  			  $(alerta).insertAfter($('.alerta'));
		  			  // -  console.log("envio ok");
		  	    	}else{
		  	    		alerta="<div class='alert alert-warning' role='alert'>imagen : "+data.codigo+"-"+data.mensaje.toString()+"</div>";
		  				  $(alerta).insertAfter($('.alerta'));
		  	    		// -  console.log("envio Nok");
		  	    	}
		    	  } 
		    	},
		    error: function () {
		    	alerta="<div class='alert alert-danger' role='alert'>error de carga de imagen</div>";
				  $(alerta).insertAfter($('.alerta'));
		  	// -  console.log("envio error");
		    }
		  });
}

function enviaDataEdicion(finalJson){
	limpiaAlerta();
	// -  console.log(finalJson);
	$.ajax({
	   	  url: "edicionSeccion/",//+ context,//+finalJson.action+"/"+finalJson[1],
	      dataType: 'json',
		  type: 'POST',
		  contentType: "application/json",
		  data: JSON.stringify(finalJson),
		  headers: {   'Access-Control-Allow-Methods': 'POST', 'Access-Control-Allow-Headers': 'X-PINGOTHER' },
		  crossDomain: true,
		  success: 	function(data){
			  window.location = '/modulos?ejecucionI=true'; //			  window.location.reload();
			  avisaAlerta(data)
			},
		  error: function(){
			  data = {
					  codigo : "99",
					  mensaje : "Error al actualizar Modulo"
			  }
			  avisaAlerta(data);
		  }
		});
}

function limpiaAlerta(){
	$( "div" ).remove( "#limpiaAlerta" );
}

function avisaAlerta(data){
	limpiaAlerta();
	// -  console.log(data)
	 if(data.codigo===200){
		 modalClose();
//		 $("#alerta").click();
		 alerta="<div id='limpiaAlerta' class='alert alert-success' role='alert'>"+data.codigo+" "+data.mensaje.toString()+"</div>";
		 alertaFade(alerta); 
	 }else{
		 modalClose();
		  alerta="<div id='limpiaAlerta' class='alert alert-warning' role='alert'>"+data.codigo+" "+data.mensaje.toString()+"</div>";
		  alertaFade(alerta); 
	  }
}

function alertaFade(alerta){
	$(alerta).insertAfter($('.alerta_Edicion'));
	  $('.alertaEdicion').fadeIn();
//	  $('.alerta').delay(5000).fadeOut();
	  $('.alertaEdicion').fadeOut( 8000);
//	 $('.alerta').hide( "drop", { direction: "down" }, "slow" );
}

function modalClose(){
	 $("#modalConfiguraModulo .close").click();
	 
	 $("body,html").animate({
	        scrollTop: 0
	    }, 600);

}

function stringToArray(str){
	var array = new Array();
	if(str){
		var tmp = str.split(","); 
		for(i in tmp){
			array.push(tmp[i]);
		}
		array = array.sort(function(a, b){return a-b});
		// -  console.log(array);
	}
	return array;
	
}

function arrayToStringCursos(arrayData){
	var tmp="";
	for(a in arrayData){
		tmp = tmp + arrayData[a]+","; 
	}
	tmp =  tmp.slice(0, (tmp.length-1));
	return tmp;
}

function clickConfigurarModulo(elemento){
//	$('#btnConfiguraModulo').trigger("click");
//	// -  console.log("clickConfigurarModuloEdicion");
	var cursoNombre = $(elemento).text();
	$('#listaNuevosAsignacion').empty();
	$('#nombreModulo').html($moduloSel.moduloDidacticoNombre);
	$('#nombreCurso').html(cursoNombre);
	$('#descripcionModulo').html($moduloSel.moduloDidacticoDescripcion);
	$moduloNombre = $moduloSel.moduloDidacticoNombre;
	$cursoNombre = cursoNombre;
	var idCurso="";
	for(a in cursos){
		if(cursoNombre === cursos[a].nombreCurso){
			idCurso = cursos[a].idCurso;
			$idCurso = idCurso;
		}
	}
	var modCurso = new Array();
	for(e in moduloCurso){
		var tmp = moduloCurso[e];
		if(($moduloSel.idModuloDidactico*1) === moduloCurso[e].idModuloDidactico){
			modCurso.push(moduloCurso[e]);
		}
	}
	var elementos="";
	for(i in modCurso){
		if((idCurso*1) === (modCurso[i].idCurso*1)){
			elementos = modCurso[i].elementos;
		}
	}
	
//	switch($moduloSel.idModuloDidactico*1){
//	case 6:
//		transformaElementosMemoria(elementos);
//		break;
//	case 7:
//		transformaElementosMemoria(elementos);
//		break;
//	
//	}
	transformaElementosMemoria(elementos);
// -  console.log(elementos);
}