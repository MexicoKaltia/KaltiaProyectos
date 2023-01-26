
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
	
//	console.log(preAsignaciones);
	console.log(preAsignacionesAE);
	console.log(asignaciones);
	console.log(vendedoresDatosEconomicos);
	var myChartC, myChart;
	var userSelect = 0;
	var idVendedorDE = 0;
	var arrayAsignaciones = new Array();
	
	window.operateEventsUpdateVendedor = {
			
		    'click .like': function (e, value, row, index) {
		    	
		    	console.log(row);
		    	userSelect = row.usuarioVendedor.idUsuario;
		    	idVendedorDE = row.idVendedor;
		    	var nombreVendedor = row.nombreVendedor;
		    	$('#modalNombreVendedorAnalisis').text(nombreVendedor);
		    	
//		    	console.log(idVendedorDE);
		    	
		    	var eventosAsignadosCount=0;
		    	var eventosAsignadosSecCount =0;
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
		        arrayAsignaciones.length = 0;
		    	
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
		    				arrayAsignaciones.push(preAsignacion.idAsignacion);
		    				for(var i in preAsignacionesAE){//buscar en Datos Economicos
		    					var preAsignacionAE = preAsignacionesAE[i];
		    					if(preAsignacionAE.formAEidPreAsignacion*1 === preAsignacion.idAsignacion*1){
		    						for(var o in vendedoresDatosEconomicos){
		    							var vendedoreDE = vendedoresDatosEconomicos[o];
		    							if(vendedoreDE.idAsignacion === preAsignacion.idAsignacion && vendedoreDE.idVendedorAsignacion*1 === idVendedorDE*1){
		    								console.log("INCLUDE"+ vendedoreDE.idAsignacion*1);
		    								console.log(vendedoreDE.nombreVendedor);
		    								if(preAsignacionAE.formAERegla3PorcentajeNuevaComisionReal > 99){
		    									console.log("positivo"+ preAsignacionAE.formAERegla3PorcentajeNuevaComisionReal *1);
				    							facturaPlus = facturaPlus + vendedoreDE.montoFacturaDivida*1;
				    							colorBar = azulBk; 
				    							colorBor = azulBr;
				    						}else{
					    							facturaMinus = facturaMinus + vendedoreDE.montoFacturaDivida*1;
					    							colorBar = rojoBk; 
					    							colorBor = rojoBr;
				    						}
				    						facturaTotal = facturaTotal + vendedoreDE.montoFacturaDivida*1;
				    						dataSetBar.push(vendedoreDE.montoFacturaDivida*1);
				    						arrayColorBar.push(colorBar);
				    						arrayBorderColorBar.push(colorBor);
				    						etiquetas.push(preAsignacion.idAsignacion);
		    							}
		    						}
		    					}
		    				}
		    			}else{
		    				eventosAsignadosCount++;
//		    				arrayAsignaciones.push(preAsignacion);
		    			}
		    		}
		    	}
		    	
		    	console.log(arrayAsignaciones);
		    	
		    	var arrayDatosEconomicosVendedor = new Array(); 
		    	for(var a in vendedoresDatosEconomicos){
		    		var vendedorDE = vendedoresDatosEconomicos[a];
//		    		console.log(vendedorDE.idVendedorAsignacion);
					if(idVendedorDE === vendedorDE.idVendedorAsignacion){
						arrayDatosEconomicosVendedor.push(vendedorDE);
					}
		    	}
		    	
		    	for(var a in arrayDatosEconomicosVendedor){
		    		var vendedorDE = arrayDatosEconomicosVendedor[a];
		    		if(!arrayAsignaciones.includes(vendedorDE.idAsignacion*1)){
		    			for(var i in preAsignacionesAE){//buscar en Datos Economicos
		    				console.log("no includes "+ vendedorDE.idAsignacion*1);
	    					var preAsignacionAE = preAsignacionesAE[i];
							if(vendedorDE.idDatosEconomicos === preAsignacionAE.idPreAsignacionAE){
								preAsignacionesCount++;
								eventosAsignadosSecCount++;
								if(preAsignacionAE.formAERegla3PorcentajeNuevaComisionReal > 99){
	    							facturaPlus = facturaPlus + vendedorDE.montoFacturaDivida*1;
	    							colorBar = azulBk; 
	    							colorBor = azulBr;
	    						}else{
		    							facturaMinus = facturaMinus + vendedorDE.montoFacturaDivida*1;
		    							colorBar = rojoBk; 
		    							colorBor = rojoBr;
	    						}
	    						facturaTotal = facturaTotal + vendedorDE.montoFacturaDivida*1;
	    						dataSetBar.push(vendedorDE.montoFacturaDivida*1);
	    						arrayColorBar.push(colorBar);
	    						arrayBorderColorBar.push(colorBor);
	    						etiquetas.push(vendedorDE.idAsignacion);
							}
						}
		    		}
		    	}
			    	
		    	$('#totalAsignaciones').text(eventosAsignadosCount*1 + preAsignacionesCount*1 + cancelacionesCount*1 );
		    	$('#eventosAsignados').text(eventosAsignadosCount);
		    	$('#eventosAsignadosSec').text(eventosAsignadosSecCount);
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
		    	  data: [preAsignacionesCount, eventosAsignadosCount + eventosAsignadosSecCount, cancelacionesCount],
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
		                  label: 'Venta Real Divida',
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
															  <span> - Monto : </span><strong><span>'+formatter.format(getMontoFacturaDividida(preAsignacionAE.idPreAsignacionAE))+'</span></strong></h6>\
															  <span> - Cliente : </span><strong><span>'+preAsignacion.clienteAsignacion+'</span></strong><br>\
															  <span> - Curso : </span><strong><span>'+preAsignacion.cursoAsignacion+'</span></strong></h6>\
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
														  <span> - Monto : </span><strong><span>'+formatter.format(getMontoFacturaDividida(preAsignacionAE.idPreAsignacionAE))+'</span></strong></h6>\
														  <span> - Cliente : </span><strong><span>'+preAsignacion.clienteAsignacion+'</span></strong><br>\
														  <span> - Curso : </span><strong><span>'+preAsignacion.cursoAsignacion+'</span></strong></h6>\
														</div>\
													</div>';	
    						}
    						$('#modalDivDetalleFactura').append(divDetalleFactura);
    					}
    				}
    			}
    		}
    	}
		
    	var arrayDatosEconomicosVendedor = new Array(); 
    	for(var a in vendedoresDatosEconomicos){
    		var vendedorDE = vendedoresDatosEconomicos[a];
//    		console.log(vendedorDE.idVendedorAsignacion);
			if(idVendedorDE === vendedorDE.idVendedorAsignacion){
				arrayDatosEconomicosVendedor.push(vendedorDE);
			}
    	}
		
    	for(var a in arrayDatosEconomicosVendedor){
    		var vendedorDE = arrayDatosEconomicosVendedor[a];
    		if(!arrayAsignaciones.includes(vendedorDE.idAsignacion)){
    			for(var i in preAsignacionesAE){//buscar en Datos Economicos
					var preAsignacionAE = preAsignacionesAE[i];
					if(vendedorDE.idDatosEconomicos === preAsignacionAE.idPreAsignacionAE){
						for(var e in asignaciones){
							var asignacion = asignaciones[e];
							if(asignacion.idAsignacion === preAsignacionAE.formAEidPreAsignacion){
								if(preAsignacionAE.formAERegla3PorcentajeNuevaComisionReal >= 99){
	    							var divDetalleFactura = '<div class="input-group" >\
							    								<div class="input-group-prepend">\
								    							    <div class="input-group-text">\
								    							      <input type="checkbox" class="detalleFactura" id="'+preAsignacionAE.formAEidPreAsignacion+'" checked>\
								    							    </div>\
								    							</div>\
							    							    <div class="alert alert-success">\
																  <h6><span>id Asignacion : </span><strong><span>'+preAsignacionAE.formAEidPreAsignacion+'</span></strong>\
																  <span> - Factura : </span><strong><span>'+asignacion.numeroFactura+'</span></strong>\
																  <span> - Monto : </span><strong><span>'+formatter.format(getMontoFacturaDividida(preAsignacionAE.idPreAsignacionAE))+'</span></strong></h6>\
																  <span> - Cliente : </span><strong><span>'+asignacion.clienteAsignacion+'</span></strong><br>\
																  <span> - Curso : </span><strong><span>'+asignacion.cursoAsignacion+'</span></strong></h6>\
																</div>\
							    							</div>';
	    													
		    						}else{
		    							var divDetalleFactura = '<div class="input-group" >\
								    								<div class="input-group-prepend">\
								    							    <div class="input-group-text">\
								    							      <input type="checkbox" class="detalleFactura" id="'+preAsignacionAE.formAEidPreAsignacion+'" checked>\
								    							    </div>\
								    							</div>\
															    <div class="alert alert-warning">\
															    <h6><span>id Asignacion : </span><strong><span>'+preAsignacionAE.formAEidPreAsignacion+'</span></strong>\
																  <span> - Factura : </span><strong><span>'+asignacion.numeroFactura+'</span></strong>\
																  <span> - Monto : </span><strong><span>'+formatter.format(getMontoFacturaDividida(preAsignacionAE.idPreAsignacionAE))+'</span></strong></h6>\
																  <span> - Cliente : </span><strong><span>'+asignacion.clienteAsignacion+'</span></strong><br>\
																  <span> - Curso : </span><strong><span>'+asignacion.cursoAsignacion+'</span></strong></h6>\
																</div>\
															</div>';	
		    						}
		    						$('#modalDivDetalleFactura').append(divDetalleFactura);
								}
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
						for(var i in vendedoresDatosEconomicos){
							var vendedorDE = vendedoresDatosEconomicos[i];
							if(vendedorDE.idDatosEconomicos*1 === preAsignacionAE.idPreAsignacionAE*1){
								if(preAsignacionAE.formAERegla3PorcentajeNuevaComisionReal > 99){
									filtroFacturasPlus = filtroFacturasPlus + vendedorDE.montoFacturaDivida;
								}else{
									filtroFacturasMinus = filtroFacturasMinus + vendedorDE.montoFacturaDivida;
								}
								facturaTotal = facturaTotal + vendedorDE.montoFacturaDivida;
							}
						}
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


	function getMontoFacturaDividida(idDatosEconomicos){
		var montoFacturaDividida = 0;
		for(var a in vendedoresDatosEconomicos){
			var vendedorDE = vendedoresDatosEconomicos[a];
			if(vendedorDE.idDatosEconomicos*1 === idDatosEconomicos*1){
//				console.log(idDatosEconomicos);
				montoFacturaDividida = vendedorDE.montoFacturaDivida; 
				break;
			}
		}
		return montoFacturaDividida;
	}
		    	
	function validateDatosEconomicos(idAsignacion){
		var flag = false;
//		console.log(idAsignacion);
		for(var a in preAsignacionesAE){
			var preAsignacionAE = preAsignacionesAE[a];
			if(preAsignacionAE.formAEidPreAsignacion*1 === idAsignacion*1){
//				console.log(idAsignacion);
				flag = true;
				break;
			}
		}
		return flag;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	