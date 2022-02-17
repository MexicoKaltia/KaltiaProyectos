
   	
$(document).ready(function(){

	console.log(preAsignacion);
	
	$.viaticosTotal = 0;
	
	$('#formAECurso').text(preAsignacion.cursoAsignacion);
	$('#formAEEmpresa').text(preAsignacion.clienteAsignacion);
	$('#formAEHorasEfectivas').text(horasEfectivas(preAsignacion.horarioAsignacion));  $.horasEfectivas = $('#formAEHorasEfectivas').text(); 
	$('#formAESesiones').val();
	$('#formAEParticipantes').text(preAsignacion.participantesAsignacion);
	$('#formAEFechaCotizacion').text(hoy());
	$('#formAESede').text(preAsignacion.nombreRegionAsignacion);
	$('#formAENivelCurso').text(preAsignacion.nivelAsignacion);

	$('#formAENumInstructor').val($.horasEfectivas);
	$('#formAETotalHoras').text(totalHoras()); $.totalHoras = $('#formAETotalHoras').text();
	
//	$("#formAECostoHoraInstructor").keyup(function(){
//		var tmp = $("formAECostoHoraInstructor").val();
//		numeral(tmp);
//	});

	$.clickViatico = 0;
	$("#btnModalInstructorViaticos").click(function(){
//		console.log("#btnModalInstructorViaticos");
		$.clickViatico++;
		$.instructorViaticosTotal = $("#instructorViaticosTotal").text();
		$.viaticosTotal = ($.viaticosTotal*1) + ($.instructorViaticosTotal*1)
		calculaAEHoraInstructor();
		var addInstructor = '<li>\
		            <div class="widget-heading">Instructor '+$.clickViatico+'</div>\
					<div class="widget-subheading">Viáticos </div>\
		                <div class="widget-numbers mt-0 fsize-2 text-primary"><label>$'+$.instructorViaticosTotal+'</label></div>\
		            </li>'
		$("#addInstructor").append(addInstructor);
		
		$('#instructorViaticosTCC').val("");
		$('#instructorViaticosTCE').val("");
		$('#instructorViaticosTEC').val("");
		$('#instructorViaticosTENumero').val("");
		$('#instructorViaticosTE').val("");
		$('#instructorViaticosCGNumero').val("");
		$('#instructorViaticosCG').val("");
		$('#instructorViaticosANumero').val("");
		$('#instructorViaticosA').val("");
		$('#instructorViaticosCNumero').val("");
		$('#instructorViaticosC').val("");
		$('#instructorViaticosHNumero').val("");
		$('#instructorViaticosH').val("");
		$('#instructorViaticosCV').val("");
		$('#instructorViaticosGE').val("");
		
		
	})
	
	
	
});  // fin de documento JQuery
	

	/****************************************************************************************************************
	 * Formulario Datos Generales Evento
	 */

	function horasEfectivas(str){
		var arry = str.split(";");
		var hrs = arry[4].split(":");
		return (hrs[0]*1)
	}
	
	function hoy(){
		var hoy = new Date();
		var dd = String(hoy.getDate()).padStart(2, '0');
		var mm = String(hoy.getMonth() + 1).padStart(2, '0'); //January is 0!
		var yyyy = hoy.getFullYear();

		hoy = dd + '/' + mm + '/' + yyyy;
		return hoy;
	}
	
	
	/****************************************************************************************************************
	 * Formulario Imparticion del Curso
	 */

	function totalHoras(){
		return $.horasEfectivas;
	}
	
	function totalImparticion(){
		return financial($.totalHoras * $.costoHoraInstructor);
	}
	
	function sumaViaticosTotal(){
//		return financial(1617*1);
	}
	
	
	/****************************************************************************************************************
	 * Formulario Recomendacion
	 */
	
	function sumaImparticionViaticos(){
		return financial($.totalImparticion*1 + $.viaticosTotal*1);
	}
	
	function costoCursoRecomendado(){
		return financial((100*$.sumaImparticionViaticos)/35);
	}
	
	function costoHoraRecomendada(){
		return financial(($.costoCursoRecomendado*1) / ($.horasEfectivas*1)); 
	}
	
	
	
	/****************************************************************************************************************
	 * Formulario Analisis Economico
	 */
	
	function imparticionPorcentaje(){
		return financial(($.imparticionTotal*1)/($.costoCursoRecomendado*1)*100);
	}
	
	function comisionVendedorPorcentaje(){
		return 10*1;
	}
	
	function comisionVendedor(){
//		console.log($.comisionVendedorPorcentaje);
//		console.log($.costoCursoRecomendado);		
		return financial($.costoCursoRecomendado*($.comisionVendedorPorcentaje/100));
	}
	
	function viaticosPorcentaje(){
		return financial(100*($.viaticosTotal*1)/$.costoCursoRecomendado);
	}
	
	function gastosFijosPorcentaje(){
		return 25*1;
	}
	
	function gastosFijos(){
		return financial($.costoCursoRecomendado*($.gastosFijosPorcentaje/100));
	}
	
	function gananciaCursoPorcentaje(){
//		console.log($.imparticionPorcentaje*1);
//		console.log($.comisionVendedorPorcentaje*1);
//		console.log($.viaticosPorcentaje*1);
//		console.log($.gastosFijosPorcentaje*1);
		return financial(100-($.imparticionPorcentaje*1+$.comisionVendedorPorcentaje*1+$.viaticosPorcentaje*1+$.gastosFijosPorcentaje*1));
	}
	
	function gananciaCurso(){
		return financial($.costoCursoRecomendado*($.gananciaCursoPorcentaje/100));
	}
	
	function totalesPorcentaje(){
		return financial($.imparticionPorcentaje*1 + $.comisionVendedorPorcentaje*1 + $.viaticosPorcentaje*1 + $.gastosFijosPorcentaje*1 + $.gananciaCursoPorcentaje*1);
	}
	
	function totales(){
		return financial($.imparticionTotal*1 + $.comisionVendedor*1 + $.viaticosTotal*1 + $.gastosFijos*1 + $.gananciaCurso*1);
	}
	
	function comisionVendedorReal(){
		return financial($.precioVentaReal*0.1);
	}
	
	function gastosFijosReal(){
		return financial(($.precioVentaReal*1)*($.gastosFijosPorcentaje*1/100));
	}
	
	function utilidadReal(){
		return financial($.precioVentaReal*1 - $.comisionVendedorReal*1 - $.imparticionTotal - $.viaticosTotal*1 - $.gastosFijosReal*1);
	}
	
	function regla3PorcentajeNuevaComision(){
		return financial(100*($.utilidadReal*1)/($.precioVentaReal*1));
	}
	
	function regla3PorcentajeNuevaComisionReal(){
		return financial(100*($.regla3PorcentajeNuevaComision*1)/($.gananciaCursoPorcentaje*1));
	}
	
	function nuevaComisionReal(){
		return financial($.regla3PorcentajeNuevaComisionReal*$.comisionVendedorReal/100);
	}
	
	/*
	 * funciones private
	 */
	function financial(x) {
		  return Math.round(Number.parseFloat(x).toFixed(2));
		}
	
	/*
	 * funciones en html
	 */
	
	function calculaAEHoraInstructor(){
		$.costoHoraInstructor = $('#formAECostoHoraInstructor').val(); 	$('#formAECostoHoraInstructor').val(financial($.costoHoraInstructor));
		$('#formAETotalImparticion').text(totalImparticion()); $.totalImparticion = $('#formAETotalImparticion').text();     
		$('#formAEViaticosTotal').text($.viaticosTotal); $.viaticosTotal = $('#formAEViaticosTotal').text(); 

		$('#formAESumaImparticionViaticos').text(sumaImparticionViaticos()); $.sumaImparticionViaticos = $('#formAESumaImparticionViaticos').text();
		$('#formAECostoCursoRecomendado').text(costoCursoRecomendado()); $.costoCursoRecomendado = $('#formAECostoCursoRecomendado').text(); 
		$('#formAECostoHoraRecomendada').text(costoHoraRecomendada()); $.costoHoraRecomendada = $('#formAECostoHoraRecomendada').text();

		$('#formAEImparticion').text($.totalImparticion); $.imparticionTotal = $('#formAEImparticion').text();
		$('#formAEImparticionPorcentaje').text(imparticionPorcentaje()); $.imparticionPorcentaje = $('#formAEImparticionPorcentaje').text();
		$('#formAEComisionVendedorPorcentaje').text(comisionVendedorPorcentaje()); $.comisionVendedorPorcentaje = $('#formAEComisionVendedorPorcentaje').text(); 
		$('#formAEComisionVendedor').text(comisionVendedor()); $.comisionVendedor = $('#formAEComisionVendedor').text();  
		$('#formAEViaticosPorcentaje').html(viaticosPorcentaje()); $.viaticosPorcentaje = $('#formAEViaticosPorcentaje').text();
		$('#formAEViaticos').text($.viaticosTotal); 
		$('#formAEGastosFijosPorcentaje').html(gastosFijosPorcentaje()); $.gastosFijosPorcentaje = $('#formAEGastosFijosPorcentaje').text();
		$('#formAEGastosFijos').text(gastosFijos()); $.gastosFijos = $('#formAEGastosFijos').text();
		$('#formAEGananciaCursoPorcentaje').html(gananciaCursoPorcentaje()); $.gananciaCursoPorcentaje = $('#formAEGananciaCursoPorcentaje').text();
		$('#formAEGananciaCurso').text(gananciaCurso()); $.gananciaCurso = $('#formAEGananciaCurso').text();
		$('#formAETotalesPorcentaje').html(totalesPorcentaje()); $.totalesPorcentaje = $('#formAETotalesPorcentaje').text();
		$('#formAETotales').text(totales()); $.totales = $('#formAETotales').text();
		
		$('#formAEPrecioVentaReal').val(financial($.totales));
		
		$('#formAEidPreAsignacion').val(preAsignacion.idAsignacion);
		$('#formAEidPreAsignacionLogica').val(preAsignacion.idAsignacionLogica);
		$('#btnFormAESubmit').attr("disabled", false);
		
		calculaPrecioVentaReal();
	}
	
	function calculaPrecioVentaReal(){
		$.precioVentaReal = $('#formAEPrecioVentaReal').val(); $('#formAEPrecioVentaReal').val(financial($.precioVentaReal)); 
		$('#formAEComisionVendedorReal').text(comisionVendedorReal()); $.comisionVendedorReal = $('#formAEComisionVendedorReal').text();
		$('#formAEGastosFijosReal').text(gastosFijosReal()); $.gastosFijosReal = $('#formAEGastosFijosReal').text();
		$('#formAEUtilidadReal').text(utilidadReal());  $.utilidadReal = $('#formAEUtilidadReal').text();
		
		$('#formAERegla3PorcentajeNuevaComision').text(regla3PorcentajeNuevaComision()); $.regla3PorcentajeNuevaComision = $('#formAERegla3PorcentajeNuevaComision').text(); $('#formAERegla3PorcentajeNuevaComision').text($.regla3PorcentajeNuevaComision+"%");
		$('#formAERegla3PorcentajeNuevaComisionReal').text(regla3PorcentajeNuevaComisionReal()); $.regla3PorcentajeNuevaComisionReal = $('#formAERegla3PorcentajeNuevaComisionReal').text(); $('#formAERegla3PorcentajeNuevaComisionReal').text($.regla3PorcentajeNuevaComisionReal+"%");
		
		$('#formAENuevaComisionReal').text(nuevaComisionReal()); $.nuevaComisionReal = $('#formAENuevaComisionReal').text();
		
		$('#formAERegla3PorcentajeNuevaComisionRealDiv').attr("aria-valuenow", $.regla3PorcentajeNuevaComisionReal);
		$('#formAERegla3PorcentajeNuevaComisionRealDiv').css("width", $.regla3PorcentajeNuevaComisionReal+"%");
		$('#formAERegla3PorcentajeNuevaComisionDiv').attr("aria-valuenow", $.regla3PorcentajeNuevaComision);
		$('#formAERegla3PorcentajeNuevaComisionDiv').css("width", $.regla3PorcentajeNuevaComision+"%");
		
		if($.regla3PorcentajeNuevaComisionReal < 100){
			$('#formAERegla3PorcentajeNuevaComisionRealDiv').removeClass("bg-success");
			$('#formAERegla3PorcentajeNuevaComisionRealDiv').addClass("bg-danger");
			$('#formAERegla3PorcentajeNuevaComisionDiv').removeClass("bg-info");
			$('#formAERegla3PorcentajeNuevaComisionDiv').addClass("bg-danger");
			
			$('#formAERegla3PorcentajeNuevaComisionReal').removeClass("text-success");
			$('#formAERegla3PorcentajeNuevaComisionReal').addClass("text-danger");
			$('#formAERegla3PorcentajeNuevaComision').removeClass("text-info");
			$('#formAERegla3PorcentajeNuevaComision').addClass("text-danger");
			
			$('#formAENuevaComisionRealDiv').removeClass("text-success");
			$('#formAENuevaComisionRealDiv').addClass("text-danger");
			$('#formAETotalesDiv').removeClass("text-success");
			$('#formAETotalesDiv').addClass("text-danger");
			
		}else{
			$('#formAERegla3PorcentajeNuevaComisionRealDiv').removeClass("bg-danger");
			$('#formAERegla3PorcentajeNuevaComisionRealDiv').addClass("bg-success");
			$('#formAERegla3PorcentajeNuevaComisionDiv').removeClass("bg-danger");
			$('#formAERegla3PorcentajeNuevaComisionDiv').addClass("bg-info");
			
			$('#formAERegla3PorcentajeNuevaComisionReal').removeClass("text-danger");
			$('#formAERegla3PorcentajeNuevaComisionReal').addClass("text-success");
			$('#formAERegla3PorcentajeNuevaComision').removeClass("text-danger");
			$('#formAERegla3PorcentajeNuevaComision').addClass("text-info");
			
			$('#formAENuevaComisionRealDiv').removeClass("text-danger");
			$('#formAENuevaComisionRealDiv').addClass("text-success");
			$('#formAETotalesDiv').removeClass("text-danger");
			$('#formAETotalesDiv').addClass("text-success");
		}
	}
	
	function calculaInstructorViatico(){
		var instructorViaticosCSuma = ($('#instructorViaticosCNumero').val()*1)*(100);  $('#instructorViaticosC').val(instructorViaticosCSuma);
		var instructorViaticosHSuma = ($('#instructorViaticosHNumero').val()*1)*(1200); $('#instructorViaticosH').val(instructorViaticosHSuma);
		
		var instructorViaticosTCC = $('#instructorViaticosTCC').val();
		var instructorViaticosTCE = $('#instructorViaticosTCE').val();
		var instructorViaticosTEC = $('#instructorViaticosTEC').val();
		var instructorViaticosTENumero = $('#instructorViaticosTENumero').val();
		var instructorViaticosTE = $('#instructorViaticosTE').val();
		var instructorViaticosCGNumero = $('#instructorViaticosCGNumero').val();
		var instructorViaticosCG = $('#instructorViaticosCG').val();
		var instructorViaticosANumero = $('#instructorViaticosANumero').val();
		var instructorViaticosA = $('#instructorViaticosA').val();
		var instructorViaticosCNumero = $('#instructorViaticosCNumero').val();
		var instructorViaticosC = $('#instructorViaticosC').val();
		var instructorViaticosHNumero = $('#instructorViaticosHNumero').val();
		var instructorViaticosH = $('#instructorViaticosH').val();
		var instructorViaticosCV = $('#instructorViaticosCV').val();
		var instructorViaticosGE = $('#instructorViaticosGE').val();
		
		var instructorViaticosTELabel = instructorViaticosTE/instructorViaticosTENumero; $('#instructorViaticosTELabel').text("$"+instructorViaticosTELabel+"c/u");
		var instructorViaticosCGLabel = instructorViaticosCG/instructorViaticosCGNumero; $('#instructorViaticosCGLabel').text("$"+instructorViaticosCGLabel+"c/u");
		var instructorViaticosALabel = instructorViaticosA/instructorViaticosANumero; $('#instructorViaticosALabel').text("$"+instructorViaticosALabel+"c/u");
		var instructorViaticosCLabel = instructorViaticosC/instructorViaticosCNumero; $('#instructorViaticosCLabel').text("$"+instructorViaticosCLabel+"c/u");
		var instructorViaticosHLabel = instructorViaticosH/instructorViaticosHNumero; $('#instructorViaticosHLabel').text("$"+instructorViaticosHLabel+"c/u");
		
		$.instructorViaticosTotal = financial((instructorViaticosTCC*1) +
				(instructorViaticosTCE*1) + 
				(instructorViaticosTEC*1) +
				(instructorViaticosTE*1) +
				(instructorViaticosCG*1) +
				(instructorViaticosA*1) +
				(instructorViaticosCSuma*1) +
				(instructorViaticosHSuma*1) +
				(instructorViaticosCV*1) +
				(instructorViaticosGE*1));
		
			$('#instructorViaticosTotal').text($.instructorViaticosTotal);
	}
	
	
	
	//   FIN  JScript