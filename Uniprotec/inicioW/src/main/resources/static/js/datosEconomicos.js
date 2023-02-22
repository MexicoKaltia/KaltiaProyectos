/**
 * Archivo de control JS para Datos Economicos 
 */

$(document).ready(function() {

	console.log(datosEconomicos);
	console.log(vendedoresDatosEconomicos);
	 $('#ventaReal').focus();	
	
	
		
			
}); // Fin Jquery

/////////////////////////////////////////////////////////////////////
/*
 * funciones de datos economicos
 */	
$("#btnActualizarDatosEconomicos").click(function(){
	var finalVendedores = new Array();
	for(var a in vendedoresArray){
		var vendedorInicial = vendedoresArray[a];
		var vendedorFinal = {
				idVendedorDE : null,
				idVendedorAsignacion : vendedorInicial.idVendedorAsignacion,
				idAsignacion : idAsignacionClic,
				idDatosEconomicos : null,
				nombreVendedor : vendedorInicial.nombreVendedor,
				comisionRealVendedor : $('#comisionReal'+vendedorInicial.idVendedorAsignacion).val(),
				porcentajeComisionVendedor : $('#porcentajeComision'+vendedorInicial.idVendedorAsignacion).val(),
				listAsignaciones : vendedorInicial.listAsignaciones
		}
		finalVendedores.push(JSON.stringify(vendedorFinal));
	}
	$('#vendedoresStr').val(finalVendedores);
	
});
		
	var vendedoresArray =  new Array();
	var idAsignacionClic;
	var flagExistDatosEconomicos = false;
	
	function porcentajeVendedor(idAsignacion){
		console.log("Nuevo Expediente Datos Economicos");
		idAsignacionClic = idAsignacion;
		flagExistDatosEconomicos = false;
		vendedoresArray.length = 0;
		var idUsuario = 0;
		for(var i in asignaciones){
			var asignacion = asignaciones[i];
			if((asignacion.idAsignacion*1) === (idAsignacion)){
				idUsuario = asignacion.userCreateAsignacion;
				break;
			}
		}
		var idVendedor= 0;
		for(var e in vendedores){
			var vendedorInicial = vendedores[e];
			if(vendedorInicial.usuarioVendedor.idUsuario == idUsuario){
				var vendedor = getVendedorFormato(vendedorInicial);
				vendedoresArray.push(vendedor);
				cargaVendedoresForm(vendedoresArray);
				break;
			}	
		}
		formatoVentaReal();
		$('#ventaReal').focus();
	}
	
	function cargaVendedoresDE(idAsignacion){
		console.log("Existe el expediente datos Economicos");
		idAsignacionClic = idAsignacion;
		flagExistDatosEconomicos = true;
		vendedoresArray.length = 0;	
		var idUsuario = 0;
		for(var i in asignaciones){
			var asignacion = asignaciones[i];
			if((asignacion.idAsignacion*1) === (idAsignacion)){
				idUsuario = asignacion.userCreateAsignacion;
				$('#prospectoClienteTexto').val(asignacion.clienteAsignacion);
				$('#prospectoCliente').val(asignacion.idClienteAsignacion);
				$('#numFactura').val(asignacion.numeroFactura);
				break;
			}
		}
		for(var e in vendedoresDatosEconomicos){
			var vendedor = vendedoresDatosEconomicos[e];
			if(vendedor.idAsignacion != null){			
				if(vendedor.idAsignacion == idAsignacion){
					$('#idAsignacionDatosEconomicos').val(idAsignacionClic);
					vendedoresArray.push(vendedor);
				}
			}else{
				var arrayAsignaciones = stringToList(vendedor.listAsignaciones);
				for(var e in arrayAsignaciones){
					var asignacion = arrayAsignaciones[e]; 
					if(asignacion*1 === idAsignacion*1){
						vendedoresArray.push(vendedor);
					}
				}
			}
		}
		cargaVendedoresForm(vendedoresArray);
		formatoVentaReal();
		$('#ventaReal').focus();
	}
	
	
	$("#btnVendedoresComision").click(function(){
		console.log("Agregar VendedoresDE");
//		$("#divAgregarVendedores").empty();
//		$("#divVendedorMain").empty();	
		vendedoresArray.length = 1;
		$('.checkboxFiltroV:checked').each(function(){
			if($(this).attr('checked',true)){
				var idVendedor= $(this).attr('id')
				for(var a in vendedores){
					var vendedorInicial = vendedores[a];
					if(idVendedor*1 === vendedorInicial.idVendedor*1){
						var vendedor = getVendedorFormato(vendedorInicial);
						vendedoresArray.push(vendedor);
					}
				}
			}
		});
		cargaVendedoresForm(vendedoresArray);
	});
	
	
	function formatoVentaReal(){
		$('#labelVentaReal').text(formatter.format($('#ventaReal').val()));
		var ventaR = $('#ventaReal').val();
		for(var a in vendedoresArray){
			var vendedor = vendedoresArray[a];
			var comisionV = $('#porcentajeComision'+vendedor.idVendedorAsignacion).val();
			var comisionR = (ventaR * comisionV)/100;
			$('#comisionReal'+vendedor.idVendedorAsignacion).val(comisionR);
			formatoPorcentajeComision(vendedor.idVendedorAsignacion);
			formatoComisionReal(vendedor.idVendedorAsignacion);
		}
		
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

	
	function formatoPorcentajeComision(idVendedor){
		var idAAA;
		if(Number.isInteger(idVendedor)){
			$('#labelPorcentajeComision'+idVendedor).text(formatoPorcentaje($('#porcentajeComision'+idVendedor).val()));
			idAAA = idVendedor;
		}
		
		var ventaR = $('#ventaReal').val();
		var comisionV = $('#porcentajeComision'+idAAA).val();
		var comisionR = (ventaR * comisionV)/100;
		var formato = formatter.format(comisionR);
		$('#comisionReal'+idAAA).val(comisionR);
		$('#labelComisionReal'+idAAA).text(formato);
		if(ventaR > 0 && comisionV > 0 && comisionR > 0){
			$('#btnActualizarDatosEconomicos').attr("disabled", false);
		}else{
			$('#btnActualizarDatosEconomicos').attr("disabled", true);
		}
	}
	

	function formatoComisionReal(idVendedor){
		var idAAA;
		if(Number.isInteger(idVendedor)){
			idAAA = idVendedor;
		}
		
		var formato = $('#comisionReal'+idAAA).val();
		formato = formatter.format(formato);
		$('#labelComisionReal'+idAAA).text(formato);
		
		var ventaR = $('#ventaReal').val();
		var comisionV = $('#porcentajeComision'+idAAA).val();
		var porcentajeVenta = $('#porcentajeVenta').val();
		if(ventaR > 0 && comisionV > 0 && porcentajeVenta > 0){
			$('#btnActualizarDatosEconomicos').attr("disabled", false);
		}else{
			$('#btnActualizarDatosEconomicos').attr("disabled", true);
		}
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
	
	function getVendedorFormato(vendedorInicial){
		var vendedor = {
				idVendedorDE : null,
				idVendedorAsignacion : vendedorInicial.idVendedor,
				idAsignacion : idAsignacion,
				idDatosEconomicos : null,
				nombreVendedor : vendedorInicial.nombreVendedor,
				comisionRealVendedor : 0,
				porcentajeComisionVendedor : vendedorInicial.porcentajeVendedor
		}
		return vendedor;
	}	

		function cargaVendedoresForm(vendedoresArray){
			$("#divAgregarVendedores").empty();
			for(var a in vendedoresArray){
				var vendedor = vendedoresArray[a];
				var registroNuevoVendedor =
					'<div class="col-md-6 mb-3">\
		            <label for="validationCustom02">Porcentaje de Comisión Vendedor</label><div class="widget-numbers mt-0 fsize-2 text-primary pull-right"><label id="labelPorcentajeComision'+vendedor.idVendedorAsignacion+'" ></label></div>\
		            <label id="nombreVendedorComision'+vendedor.idVendedorAsignacion+'" class="text-primary">'+vendedor.nombreVendedor+'</label>\
		            <input type="text" class="form-control"  id="porcentajeComision'+vendedor.idVendedorAsignacion+'" placeholder="porcentaje comision" value="'+vendedor.porcentajeComisionVendedor+'" minlength="1" maxlength="6" onfocusout="formatoPorcentajeComision('+vendedor.idVendedorAsignacion+')" required>\
		            <div class="invalid-feedback">\
		                Capturar Datos Númericos\
		            </div>\
		        </div>\
		         <div class="col-md-6 mb-3">\
		            <label for="validationCustom02">Comisión Real</label><div class="widget-numbers mt-0 fsize-2 text-primary pull-right"><label id="labelComisionReal'+vendedor.idVendedorAsignacion+'" ></label></div>\
		            <input type="text" class="form-control"  id="comisionReal'+vendedor.idVendedorAsignacion+'" placeholder="comision real" value="" maxlength="100" onfocusout="formatoComisionReal('+vendedor.idVendedorAsignacion+')" required>\
		            <div class="invalid-feedback">\
		                Capturar Datos Númericos\
		            </div>\
		        </div>';
				$("#divAgregarVendedores").append(registroNuevoVendedor );
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
	
	function stringToList(cadena){
		return cadena.split(";");
	}
	
	function isEmpty(str) {
	    return (str || 0 !== str.length);
	}