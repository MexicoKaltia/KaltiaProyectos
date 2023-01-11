/**
 * Archivo de control JS para Datos Economicos 
 */

$(document).ready(function() {

	console.log(datosEconomicos);
//	console.log(idAsignacionClic);
	
	$("#btnActualizarDatosEconomicos").click(function(){
		//idAsignacionDatosEconomicos
	});
	
	
		
			
}); // Fin Jquery

/////////////////////////////////////////////////////////////////////
/*
 * funciones de datos economicos
 */	

	$("#btnDatosEconomicos").click(function(){
		console.log("btnDatosEconomicos");
	});
		
	$("#btnVendedoresComision").click(function(){
		console.log("btnVendedoresComision");
		$("#divAgregarVendedores").empty();
		var vendedoresArray =  new Array();
		$('.checkboxFiltroV:checked').each(function(){
			if($(this).attr('checked',true)){
				var idVendedor= $(this).attr('id')
				for(var a in vendedores){
					var vendedor = vendedores[a];
					if(idVendedor*1 === vendedor.idVendedor*1){
						vendedoresArray.push(vendedor);
					}
				}
			}
		});
		console.log(vendedoresArray);
		for(var e in vendedoresArray){
			var registroNuevoVendedor =
			'<div class="col-md-6 mb-3">\
            <label for="validationCustom02">Porcentaje de Comisión Vendedor</label><div class="widget-numbers mt-0 fsize-2 text-primary pull-right"><label id="labelPorcentajeComision" ></label></div>\
            <label id="nombreVendedorComision" class="text-primary"></label>\
            <input type="text" class="form-control" th:field="*{porcentajeComision}" id="porcentajeComision" placeholder="porcentaje comision" value="" minlength="1" maxlength="6" onfocusout="formatoPorcentajeComision()" required>\
            <div class="invalid-feedback">\
                Capturar Datos Númericos\
            </div>\
        </div>\
         <div class="col-md-6 mb-3">\
            <label for="validationCustom02">Comisión Real</label><div class="widget-numbers mt-0 fsize-2 text-primary pull-right"><label id="labelComisionReal" ></label></div>\
            <input type="text" class="form-control" th:field="*{comisionReal}" id="comisionReal" placeholder="comision real" value="" maxlength="100" onfocusout="formatoComisionReal()" required>\
            <div class="invalid-feedback">\
                Capturar Datos Númericos\
            </div>\
        </div>';
			$("#divAgregarVendedores").append(registroNuevoVendedor);
		}
	});

	
	function formatoVentaReal(){
		$('#labelVentaReal').text(formatter.format($('#ventaReal').val()));
		
		var ventaR = $('#ventaReal').val();
		var comisionV = $('#porcentajeComision').val();
		var comisionR = (ventaR * comisionV)/100;
		var formato = formatter.format(comisionR);
		$('#comisionReal').val(comisionR);
		$('#labelComisionReal').text(formato);
		
		formatoComisionReal();
		formatoPorcentajeComision();
		
		var viaticosT = $('#viaticosTotales').val();
		if(viaticosT <= 0){
			$('#viaticosTotales').val(0);
		}
	}
	function formatoPorcentajeVenta(){
		$('#labelPorcentajeVenta').text(formatoPorcentaje($('#porcentajeVenta').val()));
		var a = $('#porcentajeVenta').val();
		if(a*1 < 100){
			$('#divPorcentajeVenta').removeClass("text-success");
			$('#divPorcentajeVenta').addClass("text-danger");
		}else{
			$('#divPorcentajeVenta').removeClass("text-danger");
			$('#divPorcentajeVenta').addClass("text-success");
		}
	}
	function formatoComisionReal(){
		var formato = $('#comisionReal').val();
		formato = formatter.format(formato);
		$('#labelComisionReal').text(formato);
		
		var ventaR = $('#ventaReal').val();
		var comisionV = $('#porcentajeComision').val();
		var porcentajeVenta = $('#porcentajeVenta').val();
		if(ventaR > 0 && comisionV > 0 && porcentajeVenta > 0){
			$('#btnActualizarDatosEconomicos').attr("disabled", false);
		}else{
			$('#btnActualizarDatosEconomicos').attr("disabled", true);
		}
		
	}
	
	
	function formatoPorcentajeComision(){
		$('#labelPorcentajeComision').text(formatoPorcentaje($('#porcentajeComision').val()));
		formatoVentaReal();
	}
	function formatoViaticosTotales(){
		$('#labelViaticosTotales').text(formatter.format($('#viaticosTotales').val()));
	}
	function formatoFechaPromesaPago(){
		var fechaPromesaPago = $('#fechaPromesaPago').val()
		$('#labelFechaPromesaPago').text(fechaPromesaPago);
		var elementoPicker = $datepicker.pickadate('picker');	
		var asignaFecha = elementoPicker.get('select', 'mm/dd/yyyy');
		$('#fechaPromesaPagoFormat').val(asignaFecha);
		
		var fechasPromesa = new Array();
		var strLisFechaPromesa = $("#listFechaPromesaPago").val();
		if(isEmpty(strLisFechaPromesa)){
			console.log("listFechaPromesaPago");
			fechasPromesa = stringToList(strLisFechaPromesa);
			var fechasPromesaOrdenado = new Array();
			fechasPromesaOrdenado = fechasPromesa.sort(function(a, b){return new Date(a) - new Date(b)});//orderFecha(arreglo);
			fechasPromesaOrdenado.push(asignaFecha);
			$('#listFechaPromesaPago').val(fechasPromesaOrdenado);
		}else{
			fechasPromesa.push(asignaFecha);
			$('#listFechaPromesaPago').val(fechasPromesa);
		}
		
		
	}
	function formatoFechaConfirmacion(){
		var fechaConfirmacion = $('#fechaConfirmacion').val()
		$('#labelFechaConfirmacion').text(fechaConfirmacion);
		var elementoPicker = $datepicker1.pickadate('picker');	
		var asignaFecha = elementoPicker.get('select', 'mm/dd/yyyy');
		$('#fechaConfirmacionFormat').val(asignaFecha);

		var fechasConfirmacion = new Array();
		var strLisFechaConfirmacion = $("#listFechaConfirmacion").val();
		if(isEmpty(strLisFechaConfirmacion)){
			console.log("listFechaConfirmacion");
			fechasConfirmacion = stringToList(strLisFechaConfirmacion);
			var fechasConfirmacionOrdenado = new Array();
			fechasConfirmacionOrdenado = fechasConfirmacion.sort(function(a, b){return new Date(a) - new Date(b)});//orderFecha(arreglo);
			fechasConfirmacionOrdenado.push(asignaFecha);
			$('#listFechaConfirmacion').val(fechasConfirmacionOrdenado);
		}else{
			fechasConfirmacion.push(asignaFecha);
			$('#listFechaConfirmacion').val(fechasConfirmacion);
		}
		
		
	}
	
	const formatter = new Intl.NumberFormat('es-MX', {
	      style: 'currency',
	      currency: 'MXN',
	      minimumFractionDigits: 0
	    });
	
	function formatoPorcentaje(num){
		var s = Number(num/100).toLocaleString(undefined,{style: 'percent', minimumFractionDigits:2}); 
		return s; 
	}
	
	var idAsignacionClic;
	function porcentajeVendedor(idAsignacion){
		var idUsuario = 0;
		for(var i in asignaciones){
			var asignacion = asignaciones[i];
			if((asignacion.idAsignacion*1) === (idAsignacion)){
				idUsuario = asignacion.userCreateAsignacion;
				break;
			}
		}
		for(var e in vendedores){
			var vendedor = vendedores[e];
			if(vendedor.usuarioVendedor.idUsuario == idUsuario){
				$('#nombreVendedorComision').text(vendedor.nombreVendedor);
				return vendedor.porcentajeVendedor;
			}
		}
	}
	
	function stringToList(cadena){
		return cadena.split(";");
	}
	
	function isEmpty(str) {
	    return (str || 0 !== str.length);
	}
