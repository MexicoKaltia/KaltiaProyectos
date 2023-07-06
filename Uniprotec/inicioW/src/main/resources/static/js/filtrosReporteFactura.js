/**
 * Archivo de control JS para Modulo Calendario 
 */
var datoEconomicoSelec;


$(document).ready(function() {
	
		const identificadorUsuario = idUsuario;

	var filtroVendedores = new Array();
	var filtroVendedoresVendedor = new Array();
	var asignacionesFiltroV = new Array();
	
	

	/*
	 * Filtro Vendedores
	 */
	
	var check = false;
	$( '#todosVendedores' ).on( 'click', function() {
		if(check === false){
			check = true;
		}else{
			check = false;
		}
		
		$('.checkboxFiltroV').prop( "checked", check );
			
	});
		
	$('#btnFiltroVendedores').click(function(){
		$('#todosVendedores').prop( "checked", false );
	});
		
	
	$('#btnFiltroV').click(function(){	
		$('.checkboxFiltroV:checked').each(function(){
			if($(this).attr('checked',true)){
				idVendedor = $(this).attr('id')
				filtroVendedores.push(idVendedor);
			}
		});
		
		console.log(filtroVendedores);
		var vendedoresArray = new Array();
		var datosEconomicosArray = new Array();
		
		for(var e in vendedoresDatosEconomicos){
			vendedor = vendedoresDatosEconomicos[e];
			for(var a in filtroVendedores){
				if(filtroVendedores[a]*1 === vendedor.idVendedorAsignacion*1){
					vendedoresArray.push(vendedor);
				}
			}
		}
//		console.log(vendedoresArray);
		for(var a in vendedoresArray){
			vendedorA = vendedoresArray[a];
			for(var e in datosEconomicos){
				datoEconomico = datosEconomicos[e];
				if(datoEconomico.idPreAsignacionAE === vendedorA.idDatosEconomicos){
					datosEconomicosArray.push(datoEconomico);
				}
			}
		}
		
		filtroVendedores.length = 0;
		asignacionesFiltroV.length =0;
		var dataTable = asignaData(datosEconomicosArray);
//		console.log(dataTable);
		$('#facturaTable').bootstrapTable('load', dataTable)
		$('#facturaTable').bootstrapTable({data : dataTable})
	});
	
	$('#btnFiltroV').click(function(){	
		$('.checkboxFiltroV:checked').each(function(){
			if($(this).attr('checked',true)){
				idVendedor = $(this).attr('id')
				filtroVendedores.push(idVendedor);
			}
		});
		
		console.log(filtroVendedores);
		var vendedoresArray = new Array();
		var datosEconomicosArray = new Array();
		
		for(var e in vendedoresDatosEconomicos){
			vendedor = vendedoresDatosEconomicos[e];
			for(var a in filtroVendedores){
				if(filtroVendedores[a]*1 === vendedor.idVendedorAsignacion*1){
					vendedoresArray.push(vendedor);
				}
			}
		}
//		console.log(vendedoresArray);
		for(var a in vendedoresArray){
			vendedorA = vendedoresArray[a];
			for(var e in datosEconomicos){
				datoEconomico = datosEconomicos[e];
				if(datoEconomico.idPreAsignacionAE === vendedorA.idDatosEconomicos){
					datosEconomicosArray.push(datoEconomico);
				}
			}
		}
		
		filtroVendedores.length = 0;
		asignacionesFiltroV.length =0;
		var dataTable = asignaData(datosEconomicosArray);
//		console.log(dataTable);
		$('#facturaTable').bootstrapTable('load', dataTable)
		$('#facturaTable').bootstrapTable({data : dataTable})
	});
	
	
	
$('#btnFiltroFechasActualizar').click(function (){
		 $('#fechaFinal').val("");
		 $('#fechaInicial').val("");
		 $('#tipoFecha').val("");
	});
		
	
				
}); // Fin Jquery


var datosEconomicosFechas = new Array();
function actualizaFechas(){
	console.log(datosEconomicosFechas);
	var dataTable = asignaData(datosEconomicosFechas);
	$('#facturaTable').bootstrapTable('load', dataTable)
	$('#facturaTable').bootstrapTable({data : dataTable})
}
		
function formatoFechaEmision(){
	var fechaEmision = $('#fechaEmision').val()
	$('#labelFechaEmision').text(fechaEmision);
	var elementoPicker = $datepicker1.pickadate('picker');	
	var asignaFecha = elementoPicker.get('select', 'mm/dd/yyyy');
	$('#fechaEmisionFormat').val(asignaFecha);
}

function formatoFechaPago(){
	var fechaPago = $('#fechaPago').val()
	$('#labelFechaPago').text(fechaPago);
	var elementoPicker = $datepicker.pickadate('picker');	
	var asignaFecha = elementoPicker.get('select', 'mm/dd/yyyy');
	$('#fechaPagoFormat').val(asignaFecha);
}

function formatoFechaPromesaPago(){
	var fechaPromesaPago = $('#fechaPromesaPago').val()
	$('#labelFechaPromesaPago').text(fechaPromesaPago);
	var elementoPicker = $datepicker3.pickadate('picker');	
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
	var elementoPicker = $datepicker2.pickadate('picker');	
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

function formatoFechaInicial(){
	var fechaFinal = $('#fechaFinal').val();
	var fechaInicial = $('#fechaInicial').val();
	if(isEmpty(fechaInicial) && isEmpty(fechaFinal)){
		formatoFechaFinal();
	}
}

function formatoFechaFinal(){
	var dataFechas = new Array();
	var fechaFinal = $('#fechaFinal').val();
	var fechaInicial = $('#fechaInicial').val();
	var tipoFecha = $('#tipoFecha').val();
	
	$('#labelInicial').text(fechaInicial);
	var elementoPicker1 = $datepicker4.pickadate('picker');	
	var asignaFecha1 = elementoPicker1.get('select', 'mm/dd/yyyy');
	$('#fechaInicialFormat').val(asignaFecha1);
	
	$('#labelFechaFinal').text(fechaFinal);
	var elementoPicker2 = $datepicker5.pickadate('picker');	
	var asignaFecha2 = elementoPicker2.get('select', 'mm/dd/yyyy');
	$('#fechaFinalFormat').val(asignaFecha2);
	
	if(isEmpty(fechaInicial) && isEmpty(fechaFinal) && tipoFecha !== ""){
		$('#btnFiltroFechasActualizar').attr("disabled", false);
		var  convertTipo = convertTipoFecha(tipoFecha);
		new Date(asignaFecha1), new Date(asignaFecha2)
		for(var a in datosEconomicos){
		  var datoEconomico = datosEconomicos[a];
		  if(datoEconomico.estatusDatoEconomico === convertTipo){
			  if(validateFechas(new Date(asignaFecha1), new Date(asignaFecha2), datoEconomico, tipoFecha)){
				  dataFechas.push(datoEconomico);
			  }
		  }
	   }
	}
	datosEconomicosFechas = dataFechas;

}


function validaTipoFecha(){
	var tipoFecha = $('#tipoFecha').val();
	var fechaInicial = $('#fechaInicialFormat').val();
	var fechaFinal = $('#fechaFinalFormat').val();
	
	if(tipoFecha !== ""){
		$('#fechaInicial').attr("disabled", false);
		$('#fechaFinal').attr("disabled", false);
	}
}

function validateFechas(fechaInicial, fechaFinal, datoEconomico, tipoFecha){
	var flag = false;
	var fechaRegistro; 
	
	switch(tipoFecha) {
	  case "EMISION":
		  fechaRegistro = new Date(datoEconomico.fechaEmisionFormat);
	    break;
	  case "CONFIRMACION":
		  fechaRegistro = new Date(datoEconomico.formAEFechaConfirmacionFormat);
	    break;
	  case "PROMESA":
		  fechaRegistro = new Date(datoEconomico.formAEFechaPromesaPagoFormat);
	    break;
	  case "PAGO":
		  fechaRegistro = new Date(datoEconomico.fechaPagoFormat);
		break;
	}
	if((fechaInicial <= fechaRegistro)  && (fechaRegistro <= fechaFinal)){
		flag = true;
	}
	return flag;
}

function convertTipoFecha(tipoFecha){
	var fechaRegistro; 
	
	switch(tipoFecha) {
	  case "EMISION":
		  fechaRegistro = "VIGENTE";
	    break;
	  case "CONFIRMACION":
		  fechaRegistro = "VIGENTE";
	    break;
	  case "PROMESA":
		  fechaRegistro = "PENDIENTE";
	    break;
	  case "PAGO":
		  fechaRegistro = "PAGADA";
		break;
	}
	
	return fechaRegistro;
}

function isEmpty(str) {
    return (str || 0 !== str.length);
}






























	

//	function hoy() {
//		var d = new Date();
//		var dia = d.getDate();
//		var mes = (d.getMonth() + 1);
//		var anio = d.getFullYear();
//		if (dia < 10)
//			dia = "0" + dia.toString();
//		if (mes < 10)
//			mes = "0" + mes.toString();
//		var today = anio + '-' + mes + '-' + dia;
////		//console.log(today);
//		return today;
//	}
//	
//	function hoyCambioEstatus() {
//		var d = new Date();
//		var dia = d.getDate();
//		var mes = (d.getMonth() + 1);
//		var anio = d.getFullYear();
//		var today = mes + '/' + dia + '/' + anio;
//		return today;
//	}
//	
//	function convierteMX(str){
//		var tmp = str.split("/");
//		return tmp[1]+"/"+tmp[0]+"/"+tmp[2];
//	}
//
//			
//	
//	function fechaPlus(dia){
//		var fecha = new Date();
//		fecha.setDate(fecha.getDate() + dia);		
//		return fecha;
//	}
//	
//			
//	function getFecha(fechaValor){
////		fechaValor = fechaValor.split('/')
//		var d = new Date(fechaValor);
//		var dia = d.getDate();
//		var mes = (d.getMonth() + 1);
//		var anio = d.getFullYear();
//		if (dia < 10)
//			dia = "0" + dia.toString();
//		if (mes < 10)
//			mes = "0" + mes.toString();
//		var fecha = anio + '-' + mes + '-' + dia+ 'T';
////		//console.log(fecha);
//		return fecha;
//	}
//	
//	
//	
//		
//	function cambiaFormatoFecha(fecha){
//		fecha = fecha.split("/");
//		return fecha[1]+"/"+fecha[0]+"/"+fecha[2];
//	}
//	function cambiaFormatoFechaDisponible(fecha){
//		fecha = fecha.split("-");
//		return fecha[1]+"/"+fecha[2].substring(0,2)+"/"+fecha[0];
//	}
//	
	
	
	
	
	
	

	
	// fin de documento