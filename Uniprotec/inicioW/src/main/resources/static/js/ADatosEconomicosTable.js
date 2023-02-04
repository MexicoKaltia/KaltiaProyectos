
 function operateFormatterUpdate(value, row, index) {
	    return [
	      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalEdicionDatosEconomicos">',
	      '<i class="fa fa-2x fa-braille"></i>',
	      '</a>'
	    ].join('')
	  }
	  
 function operateFormatterDelete(value, row, index) {
	 return [
	     '<a class="remove" href="javascript:void(0)" title="Eliminar">',
	     '<i class="fa fa-2x fa-address-book">',
	     '</a>'
	   ].join('')
	}
	  

	

	  
$(document).ready(function(){
	
	
	$arrayDatosEconomicosSin = new Array();
	
	for(var a in datosEconomicos){
		var datoEconomico = datosEconomicos[a];
		if(datoEconomico.status === "SIN_ASIGNACION"){
			$arrayDatosEconomicosSin.push(datoEconomico);
		}
	}
	
	console.log($arrayDatosEconomicosSin);
		
		
	window.operateEventsUpdateDE = {
			
		    'click .like': function (e, value, row, index) {
		    	console.log(row);
		    	asignacionSelect = row;
		    	$('#labelNumFacturaEdicion').text(row.formAENumFactura);
		    	$('#labelProspectoClienteEdicion').text(row.formAEClienteTexto);
		    	$('#labelVentaRealEdicion').text(row.formAEPrecioVentaReal);
		    	$('#labelPorcentajeVentaEdicion').text(row.formAEPorcentajeVentaReal);
//		    	$('#labelVendedorEdicion').text(row.);
		    	$('#labelFechaConfirmacionEdicion').text(row.formAEFechaConfirmacion);
		    	$('#labelFechaPromesaPagoEdicion').text(row.formAEFechaPromesaPago);
		    	$('#labelViaticosTotalesEdicion').text(row.formAEViaticosTotal);
		    	$('#labelObservacionesEdicion').text(row.formAEObservaciones);
		    	
		    	
	   	   }
	}
		    	
	
	$('#datosEconomicosSinTable').bootstrapTable({data : $arrayDatosEconomicosSin})
	
		
    
}); // fin jquery


	
	
	
	
	
	
	
	
	
	
	
	
	