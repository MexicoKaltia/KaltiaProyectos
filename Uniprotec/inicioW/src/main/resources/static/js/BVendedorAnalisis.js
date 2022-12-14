
 function operateFormatterUpdate(value, row, index) {
	    return [
	      '<a class="like" href="javascript:void(0)" title="Editar" id="UserUpdate" data-toggle="modal" data-target="#modalRegistro">',
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
	  

	  function alerta(){
	  }
	 

	  
$(document).ready(function(){
	
	console.log(preAsignaciones);
	console.log(preAsignacionesAE);
	console.log(asignaciones);
	var myChartC, myChart;
	var userSelect = 0;
	
	window.operateEventsUpdateVendedor = {
			
		    'click .like': function (e, value, row, index) {
		    	
		    	console.log(row);
		    	userSelect = row.usuarioVendedor.idUsuario;
		    	var nombreVendedor = row.nombreVendedor;
		    	$('#modalNombreVendedorAnalisis').text(nombreVendedor);
		    	
		    	var eventosAsignadosCount=0;
		    	var preAsignacionesCount=0;
		    	var cancelacionesCount=0;
		    	
		    	var facturaPlus=0;
		    	var facturaMinus=0;
		    	var facturaTotal=0;
		    	
		    	var dataSetBar = new Array();
		    	const rojoBk = 'rgba(255, 99, 132, 0.2)';
		        const azulBk = 'rgba(54, 162, 235, 0.2)';
		        const rojoBr = 'rgba(255, 99, 132, 1)';
		        const azulBr = 'rgba(54, 162, 235, 1)';
		        
		        var coloBar;
		        var coloBor;
		        
		        var arrayColorBar = new Array();
		        var arrayBorderColorBar = new Array();
		        
		        var etiquetas = new Array();
		    	
		    	for(var a in asignaciones){
		    		var preAsignacion = asignaciones[a];
		    		var fechaInicioFactura ="";
		    		var fechaFinFactura ="";
	    			
		    		if(preAsignacion.userCreateAsignacion*1 === userSelect*1){
		    			if(preAsignacion.statusAsignacion === "Evento Cancelado"){
		    				cancelacionesCount++;
		    			}
		    			if(validateDatosEconomicos(preAsignacion.idAsignacion)){//preAsignados
		    				preAsignacionesCount++;
		    				for(var i in preAsignacionesAE){
		    					var preAsignacionAE = preAsignacionesAE[i];
		    					if(preAsignacionAE.formAEidPreAsignacion*1 === preAsignacion.idAsignacion*1){
		    						if(preAsignacionAE.formAERegla3PorcentajeNuevaComisionReal >= 99){
			    							facturaPlus = facturaPlus + preAsignacionAE.formAEPrecioVentaReal*1;
			    							colorBar = azulBk; 
			    							colorBor = azulBr
		    						}else{
			    							facturaMinus = facturaMinus + preAsignacionAE.formAEPrecioVentaReal*1;
			    							colorBar = rojoBk; 
			    							colorBor = rojoBr
		    						}
		    						facturaTotal = facturaTotal + preAsignacionAE.formAEPrecioVentaReal*1;
		    						dataSetBar.push(preAsignacionAE.formAEPrecioVentaReal*1);
		    						arrayColorBar.push(colorBar);
		    						arrayBorderColorBar.push(colorBor);
		    						etiquetas.push(preAsignacion.idAsignacion);
		    					}
		    				}
		    			}else{
		    				eventosAsignadosCount++;
		    						    				
		    			} 
//		    			$('#seguimientoBitacora').empty();
//		    			$('#seguimientoBitacora').append(preAsignacion.seguimiento);
		    		}
		    	}
		    	
		    	
		    	
			    	
		    	$('#totalAsignaciones').text(eventosAsignadosCount*1 + preAsignacionesCount*1 + cancelacionesCount*1 );
		    	$('#eventosAsignados').text(eventosAsignadosCount);
		    	$('#preAsignaciones').text(preAsignacionesCount);
		    	$('#cancelaciones').text(cancelacionesCount);
		    	
		    	$('#facturaPlus').text(facturaPlus);
		    	$('#facturaMinus').text(facturaMinus);
		    	$('#facturaTotal').text(facturaMinus + facturaPlus);
		    	$('#bonoTrimestral').text(calculaBonoTrimestral(facturaMinus, facturaPlus))
		    	
		    	
		    	
		    	/*
		    	 * chart DONA
		    	 */
		    	const ctx1 = document.getElementById('circleChart').getContext('2d');
		    	 const data = {
		    	labels: [
		    	  'Datos Económicos - Factura Integrada',
		    	  'Eventos Asignados',
		    	  'Cancelados'
		    	],
		    	datasets: [{
		    	  label: 'My First Dataset',
		    	  data: [preAsignacionesCount, eventosAsignadosCount, cancelacionesCount],
		    	  backgroundColor: [
		    	  'rgba(54, 162, 235, 0.8)',
		    	  'rgba(75, 192, 192, 0.7)',
		    	  'rgba(255, 99, 132, 0.5)'
		    	  ],
		    	  hoverOffset: 4
		    	}]
		    	};
		    	 if (myChartC) {
		    	 		myChartC.destroy();
		    	 	}
		    	 
		    	 myChartC = new Chart(ctx1, {
		    	  type: 'doughnut',
		    	  data: data,
		    	});
		    	
		    	
		    	     
		    	/*
		    	 * chart BARRAS
		    	 */
		        const ctx = document.getElementById('myChart').getContext('2d');
		        if (myChart) {
	    	     	myChart.destroy();
	    	 	}
		        myChart = new Chart(ctx, {
		          type: 'bar',
		          data: {
		              labels: etiquetas,
		              datasets: [{
		                  label: 'Venta Real',
		                  data: dataSetBar,
		                  backgroundColor: arrayColorBar ,
		                  borderColor: arrayBorderColorBar,
		                  borderWidth: 1
		              }]
		          },
		          options: {
		              scales: {
		                  y: {
		                      beginAtZero: true
		                  }
		              }
		          }
		        });
		       
		    }
		   }

	$('#vendedoresAnalisisTable').bootstrapTable({data : $data})
	
	$('#divFacturaTotal').click(function(){
		console.log('#divFacturaTotal');
		$('#modalDivDetalleFactura').empty();
		
		for(var a in asignaciones){
    		var preAsignacion = asignaciones[a];
    		if(preAsignacion.userCreateAsignacion*1 === userSelect*1){
    			$('#modalNombreDetalleFactura').text(preAsignacion.userCreateAsignacionTexto);
    			if(validateDatosEconomicos(preAsignacion.idAsignacion)){//preAsignados
    				for(var i in preAsignacionesAE){
    					var preAsignacionAE = preAsignacionesAE[i];
    					if(preAsignacionAE.formAEidPreAsignacion*1 === preAsignacion.idAsignacion*1){
    						if(preAsignacionAE.formAERegla3PorcentajeNuevaComisionReal >= 99){
    							var divDetalleFactura = '<div class="input-group" >\
						    								<div class="input-group-prepend">\
							    							    <div class="input-group-text">\
							    							      <input type="checkbox" class="detalleFactura" id="'+preAsignacion.idAsignacion+'" checked>\
							    							    </div>\
							    							</div>\
						    							    <div class="alert alert-success">\
															  <h6><span>id Asignacion : </span><strong><span>'+preAsignacion.idAsignacion+'</span></strong>\
															  <span> - Factura : </span><strong><span>'+preAsignacion.numeroFactura+'</span></strong>\
															  <span> - Monto : </span><strong><span>'+formatter.format(preAsignacionAE.formAEPrecioVentaReal)+'</span></strong></h6>\
															</div>\
						    							</div>';
    													
    						}else{
    							var divDetalleFactura = '<div class="input-group" >\
						    								<div class="input-group-prepend">\
						    							    <div class="input-group-text">\
						    							      <input type="checkbox" class="detalleFactura" id="'+preAsignacion.idAsignacion+'" checked>\
						    							    </div>\
						    							</div>\
													    <div class="alert alert-warning">\
														  <h6><span>id Asignacion : </span><strong><span>'+preAsignacion.idAsignacion+'</span></strong>\
														  <span> - Factura : </span><strong><span>'+preAsignacion.numeroFactura+'</span></strong>\
														  <span> - Monto : </span><strong><span>'+formatter.format(preAsignacionAE.formAEPrecioVentaReal)+'</span></strong></h6>\
														</div>\
													</div>';	
    						}
    						$('#modalDivDetalleFactura').append(divDetalleFactura);
    					}
    				}
    			}
    		}
    	}
		
		$('#modalDetalleFactura').modal("show");
		
		$('#btnActualizaSumaFacturas').click(function(){
			var filtroFacturas = new Array();
			var filtroFacturasMinus = 0;
			var filtroFacturasPlus = 0;
			$('.detalleFactura:checked').each(function(){
				if($(this).attr('checked',true)){
					var idAsignacion = $(this).attr('id')
					filtroFacturas.push(idAsignacion);
				}
			});
			var facturaTotal = 0;
			for(e in preAsignacionesAE){
				var preAsignacionAE  = preAsignacionesAE[e];
				for(a in filtroFacturas){
					var idAsignacion = filtroFacturas[a];
					if(idAsignacion*1 === preAsignacionAE.formAEidPreAsignacion){
						if(preAsignacionAE.formAERegla3PorcentajeNuevaComisionReal > 99){
							filtroFacturasPlus = filtroFacturasPlus + preAsignacionAE.formAEPrecioVentaReal;
						}else{
							filtroFacturasMinus = filtroFacturasMinus + preAsignacionAE.formAEPrecioVentaReal;
						}
						facturaTotal = facturaTotal + preAsignacionAE.formAEPrecioVentaReal*1;
					}
						
				}
			}
			console.log(filtroFacturasMinus);
			console.log(filtroFacturasPlus);
			$('#facturaTotal').text(facturaTotal);
			$('#bonoTrimestral').text(calculaBonoTrimestral(filtroFacturasMinus, filtroFacturasPlus))
		});
		
	});
	
    
}); // fin jquery

		    	
	function validateDatosEconomicos(idAsignacion){
		for(var a in preAsignacionesAE){
			var preAsignacionAE = preAsignacionesAE[a];
			if(preAsignacionAE.formAEidPreAsignacion*1 === idAsignacion*1){
				return true;
			}
		}
		return false;
	}
		    	
		    	
		    	
		    	
	function calculaBonoTrimestral(facturaMinus, facturaPlus){
		if(facturaMinus > 1){
			return 0;
		}
		if(facturaPlus < 500000){
			return 0;
		}
		
		return formatter.format(facturaPlus*0.06); 
		
	}
	
	
	const formatter = new Intl.NumberFormat('es-MX', {
	      style: 'currency',
	      currency: 'MXN',
	      minimumFractionDigits: 0
	    });
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	