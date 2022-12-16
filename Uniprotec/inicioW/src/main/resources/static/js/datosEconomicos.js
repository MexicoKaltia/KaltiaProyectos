/**
 * Archivo de control JS para Datos Economicos 
 */

$(document).ready(function() {

	console.log(datosEconomicos);
	console.log(idAsignacionClic);
	
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
//		console.log($('#fechaPromesaPagoFormat').val());
	}
	function formatoFechaConfirmacion(){
		var fechaConfirmacion = $('#fechaConfirmacion').val()
		$('#labelFechaConfirmacion').text(fechaConfirmacion);
		var elementoPicker = $datepicker1.pickadate('picker');	
		var asignaFecha = elementoPicker.get('select', 'mm/dd/yyyy');
		$('#fechaConfirmacionFormat').val(asignaFecha);
//		console.log($('#fechaPromesaPagoFormat').val());
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
	