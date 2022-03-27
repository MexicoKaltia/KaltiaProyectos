
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
//	console.log(asignaciones);
	
	window.operateEventsUpdateVendedor = {
			
		    'click .like': function (e, value, row, index) {
		    	
		    	console.log(row);
		    	
		    	$('#modalNombreVendedor').text(row.nombreVendedor);
		    	
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
		    	
		    	for(var a in preAsignaciones){
		    		var preAsignacion = preAsignaciones[a];
		    		var fechaInicioFactura ="";
		    		var fechaFinFactura ="";
		    		if(preAsignacion.fechaInicioFactura){
		    			fechaInicioFactura = preAsignacion.fechaInicioFactura
		    		}
		    		if(preAsignacion.fechaFinFactura){
		    			fechaFinFactura = preAsignacion.fechaFinFactura
		    		}
		    			
		    		var fechasFactura = "<li>id Asignacion : " + preAsignacion.idPreAsignacion + " - "+ preAsignacion.fechaAsignacion + " - " + preAsignacion.cursoAsignacion + " - " + preAsignacion.clienteAsignacion + " - FECHAS : <b>" + fechaInicioFactura +"-"+fechaFinFactura+"</b>" ;
    				$('#ulFechasPago').append(fechasFactura);
		    		if(preAsignacion.userCreateAsignacion*1 === row.idVendedor*1){
		    			if(preAsignacion.idStatusAsignacion < 5){//preAsignados
		    				preAsignacionesCount++;
		    			}else{
		    				eventosAsignadosCount++;
		    				for(var i in preAsignacionesAE){
		    					var preAsignacionAE = preAsignacionesAE[i];
		    					if(preAsignacionAE.idPreAsignacionAE*1 === preAsignacion.idPreAsignacionAE*1){
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
		    						etiquetas.push(preAsignacion.idPreAsignacion);
		    					}
		    				}		    				
		    			} 
		    		}
		    	}
		    	
		    	for(var e in asignaciones){
		    		var asignacion = asignaciones[a];
		    		if(asignacion.userCreateAsignacion*1 === row.idVendedor*1){
		    			if(asignacion.statusAsignacion === "Evento Cancelado"){
		    				cancelacionesCount++;
		    			}
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
		    	  'PreAsignados',
		    	  'Eventos Asignados - Factura Integrada',
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
		    	const myChartC = new Chart(ctx1, {
		    	  type: 'doughnut',
		    	  data: data,
		    	  
		    	});
		    	
		    	/*
		    	 * chart BARRAS
		    	 */
		        const ctx = document.getElementById('myChart').getContext('2d');
		        const myChart = new Chart(ctx, {
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
    
}); // fin jquery
		    	
	
		    	
		    	
		    	
		    	
	function calculaBonoTrimestral(facturaMinus, facturaPlus){
		if(facturaMinus > 1){
			return 0;
		}
		if(facturaPlus < 500000){
			return 0;
		}
		console.log(facturaPlus*0.06);
		return facturaPlus*0.06; 
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	