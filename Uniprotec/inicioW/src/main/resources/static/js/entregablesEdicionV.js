
var $idEntregable ;
var $idEntregableLogico ;
$(document).ready(function() {
	
//	console.log(asignacionItem);
//	console.log(asignacionCliente);
	console.log(entregables);
//	console.log(entregables.length);
//	console.log(participantes);
//	console.log(participantes.length);
	var e=0;
	var $participantes;
	var $entregable;
	var $participantesLength = 0;
	var $estatusEntregable ="";
//	var $idEntregable ;
	var entregableRFC;
	var entregableLogo;
	var idEntregable
	for(var a in entregables){
		e++;
		entregableRFC = entregables[a].rfcoriginalAsignacion;
		entregableLogo = entregables[a].formALogo;
	}
	
	
	$("#idAsignacionEncabezado").html(asignacionItem.idAsignacion);
	$(".encabezadoCliente").text(asignacionItem.clienteAsignacion);
	$(".encabezadoCurso").html(asignacionItem.cursoAsignacion);
	$(".encabezadoInstructor").html(asignacionItem.instructorAsignacion);
	$('.imagenLogoClientePrev').attr('src', "/uploads/img/"+entregableRFC+"/"+entregableLogo);
    
    
	if(e > 0){
		console.log("entregable existente");
		var listEntregables = '<div class="card-body"><h5 class="card-title">cuenta con los siguientes expedientes de Documentacion:</h5>\
									<ul class="list-group list-group-flush" id="listEntregables">\
									</ul>\
								</div>';
		$('#tituloModalEntregables').after(listEntregables);
		var e =0;
		for(var a in entregables){
			var entregable = entregables[a];
			entregableRFC = entregable.rfcoriginalAsignacion;
			entregableLogo = entregable.formALogo;
			idEntregable = entregable.idEntregable;
			console.log(idEntregable);
			var entregableItem = '<a id="'+idEntregable+'" href="/verEntregable/'+entregableRFC+'-'+entregable.idAsignacion+'_'+e+'"  class="entregable list-group-item">'+idEntregable+'-'+entregable.formACurso+'</a>';
			$('.listEntregables').append(entregableItem);
			e++;
		}
		
	}

	$('#modalEntregables').modal();
	
		
			
});  // Fin JQRY

